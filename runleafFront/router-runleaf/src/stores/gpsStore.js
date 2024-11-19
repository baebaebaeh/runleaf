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
  const boardSearchDto = ref({
    page: 1,
    username: '',
    orderInt: 2,
    latitude: -1,
    longitude: -1,
  });
  function initLocation() {
    if(navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        boardSearchDto.value.latitude = position.coords.latitude;
        boardSearchDto.value.longitude = position.coords.longitude;
      }, showError);
    }
  }

  function showError(error) {
    switch (error.code) {
      case error.PERMISSION_DENIED:
        alert("User denied the request for Geolocation.");
        break;
      case error.POSITION_UNAVAILABLE:
        alert("Location information is unavailable.");
        break;
      case error.TIMEOUT:
        alert("The request to get user location timed out.");
        break;
      case error.UNKNOWN_ERROR:
        alert("An unknown error occurred.");
        break;
    }
  }
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
    intervalId, isRunning, locations, isPausing, startLatitude, startLongitude, startTs, endTs, intervalCnt, cnt, boardSearchDto,
    addLocation, postLocations, determineInitialValue, initLocation,
  };
}
);
