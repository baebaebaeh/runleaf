<template>
    <div>
      <nav class="nav-bar">
        <RouterLink :to="{ name: 'gps' }" class="nav-link">GPS</RouterLink>
        <RouterLink :to="{ name: 'board' }" class="nav-link">Board</RouterLink>
        <RouterLink :to="{ name: 'runningDataView' }" class="nav-link">Running Data</RouterLink>
        <RouterLink :to="{ name: 'joinInfo' }" class="nav-link">회원가입</RouterLink>
        <RouterLink :to="{ name: 'login' }" class="nav-link">로그인</RouterLink>
        <!-- <RouterLink :to="{ name: 'member', params: { id: userId } }" class="nav-link">내 정보</RouterLink> -->
      </nav>
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