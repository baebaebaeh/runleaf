package kr.kro.runleaf.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;		
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;
import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {
	
	private final MemberService memberService;
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping(consumes = "multipart/form-data")
	public void join(@RequestPart(value = "memberDto") MemberDto memberDto, 
	                 @RequestPart(value = "file", required = false) MultipartFile file) 
	                 throws IllegalStateException, IOException {

	    if (file != null && !file.isEmpty()) {  // 파일이 첨부된 경우만 처리
	        String orgName = file.getOriginalFilename();
	        
	        if (orgName != null && orgName.length() > 0) {
	            String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
	            File dir = new File("c:/SSAFY/uploads" + subDir);
	            dir.mkdirs();
	            
	            // 저장할 파일명 생성
	            String systemName = UUID.randomUUID().toString() + "_" + orgName;
	            file.transferTo(new File(dir, systemName));
	            
	            // 파일 정보 세팅
	            MemberFile memberFile = new MemberFile();
	            memberFile.setFilePath(subDir);
	            memberFile.setOrgName(orgName);
	            memberFile.setSystemName(systemName);
	            memberDto.setMemberFile(memberFile);
	        }
	    } else {  // 파일이 없는 경우 기본 프로필 이미지 설정
	        MemberFile defaultFile = new MemberFile();
	        defaultFile.setFilePath("/default/path"); // 기본 프로필 이미지 경로 설정
	        defaultFile.setOrgName("기본 프로필 이미지");
	        defaultFile.setSystemName("default_profile.jpg");
	        memberDto.setMemberFile(defaultFile);
	    }

	    memberService.join(memberDto);
	}


	
	// 회원 조회
	@GetMapping("/{id}")
	public ResponseEntity<MemberFindDto> findOne(@PathVariable("id") int id) {
		MemberFindDto member = memberService.findOne(id);
		if (member == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
	@PutMapping("/{id}")
	public void edit(@PathVariable("id") int id, @RequestBody MemberDto memberDto) {
		memberService.edit(memberDto);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable("id") int id) {
		memberService.remove(id);
	}
}
