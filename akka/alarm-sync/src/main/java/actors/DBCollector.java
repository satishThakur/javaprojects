package actors;

import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import scala.concurrent.Future;
import util.ResultUtil;
import domain.DbAlarms;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class DBCollector extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg) throws Exception {
		//log.info("received: " + arg);
		final ActorRef sender = getSender();
		if(arg instanceof Long) {
			final long neId = (Long)arg;				
			Future<DbAlarms> f = future(new Callable<DbAlarms>() {
				public DbAlarms call() {

					return getDbAlarms(neId);
				}
			}, getContext().dispatcher());

			f.onSuccess(new OnSuccess<DbAlarms>() {
				@Override
				public void onSuccess(DbAlarms dbAlarms) throws Throwable {
					log.info("sending DbAlarm to For: " + neId);
					sender.tell(dbAlarms, getSelf());
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

	private DbAlarms getDbAlarms(long neId) {
		if(ResultUtil.getNextBoolean()) {
			return new DbAlarms();
		}else {
			log.error("DB Collection failed for NE : " + neId);
			throw new RuntimeException();
		}
	}

}
