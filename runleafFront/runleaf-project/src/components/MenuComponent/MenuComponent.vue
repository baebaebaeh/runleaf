<template>
  <RouterLink class="home-bar" :to="{ name: `${pathName}` }">
    <div class="home-logo">
      <img class="home-img" :src="`/api/icons/home-img.svg`" />
    </div>
    <div class="home-text">
      <div class="home-txt">홈</div>
    </div>
  </RouterLink>
</template>

<script setup>
</script>

<style scoped></style>