package kr.kro.runleaf.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Member {
	
	private int id;
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-z0-9]{6,12}$", message = "아이디는 영문 소문자, 숫자 조합 6~12자리여야 합니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~ 16자리여야 합니다.")
	private String password;
	
	@NotBlank(message = "닉네임은 필수 입력값입니다.")
	private String nickname;
	
	
	private String email;
	
	@Pattern(regexp = "^[0-9]{11}$", message = "연락처는 숫자 9자리여야 합니다.")
	private String phone;
	
	private MemberFile memberFile;
	
	public Member() {
	}

	public Member(int id, String username, String password, String nickname, String email, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
	}

	public Member(int id, String username, String password, String nickname, String email, String phone,
			MemberFile memberFile) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.memberFile = memberFile;
	}
	
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
	public MemberFile getMemberFile() {
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
