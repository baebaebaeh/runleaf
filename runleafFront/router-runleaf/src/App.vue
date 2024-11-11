<template>
  <div>
      <RouterLink :to="{ name: 'gps'}">gps</RouterLink>
      <RouterLink :to="{ name: 'gps'}">gps</RouterLink>
  </div>
  <RouterView />
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref } from 'vue';
const a = ref('')
</script>

<style scoped>

</style>