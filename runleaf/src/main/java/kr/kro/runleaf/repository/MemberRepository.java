package kr.kro.runleaf.repository;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;

public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	int existsByUsername(String username);
	
//	Member selectMemberById(int id);
	
	Member selectMemberByUsername(String username);
	
	int getMemberId(String username);

	void updateMember(Member member);

	void deleteMember(int id);
}
