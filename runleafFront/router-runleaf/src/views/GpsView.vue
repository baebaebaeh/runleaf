<template>
  <div>
    <h1>Get User Location</h1>
    <div>
      <button @click="startLocationInterval">Get Location</button>
    </div>
    <div>
      <button @click="stopLocationInterval">stop Location</button>
    </div>
  </div>

</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useRunningDataStore } from '@/stores/runningDataStore.js';
const router = useRouter();
const runningDataStore = useRunningDataStore();
const intervalId = ref(null);

const goRunningDataForm = function() {
  router.push({ name: 'runningDataForm'});
}

// location 관련 함수들
const startLocationInterval = () => {
  if (intervalId.value) {
    clearInterval(intervalId.value); // 기존 인터벌 정리
  }
  intervalId.value = setInterval(getLocation, 1000); // getlocation실행파트
};
const stopLocationInterval = () => {
  if (intervalId.value) {
    clearInterval(intervalId.value);
    intervalId.value = null; // 인터벌 정리
    goRunningDataForm();
  }
}

// 모아놓은 위치 보내기
// const sendLocationsToServer = () => {
//   runningDataStore.postLocations();
// }


// gps로 위치 따오는 파트
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToPinia, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
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
  runningDataStore.addLocation({
    longitude: longitude,
    latitude: latitude,
    createdTs: fommatingTimestamp,
  });
};








function showError(error) {
  switch (error.code) {
    case error.PERMISSION_DENIED:
      alert("User denied the request for Geolocation.");
      break;
    case error.POSITION_UNAVAILABLE:
      alert("Location information is unavailable.");
      break;
    case error.TIMEOUT:
      alert("The request to get user location timed out.");
      break;
    case error.UNKNOWN_ERROR:
      alert("An unknown error occurred.");
      break;
  }
}
</script>

<style scoped>
#preview0,
#preview1,
#preview2,
#preview3,
#preview4,
#preview5 {
  width: 200px;
  /* 고정 가로 크기 */
  height: 200px;
  /* 고정 세로 크기 */
  object-fit: contain;
  /* 비율을 유지하면서 지정된 크기에 맞춤 */
}
</style>