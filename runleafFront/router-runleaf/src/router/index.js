import { createRouter, createWebHistory } from 'vue-router'
import GpsView from '@/views/GpsView.vue'
import BoardView from '@/views/BoardView.vue'
import RunningDataView from '@/views/RunningDataView.vue'
import RunningDataForm from '@/components/RunningData/RunningDataForm.vue'
import RunningDataList from '@/components/RunningData/RunningDataList.vue'
import RunningDataDetail from '@/components/RunningData/RunningDataDetail.vue'

import JoinView from '@/views/JoinView.vue'
import JoinInfo from '@/components/member/JoinInfo.vue'
import JoinProfile from '@/components/member/JoinProfile.vue'
import MemberEdit from '@/components/member/MemberEdit.vue'
import MemberView from '@/views/MemberView.vue'
import LoginView from '@/views/LoginView.vue'
import MenuView from '@/views/MenuView.vue'
import HomeView from '@/views/HomeView.vue'
import MyrunView from '@/views/MyrunView.vue'
import MyrunDetailView from '@/views/MyrunDetailView.vue'
import RunningView from '@/views/RunningView.vue'
import RunAfterView from '@/views/RunAfterView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/menu',
      name: 'menu',
      component: MenuView,
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/myrun',
      name: 'myrun',
      component: MyrunView,
    },
    {
      path: '/detail/:id',
      name: 'myrunDetail',
      component: MyrunDetailView,
    },
    {
      path: '/run',
      name: 'running',
      component: RunningView,
    },
    {
      path: '/regist',
      name: 'runAfter',
      component: RunAfterView,
    },








    

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
          path: 'list',
          name: 'runningDataList',
          component: RunningDataList,
          children: [
            {
              path: 'detail/:id',
              name: 'runningDataDetail',
              component: RunningDataDetail,
              props: true,
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
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/member',
      name: 'member',
      component: MemberView,
    },
    {
      path: '/member/edit',
      name: 'memberEdit',
      component: MemberEdit
    },
  ],
});

export default router
