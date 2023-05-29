<template>
    <ElForm class="login-form" ref="loginRef" :model="loginParam" :rules="loginRules">
        <h1 class="login-title">登录</h1>
        <ElFormItem prop="username">
            <ElInput placeholder="请输入账号" :prefix-icon="User" v-model="loginParam.name" size="large"></ElInput>
        </ElFormItem>
        <ElFormItem prop="password">
            <ElInput placeholder="请输入密码" show-password :prefix-icon="Lock" v-model="loginParam.password" size="large">
            </ElInput>
        </ElFormItem>
        <ElFormItem>
            <ElButton type="primary" class="login-btn" size="large" @click="submit(loginRef)">登录</ElButton>
        </ElFormItem>
    </ElForm>
</template>

<script setup lang="ts">
import { User, Lock } from '@element-plus/icons-vue';
import { type LoginReq } from '@/interface/user'
import { reactive, ref } from 'vue'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus';
import { userLogin } from '@/api/user'
import {useUserStore} from '@/stores/user'
import { useRouter } from 'vue-router';
const loginParam: LoginReq = reactive({
    name: "",
    password: ""
})
const userStore = useUserStore()
const router = useRouter()
const loginRef = ref<FormInstance>()
const loginRules: FormRules = reactive({
    name: [{ required: true, message: "账号不能为空", trigger: 'blur' }],
    password: [{ required: true, message: "密码不能为空", trigger: 'blur' }]
})
const submit = (formEl: FormInstance | undefined) => {
    if (!formEl) {
        return false
    }
    formEl.validate(async (validate: boolean) => {
        if (validate) {
            userLogin(loginParam).then((res) => {
                var user = res.data.data
                if (res.data.code == 200) {
                    userStore.setId(user.id)
                    userStore.setName(user.name)
                    userStore.setPassword(user.password)
                    userStore.setRole(user.role)
                    ElMessage.success(res.data.msg)
                    router.push('/')
                } else {
                    ElMessage.error(res.data.msg)
                }
            })
        } else {
            return false;
        }
    })
}
</script>

<style scoped>
.login-form {
    grid-column: 1;
    grid-row: 1;
    opacity: 1;
    transition: 1s ease-in-out;
    transition-delay: 0.5s;
    /* 上下 | 左右 */
    padding: 1% 25%;
    z-index: 1;
}

.login-form.sign-up-model {
    opacity: 0;
    transition: 1s ease-in-out;
    z-index: 0;
}

.login-title {
    text-align: center;
    color: #444;
}

.login-btn {
    width: 100%;
    font-size: 18px;
}</style>
