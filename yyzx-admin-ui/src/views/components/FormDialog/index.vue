<template>
  <el-dialog :visible.sync="dialogShow"
             :modal-append-to-body="false"
             :title="title"
             :width="width"
             :fullscreen="full"
             :append-to-body="true"
             :close-on-click-modal="false"
             :close-on-press-escape="false"
             :before-close="beforeClose"
             center>
    <el-form ref="dlgform" :label-width="labelWidth" size="mini" :model="formData" :rules="rules">
      <slot :field="formData"></slot>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button size="small" @click="cancelClose" v-if="this.action && this.action!==''">{{cancelBtn}}</el-button>
      <el-button size="small" type="primary" @click="doSubmit"  v-if="this.action && this.action!==''">{{okBtn}}</el-button>
      <el-button size="primary" @click="close" v-if="!this.action || this.action===''">{{okBtn}}</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { Put , Get} from '@/api/invoke'
  import elDragDialog from '@/directive/el-dragDialog'
  import { extend} from "@/utils";

  export default {
    directives: { elDragDialog },
    data(){
      return {
        dialogShow: false,
        formData: {},
        editors: [],
        inited: false,
        removedEditor: []
      }
    },
    watch: {
    },
    updated(a){
    },
    created(){
      this.formData=this.setInitFormData();
      //console.info('dialog created')
    },
    mounted(){
    },
    activated(){
      //console.info('dialog activated')
    },
    deactivated(){
      //console.info('dialog deactivated')
    },
    destroyed(){
      //console.info('dialog destroyed')
    },
    methods: {
      _open(data){

        //this.initTinymces();
        if(this.bindSuccess){
          this.bindSuccess(data);
        }
      },
      setInitFormData(){
        return this.initFormData || {}
      },
      open(id, defval){
        if(id && id>=0){
          Get(this.dataUrl, {id: id}).then(resp =>{
            var res = resp;
            if(res && res.success){
              this.formData = res.result;

              if(this.getDataSuccess){
                this.getDataSuccess(this.formData)
              }

              this.dialogShow = true;
              this.$nextTick(()=>{
                this._open(this.formData);
              });
            }else{
              this.$message.error((res && res.message) ? res.message : '读取数据错误')
            }
          }).catch(err =>  {
            this.$message.error('系统错误:' + err)
          })
        }else{
          this.dialogShow = true;
          this.formData = extend(this.setInitFormData(), defval, true);
          if(this.getDataSuccess){
            this.getDataSuccess(this.formData)
          }
          this.$nextTick(()=>{
            this._open();
          });
        }

      },
        getFormData(){
          return this.formData;
        },
      close(){
        //this.destroyTinymces();
        this.dialogShow = false;
        this.formData = this.setInitFormData();
        if(this.bindClose){
          this.bindClose();
        }
        this.$refs['dlgform'].clearValidate()
      },
      // 取消按钮关闭事件
      cancelClose: function(){
        this.dialogShow = false;
        this.formData = this.setInitFormData();
        if(this.bindCancelClose){
          this.bindCancelClose();
        }
        this.$refs['dlgform'].clearValidate()
      },
      setFormData: function(data){
          this.$nextTick(()=>{
              this.$set(this.formData,extend(this.formData, data, true));
          })
      },
      // 确认和关闭按钮不会触发beforeClose，只有右上角的关闭图标会触发该事件
      beforeClose: function(done){
        //this.$refs.dlgform.resetFields();
        this.formData = this.setInitFormData();
        console.log("formData",this.formData);
        // if(this.bindClose){
        //   this.bindClose();
        // }
        if(this.bindCancelClose){
          this.bindCancelClose();
        }
        this.$refs['dlgform'].clearValidate()
        this.dialogShow = false;
        done();
      },
      doValid(){
        this.$refs['dlgform'].validate(() => {});
      },
      doSubmit(){

        if(this.action===null && this.action===''){
          return false;
        }
        //console.info(this.formData);
        //提交前处理
        if(this.beforeSubmit){
          var r = this.beforeSubmit(this.formData);
          if(!r)return false;
        }
        this.$refs['dlgform'].validate((valid) => {
          if(!valid){
            this.$message.error('请根据表单提示填写正确的信息')
            return false;
          }

          //提交操作
          Put(this.action, this.formData).then(resp=>{
            var res = resp;
            if(res && res.success){
              if(this.afterSubmit){
                this.afterSubmit(res)
              }
              this.$message.success(res.message)
              this.close();
            }else{
              this.$message.error(res.message)
            }
          }).catch(err => {
            console.error(err)
            this.$message.error('提交错误：'+err.message)
          });
        })

      }
    },
    props:{
      /**
       * 表单提交地址
       */
      action: {
        type: String
      },
      /**
       * 数据URL
       */
      dataUrl: {
        type: String
      },
      /**
       * 对话框标题
       */
      title: {
        type: String,
        default: '表单'
      },
      /**
       * 对话框宽
       */
      width: {
        type: String,
        default: '50%'
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
       * label宽
       */
      labelWidth : {
        type: String,
        default: '100px'
      },
      /**
       *表单验证规则定义
       */
      rules : {
        type: Object
      },
      /**
       * 关闭事件
       */
      bindClose : {
        type: Function
      },
      /**
       * 打开完成事件
       */
      bindSuccess : {
        type: Function
      },
      /**
       * 提交前处理
       */
      beforeSubmit : {
        type: Function
      },
      /**
       * 提交成功后处理
       */
      afterSubmit: {
        type: Function
      },
      bindCancelClose: {
        type: Function
      },

      getDataSuccess: {
        type: Function,default: function(){}
      },

      initFormData:{
        type:Object,
      }
    }
  }
</script>

<style scoped>
  .dialog-footer{text-align:right;}
</style>
