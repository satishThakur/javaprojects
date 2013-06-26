package pi.main;

import pi.domain.Work;

public class Worker implements Runnable {
	private Work work;
	
	private DataCollection collector;
	
	public Worker(Work work, DataCollection collector) {
		this.work = work;
		this.collector = collector;
	}

	@Override
	public void run() {
		double result = calculatePiFor(work.getStart(), work.getNrOfElrm());
		collector.updateData(result);
	}
	
	private double calculatePiFor(int start, int nrOfElements) {
		double acc = 0.0;
		for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
			acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
		}
		return acc;
	}

}
