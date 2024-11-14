<template>
  <div class="content-container">
    <div v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId">
      <RouterLink>
        <div class="content" @click="runningBoardDetail(runningBoard.runningBoardId)">
          <div>difficulty : {{ runningBoard.difficulty }}</div>
          <div>title : {{ runningBoard.title }}</div>
          <div>content : {{ runningBoard.content }}</div>
          <div>writer : {{ runningBoard.writer }}</div>
          <img :src="`http://localhost:8080/uploads/${runningBoard.mainImagePath}`" id="preview" />
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { onBeforeRouteLeave } from 'vue-router'
import axios from 'axios';
let isFetching = false;
let hasMoreData = true;
const boardDto = ref([])
const boardSearchDto = ref({
  page: 1,
})

function infinityScroll(e) {
  // console.dir(e);
  // console.log(1111, document.body.scrollHeight, window.innerHeight, document.documentElement.scrollTop);
  if (document.body.scrollHeight - window.innerHeight - document.documentElement.scrollTop < 500) {
    if (!isFetching && hasMoreData) {
      getRunningBoardList();
    }
  }
}


const runningBoardDetail = async (id) => {
  console.log(id);
  const { data } = await axios.get(`/api/running/${id}`);

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
getRunningBoardList();

onBeforeRouteLeave((to, from, next) => {
  window.removeEventListener('scroll', infinityScroll); // 이벤트 리스너 해제
  next(); // 이동을 계속함
});

</script>

<style scoped>
#preview {
  width: 200px;
  /* 고정 가로 크기 */
  height: 200px;
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