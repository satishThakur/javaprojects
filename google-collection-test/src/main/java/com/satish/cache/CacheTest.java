package com.satish.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


public class CacheTest {
	private static Logger m_log = Logger.getLogger(MyCacheImpl.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyCacheImpl cache = new MyCacheImpl();
		
		String key1 = "testString1";
		
		String value = cache.getValue(key1);
		m_log.debug("value :" + value);	
				
		value = cache.getValue(key1);
		value = cache.getValue(key1);
		value = cache.getValue(key1);
		
		ConcurrentHashMap<String, String> cache1 = new ConcurrentHashMap<String, String>();
	}

}
