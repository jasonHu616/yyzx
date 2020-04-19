<template>
  <div class="datagrid-container">
    <el-table header-row-class-name="warning-row-head" size="mini" stripe :key='tableKey' current-row-key="id" :data="gridData" v-loading="gridLoading"
              element-loading-text="数据加载中..."  border fit highlight-current-row :empty-text="gridText"
              style="width: 100%" @current-change="handleCurrentChange" :height="height || null" @row-click="rowClick" @selection-change="selectionChange"
              :row-class-name="tableRowClassName" ref="dataTable" :stripe="false"
              @sort-change='sortChange'>
      <el-table-column
        type="index"
        :index="indexNum">
      </el-table-column>
      <slot></slot>
    </el-table>

    <div v-if="type==='grid'" class="pagination-container">
      <el-pagination small background @size-change="handlePageSizeChange"
                     @current-change="handlePageCurrentChange"
                     :current-page="pageParams.page"
                     :page-count="pageParams.pageCount"
                     :page-sizes="[10,20,30,50]"
                     :page-size="pageParams.limit"
                     layout="total, sizes, prev, pager, next, jumper" :total="pageParams.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {extend} from '@/utils/index'
    import {Post} from '@/api/invoke'

    export default {
        data() {
            return {
                gridText: '暂无数据',
                gridLoading: true,
                tableKey: 0,
                gridData: null,
                currentRow: null,
                filter: {
                    order:""
                },
                pageParams: {
                    total: 0,
                    limit: this.pagesize || 10,
                    page: 1,
                    pageCount: 0
                }
            }
        },
        created() {
            this.getList()
        },
        methods: {
            tableRowClassName({row, rowIndex}) {
                if (rowIndex%4 === 0) {
                    return 'warning-row-yellow';
                } else if (rowIndex%4 === 2) {
                    return 'warning-row-green';
                }
                return '';

            },
            onRowClick: function(row){
                if(this.rowClick){
                    this.rowClick(row);
                }
            },
            handleCurrentChange: function (row, oldrow) {
                this.currentRow = row;
                this.$emit('change-row', row)
                /*if(this.onChangeRow){
                  this.onChangeRow(row)
                }*/
            },
            indexNum(index) {
                return (index + 1) + (this.pageParams.limit * (this.pageParams.page - 1));
            },
            handlePageSizeChange: function (val) {
                this.pageParams.limit = val
                this.getList()
            },
            handlePageCurrentChange(val) {
                this.pageParams.page = val
                this.getList()
            },
            reload: function (filter) {
                this.pageParams.page = 1;
                this.filter = filter;
                this.getList();
            },
            sortChange:function(temp){
                if (temp.prop==null){
                    this.filter.order=null;
                }else{
                    let order = temp.prop+" ";
                    if (temp.order=="ascending"){
                        order+="asc";
                    }else{
                        order+="desc";
                    }
                    this.filter.order=order;
                }
                this.getList();
            },
            getList: function () {
                this.gridLoading = true
                if (!this.roleAction || this.roleAction === '' || this.roleHasAction(this.roleAction)) {
                    this.gridText = '暂无数据';
                    var params  = this.params || {};
                    this.filter = this.filter || {};
                    this.filter.page = this.pageParams ? ((this.pageParams.page==null || this.pageParams.page<=0) ? 1 : this.pageParams.page) : null;
                    this.filter.size = this.pageParams ? ((this.pageParams.limit==null || this.pageParams.limit<=0) ? 10 : this.pageParams.limit) : null;
                    this.filter = extend(params,this.filter,  true)
                    Post(this.url, this.filter)
                        .then(res => {
                            if (res) {
                                //var res = resp;
                                this.gridData = this.type === 'grid' ? res.list : res;
                                if (this.type === 'grid') {
                                    this.pageParams.total = res.total;
                                    this.pageParams.limit = res.pageSize;
                                    this.pageParams.page = res.pageNum;
                                    this.pageParams.pageCount = res.pages;
                                }

                                if(this.getDataSuccess){
                                    this.getDataSuccess(this.gridData)
                                }

                            } else {
                                this.$message.error((res && res.result && res.message) ? res.message : '加载数据错误');
                            }
                        })
                        .catch(err => {
                            console.error(err)
                            this.$message.error(err.message)
                        })
                    //console.info('gridurl:'+this.url)
                } else {
                    this.gridText = '您没有数据浏览权限';
                    this.gridData = [];
                    if (this.type === 'grid') {
                        this.pageParams.total = 0;
                        this.pageParams.limit = 10;
                        this.pageParams.page = 1;
                        this.pageParams.pageCount = 0;
                    }
                }
                this.gridLoading = false
            },
            /**
             * 清除表单数据
             */
            clearData(){
                this.gridData=null;
            },
            /**
             * 切换单条记录选中状态
             * @param row 记录行
             */
            toggleSelection: function(row){
                this.$refs.dataTable.toggleRowSelection(row);
            },

            /**
             * 清除全部多选框选中状态
             */
            clearSelection: function(){
                this.$refs.dataTable.clearSelection();
            },

            /**
             * 切换多条记录选中状态
             * @param rows 记录行数组
             */
            toggleSelections: function(rows){
                rows.forEach(r => {
                    this.$refs.dataTable.toggleRowSelection(r);
                })
            },

        },
        props: {
            roleAction: {type: String, default: null},
            url: {
                type: String,
                default: null
            },
            height: [String, Number],
            rowClick: {
                type: Function, default: function(){}
            },
            type: {
                type: String, default: 'grid'
            },
            params: {
                type: Object
            },
            pageSize: {
                type: Number,
                default: 10
            },
            pageable: {
                type: Boolean, default: true
            },
            selectionChange: {
                type: Function,default: function(){}
            },

            /**
             * 获取数据成功调用方法
             */
            getDataSuccess: {
                type: Function,default: function(){}
            }

        }
    }
</script>

<style>
  .warning-row-yellow {
    background: oldlace !important;
  }
  .warning-row-green{
    background: #f0f9eb !important;
  }


  .el-table th, .el-table tr .warning-row-head{
    //background: rgb(48,65,86) !important;
    //color: #ffffff;
  }

</style>
