<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
  >
    <el-menu-item><a href="/" style="text-decoration:none;">新闻首页</a></el-menu-item>
    <div class="flex-grow" />
    <el-menu-item>{{ userStore.password }}</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
const userStore = useUserStore()
</script>
<style>
.flex-grow {
  flex-grow: 1;
}
</style>
