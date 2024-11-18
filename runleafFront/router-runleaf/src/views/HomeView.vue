<template>
  <div class="home">
    <div class="feed-container">
      <div class="div">러닝 피드</div>
      <div class="feed">
        <div class="main-image">
          <div class="div">지도</div>
          <img class="image" :src="`/api/icons/image.png`" />
        </div>
        <div class="content">
          <div class="div2">제목</div>
          <div class="div2">내용</div>
          <div class="div2">누가 어디서 난이도 등등</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// import '@/assets/styles/home.css'
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










.home,
.home * {
  box-sizing: border-box;
}
.home {
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
.feed-container {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.div {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
}
.feed {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.main-image {
  background: var(--var-sds-color-background-default-default, #ffffff);
  border-radius: var(--var-sds-size-radius-200, 8px);
  border-style: solid;
  border-color: var(--var-sds-color-border-default-default, #d9d9d9);
  border-width: 1px;
  padding: var(--var-sds-size-space-400, 16px);
  display: flex;
  flex-direction: column;
  gap: var(--var-sds-size-space-400, 16px);
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  height: 491px;
  min-width: 240px;
  position: relative;
}
.image {
  background: var(
    --image-placeholder,
    linear-gradient(to left, #ffffff, #ffffff)
  );
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex: 1;
  position: relative;
  overflow: hidden;
  object-fit: contain;
}
.content {
  border-radius: 8px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.div2 {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}
.content2 {
  border-radius: 8px;
  border-style: solid;
  border-color: #d9d9d9;
  border-width: 1px;
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.div3 {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 24px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  flex: 1;
}


</style>