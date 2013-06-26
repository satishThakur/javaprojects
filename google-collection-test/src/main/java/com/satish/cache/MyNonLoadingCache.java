package com.satish.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MyNonLoadingCache {
	
	Cache<String,String> cache = CacheBuilder.newBuilder().
			maximumSize(1000).
			expireAfterWrite(10, TimeUnit.SECONDS).build();
	
	public void put(String key,String value) {
		cache.put(key, value);
	}
	
	public String getFromCache(String key) {
		return cache.getIfPresent(key);
	}

}
