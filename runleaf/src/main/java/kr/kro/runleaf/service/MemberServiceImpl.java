package kr.kro.runleaf.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.dto.MemberEditRequest;
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
		member.setTotalDist(0);
		member.setTotalRunningSecond(0);
		memberRepository.insertMember(member);

		// 회원 이미지
		MemberFile memberFile = member.getMemberFile();

		memberFile.setMemberId(member.getMemberId());
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
	public MemberResponse findMe(String username) {

		Member member = memberRepository.selectMemberByUsername(username);

		MemberFile memberFile = memberRepository.selectMemberFileByMemberId(member.getMemberId()); // 파일이 없을 경우 null 처리

		MemberResponse memberResponse = new MemberResponse(member);
		String fileUrl = memberFile.getFilePath();
		memberResponse.setMemberFileUrl(fileUrl);

		return memberResponse;
	}
	
	@Override
	public int getMemberIdByUsername(String username) {
		return memberRepository.getMemberId(username);
	}

	@Override
	public MemberResponse findOne(String username) {
		Member member = memberRepository.selectMemberByUsername(username);

		MemberFile memberFile = memberRepository.selectMemberFileByMemberId(member.getMemberId()); // 파일이 없을 경우 null 처리

		MemberResponse memberResponse = new MemberResponse(member);
		String fileUrl = memberFile.getFilePath();
		memberResponse.setMemberFileUrl(fileUrl);

		return memberResponse;
	}

	@Override
	@Transactional
	public void edit(MemberEditRequest editReqeust) {
		memberRepository.updateMember(editReqeust);
	}

//	@Override
//	@Transactional
//	public void remove() {
//		memberRepository.deleteMember(username);
//	}

	@Override
	public String editProfile(MultipartFile file, String username) throws IllegalStateException, IOException {
		int memberId = memberRepository.getMemberId(username);

		MemberFile memberFile = new MemberFile();
		
		memberFile.setMemberId(memberId);

		String orgName;
		File savedFile;
		String subDir;
		String systemName;
		// 기본 이미지 설정
		if (file == null || file.isEmpty()) {
			orgName = "profile-default.png";
			systemName = orgName;
			// 기본 이미지를 저장할 위치에 복사
			subDir = "uploads/default";
		} else {
			// 사용자 업로드 이미지 처리
			orgName = file.getOriginalFilename();
			subDir = "uploads" + new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
			File dir = new File("c:/SSAFY/" + subDir);
			dir.mkdirs();

			systemName = UUID.randomUUID().toString() + "_" + orgName;
			savedFile = new File(dir, systemName);
			file.transferTo(savedFile);
		}

		// 파일 정보 세팅
		memberFile.setFilePath(subDir + "/" + systemName); // 파일 경로
		memberFile.setOrgName(orgName); // 원본 파일명
		memberFile.setSystemName(systemName); // 시스템 저장 파일명
		
		memberRepository.updateMemberFile(memberFile);
		return memberFile.getFilePath();
	}



	

}
