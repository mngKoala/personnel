<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入管理员名称" />
      <el-button v-permission="['GET /admin/admin/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/admin/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="姓名" prop="name" />
      <el-table-column align="center" label="员工状态" prop="employeeStatus">
        <template slot-scope="scope">
            <span v-if="scope.row.employeeStatus === 'non_employee'">非员工</span>
            <span v-if="scope.row.employeeStatus === 'online'">在职</span>
            <span v-if="scope.row.employeeStatus === 'offline'">离职</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="员工标记" prop="employeeIdentityStatus">
        <template slot-scope="scope">
            <span v-if="scope.row.employeeIdentityStatus === 'formal'">正式</span>
            <span v-if="scope.row.employeeIdentityStatus === 'ontrial'">试用</span>
            <span v-if="scope.row.employeeIdentityStatus === 'practice'">实习</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="人员类型" prop="employeeTypeCode">
        <template slot-scope="scope">
            <span v-if="scope.row.employeeTypeCode === 'contract_worker'">合同工</span>
            <span v-if="scope.row.employeeTypeCode === 'dispatcher'">派遣工</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="联系方式" prop="contactInformation" />
      <el-table-column align="center" label="薪酬模板" prop="salaryTplId" />
      <el-table-column align="center" label="是否发放" prop="salaryPayOut">
        <template slot-scope="scope">
            <span v-if="scope.row.salaryPayOut === '1'">是</span>
        </template>        
      </el-table-column>
      <el-table-column align="center" label="薪酬备注" prop="salaryNotes" />
      <el-table-column align="center" label="员工" prop="employeeId" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="220">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/admin/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">查看</el-button>
          <el-button v-permission="['POST /admin/admin/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/admin/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" style="border:1px;">
      <el-scrollbar  :style="{ 'height': 'calc(90vh - 150px)' }">
        <el-divider content-position="left"><span style="font-size:18px;">基本信息</span></el-divider>
        <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="120px" style="margin-left:5px;margin-right:10px;">
          <el-row>
              <el-col :span="12">
                <el-form-item label="姓名" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="人员类型" prop="">
                  <el-select placeholder="请选择人员类型">
                      <el-option
                        v-for="item in personType"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>                  
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="身份证" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出生日期" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择出生日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="员工状态" prop="username">
                    <el-radio-group v-model="radio">
                      <el-radio :label="online">在职</el-radio>
                      <el-radio :label="offline">离职</el-radio>
                      <el-radio :label="non_employee">非员工</el-radio>
                    </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="民族" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>                
          <el-row>
              <el-col :span="12">
                <el-form-item label="员工标记" prop="username">
                  <el-select placeholder="请选择员工标记">
                      <el-option label="正式"  value="formal"></el-option>
                      <el-option label="试用"  value="ontrial"></el-option>
                      <el-option label="实习"  value="practice"></el-option>
                    </el-select>                           
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="籍贯" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>   
          <el-row>
              <el-col :span="12">
                <el-form-item label="工号" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="员工" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>   
          <el-row>
              <el-col :span="12">
                <el-form-item label="性别" prop="username">
                  <el-select placeholder="请选择性别">
                      <el-option label="男"  value="O"></el-option>
                      <el-option label="女"  value="F"></el-option>
                      <el-option label="其它"  value="T"></el-option>
                  </el-select>                           
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="婚姻" prop="username">
                  <el-select placeholder="请选择婚姻状况">
                      <el-option label="已婚"  value="married"></el-option>
                      <el-option label="离婚"  value="unmarried"></el-option>
                  </el-select>                           
                </el-form-item>
              </el-col>
          </el-row>                      
        <el-divider content-position="left"><span style="font-size:18px;">个人情况</span></el-divider>
          <el-row>
              <el-col :span="12">
                <el-form-item label="联系方式" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="电子邮件" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="紧急联系人" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="紧急人电话" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="家庭地址" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="微信号" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>                
          <el-row>
              <el-col :span="12">
                <el-form-item label="学历" prop="username">
                  <el-select placeholder="请选择学历">
                      <el-option label="高中"  value="high"></el-option>
                      <el-option label="大专"  value="junior_college"></el-option>
                      <el-option label="初中"  value="middle"></el-option>
                      <el-option label="研究生"  value="postgraduate"></el-option>
                      <el-option label="小学"  value="primary"></el-option>
                      <el-option label="本科"  value="undergraduate"></el-option>
                  </el-select>                           
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="毕业院校" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>   
          <el-row>
              <el-col :span="12">
                <el-form-item label="所学外语" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="外语证书" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>   
          <el-row>
              <el-col :span="12">
                <el-form-item label="计算机能力" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="计算机等级" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row> 
          <el-row>
              <el-col :span="12">
                <el-form-item label="政治面貌" prop="username">
                  <el-select placeholder="请选择政治面貌">
                      <el-option label="党员"  value="dy"></el-option>
                      <el-option label="群众"  value="qz"></el-option>
                      <el-option label="团员"  value="ty"></el-option>
                  </el-select>                  
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="入党时间" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="血型" prop="username">
                  <el-select placeholder="请选择血型">
                      <el-option label="A"  value="A"></el-option>
                      <el-option label="B"  value="B"></el-option>
                      <el-option label="AB"  value="AB"></el-option>
                      <el-option label="O"  value="O"></el-option>
                  </el-select>                  
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="身高" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="12">
                <el-form-item label="体重" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
              </el-col>
          </el-row>                                             
        <el-divider content-position="left"><span style="font-size:18px;">工作信息</span></el-divider>
          <el-row>
              <el-col :span="12">
                <el-form-item label="入职日期" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择入职日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="初次日期" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择初次日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
          </el-row>    
          <el-row>
              <el-col :span="12">
                <el-form-item label="转正日期" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择转正日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="参加工作时间" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择参加工作日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
          </el-row>    
          <el-row>
              <el-col :span="12">
                <el-form-item label="员工岗位" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="员工职称" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>                                                         
          <el-row>
              <el-col :span="12">
                <el-form-item label="离职日期" prop="username">
                  <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择离职日期">
                    </el-date-picker>                  
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="员工职级" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>                                                         
          <el-row>
              <el-col :span="12">
                <el-form-item label="离职原因" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
              </el-col>
          </el-row>                                                         
        <el-divider content-position="left"><span style="font-size:18px;">工资信息</span></el-divider>
          <el-row>
              <el-col :span="12">
                <el-form-item label="是否发放" prop="username">
                    <el-radio-group v-model="radio">
                      <el-radio :label="1">是</el-radio>
                      <el-radio :label="0">否</el-radio>
                    </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="工资卡开户行" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>    
          <el-row>
              <el-col :span="12">
                <el-form-item label="薪酬模版" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="工资卡帐号" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
          </el-row>  
          <el-row>
              <el-col :span="12">
                <el-form-item label="薪酬备注" prop="username">
                  <el-input v-model="dataForm.username" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
              </el-col>
          </el-row>
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
</style>

<script>
import { listAdmin, createAdmin, updateAdmin, deleteAdmin } from '@/api/register'
import { roleOptions } from '@/api/role'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'PersonRegister',
  components: { Pagination },
  data() {
    return {
      personType:[{
          value: 'contract_worker',
          label: '合同工'
        }, {
          value: 'dispatcher',
          label: '派遣工'
        }, 
      ],
      uploadPath,
      list: null,
      total: 0,
      roleOptions: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
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
        update: '编辑人员信息',
        create: '添加人员信息'
      },
      rules: {
        username: [
          { required: true, message: '管理员名称不能为空', trigger: 'blur' }
        ],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      },
      downloadLoading: false
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

    roleOptions()
      .then(response => {
        this.roleOptions = response.data.data.list
      })
  },
  methods: {
    formatRole(roleId) {
      for (let i = 0; i < this.roleOptions.length; i++) {
        if (roleId === this.roleOptions[i].value) {
          return this.roleOptions[i].label
        }
      }
      return ''
    },
    getList() {
      this.listLoading = true
      listAdmin(this.listQuery)
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
    uploadAvatar: function(response) {
      this.dataForm.avatar = response.data.url
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
          createAdmin(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加管理员成功'
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
          updateAdmin(this.dataForm)
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
                message: '更新管理员成功'
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
      deleteAdmin(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除管理员成功'
          })
          this.getList()
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['管理员ID', '管理员名称', '管理员头像']
        const filterVal = ['id', 'username', 'avatar']
        excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          '管理员信息'
        )
        this.downloadLoading = false
      })
    }
  }
}
</script>
