<template>
  <el-cascader
    :filterable="filterable"
    size="mini"
    :props="props"
    expand-trigger="hover"
    :options="options"
    v-model="selectedOptions"
    :clearable="clearable"
    :placeholder="placeholder"
    @change="handleChange">
  </el-cascader>
</template>

<script>
    import {Get} from "@/api/invoke";

    export default {
        data() {
            return {
                datas: {},
                options: [],
                selectedOptions: []
            }
        },
        props: {
            value: {
                type: String|Number
            },
            props: {
                type:Object,
                default:()=>{
                    return {
                        value: 'id',
                        label: 'title'
                    }
                }
            },
            onSelect: {
                type: Function
            },
            onCreate: {
                type: Function
            },
            clearable: {
                type: Boolean,
                default: false
            },
            placeholder: {type: String},
            filterable:{
                type:Boolean,
                default: false
            },
            url:{
                type:String
            },
            parentNullId:{
                type:Number|String,
                default:null
            }
        },
        mounted() {
        },
        created() {
            this.initData()
        },
        watch: {
            'value' : function(){
                if (this.value!=null && this.value!=""){
                    this.selectedOptions = this.findSelectedValue(this.datas[this.value]);
                }else{
                    this.selectedOptions=[];
                }
            }
        },
        methods: {
            initData: function () {
                this.selectedOptions = [];
                Get(this.url, {}).then(resp => {
                    resp.forEach((item, index) => {
                        this.datas[item.id] = item;
                    });
                    this.options = this.findChild(resp, this.parentNullId);
                    this.selectedOptions = this.findSelectedValue(this.datas[this.value]);
                    if (this.onCreate) {
                        this.onCreate(this.selectedOptions, this.findSelectedNames(this.selectedOptions));
                    }
                }).catch(err => {
                    console.error(err)
                    this.$message.error('加载数据错误')
                })
            },
            findSelectedNames: function (value) {
                let names = [];
                if (value) {
                    value.forEach((item, idx) => {
                        names.push(this.datas[item].title)
                    });
                }
                return names;
            },
            findSelectedValue: function (item) {
                let res = [];
                if (item != null  && this.datas) {
                    let id =item.id;
                    while (id!=null) {
                        res.unshift(id);
                        let parent=this.datas[id];
                        id= parent?parent.parentid:null;
                    }
                }
                //console.info('FIND SELECTED' + JSON.stringify(res))
                return res;
            },
            findChild: function (list, id) {
                let res = [];
                list.forEach((item, index) => {
                    if (item.parentid === id) {
                        let children = this.findChild(list, item.id);
                        if (children && children.length > 0) {
                            item.children = children;
                        }
                        res.push(item);
                    }
                });
                return res;
            },
            handleChange: function (value) {
                this.$emit('input', value[value.length - 1]);
                if (this.onSelect) {
                    this.onSelect(value, this.findSelectedNames(value))
                }
            }
        }
    }
</script>

<style scoped>

</style>
