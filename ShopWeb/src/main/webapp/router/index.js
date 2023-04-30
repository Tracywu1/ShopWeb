// 配置路由相关的信息
import VueRouter from 'vue-router'
import Vue from 'vue'

// 1.通过Vue.use(插件), 安装插件
Vue.use(VueRouter)

// 2.创建VueRouter对象
const routes = [
    {
        path: '/emp',
        name: 'emp',
        component: () => import('../views/tlias/EmpView.vue')
    },
    {
        path: '/dept',
        name: 'dept',
        component: () => import('../views/tlias/DeptView.vue')
    },
    {
        path: '/',
        redirect: '/dept'
    }
]
const router = new VueRouter({
    // 配置路由和组件之间的应用关系
    routes,
    mode: 'history',
    linkActiveClass: 'active'
})

// 3.将router对象传入到Vue实例
export default router
