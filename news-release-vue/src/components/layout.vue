<template>
    <div class="common-layout">
        <el-container>
            <el-header class="el-header">
                <TopHeader />
            </el-header>
            <el-main class="el-main">
                <el-container>
                    <el-aside>
                        <TypeSideBar @clickType="getNewsListByType" />
                    </el-aside>
                    <el-main>
                        <NewsList :newsList="pageVo.records" />
                        <el-pagination background layout="prev, pager, next" @prev-click="prevClick" @next-click="nextClick"
                            @current-change="currentChange" :total="pageVo.total" :page-count="pageVo.pages" />
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
import { pageQueryByCondition2 } from '@/api/news';
import { ref, reactive, onMounted, onBeforeMount } from 'vue';
import { ElNotification, ElMessage } from 'element-plus';
import { useUserStore } from '@/stores/user';
import router from '@/router';
const userStore = useUserStore()
// 查询条件
const queryVo = reactive({
    id: null,
    typeId: null,
    title: null,
    data: null,
    publisher: null,
    startDate: null,
    endDate: null,
    current: null,
    size: null
})
// 分页查询结果
const pageVo = reactive({
    records: [],
    total: 0,
    size: 5,
    current: 1,
    pages: 0
})
function getNewsByCondition(queryVo: object) {
    pageQueryByCondition2(queryVo).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            pageVo.records = data.records
            pageVo.total = data.total
            pageVo.size = data.size
            pageVo.current = data.current
            pageVo.pages = data.pages
        } else {
            ElNotification.error({
                title: 'error',
                message: res.data.msg,
                offset: 100
            })
        }
    }).catch((error) => {
        console.log(error)
        ElNotification.error({
            title: 'error',
            message: error,
            offset: 100
        })
    })
}
// 前一页
function prevClick(value: any) {
    queryVo.current = value
    getNewsByCondition(queryVo)
}
// 后一页
function nextClick(value: any) {
    queryVo.current = value
    getNewsByCondition(queryVo)
}

// 跳转页码
function currentChange(value: any) {
    queryVo.current = value
    getNewsByCondition(queryVo)
}
function getNewsListByType(id: any) {
    if (id == '0') {
        queryVo.typeId = null
    } else {
        queryVo.typeId = id
    }
    getNewsByCondition(queryVo)
}
onBeforeMount(() => {
    if(userStore.id == -1) {
        ElMessage.info('请先注册/登陆！')
        router.push('login')
    }
})
onMounted(() => {
    getNewsByCondition(queryVo)
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
</style>