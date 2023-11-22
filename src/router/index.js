import { createRouter, createWebHistory } from "vue-router";
import { userStore } from "@/stores/userPiniaStore";


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
            path: "/hotplace",
            name: "hotplace",
            component: () => import("../views/HotPlaceView.vue"),
            beforeEnter: onlyAuthUser,
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
            ]
        },
        {
            path: "/plan",
            name: "plan",
            component: () => import("../views/PlanView.vue"),
            redirect: { name: "plan-list" },
            children: [
                {
                    path: "list",
                    name: "plan-list",
                    component: () => import("@/components/plan/PlanList.vue"),
                    beforeEnter: onlyAuthUser
                },
                {
                    path: "write",
                    name: "plan-write",
                    component: () => import("@/components/plan/PlanWrite.vue"),
                    beforeEnter: onlyAuthUser
                },
                {
                    path: "view/:id",
                    name: "plan-view",
                    component: () => import("@/components/plan/PlanDetail.vue"),
                    beforeEnter: onlyAuthUser,
                },
            ]
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
                    redirect:{name:"user-myplan"},
                    children: [
                        {
                            path: "user-myplan",
                            name: "user-myplan",
                            component: () => import("@/components/user/components/UserMyplan.vue"),
                            beforeEnter: onlyAuthUser,
                        },
                        {
                            path: "user-board",
                            name: "user-board",
                            component: () => import("@/components/user/components/UserBoardList.vue"),
                            beforeEnter: onlyAuthUser,
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
                            component: () => import("@/components/user/components/UserInfoUpdate.vue"),
                            beforeEnter: onlyAuthUser,
                        }
                    ],
                },
            ],
        },
        {
            path: "/",
            name: "main",
            component: () => import("../views/MainView.vue"),
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
    ],
});

export default router;
