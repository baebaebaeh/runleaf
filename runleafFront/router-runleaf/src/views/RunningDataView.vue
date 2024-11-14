<template>
  <div>
    <RouterLink :to="{ name: 'runningDataList' }">runningDataList</RouterLink>
  </div>
  <div>
    <RouterLink :to="{ name: 'runningDataForm' }">runningDataForm</RouterLink>
  </div>
  <RouterView />
</template>

<script setup>
</script>

<style scoped>
</style>