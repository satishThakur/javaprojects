package com.alisoft.nano.bench;

public class MeasureState implements Comparable<MeasureState> {
	private String label;
	private long startTime;
	private long endTime;
	private long index;
	private int measurement;
	private int threadCount;

	public MeasureState(String label, long index, int measurement, int threadCount) {
		super();
		this.label = label;
		this.measurement = measurement;
		this.index = index;
		this.threadCount = threadCount;
	}
	
	public long getIndex() {
		return index;
	}

	public String getLabel() {
		return label;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}
	
	public long getMeasurements() {
		return measurement;
	}

	public long getMeasureTime() {
		return endTime - startTime;
	}

	public int getThreadCount() {
		return threadCount;
	}
	
	public void startNow() {
		this.startTime = System.nanoTime();
	}
	
	public void endNow() {
		this.endTime = System.nanoTime();
	}

	public int compareTo(MeasureState another) {
		if (this.startTime > another.startTime) {
			return -1;
		} else if (this.startTime < another.startTime) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
