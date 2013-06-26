package com.satish.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.satish.benchmark.vo.BenchMarkDataVO;
import com.satish.benchmark.vo.RepeatationResultVO;
/**
 * does the actual work of measurement
 * @author skuma004
 *
 */
public class BenchMarkMeasure {
	
	private Runnable m_taskToBenchMark;
	
	private int m_concurrencyLevel;
	
	private int m_repeatCount;
	
	private String m_taskName;
	
	private List<RepeatationResultVO> m_repeatResult = new ArrayList<RepeatationResultVO>();
	
	public static BenchMarkMeasure getInstance(Runnable taskToBenchMark, int concurrencyLevel, 
			int repeatCount, String taskName) {
		return new BenchMarkMeasure(taskToBenchMark, concurrencyLevel, repeatCount, taskName);
		
	}
	
	private BenchMarkMeasure(Runnable taskToBenchMark, int concurrencyLevel, 
			int repeatCount, String taskName) {
		m_taskToBenchMark = taskToBenchMark;
		m_concurrencyLevel = concurrencyLevel;
		m_repeatCount = repeatCount;
		m_taskName = taskName;
	}
	
	public BenchMarkDataVO measure() {
		
		ExecutorService executorService = Executors.newFixedThreadPool(m_concurrencyLevel);
		CountDownLatch allTaskLatch = new CountDownLatch(m_repeatCount);
		
		long startTime = System.nanoTime();
		for(int repeatNum = 0; repeatNum < m_repeatCount; repeatNum++) {
			executorService.execute(new TaskRunner(m_taskToBenchMark, repeatNum,allTaskLatch));
		}
		try {
			allTaskLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		
		return new BenchMarkDataVO(m_taskName, startTime,endTime,m_concurrencyLevel,m_repeatResult );
		
	}
	
	private void addRepeatResult(RepeatationResultVO result) {
		m_repeatResult.add(result);
	}
	
	private class TaskRunner implements Runnable {
		
		private Runnable m_task;
		private int m_countNum;
		private CountDownLatch m_allTaskLatch;		
		
		public TaskRunner (Runnable task, int repeatNum,CountDownLatch allTaskLatch) {
			m_task = task;
			m_countNum = repeatNum;
			m_allTaskLatch = allTaskLatch;
		}

		@Override
		public void run() {
			long startTime = System.nanoTime();
			m_task.run();
			long emdTime = System.nanoTime();
			addRepeatResult(new RepeatationResultVO(startTime, emdTime,m_countNum));
			m_allTaskLatch.countDown();
			
		}
		
	}

}
