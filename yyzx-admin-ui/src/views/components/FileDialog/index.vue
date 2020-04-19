<template>
  <el-dialog v-el-drag-dialog :visible.sync="dialogShow"
             :modal-append-to-body="false"
             :title="title"
             :width="width"
             :fullscreen="full"
             :append-to-body="true"
             :close-on-click-modal="false"
             :close-on-press-escape="false"
             :before-close="beforeClose"
             center>
    <el-upload
      class="upload-demo"
      :action="actionUrl"
      :headers="headers"
      :file-list="fileList"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传除exe,jsp,php,asp,aspx文件，且不超过100M</div>
    </el-upload>
    <div slot="footer" class="dialog-footer">
      <el-button size="small" @click="close">{{cancelBtn}}</el-button>
      <el-button size="small" type="primary" @click="doSubmit">{{okBtn}}</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import {Get, Put, Delete} from '@/api/invoke'
  import elDragDialog from '@/directive/el-dragDialog'
  import {extend} from "@/utils";
  import {getToken} from '@/utils/auth'
  import {SizeToLong, LongToSize} from '@/utils/index'

  export default {
    directives: {elDragDialog},
    data() {
      return {
        uuid: null,
        previewVisible: false,
        dialogShow: false,
        headers: {
          Authorization: getToken()
        },
        fileList: [],
        actionUrl: process.env.BASE_API + this.action + '/' + (this.define || 'others'),
        dialogImageUrl: null,
        successFile: []
      }
    },
    watch: {},
    methods: {
      handleRemove(file, fileList) {
        if(file && file.id) {
          Delete('/picture/delete/${id}', {id: file.id}).then(resp=>{
            //console.info(resp)
            if(resp && resp.success){
              if (this.successFile[file.uid]) delete this.successFile[file.uid];
            }else{
              this.$message.error((resp && resp.message) ? resp.message : '删除附件出错')
            }
          }).catch(err=>{
            console.error(err)
            this.$message.error('删除附件出错')
          })
        }else {
          if (this.successFile[file.uid]) delete this.successFile[file.uid];
        }
      },
      handlePictureCardPreview: function(file){
        // this.dialogImageUrl = process.env.IMAGE_URL + file.response.filePath;
        this.dialogImageUrl =  file.url;
        window.open(this.dialogImageUrl)
      },
      handleSuccess: function(response, file, fileList){
        if(file && file.response && file.response.success){
          this.successFile.push(file.response);
        }
      },

      doSubmit: function(){
        if(this.successFile && this.successFile.length>0){
          var data = [];
          this.successFile.forEach((item, idx) => {
            data.push({
              uuid: this.uuid,
              title: item.title,
              filepath: item.filePath
            })
          })
          if(data && data.length>0){
            Put('/picture/saveall', data).then(resp=>{
                if(resp && resp.success){
                  this.list = [];
                  this.successFile = [];
                  this.close();
                }else{
                  this.$message.error('保存附件数据出错')
                }
            }).catch(err=>{
              console.error(err)
              this.$message.error('保存附件失败')
            })
          }
        }
        this.close();
      },
      _open(data) {
        this.dialogShow = true;
        if (this.bindSuccess) {
          this.bindSuccess(data);
        }
      },
      open(uuid) {
        this.uuid = uuid;
        if (uuid && uuid !== '' ) {
          Get('/picture/list/${uuid}', {uuid: uuid}).then(resp => {
            var list = [];
            var data = resp;
            if(data && data.length>0){
              data.forEach((item, idx) => {
                list.push({
                  id: item.id,
                  name: item.title,
                  url: process.env.IMAGE_URL + item.filepath
                });
              })
            }
            this.fileList = list;
          }).catch(err => {
            this.$message.error('读取数据错误:' + err)
          })
        }
        this._open();
      },
      close() {
        this.fileList = [];
        this.list = [];
        this.successFile = [];
        this.dialogShow = false;
        if (this.bindClose) {
          this.bindClose();
        }
      },
      beforeClose: done => {
        this.fileList = [];
        this.list = [];
        this.successFile = [];
        //console.info('before close')
        //this.$refs.dlgform.resetFields();
        if (this.bindClose) {
          this.bindClose();
        }
        this.dialogShow = false;
        done();
      }
    },
    props: {
      /**
       * 上传服务端action
       */
      action: {
        type: String,
        default: '/upload'
      },
      /**
       * 数据定义
       */
      define: {
        type: String, default: 'others'
      },
      /**
       * 对话框标题
       */
      title: {
        type: String,
        default: '附件列表'
      },
      /**
       * 对话框宽
       */
      width: {
        type: String,
        default: '40%'
      },
      /**
       * 是否纵向全屏
       */
      full: {
        type: Boolean,
        default: false
      },
      /**
       * 确定按钮文字
       */
      okBtn: {
        type: String, default: '保 存'
      },
      /**
       * 取消按钮文字
       */
      cancelBtn: {
        type: String, default: '取 消'
      },
      /**
       * 关闭事件
       */
      bindClose: {
        type: Function
      },
      /**
       * 打开完成事件
       */
      bindSuccess: {
        type: Function
      },
      /**
       * 提交前处理
       */
      beforeSubmit: {
        type: Function
      },
      /**
       * 提交成功后处理
       */
      afterSubmit: {
        type: Function
      }

    }
  }
</script>

