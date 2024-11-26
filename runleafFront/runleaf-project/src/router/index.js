import { createRouter, createWebHistory } from 'vue-router'
import JoinView from '@/views/JoinView.vue'
import JoinInfo from '@/components/member/JoinInfo.vue'
import JoinProfile from '@/components/member/JoinProfile.vue'
import LoginView from '@/views/LoginView.vue'
import HomeView from '@/views/HomeView.vue'
import RunningView from '@/views/RunningView.vue'
import RunAfterView from '@/views/RunAfterView.vue'
import MyInfoView from '@/views/MyInfoView.vue'
import EditMember from '@/components/member/EditMember.vue'
import MyrunUpdateView from '@/views/MyrunUpdateView.vue'
import NaverMapTESTView from '@/views/NaverMapTESTView.vue'
import OtherInfoView from '@/views/OtherInfoView.vue'
import FollowerListView from '@/views/FollowerListView.vue'
import FollowingListView from '@/views/FollowingListView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/follower',
      name: 'follower',
      component: FollowerListView,
    },
    {
      path: '/following',
      name: 'following',
      component: FollowingListView,
    },

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
      path: '/otherinfo/:username',
      name: 'otherInfo',
      component: OtherInfoView
    },
  ],
});

export default router
