<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.businessCode" clearable class="filter-item" style="width: 200px;" placeholder="请输入合同编号" />
      <el-button v-permission="['GET /contract/contract/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /contract/contract/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="人员" prop="hrPerson" sortable width="100">
        <template slot-scope="scope">
            {{scope.row.hrPerson.name}}
        </template>        
      </el-table-column>
      <el-table-column align="center" label="合同编号" prop="businessCode" />
      <el-table-column align="center" label="合同类型" prop="type">
        <template slot-scope="scope">
            <span v-if="scope.row.type === 'normal'">普通劳动合同</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
            <span v-if="scope.row.status === 'acting'">履约中</span>
            <span v-if="scope.row.status === 'not_effective'">未生效</span>
            <span v-if="scope.row.status === 'closed'">已结束</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="合同期限" prop="contractDuration" />
      <el-table-column align="center" label="合同年份" prop="contractYear" />
      <el-table-column align="center" label="是否转正" prop="transferToRegular">
        <template slot-scope="scope">
            <span v-if="scope.row.transferToRegular === 'y'">是</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="签约方" prop="hrContractOrg" width="150">
        <template slot-scope="scope">
            {{scope.row.hrContractOrg.name}}
        </template>              
      </el-table-column>
      <el-table-column align="center" label="试用期工资" prop="probationSalary" width="120"/>
      <el-table-column align="center" label="试用期生效时间" prop="probationStartDate"  width="120"/>
      <el-table-column align="center" label="试用期到期时间" prop="probationFinishDate"  width="120"/>
      <el-table-column align="center" label="生效时间" prop="contractStartDate"  width="100"/>
      <el-table-column align="center" label="到期时间" prop="contractFinishDate" width="100"/>
      <el-table-column align="center" label="创建时间" prop="createTime" width="150"/>      
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="220"  fixed="right">
        <template slot-scope="scope">
          <el-button v-permission="['POST /contract/contract/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">查看</el-button>
          <el-button v-permission="['POST /contract/contract/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /contract/contract/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
        <el-scrollbar  :style="{ 'height': 'calc(80vh - 150px)' }">
          <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="center"  label-width="150px" style="margin-right:20px;">
            <el-divider content-position="left"><span style="font-size:16px;">人员信息</span></el-divider>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="人员" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                  </el-col>
              </el-row>
            <el-divider content-position="left"><span style="font-size:16px;">合同信息</span></el-divider>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="合同编号" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="签约方" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="合同年份" prop="username">
                        <el-date-picker
                              v-model="value1"
                              type="year"
                              placeholder="选择合同年份">
                          </el-date-picker>                  
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="合同类型" prop="username">
                        <el-select placeholder="请选择合同类型">
                            <el-option  label="普通劳动合同" value="item.value"></el-option>
                          </el-select>                  
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="合同期限" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="是否转正" prop="username">
                        <el-radio-group v-model="radio">
                          <el-radio label="1">是</el-radio>
                          <el-radio label="0">否</el-radio>
                        </el-radio-group>
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-row>
                  <el-col :span="24">
                      <el-form-item label="状态" prop="username">
                        <el-radio-group v-model="radio">
                          <el-radio label="online">未生效</el-radio>
                          <el-radio label="offline">履约中</el-radio>
                          <el-radio label="non_employee">已结束</el-radio>
                        </el-radio-group>
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                  </el-col>
              </el-row>                        
            <el-divider content-position="left"><span style="font-size:16px;">试用期信息</span></el-divider>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="试用期工资" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="试用期生效时间" prop="username">
                        <el-date-picker
                              v-model="value1"
                              type="date"
                              placeholder="选择试用期生效时间">
                          </el-date-picker>                  
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="试用期到期时间" prop="username">
                        <el-date-picker
                              v-model="value1"
                              type="date"
                              placeholder="选择试用期到期时间">
                          </el-date-picker>                  
                      </el-form-item>
                  </el-col>                  
              </el-row>
            <el-divider content-position="left"><span style="font-size:16px;">转正信息</span></el-divider>
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="工资" prop="username">
                        <el-input v-model="dataForm.username" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="生效时间" prop="username">
                        <el-date-picker
                              v-model="value1"
                              type="date"
                              placeholder="选择生效时间">
                          </el-date-picker>                  
                      </el-form-item>
                  </el-col>
              </el-row>   
              <el-row>
                  <el-col :span="12">
                      <el-form-item label="到期时间" prop="username">
                        <el-date-picker
                              v-model="value1"
                              type="date"
                              placeholder="选择到期时间">
                          </el-date-picker>                  
                      </el-form-item>
                  </el-col>
              </el-row>
            <el-divider content-position="left"><span style="font-size:16px;">其它信息</span></el-divider>
              <el-form-item label="备注" prop="username">
                    <el-input v-model="dataForm.username" />
              </el-form-item>
              <el-form-item label="合同附件" prop="username">
                    <el-button>请选择附件</el-button>
              </el-form-item>
          </el-form>
        </el-scrollbar>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
/**隐藏底部原始滚动条**/
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}

.el-dialog__body{
  padding: 0px 10px ;
}

.el-select{
  display: block;
}
</style>

<script>
import { listPersonContract, createPersonContract, updatePersonContract, deletePersonContract } from '@/api/contract'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Template from '../salary/template.vue'

export default {
  name: 'PersonContract',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        businessCode: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        id: undefined,
        username: undefined,
        password: undefined,
        avatar: undefined,
        roleIds: []
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑人员合同',
        create: '创建人员合同'
      },
      rules: {
        username: [
          { required: true, message: '合同编号不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    headers() {
      return {
        'X-Ep-Token': getToken()
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listPersonContract(this.listQuery)
        .then(response => {
          this.list = response.data.data.list
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        username: undefined,
        password: undefined,
        avatar: undefined,
        roleIds: []
      }
    },
    handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createPersonContract(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加人员合同成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updatePersonContract(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新人员合同成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleDelete(row) {
      deletePersonContract(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除人员合同成功'
          })
          this.getList()
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    }
  }
}
</script>
