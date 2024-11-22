<template>
  <div class="nav">
    <button @click="toggleMenu" class="menu-bar">
      <img class="menu-img" src="`@/assets/images/icons/menu-img.svg`" />
    </button>
    <RouterLink class="logo-bar" :to="{ name: 'home' }">
      <img class="feather-img" src="`@/assets/images/icons/feather-img.svg`" />
    </RouterLink>
    <button class="mypage-bar" @click="openModal">
      <img class="smile-img" src="`@/assets/images/icons/smile-img.svg`" />
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
import { useMenuStore } from '@/stores/menu';
import { ref } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();

const toggleMenu = () => {
  menuStore.toggleMenu();  // 메뉴 보이기/숨기기 토글
};

const menuStore = useMenuStore(); // 메뉴 store 사용
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
/* 기본 설정 */
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: none;
  text-decoration: none;
  background: none;
  -webkit-font-smoothing: antialiased;
}

/* 네비게이션 바 */
.nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 30px;
}

.menu-bar,
.logo-bar,
.mypage-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 50px;
  cursor: pointer;
}

.menu-img,
.feather-img,
.smile-img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

/* 모달 오버레이 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

/* 모달 내용 */
.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  text-align: center;
}

/* 링크 스타일 */
.nav-bar {
  display: flex;
  justify-content: center;
  gap: 20px;
  background-color: #d1d1d1;
  padding: 10px 0;
  border-radius: 10px;
}

.nav-link {
  text-decoration: none;
  padding: 10px 20px;
  font-size: 16px;
  color: #333;
  background-color: #ffffff;
  border-radius: 5px;
  border: 1px solid #ccc;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-link:hover {
  background-color: #9c9c9c;
  color: #fff;
}

.nav-bar a {
  text-decoration: none;
}
</style>