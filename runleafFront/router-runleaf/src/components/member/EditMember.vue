<template>
    <div class="main-container">
        <div class="form-container">
            <div class="header-container">
                <div class="title">회원 정보 변경</div>
            </div>
            <label for="username" class="label">아이디</label>
            <input type="text" id="username" v-model="memberStore.memberEditForm.username" class="input-field-username" disabled />

            <label for="email" class="label">이메일</label>
            <input type="email" id="email" v-model="memberStore.memberEditForm.email" class="input-field" />

            <label for="phone" class="label">연락처</label>
            <input type="tel" id="phone" v-model="memberStore.memberEditForm.phone" class="input-field" placeholder="( - )을 제외하고 입력해주세요." />
            <span v-if="errors.phone" class="error-text">{{ errors.phone }}</span>

            <button @click="editMemberInfo" class="save-button">저장</button>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { useMemberStore } from '@/stores/member';
import { onMounted, ref } from 'vue';

// Pinia store
const memberStore = useMemberStore();

const errors = ref({});

const validateInfo = () => {
  errors.value = {};

  // 연락처 유효성 검사
  if (memberStore.memberEditForm.phone && !/^[0-9]{11}$/.test(memberStore.memberEditForm.phone)) {
    errors.value.phone = '연락처는 ( - )을 제외한 숫자 11자리여야 합니다.';
  } 

  return Object.keys(errors.value).length === 0; 
}

const editMemberInfo = () => {
    if (validateInfo()) {
        memberStore.editMember(); 
        router.push('/joinInfo')
  }
};

onMounted(() => {
  memberStore.getMember();
});
</script>

<style scoped>
.main-container {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    min-height: 100vh;
    box-sizing: border-box;
    padding-top: 50px;
}

.form-container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    background: #ffffff;
    padding: 40px 20px;
    width: 440px;
    max-width: 640px;
    box-sizing: border-box;
    border-radius: 10px;
}

.header-container {
    width: 100%;
    margin-bottom: 60px;
    text-align: center;
}

.title {
    color: #000000;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 24px;
    font-weight: 600;
}

.label {
    color: #000000;
    font-family: "Inter-Regular", sans-serif;
    font-size: 16px;
    font-weight: 400;
    width: 100%;
    margin-top: 15px;
}

.input-field {
    background: #ffffff;
    border-radius: 5px;
    border: 1px solid #aeaeae;
    width: 100%;
    height: 45px;
    padding: 10px;
    font-size: 14px;
    box-sizing: border-box;
    margin-top: 5px;
}

.input-field-username {
    background: #ffffff;
    border-radius: 5px;
    border: 1px solid #aeaeae;
    width: 100%;
    height: 45px;
    padding: 10px;
    font-size: 14px;
    box-sizing: border-box;
    margin-top: 5px;
    color: #9c9c9c;
}

.save-button {
    background: #d1d1d1;
    width: 100%;
    height: 50px;
    margin-top: 25px;
    color: #ffffff;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 18px;
    font-weight: 600;
    text-align: center;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.save-button:hover {
    background-color: #9c9c9c;
}
</style>
