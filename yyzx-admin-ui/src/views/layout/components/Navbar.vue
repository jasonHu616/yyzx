<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>

    <breadcrumb class="breadcrumb-container"></breadcrumb>

    <div class="right-menu" >
      <!--<div style="display: flex;align-content:center;justify-content: flex-start;flex-direction: column; float: left;margin-right:7rem;">
        <div style="position:relative;bottom:-6px;left: -166px; font-size: 14px;line-height:1.6;"><span >欢迎您: {{getUser.realname}}</span></div>
        <span style="position:fixed;top:26px;right: 375px; font-size: 14px;line-height:1;padding-left:2rem;text-align: left;">{{time}} {{week}}</span>
      </div>-->
      <div style="position: fixed; top: 3px; right: 300px; font-size: 14px;">
        <span style="">
          <span >欢迎您: {{getUser.realname}}</span></span>
        <span style="margin-left: 30px;">{{time}} {{week}}</span>
      </div>
      <!--<el-button style="position: relative;top: 5px;"-->
      <!--plain-->
      <!--@click="onTesk">-->
      <!--待办任务-->
      <!--<el-badge class="mark" :value="teskData.allnum" />-->
      <!--</el-button>-->

      <error-log class="errLog-container right-menu-item"></error-log>

      <el-tooltip style="position: relative;top: 10px;"  effect="dark" content="全屏切换" placement="bottom">
        <screenfull class="screenfull right-menu-item"></screenfull>
      </el-tooltip>
      <!--      <el-tooltip effect="dark" content="皮肤颜色" placement="bottom">
              <theme-picker class="theme-switch right-menu-item"></theme-picker>
            </el-tooltip>-->

      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper" style="position: relative">
          <img style="width: 22px;height: 22px;" class="user-avatar" :src="(steward && steward.photo && steward.photo!='') ? steward.photo+'!80x80' : '/static/images/user.svg'">
          <!--<svg>-->
          <!--<use class="user-avatar" :href="userSvg"></use>-->
          <!--</svg>-->
          <span class="el-icon-caret-bottom" style="position: relative;top:-7px;left:0px"></span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/profile/mysteward">
            <el-dropdown-item v-if="steward">
              我的管家信息
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item  >
            <span @click="changePwd" style="display:block;">修改密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span @click="logout" style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>


      <el-dialog
        title="修改密码"
        :visible.sync="centerDialogVisible"
        width="30%"
        @open="openPwd"
        @close="closePwd"
        :append-to-body="true"
        center>
        <el-form :model="formData" size="mini" :rules="rules" ref="pwdForm" label-width="100px">
          <el-form-item label="原密码"  prop="pwd">
            <el-input type="password" v-model="formData.pwd" name="pwd"></el-input>
          </el-form-item>
          <el-form-item label="新密码"  prop="newpwd">
            <el-input type="password" v-model="formData.newpwd" name="newpwd"></el-input>
          </el-form-item>
          <el-form-item label="确认密码"  prop="rnewpwd">
            <el-input type="password" v-model="formData.rnewpwd" name="rnewpwd"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="resetForm('pwdForm')">取 消</el-button>
          <el-button type="primary" @click="submitForm('pwdForm')">保存修改</el-button>
        </span>
      </el-dialog>
    </div>
  </el-menu>
</template>

<script>
    import { mapGetters } from 'vuex'
    import { Post} from '@/api/invoke'
    import vFormDialog from '../../components/FormDialog'
    import Breadcrumb from '@/components/Breadcrumb'
    import Hamburger from '@/components/Hamburger'
    import ErrorLog from '@/components/ErrorLog'
    import Screenfull from '@/components/Screenfull'
    import ThemePicker from '@/components/ThemePicker'
    let moment = require('moment');

    export default {
        components: {
            Breadcrumb,
            Hamburger,
            ErrorLog,
            Screenfull,
            ThemePicker,
            vFormDialog
        },
        computed: {
            ...mapGetters([
                'sidebar',
                'steward',
                'user'
            ])
        },
        data() {
            return {
                centerDialogVisible:false,
                teskOpen:false,
                teskData:{},
                formData:{
                    pwd:'',
                    newpwd:'',
                    rnewpwd:''
                },
                rules: {
                    pwd: [{required: true, message: '请输入原密码'}],
                    newpwd: [{required: true, message: '请输入新密码'}],
                    rnewpwd: [{required: true, message: '请输入确认密码'}]
                },
                time:'',
                week:'',
                getUser:{},
            }
        },
        created() {
            // this.getTeskData();
        },
        methods: {
            init(){

            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if(!valid){
                        this.$message.error('请根据表单提示填写正确的信息')
                        return false;
                    }
                    //提交操作
                    Post('/indexdata/uppwd', this.formData).then(resp=>{
                        var res = resp;
                        if(res && res.success){
                            this.centerDialogVisible=false;
                            this.$refs['pwdForm'].resetFields();
                            this.$store.dispatch('LogOut').then(() => {
                                location.reload()// In order to re-instantiate the vue-router object to avoid bugs
                            })
                        }else{
                            this.$message.error(res.message)
                        }
                    }).catch(err => {
                        console.error(err)
                        this.$message.error('提交错误：'+err.message)
                    });
                });
            },
            resetForm(formName) {
                this.centerDialogVisible=false;
                this.$refs[formName].resetFields();
            },
            openPwd(){

            },
            closePwd(){
                this.$refs['pwdForm'].resetFields();
            },
            changePwd(){
                this.centerDialogVisible=true;
            },
            toggleSideBar() {
                this.$store.dispatch('toggleSideBar')
            },
            logout() {
                this.$confirm('您确定要退出当前登录状态吗?', '退出？', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(()=>{
                    this.$store.dispatch('LogOut').then(() => {
                        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
                    })
                }).catch(()=>{

                })
            },
            onTesk(){
                this.getTeskData();
                this.teskOpen=true;
            },
            // getTeskData(){
            //   Post('/count/teskData',{})
            //     .then(res => {
            //       if (res) {
            //         this.teskData=res.result;
            //       } else {
            //         this.$message.error((res && res.result && res.message) ? res.message : '加载数据错误');
            //       }
            //     })
            //     .catch(err => {
            //       console.error(err)
            //       this.$message.error(err.message)
            //     })
            // },
            // getTaskCount(){
            //   Post('/count/taskCount',{})
            //     .then(res => {
            //       if (res) {
            //         this.teskData=res.result;
            //       } else {
            //         this.$message.error((res && res.result && res.message) ? res.message : '加载数据错误');
            //       }
            //     })
            //     .catch(err => {
            //       console.error(err)
            //       this.$message.error(err.message)
            //     })
            // },
            getLoginUser(){
                Post('/loginUser/getloginUsers',{})
                    .then(res => {
                        if (res) {
                            this.getUser=res.result;
                            this.week=this.getUser.week;
                            this.time=this.getUser.date;
                        } else {
                            this.$message.error((res && res.result && res.message) ? res.message : '加载数据错误');
                        }
                    })
                    .catch(err => {
                        this.$message.error(err.message)
                    })
            },
            goToSkip(path){
                this.teskOpen=false;
                this.$router.push(path)
            },

            countdown () {
                this.getLoginUser();
            },
            setTimeout(){
                setInterval(this.countdown,15000);
                //   setInterval(this.getTaskCount,60 * 1000);
            },
            formatDate(date, fmt) {
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
                }
                let o = {
                    'M+': date.getMonth() + 1,
                    'd+': date.getDate(),
                    'h+': date.getHours(),
                    'm+': date.getMinutes(),
                    's+': date.getSeconds()
                };
                for (let k in o) {
                    if (new RegExp(`(${k})`).test(fmt)) {
                        let str = o[k] + '';
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : this.padLeftZero(str));
                    }
                }
                return fmt;
            },

            padLeftZero(str) {
                return ('00' + str).substr(str.length);
            }
        },
        mounted(){
            this.getLoginUser();
            // this.getTaskCount();
            this.setTimeout();
        }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .item {
    margin-top: 10px;
    margin-right: 40px;
  }
  .navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0px !important;
    .hamburger-container {
      line-height: 58px;
      height: 50px;
      float: left;
      padding: 0 10px;
    }
    .breadcrumb-container{
      float: left;
    }
    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }
    .right-menu {
      float: right;
      height: 100%;
      &:focus{
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        height: 20px;
      }
      .international{
        vertical-align: top;
      }
      .theme-switch {
        vertical-align: 15px;
      }
      .avatar-container {
        height: 50px;
        margin-right: 30px;
        .avatar-wrapper {
          cursor: pointer;
          margin-top: 5px;
          position: relative;
          .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }
          .el-icon-caret-bottom {
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }
</style>
