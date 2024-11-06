<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import Sidebar from "./components/Sidebar.vue";
import HomeView from "./views/HomeView.vue";
</script>

<template>
  <div class="flex p-5">
    <Sidebar />

    <div
      class="flex flex-col flex-1 min-w-0 h-screen overflow-hidden p-5 text-black m-5"
    >
      <RouterView />
    </div>
  </div>
</template>
