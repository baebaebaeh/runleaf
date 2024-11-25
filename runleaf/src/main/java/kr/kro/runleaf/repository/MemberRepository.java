package kr.kro.runleaf.repository;


import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.dto.MemberEditRequest;
import kr.kro.runleaf.dto.RunningDataRequest;

public interface MemberRepository {
	
	void insertMember(Member member);
	
	void insertMemberFile(MemberFile memberFile);

	int existsByUsername(String username);
	
	int getMemberId(String username);

	Member selectMemberByUsername(String username);
	
	MemberFile selectMemberFileByMemberId(int memberId);

	void updateMember(MemberEditRequest editRequest);

	void updateDistAndRunningSecond(RunningDataRequest runningDataRequest);

	void updateMemberFile(MemberFile memberFile);

	Member selectMemberByMemberId(int memberId);

//	void deleteMember(int id);
}
