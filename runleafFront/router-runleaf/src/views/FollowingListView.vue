<template>
    <div class="container">
        <div>
            팔로잉 목록
        </div>
        <RouterLink  v-for="(member, i) in memberDataList" :key="i" :to="{ name: 'otherInfo', params: { username: member.username } }" class="profile-section">
            <div class="profile">
                <img class="vector" :src="`/api/uploads/${member.memberFileUrl}`" alt="프로필 이미지" />
            </div>
            <div class="profile-details">
                <div class="profile-header">
                    <div class="username">{{ member.username }}</div>
                </div>
            </div>
        </RouterLink>
    </div>
</template>
<script setup>
// ======================= 임포트 =======================
import { useAuthStore } from '@/stores/auth';
import { useFollowStore } from '@/stores/follow';
import { ref, onMounted, onUnmounted, watch, computed } from 'vue';
import { onBeforeRouteLeave, RouterLink, useRoute, useRouter } from 'vue-router'
import axios from 'axios';
import { useGpsStore } from '@/stores/gpsStore.js';
import MapComponent from '@/views/NaverMapTESTView.vue'
// ======================= 임포트 ======================
// ======================= 변수선언 =======================
const boardSearch = ref({
    page: 1,
    username: '',
    orderInt: 3,
    latitude: -1,
    longitude: -1,
});
let isFetching = false;
let hasMoreData = true;

const route = useRoute();
const followStore = useFollowStore();
const authStore = useAuthStore();

const memberDataList = ref([]);
const isFollowing = computed(() => followStore.isFollowing);
const followStats = computed(() => followStore.followStats);
const isDifferentMember = computed(() => authStore.loginUsername !== route.params.username);
// ======================= 변수선언 =======================

const fetchMemberDataList = async (username) => {
    try {
        const token = sessionStorage.getItem('token')
        const response = await axios.get(`/api/following/${username}`, {
            headers: {
                'authorization': `Bearer ${token}`,
            },
        });
        memberDataList.value = response.data;
        console.log(memberDataList.value)
    } catch (error) {
        console.error('사용자 정보 가져오기 실패:', error);
    }
};

onMounted(async () => {
    await fetchMemberDataList(authStore.loginUsername);
});
</script>

<style scoped>

.container {
    background: #ffffff;
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: flex-start;
    justify-content: flex-start;
    position: relative;
    overflow: hidden;
    padding-top: 10%;
    margin-left: 50px;
}
.profile-section {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    width: 100%;
    max-width: 400px;
    gap: 20px;
    /* 프로필과 상세 정보 간 간격 */
}

.profile {
    width: 120px;
    height: 60px;
    border-radius: 50%;
    background: #ffffff;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
}

.vector {
    width: 50px;
    object-fit: contain;
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
</style>
