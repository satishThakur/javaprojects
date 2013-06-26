package actors;

import java.util.concurrent.Callable;

import domain.NodeAlarms;
import scala.concurrent.Future;
import util.ResultUtil;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import static akka.dispatch.Futures.future;

public class NodeCollector extends UntypedActor {
	
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg) throws Exception {
		//log.info("received: " + arg);
		final ActorRef sender = getSender();
		if(arg instanceof Long) {
				final long neId = (Long)arg;
				
				Future<NodeAlarms> f = future(new Callable<NodeAlarms>() {
					public NodeAlarms call() {						
						return getNodeAlarms(neId);
					}
				}, getContext().dispatcher());

				f.onSuccess(new OnSuccess<NodeAlarms>() {
					@Override
					public void onSuccess(NodeAlarms nodeAlarms) throws Throwable {
						log.info("sending NodeAlarm for : " + neId);
						sender.tell(nodeAlarms, getSelf());
					}
				}, getContext().dispatcher());
				
				f.onFailure(new OnFailure() {
					
					@Override
					public void onFailure(Throwable arg) throws Throwable {
						log.info("sending Faiure for : " + neId);
						sender.tell("FAIL", getSelf());						
					}
				}, getContext().dispatcher());
			}

		
	}

	private NodeAlarms getNodeAlarms(long neId) {
		if(ResultUtil.getNextBoolean()) {
		return new NodeAlarms();
		}else {
			log.error("Node Collection failed for NE : " + neId);
			throw new RuntimeException();
		}
		
	}



}
