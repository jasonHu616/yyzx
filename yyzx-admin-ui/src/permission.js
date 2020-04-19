import router from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {getToken, setCurrentRouterName} from '@/utils/auth' // getToken from cookie

NProgress.configure({showSpinner: false}) // NProgress Configuration

// permissiom judge function
function hasPermission(route) {
  const roles = store.getters.roles
  const user = store.getters.user
  if (!user) return false;
  if (user.admintype === 1 || user.admintype === 2 ) return true; //TODO:开发功能过滤
  if (!route.meta) return false;
  if (route.name && route.meta && !route.meta.noAuth) {
    //console.info('check:' + route.name)
    var check = false;
    roles.forEach((role, idx) => {
      if (role.menukey === route.name) {
        check = true;
      }
    })
    return check;
  } else {
    return true;
  }
}

const whiteList = ['/login', '/authredirect'] // no redirect whitelist

router.beforeEach((to, from, next) => {
  setCurrentRouterName(to.name);
  NProgress.start() // start progress bar
  var token = getToken();
  if (token) { // determine if there has token
    /* has token*/
    if (to.path === '/login') {
      next({path: '/'})
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      if (!store.getters.user || (
        store.getters.user.admintype == 0 && (!store.getters.roles || store.getters.roles.length === 0)
      )) { // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetUserInfo').then(res => { // 拉取user_info
          var r = res;
          if (r.status === 0) {
            const user = r.result.user
            const roles = r.result.roles // note: roles must be a array! such as: ['editor','develop']
            store.dispatch('GenerateRoutes', {user, roles}).then(() => { // 根据roles权限生成可访问的路由表
              router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
              next({...to, replace: true}) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
            })
          } else {
            throw r.message || '获取用户权限失败'
          }

        }).catch((err) => {
          console.error(err);
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({path: '/'})
          })
        })
      } else {
        // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
        if (hasPermission(to)) {
          //console.info('has permission',store.getters.roles)
          next() //
        } else {
          //console.info('no permission')
          next({path: '/401', replace: true, query: {noGoBack: true}})
        }
        // 可删 ↑
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login') // 否则全部重定向到登录页
      NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
    }
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})

