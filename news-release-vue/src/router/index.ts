import { createRouter, createWebHistory } from 'vue-router'
import layoutVue from '@/components/layout.vue'
import newsDetailVue from '@/views/newsDetail.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'layoutVue',
      component: layoutVue
    },
    {
      path: '/detail',
      name: 'detail',
      component: newsDetailVue
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
