package kr.kro.runleaf.service;

import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.member.domain.dto.MemberDto;

public interface MemberService {

	void join(MemberDto memberDto);

	MemberFindDto findOne(int id);

	void edit(MemberDto memberDto);

	void remove(int id);
}
