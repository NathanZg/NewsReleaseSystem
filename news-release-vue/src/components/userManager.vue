<template>
    <div class="control-add">
        <!-- 新增操作 -->
        <el-button type="primary" @click="addUser = true">
            +&ensp;新增用户
        </el-button>
    </div>
    <!-- 表格 -->
    <el-table :data="userData" style="width: 100%">
        <el-table-column prop="name" label="用户名" width="400" />
        <el-table-column prop="role" label="权限" width="300">
            <template #default="{ row }">
                <span v-if="row.role == 0">超级管理员</span>
                <span v-else-if="row.role == 1">管理员</span>
                <span v-else-if="row.role == 2">用户</span>
            </template>
        </el-table-column>
        <el-table-column fixed="right" label="Operations" width="400">
            <template #default="scope">
                <el-button link type="primary" size="small"   @click="beManager(scope.row)" v-if="scope.row.role == '2' && userStore.role == '0'">设置为管理员</el-button>
                <el-popconfirm title="确定删除所选用户信息吗？" @confirm="deleteClick(scope.row)" v-if="scope.row.role >= userStore.role">
                    <template #reference>
                        <el-button link type="primary" size="small">删除</el-button>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>

    <el-dialog v-model="addUser" align-center title="新增用户" :with-header="false" :before-close="handleClose"
        class="demo-drawer">
        <div class="demo-drawer__content">
            <el-form :model="addForm">
                <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="addForm.name" autocomplete="off" style="width:fit-content;" />
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="addForm.password" autocomplete="off" style="width:fit-content;" />
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
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { ElDrawer, ElMessageBox, ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
import { userSelect, userAdd, userDelete,configManager } from '@/api/user';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore()
onMounted(() => {
    addShow({});
    // router.push('')
})
//发送查询
function addShow(val: any) {
    userSelect(val).then((res) => {
        var data = res.data.data
        if (res.data.code == 200) {
            userData.value = data;
            console.log(userData)
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
    userAdd(queryVo).then((res) => {
        if (res.data.code == 200) {
            ElMessage('添加成功！');
            addUser.value = false;
            addShow({});
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
function deleteSubmit(id: any) {
    userDelete(id).then((res) => {
        if (res.data.code == 200) {
            ElMessage('删除成功！');
            addShow({});
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
//发送设置管理员
function setSubmit(user: any) {
    configManager(user).then((res) => {
        if (res.data.code == 200) {
            ElMessage('更改成功！');
            addShow({});
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
const addUser = ref(false)  //显示新增界面
const loading = ref(false)

const userData = ref([])   //表格数据
const addForm = reactive({
    id: null,
    name: null,
    password: null,
    role: 2
})
const setData = reactive({
    name: null,
    role: 1
})


function addClick() {
    addSubmit(addForm);
}
function deleteClick(val: any) {
    deleteSubmit(val.id);
}

const cancelForm = () => {
    addUser.value = false;
    loading.value = false;
    clearTimeout(timer)
}
//设置为管理员
const beManager = (val: any) => {
    setData.name = val.name;
    setSubmit(setData);
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
