<template>
  
  <TheHeaderNav />
  <div class="main-container">
    <div v-if="menuStore.isMenuVisible" class="side-bar-overlay" @click="menuStore.hideMenu"></div>
    <div class="side-bar" :class="{ 'side-bar-visible': menuStore.isMenuVisible }">
      <div class="member-container" v-if="authStore.isLoggedIn">
        <div class="profile-image">
          <img :src="authStore.profileImage || defaultProfileImage" alt="Profile Image" />
        </div>
        <div class="profile-info">
          <div class="username">{{ authStore.username }}</div>
          <div class="stats">
            <span>팔로잉: {{ authStore.following }}</span>
            <span>팔로워: {{ authStore.followers }}</span>
          </div>
        </div>
      </div>
      <div class="member-container" v-else>
        <p>로그인이 필요합니다.</p>
      </div>
      <div class="menu-container">
        <RouterLink class="menu-item" :to="{ name: 'home' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/icons/home-img.svg" />
          </div>
          <div class="menu-text">홈</div>
        </RouterLink>

        <div class="menu-item">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/icons/plus-circle.svg" />
          </div>
          <div class="menu-text">글쓰기</div>
        </div>

        <RouterLink class="menu-item" :to="{ name: 'running' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/icons/play.svg" />
          </div>
          <div class="menu-text">달리기 시작</div>
        </RouterLink>

        <RouterLink class="menu-item" :to="{ name: 'myrun' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/icons/myrun-img.svg" />
          </div>
          <div class="menu-text">내 달리기</div>
        </RouterLink>

        <div class="menu-item">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/icons/challenge-img.svg" />
          </div>
          <div class="menu-text">챌린지</div>
        </div>

        <!-- 로그인 후 -->
        <div v-if="authStore.isLoggedIn" class="menu-group">
          <RouterLink class="menu-item" :to="{ name: 'myInfo' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/icons/myinfo-img.svg" />
            </div>
            <div class="menu-text">내 정보</div>
          </RouterLink>

          <div class="menu-item" @click="logout">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/icons/logout.png" />
            </div>
            <div class="menu-text">로그아웃</div>
          </div>
        </div>

        <!-- 로그인 전 -->
        <div v-else class="menu-group">
          <RouterLink class="menu-item" :to="{ name: 'joinInfo' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/icons/join.png" />
            </div>
            <div class="menu-text">회원가입</div>
          </RouterLink>

          <RouterLink class="menu-item" :to="{ name: 'login' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/icons/login.png" />
            </div>
            <div class="menu-text">로그인</div>
          </RouterLink>
        </div>

      </div>
    </div>
    <div class="content" :class="{ 'menu-active': menuStore.isMenuVisible }">
      <RouterView />
    </div>
  </div>
</template>

<script setup>

import TheHeaderNav from './components/common/TheHeaderNav.vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from './stores/auth';
import { useMenuStore } from './stores/menu';

const router = useRouter();
const authStore = useAuthStore();
const menuStore = useMenuStore();

router.push('/'); // 다음 경로로 이동

const defaultProfileImage = '@/assets/images/icons/default-profile.png';

const logout = () => {
  authStore.logout();
};

router.afterEach(() => {
  menuStore.hideMenu();
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  text-decoration: none;
}

.side-bar {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 20px;
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 240px;
  transform: translateX(-100%);
  transition: transform 0.3s ease;
  z-index: 1000;
}

.side-bar-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  /* 어두운 반투명 배경 */
  z-index: 999;
  /* 메뉴 뒤, 콘텐츠 앞 */
}

.side-bar-visible {
  transform: translateX(0);
}

.member-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  border-bottom: dashed 1px  #cccccc;
}

.profile-image img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
}

.profile-info {
  margin-top: 10px;
  text-align: center;
}

.username {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.stats {
  margin-top: 5px;
  font-size: 14px;
  color: #666;
}

.stats span {
  display: block;
  margin-top: 2px;
}

.menu-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.menu-item {
  display: flex;
  align-items: center;
  padding: 11px;
  border-radius: 5px;
  cursor: pointer;
  transition: background 0.3s ease;
}

.menu-item:hover {
  background: #e0e0e0;
}

.menu-logo {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.menu-img {
  max-width: 100%;
  max-height: 100%;
}

.menu-text {
  font-size: 14px;
  font-family: "Inter-Regular", sans-serif;
  color: #000000;
  margin-left: 10px;
}

.menu-group {
  display: flex;
  flex-direction: column;
  gap: 10px;
  /* 그룹 내부 간격 */
  margin-bottom: 15px;
  /* 그룹 간의 간격 */
}
</style>
