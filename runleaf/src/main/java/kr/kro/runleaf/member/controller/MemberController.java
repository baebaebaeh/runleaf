package kr.kro.runleaf.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.member.domain.dto.MemberJoinDto;
import kr.kro.runleaf.member.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public ResponseEntity<MemberJoinDto> join() {
		memberService.join();
		return ResponseEntity.status(HttpStatus.OK).body();
	}
	
}
