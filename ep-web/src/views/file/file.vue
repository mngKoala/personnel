<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入人员档案名称" />
      <el-button v-permission="['GET /person/file/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /person/file/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="档案编号" prop="code" sortable />
      <el-table-column align="center" label="档案状态" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 'out'">调出</span>
          <span v-if="scope.row.status === 'in'">调入</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="人员" prop="hrPerson">
        <template slot-scope="scope">
          <span v-if="scope.row.hrPerson != null">{{ scope.row.hrPerson.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否新建档" prop="ifNew">
        <template slot-scope="scope">
          <span v-if="scope.row.ifNew === 'yes'">是</span>
          <span v-if="scope.row.ifNew === 'no'">否</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="来源" prop="source" />
      <el-table-column align="center" label="档案保管地" prop="saveLoc" />
      <el-table-column align="center" label="参加工作时间" prop="workStartDate" />
      <el-table-column align="center" label="附件" prop="file" />
      <el-table-column align="center" label="备注" prop="note" />
      <el-table-column align="center" label="创建时间" prop="createTime" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="300">
        <template slot-scope="scope">
          <el-button v-permission="['POST /person/file/detail']" type="primary" size="mini" @click="handleDetail(scope.row)">查看</el-button>
          <el-button v-permission="['POST /person/file/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /person/file/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="150px" style="margin-right:20px;">
        <el-row>
          <el-col :span="12">
            <el-form-item label="人员" prop="userId">
              <el-select v-model="dataForm.userId" :disabled="dialogStatus === 'detail'" placeholder="请选择">
                <el-option
                  v-for="item in listPerson"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
                <el-col :span="12" style="margin-top:0px;margin-bottom:30px;">
                  <div class="bottomPage">
                    <pagination v-show="totalPerson>0" :total="totalPerson" :page.sync="listQueryPerson.page" :limit.sync="listQueryPerson.limit" layout="prev, pager, next" prev-text="上一页" next-text="下一页" @pagination="getListPerson" />
                  </div>
                </el-col>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="参加工作时间" prop="workStartDate">
              <el-date-picker
                v-model="dataForm.workStartDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择参加工作时间"
                :disabled="dialogStatus === 'detail'"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="档案保管地" prop="saveLoc">
              <el-input v-model="dataForm.saveLoc" :disabled="dialogStatus === 'detail'" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="档案编号" prop="code">
              <el-input v-model="dataForm.code" :disabled="dialogStatus === 'detail'" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否新建档" prop="ifNew">
              <el-radio-group v-model="dataForm.ifNew">
                <el-radio label="yes" :disabled="dialogStatus === 'detail'">是</el-radio>
                <el-radio label="no" :disabled="dialogStatus === 'detail'">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源" prop="source">
              <el-input v-model="dataForm.source" :disabled="dialogStatus === 'detail'" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="note">
              <el-input v-model="dataForm.note" :disabled="dialogStatus === 'detail'" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="附件" prop="file">
              <el-button :disabled="dialogStatus === 'detail'">请选择附件</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-if="dialogStatus=='update'" type="primary" @click="updateData">确定</el-button>
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
import { listPersonFile, createPersonFile, updatePersonFile, deletePersonFile } from '@/api/file'
import { listPerson } from '@/api/register'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'PersonFile',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listPerson: null,
      totalPerson: 0,
      listQueryPerson: {
        page: 1,
        limit: 5
      },
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        ifNew: 'yes',
        status: 'in'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑人员档案',
        create: '添加人员档案',
        detail: '查看人员档案'
      },
      rules: {
        userId: [
          { required: true, message: '人员不能为空', trigger: 'blur' }
        ],
        ifNew: [{ required: true, message: '请选择是否新建档案', trigger: 'blur' }]
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
    this.getListPerson()
  },
  methods: {
    getListPerson() {
      listPerson(this.listQueryPerson)
        .then(response => {
          this.listPerson = response.data.data.list
          this.totalPerson = response.data.data.total
        })
        .catch(() => {
          this.listPerson = []
          this.totalPerson = 0
        })
    },
    getList() {
      this.listLoading = true
      listPersonFile(this.listQuery)
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
        ifNew: 'yes',
        status: 'in'
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
          createPersonFile(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加人员档案成功'
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
      this.list = []
      this.total = 0
      this.getList()
    },
    handleDetail(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'detail'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
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
          updatePersonFile(this.dataForm)
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
                message: '更新人员档案成功'
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
      this.list = []
      this.total = 0
      this.getList()
    },
    handleDelete(row) {
      deletePersonFile(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除人员档案成功'
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
