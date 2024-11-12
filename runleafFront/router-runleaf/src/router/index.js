import { createRouter, createWebHistory } from 'vue-router'
import GpsView from '@/views/GpsView.vue'
import BoardView from '@/views/BoardView.vue'
import RunningDataView from '@/views/RunningDataView.vue'

import JoinView from '@/views/JoinView.vue'
import JoinProfile from '@/components/JoinProfile.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/board',
      name: 'board',
      component: BoardView,
    },
    {
      path: '/running',
      name: 'runningData',
      component: RunningDataView,
    },
    {
      path: '/gps',
      name: 'gps',
      component: GpsView,
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView,
      children: [
        {
          path: "profile",
          name: "joinProfile",
          component: JoinProfile
        }
      ]
    },
  ],
})

export default router
