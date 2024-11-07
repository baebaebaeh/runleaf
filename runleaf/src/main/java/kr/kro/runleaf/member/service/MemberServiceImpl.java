package kr.kro.runleaf.member.service;

import org.springframework.stereotype.Service;

import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.member.domain.dto.MemberDto;
import kr.kro.runleaf.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(MemberDto joinDto) {
		memberRepository.insertMember(joinDto);
	}

	@Override
	public MemberFindDto findOne(int id) {
		return memberRepository.selectMember(id);
	}

	@Override
	public void edit(MemberDto memberDto) {
		memberRepository.updateMember(memberDto);
	}

	@Override
	public void remove(int id) {
		memberRepository.deleteMember(id);
	}
}
