<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <TopHeader class="el-header" />
            </el-header>
            <el-main>
                <el-row>
                    <el-col :span="3">
                    </el-col>
                    <el-col :span="18">
                        <el-card class="box-card">
                            <template #header>
                                <div class="card-header">
                                    <span>发布人：{{ news.publisher }}</span>
                                    <h1>{{ news.title }}</h1>
                                    <span>发布时间：{{ news.date }}</span>
                                </div>
                            </template>
                            <div class="imgDiv">
                                <img src="https://api.biuioi.com/random-picture/index.php" />
                            </div>
                            <div class="text item" v-html="news.data">
                            </div>
                        </el-card>
                        <div class="text item">
                            <div style="padding: 10px;">
                                <span>评论</span>
                                <div v-for="comment in commentPageVo.records" class="comment-info-root">
                                    <div class="comment-user-info">
                                        <div>
                                            <img class="comment-ic" :src="'https://api.multiavatar.com/' + comment.user + '.png'">
                                            <span style="cursor: default">{{ comment.user }}</span>
                                        </div>
                                    </div>
                                    <div class="conment-detail">
                                        <span>{{ comment.commentData }}</span>
                                    </div>
                                    <span class="comment-date">
                                        {{ comment.commentDate }}
                                    </span>
                                </div>
                            </div>
                            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                                <el-pagination background layout="prev, pager, next" @prev-click="prevClick"
                                    @next-click="nextClick" @current-change="currentChange" :total="commentPageVo.total"
                                    :page-count="commentPageVo.pages"/>
                            </div>
                            <div class="repaly-root">
                                <el-input v-model="comment.commentData" type="textarea"
                                    :autosize="{ minRows: 5, maxRows: 20 }" placeholder="请输入内容" style="width: 700px;">
                                </el-input>
                                <el-button @click="addComment" type="primary" size="small" style="margin-left: 10px;">发布</el-button>
                            </div>
                        </div>
                    </el-col>
                    <el-col :span="3">
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>
<script lang="ts" setup>
import TopHeader from '@/components/header.vue'
import { ref, reactive, onMounted,onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { getNewsDetailById } from '@/api/news'
import { pageQueryCommentByCondition, insertComment } from '@/api/comment'
import { ElNotification, ElMessage } from 'element-plus'
import { useUserStore } from '@/stores/user'
const userStore = useUserStore()
const router = useRouter()
const news = reactive({
    id: null,
    title: null,
    data: null,
    publisher: null,
    date: null,
    typeId: null
})
const commentPageVo = reactive({
    records: ref<any>([]),
    total: 1,
    size: 5,
    current: 1,
    pages: 1
})
const commentQueryVo = reactive({
    id: null,
    user: null,
    commentData: null,
    newsId: router.currentRoute.value.params.id,
    startDate: null,
    endDate: null,
    current: 1,
    size: 5
})

const comment = reactive({
    id: null,
    user: userStore.name,
    commentData: null,
    commentDate: null,
    newsId: router.currentRoute.value.params.id
})
function getNewsDetail() {
    var newsId = router.currentRoute.value.params.id;
    getNewsDetailById(newsId).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            news.id = data.news.id
            news.title = data.news.title
            news.typeId = data.news.typeId
            news.publisher = data.news.publisher
            news.data = data.news.data
            news.date = data.news.date
            commentPageVo.current = data.commentPageVo.current
            commentPageVo.pages = data.commentPageVo.pages
            commentPageVo.records = data.commentPageVo.records
            commentPageVo.size = data.commentPageVo.size
            commentPageVo.total = data.commentPageVo.total
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
function getComment(commentQueryVo: object) {
    pageQueryCommentByCondition(commentQueryVo).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            commentPageVo.current = data.current
            commentPageVo.pages = data.pages
            commentPageVo.records = data.records
            commentPageVo.size = data.size
            commentPageVo.total = data.total
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
    commentQueryVo.current = value
    getComment(commentQueryVo)
}
// 后一页
function nextClick(value: any) {
    commentQueryVo.current = value
    getComment(commentQueryVo)
}

// 跳转页码
function currentChange(value: any) {
    commentQueryVo.current = value
    getComment(commentQueryVo)
}

function addComment() {
    insertComment(comment).then((res) => {
        if (res.data.code == 200) {
            comment.commentData = null
            getComment(commentQueryVo)
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
onBeforeMount(() => {
    if(userStore.id == -1) {
        ElMessage.info('请先注册/登陆！')
        router.push('/login')
    }
})
onMounted(() => {
    getNewsDetail()
})
</script>
<style lang="scss" scoped>
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

.imgDiv {
    width: 1065px;
    height: 400px;
    border: 1px solid black;
}

.imgDiv img {
    width: 100%;
    height: 100%;
}

.comment-info-root {
    border-bottom: 1px solid #f0f0f0;
    padding: 10px 0;
}

.repaly-root {
    margin-top: 10px;
}

.comment-ic {
    width: 30px;
    height: 30px;
    border-radius: 10%;
}

.comment-user-info {
    display: flex;
    justify-content: space-between;
    align-items: center;

    div {
        display: flex;
        align-items: center;
        padding: 10px 0;

        span {
            margin-left: 10px;
            font-size: 14px;
            color: #555;
        }
    }
}

.comment-date {
    font-size: 13px;
    color: #bbb;
    padding-left: 40px;
}

.conment-detail {
    font-size: 14px;
    color: #333;
    padding: 10px 40px;
}
</style>