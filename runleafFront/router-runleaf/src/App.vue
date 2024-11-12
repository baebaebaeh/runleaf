<template>
  <div>
    <TheHeaderNav />
  </div>
  <RouterView />
  <!--  
      <RouterLink :to="{ name: 'board'}">board</RouterLink>
      <RouterLink :to="{ name: 'runningData'}">runningData</RouterLink>
      <RouterLink :to="{ name: 'gps'}">gps</RouterLink>
      <RouterLink :to="{ name: 'join'}">회원가입</RouterLink>
    -->
  <!-- 
  </div>
   -->
</template>

<script setup>
import TheHeaderNav from './components/common/TheHeaderNav.vue';
</script>

<style scoped></style>