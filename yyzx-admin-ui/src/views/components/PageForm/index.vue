<template>
    <el-form ref="formbox" :label-width="labelWidth" size="mini" :model="formData" :rules="rules">
      <slot :field="formData"></slot>
      <el-form-item>
        <el-button size="small" type="primary" @click="doSubmit">{{okBtn}}</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
  import { Put , Get} from '@/api/invoke'
  import { extend} from "@/utils";

  export default {
    data(){
      return {
        formData: {}
      }
    },
    watch: {
    },
    mounted(){
      //console.info('pageform mounted')
    },
    activated(){
      //console.info('pageform activited')
    },
    deactivated(){
      //console.info('pageform deactivited')
    },
    methods: {
      load(id, defval){
        //console.info('page form load...')
        if(id && id>0){
          Get(this.dataUrl, {id: id}).then(resp =>{
            var res = resp;
            if(res && res.success){
              this.formData = res.result;
              console.info(this.formData)
            }else{
              this.$message.error((res && res.message) ? res.message : '读取数据错误')
            }
          }).catch(err =>  {
            this.$message.error('系统错误:' + err)
          })
        }else{
          this.formData = extend({}, defval, true);
        }
      },
      doSubmit(){
        //提交前处理
        if(this.beforeSubmit){
          var r = this.beforeSubmit(this.formData);
          if(!r)return false;
        }
        this.$refs['formbox'].validate((valid) => {
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
      }

    }
  }
</script>

<style scoped>
</style>
