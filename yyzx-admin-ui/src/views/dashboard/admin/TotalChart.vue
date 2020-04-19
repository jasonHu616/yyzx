<template>
  <div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
    import echarts from 'echarts'

    require('echarts/theme/macarons') // echarts theme
    import {debounce} from '@/utils'

    const animationDuration = 6000;

    export default {
        props: {
            className: {
                type: String,
                default: 'chart'
            },
            width: {
                type: String,
                default: '100%'
            },
            height: {
                type: String,
                default: '550px'
            },
            barlist: {
                type: Array | Object,
                required: true
            }
        },
        data() {
            return {
                chart: null,
                data_X: [],
                data_Y: [],
            }
        },
        watch: {
            barlist() {
                let bar_list = {
                    month: [],
                    project_count: [
                        {
                            name:'问诊订单金额',
                            type:'line',
                            data:[]
                        },
                        {
                            name:'药方订单金额',
                            type:'line',
                            data:[]
                        },
                        {
                            name:'HIS挂号金额',
                            type:'line',
                            data:[]
                        },
                        {
                            name:'平台挂号金额',
                            type:'line',
                            data:[]
                        },
                        {
                            name:'义诊挂号金额',
                            type:'line',
                            data:[]
                        },
                        {
                            name:'诊间支付金额',
                            type:'line',
                            data:[]
                        }
                    ]
                };
                this.barlist.forEach(e => {
                    bar_list.month.push(e.date);
                    bar_list.project_count[0].data.push(e.asktotal);
                    bar_list.project_count[1].data.push(e.prescripttotal);
                    bar_list.project_count[2].data.push(e.hisordertotal);
                    bar_list.project_count[3].data.push(e.platformtotal);
                    bar_list.project_count[4].data.push(e.freeregtotal);
                    bar_list.project_count[5].data.push(e.hispaytotal);
                });

                this.chart.setOption({
                    title: {
                        text: '平台金额统计',
                        subtext: '最近一年内各项指数对比'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['问诊订单金额','药方订单金额','HIS挂号金额','平台挂号金额','义诊挂号金额','诊间支付金额']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: bar_list.month
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: bar_list.project_count
                })
            }
        },
        mounted() {
            this.initChart();
            this.__resizeHanlder = debounce(() => {
                if (this.chart) {
                    this.chart.resize()
                }
            }, 100);
            window.addEventListener('resize', this.__resizeHanlder)
        },
        beforeDestroy() {
            if (!this.chart) {
                return
            }
            window.removeEventListener('resize', this.__resizeHanlder)
            this.chart.dispose();
            this.chart = null
        },
        methods: {
            initChart() {
                this.chart = echarts.init(this.$el, 'macarons');
                window.onresize = this.chart.resize;
            },

        }
    }
</script>
