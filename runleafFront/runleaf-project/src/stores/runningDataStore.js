import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useRunningDataStore = defineStore('runningData', () => {
  const updateBoardDto = ref({});
  const updateBoardImageDto = ref([]);
  const coodinate = ref([]);
  return { updateBoardDto, updateBoardImageDto, coodinate };
}
);
