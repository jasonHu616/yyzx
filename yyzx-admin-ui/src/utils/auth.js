const TokenKey = 'Authorization';
const CurrentRouterName = 'currentRouter';

export function getToken() {
    //console.info('getToken:' + sessionStorage.getItem(TokenKey))
    return sessionStorage.getItem(TokenKey)
}

export function setToken(token) {
    //console.info('setToken:' + token)
    return sessionStorage.setItem(TokenKey, token)
}

export function removeToken() {
    //console.info('removeToken:' + TokenKey)
    return sessionStorage.removeItem(TokenKey)
}

/**
 * 获取当前路由
 * @returns {string | null}
 */
export function getCurrentRouterName(){
  return sessionStorage.getItem(CurrentRouterName);
}

/**
 * 设置当前路由
 * @param name
 */
export function setCurrentRouterName(name){
  return sessionStorage.setItem(CurrentRouterName,name);
}
