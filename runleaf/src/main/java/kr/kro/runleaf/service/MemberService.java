package kr.kro.runleaf.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.dto.MemberEditRequest;
import kr.kro.runleaf.dto.MemberResponse;

public interface MemberService {

	boolean join(Member member);

	MemberResponse findMe(String username);
	
	int getMemberIdByUsername(String username);

	void edit(MemberEditRequest editRequest);

	boolean existsByUsername(String username);

	String editProfile(MultipartFile file, String username) throws IllegalStateException, IOException;

	MemberResponse findOne(String username);

	List<MemberResponse> findFollowers(String username);

	List<MemberResponse> findFollowings(String username);

	//	void remove(int memberId);
}
