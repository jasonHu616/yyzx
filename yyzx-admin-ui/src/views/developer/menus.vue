<template>
    <div class="app-container">
      <el-row>
        <el-button size="mini" type="success" @click="syncNodes">同步选中节点</el-button>
        <el-button size="mini" type="success" @click="checkAll">选择所有</el-button>
        <el-button size="mini" type="success" @click="cleanAll">取消所有</el-button>
      </el-row>
      <el-tree
        :data="dataList"
        show-checkbox
        default-expand-all
        node-key="keycode"
        ref="tree"
        highlight-current
        :props="treeProps">
      </el-tree>

    </div>
</template>

<script>
  import { asyncRouterMap } from '@/router'
  import {Put} from "../../api/invoke";

  export default {
        name: 'menus',
        data() {
            return {
              nodes: [],
              dataList: [],
              treeProps: {
                children: 'children',
                label: 'title'
              }
            }
        },
        created() {
          this.init();
        },
        methods: {
          init: function(){
            this.dataList = this.loadList(asyncRouterMap, null);
          },
          syncNodes: function(){
            var selected = this.$refs.tree.getCheckedNodes();
            var nodes = this.$refs.tree.getHalfCheckedNodes().concat(selected);
            if(nodes && nodes.length>0) {
              Put('/menu/sync', nodes).then(resp => {

                if(resp.success){
                  this.$message.success('同步完成')
                }else{
                  this.$message.error(resp.message)
                }
              }).catch(err => {
                console.error(err)
                this.$message.error('同步菜单失败')
              })
            }else{
              this.$message.warning('没有需要同步的数据')
            }
          },
          checkAll: function(){
            this.$refs.tree.setCheckedNodes(this.nodes);
          },
          cleanAll: function(){
            this.$refs.tree.setCheckedKeys([]);
          },
          loadList : function(routers, parent){
            var list = [];
            routers.forEach((item, idx) => {
              //console.info(item)
              var node = {
                keycode: item.name,
                parentkey: parent,
                title: item.meta.title,
                icon: item.meta.icon,
                superscope: item.meta.superscope ? 1 : 0,
                devmode: item.meta.devmode ? 1 : 0,
                ordernum: routers.length - idx
              };
              this.nodes.push(node);
              if(item.children && item.children.length>0){
                node.children = this.loadList(item.children, item.name)
              }
              list.push(node)
            })
            return list;
          }
        }
    }
</script>

<style scoped>

</style>
