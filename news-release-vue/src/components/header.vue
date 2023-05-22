<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
  >
    <el-menu-item>LOGO</el-menu-item>
    <div class="flex-grow" />
    <el-menu-item>Processing Center</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>
