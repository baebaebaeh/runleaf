package kr.kro.runleaf.repository;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.dto.MemberEditRequest;

public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	int existsByUsername(String username);
	
//	Member selectMemberById(int id);
	
	Member selectMemberByUsername(String username);
	
	int getMemberId(String username);

	void updateMember(MemberEditRequest editRequest);

	void deleteMember(int id);

	MemberFile selectMemberFileByMemberId(int id);
}
