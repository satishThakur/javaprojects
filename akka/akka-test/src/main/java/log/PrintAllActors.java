package log;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

public class PrintAllActors extends UntypedActor{
	
	public static void printAllActors(ActorRef parent) {
		ActorSystem system;
		if(!parent.isTerminated()){
			
		}
	}
	
	
	public static void printAllActors(ActorSystem system) {
		ActorRef top = system.actorFor("/");
		System.out.println(top.isTerminated());
		System.out.println(top);
	}
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("dummySys");
		printAllActors(system);
		
		
	}


	@Override
	public void onReceive(Object arg0) throws Exception {
		
		
	}

}
