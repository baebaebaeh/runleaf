<template>
  <div class="menu">
    <div class="feed-container">
      <div class="feed" v-for="(runningBoard, index) in boardDto" :key="runningBoard.runningBoardId">
        <!-- 이미지 관련 파트 -->
        <div class="main-image">
          <RouterLink class="div3" :to="{ name : 'otherInfo', params : { username : runningBoard.writer } }">{{ runningBoard.writer }}</RouterLink>
          <div class="div">{{ runningBoard.title }}</div>

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
            <div class="div3">난이도 : {{ runningBoard.difficulty }}</div>
            <div class="div3">뛴거리 : {{ runningBoard.totalDist }}m</div>
            <div v-if="runningBoard.totalRunningSecond < 60" class="div3">뛴시간 : {{ runningBoard.totalRunningSecond }}초
            </div>
            <div v-if="runningBoard.totalRunningSecond >= 60 && runningBoard.totalRunningSecond < 3600" class="div3">
              뛴시간 : {{ Math.floor(runningBoard.totalRunningSecond / 60) }}분 {{ runningBoard.totalRunningSecond % 60 }}초
            </div>
            <div v-if="runningBoard.totalRunningSecond >= 3600" class="div3">뛴시간 : {{
              Math.floor(runningBoard.totalRunningSecond / 3600) }}시간 {{ Math.floor(runningBoard.totalRunningSecond %
                3600 / 60) }}분 {{ runningBoard.totalRunningSecond % 60 }}초 </div>
            <div class="div2">{{ runningBoard.content }}</div>
          </div>
          <div class="commentmap">
            <img class="message-square" src="@/assets/images/icons/message-square.svg"
              @click="openCommentModal(index)" />
            <img class="map-icon" src="@/assets/images/icons/map.svg"
              @click="openMapModal(boardDto[index].runningBoardId)" />
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
        <div class="frame-49" v-for="(comment, i) in comments">
          <div class="comment-css">
            <h3>작성자 : {{ comment.writer }}</h3>
            <h3>{{ comment.content }}</h3>
          </div>
          <div class="comment-buttons">
            <button @click="isOnChange(i)">답글 달기</button>
            <button class="delete-btn" @click="deleteComment(comment.commentId)">삭제</button>
          </div>
          <button v-if="comment.replyCount != 0" @click="isOnReply(i)">
            답글 {{ comment.replyCount }}개 더보기
          </button>
          <div class="reply-css" v-for="(reply, i) in comments[i].replys">
            <h3>작성자 : {{ reply.writer }}</h3>
            <h3>{{ reply.content }}</h3>
            <!-- <button v-if="authStore.loginUsername === comment.writer" @click="deleteComment()"> -->
            <div class="reply-buttons">
              <button class="delete-btn" @click="deleteComment(reply.commentId)">삭제</button>
            </div>
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
import { ref, onMounted, onUnmounted, watch } from 'vue';
import { onBeforeRouteLeave, RouterLink } from 'vue-router'
import axios from 'axios';
import { useGpsStore } from '@/stores/gpsStore.js';
import { useAuthStore } from '@/stores/auth.js'
import MapComponent from '@/views/NaverMapTESTView.vue'
// ======================= 임포트 =======================

// ======================= 변수선언 =======================
const authStore = useAuthStore();
const username = authStore.loginUsername;
const gpsStore = useGpsStore();
const boardDto = ref([])
const isOn = ref({
  isReply: false,
  writer: '',
});
const commentDto = ref({
  parentId: 0,
  runningBoardId: 0,
  content: '',
})
const boardDetail = ref()
const comments = ref([])
const coodinate = ref([])
const isMapModal = ref(false);
const isCommentModal = ref(false);
const orderString = ref()
const tempIdx = ref();
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
const openCommentModal = async (index) => {
  const id = boardDto.value[index].runningBoardId;
  tempIdx.value = index;
  console.log(id);
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
  commentDto.value.content = '';
  openCommentModal(tempIdx.value)
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


const deleteComment = async (id) => {
  console.log(id);
  const confirmed = confirm("정말로 삭제하시겠습니까?");
  if (confirmed) {
    const token = sessionStorage.getItem('token');
    await axios.delete(`/api/comment/${id}`, {
      headers: {
        'authorization': `Bearer ${token}`,
      },
    });
    openCommentModal(tempIdx.value)
    return
  } else {
    return
  }
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
          location: runningBoard.location,
          totalDist: runningBoard.totalDist,
          totalRunningSecond: runningBoard.totalRunningSecond,
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
  color: #c3c3c3;
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

.comment-buttons,
.reply-buttons {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.delete-btn {
  background-color: #ff4d4d;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 12px;
  font-weight: bold;
}

.delete-btn:hover {
  background-color: #cc0000;
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
  font-size: 18px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
  align-self: stretch;
}

.div3 {
  color: #5a5a5a;
  text-align: left;
  font-family: "Inter-Regular", sans-serif;
  font-size: 14px;
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