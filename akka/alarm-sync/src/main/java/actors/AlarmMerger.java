package actors;

import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import scala.concurrent.Future;
import domain.AlarmSyncData;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AlarmMerger extends UntypedActor {
	
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg) throws Exception {
		log.info("received: " + arg);
		final ActorRef sender = getSender();
		if(arg instanceof AlarmSyncData) {
			
				Future<String> f = future(new Callable<String>() {
					public String call() {
						return mergeAlarms();
					}
				}, getContext().dispatcher());

				f.onSuccess(new OnSuccess<String>() {
					@Override
					public void onSuccess(String message) throws Throwable {

						sender.tell(message, getSelf());
					}
				}, getContext().dispatcher());
			}

		
	}

	private String mergeAlarms() {
		return "SUCCESS";
	}

}
