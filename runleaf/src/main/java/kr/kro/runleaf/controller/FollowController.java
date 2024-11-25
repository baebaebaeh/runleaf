package kr.kro.runleaf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.service.FollowService;
import kr.kro.runleaf.service.MemberService;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;
    private final MemberService memberService;
    
    public FollowController(FollowService followService, MemberService memberService) {
		this.followService = followService;
		this.memberService = memberService;
	}

	// 팔로우 상태 확인
    @GetMapping("/{username}/status")
    public ResponseEntity<Map<String, Boolean>> checkFollowingStatus(@PathVariable String username) {
    	String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
       
    	int followerId = memberService.getMemberIdByUsername(loggedInUsername);
        int followingId = memberService.getMemberIdByUsername(username);

        boolean isFollowing = followService.isFollowing(followerId, followingId);

        System.out.println(isFollowing);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFollowing", isFollowing);
        return ResponseEntity.ok(response);
    }

    // 팔로우 요청
    @PostMapping("/{username}")
    public ResponseEntity<Void> follow(
            @PathVariable String username) {
    	String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        int followerId = memberService.getMemberIdByUsername(loggedInUsername);
        int followingId = memberService.getMemberIdByUsername(username);

        followService.follow(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    // 언팔로우 요청
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> unfollow(
            @PathVariable String username) {
    	String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

    	int followerId = memberService.getMemberIdByUsername(loggedInUsername);
    	int followingId = memberService.getMemberIdByUsername(username);

        followService.unfollow(followerId, followingId);
        return ResponseEntity.noContent().build();
    }
    
    // 팔로워, 팔로잉 조회
    @GetMapping("/{username}/stats")
    public ResponseEntity<Map<String, Integer>> getFollowStats(@PathVariable String username) {
    	int memberId = memberService.getMemberIdByUsername(username);
    	
        Map<String, Integer> stats = followService.getFollowStats(memberId);
        return ResponseEntity.ok(stats);
    }
}
