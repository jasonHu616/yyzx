<template>
  <div>
  </div>
</template>
<style>
  .demo-color-box {
    border-radius: 4px;
    padding: 20px;
    margin: 5px 0;
    box-sizing: border-box;
    color: #fff;
    font-size: 14px;
  }
  .wrapper{color:#666666; float:left; padding-bottom:20px;}
  .title{color:#666666; line-height:40px;}
  .titledate{font-size: 36px; font-weight: bold; color:#000000; padding-bottom:10px;}
  .lastDay{line-height: 40px;}
  .num{color:#000000; font-weight: bold;}
  .num1{color:green; font-weight: bold;}
  .num2{color:red; font-weight: bold;}
  .border{height:110px; border-right:1px solid #d8d6d6; float:right; margin-top:35px;}

</style>
<script>
    import {Post} from "../../api/invoke";
    import PanelGroup from './admin/PanelGroup';
    import BarChart from './admin/BarChart'
    import BarChartGuahao from "./admin/BarChartGuahao";
    import TotalChart from "./admin/TotalChart";
    import CountTo from 'vue-count-to'

    export default {
        name: 'index',
        data() {
            return {
                data: {},
                currentRole: 'adminDashboard',
                panelGroup_list: {},
                countData:{},
                pie_list: [],
                house_bar: [],
                bar_list: [],
                noPerMission: true
            }
        },
        created() {
        },

        methods: {
            getCount() {
                Post('/count/ordercount/last').then(resp => {
                    if (resp && resp.success) {
                        this.countData = resp.result;
                        this.noPerMission = false;
                    } else {
                        //this.$message.error((res && res.message) ? res.message : '读取数据错误')
                        this.noPerMission = true;
                    }
                }).catch(err => {
                   // this.$message.error('系统错误:' + err)
                });
                Post('/count/ordercount').then(resp => {
                    if (resp && resp.success) {
                        this.panelGroup_list = resp.result;
                        this.noPerMission = false;
                    } else {
                        //this.$message.error((res && res.message) ? res.message : '读取数据错误')
                        this.noPerMission = true;
                    }
                }).catch(err => {
                   // this.$message.error('系统错误:' + err)
                });


                Post('/count/indexplateformsixmonthcount', {}).then(resp =>{
                  if(resp && resp.success){
                    this.bar_list = resp.result;
                      this.noPerMission = false;
                  }else{
                    this.$message.error((res && res.message) ? res.message : '读取数据错误')
                  }
                }).catch(err =>  {
                 // this.$message.error('系统错误:' + err)
                });

            },
        },
        mounted() {
            this.getCount();
        },
        components: {
            PanelGroup,
            BarChart,
            BarChartGuahao,
            TotalChart,
            CountTo
        },
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    /*background-color: rgb(240, 242, 245);*/
    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }
</style>
