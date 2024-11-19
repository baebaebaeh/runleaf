<template>
  <div class="feed-container">
    <div class="div">피드 디테일</div>
    <div class="feed">
      <div class="map-container">
        <div class="div">지도</div>
        <img class="image" src="`@/assets/images/icons/image.png`" />
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
        <div class="upordel">
          <img class="x-square" src="@/assets/images/icons/delete-board.PNG" />
          <img class="plus-square" src="@/assets/images/icons/edit-board.png" />
        </div>
      </div>
    </div>
  </div>



</template>

<script setup>
import { useRoute } from 'vue-router';
import axios from 'axios';
import { ref } from 'vue';
const boardDetail = ref({});
const boardDetailImage = ref([]);
const coodinate = ref([]);
const route = useRoute();
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
.content-detail,
.content-detail * {
  box-sizing: border-box;
}

.content-detail {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  min-height: 1000px;
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
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 370px;
  position: relative;
  overflow: hidden;
}

.map-container {
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
  height: 351px;
  min-width: 240px;
  position: relative;
}

.image {
  background: var(--image-placeholder,
      linear-gradient(to left, #e3e3e3, #e3e3e3));
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex: 1;
  position: relative;
  overflow: hidden;
  object-fit: cover;
}

.image-container {
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

.image2 {
  background: var(--image-placeholder,
      linear-gradient(to left, #e3e3e3, #e3e3e3));
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 283px;
  position: relative;
  overflow: hidden;
  object-fit: cover;
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
  font-size: 15px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}
.upordel {
  padding: 5px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.x-square {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
.plus-square {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
</style>