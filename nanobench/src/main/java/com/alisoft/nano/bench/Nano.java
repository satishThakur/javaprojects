package com.alisoft.nano.bench;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.alisoft.nano.bench.listener.Listeners;
import com.alisoft.nano.bench.listener.MeasureListener;
import com.alisoft.nano.bench.util.MemoryUtil;

public class Nano implements Benchmark {
	private int numberOfMeasurement = 20;
	private int numberOfWarmUp = 10;
	private int numberOfThread = 1;
	private CountDownLatch measureLatch;
	private CountDownLatch warmUpLatch;
	private List<MeasureListener> listeners;
	
	public static Nano bench() {
		return new Nano();
	}
	
	public Nano measurements(int numberOfMeasurement) {
		this.numberOfMeasurement = numberOfMeasurement;
		return this;
	}
	
	public Nano threads(int numberOfThread) {
		this.numberOfThread = numberOfThread;
		return this;
	}

	public void measure(Runnable task) {
		measure("", task);
	}

	public void measure(String label, Runnable task) {
		this.warmUpLatch = new CountDownLatch(this.numberOfWarmUp);
		this.measureLatch = new CountDownLatch(this.numberOfMeasurement);
		MemoryUtil.restoreJvm();
		doWarmup(task);
		MemoryUtil.restoreJvm();
		doMeasure(label, task);
	}

	private void doMeasure(String label, Runnable task) {
		Executor executor = Executors.newFixedThreadPool(this.numberOfThread);
		for (int i = 0; i < this.numberOfMeasurement; i++) {
			executor.execute(new TimeMeasureProxy(new MeasureState(label,
					i, this.numberOfMeasurement, this.numberOfThread), task, this.getListeners(),
					this.measureLatch));
		}

		try {

			this.measureLatch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void doWarmup(Runnable task) {
		Executor executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < this.numberOfWarmUp; i++) {
			executor.execute(new TimeMeasureProxy(new MeasureState("_warmup_",
					i, this.numberOfWarmUp, 1), task, this.getListeners(),
					this.warmUpLatch));
		}
		try {
			this.warmUpLatch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	private synchronized List<MeasureListener> getListeners() {
		if (this.listeners == null) {
			this.listeners = Arrays.asList(Listeners.simple());
		}
		return this.listeners;
	}

	public void addListener(MeasureListener listener) {
		this.listeners.add(listener);
	}

	private static class TimeMeasureProxy implements Runnable {
		private MeasureState state;
		private Runnable runnable;
		private List<MeasureListener> listeners;
		private CountDownLatch measureLatch;

		public TimeMeasureProxy(MeasureState state, Runnable runnable,
				List<MeasureListener> listeners, CountDownLatch measureLatch) {
			super();
			this.state = state;
			this.runnable = runnable;
			this.listeners = listeners;
			this.measureLatch = measureLatch;
		}

		public void run() {
			this.state.startNow();
			this.runnable.run();
			this.state.endNow();
			notifyMeasurement(state);
			this.measureLatch.countDown();
		}

		private void notifyMeasurement(MeasureState times) {
			for (MeasureListener listener : this.listeners) {
				listener.onMeasure(times);
			}
		}
	}

}
