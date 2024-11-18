<template>
  <div class="running">
    <div class="content-container">
      <div class="_01-35-01">
        운동시간
        <br />
        01:35:01
      </div>
      <img @click="startLocationInterval" class="play-circle" :src="`/api/icons/play-circle.svg`"
      v-if="!gpsStore.isRunning || gpsStore.isPause" />
      <img @click="pauseLocationInterval" class="pause-circle" :src="`/api/icons/pause-circle.svg`"
      v-if="gpsStore.isRunning && !gpsStore.isPause" />
    <img @click="stopLocationInterval" class="stop-circle" :src="`/api/icons/stop-circle.svg`"
      v-if="gpsStore.isRunning" />
    </div>
  </div>
</template>

<script setup>
import '@/assets/styles/running.css'
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

import { useGpsStore } from '@/stores/gpsStore.js';
const router = useRouter();
const gpsStore = useGpsStore();

const goRunningDataForm = function () {
  router.push({ name: 'runAfter' });
}

// location 관련 함수들
const startLocationInterval = () => {
  if (gpsStore.intervalId) {
    clearInterval(gpsStore.intervalId); // 기존 인터벌 정리
  }
  gpsStore.intervalId = setInterval(getLocation, 1000); // getlocation실행파트
  gpsStore.isRunning = true;
  gpsStore.isPause = false;
};
const stopLocationInterval = () => {
  clearInterval(gpsStore.intervalId);
  gpsStore.intervalId = null; // 인터벌 정리
  gpsStore.isRunning = false;
  gpsStore.isPause = false;
  gpsStore.determineInitialValue();
  console.log("스탑로케이션")
  goRunningDataForm();
}
const pauseLocationInterval = () => {
  if (gpsStore.intervalId) {
    clearInterval(gpsStore.intervalId);
    gpsStore.intervalId = null; // 인터벌 정리
    gpsStore.isPause = true;
  }
}







// 위치 따서 pinia에 임시로 저장해놓는 파트
const sendPositionToPinia = (position) => {
  const longitude = position.coords.longitude;
  const latitude = position.coords.latitude;
  console.log(longitude)
  console.log(latitude)

  const timestamp = new Date;
  const fommatingTimestamp = timestamp.toISOString().slice(0, 19)
  console.log(fommatingTimestamp)
  gpsStore.addLocation({
    longitude: longitude,
    latitude: latitude,
    createdTs: fommatingTimestamp,
  });
};









// gps로 위치 따오는 파트
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToPinia, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
  }
}
function showError(error) {
  switch (error.code) {
    case error.PERMISSION_DENIED:
      alert("User denied the request for Geolocation.");
      stopLocationInterval();
      break;
    case error.POSITION_UNAVAILABLE:
      alert("Location information is unavailable.");
      stopLocationInterval();
      break;
    case error.TIMEOUT:
      alert("The request to get user location timed out.");
      stopLocationInterval();
      break;
    case error.UNKNOWN_ERROR:
      alert("An unknown error occurred.");
      stopLocationInterval();
      break;
  }
}
</script>

<style scoped>
</style>