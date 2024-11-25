package kr.kro.runleaf.domain;

import java.time.LocalDateTime;

public class Location {
	private int runningCoodinateId;
	private int runningBoardId;
	private double latitude;
	private double longitude;
	private double accuracy;
	private LocalDateTime createdTs;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Location(int runningCoodinateId, int runningBoardId, double latitude, double longitude, double accuracy,
			LocalDateTime createdTs) {
		super();
		this.runningCoodinateId = runningCoodinateId;
		this.runningBoardId = runningBoardId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.createdTs = createdTs;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public int getRunningCoodinateId() {
		return runningCoodinateId;
	}
	public void setRunningCoodinateId(int runningCoodinateId) {
		this.runningCoodinateId = runningCoodinateId;
	}
	public int getRunningBoardId() {
		return runningBoardId;
	}
	public void setRunningBoardId(int runningBoardId) {
		this.runningBoardId = runningBoardId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	@Override
	public String toString() {
		return "Location [runningCoodinateId=" + runningCoodinateId + ", runningBoardId=" + runningBoardId
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", createdTs=" + createdTs + "]";
	}
	
	
	
}
