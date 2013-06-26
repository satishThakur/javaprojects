package future;

import java.util.concurrent.Callable;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.dispatch.OnSuccess;

public class FutureActor extends UntypedActor {

	@Override
	public void onReceive(Object arg) throws Exception {
		ExecutionContext ec = getContext().dispatcher();
		if(arg instanceof String) {
			
			System.out.println(arg);
			System.out.println(getSender());
			
			Future<String> f1 = Futures.future(new Callable<String>() {
				  public String call() {
				    return "Hello" + "World";
				  }
				}, ec);
			
			f1.onSuccess(new OnSuccess<String>() {

				@Override
				public void onSuccess(String arg) throws Throwable {
					System.out.println("myself:" + getSelf());
					System.out.println("Sender:" + getSender());
					getSender().tell(arg, getSelf());
					
				}
				
			}, ec);
			
			
		}

	}

}
