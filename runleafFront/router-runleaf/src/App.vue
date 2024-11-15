<template>
  <TheHeaderNav />
  <div class="main-container">
    <RouterView />
  </div>
</template>

<script setup>
import TheHeaderNav from './components/common/TheHeaderNav.vue';
</script>

<style scoped>
/* 페이지 전체를 flexbox로 사용하여 중앙 정렬 */
.main-container {
  display: flex;
  flex-direction: column;  /* 상하 배치 */
  justify-content: center; /* 세로 방향 중앙 정렬 */
  align-items: center;     /* 가로 방향 중앙 정렬 */
  min-height: 100vh;       /* 화면 전체를 차지하도록 */
  padding: 20px;
  box-sizing: border-box;
}

body, html {
  margin: 0;
  height: 100%;
}
</style>
