package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MyUntypedActor extends UntypedActor{
	//private ActorRef future = getContext().actorOf(new Props(MyUntypedActor.class), "myactorFuture");
	public void onReceive(Object message) {
		
		if(message instanceof String) {
			
		}
		
	}
	
	@Override
	public void preStart() {
		System.out.println(Thread.currentThread().getId() + " Prestart...!!");
		super.preStart();
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef myActor = system.actorOf(new Props(MyUntypedActor.class), "myactor");
		myActor.tell("hello");
		System.out.println( Thread.currentThread().getId() +" World");
	}

}