import { createRouter, createWebHistory } from 'vue-router'
import ShareView from "../views/ShareView.vue"
import HotPlaceView from "../views/HotPlaceView.vue"
import AttractionView from "../views/AttractionView.vue"
import MyPlanView from "../views/MyPlanView.vue"
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
      path: "/user",
      name: "user",
      component: () => import("../views/UserView.vue"),
      redirect: {name: "login"},
      children:[
        { 
          path: '/login',
          name: 'login',
          component: ()=>import("@/components/user/LoginPage.vue") 
        },    
        {
          path: '/join',
          name: 'join',
          component: ()=>import("@/components/user/JoinPage.vue") 
        },
        {
          path: '/mypage',
          name: 'my-page',
          component: ()=>import("@/components/user/MyPage.vue") 
        },
      ]},
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
