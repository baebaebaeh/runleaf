package kr.kro.runleaf.dto;

import kr.kro.runleaf.domain.Member;

public class MemberResponse {

	private String username;
	private String email;
	private String phone;
	private double totalDist;
	private double totalRunningSecond;
	private String memberFileUrl;

	public MemberResponse(Member member) {
		this.username = member.getUsername();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.totalDist = member.getTotalDist();
		this.totalRunningSecond = member.getTotalRunningSecond();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getTotalDist() {
		return totalDist;
	}

	public void setTotalDist(double totalDist) {
		this.totalDist = totalDist;
	}

	public double getTotalRunningSecond() {
		return totalRunningSecond;
	}

	public void setTotalRunningSecond(double totalRunningSecond) {
		this.totalRunningSecond = totalRunningSecond;
	}

	public String getMemberFileUrl() {
		return memberFileUrl;
	}

	public void setMemberFileUrl(String memberFileUrl) {
		this.memberFileUrl = memberFileUrl;
	}
}
