<template>
    <div class="common-layout">
        <el-container>
            <el-header class="el-header">
                <TopHeader />
            </el-header>
            <el-main class="el-main">
                <el-container>
                    <el-aside>
                        <Sidebar/>
                    </el-aside>
                    <el-main>
                        <RouterView></RouterView>
                    </el-main>
                </el-container> 
            </el-main>
        </el-container>
    </div>
</template>
<script setup lang="ts">
import Sidebar from '@/components/sidebar.vue';
import TopHeader from '@/components/b_header.vue';
import { useUserStore } from '@/stores/user';
import { onBeforeMount } from 'vue';
import router from '@/router';
import {ElMessage } from 'element-plus';
const userStore = useUserStore()
onBeforeMount(() => {
    if(userStore.id == -1) {
        ElMessage.info('请先注册/登陆！')
        router.push('login')
    } else if (userStore.role == '2') {
        ElMessage.error('您的权限不足！')
        router.push('/')
    }
})
</script>