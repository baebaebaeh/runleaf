package kr.kro.runleaf.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowRepository {

    // 팔로우 추가
    void insertFollow(@Param("followerId") int followerId, @Param("followingId") int followingId);

    // 팔로우 삭제
    void deleteFollow(@Param("followerId") int followerId, @Param("followingId") int followingId);

    // 팔로잉 수 조회
    int getFollowingCount(@Param("memberId") int memberId);

    // 팔로워 수 조회
    int getFollowerCount(@Param("memberId") int memberId);

    // 팔로우 여부 확인
    boolean isFollowing(@Param("followerId") int followerId, @Param("followingId") int followingId);
}
