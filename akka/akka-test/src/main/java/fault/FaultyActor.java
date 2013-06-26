package fault;

import scala.Option;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.japi.Function;

public class FaultyActor {

	public static class GoodActor extends UntypedActor {

		private static SupervisorStrategy strategy =
			new OneForOneStrategy(10, Duration.create("1 minute"),
					new Function<Throwable, Directive>() {
				@Override
				public Directive apply(Throwable t) {
					
						return SupervisorStrategy.stop();
					
				}
			});
		
		
		public SupervisorStrategy supervisorStrategy() {
			return strategy;
		}

		private ActorRef child = getContext().actorOf(new Props(FaultActor.class), "FA");
		@Override
		public void onReceive(Object arg0) throws Exception {
			System.out.println("GA: Received: " + arg0);
			if(arg0 instanceof Terminated) {
				Terminated childTerm = (Terminated)arg0;
				System.out.println("Child terminated" + childTerm.actor());
				getContext().stop(getSelf());
				
			}else {
			
			child.tell(arg0, getSelf());
			}

		}

		@Override
		public void preRestart(Throwable reason, Option<Object> message) {
			System.out.println("GA : PRERESTAT");
			super.preRestart(reason, message);
		}

		@Override
		public void preStart() {
			System.out.println("GA : preStart!!");
			getContext().watch(child);
			super.preStart();
		}

	}

	public static class FaultActor extends UntypedActor {

		@Override
		public void onReceive(Object arg0) throws Exception {
			System.out.println("BA: Received: " + arg0 + " " + getSelf());
			throw new RuntimeException();

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("test");
		ActorRef goodActor = system.actorOf(new Props(GoodActor.class), "GA");
		goodActor.tell("anything", null);

		goodActor.tell("anything", null);
		goodActor.tell("anything", null);

	}

}
