import { createRouter, createWebHistory } from 'vue-router'
import GpsView from '@/views/GpsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'gps',
      component: GpsView,
    },

  ],
})

export default router
