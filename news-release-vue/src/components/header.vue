<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
  >
    <el-menu-item><a href="/">新闻首页</a></el-menu-item>
    <div class="flex-grow" />
    <el-menu-item>个人中心</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>
