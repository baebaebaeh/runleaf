<template>
  <div>
    <h1>Get User Location</h1>
    <button @click="getLocation()">Get Location</button>
    <a href="${pageContext.request.contextPath}/">main</a>
    <div>너의 위치</div>
    <div>경도 : {{ location.longitude }}</div>
    <div>경도 : {{ location.latitude }}</div>
  </div>
  <div>

    <div>
      memberId : <input type="text" v-model="insertBoardDto.memberId">
    </div>
    <div>
      runningDataId : <input type="text" v-model="insertBoardDto.runningDataId">
    </div>
    <div>
      content : <input type="text" v-model="insertBoardDto.content">
    </div>
    <div>
      mainImagePath : <input type="text" v-model="insertBoardDto.mainImagePath">
    </div>
    <div>
      startLongitude : <input type="text" v-model="insertBoardDto.startLongitude">
    </div>
    <div>
      startLatitude : <input type="text" v-model="insertBoardDto.startLatitude">
    </div>
    <div>
      <input type="file" id="upload-image" @change="getFileName($event.target.files)" multiple hidden />
    </div>
    <div v-for="(preview, index) in previews" :key="index">
      <label for="upload-image">
          <img src="../assets/images/abcd.PNG" :id="preview" />
      </label>
    </div>
    <button @click="uploadFile">등록</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useGpsStore } from '@/stores/runningDataStore';
const gpsStore = useGpsStore();
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
  }
}
const location = ref({
  longitude: 0,
  latitude: 0,
});
const sendPositionToPinia = (position) => {
  const longitude = position.coords.longitude;
  const latitude = position.coords.latitude;
  location.value.longitude = longitude;
  location.value.latitude = latitude;
  gpsStore.addLocation(location);
  location.value.longitude = 0;
  location.value.longitude = 0;
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
#preview0, #preview1,#preview2,
#preview3, #preview4,#preview5{
  width: 200px; /* 고정 가로 크기 */
  height: 200px; /* 고정 세로 크기 */
  object-fit: contain; /* 비율을 유지하면서 지정된 크기에 맞춤 */
}
</style>