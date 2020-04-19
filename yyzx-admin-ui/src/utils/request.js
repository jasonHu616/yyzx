import axios from 'axios'
import {Message} from 'element-ui'
import store from '@/store'
import {getToken,getCurrentRouterName} from '@/utils/auth'
import qs from 'qs';
import Vue from 'vue';

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 60000, // request timeout
  maxContentLength: 9999999999,
  responseType: 'json',
  withCredentials: true
})


var formatUrl = ((apiUrl, data) => {
  var url = apiUrl;
  if (url.indexOf('${') > -1) {
    let u = url;
    var t = /\$\{[^}]+}/g;
    var m = u.match(t);
    var temp = data;
    if (m != null && m.length > 0) {
      for (var i = 0; i < m.length; i++) {
        var k = m[i].substring(2, m[i].length - 1);
        //console.info("UK:" + m[i] + '/' + k + '/' + data[k]);
        u = u.replace(new RegExp(m[i], 'gi'), data[k]);
        u = u.replace(m[i], temp[k]);
        delete data[k];
      }
    }
    url = u;
  }
  var result = {url: url, data: data};
  return result;
});


// request interceptor
service.interceptors.request.use(config => {
  // Do something before request is sent
  //console.info('request interceptors...' + config.url + '/' + store.getters.token);
  //console.info('REQUEST: ' + config.url);
  //console.info(config);
  var fmtUrl = formatUrl(config.url, config.params);
  //console.info(fmtUrl)
  config.url = fmtUrl.url;
  config.params = fmtUrl.data || {};
  //console.info(config)
  config.headers['referee']=getCurrentRouterName();
  // console.log(config);
  if (store.getters.token) {
    config.headers['Authorization'] = getToken() // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
  }
  if (config.method.toLowerCase() !== 'put') {
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
    config.data = qs.stringify(config.data)
  } else {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    //console.info('put')
    //console.info(config.data)
  }
  //console.info(config);
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})



// respone interceptor
service.interceptors.response.use(
  //response => response,
  response => {
    // console.info(response)
    if(response.status && response.statusText!=null && response.data && response.config){
      let httpResp = response.data;
      if(httpResp.status && httpResp.status === 1001) {  //无权限
        httpResp.message="NoAuth:您没有当前功能的操作权限，请联系管理员！";
        Message.error('NoAuth:'+"您没有当前功能的操作权限，请联系管理员！");
      }
      return httpResp;
    }else if(response.status && response.message!=null && ! response.config){
      if (response.status===0){
        return response.data;
      }else if(response.status === 1001) {  //无权限
        Message.error('NoAuth:'+"您没有当前功能的操作权限，请联系管理员！")
      }else{
        Message.error('Other:'+response.message)
      }
    }else if(response.headers && (response.headers['content-type'] === 'application/octet-stream')){
      return response;
    }else{
      return response;
    }
  },
  /**
   * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
   * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
   */
    //  const res = response.data;
    //     if (res.code !== 20000) {
    //       Message({
    //         message: res.message,
    //         type: 'error',
    //         duration: 5 * 1000
    //       });
    //       // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
    //       if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
    //         MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
    //           confirmButtonText: '重新登录',
    //           cancelButtonText: '取消',
    //           type: 'warning'
    //         }).then(() => {
    //           store.dispatch('FedLogOut').then(() => {
    //             location.reload();// 为了重新实例化vue-router对象 避免bug
    //           });
    //         })
    //       }
    //       return Promise.reject('error');
    //     } else {
    //       return response.data;
    //     }
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  })

export default service
