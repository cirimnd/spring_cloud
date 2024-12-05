import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../views/login.vue'),
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('../views/HomePage.vue')//这里写具体哪个组件
    },
  ],
})

export default router
