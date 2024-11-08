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
      <input type="file" id="upload-image" hidden @change="getFileName($event.target.files)"/>
      <label for="upload-image">
        <img src="../assets/images/abcd.PNG" id="preview" />
      </label>
      <button @click="uploadFile">등록</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
const formData = new FormData();
const uploadFile = async () => {
  await axios.post("http://localhost:8080/board", formData);
};
const getFileName = async(files) => {
  const fileName = files[0]
  formData.append("file", fileName);
  console.log(formData)
  await base64(fileName)
}
const base64 = (file) => {
  // 비동기적으로 동작하기 위하여 promise를 return 해준다.
  const prom = new Promise(resolve => {
    console.log(resolve)
    // 업로드된 파일을 읽기 위한 FileReader() 객체 생성
    let a = new FileReader()
    // console.log(a)
    // 읽기 동작이 성공적으로 완료됐을 때 발생
    a.onload = e => {
      console.log(e)
      resolve(e.target.result)
			// 썸네일을 보여주고자 하는 <img>에 id값을 가져와 src에 결과값을 넣어준다.
      const previewImage = document.getElementById('preview')
      previewImage.src = e.target.result
    }
		// file 데이터를 base64로 인코딩한 문자열. 이 문자열을 브라우저가 인식하여 원래 데이터로 만들어준다.
    a.readAsDataURL(file)
  })
  // console.log(prom)
  return prom
}
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
  }
}

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