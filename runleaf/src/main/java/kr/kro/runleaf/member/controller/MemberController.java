package kr.kro.runleaf.member.controller;

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
import org.springframework.web.bind.annotation.RestController;

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
	public void join(@RequestBody MemberDto memberDto) {
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
