import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useRunningDataStore = defineStore('runningData', () => {
  const boardDto = ref([]);
  const locations = ref([]);
  const boardSearchDto = ref({
    page: 1,
    userId: -1,
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
    console.log(boardSearchDto.value);
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

  const postLocations = async () => {
    locations.value = [];
  };

  function addBoard(newBoard) {
    boardDto.value.push(newBoard);

  };
  function getLocations() {
    return locations.value;
  };

  return { boardDto, locations,
     addBoard, addLocation, postLocations, getLocations, initLocation };
}
);
