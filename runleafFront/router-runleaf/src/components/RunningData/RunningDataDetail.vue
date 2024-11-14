<template>
  <div>
    <h2>
      <div>title : {{ boardDetail.title }}</div>
    </h2>
     <h3>
      <div>content : {{ boardDetail.content }}</div>
    </h3>
    <div>difficulty : {{ boardDetail.difficulty }}</div>
    <div>startRunningTs : {{ boardDetail.startRunningTs }}</div>
    <div>endRunningTs : {{ boardDetail.endRunningTs }}</div>
    <div>startLatitude : {{ boardDetail.startLatitude }}</div>
    <div>startLongitude : {{ boardDetail.startLongitude }}</div>
    <div>createdTs : {{ boardDetail.createdTs }}</div>
    <div>modifiedTs : {{ boardDetail.modifiedTs }}</div>
    <div>onBoard : {{ boardDetail.onBoard }}</div>
    <div v-for="(image, index) in boardDetailImage" :key="boardDetailImage.runningBoardImageId">
      <img :src="`http://localhost:8080/uploads/${image.path}${image.systemName}`" id="preview" />
    </div>
    <div>-------------------------------</div>
  </div>
</template>

<script setup>
import { useRunningDataStore } from '@/stores/runningDataStore';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { ref } from 'vue';
const boardDetail = ref({});
const boardDetailImage = ref([]);
const route = useRoute();
const runningDataStore = useRunningDataStore();
const id = Number(route.params.id);
const findDetailById = async() => {
  const { data } = await axios.get(`/api/running/board/${id}`);
  boardDetail.value = data;
}
const findDetailImageById = async() => {
  const { data } = await axios.get(`/api/running/image/${id}`);
  boardDetailImage.value = data;
  
}
findDetailImageById();
findDetailById();
</script>

<style scoped>
#preview {
  width: 200px;
  /* 고정 가로 크기 */
  height: 200px;
  /* 고정 세로 크기 */
  object-fit: contain;
  /* 비율을 유지하면서 지정된 크기에 맞춤 */
}</style>