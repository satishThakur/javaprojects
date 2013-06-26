package fault;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

public class UnhandledMessage extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		unhandled(msg);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("sysys");
		
		ActorRef ref = system.actorFor("dummy");
		
		ref.tell("anything", null);
		

	}

}
