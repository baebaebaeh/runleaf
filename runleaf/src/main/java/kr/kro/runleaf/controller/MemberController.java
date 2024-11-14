package kr.kro.runleaf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 회원 가입
	@PostMapping
	public ResponseEntity<String> join(
	        @RequestPart(value = "member") @Valid Member member,
	        @RequestPart(value = "file", required = false) MultipartFile file)
	        throws IllegalStateException, IOException {

	    try {
	        String orgName;
	        File savedFile;
	        
	        // 기본 이미지 설정
	        if (file == null || file.isEmpty()) {
	            orgName = "profile-default.png";
	            // 서버 내 기본 이미지 파일 경로 설정
	            File defaultFile = ResourceUtils.getFile("c:/SSAFY/uploads/default/profile-default.png");
	            
	            // 기본 이미지를 저장할 위치에 복사
	            String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
	            File dir = new File("c:/SSAFY/uploads" + subDir);
	            dir.mkdirs();
	            
	            String systemName = UUID.randomUUID().toString() + "_" + orgName;
	            savedFile = new File(dir, systemName);
	            Files.copy(defaultFile.toPath(), savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        } else {
	            // 사용자 업로드 이미지 처리
	            orgName = file.getOriginalFilename();
	            String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
	            File dir = new File("c:/SSAFY/uploads" + subDir);
	            dir.mkdirs();

	            String systemName = UUID.randomUUID().toString() + "_" + orgName;
	            savedFile = new File(dir, systemName);
	            file.transferTo(savedFile);
	        }

	        // 파일 정보 세팅
	        MemberFile memberFile = new MemberFile();
	        memberFile.setFilePath(savedFile.getParent()); // 파일 경로
	        memberFile.setOrgName(orgName);                // 원본 파일명
	        memberFile.setSystemName(savedFile.getName()); // 시스템 저장 파일명
	        member.setMemberFile(memberFile);

	        if (memberService.join(member)) {
	            return ResponseEntity.ok("회원가입이 완료되었습니다!");
	        }
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 문제가 발생했습니다.");
	    }
	}

	// 회원 조회
	@GetMapping("/{id}")
	public ResponseEntity<Member> findOne(@PathVariable("id") int id) {
		Member member = memberService.findOne(id);
		if (member == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}

	@PutMapping("/{id}")
	public void edit(@PathVariable("id") int id, @RequestBody Member memberDto) {
		memberService.edit(memberDto);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable("id") int id) {
		memberService.remove(id);
	}
}
