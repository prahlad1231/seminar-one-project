<script setup lang="ts">
import type { MenuItem } from "@/types";

const menuItems: MenuItem[] = [
  {
    name: "Dashboard",
    url: "/dashboard",
  },
  {
    name: "Topic List",
    url: "/topicList",
  },
  {
    name: "Location List",
    url: "/locationList",
  },
  {
    name: "Seminar List",
    url: "/seminarList",
  },
  {
    name: "Settings",
    url: "/settings",
  },
];
</script>

<template>
  <div class="space-y-5">
    <h1 class="text-4xl">Seminar One</h1>
    <nav>
      <ul>
        <li v-for="(item, index) in menuItems" :key="index" class="mb-4">
          <RouterLink :to="item.url" class="text-lg hover:underline">{{
            item.name
          }}</RouterLink>
        </li>
      </ul>
    </nav>
  </div>
</template>
