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
                                    <span></span>
                                    <h1>{{ newsDetail.news.title }}</h1>
                                    <span>发布时间：{{ newsDetail.news.date }}</span>
                                </div>
                            </template>
                            <div class="imgDiv">
                                <img src="https://api.ghser.com/random/fengjing.php" />
                            </div>
                            <div class="text item" v-html="newsDetail.news.data">
                            </div>
                        </el-card>
                        <div class="text item">
                            <div style="padding: 10px;">
                                <span>pinglun</span>
                                <div class="comment-info-root">
                                    <div class="comment-user-info">
                                        <div>
                                            <img class="comment-ic" src="https://api.biuioi.com/random-picture/index.php">
                                            <span style="cursor: default">昵称</span>
                                        </div>
                                    </div>
                                    <div class="conment-detail">
                                        <span>评论</span>
                                    </div>
                                    <span class="comment-date">
                                        发布日期
                                    </span>
                                </div>
                            </div>
                            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                                <el-pagination background layout="total, prev, pager, next" :total="10">
                                </el-pagination>
                            </div>
                            <div class="repaly-root">
                                <el-input type="textarea" :autosize="{ minRows: 1, maxRows: 20 }" placeholder="请输入内容"
                                    style="width: 700px;">
                                </el-input>
                                <el-button type="primary" size="small" style="margin-left: 10px;">发布</el-button>
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
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getNewsDetailById } from '@/api/news'
const router = useRouter()
const newsDetail = reactive({
    news: {
        id: null,
        title: null,
        data: null,
        publisher: null,
        date: null,
        typeId: null
    },
    commentList: null
})
function getNewsDetail() {
    var newsId = router.currentRoute.value.params.id;
    getNewsDetailById(newsId).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            newsDetail.news = data.news
            newsDetail.commentList = data.commentList
            console.log(newsDetail)
        } else {
            ElNotification.error({
                title: 'error',
                message: res.data.msg,
                offset: 100
            })
        }
    })
}
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