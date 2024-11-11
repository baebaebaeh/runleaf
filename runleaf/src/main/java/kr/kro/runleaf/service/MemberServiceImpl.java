package kr.kro.runleaf.service;

import org.springframework.stereotype.Service;	
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD:runleaf/src/main/java/kr/kro/runleaf/service/MemberServiceImpl.java
import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.repository.MemberRepository;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;
=======
import kr.kro.runleaf.member.domain.Member;
import kr.kro.runleaf.member.domain.MemberFile;
import kr.kro.runleaf.member.repository.MemberRepository;
>>>>>>> 60f9abea388285be61f9329ea876dbc6e80d139d:runleaf/src/main/java/kr/kro/runleaf/member/service/MemberServiceImpl.java

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
