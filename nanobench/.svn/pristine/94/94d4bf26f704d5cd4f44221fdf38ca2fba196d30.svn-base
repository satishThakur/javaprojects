package com.alisoft.benchmark;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.alisoft.nano.bench.Nano;

public class MapBenchTest {
	private static int measurements = 10000;
	private static int threads = 3;

	@Test
	public void testHashTable() {
		final Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
		Nano.bench().measurements(measurements).threads(threads).measure(
				"hashtable", new Runnable() {
					public void run() {
						Random random = new Random(10000);
						for (int i = 0; i < 1000; i++) {
							hash.put(i, i);
							if (i % 2 == 0) {
								for (int j = 0; j < 3; j++) {
									hash.get(random.nextInt());
								}
							}
						}
					}
				});
	}

	@Test
	public void testSyncHashMap() {
		final Map<Integer, Integer> hash = Collections
				.synchronizedMap(new HashMap<Integer, Integer>());
		Nano.bench().measurements(measurements).threads(threads).measure(
				"sync-hashmap", new Runnable() {
					public void run() {
						Random random = new Random(10000);
						for (int i = 0; i < 1000; i++) {
							hash.put(i, i);
							if (i % 2 == 0) {
								for (int j = 0; j < 3; j++) {
									hash.get(random.nextInt());
								}
							}
						}
					}
				});
	}

	@Test
	public void testCurrentHashMap() {
		final Map<Integer, Integer> hash = new ConcurrentHashMap<Integer, Integer>();
		Nano.bench().measurements(measurements).threads(threads).measure(
				"concurrent-hashmap", new Runnable() {
					public void run() {
						Random random = new Random(10000);
						for (int i = 0; i < 1000; i++) {
							hash.put(i, i);
							if (i % 2 == 0) {
								for (int j = 0; j < 3; j++) {
									hash.get(random.nextInt());
								}
							}
						}
					}
				});
	}

}
