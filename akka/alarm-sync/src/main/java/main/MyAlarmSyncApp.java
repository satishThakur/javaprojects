package main;

import actors.AlarmSyncDispatcher;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MyAlarmSyncApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef alarmSyncDispatcher = system.actorOf(new Props(AlarmSyncDispatcher.class), "alarmSyncDisp");
		
		for(int i = 0 ; i < 20; i ++) {		
		alarmSyncDispatcher.tell(new Long(i), null);
		}

	}

}
