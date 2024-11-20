import { createRouter, createWebHistory } from 'vue-router'
import JoinView from '@/views/JoinView.vue'
import JoinInfo from '@/components/member/JoinInfo.vue'
import JoinProfile from '@/components/member/JoinProfile.vue'
import MemberEdit from '@/components/member/MemberEdit.vue'
import LoginView from '@/views/LoginView.vue'
import MenuView from '@/views/MenuView.vue'
import HomeView from '@/views/HomeView.vue'
import MyrunView from '@/views/MyrunView.vue'
import MyrunDetailView from '@/views/MyrunDetailView.vue'
import RunningView from '@/views/RunningView.vue'
import RunAfterView from '@/views/RunAfterView.vue'
import MyInfoView from '@/views/MyInfoView.vue'
import MyrunUpdateView from '@/views/MyrunUpdateView.vue'

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
      path: '/update/:id',
      name: 'myrunUpdate',
      component: MyrunUpdateView,
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
      path: '/info',
      name: 'myInfo',
      component: MyInfoView,
    },
    {
      path: '/member/edit',
      name: 'memberEdit',
      component: MemberEdit
    },
  ],
});

export default router
