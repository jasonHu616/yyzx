<template>
  <el-row class="panel-group" :gutter="20">

    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          免费咨询订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-free">
            <svg-icon icon-class="free" class-name="card-panel-icon" />
          </div>
            <count-to class="card-panel-num" :startVal="0" :endVal="list.freeordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          问诊订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="message" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.askordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          问诊订单金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.asktotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          回访订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-recall">
            <svg-icon icon-class="recall" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.backordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          药方订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-yaofang">
            <svg-icon icon-class="yaofang" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.prescriptnum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          药方订单金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.prescripttotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          HIS挂号订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="hisguahao" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.hisordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          HIS挂号金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.hisordertotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          平台挂号订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-recall">
            <svg-icon icon-class="platformguahao" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.platformnum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          平台挂号金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.platformtotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          义诊挂号订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-free">
            <svg-icon icon-class="yizhenguahao" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.freeregordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          义诊挂号金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.freeregtotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          诊间支付订单
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="pay" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.hispayordernum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          诊间支付金额
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.hispaytotal || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="6" :lg="4" :xl="4.5" class="card-panel-col">
      <div class="card-panel">
        <div class="title">
          注册量
        </div>
        <div class="content">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="user" class-name="card-panel-icon" />
          </div>
          <count-to class="card-panel-num" :startVal="0" :endVal="list.registnum || 0" :duration="3200"></count-to>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'

export default {
  props:{
    list:{
      type:Array|Object,
      required:true
    }
  },
  components: {
    CountTo
  },
  methods:{
    // goToSkip(path){
    //   this.$router.push(path)
    // }
  },
  mounted(){
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.panel-group {
  margin-top: 18px;
  .card-panel-col{
    margin-bottom: 32px;
    margin-left: 18px;
  }
  .card-panel {
    padding: 14px;
    font-weight:bold;
    height: 100px;
    min-width: 200px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    .title {
      line-height: 18px;color: rgba(0,0,0,0.45);font-size: 16px; margin-bottom: 12px;
    };
    .content{
      font-size: 20px;display:flex;justify-content:flex-start;align-items: center
    };
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
         background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-free {
        background: #f4a945;
      }
      .icon-recall {
        background: #8079f4;
      }
      .icon-yaofang {
        background: #7cf472;
      }
      .icon-shoppingCard {
        background: #34bfa3
      }
    }
    .icon-yaofang {
      color: #7cf472;
    }
    .icon-recall {
      color: #8079f4;
    }
    .icon-free {
      color: #f4a945;
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shoppingCard {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      margin-right: 10px;
      float: left;
      padding: 10px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 30px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
</style>
