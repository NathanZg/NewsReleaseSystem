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
    },
    {
      path: '/console',
      name: 'console',
      component: () => import('@/components/console.vue'),
      children: [
        {
          path: '/console/newsManager',
          name: 'newsManager',
          component: () => import('@/components/newsManager.vue'),
        },
        {
          path: '/console/typeManager',
          name: 'typeManager',
          component: () => import('@/components/typeManager.vue'),
        },
        {
          path: '/console/userManager',
          name: 'userManager',
          component: () => import('@/components/userManager.vue'),
        },
        {
          path: '/console/commentManager/:id',
          name: 'commentManager',
          component: () => import('@/components/commentManager.vue'),
        }
      ]
    },
  ]
})

export default router
