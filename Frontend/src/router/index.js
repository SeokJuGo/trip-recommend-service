import { createRouter, createWebHistory } from "vue-router";
import { userStore } from "@/stores/userPiniaStore";

import { useRouter } from "vue-router";

const onlyAuthUser = async (to, from, next) => {
    const store = userStore();
    let token = sessionStorage.getItem("access-token");
    await store.getUserInfo(token);
    const checkToken = store.checkToken;

    if (!checkToken) {
        store.isLogin = false;
        alert("로그인이 필요한 페이지입니다.");

        router.push({ name: "login" });
    } else {
        next();
    }
};
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),

    routes: [
        {
            path: "/",
            name: "main",
            component: () => import("@/views/MainView.vue"),
            redirect: { path: "/index" },
            children: [
                {
                    path: "index",
                    name: "main-index",
                    component: () => import("@/components/main/MainPage.vue"),
                },
            ],
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
                    redirect: { name: "user-info" },
                    children: [
                        {
                            path: "user-myplan",
                            name: "user-myplan",
                            component: () => import("@/components/user/components/UserMyplan.vue"),
                            beforeEnter: onlyAuthUser,
                        },
                        {
                            path: "find",
                            name: "user-find",
                            component: () =>
                                import("@/components/user/components/FindPassword.vue"),
                        },
                        {
                            path: "user-info",
                            name: "user-info",
                            component: () => import("@/components/user/components/UserInfo.vue"),
                            beforeEnter: onlyAuthUser,
                        },
                        {
                            path: "user-update",
                            name: "user-update",
                            component: () =>
                                import("@/components/user/components/UserInfoUpdate.vue"),
                            beforeEnter: onlyAuthUser,
                        },
                    ],
                },
            ],
        },
        {
            path: "/attraction",
            name: "attraction",
            component: () => import("../views/AttractionView.vue"),
            redirect: { name: "att-page" },
            children: [
                {
                    path: "page",
                    name: "att-page",
                    component: () => import("@/components/attraction/AttractionPage.vue"),
                    beforeEnter: onlyAuthUser,
                },
            ],
        },
        {
            path: "/plan",
            name: "plan",
            component: () => import("@/views/PlanView.vue"),
            redirect: { path: "/plan/list" },
            children: [
                {
                    path: "list",
                    name: "plan-list",
                    component: () => import("@/components/plan/PlanList.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "write",
                    name: "plan-write",
                    component: () => import("@/components/plan/PlanWrite.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "view/:id",
                    name: "plan-view",
                    component: () => import("@/components/plan/PlanDetail.vue"),
                    beforeEnter: onlyAuthUser,
                },
                // {
                //     path: "update/:id",
                //     name: "plan-update",
                //     component: () => import("@/components/plan/PlanUpdate.vue"),
                // },
            ],
        },
        // {
        //     path: "/myplan",
        //     name: "myplan",
        //     component: () => import("@/views/PlanView.vue"),
        //     redirect: { path: "/myplan/list" },
        //     children: [
        //         {
        //             path: "list",
        //             name: "plan-list",
        //             component: () => import("@/components/plan/PlanList.vue"),
        //         },
        //         // {
        //         //     path: "view/:id",
        //         //     name: "plan-view",
        //         //     component: () => import("@/components/plan/PlanDetail.vue"),
        //         // },
        //         // {
        //         //     path: "write",
        //         //     name: "plan-write",
        //         //     component: () => import("@/components/plan/PlanWrite.vue"),
        //         // },
        //     ],
        // },
        {
            path: "/hotplace",
            name: "hotplace",
            component: () => import("../views/HotPlaceView.vue"),
            redirect: { path: "/hotplace/list" },
            children: [
                {
                    path: "list",
                    name: "hotplace-list",
                    component: () => import("@/components/hotplace/HotplaceList.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "view/:id",
                    name: "hotplace-view",
                    component: () => import("@/components/hotplace/HotplaceDetail.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "write",
                    name: "hotplace-write",
                    component: () => import("@/components/hotplace/HotplaceWrite.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "update/:id",
                    name: "hotplace-update",
                    component: () => import("@/components/hotplace/HotplaceUpdate.vue"),
                    beforeEnter: onlyAuthUser,
                },
            ],
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
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "view/:id",
                    name: "share-view",
                    component: () => import("@/components/share/ShareDetail.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "write",
                    name: "share-write",
                    component: () => import("@/components/share/ShareWrite.vue"),
                    beforeEnter: onlyAuthUser,
                },
                {
                    path: "update/:id",
                    name: "share-update",
                    component: () => import("@/components/share/ShareUpdate.vue"),
                    beforeEnter: onlyAuthUser,
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
