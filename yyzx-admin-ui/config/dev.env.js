/*'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"'
})*/

module.exports = {
  NODE_ENV: '"development"',
  ENV_CONFIG: '"dev"',
  BASE_API: '"/api"',
  IMAGE_URL: '"https://dev-1257318792.cos.ap-chongqing.myqcloud.com"',
  SOCKET_URL: '"ws://192.168.1.18:8080/socket"'
  //IMAGE_URL: '"http://file-1257318792.file.myqcloud.com"'
}
