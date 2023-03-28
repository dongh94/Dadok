import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: () => import("@/views/HomeView.vue"),
  },
  {
    path: "/makegroup",
    name: "MakeGroupView",
    component: () => import("@/views/MakeGroupView.vue"),
  },
  {
    path: "/start",
    name: "StartView",
    component: () => import("@/views/StartView.vue"),
  },
  {
    path: "/searchgroup",
    name: "SearchGroupView",
    component: () => import("@/views/SearchGroupView.vue"),
  },
  {
    path: "/searchgroup/:meetingId",
    name: "SearchGroupDetail",
    component: () => import("@/components/searchgroup/SearchGroupDetail.vue"),
  },
  //마이페이지
  {
    path: "/mypage",
    name: "MyPageView",
    component: () => import("@/views/MyPageView.vue"),
  },

  {
    path: "/mypage/leadgroup",
    name: "LeadGroupView",
    component: () => import("@/views/LeadGroupView.vue"),
  },
  {
    path: "/mypage/followgroup",
    name: "FollowGroupView",
    component: () => import("@/views/FollowGroupView.vue"),
  },
  {
    path: "/mypage/library/1",
    name: "MypageLibraryEx",
    component: () => import("@/views/MypageLibraryEx.vue"),
  },
  {
    path: "/mypage/library1",
    name: "MyLibrary1View",
    component: () => import("@/views/MyLibrary1View.vue"),
  },
  {
    path: "/mypage/library2",
    name: "MyLibrary2View",
    component: () => import("@/views/MyLibrary2View.vue"),
  },
  {
    path: "/video/room",
    name: "VideoView",
    component: () => import("@/views/VideoView.vue"),
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
