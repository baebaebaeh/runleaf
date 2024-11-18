<template>
  <div class="login-container">
    <div class="login-card">
      <h2 class="login-title">로그인</h2>
      <form @submit.prevent="handleSubmit">
        <div class="input-group">
          <label for="username">아이디</label>
          <input type="text" id="username" v-model="username" required />
        </div>
        <div class="input-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        <button @click="login" type="submit" class="login-button" :disabled="isSubmitting">로그인</button>
      </form>
      <p class="signup-link">
        아직 회원이 아니신가요? <router-link to="/signup">회원가입</router-link>
      </p>
    </div>
  </div>
</template>

<script setup>
import { useMemberStore } from '@/stores/member';
import { ref } from 'vue';

const store = useMemberStore();

const username = ref('');
const password = ref('');

const login = function () {
  store.memberLogin(username.value, password.value)
}



</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding-top: 200px;
  height: 100vh;
}

.login-card {
  padding: 20px;
  width: 280px;
  max-width: 400px;
}

.login-title {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 15px;
}

.input-group label {
  display: block;
  font-size: 14px;
  color: #555;
  margin-bottom: 5px;
}

.input-group input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.input-group input:focus {
  border-color: #5e5e5e;
  outline: none;
}

.error-message {
  color: #fd6c6c;
  font-size: 14px;
  margin-bottom: 10px;
}

.login-button {
  width: 100%;
  padding: 10px;
  background-color: #bdbdbd;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

.login-button:hover {
  color: white;
  background-color: #808080;
  
}

.login-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.signup-link {
  text-align: center;
  margin-top: 15px;
}

.signup-link a {
  color: #bdbdbd;
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
  color: #747474;
}
</style>
