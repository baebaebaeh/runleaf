package kr.kro.runleaf.domain;

public class RunningData {
	private int runningDataId;
	private int memberId;
	private int difficulty;
	private String runningTime;
	private String startLatitude;
	private String startLongitude;
	private String createdTime;
	private String title;
	private String oneLineComment;
	@Override
	public String toString() {
		return "RunningData [runningDataId=" + runningDataId + ", memberId=" + memberId + ", difficulty=" + difficulty
				+ ", runningTime=" + runningTime + ", startLatitude=" + startLatitude + ", startLongitude="
				+ startLongitude + ", createdTime=" + createdTime + ", title=" + title + ", oneLineComment="
				+ oneLineComment + "]";
	}
	public RunningData(int runningDataId, int memberId, int difficulty, String runningTime, String startLatitude,
			String startLongitude, String createdTime, String title, String oneLineComment) {
		super();
		this.runningDataId = runningDataId;
		this.memberId = memberId;
		this.difficulty = difficulty;
		this.runningTime = runningTime;
		this.startLatitude = startLatitude;
		this.startLongitude = startLongitude;
		this.createdTime = createdTime;
		this.title = title;
		this.oneLineComment = oneLineComment;
	}
	public RunningData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRunningDataId() {
		return runningDataId;
	}
	public void setRunningDataId(int runningDataId) {
		this.runningDataId = runningDataId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	public String getStartLatitude() {
		return startLatitude;
	}
	public void setStartLatitude(String startLatitude) {
		this.startLatitude = startLatitude;
	}
	public String getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(String startLongitude) {
		this.startLongitude = startLongitude;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOneLineComment() {
		return oneLineComment;
	}
	public void setOneLineComment(String oneLineComment) {
		this.oneLineComment = oneLineComment;
	}
	
	
}
