package pi.main;

public class Pi {

	public static void main(String[] args) {
		Pi pi = new Pi();
		pi.calculate(3, 10000, 10000);
	}

	// actors and messages ...

	public void calculate(
			final int nrOfWorkers, 
			final int nrOfElements, 
			final int nrOfMessages) {
		new Master(nrOfWorkers, nrOfElements, nrOfMessages).calculate();
	}

}
