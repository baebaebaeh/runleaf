import { defineStore } from 'pinia';
import axios from 'axios';
import { ref } from 'vue';

export const useFollowStore = defineStore('follow', () => {
    const isFollowing = ref(false);

    // 팔로우 상태 확인
    const checkFollowStatus = async (username) => {
        try {
            const token = sessionStorage.getItem('token');
            const response = await axios.get(`/api/follow/${username}/status`, {
                headers: {
                    authorization: `Bearer ${token}`,
                }
            });
            isFollowing.value = response.data.isFollowing; // API 응답 구조에 맞게 수정
        } catch (error) {
            console.error('팔로우 상태 확인 실패:', error);
        }
    };

    // 팔로우 기능
const followUser = async (username) => {
    try {
        const token = sessionStorage.getItem('token'); // 토큰 가져오기
        if (!token) {
            console.error('토큰이 없습니다. 로그인이 필요합니다.');
            return;
        }

        await axios.post(`/api/follow/${username}`,
            {}, // POST 요청에서 데이터가 필요하지 않으므로 빈 객체 전달
            {
                headers: {
                    authorization: `Bearer ${token}`, // 인증 헤더 추가
                },
            }
        );
        isFollowing.value = true;
    } catch (error) {
        console.error('팔로우 실패:', error);
    }
};

// 언팔로우 기능
const unfollowUser = async (username) => {
    try {
        const token = sessionStorage.getItem('token'); // 토큰 가져오기
        if (!token) {
            console.error('토큰이 없습니다. 로그인이 필요합니다.');
            return;
        }

        await axios.delete(`/api/follow/${username}`, {
            headers: {
                authorization: `Bearer ${token}`, // 인증 헤더 추가
            },
        });
        isFollowing.value = false;
    } catch (error) {
        console.error('언팔로우 실패:', error);
    }
};

const getFollowStats = async (memberId) => {
    try {
        const response = await axios.get(`/api/follow/${memberId}/stats`);
        return response.data; // { followingCount: 10, followerCount: 20 }
    } catch (error) {
        console.error('팔로잉/팔로워 수 가져오기 실패:', error);
        return { followingCount: 0, followerCount: 0 };
    }
};


    return { isFollowing, checkFollowStatus, followUser, unfollowUser, getFollowStats };
});
