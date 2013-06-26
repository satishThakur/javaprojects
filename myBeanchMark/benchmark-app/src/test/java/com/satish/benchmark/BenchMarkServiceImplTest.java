package com.satish.benchmark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.satish.benchmark.vo.TaskDataVO;

import junit.framework.TestCase;

public class BenchMarkServiceImplTest extends TestCase {
	
	private BenchMarkService m_service;
	
	@Override
	protected void setUp() throws Exception {
		m_service = BenchMarkServiceImpl.getInstance();
	}
	
	public void testArrayListAdd() {
		TaskDataVO taskData = new TaskDataVO(10,1,"ArrayList Insertion");
		m_service.BenchMark(new Runnable() {

			@Override
			public void run() {
				List<Integer> list = new ArrayList<Integer>();
				for(int i = 0; i <1000 ; i++)
					list.add(i);				
			}
			
		}, taskData);
		
	}
	
	public void testSetListAdd() {
		TaskDataVO taskData = new TaskDataVO(10,1,"HashSet Insertion");
		m_service.BenchMark(new Runnable() {

			@Override
			public void run() {
				Set<Integer> intSet = new HashSet<Integer>();
				for(int i = 0; i <1000 ; i++)
					intSet.add(i);				
			}
			
		}, taskData);
		
	}
}
