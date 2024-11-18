<template>
  <div class="home">
    <div class="feed-container">
      <div class="div">러닝 피드</div>
      <div class="feed">
        <div class="main-image">
          <div class="div">지도</div>
          <img class="image" :src="`/api/icons/image.png`" />
        </div>
        <div class="content">
          <div class="div2">제목</div>
          <div class="div2">내용</div>
          <div class="div2">누가 어디서 난이도 등등</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import '@/assets/styles/home.css'
</script>

<style scoped>

</style>