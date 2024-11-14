package kr.kro.runleaf.repository;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;

public interface MemberRepository {
	
	int insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	Member selectMember(int id);
	
	int getMemberId(String username);

	void updateMember(Member member);

	void deleteMember(int id);
}
