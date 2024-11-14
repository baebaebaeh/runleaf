import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useRunningDataStore = defineStore('runningData', () => {
  const boardDto = ref([]);

  function addBoard(newBoard) {
    boardDto.value.push(newBoard);
  };

  return { boardDto, addBoard };
}
);
