import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import './assets/base.css';
const app = createApp(App)
app.use(createPinia())
app.use(router)
app.mount('#app')

import { useGpsStore } from '@/stores/gpsStore.js';
const gpsStore = useGpsStore();
gpsStore.initLocation();