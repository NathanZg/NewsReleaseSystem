<template>
    <el-menu
      class="el-menu-vertical-demo"
      :router="true"
    >
      <el-menu-item index="/console/newsManager">
        <el-icon><List /></el-icon>
        <template #title>新闻信息</template>
      </el-menu-item>
      <el-menu-item index="/console/typeManager">
        <el-icon><FolderAdd /></el-icon>
        <template #title>类型信息</template>
      </el-menu-item>
      <el-menu-item index="/console/userManager">
        <el-icon><FolderAdd /></el-icon>
        <template #title>用户信息</template>
      </el-menu-item>
    </el-menu>
  </template>

  <script lang="ts" setup>
  import {Document, Menu as IconMenu,} from '@element-plus/icons-vue'</script>

  <style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  </style>