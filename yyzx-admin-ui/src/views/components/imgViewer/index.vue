<template>
  <div id="ImgPopover">
    <el-popover ref="imgPopover" placement="right" trigger="hover">
      <img class="smallImg" :src="url">
    </el-popover>
    <el-button v-if="type==0" type="text" v-popover:imgPopover>[图片]</el-button>
    <img v-else class="img" v-popover:imgPopover :src="url"/>
  </div>
</template>

<script>
    export default {
        name: "index",
      props: {
            url:{
                type:String
            },
          type:{
                type:Number,
              default:0
          }
      },
      data: function () {
        return {
        }
      }
    }
</script>

<style scoped>
  .smallImg{
    max-width: 500px;
    max-height: 500px;
  }
  .img{
    width: 200px;
    height: 200px;
  }
</style>
