import Vue from 'vue'
import VueRouter from 'vue-router'
import DeptView from '../views/element-case-management-system/DeptView.vue'

Vue.use(VueRouter)


//1先配置路由表   有两种方式
const routes = [
  {
    path: '/dept',
    name: 'dept',
    component: DeptView
  },
  {
    path: '/emp',
    name: 'emp',
    component: () => import('../views/element-case-management-system/EmpView.vue')
  },
  {//因为浏览器打开默认是/ 只使用上面两个打开浏览器的时候/会空白  所以将/重定向到一个url
    path: '/',
    redirect: '/emp'//重定向到/emp
  }
]

const router = new VueRouter({
  routes
})

export default router
