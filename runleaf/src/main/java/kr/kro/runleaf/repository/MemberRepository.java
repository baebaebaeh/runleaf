package kr.kro.runleaf.repository;


<<<<<<< HEAD:runleaf/src/main/java/kr/kro/runleaf/repository/MemberRepository.java
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;
=======
import kr.kro.runleaf.member.domain.Member;
import kr.kro.runleaf.member.domain.MemberFile;
>>>>>>> 60f9abea388285be61f9329ea876dbc6e80d139d:runleaf/src/main/java/kr/kro/runleaf/member/repository/MemberRepository.java


public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	Member selectMember(int id);
	
	int getMemberId(String username);

	void updateMember(Member member);

	void deleteMember(int id);
}
