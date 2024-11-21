package kr.kro.runleaf.domain;

public class MemberFile {
	
	private int memberFileId;
	private String filePath;
	private String orgName;
	private String systemName;
	private int memberId;
	

	public int getMemberFileId() {
		return memberFileId;
	}
	public void setMemberFileId(int memberFileId) {
		this.memberFileId = memberFileId;
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
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "MemberFile [memberFileId=" + memberFileId + ", filePath=" + filePath + ", orgName=" + orgName + ", systemName=" + systemName
				+ ", memberId=" + memberId + "]";
	}
}
