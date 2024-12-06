import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'base',
      component: () => import('../views/HomePage.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login.vue'),
    },
    {
        path: '/home/:userName/:password',
        name: 'home',
        component: () => import('../views/HomeafterLo.vue')//这里写具体哪个组件
    },
    {
      path: '/personal',
      name: 'personal',
      component: () => import('../views/Personal.vue')//这里写具体哪个组件
    },
  ],
})

export default router
