<template>
    <div class="control-add">
        <el-popconfirm title="确定删除所选类型信息及该类型下的所有新闻吗？" @confirm="toggleSelection">
            <template #reference>
                <el-button type="primary" slot="reference">
                    -&ensp;删除类型
                </el-button>
            </template>
        </el-popconfirm>

        <!-- 新增操作 -->
        <el-button type="primary" @click="addType = true" style="margin-left: 40px;">
            +&ensp;新增类型
        </el-button>
    </div>
    <br><br>
    <div style="margin-left: 10px;">
    <!-- 表格 -->
    <el-table :data="typeData" style="width: 50%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="100" />
        <el-table-column prop="type" label="类型" width="200" />
    </el-table>
    </div>

    <el-dialog v-model="addType" align-center title="新增类型" :with-header="false" :before-close="handleClose"
        class="demo-drawer">
        <div class="demo-drawer__content">
            <el-form :model="addForm">
                <el-form-item label="类型" :label-width="formLabelWidth">
                    <el-input v-model="addForm.type" autocomplete="off" style="width:fit-content;" />
                </el-form-item>
            </el-form>
            <div class="demo-drawer__footer">
                <el-button @click="cancelForm">取消</el-button>
                <el-button type="primary" :loading="loading" @click="addClick">{{
                    loading ? 'Submitting ...' : '提交'
                }}</el-button>
            </div>
        </div>
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { ElDrawer, ElMessageBox, ElMessage } from 'element-plus'
import { typeAdd, typeDelete, typeSelect } from '@/api/type';

onMounted(() => {
    typeShow();
    // router.push('')
})
//发送查询
function typeShow() {
    typeSelect().then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            typeData.value = data;
            console.log(typeData)
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
    typeAdd(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('添加成功！');
            addType.value = false;
            typeShow();
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
    typeDelete(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('删除成功！');
            typeShow();
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

const formLabelWidth = '80px'
let timer: any
const addType = ref(false)  //显示新增界面
const loading = ref(false)

const typeData = ref([])   //表格数据
const addForm = reactive({
    id:null,
    type: null
})

let selectLine: string | any[] = []     //选中数据


function addClick() {
    addSubmit(addForm);
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
const cancelForm = () => {
    addType.value = false;
    loading.value = false;
    clearTimeout(timer)
}
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
</script>

<style>
.demo-drawer__content {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
}
</style>
