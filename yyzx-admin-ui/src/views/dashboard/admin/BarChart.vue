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
                config: {
                    rotate: 90,
                    align: 'left',
                    verticalAlign: 'middle',
                    position: 'insideBottom',
                    distance: 15,
                    onChange: function () {
                        this.chart.setOption({
                            series: [{
                                label: this.labelOption
                            }, {
                                label: this.labelOption
                            }, {
                                label: this.labelOption
                            }, {
                                label: this.labelOption
                            }]
                        });
                    }
                },
                labelOption: {
                    normal: {
                        show: false,
                        position: 'insideBottom',
                        distance: 15,
                        align: 'left',
                        verticalAlign: 'middle',
                        rotate: 90,
                        formatter: '{c}  {name|{a}}',
                        fontSize: 15,
                        rich: {
                            name: {
                                textBorderColor: '#fff'
                            }
                        }
                    }
                }
            }
        },
        watch: {
            barlist() {
                let bar_list = {
                    month: [],
                    project_count: [
                        {
                            name: '免费咨询订单',
                            type: 'bar',
                            barGap: 0,
                            label: this.labelOption,
                            data: []
                        },
                        {
                            name: '问诊订单',
                            type: 'bar',
                            barGap: 0,
                            label: this.labelOption,
                            data: []
                        },
                        {
                            name: '回访订单',
                            type: 'bar',
                            barGap: 0,
                            label: this.labelOption,
                            data: []
                        },
                        {
                            name: '药方订单',
                            type: 'bar',
                            barGap: 0,
                            label: this.labelOption,
                            data: []
                        }


                    ]
                };
                this.barlist.forEach(e => {
                    bar_list.month.push(e.date);
                    bar_list.project_count[0].data.push(e.freeordernum);
                    bar_list.project_count[1].data.push(e.askordernum);
                    bar_list.project_count[2].data.push(e.backordernum);
                    bar_list.project_count[3].data.push(e.prescriptnum);
                });

                this.chart.setOption({
                    title: {
                        text: '平台问诊统计',
                        subtext: '最近一年内各项指数对比'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['免费咨询订单', '问诊订单', '回访订单', '药方订单']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: bar_list.month
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
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
