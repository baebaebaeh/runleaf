package kr.kro.runleaf.domain;


public class Board {
	private int boardId;
	private int memberId;
	private int runningDataId;
	private String content;
	private String createdDate;
	private String modifidedDate;
	private String mainImagePath;
	private String nowLatitude;
	private String nowLongitude;
	private String writer;
	private RunningData runningData;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getRunningDataId() {
		return runningDataId;
	}
	public void setRunningDataId(int runningDataId) {
		this.runningDataId = runningDataId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifidedDate() {
		return modifidedDate;
	}
	public void setModifidedDate(String modifidedDate) {
		this.modifidedDate = modifidedDate;
	}
	public String getMainImagePath() {
		return mainImagePath;
	}
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	public String getNowLatitude() {
		return nowLatitude;
	}
	public void setNowLatitude(String nowLatitude) {
		this.nowLatitude = nowLatitude;
	}
	public String getNowLongitude() {
		return nowLongitude;
	}
	public void setNowLongitude(String nowLongitude) {
		this.nowLongitude = nowLongitude;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public RunningData getRunningData() {
		return runningData;
	}
	public void setRunningData(RunningData runningData) {
		this.runningData = runningData;
	}

	
	
}
