package kr.kro.runleaf.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.service.FollowService;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping
    public ResponseEntity<String> followUser(@RequestBody Map<String, Integer> payload) {
        int followerId = payload.get("followerId");
        int followingId = payload.get("followingId");
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("팔로우 성공");
    }

    @DeleteMapping
    public ResponseEntity<String> unfollowUser(@RequestBody Map<String, Integer> payload) {
    	int followerId = payload.get("followerId");
    	int followingId = payload.get("followingId");
        followService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok("팔로우 취소 성공");
    }

    @GetMapping("/followers/{memberId}")
    public ResponseEntity<List<Map<String, Object>>> getFollowers(@PathVariable int memberId) {
        return ResponseEntity.ok(followService.getFollowers(memberId));
    }

    @GetMapping("/following/{memberId}")
    public ResponseEntity<List<Map<String, Object>>> getFollowing(@PathVariable int memberId) {
        return ResponseEntity.ok(followService.getFollowing(memberId));
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> isFollowing(@RequestParam int followerId, @RequestParam int followingId) {
        return ResponseEntity.ok(followService.isFollowing(followerId, followingId));
    }
}
