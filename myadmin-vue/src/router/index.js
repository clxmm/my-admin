import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import User from "@/views/sys/User"
import Menu from  "@/views/sys/Menu"
import Role from "@/views/sys/Role"
import UserCenter from "@/views/user/UserCenter";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/index',
        name: 'Index',
        component: () => import('../views/Index')
      },
      {
        path: '/user',
        name: 'User',
        component: User
      },
      {
        path: '/menu',
        name: 'Menu',
        component: Menu
      },
      {
        path: '/role',
        name: 'Role',
        component: Role
      },
      {
        path: '/userCenter',
        name: 'UserCenter',
        component: UserCenter
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login')
  },

]

const router = new VueRouter({
  routes
})

export default router
