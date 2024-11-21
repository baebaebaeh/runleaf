<template>
  <div class="menu">
    <div class="feed-container">
      <div class="div">피드</div>
      <div class="feed" v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId"
        >
        <!-- 이미지 관련 파트 -->
        <div class="main-image">
          <div class="div">제목 : {{ runningBoard.title }}</div>

          <!-- 이미지 있을때 캐러셀 표시 -->
          <div v-if="boardDto[index].runningBoardImage.length != 0" class="carousel" @touchstart="startTouch"
            @touchmove="moveTouch" @touchend="endTouch(index)" @mousedown="startDrag" @mousemove="moveDrag"
            @mouseup="endDrag(index)" @mouseleave="endDrag(index)">
            <div class="carousel-track" :style="{ transform: `translateX(-${boardDto[index].currentIndex * 100}%)` }">
              <div class="carousel-item" v-for="(image, i) in runningBoard.runningBoardImage" :key="i">
                <img :src="`/api/uploads/${image.path}${image.systemName}`" alt="carousel image" />
              </div>
            </div>
            <!-- 이미지 개수 표시 -->
            <div>
              {{ boardDto[index].currentIndex + 1 + '/' + runningBoard.runningBoardImage.length }}
            </div>
          </div>

          <!-- 이미지 없을때 기본이미지 표시 -->
          <div v-if="boardDto[index].runningBoardImage.length == 0">
            <img class="image" :src="`/api/uploads/uploads/defaultimg/abcd.png`" />
            <div>
              0/0
            </div>
          </div>

          <div class="content">
            <div class="div2">내용 : {{ runningBoard.content }}</div>
            <div class="div2">난이도 : {{ runningBoard.difficulty }}</div>
          </div>
          <div class="commentmap">
            <img class="message-square" src="@/assets/images/icons/message-square.svg" @click="openCommentModal(boardDto[index].runningBoardId)"  />
            <img class="map-icon" src="@/assets/images/icons/map.svg" @click="openMapModal(boardDto[index].runningBoardId)" />
          </div>
        </div>
      </div>
    </div>

    <!-- 모달 관련 파트 -->
    <div v-if="isMapModal" class="modal-overlay" @click.self="closeMapModal">
      <div class="modal-content">
        <MapComponent class="map" :coodinate="coodinate" />
      </div>
    </div>
    <div v-if="isCommentModal" class="modal-overlay" @click.self="closeCommentModal">
      <div class="modal-content">
        <h3>{{ boardDetail.runningBoardId }}</h3>
        <h3>{{ boardDetail.title }}</h3>
        <h3>{{ boardDetail.content }}</h3>
        <div v-for="(location, i) in boardDetail.location">
          <h3>{{ location.latitude }}</h3>
          <h3>{{ location.longitude }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// ======================= 임포트 =======================
import { ref, onMounted, onUnmounted, watch } from 'vue';
import { onBeforeRouteLeave, RouterLink } from 'vue-router'
import axios from 'axios';
import { useGpsStore } from '@/stores/gpsStore.js';
import MapComponent from '@/views/NaverMapTESTView.vue'
// ======================= 임포트 =======================

// ======================= 변수선언 =======================
const gpsStore = useGpsStore();
const boardDto = ref([])
const boardDetail = ref()
const comment = ref([])
const coodinate = ref([])
const isMapModal = ref(false);
const isCommentModal = ref(false);
const orderString = ref()
let isFetching = false;
let hasMoreData = true;
// ======================= 변수선언 =======================

const initOrderString = () => {
  if (gpsStore.boardSearchDto.orderInt == 1) {
    orderString.value = '거리순'
  } else if (gpsStore.boardSearchDto.orderInt == 2) {
    orderString.value = '최신순'
  }
}
initOrderString();
// ======================= 지도모달 로직 =======================
const openMapModal = async (id) => {
  console.log(id)
  const { data } = await axios.get(`/api/running/coodinate/${id}`);
  coodinate.value = data;
  console.log(data)
  isMapModal.value = true;
}
const closeMapModal = () => {
  isMapModal.value = false;
}
// ======================= 지도모달 로직 =======================

// ======================= 댓글모달 로직 =======================
const openCommentModal = async (id) => {
  console.log(id);
  const { data } = await axios.get(`/api/comment/${id}`);
  comment.value = data;
  isCommentModal.value = true;
}
const closeCommentModal = () => {
  isCommentModal.value = false;
}
// ======================= 댓글모달 로직 =======================

// ======================= 캐러셀 로직 =======================
// 슬라이드 감지 관련 변수
const startX = ref(0); // 터치 또는 드래그 시작 위치
const deltaX = ref(0); // 이동 거리
const isDragging = ref(false); // 드래그 상태

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
const endTouch = (index) => {
  handleSlide(index);
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
const endDrag = (index) => {
  if (!isDragging.value) return;
  isDragging.value = false;
  handleSlide(index);
};

// 슬라이드 처리
const handleSlide = (index) => {
  if (deltaX.value > 50) {
    // 오른쪽에서 왼쪽으로 슬라이드
    prevSlide(index);
  } else if (deltaX.value < -50) {
    // 왼쪽에서 오른쪽으로 슬라이드
    nextSlide(index);
  }
  deltaX.value = 0;
};

// 다음 슬라이드
const nextSlide = (index) => {
  if (boardDto.value[index].currentIndex < boardDto.value[index].runningBoardImage.length - 1) {
    boardDto.value[index].currentIndex++;
    console.log(boardDto.value[index].currentIndex);
  }
};

// 이전 슬라이드
const prevSlide = (index) => {
  if (boardDto.value[index].currentIndex > 0) {
    boardDto.value[index].currentIndex--;
    console.log(boardDto.value[index].currentIndex);
  }
};
// ======================= 캐러셀 로직 =======================


// vue관련 로직
onMounted(() => {
  getRunningBoardList();
});
// 최신순, 거리순 바뀌면 보드 새로 불러오기
watch(
  () => gpsStore.boardSearchDto.orderInt,
  (newVal, oldVal) => {
    gpsStore.boardSearchDto.page = 1;
    boardDto.value = [];
    getRunningBoardList();
    hasMoreData = true;
  },
  { deep: true } // 객체 내부 속성까지 감시
);


// ======================= 보드리스트 불러오기 로직 =======================
// console.log(sessionStorage.getItem('token'));

const getRunningBoardList = async () => {
  isFetching = true;
  try {
    const token = sessionStorage.getItem('token');
    const { data } = await axios.get("/api/running/list", {
      params: gpsStore.boardSearchDto,
      // headers: {
      //   'authorization': `Bearer ${token}`,
      // },
    });
    if (data.length == 0) {
      hasMoreData = false;
    } else {
      data.forEach((runningBoard) => {
        boardDto.value.push({
          currentIndex: 0, // 캐러셀을 위한 변수
          runningBoardId: runningBoard.runningBoardId,
          memberId: runningBoard.memberId,
          difficulty: runningBoard.difficulty,
          startRunningTs: runningBoard.startRunningTs,
          endRunningTs: runningBoard.endRunningTs,
          startLatitude: runningBoard.startLatitude,
          startLongitude: runningBoard.startLongitude,
          createdTs: runningBoard.createdTs,
          modifiedTs: runningBoard.modifiedTs,
          title: runningBoard.title,
          content: runningBoard.content,
          mainImagePath: runningBoard.mainImagePath,
          writer: runningBoard.writer,
          onBoard: runningBoard.onBoard,
          runningBoardImage: runningBoard.runningBoardImage,
          location: runningBoard.location
        })
        console.log(boardDto.value[0].runningBoardImage);
      });
      gpsStore.boardSearchDto.page += 1;
    }
  } catch (error) {
    console.log(gpsStore.boardSearchDto.page);
  } finally {
    isFetching = false;
  }
  console.log(boardDto.value)
}
// ======================= 보드리스트 불러오기 로직 =======================


// ======================= 스크롤 관련 로직 =======================
function infinityScroll(e) {
  if (document.body.scrollHeight - window.innerHeight - document.documentElement.scrollTop < 100) {
    if (!isFetching && hasMoreData) {
      getRunningBoardList();
    }
  }
}
window.addEventListener('scroll', infinityScroll);
onBeforeRouteLeave((to, from, next) => {
  window.removeEventListener('scroll', infinityScroll); // 이벤트 리스너 해제
  gpsStore.boardSearchDto.page = 1;
  next(); // 이동을 계속함
});
// ======================= 스크롤 관련 로직 =======================
</script>

<style scoped>
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















.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* background-color: rgba(0, 0, 0, 0.5); */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  /* 항상 위에 보이도록 설정 */
}

/* 모달 내용 */
.modal-content {
  display: flex;
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 100%;
  height: 50%;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  text-align: center;
}
.map {
  width: 100%;
  height: 100%;
}

.nav-bar {
  display: flex;
  justify-content: center;
  gap: 20px;
  /* 링크들 사이에 간격 */
  background-color: #d1d1d1;
  /* 배경색 */
  padding: 10px 0;
  border-radius: 10px;
  /* 모서리 둥글게 */
}

.nav-link {
  text-decoration: none;
  padding: 10px 20px;
  font-size: 16px;
  color: #333;
  background-color: #ffffff;
  border-radius: 5px;
  border: 1px solid #ccc;
  /* 테두리 색 */
  transition: background-color 0.3s ease, color 0.3s ease;
}

.nav-link:hover {
  background-color: #9c9c9c;
  /* 호버 시 배경색 */
  color: #fff;
  /* 호버 시 텍스트 색상 */
}

.nav-bar a {
  text-decoration: none;
  /* 링크에 밑줄 제거 */
}








.myrun,
.myrun * {
  box-sizing: border-box;
}

.myrun {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
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
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 370px;
  position: relative;
  overflow: hidden;
}

.main-image {
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
      linear-gradient(to left, #ffffff, #ffffff));
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
  object-fit: contain;
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
  font-size: 24px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}



.commentmap {
  border-width: 1px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
}
.message-square {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}
.map-icon {
  flex-shrink: 0;
  width: 48px;
  height: 48px;
  position: relative;
  overflow: visible;
}

</style>