import Vue from 'vue'
import Router from 'vue-router'

const _import = require('./_import_' + process.env.NODE_ENV)

Vue.use(Router)

import Layout from '../views/layout/Layout'
import Child from '../views/layout/ChildPage'


export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true, meta: {noAuth: true}},
  {path: '/404', component: _import('errorPage/404'), hidden: true, meta: {noAuth: true}},
  {path: '/401', component: _import('errorPage/401'), hidden: true, meta: {noAuth: true}},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index'),
      name: 'dashboard',
      meta: {title: '管理首页', icon: 'test_home', noCache: true, noAuth: true}
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

let asyncRouters = [];

/**
 * 开发管理菜单，仅在开发模式下生效
 */
if(process.env.NODE_ENV === 'development') {
  const DeveloperRouter = {
    path: '/developer',
    component: Layout,
    name: 'developer',
    meta: {title: '开发管理', icon: 'example', devmode: true},
    children: [{
      path: 'menus',
      component: _import('developer/menus'),
      name: 'menus',
      meta: {title: '菜单脚本生成', icon: 'form', noCache: true}
    }
      // , {
      //   path: 'adwaredata',
      //   component: _import('developer/adwaredata'),
      //   name: 'adwaredata',
      //   meta: {title: '广告位管理', icon: 'form', noCache: true}
      // }
    ]
  }
  asyncRouters.push(DeveloperRouter)
}

const NotfoundRouter = {
  path: '*', redirect:'/404', hidden:true, meta:{}
}
asyncRouters.push(NotfoundRouter);
export const asyncRouterMap = asyncRouters;
