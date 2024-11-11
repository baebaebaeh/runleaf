package kr.kro.runleaf.member.service;

import kr.kro.runleaf.member.domain.Member;

public interface MemberService {

	void join(Member member);

	Member findOne(int id);

	void edit(Member member);

	void remove(int id);
}
