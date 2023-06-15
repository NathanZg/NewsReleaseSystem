<template>
  <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
    <el-menu-item><a href="/" style="text-decoration:none;">新闻首页</a></el-menu-item>
    <div class="flex-grow" />
    <el-menu-item>
      <el-avatar :src="'https://api.multiavatar.com/' + userStore.name + '.png'" />
      <el-sub-menu index="2-4">
        <template #title>{{ userStore.name }}</template>
        <el-menu-item @click="() => { router.push('/') }">新闻首页</el-menu-item>
        <el-menu-item @click="() => { router.push('/console') }" v-if="userStore.role == '0' || userStore.role == '1'">后台管理</el-menu-item>
        <el-menu-item @click="logOut">退出登陆</el-menu-item>
      </el-sub-menu>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import router from '@/router';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore()
function logOut() {
  userStore.reSet()
  router.push('/login')
}
</script>
<style>
.flex-grow {
  flex-grow: 1;
}
</style>
