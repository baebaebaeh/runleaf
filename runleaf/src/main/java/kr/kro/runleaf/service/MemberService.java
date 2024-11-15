package kr.kro.runleaf.service;

import kr.kro.runleaf.domain.Member;

public interface MemberService {

	boolean join(Member member);

	Member findOne(int id);

	void edit(Member member);

	void remove(int id);

	boolean existsByUsername(String username);
}
