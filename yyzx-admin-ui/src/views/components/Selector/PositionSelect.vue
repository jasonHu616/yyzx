<template>
  <el-cascader
    :filterable="filterable"
    size="mini"
    :props="props"
    expand-trigger="hover"
    :change-on-select="changeOnSelect"
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
                props: {
                    value: 'id',
                    label: 'title'
                },
                positions: {},
                positionOfCodes:{},
                options: [],
                selectedOptions: []
            }
        },
        props: {
            value: {
                type: String|Number
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
            changeOnSelect:{
              type:Boolean,
              default:false
            },
            placeholder: {type: String},
            filterable:{
                type:Boolean,
                default: false
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
                    this.initData();
                }else{
                    this.selectedOptions=[];
                }
            }
        },
        methods: {
            initData: function () {
                this.selectedOptions = [];
                if (this.options != null && this.options.length > 0) {
                    this.selectedOptions = this.findSelectedValue(this.positions[this.value]);
                    if (this.onCreate) {
                        this.onCreate(this.selectedOptions, this.findSelectedNames(this.selectedOptions));
                    }
                } else {
                  Get('/indexdata/positions/all', {}).then(resp => {
                      resp.forEach((item, index) => {
                          this.positions[item.id] = item;
                          this.positionOfCodes[item.keycode] = item;
                      });
                      this.options = this.findChild(resp, "0");
                      this.selectedOptions = this.findSelectedValue(this.positions[this.value]);
                      if (this.onCreate) {
                          this.onCreate(this.selectedOptions, this.findSelectedNames(this.selectedOptions));
                      }
                  }).catch(err => {
                      console.error(err)
                      this.$message.error('加载区域数据错误')
                  })
              }
            },
            findSelectedNames: function (value) {
                var names = [];
                if (value) {
                    value.forEach((item, idx) => {
                        names.push(this.positions[item].title)
                    });
                }
                return names;
            },
            findSelectedValue: function (item) {
                let res = [];
                if (item != null  && this.positions) {
                    let id =item.id;
                    let code =item.keycode;
                    while (id!=null) {
                        res.unshift(id);
                        let p = this.positionOfCodes[code];
                        let parentp=this.positionOfCodes[p.parentcode];
                        code = parentp?parentp.keycode:"0";
                        id= parentp?parentp.id:null;
                    }
                }
                //console.info('FIND SELECTED' + JSON.stringify(res))
                return res;
            },
            findChild: function (list, pcode) {
                var res = [];
                list.forEach((item, index) => {
                    if (item.parentcode === pcode) {
                        var children = this.findChild(list, item.keycode);
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
