package kr.kro.runleaf.domain;

import java.time.LocalDateTime;
import java.util.List;

public class RunningBoard {
	private int runningBoardId;
	private int memberId;
	private String difficulty;
	private LocalDateTime startRunningTs;
	private LocalDateTime endRunningTs;
	private double totalRunningSecond;
	private double startLatitude;
	private double startLongitude;
	private LocalDateTime createdTs;
	private LocalDateTime modifiedTs;
	private String title;
	private String content;
	private String mainImagePath;
	private String writer;
	private double totalDist;
	private boolean onBoard;
	private List<RunningBoardImage> runningBoardImage;
	private List<Location> location;
	public RunningBoard() {
		// TODO Auto-generated constructor stub
	}

	public RunningBoard(int runningBoardId, int memberId, String difficulty, LocalDateTime startRunningTs,
			LocalDateTime endRunningTs, double totalRunningSecond, double startLatitude, double startLongitude,
			LocalDateTime createdTs, LocalDateTime modifiedTs, String title, String content, String mainImagePath,
			String writer, double totalDist, boolean onBoard, List<RunningBoardImage> runningBoardImage,
			List<Location> location) {
		super();
		this.runningBoardId = runningBoardId;
		this.memberId = memberId;
		this.difficulty = difficulty;
		this.startRunningTs = startRunningTs;
		this.endRunningTs = endRunningTs;
		this.totalRunningSecond = totalRunningSecond;
		this.startLatitude = startLatitude;
		this.startLongitude = startLongitude;
		this.createdTs = createdTs;
		this.modifiedTs = modifiedTs;
		this.title = title;
		this.content = content;
		this.mainImagePath = mainImagePath;
		this.writer = writer;
		this.totalDist = totalDist;
		this.onBoard = onBoard;
		this.runningBoardImage = runningBoardImage;
		this.location = location;
	}


	public double getTotalRunningSecond() {
		return totalRunningSecond;
	}


	public void setTotalRunningSecond(double totalRunningSecond) {
		this.totalRunningSecond = totalRunningSecond;
	}


	public double getTotalDist() {
		return totalDist;
	}
	public void setTotalDist(double totalDist) {
		this.totalDist = totalDist;
	}
	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

	public int getRunningBoardId() {
		return runningBoardId;
	}
	public void setRunningBoardId(int runningBoardId) {
		this.runningBoardId = runningBoardId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public LocalDateTime getStartRunningTs() {
		return startRunningTs;
	}
	public void setStartRunningTs(LocalDateTime startRunningTs) {
		this.startRunningTs = startRunningTs;
	}
	public LocalDateTime getEndRunningTs() {
		return endRunningTs;
	}
	public void setEndRunningTs(LocalDateTime endRunningTs) {
		this.endRunningTs = endRunningTs;
	}
	public double getStartLatitude() {
		return startLatitude;
	}
	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}
	public double getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	public LocalDateTime getModifiedTs() {
		return modifiedTs;
	}
	public void setModifiedTs(LocalDateTime modifiedTs) {
		this.modifiedTs = modifiedTs;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public boolean isOnBoard() {
		return onBoard;
	}
	public void setOnBoard(boolean onBoard) {
		this.onBoard = onBoard;
	}
	public List<RunningBoardImage> getRunningBoardImage() {
		return runningBoardImage;
	}
	public void setRunningBoardImage(List<RunningBoardImage> runningBoardImage) {
		this.runningBoardImage = runningBoardImage;
	}
	
	
}
