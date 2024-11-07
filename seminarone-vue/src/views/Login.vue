<script setup lang="ts">
import { ref } from "vue";

const username = ref("");
const password = ref("");
// const rememberMe = ref(false);

const handleSubmit = () => {
  // Logic to handle login can go here.
  console.log("username:", username.value);
  console.log("Password:", password.value);
  //   console.log("Remember Me:", rememberMe.value);
};
</script>

<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="w-full max-w-md p-8 space-y-8 bg-white rounded-lg shadow-lg">
      <h2 class="text-2xl font-semibold text-center text-gray-800">Login</h2>
      <form @submit.prevent="handleSubmit" class="space-y-6">
        <div>
          <label for="username" class="block text-sm font-medium text-gray-600">
            Username
          </label>
          <input
            type="username"
            id="username"
            v-model="username"
            required
            placeholder="Enter your username"
            class="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-400 focus:border-transparent"
          />
        </div>
        <div>
          <label for="password" class="block text-sm font-medium text-gray-600">
            Password
          </label>
          <input
            type="password"
            id="password"
            v-model="password"
            required
            placeholder="Enter your password"
            class="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-400 focus:border-transparent"
          />
        </div>
        <div class="flex items-center justify-between">
          <!-- <router-link
            to="/forgot-password"
            class="text-sm text-indigo-600 hover:underline"
          >
            Forgot Password?
          </router-link> -->
        </div>
        <button
          type="submit"
          class="w-full px-4 py-2 text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 focus:outline-none focus:bg-indigo-700"
        >
          Login
        </button>
      </form>
    </div>
  </div>
</template>
