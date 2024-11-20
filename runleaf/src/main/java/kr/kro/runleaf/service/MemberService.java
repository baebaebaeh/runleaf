package kr.kro.runleaf.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.dto.MemberEditRequest;
import kr.kro.runleaf.dto.MemberResponse;

public interface MemberService {

	boolean join(Member member);

	MemberResponse findOne(String username);

	void edit(MemberEditRequest editRequest);

	void remove(int id);

	boolean existsByUsername(String username);

	String editProfile(MultipartFile file, String username) throws IllegalStateException, IOException;
}
