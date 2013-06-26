package pi.domain;

public class PiApproxiamtion {
	
	private final Double piApprox;
	private final long duration;
	
	public PiApproxiamtion(Double piApprox, long duration) {
		super();
		this.piApprox = piApprox;
		this.duration = duration;
	}
	public Double getPiApprox() {
		return piApprox;
	}
	public long getDuration() {
		return duration;
	}	

}
