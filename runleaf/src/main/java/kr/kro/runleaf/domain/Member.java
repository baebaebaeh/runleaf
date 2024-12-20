package kr.kro.runleaf.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Member {
	
	private int memberId;
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-z0-9]{6,12}$", message = "아이디는 영문 소문자, 숫자 조합 6~12자리여야 합니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,16}",
            message = "비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 6 ~ 16자리여야 합니다.")
	private String password;
	
	private String email;
	
	private String phone;
	
	private MemberFile memberFile;
	
	private double totalDist;

	private double totalRunningSecond;
	
	private String role;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", memberFile=" + memberFile + ", totalDist=" + totalDist
				+ ", totalRunningSecond=" + totalRunningSecond + ", role=" + role + "]";
	}
}
