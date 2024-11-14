 package kr.kro.runleaf.domain;


public class RunningBoardImage {
	private int runningBoardImageId;
	private int runningBoardId;
	private String oriName;
	private String systemName;
	private String path;
	public RunningBoardImage() {
		// TODO Auto-generated constructor stub
	}
	public RunningBoardImage(int runningBoardId, String oriName, String systemName,
			String path) {
		super();
		this.runningBoardId = runningBoardId;
		this.oriName = oriName;
		this.systemName = systemName;
		this.path = path;
	}
	public int getRunningBoardImageId() {
		return runningBoardImageId;
	}
	public void setRunningBoardImageId(int runningBoardImageId) {
		this.runningBoardImageId = runningBoardImageId;
	}
	public int getRunningBoardId() {
		return runningBoardId;
	}
	public void setRunningBoardId(int runningBoardId) {
		this.runningBoardId = runningBoardId;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "RunningBoardImage [runningBoardImageId=" + runningBoardImageId + ", runningBoardId=" + runningBoardId
				+ ", oriName=" + oriName + ", systemName=" + systemName + ", path=" + path + "]";
	}
	
	
	
}
