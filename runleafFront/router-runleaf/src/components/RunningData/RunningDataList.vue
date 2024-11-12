<template>
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
          <img src="../../assets/images/abcd.PNG" :id="preview" />
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
const getRunningDataList = async () => {
  const { data } = await axios.get("/api/runningData")
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