import { createRouter, createWebHistory } from 'vue-router'
import ShareView from "../views/share/ShareView.vue"
import HotPlaceView from "../views/hotplace/HotPlaceView.vue"
import AttractionView from "../views/attraction/AttractionView.vue"
import MyPlanView from "../views/myplans/MyPlanView.vue"
import LoginView from "../views/user/LoginView.vue"
import JoinView from "../views/user/JoinView.vue"
import MyPageView from "../views/user/MyPageView.vue"
import MainView from "../views/MainView.vue"
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
     {
      path: '/share',
      name: 'share',
      component: ShareView
     },
     {
      path: '/hotplace',
      name: 'hotplace',
      component: HotPlaceView
     },
     {
       path: '/attraction',
       name: 'attraction',
       component: AttractionView
     },
     {
      path: '/myplan',
      name: 'myplan',
      component: MyPlanView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },    
    {
      path: '/join',
      name: 'join',
      component: JoinView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageView
    },
    {
      path: '/',
      name: 'main',
      component: MainView
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
     
  ]
})

export default router
