package kr.kro.runleaf.member.repository;


import kr.kro.runleaf.member.domain.Member;
import kr.kro.runleaf.member.domain.MemberFile;


public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	Member selectMember(int id);
	
	int getMemberId(String username);

	void updateMember(Member member);

	void deleteMember(int id);
}
