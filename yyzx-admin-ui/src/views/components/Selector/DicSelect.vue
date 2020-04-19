<template>
  <el-select :remote="remote" v-model="selectedValue"
             filterable :placeholder="placeholder"
             :clearable="clearable"
             :filter-method="doFilter"
             :remote-method="doFilter"
             :multiple="multiple"
             :disabled="disabled"
             @change="handleChange"
             oading-text="检索中..."
             :popper-class="selectClass">
    <el-option
      v-for="item in dataList"
      :key="item[valueField]"
      :label="item[labelField]"
      :value="item[valueField]">
      <slot :field="item"></slot>
    </el-option>
  </el-select>
</template>

<script>
    import {Post, Get} from "../../../api/invoke";
    import {extend} from '@/utils/index'

    export default {
        data() {
            return {
                selectClass: this.className,
                tempList: [],
                dataList: [],
                selectedValue: null
            }
        },
        props: {
            disabled:{
              type:Boolean,
              default:false
            },
            clearable: {
                type: Boolean,
                default: false
            },
            /**
             * 数据URL
             */
            url: {type: String},
            /**
             * 是否远程搜索
             */
            remote: {type: Boolean, default: false},
            /**
             * 远程请求方法
             */
            method: {type: String, default: 'post'},
            /**
             * 请求参数
             */
            params: {
                type: Object, default() {
                    return {}
                }
            },
            /**
             * 值字段
             */
            valueField: {type: String, default: 'id'},
            /**
             * 文本字段
             */
            labelField: {type: String, default: 'title'},
            /**
             * 当前选中值
             */
            value: {type: String|Number},

            multiple:{type:Boolean,default:false},
            /**
             * 搜索字段
             */
            searchFields: {
                type: Array, default() {
                    return ['keycode', 'title']
                }
            },
            /**
             * 下拉框样式
             */
            className: {type: String},
            change: {type: Function},
            placeholder: {type: String, default: '请选择'}
        },
        watch: {
            'value': function () {
                if (this.value==this.selectedValue){
                    //console.log("内部选择到外部")
                }else{
                    //console.log("外部改变到内部")
                    if (this.value!=null && this.value!=""){
                        if (this.remote) {
                            //console.log("加载初始化数据")
                            let self = this;
                            this.loadData(function (data) {
                                //console.log(data)
                                self.tempList = data;
                                self.dataList = data;
                            },extend({id: (this.multiple?JSON.stringify(this.value):this.value)},this.params,  true))
                        }
                    }
                }
                this.selectedValue = this.value;
            },
          params(){
              this.init()
          }
        },
        created() {
            this.init();
        },
        methods: {
            handleChange: function (value) {
                this.$emit('input', value)
                if (this.change) {
                    if (this.multiple){
                        this.change(value);
                    }else{
                        this.tempList.forEach((item, idx) => {
                            if (item.id === value) this.change(item);
                        });
                    }

                }
            },
            loadData: function (cbk, data) {
                if (this.url) {
                    var method = this.method.toLowerCase() === 'post' ? Post : Get;
                    var params = data || {};
                    method(this.url, params).then(resp => {
                        if (cbk) cbk(resp);
                    }).catch(err => {
                        console.error(err)
                        this.$message.error('读取数据错误')
                    })
                }
            },
            init: function () {
                let self = this;
                let params=this.params;
                if (this.value!=null && this.remote){
                    params.id=this.value;
                }
                this.loadData(function (data) {
                    self.tempList = data;
                    self.dataList = data;
                    self.selectedValue = self.value;
                }, params)
            },
            doFilter: function (query) {
                //console.info('doFilter', query, this.remote)
                if (query === '') {
                    this.dataList = this.tempList;
                } else {
                    if (this.remote) {
                        let self = this;
                        this.loadData(function (data) {
                            self.tempList = data;
                            self.dataList = data;
                        }, extend({keyword: query,id:(this.multiple?JSON.stringify(this.value):this.value)},this.params,  true))

                    } else {
                        this.dataList = this.tempList.filter(item => {
                            var matched = false;
                            this.searchFields.forEach((field, idx) => {
                                if (item[field] && item[field].toLowerCase().indexOf(query) > -1) {
                                    matched = true;
                                }
                            })
                            return matched;
                        })
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>
