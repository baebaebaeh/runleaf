<template>
  <div class="feed-container">
    <div class="div">피드 디테일</div>
    <div class="feed">
      <div class="map-container">
        <div class="div">지도</div>
        <img class="image" :src="`/api/icons/image.png`" />
      </div>
      <div class="image-container">
        <div class="div">사진들</div>
        <img v-for="(image, index) in boardDetailImage" :key="image.runningBoardImageId" class="image"
          :src="`/api/uploads/${image.path}${image.systemName}`" />
        <div class="content">
          <div class="div2">제목 : {{ boardDetail.title }}</div>
          <div class="div2">내용 : {{ boardDetail.content }}</div>
          <div class="div2">난이도 : {{ boardDetail.difficulty }}</div>
          <div class="div2">startRunningTs : {{ boardDetail.startRunningTs }}</div>
          <div class="div2">endRunningTs : {{ boardDetail.endRunningTs }}</div>
          <div class="div2">startLatitude : {{ boardDetail.startLatitude }}</div>
          <div class="div2">startLongitude : {{ boardDetail.startLongitude }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div>
          <div class="div2">modifiedTs : {{ boardDetail.modifiedTs }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div>
          <div class="div2">onBoard : {{ boardDetail.onBoard }}</div>
          <div class="div2" v-for="(c, index) in coodinate" :key="c.runningCoodinateId">
            <div>latitude: {{ c.latitude }}</div>
            <div>longitude: {{ c.longitude }}</div>
            <div>createdTs: {{ c.createdTs }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>



</template>

<script setup>
import '@/assets/styles/myrunDetail.css'
import { useRunningDataStore } from '@/stores/runningDataStore';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { ref } from 'vue';
const boardDetail = ref({});
const boardDetailImage = ref([]);
const coodinate = ref([]);
const route = useRoute();
const runningDataStore = useRunningDataStore();
const id = Number(route.params.id);
const findDetailById = async () => {
  const { data } = await axios.get(`/api/running/board/${id}`);
  boardDetail.value = data;
}
const findDetailImageById = async () => {
  const { data } = await axios.get(`/api/running/image/${id}`);
  boardDetailImage.value = data;

}
const findDetailCoodinateById = async () => {
  const { data } = await axios.get(`/api/running/coodinate/${id}`);
  console.log(data)
  coodinate.value = data;

}
findDetailCoodinateById();
findDetailImageById();
findDetailById();
</script>

<style scoped>
</style>