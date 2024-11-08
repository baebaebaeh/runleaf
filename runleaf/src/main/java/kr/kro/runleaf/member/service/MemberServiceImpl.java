package kr.kro.runleaf.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.member.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;
import kr.kro.runleaf.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	@Transactional
	public void join(MemberDto joinDto) {
		memberRepository.insertMember(joinDto);
		MemberFile memberFile = joinDto.getMemberFile();
		
		int memberId = memberRepository.getMemberId(joinDto.getUsername());
	    memberFile.setMemberId(memberId);
	    memberRepository.insertMemberFile(memberFile);
	}

	@Override
	public MemberFindDto findOne(int id) {
		return memberRepository.selectMember(id);
	}

	@Override
	@Transactional
	public void edit(MemberDto memberDto) {
		memberRepository.updateMember(memberDto);
	}

	@Override
	@Transactional
	public void remove(int id) {
		memberRepository.deleteMember(id);
	}
}
