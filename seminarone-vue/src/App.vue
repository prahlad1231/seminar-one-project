<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import Sidebar from "./components/Sidebar.vue";
import Login from "./views/Login.vue";

const authenticated = true;
</script>

<template>
  <div v-if="authenticated">
    <div class="flex p-5">
      <Sidebar />

      <div
        class="flex flex-col flex-1 min-w-0 h-screen overflow-hidden p-5 text-black m-5"
      >
        <RouterView />
      </div>
    </div>
  </div>
  <div v-else>
    <Login />
  </div>
</template>
