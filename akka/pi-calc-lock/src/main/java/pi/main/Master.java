package pi.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pi.domain.Work;

public class Master implements DataCollection{

	private final int numOfWorkers;
	private final int nrOfMessages;
	private final int nrOfElements;

	private double pi;
	private int nrOfResults;
	private final long start = System.currentTimeMillis();
	
	private ExecutorService execService;

	public Master(int nrOfWorkers, 
			int nrOfMessages, 
			int nrOfElements) {
		
		this.nrOfMessages = nrOfMessages;
		this.nrOfElements = nrOfElements;
		this.numOfWorkers = nrOfWorkers;
		execService = Executors.newFixedThreadPool(this.numOfWorkers);
		 
		
	}
	
	public void calculate() {
		for (int start = 0; start < nrOfElements; start++) {
			 execService.submit(new Worker(new Work(start, nrOfElements), this));
		 }
	}

	@Override
	public synchronized void  updateData(Double num) {
		pi += num;
		nrOfResults++;
		if(nrOfResults == nrOfMessages) {
			 long duration =System.currentTimeMillis() - start;
			 System.out.println(String.format("\n\tPi approximation: " + 
				        "\t%s\n\tCalculation time: \t%s millSec",
				        pi, duration));
			 execService.shutdown();
		}
		
	}


}
