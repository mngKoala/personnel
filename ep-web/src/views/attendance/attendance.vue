<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入管理员名称" />
      <el-button v-permission="['GET /attendance/rec/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /attendance/rec/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="人员" prop="personId" sortable  width="120">
        <template slot-scope="scope">
          {{scope.row.hrPerson.name}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="工号" prop="jobNumber"  width="150"/>
      <el-table-column align="center" label="加班(天)" prop="jbCnt" />
      <el-table-column align="center" label="年假(天)" prop="njCnt" />
      <el-table-column align="center" label="事假(天)" prop="sjCnt" />
      <el-table-column align="center" label="病假(天)" prop="bjCnt" />
      <el-table-column align="center" label="出差(天)" prop="ccCnt" />
      <el-table-column align="center" label="其它(天)" prop="otherCnt" />
      <el-table-column align="center" label="记录时间" prop="recTime"   width="100"/>
      <el-table-column align="center" label="备注" prop="notes" />
      <el-table-column align="center" label="创建时间" prop="createTime"  width="150"/>
      
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width"  fixed="right" width="240">
        <template slot-scope="scope">
          <el-button v-permission="['POST /attendance/rec/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">查看</el-button>
          <el-button v-permission="['POST /attendance/rec/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /attendance/rec/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style=" margin-right:20px;">
        <el-divider content-position="left"><span style="font-size:16px;">人员信息</span></el-divider>
        <el-row>
            <el-col :span="12">
              <el-form-item label="人员" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="记录时间" prop="username">
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
        </el-row>
        <el-divider content-position="left"><span style="font-size:16px;">考勤信息</span></el-divider>
        <el-row>
            <el-col :span="12">
              <el-form-item label="加班(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="事假(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
              <el-form-item label="年假(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="病假(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
        </el-row>    
        <el-row>
            <el-col :span="12">
              <el-form-item label="出差(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="其它(天)" prop="username">
                <el-input v-model="dataForm.username" />
              </el-form-item>
            </el-col>
        </el-row>            
        <el-row>
            <el-col :span="24">
              <el-form-item label="备注" prop="username">
                <el-input v-model="dataForm.username"  type="textarea" :rows="5"/>
              </el-form-item>
            </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.el-dialog__body{
  padding: 0px 10px ;
}
</style>

<script>
import { listAttendanceRec, createAttendanceRec, updateAttendanceRec, deleteAttendanceRec } from '@/api/attendance'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Template from '../salary/template.vue'

export default {
  name: 'AttendanceRec',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
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
        update: '编辑人员考勤',
        create: '添加人员考勤'
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
  },
  methods: {
    getList() {
      this.listLoading = true
      listAttendanceRec(this.listQuery)
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
          createAttendanceRec(this.dataForm)
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
          updateAttendanceRec(this.dataForm)
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
      deleteAttendanceRec(row)
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
    }
  }
}
</script>
