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
import '@/assets/styles/myrun.css'
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
      alert('최대페이지 입니다')
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

<style scoped></style>