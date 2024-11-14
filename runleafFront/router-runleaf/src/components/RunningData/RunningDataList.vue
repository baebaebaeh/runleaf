<template>
  <div class="content-container">
    <div v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId">
      <RouterLink :to="{
        name: 'runningDataDetail',
        params: {
          id: runningBoard.runningBoardId,
        }
      }">
        <div class="content">
          <!-- @click="runningBoardDetail(runningBoard.runningBoardId)" -->
          <div>title : {{ runningBoard.title }}</div>
          <img :src="`${runningBoard.mainImagePath.replace(/^.*\/src/, '/src')}`" id="preview" />
        </div>
      </RouterLink>
      <RouterView v-if="$route.params.id == runningBoard.runningBoardId" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted  } from 'vue';
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
  if (document.body.scrollHeight - window.innerHeight - document.documentElement.scrollTop < 500) {
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

<style scoped>
#preview {
  width: 300px;
  /* 고정 가로 크기 */
  height: 300px;
  /* 고정 세로 크기 */
  object-fit: contain;
  /* 비율을 유지하면서 지정된 크기에 맞춤 */
}

.scroll-container {
  height: 100%;
  /* 고정된 높이 설정 */
  overflow-y: auto;
  /* 세로 스크롤 활성화 */
}
</style>