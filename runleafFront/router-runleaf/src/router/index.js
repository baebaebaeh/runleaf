import { createRouter, createWebHistory } from 'vue-router'
import JoinView from '@/views/JoinView.vue'
import JoinInfo from '@/components/member/JoinInfo.vue'
import JoinProfile from '@/components/member/JoinProfile.vue'
import LoginView from '@/views/LoginView.vue'
import HomeView from '@/views/HomeView.vue'
import MyrunView from '@/views/MyrunView.vue'
import MyrunDetailView from '@/views/MyrunDetailView.vue'
import RunningView from '@/views/RunningView.vue'
import RunAfterView from '@/views/RunAfterView.vue'
import MyInfoView from '@/views/MyInfoView.vue'
import EditMember from '@/components/member/EditMember.vue'
import MyrunUpdateView from '@/views/MyrunUpdateView.vue'
import NaverMapTESTView from '@/views/NaverMapTESTView.vue'
import MemberInfo from '@/components/member/MemberInfo.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: '',
          name: 'test',
          component: NaverMapTESTView,
        },
      ]
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
      children: [
        {
          path: '',
          name: 'test',
          component: NaverMapTESTView,
        },
      ]
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
      children: [
        {
          path: '',
          name: 'test',
          component: NaverMapTESTView,
        },
      ]
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
      path: '/myinfo',
      name: 'myInfo',
      component: MyInfoView,
    },
    {
      path: '/edit/member',
      name: 'editMember',
      component: EditMember
    },
    {
      path: '/memberinfo',
      name: 'memberInfo',
      component: MemberInfo
    },
  ],
});

export default router
