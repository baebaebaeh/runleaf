package kr.kro.runleaf.member.service;

import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;

import kr.kro.runleaf.member.domain.Member;
import kr.kro.runleaf.member.domain.MemberFile;
import kr.kro.runleaf.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	@Transactional
	public void join(Member member) {
		memberRepository.insertMember(member);
		MemberFile memberFile = member.getMemberFile();
		
	    memberFile.setMemberId(member.getId());
	    memberRepository.insertMemberFile(memberFile);
	}

	@Override
	public Member findOne(int id) {
		return memberRepository.selectMember(id);
	}

	@Override
	@Transactional
	public void edit(Member member) {
		memberRepository.updateMember(member);
	}

	@Override
	@Transactional
	public void remove(int id) {
		memberRepository.deleteMember(id);
	}
}
