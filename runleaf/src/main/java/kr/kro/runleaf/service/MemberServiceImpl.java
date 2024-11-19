package kr.kro.runleaf.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.dto.MemberResponse;
import kr.kro.runleaf.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.memberRepository = memberRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public boolean join(Member member) {

		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setRole("ROLE_MEMBER");
		memberRepository.insertMember(member);

		// 회원 이미지
		MemberFile memberFile = member.getMemberFile();

		memberFile.setMemberId(member.getId());
		memberRepository.insertMemberFile(memberFile);

		return true;
	}

	@Override
	public boolean existsByUsername(String username) {
		if (memberRepository.existsByUsername(username) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public MemberResponse findOne(String username) {
		
		Member member = memberRepository.selectMemberByUsername(username);
		
		System.out.println(member);
		
		MemberFile memberFile = memberRepository.selectMemberFileByMemberId(member.getId()); // 파일이 없을 경우 null 처리
		
		MemberResponse memberResponse = new MemberResponse(member);
		String fileUrl = memberFile.getFilePath() + "/" + memberFile.getSystemName();
		memberResponse.setMemberFileUrl(fileUrl);
		
		return memberResponse;
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
