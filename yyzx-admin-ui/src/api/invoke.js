import request from '@/utils/request'

export function Post(url, params) {
  return request({
    url: url,
    method: 'post',
    data: params
  })
}

export function Put(url, params) {
  return request({
    url: url,
    method: 'put',
    data: params
  })
}

export function Get(url, params) {
  return request({
    url: url,
    method: 'get',
    params: params
  })
}

export function Delete(url, params) {
  return request({
    url: url,
    method: 'delete',
    params: params
  })
}


export function Download(url, params) {
  return request({
    url: url,
    method: 'get',
    params: params,
    responseType:'blob'
  })
}

export function DeleteByID(url, id) {
  return request({
    url: url,
    method: 'delete',
    params: { id: id }
  })
}
