import Vue from 'vue'

import Print from 'vue-print-nb'
Vue.use(Print);

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import base from './assets/js/base'

import './icons' // icon
import './errorLog' // error log
import './permission' // permission control


import * as filters from './filters' // global filters
Vue.use(base);
Vue.use(Element, {
  size: 'mini' // set element-ui default size
})

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

/**
 * 检测操作权限
 * @param action
 * @returns {boolean}
 */
Vue.prototype.roleHasAction = function (action) {
  const vm = this.$route
  const user = store.getters.user;
  const roles = store.getters.roles;
  //console.info(vm, user, roles)
  if (user.admintype === 1 || user.admintype === 2) return true;
  for (let i = 0; i < roles.length; i++) {
    if (roles[i].menukey === vm.name) {
      return (',' + roles[i].actions + ',').indexOf(action) > -1;
    }
  }
  return false;
}

Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
