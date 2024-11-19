<template>
    <div class="container">
        <div class="frame-44">
            <div class="div2">내 정보</div>
            <div class="edit-info">
                <RouterLink :to="{ name: 'memberEdit' }">변경</RouterLink>
            </div>
        </div>
        <div class="frame-48">
            <div class="frame-9">
                <div class="user-circle-1">
                    <img class="vector" :src="member.memberFile" />
                </div>
            </div>
        </div>
        <div class="frame-462">
            <div class="frame-46">
                <div class="div4">이메일</div>
            </div>
            <div class="frame-47">
                <div class="email">{{ store.member.email }}</div>
            </div>
        </div>
        <div class="frame-472">
            <div class="frame-46">
                <div class="div4">연락처</div>
            </div>
            <div class="frame-47">
                <div class="phone">{{ store.member.phone }}</div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { useMemberStore } from '@/stores/member';
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const store = useMemberStore();
const route = useRoute();
const router = useRouter();

onMounted(() => {
    store.getMember()
})

</script>

<style scoped>
.container {
    background: #ffffff;
    display: flex;
    flex-direction: column;
    gap: 20px;
    align-items: center; /* 중앙 정렬 */
    justify-content: center; /* 중앙 정렬 */
    height: 640px;
    position: relative;
    overflow: hidden;
    padding-top: 80px;
}

.frame-44 {
    padding: 10px 0;
    display: flex;
    flex-direction: row;
    align-items: center; /* 세로 중앙 정렬 */
    justify-content: center; /* 기본 중앙 정렬 */
    width: 90%; /* 적당한 너비 설정 */
    max-width: 500px; /* 최대 너비 제한 */
    position: relative; /* "변경"을 오른쪽 끝에 배치하기 위해 relative 사용 */
}

.div2 {
    color: #000000;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 24px;
    line-height: 140%;
    font-weight: 600;
    position: absolute; /* 중앙 고정 */
    left: 50%; /* 왼쪽 기준으로 50% 이동 */
    transform: translateX(-50%); /* 정확히 가운데 정렬 */
    text-align: center;
}

.edit-info {
    color: #777777;
    font-family: "Inter-Regular", sans-serif;
    font-size: 18px;
    line-height: 140%;
    font-weight: 400;
    text-align: right;
    margin-left: auto; /* 오른쪽 정렬 */
    position: absolute; /* 버튼 고정 */
    right: 0; /* 부모 요소의 오른쪽 끝으로 고정 */
}

.frame-48 {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center; /* 중앙 정렬 */
    width: 90%;
    max-width: 150px;
    padding: 80px;
}

.user-circle-1 {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    background: #f0f0f0; /* 기본 배경색 추가 */
    display: flex;
    align-items: center; /* 이미지가 중앙에 배치되도록 */
    justify-content: center;
    overflow: hidden;
}

.frame-462, .frame-472 {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between; /* 양쪽 정렬 */
    width: 90%;
    max-width: 450px;
    padding: 15px 5px;
    border-bottom: 1px solid #e3dfdf;
}

.div4, .email, .phone {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 18px;
    line-height: 140%;
    font-weight: 400;
}

.email, .phone {
    text-align: right; /* 오른쪽 정렬 */
    color: #9c9c9c;
    font-size: 20px;
}

.edit-info a {
    color: #bdbdbd;
    text-decoration: none;
}

.edit-info a:hover {
    color: #747474;
}
</style>