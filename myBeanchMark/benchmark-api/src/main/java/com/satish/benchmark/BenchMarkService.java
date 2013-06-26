package com.satish.benchmark;

import com.satish.benchmark.vo.TaskDataVO;

public interface BenchMarkService {
	
	/**
	 * 
	 * @param task task to be benchmark, implement all the logic of task in run method of runnable.
	 * @param taskData The parameters for the taskData
	 */
	public void BenchMark(Runnable task, TaskDataVO taskData);
	
	/**
	 * Client can listen to the events, that is completion of benchmark.
	 * @param listner listener need to implement the interface
	 */
	public void addBenchMarkServiceListner(BenchMarkServiceListner listner);
	
	/**
	 * remove the listener 
	 * @param listner
	 */
	public void removeBenchMarkServiceListner(BenchMarkServiceListner listner);
	
	
}
