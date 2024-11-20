package kr.kro.runleaf.repository;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.dto.MemberEditRequest;

public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	int existsByUsername(String username);
	
	int getMemberId(String username);

	Member selectMemberByUsername(String username);
	
	MemberFile selectMemberFileByMemberId(int id);

	void updateMember(MemberEditRequest editRequest);

	void updateMemberFile(MemberFile memberFile);

	void deleteMember(int id);
}
