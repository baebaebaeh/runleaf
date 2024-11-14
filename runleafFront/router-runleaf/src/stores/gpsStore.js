import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

export const useGpsStore = defineStore('gps', () => {
  const locations = ref([]);

  function addLocation(location) {
    locations.value.push(location);
    console.log(locations);
  };

  const postLocations = async () => {
    const { data } = await axios.post('/api/gps', locations.value);
    console.log(data)
  };

  return { locations, addLocation, postLocations };
}
);
