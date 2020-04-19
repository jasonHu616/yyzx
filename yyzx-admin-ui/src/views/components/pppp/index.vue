<template>
  <el-row>
    <el-col :span="24" style="border:1px solid #c0c4cc; border-radius:5px; padding:15px;">
      <el-col :span="24" style="padding-bottom:15px;">药方信息</el-col>
      <el-col :span="24">
        <div style="display: flex; padding-bottom:15px;">
          <div style="width:60px;">订单号：</div>
          <div>{{formData.orderno}}</div>
        </div>
      </el-col>
      <el-col :span="24" style=" padding-bottom:15px;">
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">患者：</div>
            <div>{{formData.accountusername}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">性别：</div>
            <div v-if="formData.patientsex==0">男</div>
            <div v-if="formData.patientsex==1">女</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">年龄：</div>
            <div>{{formData.patientage}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:60px;">身份证：</div>
            <div>{{formData.patientidcard}}</div>
          </div>
        </el-col>
      </el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <el-col :span="6" v-if="formData.prescriptinfo">
          <div style="display: flex;">
            <div style="width:45px;">诊断：</div>
            <div>{{formData.prescriptinfo.diagnose}}</div>
          </div>
        </el-col>
        <el-col :span="6" v-if="formData.prescriptinfo">
          <div style="display: flex;">
            <div style="width:45px;">用法：</div>
            <div v-if="formData.prescriptinfo.iswithin==0">内服</div>
            <div v-if="formData.prescriptinfo.iswithin==1">外用</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">剂型：</div>
            <div>{{formData.kindtitle}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">代煎：</div>
            <div v-if="formData.ismake==1">需要代煎</div>
            <div v-if="formData.ismake==0">不需要代煎</div>
          </div>
        </el-col>
      </el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">药房：</div>
            <div>{{formData.pharmacytitle}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:60px;">可见度：</div>
            <div>{{formData.hidetypetitle}}</div>
          </div>
        </el-col>
        <el-col :span="12" v-if="formData.prescriptinfo">
          <div style="display: flex;">
            <div style="width:45px;">方案：</div>
            <div>共{{formData.countnum}}副，每{{formData.prescriptinfo.daynum}}天{{formData.prescriptinfo.countnum1}}副，每天分{{formData.prescriptinfo.timenum}}次{{formData.prescriptinfo.iswithin==0?'服':'使'}}用</div>
          </div>
        </el-col>
      </el-col>

      <el-col :span="24" style="padding-bottom:15px;" v-if="formData.prescriptinfo">
        <div style="display: flex;">
          <div style="width:45px;">医嘱：</div>
          <div>{{formData.tabu}};{{formData.eattime}};{{formData.doctorremark}}</div>
        </div>
      </el-col>
      <el-col :span="24"  v-if="formData.prescriptinfo">
        <div style="display: flex;">
          <div style="width:72px;">特殊用法：</div>
          <div>{{formData.prescriptinfo.specialmethod==null?'--':formData.prescriptinfo.specialmethod}}</div>
        </div>
      </el-col>
    </el-col>
    <el-col :span="24" style="border:1px solid #c0c4cc; border-radius:5px; padding:15px; margin-top:15px">
      <el-col :span="24" style="padding-bottom:15px;">收货信息</el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <div style="display: flex;">
          <div style="width:72px;">配送方式：</div>
          <div v-if="formData.ispick==1">自取</div>
          <div v-if="formData.ispick==0">{{formData.expresstitle}}</div>
        </div>
      </el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <el-col :span="8">
          <div style="display: flex;">
            <div style="width:60px;">收货人：</div>
            <div>{{formData.ispick==1?formData.patientname:formData.addressname}}</div>
          </div>
        </el-col>
        <el-col :span="16">
          <div style="display: flex;">
            <div style="width:45px;">电话：</div>
            <div>{{formData.ispick==1?formData.patientphone:formData.addressphone}}</div>
          </div>
        </el-col>
      </el-col>
      <el-col :span="24">
        <div style="display: flex;">
          <div style="width:72px;">收货地址：</div>
          <div>{{formData.address}}</div>
        </div>
      </el-col>
    </el-col>
    <el-col :span="24" style="border:1px solid #c0c4cc; border-radius:5px; padding:15px; margin-top:15px">
      <el-col :span="24" style="padding-bottom:15px;">费用明细</el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:45px;">药费：</div>
            <div>￥{{formData.total}}</div>
          </div>
        </el-col>
        <!--<el-col :span="6" v-if="formData.prescriptinfo">
          <div style="display: flex;">
            <div style="width:45px;">副数：</div>
            <div>{{formData.prescriptinfo.countnum}}副</div>
          </div>
        </el-col>-->
        <el-col :span="12">
          <div style="display: flex;">
            <div style="width:45px;">单价：</div>
            <div>￥{{formData.prescriptcontenttotal}}</div>
          </div>
        </el-col>
      </el-col>
      <el-col :span="24">
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:60px;">代煎费：</div>
            <div>￥{{formData.maketotal||0}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:60px;">物流费：</div>
            <div>￥{{formData.expresstotal||0}}</div>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="display: flex;">
            <div style="width:84px;">医事服务费：</div>
            <div>￥{{formData.servertotal||0}}</div>
          </div>
        </el-col>
      </el-col>
    </el-col>
    <el-col :span="24" style="border:1px solid #c0c4cc; border-radius:5px; padding:15px; margin-top:15px">
      <el-col :span="24" style="padding-bottom:15px;">RP:</el-col>
      <el-col :span="24">
        <v-page-grid role-action="list" ref="newsgrid" url="/prescription/prescriptcontent/grid" :params="{prescriptid:formData.id}" :page-size="20" style="padding-bottom:30px;">
          <el-table-column prop="medpharmacytitle" label="药品" align="center"></el-table-column>
          <el-table-column prop="num" label="用量" align="center"></el-table-column>
          <el-table-column prop="num" label="单位" align="center"></el-table-column>
          <el-table-column prop="total" label="单价" align="center"></el-table-column>
          <el-table-column label="是否超药典" align="center">
            <template slot-scope="scope">
              <el-tag size="mini" v-if="scope.row.mls==0" type="">否</el-tag>
              <el-tag size="mini" v-if="scope.row.mls==1" type="success">是</el-tag>
            </template>
          </el-table-column>
        </v-page-grid>
      </el-col>
    </el-col>
    <el-col :span="24" style="border:1px solid #c0c4cc; border-radius:5px; padding:15px; margin-top:15px">
      <el-col :span="24" style="padding-bottom:15px;">人物信息</el-col>
      <el-col :span="24" style="padding-bottom:15px;">
        <el-col :span="6">
          <div style="display: flex;">
            <div style="width:60px;">就诊人：</div>
            <div>{{formData.patientname}}</div>
          </div>
        </el-col>
        <el-col :span="18">
          <div style="display: flex;">
            <div style="width:72px;">开方医生：</div>
            <div>{{formData.doctorname}}</div>
          </div>
        </el-col>
      </el-col>
      <el-col :span="24" style="padding-bottom:8px;">
        <div style="display: flex;">
          <div style="width:72px;">平台审核：</div>
          <div>{{formData.acceptname}}</div>
        </div>
      </el-col>
      <el-col :span="24">
        <el-col :span="8">
          <div style="display: flex;">
            <div style="width:72px; line-height:28px;">药房审核：</div>
            <div style="line-height:28px;">{{formData.acceptname}}</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="display: flex;">
            <div style="width:45px; line-height:28px;">抓药：</div>
            <div>
              <v-dic-select method="get" url="/indexdata/doctor/all" :params="{}" labelField="realname" :remote="true" clearable class="filter-item"
                            v-model="orderfilter.doctorid" placeholder="输入抓药人姓名">
                <template slot-scope="scope">
                  <span style="float: left">{{scope.field.realname}}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ scope.field.phone}}</span>
                </template>
              </v-dic-select>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="display: flex;">
            <div style="width:45px; line-height:28px;">复核：</div>
            <div>
              <v-dic-select method="get" url="/indexdata/doctor/all" :params="{}" labelField="realname" :remote="true" clearable class="filter-item"
                            v-model="orderfilter.doctorid" placeholder="输入抓药人姓名">
                <template slot-scope="scope">
                  <span style="float: left">{{scope.field.realname}}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ scope.field.phone}}</span>
                </template>
              </v-dic-select>
            </div>
          </div>
        </el-col>
      </el-col>
    </el-col>
    <el-col :span="24" style="padding-bottom:15px;"></el-col>
  </el-row>
</template>

<script>
    export default {
        name: "index",
      props:{
        formData:{
          type:Object,
          default(){
            return{};
          }
        }
      }
    }
</script>

<style scoped>

</style>
