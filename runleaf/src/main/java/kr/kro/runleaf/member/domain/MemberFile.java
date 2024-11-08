package kr.kro.runleaf.member.domain;

public class MemberFile {
	
	private int id;
	private String filePath;
	private String orgName;
	private String systemName;
	private String memberId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "MemberFile [id=" + id + ", filePath=" + filePath + ", orgName=" + orgName + ", systemName=" + systemName
				+ ", username=" + username + "]";
	}
}
