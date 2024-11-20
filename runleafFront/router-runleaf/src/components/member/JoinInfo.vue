<template>
  <div class="main-container">
    <div class="form-container">
      <div class="header-container">
        <div class="div2">회원가입</div>
      </div>
      <label for="username" class="label">아이디*</label>
      <div class="input-username">
        <input type="text" id="username" name="username" v-model="member.username" class="input-field"
          placeholder="영문 소문자, 숫자 조합 6~12자" required>
        <button @click="checkUsername" class="check-button">중복 검사</button>
      </div>
      <span v-if="errors.username" class="error-text">{{ errors.username }}</span>
      <!-- <span v-if="!isUsernameChecked" class="error-text">중복 검사를 해주세요.</span> -->
  
      <label for="password" class="label">비밀번호*</label>
      <input type="password" id="password" name="password" v-model="member.password" class="input-field"
        placeholder="영문, 숫자 조합 6~16자" required>
      <span v-if="errors.password" class="error-text">{{ errors.password }}</span>
  
      <!-- <label for="confirm_password" class="label">비밀번호 확인*</label>
      <input type="password" id="confirm_password" name="confirm_password" class="input-field" placeholder="비밀번호를 한 번 더 입력해주세요." required>
       -->
      <label for="email" class="label">이메일</label>
      <input type="email" id="email" name="email" v-model="member.email" class="input-field"
        placeholder="예) abc@ssafy.com">
      <!-- <span v-if="memberStore.errors.email">{{ memberStore.errors.email }}</span> -->
  
      <label for="phone" class="label">연락처*</label>
      <input type="tel" id="phone" name="phone" v-model="member.phone" class="input-field" placeholder="( - )을 제외하고 입력해주세요.">
      <span v-if="errors.phone" class="error-text">{{ errors.phone }}</span>
  
      <button @click="saveInfoAndNext" class="next-button">다음</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMemberStore } from '@/stores/member';
import { useRouter } from 'vue-router';
import axios from 'axios';

const memberStore = useMemberStore();
const router = useRouter();

const member = ref({
  username: '',
  password: '',
  email: '',
  phone: ''
})

const errors = ref({});
const isUsernameChecked = ref(false); // 중복 검사 여부

const validateInfo = () => {
  errors.value = {};

  if (!/^[a-z0-9]{6,12}$/.test(member.value.username)) {
    errors.value.username = '아이디는 영문 소문자, 숫자 조합 6~12자리여야 합니다.';
  } else if (isUsernameChecked.value === false) {
    errors.value.username = '아이디 중복 검사를 해주세요.';
  } else {
    delete errors.value.username; // 오류 제거
  }

  // 비밀번호 유효성 검사
  if (!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{6,16}/.test(member.value.password)) {
    errors.value.password = '비밀번호는 영문, 숫자, 특수기호가 포함된 6~16자리여야 합니다.';
  } 

  // 연락처 유효성 검사
  if (member.value.phone && !/^[0-9]{11}$/.test(member.value.phone)) {
    errors.value.phone = '연락처는 ( - )을 제외한 숫자 11자리여야 합니다.';
  } 

  return Object.keys(errors.value).length === 0; // 오류가 없으면 true 반환
}

const checkUsername = async () => {
  if (!member.value.username) {
    // 아이디가 빈 문자열인 경우 에러 메시지를 설정
    errors.value.username = '아이디는 영문 소문자, 숫자 조합 6~12자리여야 합니다.';
    isUsernameChecked.value = false;
    return; 
  }
  try {
    const response = await axios.get(`/api/check?username=${member.value.username}`);
    if (response.data) {
      errors.value.username = '이미 사용 중인 아이디입니다.';
    } else {
      errors.value.username = '';  // 중복되지 않으면 오류 메시지 제거
      isUsernameChecked.value = true;
      alert('사용 가능한 아이디입니다.');
    }
  } catch (error) {
    console.error('아이디 중복 검사 오류:', error);
  }
};

const saveInfoAndNext = () => {
  console.log(isUsernameChecked.value)
  if (validateInfo() && isUsernameChecked.value) {
    memberStore.updateJoinForm(member.value);
    router.push('/join/profile'); // 다음 경로로 이동
  } else if (!isUsernameChecked.value) {
    errors.value.username = '아이디 중복 검사를 해주세요.'
  }
};

</script>

<style scoped>
.main-container {
  display: flex;
  justify-content: center; 
  align-items: center;     
  min-height: 100vh;       
  box-sizing: border-box;
  padding-top: 100px;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  background: #ffffff;
  padding-bottom: 320px; 
  width: 440px; 
  max-width: 640px;
  box-sizing: border-box;
}

.header-container {
  width: 100%;
  margin: 20px;
  text-align: center;
}

.input-username {
  display: flex;
  align-items: center;
  width: 100%;
}

.div2 {
  color: #000000;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 25px;
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
  height: 55px; 
  padding: 12px; 
  font-size: 16px; 
  box-sizing: border-box;
  margin-top: 10px; /* 간격 증가 */
}

.check-button {
  width: 120px; /* 버튼 크기 증가 */
  height: 55px; /* 버튼 높이 증가 */
  margin-top: 8px;
  margin-left: 8px; /* 간격 증가 */
  padding: 10px 14px; /* 내부 여백 증가 */
  background-color: #d1d1d1;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.check-button:hover {
  background-color: #9c9c9c;
}

.next-button {
  background: #d1d1d1;
  width: 100%;
  height: 60px; /* 높이 증가 */
  margin-top: 25px; /* 간격 증가 */
  color: #ffffff;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 18px; /* 폰트 크기 증가 */
  font-weight: 600;
  text-align: center;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.next-button:hover {
  background-color: #9c9c9c;
}

.error-text {
  color: #fd6c6c;
  font-size: 14px; 
}
</style>
