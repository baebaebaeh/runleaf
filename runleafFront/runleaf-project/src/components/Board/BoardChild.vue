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


const previews = ref([
  'preview0',
  'preview1',
  'preview2',
  'preview3',
  'preview4',
])
const insertBoardDto = ref({
  memberId: '',
  runningDataId: '',
  content: '',
  mainImagePath: '',
  startLongitude: '',
  startLatitude: '',
})


const uploadFile = async () => {
  await axios.post("http://localhost:8080/api/boardImg", formData);
  console.log(insertBoardDto.value)
  await axios.post("http://localhost:8080/api/board", insertBoardDto.value);
};
let formData = new FormData();
const getFileName = async(files) => {
  formData = new FormData();
  for (let index = 0; index < previews.value.length; index++) {
    const file = files[index];
    if (file === undefined) {
      const previewImage = document.getElementById('preview' + index)
      previewImage.src = 'src/assets/images/abcd.PNG';
    } else {
      formData.append(`file`, file);
      const fileName = files[index];
      await base64(fileName, index);
    }
    
  }
}
const base64 = (file, index) => {
  // 비동기적으로 동작하기 위하여 promise를 return 해준다.
  const prom = new Promise(resolve => {
    // 업로드된 파일을 읽기 위한 FileReader() 객체 생성
    let a = new FileReader()
    // console.log(a)
    // 읽기 동작이 성공적으로 완료됐을 때 발생
    a.onload = e => {
      resolve(e.target.result)
			// 썸네일을 보여주고자 하는 <img>에 id값을 가져와 src에 결과값을 넣어준다.
      const previewImage = document.getElementById('preview' + index)
      previewImage.src = e.target.result
    }
		// file 데이터를 base64로 인코딩한 문자열. 이 문자열을 브라우저가 인식하여 원래 데이터로 만들어준다.
    a.readAsDataURL(file)
  })
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

<style scoped>
#preview0, #preview1,#preview2,
#preview3, #preview4,#preview5{
  width: 200px; /* 고정 가로 크기 */
  height: 200px; /* 고정 세로 크기 */
  object-fit: contain; /* 비율을 유지하면서 지정된 크기에 맞춤 */
}
</style>