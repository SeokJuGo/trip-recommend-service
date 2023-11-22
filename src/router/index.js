import { createRouter, createWebHistory } from "vue-router";
import ShareView from "../views/ShareView.vue";
import HotPlaceView from "../views/HotPlaceView.vue";
import AttractionView from "../views/AttractionView.vue";
import MyPlanView from "../views/MyPlanView.vue";
import MainView from "../views/MainView.vue";
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/attraction",
            name: "attraction",
            component: AttractionView,
        },
        {
            path: "/myplan",
            name: "myplan",
            component: MyPlanView,
        },
        {
            path: "/user",
            name: "user",
            component: () => import("../views/UserView.vue"),
            redirect: { name: "login" },
            children: [
                {
                    path: "login",
                    name: "login",
                    component: () => import("@/components/user/LoginPage.vue"),
                },
                {
                    path: "join",
                    name: "join",
                    component: () => import("@/components/user/JoinPage.vue"),
                },
                {
                    path: "mypage",
                    name: "mypage",
                    component: () => import("@/components/user/MyPage.vue"),
                    redirect: { name: "user-myplan" },
                    children: [
                        {
                            path: "user-myplan",
                            name: "user-myplan",
                            component: () => import("@/components/user/components/UserMyplan.vue"),
                        },
                        {
                            path: "user-board",
                            name: "user-board",
                            component: () =>
                                import("@/components/user/components/UserBoardList.vue"),
                        },
                        {
                            path: "user-info",
                            name: "user-info",
                            component: () => import("@/components/user/components/UserInfo.vue"),
                        },
                        {
                            path: "user-update",
                            name: "user-update",
                            component: () =>
                                import("@/components/user/components/UserInfoUpdate.vue"),
                        },
                    ],
                },
            ],
        },
        {
            path: "/",
            name: "main",
            component: MainView,
            // component: SampleView,
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
        },
        {
            path: "/share",
            name: "share",
            component: () => import("../views/ShareView.vue"),
            redirect: { name: "share-list" },
            children: [
                {
                    path: "list",
                    name: "share-list",
                    component: () => import("@/components/share/ShareList.vue"),
                },
                {
                    path: "view/:id",
                    name: "share-view",
                    component: () => import("@/components/share/ShareDetail.vue"),
                },
                {
                    path: "write",
                    name: "share-write",
                    component: () => import("@/components/share/ShareWrite.vue"),
                },
                {
                    path: "update/:id",
                    name: "share-update",
                    component: () => import("@/components/share/ShareUpdate.vue"),
                },
            ],
        },
        {
            path: "/hotplace",
            name: "hotplace",
            component: HotPlaceView,
            redirect: { path: "/hotplace/list" },
            children: [
                {
                    path: "list",
                    name: "hotplace-list",
                    component: () => import("@/components/hotplace/HotplaceList.vue"),
                },
                {
                    path: "view/:id",
                    name: "hotplace-view",
                    component: () => import("@/components/hotplace/HotplaceDetail.vue"),
                },
                {
                    path: "write",
                    name: "hotplace-write",
                    component: () => import("@/components/hotplace/HotplaceWrite.vue"),
                },
                {
                    path: "update/:id",
                    name: "hotplace-update",
                    component: () => import("@/components/hotplace/HotplaceUpdate.vue"),
                },
            ],
        },

        // {
        //   path: '/about',
        //   name: 'about',
        //   // route level code-splitting
        //   // this generates a separate chunk (About.[hash].js) for this route
        //   // which is lazy-loaded when the route is visited.
        //   component: () => import('../views/AboutView.vue')
        // }
    ],
});

export default router;
