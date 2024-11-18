<template>
  <div class="menu">
    <div class="feed-container">
      <div class="div">나의 운동 기록</div>
      <RouterLink class="feed" v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId" :to="{
        name: 'myrunDetail',
        params: {
          id: runningBoard.runningBoardId,
        }
      }">
        <div class="main-image">
          <div class="div">제목 : {{ runningBoard.title }}</div>
          <img class="image" :src="`/api/uploads/${runningBoard.mainImagePath}`" />
          <div class="content">
            <div class="div2">내용 : {{ runningBoard.content }}</div>
            <div class="div2">난이도 : {{ runningBoard.difficulty }}</div>
          </div>
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { onBeforeRouteLeave } from 'vue-router'
import { useRunningDataStore } from '@/stores/runningDataStore.js'
import axios from 'axios';
const boardDto = ref([])
const runningDataStore = useRunningDataStore();
let isFetching = false;
let hasMoreData = true;
const boardSearchDto = ref({
  page: 1,
});

function infinityScroll(e) {
  // console.dir(e);
  // console.log(1111, document.body.scrollHeight, window.innerHeight, document.documentElement.scrollTop);
  if (document.body.scrollHeight - window.innerHeight - document.documentElement.scrollTop < 100) {
    if (!isFetching && hasMoreData) {
      getRunningBoardList();
    }
  }
}

const getRunningBoardList = async () => {
  isFetching = true;
  try {
    const { data } = await axios.get("/api/running", {
      params: boardSearchDto.value,
    });
    if (data.length == 0) {
      hasMoreData = false;
    } else {
      data.forEach((runningBoard) => {
        console.log(2);
        boardDto.value.push({
          runningBoardId: runningBoard.runningBoardId,
          memberId: runningBoard.memberId,
          difficulty: runningBoard.difficulty,
          startRunningTs: runningBoard.startRunningTs,
          endRunningTs: runningBoard.endRunningTs,
          startLatitude: runningBoard.startLatitude,
          startLongitude: runningBoard.startLongitude,
          createdTs: runningBoard.createdTs,
          modifiedTs: runningBoard.modifiedTs,
          title: runningBoard.title,
          content: runningBoard.content,
          mainImagePath: runningBoard.mainImagePath,
          writer: runningBoard.writer,
          onBoard: runningBoard.onBoard,
          runningBoardImage: runningBoard.runningBoardImage
        })
      });
      boardSearchDto.value.page += 1;
    }
  } catch (error) {
    console.log(boardSearchDto.value.page);
  } finally {
    isFetching = false;
  }
}

// onmount
window.addEventListener('scroll', infinityScroll);
// onMounted(() => {
//   if(!runningDataStore.boardDto.value) {
//     getRunningBoardList();
//   }
// });
onMounted(() => {
  getRunningBoardList();
});

onBeforeRouteLeave((to, from, next) => {
  window.removeEventListener('scroll', infinityScroll); // 이벤트 리스너 해제
  next(); // 이동을 계속함
});

</script>

<style scoped>
.myrun,
.myrun * {
  box-sizing: border-box;
}
.myrun {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  position: relative;
  overflow: hidden;
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
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 370px;
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
  flex-shrink: 0;
  height: 299px;
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




</style>