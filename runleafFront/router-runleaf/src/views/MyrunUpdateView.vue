<template>
  <div class="run-after2">
    <div class="div">달리기 저장파트</div>
    <div class="form-container">
      <div class="content">
        <input class="regist-input" type="text" placeholder="여기에 제목을 입력하세요"
          v-model="runningDataStore.updateBoardDto.title" />
        <textarea class="regist-content-input" type="text" placeholder="여기에 내용을 입력하세요"
          v-model="runningDataStore.updateBoardDto.content"></textarea>
          <select class="difficulty-select" v-model="runningDataStore.updateBoardDto.difficulty">
            <option value="" disabled selected>난이도를 선택하세요</option>
            <option value="쉬움">쉬움</option>
            <option value="보통">보통</option>
            <option value="어려움">어려움</option>
          </select>
          <select class="difficulty-select" v-model="runningDataStore.updateBoardDto.onBoard">
            <option value="" disabled selected>공개여부를 선택하세요</option>
            <option :value="true">공개</option>
            <option :value="false">비공개</option>
          </select>
      </div>
      <div class="sub-image-container">
        <div class="div">사진을 선택해주세요</div>
        <div class="div">주의 : 사진을 선택하면 기존의 사진은 지워집니다</div>
        <div>
          <input type="file" id="upload-image" @change="getFileName($event.target.files)" multiple hidden />
        </div>
        <div v-if="!isNewFile" class="flex" v-for="(image, index) in runningDataStore.updateBoardImageDto" :key="index">
          <label class="flex" for="upload-image">
            <img class="image2" :src="`/api/uploads/${image.path}${image.systemName}`" />
          </label>
        </div>
        <div v-if="!isNewFile && runningDataStore.updateBoardImageDto.length == 0" class="flex">
          <label class="flex" for="upload-image">
            <img class="image2" :src="`/api/uploads/uploads/defaultimg/abcd.png`" />
          </label>
        </div>
        <div v-if="isNewFile" class="flex" v-for="(preview, index) in previews" :key="index">
          <label class="flex" for="upload-image">
            <img class="image2" src="" :id="preview" />
          </label>
        </div>
      </div>
    </div>
    <div class="share-container">
      <div class="share">
        <div class="share2">
          <img class="share3" src="`@/assets/images/icons/share.svg`" />
        </div>
        <div class="frame">
          <button @click="uploadFile" class="div">저장하기</button>
        </div>
      </div>
    </div>
  </div>

  <div>
    <!-- 
    <div>memberId : <input type="text" v-model="runningDataStore.updateBoardDto.memberId"></div>
    <div>runningBoardId : <input type="text" v-model="runningDataStore.updateBoardDto.runningBoardId"></div>
    <div>startRunningTs : <input type="text" v-model="runningDataStore.updateBoardDto.startRunningTs"></div>
    <div>endRunningTs :<input type="text" v-model="runningDataStore.updateBoardDto.endRunningTs"></div>
    <div>startLatitude :<input type="text" v-model="runningDataStore.updateBoardDto.startLatitude"></div>
    <div>startLongitude :<input type="text" v-model="runningDataStore.updateBoardDto.startLongitude"></div>
    <div>createdTs :<input type="text" v-model="runningDataStore.updateBoardDto.createdTs"></div>
    <div>modifiedTs :<input type="text" v-model="runningDataStore.updateBoardDto.modifiedTs"></div>
    <div>mainImagePath :<input type="text" v-model="runningDataStore.updateBoardDto.mainImagePath"></div>
    <div>writer :<input type="text" v-model="runningDataStore.updateBoardDto.writer"></div>
    <div>boolean :<input type="text" v-model="runningDataStore.updateBoardDto.onBoard"></div>
   
     -->
    <!-- v-bind:style="{ display: 'none' }" -->
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';
import { useGpsStore } from '@/stores/gpsStore.js';
import { useRunningDataStore } from '@/stores/runningDataStore';
import MapComponent from '@/views/NaverMapTESTView.vue'
// 사진등록하면 새로운 사진 보여주기 위한 변수
const isNewFile = ref(false);
const runningDataStore = useRunningDataStore();
const router = useRouter();
const route = useRoute();
const gpsStore = useGpsStore();
const id = Number(route.params.id);
const existingPhoto = ref(false);
const goRunningDataList = function () {
  router.push({ name: 'myrun' });
}
const previews = ref([])
const boardDto = ref({
  memberId: '',
  difficulty: '',
  startRunningTs: gpsStore.startTs,
  endRunningTs: gpsStore.endTs,
  startLatitude: gpsStore.startLatitude,
  startLongitude: gpsStore.startLongitude,
  createdTs: '',
  modifiedTs: '',
  title: '',
  content: '',
  mainImagePath: '1',
  writer: '',
  onBoard: false,
})
let formData = new FormData();


// 파일 업로드 파트
const uploadFile = async () => {
  // formData.append(`board`, new Blob([JSON.stringify(boardDto.value)], { type: "application/json" }));
  formData.append("board", new Blob([JSON.stringify(runningDataStore.updateBoardDto)], { type: "application/json" })); // board 객체 추가
  const token = sessionStorage.getItem('token');
  await axios.put(`/api/running/${id}`, formData, {
    headers: {
      'authorization': `Bearer ${token}`,
    }
  });
  gpsStore.postLocations();
  goRunningDataList();
};


const getFileName = async (files) => {
  if (files.length > 5) {
    alert("사진은 최대 5개 까지 등록 가능합니다");
    return;
  }
  if (files.length == 0) {
    return;
  }
  isNewFile.value = true;
  formData = new FormData();
  runningDataStore.updateBoardImageDto = [];
  previews.value = [];
  for (let index = 0; index < files.length; index++) {
    previews.value.push("preview" + index);
    const file = files[index];
    formData.append(`file`, file);
    const fileName = files[index];
    await base64(fileName, index);
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
.run-after,
.run-after * {
  box-sizing: border-box;
}

.run-after {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  min-height: 1000px;
  position: relative;
  overflow: hidden;
}

.run-after2 {
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

.form-container {
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

.path-container {
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

.image {
  background: var(--image-placeholder,
      linear-gradient(to left, #e3e3e3, #e3e3e3));
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 299px;
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

.regist-input {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 20px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}

.sub-image-container {
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

.flex {
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
      linear-gradient(to left, #ffffff, #ffffff));
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
  object-fit: contain;
}

.share-container {
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 370px;
  position: relative;
  overflow: hidden;
}

.share {
  background: var(--var-sds-color-background-default-default, #ffffff);
  border-radius: var(--var-sds-size-radius-200, 8px);
  border-style: solid;
  border-color: var(--var-sds-color-border-default-default, #d9d9d9);
  border-width: 1px;
  padding: 7px 7px 7px 20px;
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex: 1;
  min-width: 240px;
  position: relative;
}

.share2 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}

.share3 {
  flex-shrink: 0;
  width: 43px;
  height: 43px;
  position: relative;
  overflow: visible;
}

.frame {
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex: 1;
  position: relative;
  overflow: hidden;
}

.map {
  background: var(--image-placeholder,
      linear-gradient(to left, #e3e3e3, #e3e3e3));
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 299px;
  position: relative;
  overflow: hidden;
  /* height: 100%; */
}


.regist-input {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 20px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}

.regist-content-input {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 20px;
  height: 200px;
  flex-wrap: wrap;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}

.difficulty-select {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: white;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  appearance: none;
  /* 기본 드롭다운 화살표 제거 */
  background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20'%3E%3Cpath fill='gray' d='M0 0l10 10L20 0z'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
}

.difficulty-select:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}
</style>