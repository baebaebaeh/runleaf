<template>
  <div class="menu">
    <div class="menu-container">
      <RouterLink class="home-bar" :to="{ name: 'home' }">
        <div class="home-logo">
          <img class="home-img" :src="`/api/icons/home-img.svg`" />
        </div>
        <div class="home-text">
          <div class="home-txt">홈</div>
        </div>
      </RouterLink>
      <div class="write-bar">
        <div class="write-logo">
          <img class="plus-circle" :src="`/api/icons/plus-circle.svg`" />
        </div>
        <div class="write-text">
          <div class="home-txt">글쓰기</div>
        </div>
      </div>
      <RouterLink class="run-bar" :to="{ name: 'running' }">
        <div class="run-logo">
          <img class="play" :src="`/api/icons/play.svg`" />
        </div>
        <div class="run-text">
          <div class="home-txt">달리기 시작</div>
        </div>
      </RouterLink>
      <RouterLink class="myrun-bar" :to="{ name: 'myrun' }">
        <div class="myrun-logo">
          <img class="myrun-img" :src="`/api/icons/myrun-img.svg`" />
        </div>
        <div class="myrun-text">
          <div class="myrun-txt">내 달리기</div>
        </div>
      </RouterLink>
      <div class="challenge-bar">
        <div class="challenge-logo">
          <img class="challenge-img" :src="`/api/icons/challenge-img.svg`" />
        </div>
        <div class="challenge-text">
          <div class="challenge-txt">챌린지</div>
        </div>
      </div>
      <div class="mychallenge-bar">
        <div class="mychallenge-logo">
          <img class="challenge-img2" :src="`/api/icons/challenge-img.svg`" />
        </div>
        <div class="mychallenge-text">
          <div class="challenge-txt">내 챌린지</div>
        </div>
      </div>
      <div class="myinfo-bar">
        <div class="myinfo-logo">
          <img class="myinfo-img" :src="`/api/icons/myinfo-img.svg`" />
        </div>
        <div class="myinfo-text">
          <div class="myinfo-txt">내 정보</div>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
// import '@/assets/styles/menu.css'
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: none;
  text-decoration: none;
  background: none;

  -webkit-font-smoothing: antialiased;
}












.menu,
.menu * {
  box-sizing: border-box;
}
.menu {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  min-height: 1000px;
  position: relative;
  overflow: hidden;
}
.nav {
  padding: 30px 0px 0px 0px;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: flex-end;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 126px;
  position: relative;
  overflow: hidden;
}
.menu-bar {
  padding: 11px 0px 11px 23px;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 108px;
  height: 77px;
  position: relative;
  overflow: hidden;
}
.menu-img {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
.logo-bar {
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: center;
  flex: 1;
  height: 77px;
  position: relative;
  overflow: hidden;
}
.feather-img {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
.mypage-bar {
  padding: 11px 23px 11px 0px;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-end;
  flex-shrink: 0;
  width: 108px;
  height: 77px;
  position: relative;
  overflow: hidden;
}
.smile-img {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
.menu-container {
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.home-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.home-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.home-img {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.home-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.home-txt {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  font-weight: 400;
  position: relative;
  flex: 1;
}
.write-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.write-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.plus-circle {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.write-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.run-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.run-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.play {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.run-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myrun-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.myrun-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myrun-img {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.myrun-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myrun-txt {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  font-weight: 400;
  position: relative;
  flex: 1;
}
.challenge-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.challenge-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.challenge-img {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.challenge-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.challenge-txt {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  font-weight: 400;
  position: relative;
  flex: 1;
}
.mychallenge-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.mychallenge-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.challenge-img2 {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.mychallenge-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myinfo-bar {
  background: #ffffff;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 102px;
  position: relative;
}
.myinfo-logo {
  padding: 20px 19px 20px 19px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 100px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myinfo-img {
  align-self: stretch;
  flex: 1;
  height: auto;
  position: relative;
  overflow: visible;
}
.myinfo-text {
  padding: 9px 15px 9px 15px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 302px;
  height: 102px;
  position: relative;
  overflow: hidden;
}
.myinfo-txt {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  font-weight: 400;
  position: relative;
  flex: 1;
}

</style>