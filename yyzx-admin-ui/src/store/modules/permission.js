import { asyncRouterMap, constantRouterMap } from '@/router'


function checkPermission(roles, route){
  if(route.children && route.children.length>0){
    //console.info('children:', route.children)
    var childrencheck = false;
    route.children.forEach((children, i) => {
      var check = checkPermission(roles, children);
      if(check)childrencheck = true;
    })
    return childrencheck;
  }else{
    var check = false;
    roles.forEach((role, idx) =>{
      //console.info('check:'+route.name, role, route, role.menukey===route.name)
      if(role.menukey===route.name){
        check = true;
      }
    })
    return check;
  }
  return false;
}
/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param user
 * @param roles
 * @param route
 */
function hasPermission(user, roles, route) {
  if(!user) return false;
  if(user.superuser===1 || user.devmoce===1) return true; //TODO:开发功能过滤
  if (route.name && route.meta && !route.meta.noAuth) {
    //console.info(user, roles, route)
    //return roles.some(role => route.meta.roles.indexOf(role) >= 0)
    return checkPermission(roles, route);
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param user
 * @param roles
 */
function filterAsyncRouter(asyncRouterMap, user, roles) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasPermission(user, roles, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, user, roles)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      //console.info('GenerateRoutes....');
      return new Promise(resolve => {
        const { user, roles } = data
        let accessedRouters
        //if (roles.indexOf('admin') >= 0) {
        //  accessedRouters = asyncRouterMap
        //} else {
        accessedRouters = filterAsyncRouter(asyncRouterMap, user, roles)
        //}
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
