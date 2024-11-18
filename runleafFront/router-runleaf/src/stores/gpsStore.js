import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useGpsStore = defineStore('gps', () => {
  const locations = ref([]);
  const intervalId = ref(null);
  const intervalCnt = ref(null);
  const isRunning = ref(false);
  const isPausing = ref(false);
  const startLatitude = ref();
  const startLongitude = ref();
  const startTs = ref();
  const endTs = ref();
  const cnt = ref(0);

  function addLocation(location) {
    locations.value.push(location);
    console.log(locations);
  };

  function determineInitialValue() {
    startLatitude.value = locations.value[0].latitude;
    startLongitude.value = locations.value[0].longitude;
    startTs.value = locations.value[0].createdTs;
    endTs.value = locations.value[locations.value.length - 1].createdTs;
  };

  const postLocations = async () => {
    locations.value = [];
    cnt.value = 0;
  };

  return {
    intervalId, isRunning, locations, isPausing, startLatitude, startLongitude, startTs, endTs, intervalCnt, cnt,
    addLocation, postLocations, determineInitialValue
  };
}
);
