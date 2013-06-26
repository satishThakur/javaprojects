package domain;


public class AlarmSyncData {
	
	private final DbAlarms dbAlarm;
	private final NodeAlarms nodeAlarm;
	private final long neId;

	public AlarmSyncData(DbAlarms _dbAlarms, NodeAlarms _nodeAlarms, Long _neId) {
		dbAlarm = _dbAlarms;
		nodeAlarm = _nodeAlarms;
		neId = _neId;
	}

	public DbAlarms getDbAlarm() {
		return dbAlarm;
	}

	public NodeAlarms getNodeAlarm() {
		return nodeAlarm;
	}
	
	@Override
	public String toString() {
		
		return "DB Alarms and Node Alarms";
	}

	public long getNeId() {
		return neId;
	}

}
