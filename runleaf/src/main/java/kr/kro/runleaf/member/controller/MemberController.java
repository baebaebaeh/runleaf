package kr.kro.runleaf.member.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.member.domain.MemberFile;
import kr.kro.runleaf.member.domain.dto.MemberDto;
import kr.kro.runleaf.member.domain.dto.MemberFindDto;
import kr.kro.runleaf.member.service.MemberService;

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
	public void join(@RequestParam("file") MultipartFile file, @RequestBody MemberDto memberDto) throws IllegalStateException, IOException {
		String orgName = file.getOriginalFilename();
		if (orgName.length() > 0) {
			String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
			File dir = new File("c:/SSAFY/uploads" + subDir);
			dir.mkdirs();
			String systemName = UUID.randomUUID().toString() + orgName;
			file.transferTo(new File(dir, systemName));
			
			MemberFile memberImage = new MemberFile();
			memberImage.setFilePath(subDir);
			memberImage.setOrgName(orgName);
			memberImage.setSystemName(systemName);
			memberDto.setMemberImage(memberImage);
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
