package future;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import actors.MyUntypedActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class MainTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef myActor = system.actorOf(new Props(MyUntypedActor.class), "myactor");
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		Future<Object> future = Patterns.ask(myActor, "test", timeout);
		
		String result = (String) Await.result(future, timeout.duration());
		
		System.out.println("got it" + " " + result);
		

	}

}
