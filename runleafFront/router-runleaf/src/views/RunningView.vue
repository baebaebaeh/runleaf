<template>
  <div class="running">
    <div class="content-container">
      <div class="_01-35-01">
        운동시간
        <br />
        {{ Math.floor(gpsStore.cnt / 3600) }} : {{ Math.floor(gpsStore.cnt / 60) % 60 }} : {{ gpsStore.cnt % 60 }}
      </div>
      <div>
        {{ tempx }}
      </div>
      <div>
        {{ tempy }}
      </div>
      <img @click="startLocationInterval" class="play-circle" src="`@/assets/images/icons/play-circle.svg`"
        v-if="!gpsStore.isRunning || gpsStore.isPause" />
      <img @click="pauseLocationInterval" class="pause-circle" src="`@/assets/images/icons/pause-circle.svg`"
        v-if="gpsStore.isRunning && !gpsStore.isPause" />
      <img @click="stopLocationInterval" class="stop-circle" src="`@/assets/images/icons/stop-circle.svg`"
        v-if="gpsStore.isRunning" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import { create, all } from "mathjs";
import { useGpsStore } from "@/stores/gpsStore.js";
import { useRouter } from "vue-router";
const tempx = ref();
const tempy = ref();
const router = useRouter();
// Helper functions
const timestamp = ref(0);
const initialSamples = ref([]); // 초기 데이터를 저장할 배열
const initialized = ref(false); // 초기화 상태
// Vuex Store
const gpsStore = useGpsStore();
// Initialize Math.js
const math = create(all);

// Kalman filter state
let state = {
  x: 0, // Latitude
  y: 0, // Longitude
  vx: 0, // Velocity in x direction
  vy: 0, // Velocity in y direction
};

/*
변수                  값이 작을 때	                  값이 클 때
Q (프로세스 노이즈)  	모델에 더 의존 (측정값 무시)	   측정값에 더 의존 (모델 무시)
R (측정 노이즈)	      측정값에 더 의존	               모델에 더 의존
P (초기 오차 공분산)	초기 상태를 더 신뢰	             초기 상태를 덜 신뢰하고 점진적 보정
A (상태 전이 행렬)	  정확한 상태 예측	              모델이 현실과 동떨어질 수 있음
H (관측 행렬)	        측정 가능한 상태를 정확히 반영	잘못된 측정 데이터를 가져올 가능성
*/

const Q = math.identity(4).map((v) => v * 0.01); // Process noise
const R = math.matrix([
  [5, 0], // GPS 노이즈
  [0, 5],
]); // Measurement noise
let P = math.matrix([
  [1, 0, 0, 0],
  [0, 1, 0, 0],
  [0, 0, 1000, 0],
  [0, 0, 0, 1000],
]); // Initial covariance matrix
const A = math.matrix([
  [1, 0, 1, 0],
  [0, 1, 0, 1],
  [0, 0, 1, 0],
  [0, 0, 0, 1],
]); // State transition matrix
const H = math.matrix([
  [1, 0, 0, 0],
  [0, 1, 0, 0],
]); // Observation matrix




// 칼만 필터 로직
const kalmanFilter = (z, dt) => {
  const A_dt = A.map((value, [i, j]) => (i < 2 && j >= 2 ? value * dt : value));
  const x_pred = math.multiply(A_dt, math.matrix([state.x, state.y, state.vx, state.vy]));
  const P_pred = math.add(math.multiply(A_dt, math.multiply(P, math.transpose(A_dt))), Q);

  const S = math.add(math.multiply(H, math.multiply(P_pred, math.transpose(H))), R);
  const K = math.multiply(P_pred, math.multiply(math.transpose(H), math.inv(S)));

  const y = math.subtract(z, math.multiply(H, x_pred));
  const x_updated = math.add(x_pred, math.multiply(K, y));
  const P_updated = math.multiply(math.subtract(math.identity(4), math.multiply(K, H)), P_pred);

  state.x = x_updated.get([0]);
  state.y = x_updated.get([1]);
  state.vx = x_updated.get([2]);
  state.vy = x_updated.get([3]);
  P = P_updated;

  return { latitude: state.x, longitude: state.y, speed: Math.sqrt(state.vx ** 2 + state.vy ** 2) };
};

// 노이즈 행렬 조정 함수
const adjustMeasurementNoise = (accuracy) => {
  const noiseScale = Math.max(accuracy / 10, 1); // accuracy를 기반으로 노이즈 비율 계산
  return math.matrix([
    [noiseScale, 0],
    [0, noiseScale],
  ]);
};

// GPS 데이터 가져오기
const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const longitude = position.coords.longitude;
      const latitude = position.coords.latitude;
      const accuracy = position.coords.accuracy; // Geolocation API에서 제공하는 accuracy
      const currentTimestamp = new Date().getTime();

      if (!initialized.value) {
        initialSamples.value.push({ latitude, longitude });
        if (initialSamples.value.length >= 3) {
          console.log("Initial 3 samples collected. Starting tracking...");
          initialized.value = true;
        }
        return;
      }

      const dt = (currentTimestamp - timestamp.value) / 1000; // 시간 간격 계산
      const z = math.matrix([latitude, longitude]);

      const filtered = kalmanFilter(z, dt, accuracy);
      tempx.value = filtered.latitude;
      tempy.value = filtered.longitude;
      gpsStore.addLocation({
        longitude: filtered.longitude,
        latitude: filtered.latitude,
        speed: filtered.speed,
        createdTs: new Date().toISOString(),
      });

      timestamp.value = currentTimestamp;
    });
  } else {
    alert("Geolocation is not supported by this browser.");
  }
};

// 타이머 관리
const startLocationInterval = () => {
  if (gpsStore.intervalId) clearInterval(gpsStore.intervalId);
  gpsStore.intervalId = setInterval(getLocation, 1000);
  gpsStore.intervalCnt = setInterval(() => {
    gpsStore.cnt += 1;
  }, 1000);
  gpsStore.isRunning = true;
  gpsStore.isPause = false;
};

const pauseLocationInterval = () => {
  clearInterval(gpsStore.intervalId);
  clearInterval(gpsStore.intervalCnt);
  gpsStore.isRunning = false;
  gpsStore.isPause = true;
};

const stopLocationInterval = () => {
  clearInterval(gpsStore.intervalId);
  clearInterval(gpsStore.intervalCnt);
  gpsStore.determineInitialValue();
  gpsStore.isRunning = false;
  gpsStore.isPause = false;
  router.push({ name: "runAfter" });
};

// Lifecycle hooks
onMounted(() => {
  if (navigator.geolocation) {
    getLocation(); // 초기 위치 가져오기
  }
});

onBeforeUnmount(() => {
  if (gpsStore.intervalId) clearInterval(gpsStore.intervalId);
  if (gpsStore.intervalCnt) clearInterval(gpsStore.intervalCnt);
});
</script>

<style scoped>
.running,
.running * {
  box-sizing: border-box;
}
.running {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  position: relative;
  overflow: hidden;
}
.content-container {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 680px;
  position: relative;
  overflow: hidden;
}
._01-35-01 {
  color: #000000;
  text-align: center;
  font-family: "Inter-Regular", sans-serif;
  font-size: 48px;
  line-height: 140%;
  font-weight: 400;
  position: relative;
}
.play-circle {
  flex-shrink: 0;
  width: 190px;
  height: 190px;
  position: relative;
  overflow: visible;
}
.pause-circle {
  flex-shrink: 0;
  width: 190px;
  height: 190px;
  position: relative;
  overflow: visible;
}
.stop-circle {
  flex-shrink: 0;
  width: 190px;
  height: 190px;
  position: relative;
  overflow: visible;
}
</style>
