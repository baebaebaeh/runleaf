package kr.kro.runleaf.service;

import kr.kro.runleaf.domain.Member;

public interface MemberService {

	void join(Member member);

	Member findOne(int id);

	void edit(Member member);

	void remove(int id);
}
