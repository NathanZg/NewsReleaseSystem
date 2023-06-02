<template>
    <!-- 查询表单 -->
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <div>
            <el-form-item label="编号">
                <el-input v-model="formInline.id" placeholder="编号" style="width: 150px;" />
            </el-form-item>
            <el-form-item label="标题">
                <el-input v-model="formInline.title" placeholder="标题"/>
            </el-form-item>
            <el-form-item label="编辑">
                <el-input v-model="formInline.publisher" placeholder="编辑" style="width: 200px;" />
            </el-form-item>
            <el-form-item label="类型">
                <el-select v-model="formInline.type" placeholder="请选择类型">
                    <el-option v-for="data in typeData.data" :key="data.id" :value="data.type" :label="data.type" />
                </el-select>
            </el-form-item>
            <el-form-item label="起始日期">
                <el-date-picker v-model="formInline.startDate" type="datetime" placeholder="选择日期" size="default"/>
            </el-form-item>
            <el-form-item label="截至日期">
                <el-date-picker v-model="formInline.endDate" type="datetime" placeholder="选择日期" size="default"/>
            </el-form-item>
        </div>
        <div class="control">
            <div class="control-add">
                <el-popconfirm title="确定删除所选新闻信息吗？" @confirm="toggleSelection">
                    <template #reference>
                        <el-button type="primary" slot="reference">
                            -&ensp;删除所选新闻
                        </el-button>
                    </template>
                </el-popconfirm>

                <!-- 新增操作 -->
                <el-button type="primary" @click="add" style="margin-left: 40px;">
                    +&ensp;发布新闻
                </el-button>
            </div>
            <div style="width: 650px;"></div>
            <div class="control-form">
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="info" @click="resetForm">重置</el-button>
                </el-form-item>
            </div>

        </div>
    </el-form>

    <!-- 表格 -->
    <el-table :data="newsPageVo.records" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="编号" width="180" />
        <el-table-column prop="title" label="标题" width="180" />
        <!-- <el-table-column prop="data" label="内容" width="180" /> -->
        <el-table-column prop="publisher" label="编辑" width="180" />
        <el-table-column prop="type" label="类型" width="180" />
        <el-table-column prop="date" label="发布日期" />
        <el-table-column fixed="right" label="Operations" width="120">
            <template #default="scope">
                <el-button link type="primary" size="small" @click="handleClick(scope.row)">详情</el-button>
                <el-button link type="primary" size="small" @click="editClick(scope.row)">修改</el-button>
                <el-button link type="primary" size="small" @click="commentMan(scope.row.id)">评论管理</el-button>
            </template>
        </el-table-column>
    </el-table>

    <!-- 分页 -->
    <!-- <el-pagination background layout="sizes, prev, pager, next" :total="total" :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" @size-change="handleSizeChange"
        @current-change="handleCurrentChange"></el-pagination> -->
    <div class="Pagination" style="text-align: left;margin-top: 10px;">
        <el-pagination background layout="prev, pager, next" @prev-click="prevClick" @next-click="nextClick"
            @current-change="currentChange" :total="newsPageVo.total" :page-count="newsPageVo.pages" />
    </div>

    <!-- 发布新闻对话框 -->
    <el-dialog v-model="addNews" align-center title="发布新闻" :with-header="false" :before-close="handleClose"
        class="demo-drawer">
        <div class="demo-drawer__content">
            <el-form :model="addForm">
                <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="addForm.title" autocomplete="off" style="width:fit-content;" />
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <my-editor :data="addForm" />
                    <!-- <el-input v-model="form.data" autocomplete="off" /> -->
                </el-form-item>
                <el-form-item label="类型" :label-width="formLabelWidth">
                    <el-select v-model="addForm.typeId" placeholder="请选择类型">
                        <el-option v-for="data in typeData.data" :key="data.id" :value="data.id" :label="data.type"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="日期" :label-width="formLabelWidth">
                    <el-date-picker v-model="addForm.date" type="datetime" placeholder="选择日期" size="default" />
                </el-form-item>

            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="cancelForm">Cancel</el-button>
                <el-button type="primary" :loading="loading" @click="addClick">{{
                    loading ? 'Submitting ...' : 'Submit'
                }}</el-button>
            </div>
        </div>
    </el-dialog>

    <!-- 修改对话框 -->
    <el-dialog v-model="editNew" title="修改新闻信息" :before-close="handleClose" class="demo-drawer">
        <div class="demo-drawer__content">
            <el-form :model="editForm">
                <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="editForm.title" autocomplete="off" style="width:fit-content;" />
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <my-editor :data="editForm" />
                </el-form-item>
                <el-form-item label="编辑" :label-width="formLabelWidth">
                    <el-input v-model="editForm.publisher" autocomplete="off" style="width:fit-content;" />
                </el-form-item>
                <el-form-item label="类型" :label-width="formLabelWidth">
                    <el-select v-model="editForm.typeId" placeholder="请选择类型">
                        <el-option v-for="data in typeData.data" :key="data.id" :value="data.id" :label="data.type" />
                    </el-select>
                </el-form-item>
                <el-form-item label="日期" :label-width="formLabelWidth">
                    <el-date-picker v-model="editForm.date" type="date" placeholder="选择日期" size="default" />
                </el-form-item>
            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="cancelForm">Cancel</el-button>
                <el-button type="primary" :loading="loading" @click="editConfirm">{{
                    loading ? 'Submitting ...' : 'Submit'
                }}</el-button>
            </div>
        </div>
    </el-dialog>
</template>
  


<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { ElDrawer, ElMessageBox, ElMessage } from 'element-plus'
import { pageQueryByCondition2, newsDelete, newsUpdate, newsAdd } from '@/api/news'
import myEditor from '@/components/myEditor.vue'
import { useRouter } from 'vue-router';
import { typeSelect } from '@/api/type';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore()
const router = useRouter()
onMounted(() => {
    pageSelect({});
    getType();
    // router.push('')
})

function add() {
    addForm.title = null
    addForm.typeId = null
    addForm.date = null
    addForm.data = null
    addNews.value = true
}

//发送查询
function pageSelect(queryVo: any) {
    pageQueryByCondition2(queryVo).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            newsPageVo.current = data.current
            newsPageVo.pages = data.pages
            newsPageVo.records = data.records
            console.log(newsPageVo.records)
            newsPageVo.size = data.size
            newsPageVo.total = data.total
            // news.id = data.records.id
            // news.title = data.records.title
            // news.typeId = data.records.typeId
            // news.publisher = data.records.publisher
            // news.data = data.records.data
            // news.date = data.records.date
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
//发送新增
function addSubmit(queryVo: any) {
    newsAdd(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('添加成功！');
            addNews.value = false;
            pageSelect({});
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
//发送修改
function editSubmit(queryVo: any) {
    newsUpdate(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('修改成功！');
            editNew.value = false;
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
function deleteSubmit(queryVo: any) {
    newsDelete(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('删除成功！');
            pageSelect({});
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
//发送类型查询
function getType() {
    typeSelect().then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            typeData.data = data
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


//选中数据
let selectLine: string | any[] = []

function addClick() {
    addSubmit(addForm)
}
function editConfirm() {
    loading.value = false
    editSubmit(editForm)
}

//提交查询按钮
const onSubmit = () => {
    pageSelect(formInline);
    // console.log(formInline);
}
//重置按钮
const resetForm = () => {
    formInline.startDate = null;
    formInline.endDate = null;
    formInline.id = null;
    formInline.title = null;
    formInline.publisher = '';
    formInline.type = null;
    pageSelect({});
}
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
const commentMan = (id:any) =>{
    router.push('/console/commentManager/'+id)
}
//提交新增表单
// const addCommit = () => {
//     addSubmit(addForm);
//     console.log(addForm);
// }

//详情按钮
const handleClick = (val: any) => {
    router.push('/detail/' + val.id);
}
//修改按钮
const editClick = (val: any) => {
    console.log(val);
    editForm.id = val.id
    editNew.value = true;
    editForm.title = val.title;
    editForm.data = val.data;
    editForm.typeId = val.typeId;
    editForm.publisher = val.publisher;
    editForm.date = val.date;
    console.log(editForm)
}

//查询表单数据
const formInline = reactive({
    id: null,
    title: null,
    publisher: '',
    type: null,
    startDate: null,
    endDate:null
})
//新增表单数据
const addForm = reactive({
    title: null,
    data: null,
    publisher: userStore.name,
    date: null,
    typeId: null
})
//修改表单数据
const editForm = reactive({
    id: null,
    title: null,
    data: "null",
    publisher: "null",
    date: null,
    typeId: "1"
})

//表格数据
const newsPageVo = reactive({
    records: [],
    total: 1,
    size: 10,
    current: 1,
    pages: 1
})
//类型数据
const typeData = reactive({
    data: []
})



const formLabelWidth = '80px'
let timer: any

const editNew = ref(false)   //修改抽屉显示
const loading = ref(false)
const addNews = ref(false)   //新增抽屉显示

//对话框的动画
const drawerRef = ref<InstanceType<typeof ElDrawer>>()
const onClick = () => {
    drawerRef.value!.close()
}

const handleClose = (done: any) => {
    if (loading.value) {
        return
    }
    ElMessageBox.confirm('是否提交？')
        .then(() => {
            loading.value = true
            timer = setTimeout(() => {
                done()
                // 动画关闭需要一定的时间
                setTimeout(() => {
                    loading.value = false
                }, 400)
            }, 2000)
        })
        .catch(() => {
            // catch error
        })
}

const cancelForm = () => {
    loading.value = false
    editNew.value = false
    addNews.value = false
    clearTimeout(timer)
}

// 前一页
function prevClick(value: any) {
    newsPageVo.current = value;
    pageSelect(newsPageVo);
}
// 后一页
function nextClick(value: any) {
    newsPageVo.current = value;
    pageSelect(newsPageVo);
}

// 跳转页码
function currentChange(value: any) {
    newsPageVo.current = value;
    pageSelect(newsPageVo);
}


</script>

<style>
.control {
    display: flex;
}

.control-add {
    float: left;
}

.control-form {
    float: right;
}
</style>
  