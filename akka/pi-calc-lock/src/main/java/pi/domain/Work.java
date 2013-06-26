package pi.domain;

public class Work {
	
	private final int start;
	private final int nrOfElrm;	
	
	public Work(int start, int nrOfElrm) {
		this.start = start;
		this.nrOfElrm = nrOfElrm;
		
	}
	public int getStart() {
		return start;
	}
	
	public int getNrOfElrm() {
		return nrOfElrm;
	}	

}
