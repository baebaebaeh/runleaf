package kr.kro.runleaf.domain;


public class Board {
	private int boardId;
	private int memberId;
	private int runningDataId;
	private String content;
	private String createdDate;
	private String modifidedDate;
	private String mainImagePath;
	private String writer;
	private RunningBoard runningData;
	
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public RunningBoard getRunningData() {
		return runningData;
	}
	public void setRunningData(RunningBoard runningData) {
		this.runningData = runningData;
	}
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
