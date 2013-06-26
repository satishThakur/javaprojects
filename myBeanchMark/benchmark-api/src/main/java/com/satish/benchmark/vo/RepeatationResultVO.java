package com.satish.benchmark.vo;

public class RepeatationResultVO {
	
	private long m_startTime;
	
	private long m_endTime;
	
	private int m_repeatNum;
	
	public RepeatationResultVO(long startTime,long endTime, int repeatNum) {
		super();		
		m_startTime = startTime;
		m_endTime = endTime;
		m_repeatNum = repeatNum;
	}
	
	public long getStartTime() {
		return m_startTime;
	}

	public long getEndTime() {
		return m_endTime;
	}

	public int getRepeatNum() {
		return m_repeatNum;
	}

}
