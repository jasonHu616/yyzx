<template>
  <div id="app">
   <!-- <img src="./assets/logo.png">-->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  /*解决table表头错位问题*/
  body .el-table th.gutter{
    display: table-cell!important;
  }
</style>
