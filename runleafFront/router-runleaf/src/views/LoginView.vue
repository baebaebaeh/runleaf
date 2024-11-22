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
        <button @click="login" type="submit" class="login-button">로그인</button>
      </form>
      <p class="signup-link">
        아직 회원이 아니신가요? <router-link to="/join">회원가입</router-link>
      </p>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { ref } from 'vue';

const authStore = useAuthStore();

const username = ref('');
const password = ref('');

const login = function () {
  authStore.login(username.value, password.value)
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 100vh;
}

.login-card {
  padding-top: 30%;
  width: 320px; 
  max-width: 450px;
}

.login-title {
  text-align: center;
  font-size: 26px;
  margin-bottom: 30px;
}

.input-group {
  margin-bottom: 5px; 
}

.input-group label {
  display: block;
  font-size: 16px; 
  color: #555;
  margin-bottom: 8px; 
}

.input-group input {
  width: 100%;
  padding: 12px; 
  font-size: 18px; 
  border: 1px solid #ccc;
  border-radius: 6px; 
  box-sizing: border-box;
}

.input-group input:focus {
  border-color: #5e5e5e;
  outline: none;
}

.error-message {
  color: #fd6c6c;
  font-size: 15px; 
  margin-bottom: 12px; 
}

.login-button {
  margin-top: 5px;
  width: 100%;
  padding: 12px;
  background-color: #c9c9c9;
  color: white;
  border: none;
  border-radius: 6px; 
  font-size: 18px; 
  cursor: pointer;
}

.login-button:hover {
  color: white;
  background-color: #a0a0a0;
}

.login-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.signup-link {
  text-align: center;
  margin-top: 20px; 
}

.signup-link a {
  color: #bdbdbd;
  font-size: 16px; 
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
  color: #747474;
}

</style>
