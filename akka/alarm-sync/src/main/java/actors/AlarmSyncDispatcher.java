package actors;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class AlarmSyncDispatcher extends UntypedActor {

	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Object arg) throws Exception {
		if(arg instanceof List) {
			List<Long> nes = (List<Long>)arg;
			for(Long neId : nes) {
				createAlarmSyncActor(neId);
			}
		}else if(arg instanceof Long) {
			long neId = (Long) arg;
			createAlarmSyncActor(neId);
		}

	}

	private void createAlarmSyncActor(Long neId) {
		ActorRef child = getContext().actorOf(new Props(AlarmSyncActor.class), AlarmSyncActor.class.getName() + "_" + neId);
		child.tell(neId, getSelf());
		
	}

}
