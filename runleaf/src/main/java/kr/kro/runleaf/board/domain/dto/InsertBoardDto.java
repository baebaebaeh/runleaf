package kr.kro.runleaf.board.domain.dto;

/**
 * 게시글 생성시 사용하는 dto
 * createdDate, modifidedDate는 생략
 */
public class InsertBoardDto {
	private int memberId;
	private int runningDataId;
	private String content;
	private String mainImagePath;
	private double startLongitude;
	private double startLatitude;
	public InsertBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InsertBoardDto(int memberId, int runningDataId, String content, String mainImagePath, double startLongitude,
			double startLatitude) {
		super();
		this.memberId = memberId;
		this.runningDataId = runningDataId;
		this.content = content;
		this.mainImagePath = mainImagePath;
		this.startLongitude = startLongitude;
		this.startLatitude = startLatitude;
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
	public String getMainImagePath() {
		return mainImagePath;
	}
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	public double getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}
	public double getStartLatitude() {
		return startLatitude;
	}
	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}
	
	
	
	
}
