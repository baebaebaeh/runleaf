package kr.kro.runleaf.dto;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;

public class MemberResponse {

	private String username;
	private String email;
	private String phone;
	private String memberFileUrl;

	public MemberResponse(Member member) {
		this.username = member.getUsername();
		this.email = member.getEmail();
		this.phone = member.getPhone();
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

	public String getMemberFileUrl() {
		return memberFileUrl;
	}

	public void setMemberFileUrl(String memberFileUrl) {
		this.memberFileUrl = memberFileUrl;
	}
}
