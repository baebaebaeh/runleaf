package kr.kro.runleaf.dto;

public class RunningDataRequest {
	
	String username;
	
	double totalDist;
	
	double totalRunningSecond;
	
	public RunningDataRequest() {
	}

	public RunningDataRequest(String username, double totalDist, double totalRunningSecond) {
		super();
		this.username = username;
		this.totalDist = totalDist;
		this.totalRunningSecond = totalRunningSecond;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
