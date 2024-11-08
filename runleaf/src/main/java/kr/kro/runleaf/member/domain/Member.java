package kr.kro.runleaf.member.domain;


public class Member {
	
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private MemberFile memberFile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public MemberFile getProfileFile() {
		return memberFile;
	}
	public void setMemberFile(MemberFile memberFile) {
		this.memberFile = memberFile;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", memberFile=" + memberFile + "]";
	}
}
