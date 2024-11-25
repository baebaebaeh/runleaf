<template>
    <div class="container">
        <div class="frame-44">
            <RouterLink :to="{ name: 'editMember' }" class="edit-info">변경</RouterLink>
        </div>
        <div class="profile-section">
            <div class="profile">
                <img class="vector" :src="`/api/uploads/${memberStore.memberInfoForm.memberFileUrl}`" />
            </div>
            <div class="profile-details">
                <div class="username">{{ memberStore.memberInfoForm.username }}</div>
                <div class="stats">
                    <span>팔로워: {{ memberStore.memberInfoForm.followers || 0 }}</span>
                    <span>팔로잉: {{ memberStore.memberInfoForm.following || 0 }}</span>
                </div>
                <div v-if="isOtherProfile">
                    <button v-if="!followStore.isFollowing" @click="handleFollow" class="follow-btn">팔로우</button>
                    <button v-else @click="handleUnfollow" class="unfollow-btn">언팔로우</button>
                </div>
            </div>
        </div>
        <div class="info-section">
            <div class="info-detail">
                <div class="label">이메일</div>
                <div class="value">{{ memberStore.memberInfoForm.email }}</div>
            </div>
            <div class="info-detail">
                <div class="label">연락처</div>
                <div class="value">{{ memberStore.memberInfoForm.phone }}</div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useFollowStore } from '@/stores/follow';
import { useMemberStore } from '@/stores/member';
import { computed, onMounted } from 'vue';

const memberStore = useMemberStore();
const followStore = useFollowStore();
const authStore = useAuthStore();

const username = computed(() => memberStore.memberInfoForm.username); // 프로필 사용자 ID
const currentUsername = computed(() => authStore.loginUsername); // 현재 로그인 사용자 ID
const isOtherProfile = computed(() => username.value !== currentUsername.value); // 다른 사람 프로필인지 확인

const handleFollow = async () => {
    await followStore.followUser(currentUsername.value, username.value);
};

const handleUnfollow = async () => {
    await followStore.unfollowMember(currentUsername.value, username.value);
};

onMounted(() => {
    memberStore.getMember();
    if (isOtherProfile.value) {
        followStore.checkFollowStatus(currentUsername.value, username.value);
    }
})

</script>

<style scoped>
.container {
    background: #ffffff;
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
    justify-content: flex-start;
    height: 600px;
    position: relative;
    overflow: hidden;
    padding-top: 10%;
}

.frame-44 {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 85%;
    max-width: 400px;
    position: relative;
}

.title {
    color: #000000;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 25px;
    line-height: 130%;
    font-weight: 600;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
}

.edit-info {
    color: #919191;
    font-family: "Inter-Regular", sans-serif;
    font-size: 17px;
    text-align: right;
    margin-left: auto;
    position: relative;
}

.edit-info:hover {
    color: #444444;
}

.profile-section {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    width: 85%;
    max-width: 400px;
    margin-top: 20px;
    gap: 20px;
    /* 프로필과 상세 정보 간 간격 */
}

.profile {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background: #ffffff;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
}

.vector {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
}

.profile-details {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 10px;
}

.username {
    font-size: 20px;
    font-weight: bold;
    color: #333;
}

.stats {
    display: flex;
    flex-direction: row;
    gap: 15px;
    /* 팔로워와 팔로잉 간 간격 */
}

.stats span {
    font-size: 14px;
    color: #666;
}

.follow-btn, .unfollow-btn {
    padding: 8px 16px;
    font-size: 14px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.follow-btn {
    background-color: #a1ffa5; /* 녹색 */
    color: white;
}

.unfollow-btn {
    background-color: #dddddd; /* 빨간색 */
    color: white;
}

.follow-btn:hover, .unfollow-btn:hover {
    opacity: 0.9;
}

.info-section {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 85%;
    max-width: 400px;
    gap: 15px;
    padding-top: 30px;
}

.info-detail {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
    padding: 10px 0;
    border-bottom: 1px solid #e3dfdf;
    /* 하단 구분선 */
}

.label {
    font-size: 16px;
    font-weight: 400;
    color: #000000;
    /* 더 명확한 색상 */
}

.value {
    font-size: 16px;
    font-weight: 400;
    color: #858585;
    /* 부드러운 색상 */
    text-align: right;
    /* 오른쪽 정렬 */
    word-break: break-all;
    /* 텍스트가 너무 길 경우 줄바꿈 */
}
</style>
