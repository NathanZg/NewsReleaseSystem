import { createRouter, createWebHistory } from 'vue-router'
import layoutVue from '@/components/layout.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'layoutVue',
      component: layoutVue
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login.vue')
    },
    {
      path: '/detail/:id',
      name: 'detail',
      component: () => import('@/views/newsDetail.vue')
    }
  ]
})

export default router
