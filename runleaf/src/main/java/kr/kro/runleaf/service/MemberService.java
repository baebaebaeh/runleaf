package kr.kro.runleaf.service;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.dto.MemberResponse;

public interface MemberService {

	boolean join(Member member);

	MemberResponse findOne(String username);

	void edit(Member member);

	void remove(int id);

	boolean existsByUsername(String username);
}
