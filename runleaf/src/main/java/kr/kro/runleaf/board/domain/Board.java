package kr.kro.runleaf.board.domain;


public class Board {
	private int id;
	private int memberId;
	private int runningDataId;
	private String content;
	private String createdDate;
	private String modifidedDate;
	private String mainImagePath;
	private String startLatitude;
	private String startLongitude;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int id, int memberId, int runningDataId, String content, String createdDate, String modifidedDate,
			String mainImagePath, String startLatitude, String startLongitude) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.runningDataId = runningDataId;
		this.content = content;
		this.createdDate = createdDate;
		this.modifidedDate = modifidedDate;
		this.mainImagePath = mainImagePath;
		this.startLatitude = startLatitude;
		this.startLongitude = startLongitude;
	}
	
	
}
