<template>
  <div>
    <h1>Get User Location</h1>
    <button @click="getLocation()">Get Location</button>
    <a href="${pageContext.request.contextPath}/">main</a>
    <div>너의 위치</div>
    <div>경도 : {{ location.longitude }}</div>
    <div>경도 : {{ location.latitude }}</div>
  </div>
</template>

<script setup>

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
  }
}
import { ref } from 'vue';
import axios from 'axios';
const locations = ref([])
const location = ref({
  longitude: 0,
  latitude: 0,
});
const sendPositionToServer = async (position) => {
  const longitude = position.coords.longitude;
  const latitude = position.coords.latitude;
  location.value.longitude = longitude;
  location.value.latitude = latitude;
  await axios.post("https://www.runleaf.kro.kr:8080/gps", location.value);
  console.log(longitude);
  console.log(latitude);
  locations.value.push(location);
};

// function sendPositionToServer(position) {
//   const latitude = position.coords.latitude;
//   const longitude = position.coords.longitude;


//   fetch('/location', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json'
//     },
//     body: JSON.stringify({
//       latitude: latitude,
//       longitude: longitude
//     })
//   }).then(response => {
//     if (response.ok) {
//       return response.json();
//     }
//   }).then(data => {
//     console.log('Server Response:', data);
//   }).catch(error => {
//     console.error('Error:', error);
//   });
// }

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

<style scoped></style>