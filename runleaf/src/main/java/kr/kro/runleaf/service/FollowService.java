package kr.kro.runleaf.service;

import java.util.Map;

public interface FollowService {

	public void follow(int followerId, int followingId);

	public void unfollow(int followerId, int followingId);

	public Map<String, Integer> getFollowStats(int memberId);

	public boolean isFollowing(int followerId, int followingId);
}
