package actors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import akka.dispatch.OnSuccess;

public class PromiseTest {
	
	public static class Actor1 extends UntypedActor {
		
		
		@Override
		public void onReceive(Object msg) throws Exception {
			ExecutionContext ec = getContext().dispatcher();
			Promise<List<String>> p = new Producer(getContext().dispatcher()).giveSomePromise();
			System.out.println("got back promise....");
			Future<List<String>> f = p.future();
			
			f.onComplete(new OnComplete<List<String>>() {

				@Override
				public void onComplete(Throwable failure, List<String> result)
						throws Throwable {
					if(failure != null) {
						System.out.println("Failed!!!");
					}else {
						System.out.println("Promise kept : " + result);
					}
					
				}
			}, ec);
			
			
		}
		
	}
	
	public static class Producer {
		private ExecutionContext ec;
		public Producer(ExecutionContext ec) {
			this.ec = ec;
		}
		
		public Promise<List<String>> giveSomePromise() {
			
			final Promise<List<String>> p = Futures.promise();
			
			Future<String> f1 = Futures.future(new Callable<String>() {
				  public String call() throws InterruptedException {
					  Thread.sleep(10000);
				    return "Hello" + "World";
				  }
				}, ec);
			
			f1.onSuccess(new OnSuccess<String>() {

				@Override
				public void onSuccess(String arg) throws Throwable {
					p.success(new ArrayList<String>(Arrays.asList(arg)));
					
				}
				
			}, ec);
			
			return p;
		}

		
	}
	
	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("some");
		ActorRef actor = system.actorOf(new Props(Actor1.class), "actor1");
		
		actor.tell("doSomething", null);
		
		
	}

}
