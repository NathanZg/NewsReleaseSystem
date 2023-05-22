<template>
    <div class="common-layout">
        <el-container>
            <el-header class="el-header">
                <TopHeader/>
            </el-header>
            <el-main class="el-main">
                <el-container>
                    <el-aside>
                        <TypeSideBar @clickType="getNewsListByType"/>
                    </el-aside>
                    <el-main>
                        <NewsList :newsList="list"/>
                    </el-main>
                </el-container>
            </el-main>
        </el-container>
    </div>
</template>
<script lang="ts" setup>
import TopHeader from '@/components/header.vue';
import NewsList from '@/components/newslist.vue';
import TypeSideBar from '@/components/typesidebar.vue';
import { newsSelect,getNewsByType } from '@/api/news';
import { ref,reactive, onMounted } from 'vue';
const list = ref([])
function getNews() {
    newsSelect({}).then((res)=>{
        list.value = res.data.data
    })
}
function getNewsListByType(id: string) {
    getNewsByType(id).then((res) => {
        list.value = res.data.data
    })
}
onMounted(() => {
    getNews()
})
</script>
<style scoped>
.flex-grow {
    flex-grow: 1;
}

.el-header {
    --el-header-padding: 0 0px;
    --el-header-height: 60px;
    padding: var(--el-header-padding);
    box-sizing: border-box;
    flex-shrink: 0;
    height: var(--el-header-height);
}

.el-main {
    --el-main-padding: 0px;
    display: block;
    flex: 1;
    flex-basis: auto;
    overflow: auto;
    box-sizing: border-box;
    padding: var(--el-main-padding);
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    width: 480px;
}
</style>