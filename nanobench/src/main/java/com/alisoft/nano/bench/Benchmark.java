package com.alisoft.nano.bench;


public interface Benchmark {
	void measure(Runnable task);
	void measure(String label, Runnable task);
}
