<template>
  <div class="nav">
    <RouterLink class="menu-bar" :to="{ name: 'menu' }">
      <img class="menu-img" :src="`/api/icons/menu-img.svg`" />
    </RouterLink>
    <RouterLink class="logo-bar" :to="{ name: 'home' }">
      <img class="feather-img" :src="`/api/icons/feather-img.svg`" />
    </RouterLink>
    <button class="mypage-bar" @click="openModal">
      <img class="smile-img" :src="`/api/icons/smile-img.svg`" />
    </button>
  </div>
  <div>
    <!-- 모달 -->
    <div v-if="isModal" class="modal-overlay" @click.self="closeModal">
      <div class="modal-content">
        <h3>{{ orderString }}</h3>
        <button @click="changeOrderInt">바꾸기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useGpsStore } from '@/stores/gpsStore.js'
import { ref } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
const gpsStore = useGpsStore();
const isModal = ref(false);
const orderString = ref()
const initOrderString = () => {
  if (gpsStore.boardSearchDto.orderInt == 1) {
    orderString.value = '거리순'
  } else if (gpsStore.boardSearchDto.orderInt == 2) {
    orderString.value = '최신순'
  }
}
initOrderString();
const changeOrderInt = () => {
  if (gpsStore.boardSearchDto.orderInt == 1) {
    gpsStore.boardSearchDto.orderInt = 2;
    orderString.value = '최신순'
    console.log(gpsStore.boardSearchDto.orderInt)
    return;
  } else if (gpsStore.boardSearchDto.orderInt == 2) {
    gpsStore.boardSearchDto.orderInt = 1;
    orderString.value = '거리순'
    console.log(1)
    return;
  }

}
const openModal = () => {
  isModal.value = true;
}
const closeModal = () => {
  isModal.value = false;
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* background-color: rgba(0, 0, 0, 0.5); */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  /* 항상 위에 보이도록 설정 */
}

/* 모달 내용 */
.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  text-align: center;
}

.nav-bar {
  display: flex;
  justify-content: center;
  gap: 20px;
  /* 링크들 사이에 간격 */
  background-color: #d1d1d1;
  /* 배경색 */
  padding: 10px 0;
  border-radius: 10px;
  /* 모서리 둥글게 */
}

.nav-link {
  text-decoration: none;
  padding: 10px 20px;
  font-size: 16px;
  color: #333;
  background-color: #ffffff;
  border-radius: 5px;
  border: 1px solid #ccc;
  /* 테두리 색 */
  transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-link:hover {
  background-color: #9c9c9c;
  /* 호버 시 배경색 */
  color: #fff;
  /* 호버 시 텍스트 색상 */
}

.nav-bar a {
  text-decoration: none;
  /* 링크에 밑줄 제거 */
}
</style>