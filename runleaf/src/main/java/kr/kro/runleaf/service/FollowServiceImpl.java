package kr.kro.runleaf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kro.runleaf.repository.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {
	
	private final FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
		this.followRepository = followRepository;
	}

	@Transactional
    public void follow(int followerId, int followingId) {
		followRepository.insertFollow(followerId, followingId);
    }

    @Transactional
    public void unfollow(int followerId, int followingId) {
    	followRepository.deleteFollow(followerId, followingId);
    }

    public Map<String, Integer> getFollowStats(int memberId) {
        int followingCount = followRepository.getFollowingCount(memberId);
        int followerCount = followRepository.getFollowerCount(memberId);

        Map<String, Integer> stats = new HashMap<>();
        stats.put("followingCount", followingCount);
        stats.put("followerCount", followerCount);

        return stats;
    }

    public boolean isFollowing(int followerId, int followingId) {
        return followRepository.isFollowing(followerId, followingId);
    }
}
