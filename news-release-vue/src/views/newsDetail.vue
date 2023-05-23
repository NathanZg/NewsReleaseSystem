<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <TopHeader class="el-header" />
            </el-header>
            <el-main>
                <el-row>
                    <el-col :span="3">
                        <!-- <el-input></el-input> -->
                    </el-col>
                    <el-col :span="18">
                        <el-card class="box-card">
                            <template #header>
                                <div class="card-header">
                                    <span>Card name</span>
                                    <h1>Card name</h1>
                                    <el-button class="button" text>Operation button</el-button>
                                </div>
                            </template>
                            <div class="text item"></div>
                        </el-card>
                    </el-col>
                    <el-col :span="3">
                        <!-- <el-input></el-input> -->
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>
<script lang="ts" setup>
import TopHeader from '@/components/header.vue';
import { ref } from 'vue'
</script>
<style scoped>
.el-header {
    --el-header-padding: 0 0px;
    --el-header-height: 60px;
    padding: var(--el-header-padding);
    box-sizing: border-box;
    flex-shrink: 0;
    height: var(--el-header-height);
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>