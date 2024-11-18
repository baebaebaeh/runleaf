<template>
  <div>
    <div>
      memberId : 
      <input type="text" v-model="boardDto.memberId" style="display: none">
    </div>
    <div>
      difficulty : 
      <input type="text" v-model="boardDto.difficulty">
    </div>
    <div>
      startRunningTs : 
      <input type="text" v-model="boardDto.startRunningTs" style="display: none">
    </div>
    <div>
      endRunningTs : 
      <input type="text" v-model="boardDto.endRunningTs" style="display: none">
    </div>
    <div>
      startLatitude : 
      <input type="text" v-model="boardDto.startLatitude" style="display: none">
    </div>

    <div>
      startLongitude : 
      <input type="text" v-model="boardDto.startLongitude" style="display: none">
    </div>
    <div>
      createdTs : 
      <input type="text" v-model="boardDto.createdTs" style="display: none">
    </div>
    <div>
      modifiedTs : 
      <input type="text" v-model="boardDto.modifiedTs" style="display: none">
    </div>
    <div>
      title : 
      <input type="text" v-model="boardDto.title">
    </div>
    <div>
      content : 
      <input type="text" v-model="boardDto.content">
    </div>
    <div>
      mainImagePath : 
      <input type="text" v-model="boardDto.mainImagePath" style="display: none">
    </div>
    <div>
      writer : 
      <input type="text" v-model="boardDto.writer" style="display: none">
    </div>
    <div>
      boolean :
      <input type="text" v-model="boardDto.onBoard" style="display: none">
    </div>
    <div>
      <input type="file" id="upload-image" @change="getFileName($event.target.files)" multiple hidden />
    </div>
    <div v-for="(preview, index) in previews" :key="index">
      <label for="upload-image">
          <img src="../../assets/images/abcd.PNG" :id="preview" />
      </label>
    </div>
    <button @click="uploadFile">등록</button>
  </div>
  <!-- 
  	
          <img
            v-if="mainImage(board)"
            :src="`http://localhost:8080/uploads/${mainImage(board).path}${
              mainImage(board).systemName
            }`"
            alt="Board Image"
          />
  
  -->
</template>



<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useRunningDataStore } from '@/stores/runningDataStore.js';
const router = useRouter();
const runningDataStore = useRunningDataStore();
const goRunningDataList = function() {
  router.push({ name: 'runningDataList'});
}
const previews = ref([
  'preview0',
  'preview1',
  'preview2',
  'preview3',
  'preview4',
])
const boardDto = ref({
      memberId: '1',
      difficulty: '1',
      startRunningTs: '',
      endRunningTs: '',
      startLatitude: '1',
      startLongitude: '1',
      createdTs: '',
      modifiedTs: '',
      title: '1',
      content: '1',
      mainImagePath: '1',
      writer: '1',
      onBoard: '',
})
let formData = new FormData();


const uploadFile = async () => {
  // formData.append(`board`, new Blob([JSON.stringify(boardDto.value)], { type: "application/json" }));
  formData.append("board", new Blob([JSON.stringify(boardDto.value)], { type: "application/json" })); // board 객체 추가
  formData.append("location", new Blob([JSON.stringify(runningDataStore.getLocations())], { type: "application/json" })); // board 객체 추가
  await axios.post("/api/running", formData);
  runningDataStore.postLocations();
  goRunningDataList();
};


const getFileName = async(files) => {
  formData = new FormData();
  for (let index = 0; index < previews.value.length; index++) {
    const file = files[index];
    if (file === undefined) {
      const previewImage = document.getElementById('preview' + index)
      previewImage.src = '../../assets/images/abcd.PNG';
    } else {
      formData.append(`file`, file);
      const fileName = files[index];
      await base64(fileName, index);
    }
    
  }
};

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
};

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
  } else {
    alert("Geolocation is not supported by this browser.");
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