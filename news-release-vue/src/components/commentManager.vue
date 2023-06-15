<template>
    <div>
        <el-button type="primary" :icon="ArrowLeft" @click="goback">返回</el-button>
        <el-popconfirm title="确定删除所选评论信息吗？" @confirm="toggleSelection">
            <template #reference>
                <el-button type="primary" slot="reference">
                    -&ensp;删除评论
                </el-button>
            </template>
        </el-popconfirm>
    </div>
    <!-- 表格 -->
    <el-table :data="tableData.records" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="编号" width="180" />
        <el-table-column prop="user" label="用户" width="180" />
        <el-table-column prop="commentData" label="评论内容" />
        <el-table-column prop="commentDate" label="评论时间" width="180" />
    </el-table>
    <!-- 分页 -->
    <div class="Pagination" style="text-align: left;margin-top: 10px;">
        <el-pagination background layout="prev, pager, next" @prev-click="prevClick" @next-click="nextClick"
            @current-change="currentChange" :total="tableData.total" :page-count="tableData.pages" />
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { ElDrawer, ElMessageBox, ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
import { pageQueryCommentByCondition, commentDelete } from '@/api/comment';
import { ArrowLeft } from '@element-plus/icons-vue'

onMounted(() => {
    commentShow(commentQueryVo);
    // router.push('')
})
//发送查询
function commentShow(pageQueryVo: any) {
    pageQueryCommentByCondition(pageQueryVo).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            tableData.current = data.current
            tableData.pages = data.pages
            tableData.records = data.records
            tableData.size = data.size
            tableData.total = data.total
            console.log(tableData)
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

//发送删除
function deleteSubmit(ids: any) {
    commentDelete(ids).then((res) => {
        if (res.data.code == 200) {
            ElMessage('删除成功！');
            commentShow(commentQueryVo);
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

const router = useRouter()

const tableData = reactive({  //表格数据
    records: [],
    total: 1,
    size: 10,
    current: 1,
    pages: 1
})
const commentQueryVo = reactive({   //评论分页
    id: null,
    user: null,
    commentData: null,
    newsId: router.currentRoute.value.params.id,
    startDate: null,
    endDate: null,
    current: 1,
    size: 10
})
const news = reactive({
    newsId: null
})


let selectLine: string | any[] = []     //选中数据


//处理选中项
const handleSelectionChange = (val: []) => {
    var i;
    var arr = Object.keys(val);
    for (i = 0; i < arr.length; i++) {
        selectLine[i] = val[i].id;
    }
    //   console.log(selectLine);
}
//删除按钮
const toggleSelection = () => {
    if (selectLine) {
        let str = JSON.stringify(selectLine);
        //移除字符串中的所有[]括号（不包括其内容）
        let newStr = str.replace(/\[|]/g, '');
        deleteSubmit(newStr);
        // console.log(newStr);
    }
}
const goback = () => {
    console.log(123)
    router.push('/console/newsManager')
}


// 前一页
function prevClick(value: any) {
    commentQueryVo.current = value;
    commentShow(commentQueryVo);
}
// 后一页
function nextClick(value: any) {
    commentQueryVo.current = value;
    commentShow(commentQueryVo);
}

// 跳转页码
function currentChange(value: any) {
    commentQueryVo.current = value;
    commentShow(commentQueryVo);
}
</script>
