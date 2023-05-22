<template>
  <el-row class="tac">
    <el-col :span="24">
      <h5 class="mb-2">新闻分类</h5>
      <el-menu default-active="2" class="el-menu-vertical-demo">
        <el-menu-item v-for="t in types" index="2">
          <el-icon><icon-menu /></el-icon>
          <span>{{ t.type}}</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
  
<script lang="ts" setup>
import { typeSelect } from '@/api/type';
import {onMounted, ref} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
const prop = defineProps({
  typeList: {
    type: Object,
    default: ref([])
  }
})
const types = ref<any>([])
function getTypes() {
  typeSelect().then((res)=> {
    types.value = res.data.data
  })
}
onMounted(() => {
  getTypes()
})
</script>
  