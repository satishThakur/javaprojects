package actors;

import scala.concurrent.duration.Duration;
import domain.AlarmSyncData;
import domain.DbAlarms;
import domain.NodeAlarms;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;

//http://stackoverflow.com/questions/11897881/akka-getsender-lost-in-future


public class AlarmSyncActor extends UntypedActor {

	private enum State {
		ready, nodeCollected, dbCollected, nodeDbCollected, terminated
	}

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	//initial state is ready
	private State state = State.ready;

	private DbAlarms dbAlarms;
	private NodeAlarms nodeAlarms;
	
	private Long neId;

	private ActorRef dbCollector = getContext().actorOf(new Props(DBCollector.class), "dbCollector");
	private ActorRef nodeCollector = getContext().actorOf(new Props(NodeCollector.class), "nodeCollector");
	private ActorRef alarmMerger = getContext().actorOf(new Props(AlarmMerger.class), "alarmMerger");
	
	//define fault handling strategy.
	//well we simply stop the child which throws whatever exception. 
	
	private static SupervisorStrategy strategy =
		new OneForOneStrategy(10, Duration.create("1 minute"),
				new Function<Throwable, Directive>() {
			@Override
			public Directive apply(Throwable t) {
				
					return SupervisorStrategy.stop();
				
			}
		});
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
	
	@Override
	public void preStart() {
		//we would rather watch all the children for termination
		//as we stop the child in case of any problem.. see the strategy above.		
		getContext().watch(dbCollector);
		getContext().watch(nodeCollector);
		getContext().watch(alarmMerger);
	}

	@Override
	public void onReceive(Object arg) throws Exception {
		//log.info("received: " + arg);
		if(arg instanceof Long) {
			neId = (Long)arg;
			dbCollector.tell(neId, getSelf());
			nodeCollector.tell(neId, getSelf());
			
		}else if(arg instanceof String) {
			if("SUCCESS".equals(String.valueOf(arg))){
				log.info("ALARM SYNC DONE!!");
				doShutDown();
			}else if("FAIL".equals(String.valueOf(arg))) {
				log.info("ALARM SYNC FAILED!!");
				handleFailEvent();
			}
		} else if(arg instanceof DbAlarms) {
			handleDbAlarmEvent(arg);
		} else if(arg instanceof NodeAlarms) {
			handleNodeAlarmEvent(arg);
		}else if(arg instanceof Terminated) {
			handleTermination((Terminated)arg);
		}

	}

	private void handleTermination(Terminated term) {
		//log alarm sync failure...
		log.error("ALARM SYNC FAILED DUE TO : " + term.getActor());
		state = State.terminated;
		doShutDown();
		
	}
	
	private void handleFailEvent(){
		log.error("ALARM SYNC FAILED DUE TO : " + getSender());
		state = State.terminated;
		doShutDown();
	}

	private void doShutDown() {
		log.info("Shutdown AlarmSync For NE: " + neId);
		getContext().stop(getSelf());
		
	}

	private void handleNodeAlarmEvent(Object arg) {
		nodeAlarms = (NodeAlarms)arg;
		if(State.terminated.equals(state)) {
			log.info("Already terminated!!");
		}
		if(State.ready.equals(state)) {
			state = State.nodeCollected;
		}
		if(State.dbCollected.equals(state)) {
			state = State.nodeDbCollected;
		}
		if(State.nodeDbCollected.equals(state)) {
			doAlarmSync();
		}
	}

	private void handleDbAlarmEvent(Object arg) {
		dbAlarms = (DbAlarms)arg;
		if(State.terminated.equals(state)) {
			log.info("Already terminated!!");
		}
		if(State.ready.equals(state)) {
			state = State.dbCollected;
		}
		if(State.nodeCollected.equals(state)) {
			state = State.nodeDbCollected;
		}
		if(State.nodeDbCollected.equals(state)) {
			doAlarmSync();
		}
	}

	private void doAlarmSync() {
		alarmMerger.tell(new AlarmSyncData(dbAlarms, nodeAlarms, neId), getSelf());

	}

}
