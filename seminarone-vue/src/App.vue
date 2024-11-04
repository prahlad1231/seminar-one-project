<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import Sidebar from "./components/Sidebar.vue";
import Content from "./components/Content.vue";
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <!-- <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink> -->
      </nav>
    </div>
  </header>

  <div class="flex">
    <Sidebar />

    <div
      class="flex flex-col flex-1 min-w-0 h-screen overflow-hidden p-5 bg-slate-900 text-white"
    >
      <Content msg="Content" />
    </div>
  </div>
  <!-- <RouterView /> -->
</template>
