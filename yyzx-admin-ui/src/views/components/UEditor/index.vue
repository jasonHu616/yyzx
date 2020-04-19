<template>
    <div>
      <vue-ueditor-wrap :readonly="readonly" :config="myConfig" ref="ueditor" :destroy="true" v-model="content"></vue-ueditor-wrap>
    </div>
</template>

<script>
  import VueUeditorWrap from './UeditorVueBase';
  import { getToken,getCurrentRouterName } from "@/utils/auth";
  export default {
    name: "ueditor",
    data(){
      return {
        content:this.value==null?'':this.value,
        myConfig: {
          // 如果需要上传功能,找后端小伙伴要服务器接口地址
          serverUrl: process.env.BASE_API + '/editorUpload/editor',
          // 你的UEditor资源存放的路径,相对于打包后的index.html(路由使用history模式注意使用绝对路径或者填写正确的相对路径)
          UEDITOR_HOME_URL: './static/UEditor/',
          //主题路径
          themePath:"/static/UEditor/themes/",
          theme:'default',
          // 编辑器不自动被内容撑高
          autoHeightEnabled: false,
          // 初始容器高度
          initialFrameHeight: 400,
          // 初始容器宽度
          initialFrameWidth: '100%',
          // 关闭自动保存
          enableAutoSave: false,
          requestHeads:{
            Authorization:getToken(),
            referee:getCurrentRouterName()
          },
          /* 上传图片配置项 */
          imageActionName: "uploadimage", /* 执行上传图片的action名称 */
          imageFieldName: "file", /* 提交的图片表单名称 */
          imageMaxSize: 2048000, /* 上传大小限制，单位B */
          imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */
          imageCompressEnable: true, /* 是否压缩图片,默认是true */
          imageCompressBorder: 1600, /* 图片压缩最长边限制 */
          imageInsertAlign: "none", /* 插入的图片浮动方式 */
          imageUrlPrefix: process.env.IMAGE_URL, /* 图片访问路径前缀 */
          //imagePathFormat: "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
          /* 列出指定目录下的图片 */
          imageManagerActionName: "listimage", /* 执行图片管理的action名称 */
          imageManagerListPath: "/ueditor/jsp/upload/image/", /* 指定要列出图片的目录 */
          imageManagerListSize: 20, /* 每次列出文件数量 */
          imageManagerUrlPrefix: process.env.IMAGE_URL, /* 图片访问路径前缀 */
          imageManagerInsertAlign: "none", /* 插入的图片浮动方式 */
          imageManagerAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 列出的文件类型 */
          /* 上传视频配置项 */
          videoActionName: "uploadvideo", /* 执行上传图片的action名称 */
          videoFieldName: "file", /* 提交的图片表单名称 */
          videoMaxSize: 20480000, /* 上传大小限制，单位B */
          videoAllowFiles: [".mp4", ".3gp", ".avi", ".flv", ".mov",".rm",".rmvb",".wmv"], /* 上传格式显示 */
          videoUrlPrefix: process.env.IMAGE_URL, /* 图片访问路径前缀 */

          /* 上传文件配置项 */
          fileActionName: "uploadfile", /* 执行上传图片的action名称 */
          fileFieldName: "file", /* 提交的图片表单名称 */
          fileMaxSize: 20480000, /* 上传大小限制，单位B */
          fileAllowFiles: [".pdf", ".docx", ".ppt", ".pptx", ".xls",".xlsx",".txt",".mp3",".apk"], /* 上传格式显示 */
          fileUrlPrefix: process.env.IMAGE_URL, /* 图片访问路径前缀 */
        }
      }
    },
    props : {
      value: {type:String, default:''},
        readonly:{
          type:Boolean,
            default:false
        }
    },
    watch: {
      content(val){
        this.$emit('input', val);
      },
      value(val){
        this.content=val;
        if(val==null||val=='')
        this.$refs.ueditor._setContent('');
      }
    },
    created(){
    },
    mounted(){
    },
    beforeDestroy(){
      console.log('')
    },
    methods: {
    },
    components: {
      VueUeditorWrap
    },

  }
</script>

<style scoped>

</style>
