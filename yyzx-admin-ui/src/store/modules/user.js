import {loginByUsername, logout, getUserInfo} from '@/api/login'
import {getToken, setToken, removeToken} from '@/utils/auth'

const user = {
  state: {
    user: null,
    steward: null,
    status: '',
    code: '',
    token: getToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    SET_USER: (state, user) => {
      state.user = user
    },
    SET_STEWARD: (state, steward) => {
      state.steward = steward
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_ROLES: (state, roles) => {
      //console.info('setRoles')
      //console.info(roles);
      state.roles = roles
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({commit}, userInfo) {
      //console.info('LoginByUsername in store/user.js');
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password).then(response => {
          const data = response
          //console.info(data)
          if (data.success) {
            //console.info(data);
            commit('SET_TOKEN', data.result.token)
            setToken(data.result.token)
            //console.info(resolve);
            resolve()
          } else {
            reject(data.message)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetUserInfo({commit, state}) {
      return new Promise((resolve, reject) => {
        getUserInfo().then(response => {
          //console.info(response);
          if (!response || !response.success || !response.result) {
            reject('获取用户权限失败')
          }
          const data = response.result
          if (data.user.superuser === 0 && data.user.devmode === 0 && (!data.roles || data.roles.length === 0)) {
            reject('用户没有任何权限')
          }
          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          }
          commit('SET_USER', data.user)
          commit('SET_STEWARD', data.steward)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({commit, state}) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({commit}, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response
          commit('SET_ROLES', data.roles)
          resolve()
        })
      })
    }
  }
}

export default user
