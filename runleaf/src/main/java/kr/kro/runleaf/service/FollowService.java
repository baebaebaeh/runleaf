package kr.kro.runleaf.service;

import java.util.List;
import java.util.Map;

public interface FollowService {

	public void follow(int followerId, int followingId);

	public void unfollow(int followerId, int followingId);

	public List<Map<String, Object>> getFollowers(int memberId);

	public List<Map<String, Object>> getFollowing(int memberId);

	public boolean isFollowing(int followerId, int followingId);
}
