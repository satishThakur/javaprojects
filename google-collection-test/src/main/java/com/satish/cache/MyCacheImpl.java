package com.satish.cache;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class MyCacheImpl {
	
	LoadingCache<String, String> m_cache;
	private static Logger m_log = Logger.getLogger(MyCacheImpl.class);
	public MyCacheImpl() {
		CacheLoader<String, String> loader = new CacheLoader<String, String>() {

			@Override
			public String load(String key) throws Exception {
				m_log.debug("loading the cache for key: " + key);
				return key.toUpperCase();
			}
		};
		m_cache = CacheBuilder.newBuilder().build(loader);
	}
	
	public String getValue(String key) {
		return m_cache.getUnchecked(key);
	}

}
