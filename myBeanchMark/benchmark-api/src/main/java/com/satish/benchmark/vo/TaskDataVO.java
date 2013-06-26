package com.satish.benchmark.vo;

 import static com.satish.benchmark.constants.BenchMarkConstants.*;
/**
 * represent the parameters for the task
 * @author skuma004
 *
 */
public class TaskDataVO {	
	
	//name of the task
	private String m_taskName;
	
	//number of threads would be used for the test
	private int m_ConcurrencyLevel;
	
	//number of repeats, this should be read like: number of threads to achive this number of repeats.
	private int m_numOfRepeation;
	
	public TaskDataVO(int numOfRepeation, int concurrencyLevel, String name) {
		super();
		m_numOfRepeation = numOfRepeation;
		m_ConcurrencyLevel = concurrencyLevel;
		m_taskName = name;
	}
	
	public TaskDataVO(String name) {
		this(DEFAULT_REPEATION_COUNT,DEFAULT_CONCURRENCY,name);
	}
	
	public static TaskDataVO getWarmUpTaskData() {
		return new TaskDataVO(WARMUP_REPEATION_COUNT, WARMUP_CONCURRENCY, "WarmUp Activity");
	}
	
	public String getTaskName() {
		return m_taskName;
	}
	
	public int getConcurrencyLevel() {
		return m_ConcurrencyLevel;
	}
	
	public int getRepeatationCount() {
		return m_numOfRepeation;
	}	

}
