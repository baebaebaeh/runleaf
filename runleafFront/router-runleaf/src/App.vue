<template>
  <TheHeaderNav />
  <RouterView />
</template>

<script setup>
import TheHeaderNav from './components/common/TheHeaderNav.vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import '@/assets/styles/base.css'
router.push('/home'); // 다음 경로로 이동
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

body,
html {
  margin: 0;
  height: 100%;
}
</style>
