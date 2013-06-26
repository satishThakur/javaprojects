package com.satish.benchmark;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import junit.framework.TestCase;

import com.satish.benchmark.vo.TaskDataVO;
import com.satish.cache.MyCacheImpl;

public class CacheTestImpl extends TestCase{

	private BenchMarkService m_service;
	private MyCacheImpl m_cache;
	
	private ConcurrentMap<String,String> m_mapCache;
	
	private Map<String,String> m_normalMapCache;
	
	private static String TEST = "test";
	
	private Random m_rand = new Random(37);

	@Override
	protected void setUp() throws Exception {
		m_service = BenchMarkServiceImpl.getInstance();
		m_cache = new MyCacheImpl();
		m_mapCache = new ConcurrentHashMap<String, String>();
		
		m_normalMapCache = new HashMap<String, String>();
		
	}

	public void testCache() {
		TaskDataVO taskData = new TaskDataVO(5000,500,"Cache Test");
		m_service.BenchMark(new Runnable() {

			@Override
			public void run() {
				
				for(int i = 0; i <1000 ; i++) {
					int random = m_rand.nextInt(1000);
					m_cache.getValue(TEST + random);
				}

			}

		}, taskData);
	}
	
	public void testMap() {
		
		TaskDataVO taskData = new TaskDataVO(5000,500,"Map Cache Test");
		m_service.BenchMark(new Runnable() {

			@Override
			public void run() {
				
				for(int i = 0; i <1000 ; i++) {
					int random = m_rand.nextInt(1000);
					String testString =TEST + random;
					String value = m_mapCache.get(testString);
					
					if(value == null)
						value = m_mapCache.putIfAbsent(testString, testString.toUpperCase());
				}

			}

		}, taskData);
		
	}
	
public void testHashMap() {
		
		TaskDataVO taskData = new TaskDataVO(5000,500,"Hash Map Cache Test");
		m_service.BenchMark(new Runnable() {

			@Override
			public void run() {
				
				for(int i = 0; i <1000 ; i++) {
					int random = m_rand.nextInt(1000);
					String testString =TEST + random;
					synchronized (m_normalMapCache) {
						String value = m_normalMapCache.get(testString);
						
						if(value == null)
							value = m_normalMapCache.put(testString, testString.toUpperCase());
					}
					
				}

			}

		}, taskData);
		
	}



}
