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
.main-container {
  display: flex;
  flex-direction: column;  
  justify-content: center; 
  align-items: center;     
  min-height: 100vh;       
  padding: 20px;
  box-sizing: border-box;
}

body, html {
  margin: 0;
  height: 100%;
}
</style>
