package kr.kro.runleaf.dto;

public class RunningDataRequest {
	
	double totalDist;
	
	double totalRunningSecond;
	
	public RunningDataRequest() {
	}

	public RunningDataRequest(double totalDist, double totalRunningSecond) {
		this.totalDist = totalDist;
		this.totalRunningSecond = totalRunningSecond;
	}

	public double getTotalDist() {
		return totalDist;
	}

	public void setTotalDist(double totalDist) {
		this.totalDist = totalDist;
	}

	public double getTotalRunningSecond() {
		return totalRunningSecond;
	}

	public void setTotalRunningSecond(double totalRunningSecond) {
		this.totalRunningSecond = totalRunningSecond;
	}
}
