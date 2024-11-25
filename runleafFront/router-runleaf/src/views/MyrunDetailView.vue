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
          <!-- <div class="div2">onBoard : {{ boardDetail.onBoard }}</div>
          <div class="div2">startRunningTs : {{ boardDetail.startRunningTs }}</div>
          <div class="div2">endRunningTs : {{ boardDetail.endRunningTs }}</div>
          <div class="div2">startLatitude : {{ boardDetail.startLatitude }}</div>
          <div class="div2">startLongitude : {{ boardDetail.startLongitude }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div>
          <div class="div2">modifiedTs : {{ boardDetail.modifiedTs }}</div>
          <div class="div2">writer : {{ boardDetail.writer }}</div>
          <div class="div2">createdTs : {{ boardDetail.createdTs }}</div> -->

          <!-- <div class="div2" v-for="(c, index) in coodinate" :key="c.runningCoodinateId">
            <div>latitude: {{ c.latitude }}</div>
            <div>longitude: {{ c.longitude }}</div>
            <div>createdTs: {{ c.createdTs }}</div>
          </div> -->

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
          <button @click="openCommentModal()">
            <img class="delete-board" src="@/assets/images/icons/message-square.svg" />
          </button>
          <button @click="deleteBoard()">
            <img class="delete-board" src="@/assets/images/icons/delete-board.png" />
          </button>
        </div>
      </div>
    </div>


    <div v-if="isCommentModal" class="modal-overlay" @click.self="closeCommentModal">
      <div class="modal-content">
        <div class="frame-49" v-for="(comment, i) in comments">
          <div class="comment-css">
            <h3>작성자 : {{ comment.writer }}</h3>
            <h3>{{ comment.content }}</h3>
          </div>
          <button @click="isOnChange(i)">
            답글 달기
          </button>
          <button v-if="comment.replyCount != 0" @click="isOnReply(i)">
            답글 {{ comment.replyCount }}개 더보기
          </button>
          <div class="reply-css" v-for="(reply, i) in comments[i].replys">
            {{ reply.content }}
          </div>
        </div>
        <div class="frame-50">
          <div v-if="isOn.isReply">
            <button @click="isOffChange()">
              {{ isOn.writer }}님에게 답글다는중
            </button>
          </div>
          <input name="commentId" type="text" v-model="commentDto.content" />
          <input name="commentId" type="text" v-model="commentDto.runningBoardId" hidden />
          <input name="commentId" type="text" v-model="commentDto.parentId" hidden />
          <button @click="insertCommet()">등록</button>
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
const isCommentModal = ref(false);
const comments = ref([])
const commentDto = ref({
  parentId: 0,
  runningBoardId: 0,
  content: '',
})
const isOn = ref({
  isReply: false,
  writer: '',
});
// ======================= 변수 선언 끝 =======================


// ======================= 댓글모달 로직 =======================
const openCommentModal = async () => {
  const { data } = await axios.get(`/api/comment/${id}`);
  comments.value = data;
  commentDto.value.runningBoardId = id;
  isCommentModal.value = true;
}
const closeCommentModal = () => {
  isCommentModal.value = false;
}
const isOnChange = (i) => {
  isOn.value.isReply = true;
  isOn.value.writer = comments.value[i].writer;
  commentDto.value.parentId = comments.value[i].commentId;
  console.log(comments.value[i].commentId);
}
const insertCommet = async () => {
  const token = sessionStorage.getItem('token');
  const { data } = await axios.post(`/api/comment`, commentDto.value, {
    headers: {
      'authorization': `Bearer ${token}`,
    }
  })
  openCommentModal()
};
const isOffChange = () => {
  isOn.value.isReply = false;
  commentDto.value.parentId = 0;
}
const isOnReply = async (i) => {
  const id = comments.value[i].commentId;
  const { data } = await axios.get(`/api/comment/reply/${id}`)
  if (comments.value[i].isOnReply) {
    comments.value[i].isOnReply = false;
  } else {
    comments.value[i].isOnReply = true;
  }
  comments.value[i].replys = data;
}
// ======================= 댓글모달 로직 =======================



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



/* ======================= 모달관련 css ======================= */
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
  background-color: #f9f9f9;
  /* 밝은 회색 배경 */
  flex-direction: column;
  padding-top: 20px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 10px;
  width: 370px;
  height: 50%;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
  text-align: center;
  overflow-y: auto;
  /* 댓글이 많을 때 스크롤 활성화 */
}

.frame-49 {
  padding: 15px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  background-color: white;
  border-radius: 10px;
  align-items: flex-start;
  justify-content: center;
  margin-bottom: 10px;
}

.frame-49 div h3 {
  text-align: left;
  margin: 0;
  font-size: 14px;
  font-weight: 600;
  color: #333;
}

.frame-49 button:hover {
  color: #0056b3;
}

.frame-50 {
  background-color: #f9f9f9;
  /* 배경색 추가 */
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 20px;
  position: sticky;
  bottom: 0;
  padding-bottom: 20px;
}

.frame-50 button {
  background-color: #c3c3c3;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 10px;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
}

.frame-50 button:hover {
  background-color: #0056b3;
}

.frame-50 input[type="text"] {
  width: 100%;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 10px;
}

.frame-50 input[type="text"]:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.frame-49 button {
  margin-top: 5px;
  padding: 5px 10px;
  font-size: 12px;
  color: #000000;
  background: none;
  border: none;
  cursor: pointer;
  text-decoration: underline;
}

.modal-overlay .modal-content {
  animation: fadeIn 0.1s ease-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.comment-css {
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  margin-bottom: 10px;
  font-size: 14px;
  font-weight: 400;
  color: #333;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.reply-css {
  padding: 10px 15px;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  background-color: #ffffff;
  margin: 5px 0 10px 20px;
  /* 대댓글은 안쪽으로 들여쓰기 */
  font-size: 13px;
  font-weight: 400;
  color: #555;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}

.comment-css h3,
.reply-css h3 {
  margin: 5px 0;
  font-size: 14px;
  font-weight: 600;
  color: #222;
}

.comment-css button,
.reply-css button {
  margin-top: 10px;
  padding: 5px 10px;
  font-size: 12px;
  font-weight: 600;
  color: #007bff;
  background-color: transparent;
  border: none;
  cursor: pointer;
  text-decoration: underline;
}

.comment-css button:hover,
.reply-css button:hover {
  color: #0056b3;
}

.reply-css::before {
  content: '';
  display: block;
  width: 2px;
  height: 100%;
  background-color: #ddd;
  position: absolute;
  left: 10px;
  top: 0;
  bottom: 0;
  z-index: -1;
}

.frame-49 {
  padding: 15px;
  background-color: white;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  border: 1px solid #ddd;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* ======================= 모달관련 css ======================= */
</style>