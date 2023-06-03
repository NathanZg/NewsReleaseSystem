<template>
  <el-row class="tac">
    <el-col :span="24">
      <h5 class="mb-2" align="center">新闻分类</h5>
      <el-menu default-active="0" @select="handleSelect" class="el-menu-vertical-demo">
        <el-menu-item index='0'>
          <el-icon><icon-menu /></el-icon>
          <span>全部新闻</span>
        </el-menu-item>
        <el-menu-item v-for="t in types" :index="String(t.id)">
          <el-icon><icon-menu /></el-icon>
          <span>{{ t.type }}</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
  
<script lang="ts" setup>
import { typeSelect } from '@/api/type';
import { onMounted, ref } from 'vue'
import {
  Menu as IconMenu,
} from '@element-plus/icons-vue'
const prop = defineProps({
  typeList: {
    type: Object,
    default: ref([])
  }
})
const emit = defineEmits(
  [
    'clickType'
  ]
);
const types = ref<any>([])
function getTypes() {
  typeSelect().then((res) => {
    types.value = res.data.data
  })
}
function handleSelect(key: string, keyPath: string[]) {
  emit('clickType', key)
}
onMounted(() => {
  getTypes()
})
</script>
  