<template>
  <div class="form-container">
    <div class="header-container">
      <RouterLink to="/">
        <img class="image-arrow" src="@/assets/images/join/icons8-arrow-30.png" alt="다음 아이콘">
      </RouterLink>
      <div class="div2">회원가입</div>
    </div>

    <label for="username" class="label">아이디*</label>
    <input type="text" id="username" name="username" v-model="member.username" class="input-field" placeholder="영문, 숫자 조합 6~12자" required>

    <label for="password" class="label">비밀번호*</label>
    <input type="password" id="password" name="password" v-model="member.password" class="input-field" placeholder="영문, 숫자 조합 8~16자" required>

    <label for="confirm_password" class="label">비밀번호 확인*</label>
    <input type="password" id="confirm_password" name="confirm_password" class="input-field"
      placeholder="비밀번호를 한 번 더 입력해주세요." required>

    <label for="nickname" class="label">닉네임*</label>
    <input type="text" id="nickname" name="nickname" v-model="member.nickname" class="input-field" placeholder="영문, 숫자 조합 8~16자" required>

    <label for="email" class="label">이메일*</label>
    <input type="email" id="email" name="email" v-model="member.email" class="input-field" placeholder="예) abc@ssafy.com" required>

    <label for="phone" class="label">연락처*</label>
    <input type="tel" id="phone" name="phone" v-model="member.phone" class="input-field" placeholder="‘-’을 제외하고 입력해주세요." required>

    <button @click="saveInfoAndNext" class="next-button">다음</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMemberStore } from '@/stores/member';
import { useRouter } from 'vue-router';

const memberStore = useMemberStore();
const router = useRouter();

const member = ref({
  username: '',
  password: '',
  nickname: '',
  email: '',
  phone: ''
})

const saveInfoAndNext = () => {
  memberStore.updateMemberInfo(member.value);
  console.log(memberStore.memberInfo)
  router.push('/join/profile');
};

</script>

<style scoped>
/* 최상위 컨테이너를 화면 중앙에 배치 */
form {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

/* form-container 요소를 가운데 정렬하고, 왼쪽 정렬 */
.form-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  /* 내부 콘텐츠는 왼쪽 정렬 */
  background: #ffffff;
  padding: 100px;
  width: 600px;
  max-width: 800px;
  box-sizing: border-box;
}

.header-container {
  display: block;
  align-items: center;
  margin-bottom: 20px;
}

.image-arrow {
  display: inline-flex;
  width: 25px;
  height: 25px;
  margin-right: 10px;
}

.div2 {
  color: #000000;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 20px;
  font-weight: 600;
}

.label {
  color: #000000;
  font-family: "Inter-Regular", sans-serif;
  font-size: 14px;
  font-weight: 400;
  width: 100%;
  margin-top: 10px;
}

.input-field {
  background: #ffffff;
  border-radius: 5px;
  border: 1px solid #aeaeae;
  width: 100%;
  height: 50px;
  padding: 10px;
  font-size: 14px;
  box-sizing: border-box;
  margin-top: 5px;
}

.next-button {
  background: #c5c5c5;
  width: 100%;
  height: 55px;
  margin-top: 20px;
  color: #ffffff;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 16px;
  font-weight: 600;
  text-align: center;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
