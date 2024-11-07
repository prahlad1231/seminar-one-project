import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Settings from "@/views/Settings.vue";
import TopicList from "@/views/TopicList.vue";
import LocationList from "@/views/LocationList.vue";
import SeminarList from "@/views/SeminarList.vue";
import Login from "@/views/Login.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: HomeView,
    },
    {
      path: "/topicList",
      name: "topicList",
      component: TopicList,
    },
    {
      path: "/locationList",
      name: "locationList",
      component: LocationList,
    },
    {
      path: "/seminarList",
      name: "seminarList",
      component: SeminarList,
    },
    {
      path: "/settings",
      name: "settings",
      // component: () => import("../views/AboutView.vue"), // it's one way to do it
      component: Settings,
    },
  ],
});

export default router;
