 package kr.kro.runleaf.domain;


public class RunningDataImage {
	private int RunningDataImageId;
	private int runningDataId;
	private String realName;
	private String systemName;
	private String path;
	public RunningDataImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RunningDataImage(int runningDataId, String realName, String systemName,
			String path) {
		super();
		this.runningDataId = runningDataId;
		this.realName = realName;
		this.systemName = systemName;
		this.path = path;
	}
	public int getRunningDataImageId() {
		return RunningDataImageId;
	}
	public void setRunningDataImageId(int runningDataImageId) {
		RunningDataImageId = runningDataImageId;
	}
	public int getRunningDataId() {
		return runningDataId;
	}
	public void setRunningDataId(int runningDataId) {
		this.runningDataId = runningDataId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
		return "RunningDataImage [RunningDataImageId=" + RunningDataImageId + ", runningDataId=" + runningDataId
				+ ", realName=" + realName + ", systemName=" + systemName + ", path=" + path + "]";
	}
	
	
	
}
