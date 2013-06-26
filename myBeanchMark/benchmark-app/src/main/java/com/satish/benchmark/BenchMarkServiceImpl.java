package com.satish.benchmark;

import java.util.ArrayList;
import java.util.List;

import com.satish.benchmark.report.BenchMarkResultReporter;
import com.satish.benchmark.vo.BenchMarkDataVO;
import com.satish.benchmark.vo.TaskDataVO;

/**
 * This class is used to benchmark any operation. 
 * @author skuma004
 *
 */
public class BenchMarkServiceImpl implements BenchMarkService{
	
	private static BenchMarkService m_instance = new BenchMarkServiceImpl();
	
	private List<BenchMarkServiceListner> m_listners = new ArrayList<BenchMarkServiceListner>();
	public static BenchMarkService getInstance() {
		return m_instance;
	}
	
	private BenchMarkServiceImpl() {
		addBenchMarkServiceListner(new BenchMarkResultReporter());		
	}
	
	@Override
	public void BenchMark(Runnable task, TaskDataVO taskData) {
		doJVMWarmUp(task);
		BenchMarkMeasure measure = BenchMarkMeasure.getInstance(task, taskData.getConcurrencyLevel(), 
				taskData.getRepeatationCount(), taskData.getTaskName());
		BenchMarkDataVO benchMarkData = measure.measure();
		sendEvent(benchMarkData);
	}

	private void doJVMWarmUp(Runnable task) {
		TaskDataVO warmUpData = TaskDataVO.getWarmUpTaskData();
		BenchMarkMeasure measure = BenchMarkMeasure.getInstance(task, warmUpData.getConcurrencyLevel(), 
				warmUpData.getRepeatationCount(), warmUpData.getTaskName());
				measure.measure();
	}

	private void sendEvent(BenchMarkDataVO benchMarkData) {
		for(BenchMarkServiceListner listner : m_listners) {
			listner.onBenchMarkData(benchMarkData);
		}		
	}

	@Override
	public synchronized void addBenchMarkServiceListner(BenchMarkServiceListner listner) {
		m_listners.add(listner);
		
	}

	@Override
	public synchronized void removeBenchMarkServiceListner(BenchMarkServiceListner listner) {
		m_listners.remove(listner);
		
	}
	

}
