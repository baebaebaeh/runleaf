package kr.kro.runleaf.member.domain.dto;

import kr.kro.runleaf.member.domain.MemberFile;

public class MemberDto {
	
	/*
	 * { "username" : "ssafy", "password" : 1234, "nickname" : "김싸피", "email" :
	 * "ssafy@naver.com", "phone" : "010-0000-0000" }
	 */
	
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private MemberFile memberFile;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public MemberFile getMemberFile() {
		return memberFile;
	}
	public void setMemberFile(MemberFile memberFile) {
		this.memberFile = memberFile;
	}
}
