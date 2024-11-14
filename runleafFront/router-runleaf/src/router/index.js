import { createRouter, createWebHistory } from 'vue-router'
import GpsView from '@/views/GpsView.vue'
import JoinView from '@/views/JoinView.vue'
import BoardView from '@/views/BoardView.vue'
import RunningDataView from '@/views/RunningDataView.vue'
import RunningDataForm from '@/components/RunningData/RunningDataForm.vue'
import RunningDataList from '@/components/RunningData/RunningDataList.vue'
import RunningDataDetail from '@/components/RunningData/RunningDataDetail.vue'

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
      name: 'runningDataView',
      component: RunningDataView,
      children: [
        {
          path: '5list',
          name: 'runningDataList',
          component: RunningDataList,
          children: [
            {
              path: 'detail',
              name: 'runningDataDetail',
              component: RunningDataDetail
            },
          ],
        },
        {
          path: 'form',
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
    },
  ],
})

export default router
