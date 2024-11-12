import { createRouter, createWebHistory } from 'vue-router'
import GpsView from '@/views/GpsView.vue'
import BoardView from '@/views/BoardView.vue'
import RunningDataView from '@/views/RunningDataView.vue'
import RunningDataForm from '@/components/RunningData/RunningDataForm.vue'
import RunningDataList from '@/components/RunningData/RunningDataList.vue'

import JoinView from '@/views/JoinView.vue'
import JoinInfo from '@/components/member/JoinInfo.vue'
import JoinProfile from '@/components/member/JoinProfile.vue'

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
      children: [
        {
          path: '',
          name: 'runningDataList',
          component: RunningDataList
        },
        {
          path: 'runningForm',
          name: 'runningDataForm',
          component: RunningDataForm
        },
      ],
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
          path: '',
          name: 'joinInfo',
          component: JoinInfo
        },
        {
          path: 'profile',
          name: 'joinProfile',
          component: JoinProfile
        },
      ]
    },
  ],
});

export default router
