package com.satish.benchmark.vo;

import java.util.List;

public class BenchMarkDataVO {
	
	private String m_taskName;

	private long m_startTime;
	
	private long m_endTime;
	
	private int m_concurrency;
	
	private List<RepeatationResultVO> m_repeatationResult;
	
	
	public BenchMarkDataVO(String name, long startTime, long endTime, 
			int concurrencyLevel, List<RepeatationResultVO> repeatationResult) {
		m_taskName = name;
		m_startTime = startTime;
		m_endTime = endTime;
		m_concurrency = concurrencyLevel;
		m_repeatationResult = repeatationResult;		
	}
	
	
	public String getTaskName() {
		return m_taskName;
	}

	public long getStartTime() {
		return m_startTime;
	}

	public long getEndTime() {
		return m_endTime;
	}
	
	public List<RepeatationResultVO> getRepeatationResult() {
		return m_repeatationResult;
	}
	
	public int getConCurrencyLevel() {
		return m_concurrency;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		long totalTime = m_endTime - m_startTime;
		int numberOfRepeat = m_repeatationResult.size() == 0?1:m_repeatationResult.size();
		sb.append("Task name is: ").append(m_taskName).append("\t").append("number of Cycles: ").append(numberOfRepeat).append("\t")
		.append("Number of Threads: ").append(m_concurrency);
		sb.append("\n");
		
		sb.append("total time taken: ").append(totalTime/1000).append("ms");
		sb.append("\n");
		
		
		
		sb.append("Number of repeatation: ").append(numberOfRepeat).append("\t");
		
		sb.append("avarage time taken: ").append(totalTime/(1000*numberOfRepeat)).append("ms");
		
		
		return sb.toString();
	}







}
