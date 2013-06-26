package actors;

import scala.concurrent.duration.Duration;
import akka.actor.UntypedActor;

public class ActiveActorLogger extends UntypedActor{
	private boolean isStarted;
	@Override
	public void onReceive(Object arg) throws Exception {
		if(arg instanceof String) {
			if("START".equals(String.valueOf(arg)) && !isStarted) {
				getContext().system().scheduler().schedule(
			            Duration.Zero(), Duration.create(1, "second"), getSelf(), "PRINT",
			          getContext().dispatcher()
			        );
				isStarted = true;
			}else if("PRINT".equals(String.valueOf(arg))) {
				printAllActors();
			}
		}
		
	}
	private void printAllActors() {
		// no idea how it can be done as of now...
		
	}
	

}
