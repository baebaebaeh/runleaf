<template>
  <TheHeaderNav />
  <div class="main-container">
    <div v-if="menuStore.isMenuVisible" class="side-bar-overlay" @click="menuStore.hideMenu"></div>
    <div class="side-bar" :class="{ 'side-bar-visible': menuStore.isMenuVisible }">
      <div class="member-container" v-if="authStore.isLoggedIn">
        <div class="profile-image">
          <img :src="`/api/uploads/${authStore.loginProfileImage}`" alt="Profile Image" />
        </div>
        <div class="profile-info">
          <div class="username">{{ authStore.loginUsername }}</div>
          <div class="stats">
            <RouterLink class="follow" :to="{ name: 'follower' }">
              팔로워 {{ authStore.myFollowStats.followerCount }}
            </RouterLink>
            <RouterLink class="follow" :to="{ name: 'following' }">
              팔로잉 {{ authStore.myFollowStats.followingCount }}
            </RouterLink>
          </div>
        </div>
      </div>
      <div class="member-container" v-else>
        <p><RouterLink :to="{ name : 'login' }" class="need-login">로그인</RouterLink>이 필요합니다</p>
      </div>
      <div class="menu-container">
        <RouterLink class="menu-item" :to="{ name: 'home' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/sidebar/home.png" />
          </div>
          <div class="menu-text">홈</div>
        </RouterLink>
        
        <RouterLink class="menu-item" :to="{ name: 'running' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/sidebar/running.png" />
          </div>
          <div class="menu-text">달리기 시작</div>
        </RouterLink>
<!-- 
        <RouterLink class="menu-item" :to="{ name: 'myrun' }">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/sidebar/myrun.png" />
          </div>
          <div class="menu-text">나의 달리기</div>
        </RouterLink>
         -->
<!--         
        <div class="menu-item">
          <div class="menu-logo">
            <img class="menu-img" src="@/assets/images/sidebar/challenge.png" />
          </div>
          <div class="menu-text">챌린지</div>
        </div>
         -->
        <!-- 로그인 후 -->
        <div v-if="authStore.isLoggedIn" class="menu-group">
          <RouterLink class="menu-item" :to="{ name: 'myInfo' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/sidebar/myinfo.png" />
            </div>
            <div class="menu-text">내 정보</div>
          </RouterLink>

          <div class="menu-item" @click="logout">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/sidebar/logout.png" />
            </div>
            <div class="menu-text">로그아웃</div>
          </div>
        </div>

        <!-- 로그인 전 -->
        <div v-else class="menu-group">
          <RouterLink class="menu-item" :to="{ name: 'joinInfo' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/sidebar/join.png" />
            </div>
            <div class="menu-text">회원가입</div>
          </RouterLink>

          <RouterLink class="menu-item" :to="{ name: 'login' }">
            <div class="menu-logo">
              <img class="menu-img" src="@/assets/images/sidebar/login.png" />
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
import { useFollowStore } from './stores/follow';
import { onMounted } from 'vue';

const router = useRouter();
const authStore = useAuthStore();
const menuStore = useMenuStore();
const followStore = useFollowStore();

const logout = () => {
  authStore.logout();
};

router.afterEach(() => {
  menuStore.hideMenu();
});

onMounted(async () => {
  if (authStore.isLoggedIn) {
    await followStore.getFollowStats(authStore.loginUsername);
  }
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
  justify-content: flex-start;
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
  padding: 40px;
  border-bottom: dashed 1px  #cccccc;
}

.need-login {
  color: #adadad;
  font-weight: bolder;
  cursor: pointer;
}

.need-login:hover {
  color: #686868;
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
  display: flex;
  gap: 20px;
  margin-top: 5px;
  font-size: 14px;
  color: #666;
}

.follow {
  margin-top: 2px;
  color: #666;
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
  background: #d3d3d3;
}

.menu-logo {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.menu-img {
  max-width: 80%;
  max-height: 80%;
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
