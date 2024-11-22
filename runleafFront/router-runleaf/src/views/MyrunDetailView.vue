<template>


  <div class="feed-container">
    <div class="div">피드 디테일</div>
    <div class="feed">

      <!-- 네이버 지도 파트 -->
      <div class="map-container">
        <div class="div">지도</div>
        <ChildComponent v-if="isMapMount" class="image" :coodinate="coodinate" />
        <!-- <img class="image" src="`@/assets/images/icons/image.png`" /> -->
      </div>

      <!-- 이미지 관련 파트 -->
      <div class="image-container">
        <div class="div">사진들</div>
        <!-- 이미지 있을때 캐러셀 표시 -->
        <div v-if="boardDetailImage.length != 0" class="carousel" @touchstart="startTouch" @touchmove="moveTouch"
          @touchend="endTouch" @mousedown="startDrag" @mousemove="moveDrag" @mouseup="endDrag" @mouseleave="endDrag">
          <div class="carousel-track" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
            <div class="carousel-item" v-for="(image, index) in boardDetailImage" :key="index">
              <img :src="`/api/uploads/${image.path}${image.systemName}`" alt="carousel image" />
            </div>
          </div>
          <!-- 이미지 개수 표시 -->
          <div>
            {{ currentIndex + 1 + '/' + boardDetailImage.length }}
          </div>
        </div>

        <!-- 이미지 없을때 기본이미지 표시 -->
        <img v-if="boardDetailImage.length == 0" class="image" :src="`/api/uploads/uploads/defaultimg/abcd.png`" />

        <!-- 보드의 디테일들 -->

        <div class="content">
          <div class="div2">제목 : {{ boardDetail.title }}</div>
          <div class="div2">내용 : {{ boardDetail.content }}</div>
          <div class="div2">난이도 : {{ boardDetail.difficulty }}</div>
          <div class="div2">onBoard : {{ boardDetail.onBoard }}</div>
          <div class="div2">startRunningTs : {{ boardDetail.startRunningTs }}</div>
          <div class="div2">endRunningTs : {{ boardDetail.endRunningTs }}</div>
          <div class="div2">startLatitude : {{ boardDetail.startLatitude }}</div>
          <div class="div2">startLongitude : {{ boardDetail.startLongitude }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div>
          <div class="div2">modifiedTs : {{ boardDetail.modifiedTs }}</div>
          <div class="div2">writer : {{ boardDetail.writer }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div>
          
          <div class="div2" v-for="(c, index) in coodinate" :key="c.runningCoodinateId">
            <div>latitude: {{ c.latitude }}</div>
            <div>longitude: {{ c.longitude }}</div>
            <div>createdTs: {{ c.createdTs }}</div>
          </div>
         
        </div>



        <!-- 보드 업데이트, 삭제 -->
        <div class="upordel">
          <RouterLink @click="tempSaveBoard()" :to="{
            name: 'myrunUpdate',
            params: {
              id: id,
            }
          }">
            <img class="edit-board" src="@/assets/images/icons/edit-board.png" />
          </RouterLink>
          <button @click="deleteBoard()">
            <img class="delete-board" src="@/assets/images/icons/delete-board.png" />
          </button>
        </div>

      </div>
    </div>
  </div>



</template>

<script setup>
// ======================= 임포트 =======================
import ChildComponent from "./NaverMapTESTView.vue"
import { RouterLink, useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import { ref, watch } from 'vue';
import { useRunningDataStore } from '@/stores/runningDataStore';
// ======================= 임포트 끝 =======================

// ======================= 변수 선언 =======================
// vue 관련 변수
const route = useRoute();
const router = useRouter();
const runningDataStore = useRunningDataStore();
// 맵 관련 변수
const isMapMount = ref(false);
// 보드관련 변수
const boardDetail = ref({});
const boardDetailImage = ref([]);
const coodinate = ref([]);
// 보드 번호
const id = Number(route.params.id);
// 현재 이미지 번호
const currentIndex = ref(0);
// 슬라이드 감지 관련 변수
const startX = ref(0); // 터치 또는 드래그 시작 위치
const deltaX = ref(0); // 이동 거리
const isDragging = ref(false); // 드래그 상태
// ======================= 변수 선언 끝 =======================



// ======================= 케러셀 관련 로직들 =======================


// 터치 시작
const startTouch = (event) => {
  startX.value = event.touches[0].clientX;
  deltaX.value = 0;
};

// 터치 이동
const moveTouch = (event) => {
  deltaX.value = event.touches[0].clientX - startX.value;
};

// 터치 종료
const endTouch = () => {
  handleSlide();
};

// 드래그 시작 (마우스)
const startDrag = (event) => {
  isDragging.value = true;
  startX.value = event.clientX;
  deltaX.value = 0;
};

// 드래그 이동 (마우스)
const moveDrag = (event) => {
  if (!isDragging.value) return;
  deltaX.value = event.clientX - startX.value;
};

// 드래그 종료 (마우스)
const endDrag = () => {
  if (!isDragging.value) return;
  isDragging.value = false;
  handleSlide();
};

// 슬라이드 처리
const handleSlide = () => {
  if (deltaX.value > 50) {
    // 오른쪽에서 왼쪽으로 슬라이드
    prevSlide();
  } else if (deltaX.value < -50) {
    // 왼쪽에서 오른쪽으로 슬라이드
    nextSlide();
  }
  deltaX.value = 0;
};

// 다음 슬라이드
const nextSlide = () => {
  if (currentIndex.value < boardDetailImage.value.length - 1) {
    currentIndex.value = (currentIndex.value + 1);
  }
};

// 이전 슬라이드
const prevSlide = () => {
  if (currentIndex.value > 0) {
    currentIndex.value = (currentIndex.value - 1);
  }
};
// ======================= 케러셀 관련 로직들 끝 =======================


// ======================= 보드 수정삭제조회 =======================
const deleteBoard = async () => {
  tempSaveBoard();
  const confirmed = confirm("정말로 삭제하시겠습니까?");
  if (confirmed) {
    const token = sessionStorage.getItem('token');
    await axios.delete(`/api/running/${id}`, {
      data: runningDataStore.updateBoardImageDto,
      headers: {
        'authorization': `Bearer ${token}`,
      },
    });
    router.push({ name: 'myrun' });
    return
  } else {
    return
  }
}
const findDetailById = async () => {
  const { data } = await axios.get(`/api/running/board/${id}`);
  boardDetail.value = data;
  console.log(data);
}
const findDetailImageById = async () => {
  const { data } = await axios.get(`/api/running/image/${id}`);
  boardDetailImage.value = data;
  console.log(data);
}
const findDetailCoodinateById = async () => {
  const { data } = await axios.get(`/api/running/coodinate/${id}`);
  console.log(data)
  coodinate.value = data;
}
const tempSaveBoard = () => {
  runningDataStore.updateBoardDto = boardDetail.value;
  runningDataStore.updateBoardImageDto = boardDetailImage.value;
  runningDataStore.coodinate = coodinate.value;
}
findDetailCoodinateById();
findDetailImageById();
findDetailById();
// ======================= 보드 수정삭제조회 끝 =======================


// ======================= 지도 관련 로직 =======================
watch(() => coodinate,
  (newCood) => {
    isMapMount.value = true;
    console.log(isMapMount.value)
  },
  { deep: true }
);
// ======================= 지도 관련 로직 끝 =======================
</script>

<style scoped>
/* ======================= 캐러셀 관련 css ======================= */
.carousel {
  position: relative;
  width: 316px;
  height: auto;
  overflow: hidden;
  margin: 0 auto;
}

.carousel-track {
  display: flex;
  transition: transform 0.3s ease-in-out;
}

.carousel-item {
  flex: 0 0 100%;
  position: relative;
  padding-top: 100%;
  /* 1:1 비율 */
}

.carousel-item img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.carousel-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
  color: white;
  font-size: 1.5rem;
  cursor: pointer;
  padding: 0.5rem 1rem;
  z-index: 10;
}

.carousel-button.prev {
  left: 10px;
}

.carousel-button.next {
  right: 10px;
}

/* ======================= 캐러셀 관련 css 끝 ======================= */




/* ======================= 기본 css ======================= */
.content-detail,
.content-detail * {
  box-sizing: border-box;
}

.content-detail {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  min-height: 1000px;
  position: relative;
  overflow: hidden;
}

.feed-container {
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

.feed {
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

.map-container {
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
  height: 351px;
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
  flex: 1;
  position: relative;
  overflow: hidden;
  object-fit: cover;
}

.image-container {
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

.image2 {
  background: var(--image-placeholder,
      linear-gradient(to left, #e3e3e3, #e3e3e3));
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

.div2 {
  color: #000000;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 15px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}


.upordel {
  padding: 5px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}

.edit-board {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  position: relative;
  overflow: visible;
}

.delete-board {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  position: relative;
  overflow: visible;
}

/* ======================= 기본 css ======================= */
</style>