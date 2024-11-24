import { defineStore } from 'pinia';
import axios from 'axios';

export const useFollowStore = defineStore('follow', () => {
    const isFollowing = ref(false);

    // 팔로우 상태 확인
    const checkFollowStatus = async (followerId, followingId) => {
        try {
            const response = await axios.get('/api/follow/status', {
                params: { followerId, followingId },
            });
            isFollowing.value = response.data;
        } catch (error) {
            console.error('팔로우 상태 확인 실패:', error);
        }
    };

    // 팔로우 기능
    const followUser = async (followerId, followingId) => {
        try {
            await axios.post('/api/follow', { followerId, followingId });
            isFollowing.value = true;
        } catch (error) {
            console.error('팔로우 실패:', error);
        }
    };

    // 언팔로우 기능
    const unfollowUser = async (followerId, followingId) => {
        try {
            await axios.delete('/api/follow', {
                data: { followerId, followingId },
            });
            isFollowing.value = false;
        } catch (error) {
            console.error('언팔로우 실패:', error);
        }
    };

    return { isFollowing, checkFollowStatus, followUser, unfollowUser };
});
