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
      <RouterLink :to="{ name: 'member' }">
        <div class="myinfo-bar">
          <div class="myinfo-logo">
            <img class="myinfo-img" :src="`/api/icons/myinfo-img.svg`" />
          </div>
          <div class="myinfo-text">
            <div class="myinfo-txt">내 정보</div>
          </div>
        </div>
      </RouterLink>
    </div>
  </div>

</template>

<script setup>
import '@/assets/styles/menu.css'
</script>

<style scoped>
</style>