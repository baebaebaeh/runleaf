<template>
    <div class="container">
        <div class="frame-44">
            <div class="title">내 정보</div>
            <RouterLink :to="{ name: 'editMember' }" class="edit-info">변경</RouterLink>
        </div>
        <div class="frame-48">
            <div class="profile">
                <img class="vector" :src="`/api/uploads/${memberStore.memberInfoForm.memberFileUrl}`" />
            </div>
        </div>
        <div class="frame-462">
            <div class="frame-46">
                <div class="div4">아이디</div>
            </div>
            <div class="frame-47">
                    <div class="my-info">{{ memberStore.memberInfoForm.username }}</div>
            </div>
        </div>
        <div class="frame-462">
            <div class="frame-46">
                <div class="div4">이메일</div>
            </div>
            <div class="frame-47">
                <div class="my-info">{{ memberStore.memberInfoForm.email }}</div>
            </div>
        </div>
        <div class="frame-472">
            <div class="frame-46">
                <div class="div4">연락처</div>
            </div>
            <div class="frame-47">
                <div class="my-info">{{ memberStore.memberInfoForm.phone }}</div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useMemberStore } from '@/stores/member';
import { onMounted } from 'vue';

const memberStore = useMemberStore();

onMounted(() => {
    memberStore.getMember();
})
</script>

<style scoped>
.container {
    background: #ffffff;
    display: flex;
    flex-direction: column;
    gap: 15px;
    align-items: center;
    justify-content: center;
    height: 600px;
    position: relative;
    overflow: hidden;
    padding-top: 150px;
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
    font-size: 19px;
    line-height: 130%;
    font-weight: 400;
    text-align: right;
    margin-left: auto;
    position: relative;
}

.frame-48 {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 85%;
    max-width: 120px;
    padding: 60px;
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

.frame-462,
.frame-472 {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 85%;
    max-width: 400px;
    padding: 10px 5px;
    border-bottom: 1px solid #e3dfdf;
}

.div4,
.my-info {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 16px;
    line-height: 130%;
    font-weight: 400;
}

.info-container {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding-top: 20px;
}

.my-info {
    font-size: 18px;
    color: #9c9c9c;
    margin-bottom: 5px;
}

.edit-info:hover {
    color: #4d4d4d;
}
</style>
