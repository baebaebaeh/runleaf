<template>
  <div class="run-after">
    <div class="div">달리기 저장파트</div>
    <div class="form-container">
      <div class="path-container">
        <div class="div">뛴 경로</div>
        <img class="image2" :src="`/api/icons/image.png`" />
        <div class="content">
          <input class="regist-input" type="text" placeholder="여기에 제목을 입력하세요" v-model="boardDto.title" />
          <input class="regist-input" type="text" placeholder="여기에 내용을 입력하세요" v-model="boardDto.content" />
          <input class="regist-input" type="text" placeholder="여기에 난이도를 입력하세요" v-model="boardDto.difficulty" />
          <input class="regist-input" type="text" placeholder="" v-model="boardDto.difficulty" />
        </div>
      </div>
      <div class="sub-image-container">
        <div class="div">사진을 선택해주세요</div>
        <div>
          <input type="file" id="upload-image" @change="getFileName($event.target.files)" multiple hidden />
        </div>
        <div class="flex" v-for="(preview, index) in previews" :key="index">
          <label class="flex" for="upload-image">
            <img class="image2" :src="`/api/icons/image.png`" :id="preview" />
          </label>
        </div>
        <img class="plus-square2" :src="`/api/icons/plus-square.svg`" />
      </div>
    </div>
    <div class="share-container">
      <div class="share">
        <img class="share3" :src="`/api/icons/share.svg`" />
        <button @click="uploadFile" class="div">공유하기</button>
      </div>
    </div>
  </div>

  <div>
    <div>
      memberId :
      <input type="text" v-model="boardDto.memberId">
    </div>
    <div>
      startRunningTs :
      <input type="text" v-model="boardDto.startRunningTs">
    </div>
    <div>
      endRunningTs :
      <input type="text" v-model="boardDto.endRunningTs">
    </div>
    <div>
      startLatitude :
      <input type="text" v-model="boardDto.startLatitude">
    </div>

    <div>
      startLongitude :
      <input type="text" v-model="boardDto.startLongitude">
    </div>
    <div>
      createdTs :
      <input type="text" v-model="boardDto.createdTs">
    </div>
    <div>
      modifiedTs :
      <input type="text" v-model="boardDto.modifiedTs">
    </div>
    <div>
      mainImagePath :
      <input type="text" v-model="boardDto.mainImagePath">
    </div>
    <div>
      writer :
      <input type="text" v-model="boardDto.writer">
    </div>
    <div>
      boolean :
      <input type="text" v-model="boardDto.onBoard" v-bind:style="{display: 'none'}">
    </div>
  </div>
</template>

<script setup>
import '@/assets/styles/runAfter.css'
import { ref, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useGpsStore } from '@/stores/gpsStore.js';
const router = useRouter();
const gpsStore = useGpsStore();
const goRunningDataList = function () {
  router.push({ name: 'myrun' });
}
const previews = ref([
  'preview0',
  'preview1',
  'preview2',
  'preview3',
  'preview4',
])
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
  writer: '1',
  onBoard: 0,
})

// watch(
//   [
//     () => gpsStore.startLatitude, () => gpsStore.startLongitude,
//     () => gpsStore.startTs, () => gpsStore.endTs,
//   ], // 여러 값 감시
//   (
//     [newLat, newLong, newStartTs, newEndTs], [oldLat, oldLong, oldStartTs, oldEndTs]
//   ) => {
//     startLatitude.value = newLat;
//     startLongitude.value = newLong;
//     startRunningTs.value = newStartTs;
//     endRunningTs.value = newEndTs;
//   }
// );
let formData = new FormData();


const uploadFile = async () => {
  // formData.append(`board`, new Blob([JSON.stringify(boardDto.value)], { type: "application/json" }));
  formData.append("board", new Blob([JSON.stringify(boardDto.value)], { type: "application/json" })); // board 객체 추가
  formData.append("location", new Blob([JSON.stringify(gpsStore.locations)], { type: "application/json" })); // board 객체 추가
  await axios.post("/api/running", formData);
  gpsStore.postLocations();
  goRunningDataList();
};


const getFileName = async (files) => {
  formData = new FormData();
  for (let index = 0; index < previews.value.length; index++) {
    const file = files[index];
    if (file === undefined) {
      const previewImage = document.getElementById('preview' + index)
      previewImage.src = '/api/icons/image.png';
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

<style scoped></style>