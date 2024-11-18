import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useRunningDataStore = defineStore('runningData', () => {
  const boardDto = ref([]);
  const locations = ref([]);

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

  return { boardDto, locations, addBoard, addLocation, postLocations, getLocations };
}
);
