<template>
  <div @scroll="handleScroll" class="scroll-container" >
    <div v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId">
      <div>difficulty : {{ runningBoard.difficulty }}</div>
      <div>title : {{ runningBoard.title }}</div>
      <div>content : {{ runningBoard.content }}</div>
      <div>writer : {{ runningBoard.writer }}</div>
      <img :src="`http://localhost:8080/uploads/${runningBoard.mainImagePath}`" id="preview" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
const boardDto = ref([])
const boardSearchDto = ref({
  page: 0,
})
function infinityScroll(e) {
  // console.dir(e);
  // console.log(1111, document.body.scrollHeight, window.innerHeight, document.documentElement.scrollTop);
  if (document.body.scrollHeight - window.innerHeight - document.documentElement.scrollTop < 500) {
    // console.log("스크롤 거의 다 내려왔음")
    getRunningBoardList();
  }
}
window.addEventListener('scroll', infinityScroll);


const getRunningBoardList = async () => {
  const { data } = await axios.get("/api/running", boardSearchDto.value)
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
  console.log(boardSearchDto.value.page);
}


getRunningBoardList();

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
  height: 100%; /* 고정된 높이 설정 */
  overflow-y: auto; /* 세로 스크롤 활성화 */
}
</style>