import request from '@/utils/request'

export function GetGroups() {
  return request({
    url: '/configure/groups',
    method: 'get'
  })
}
