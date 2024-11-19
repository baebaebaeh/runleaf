<template>
    <div class="nav">
        <RouterLink class="menu-bar" :to="{ name: 'menu' }">
            <img class="menu-img" :src="`/api/icons/menu-img.svg`" />
        </RouterLink>
        <RouterLink class="logo-bar" :to="{ name: 'home' }">
            <img class="feather-img" :src="`/api/icons/feather-img.svg`" />
        </RouterLink>
        <RouterLink class="mypage-bar" :to="{ name: 'home' }">
            <img class="smile-img" :src="`/api/icons/smile-img.svg`" />
        </RouterLink>
        
    </div>
</template>

<script setup>

</script>

<style scoped>
.nav-bar {
  display: flex;
  justify-content: center;
  gap: 20px; /* 링크들 사이에 간격 */
  background-color: #d1d1d1; /* 배경색 */
  padding: 10px 0;
  border-radius: 10px; /* 모서리 둥글게 */
}

.nav-link {
  text-decoration: none;
  padding: 10px 20px;
  font-size: 16px;
  color: #333;
  background-color: #ffffff;
  border-radius: 5px;
  border: 1px solid #ccc; /* 테두리 색 */
  transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-link:hover {
  background-color: #9c9c9c; /* 호버 시 배경색 */
  color: #fff; /* 호버 시 텍스트 색상 */
}

.nav-bar a {
  text-decoration: none; /* 링크에 밑줄 제거 */
}
</style>