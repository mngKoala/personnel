<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入薪酬发放名称" />
      <el-button v-permission="['GET /salary/action/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /salary/action/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="状态" prop="status" sortable>
        <template slot-scope="scope">
          <span v-if="scope.row.status === 'wait'">未发放</span>
          <span v-if="scope.row.status === 'finish'">已发放</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="标签" prop="label">
        <template slot-scope="scope">
          <span v-if="scope.row.label === 'salary'">工资</span>
          <span v-else>{{ scope.row.label }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="月份" prop="actionMonth" />
      <el-table-column align="center" label="模板" prop="hrSalaryTpl">
        <template slot-scope="scope">
          <span v-if="scope.row.hrSalaryTpl != null">{{ scope.row.hrSalaryTpl.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="备注" prop="notes" />
      <el-table-column align="center" label="创建时间" prop="createTime" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="240">
        <template slot-scope="scope">
          <el-button v-permission="['POST /salary/action/detail']" type="primary" size="mini" @click="handleDetail(scope.row)">查看</el-button>
          <el-button v-permission="['POST /salary/action/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /salary/action/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style="margin-right:20px;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="请输入名称" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
        <el-form-item label="标签" prop="label">
          <el-radio-group v-model="dataForm.label">
            <el-radio label="online" :disabled="dialogStatus === 'detail'">工资</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="模板" prop="tplId">
          <el-select v-model="dataForm.tplId" :disabled="dialogStatus === 'detail'" placeholder="请选择">
            <el-option
              v-for="item in listSalaryTemplate"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
            <el-col :span="12" style="margin-top:0px;margin-bottom:30px;">
              <div class="bottomPage">
                <pagination v-show="totalSalaryTemplate>0" :total="totalSalaryTemplate" :page.sync="listQuerySalaryTemplate.page" :limit.sync="listQuerySalaryTemplate.limit" layout="prev, pager, next" prev-text="上一页" next-text="下一页" @pagination="getListSalaryTemplate" />
              </div>
            </el-col>
          </el-select>
        </el-form-item>
        <el-form-item label="月份" prop="actionMonth">
          <el-date-picker
            v-model="dataForm.actionMonth"
            type="month"
            value-format="yyyy-mm"
            placeholder="选择月份"
            :disabled="dialogStatus === 'detail'"
          />
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input v-model="dataForm.notes" type="textarea" :rows="5" placeholder="请输入备注" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
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
</style>

<script>
import { listSalaryAction, createSalaryAction, updateSalaryAction, deleteSalaryAction } from '@/api/salary'
import { listSalaryTemplate } from '@/api/salary'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Template from './template.vue'

export default {
  name: 'SalaryAction',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listSalaryTemplate: null,
      totalSalaryTemplate: 0,
      listLoading: true,
      listQuerySalaryTemplate: {
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
        label: 'online',
        status: 'wait'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑薪酬发放',
        create: '创建薪酬发放',
        detail: '查看薪酬发放'
      },
      rules: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
        tplId: [
          { required: true, message: '模板不能为空', trigger: 'blur' }
        ],
        actionMonth: [
          { required: true, message: '年份不能为空', trigger: 'blur' }
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
    this.getListSalaryTemplate()
  },
  methods: {
    getListSalaryTemplate() {
      listSalaryTemplate(this.listQuerySalaryTemplate)
        .then(response => {
          this.listSalaryTemplate = response.data.data.list
          this.totalSalaryTemplate = response.data.data.total
        })
        .catch(() => {
          this.listSalaryTemplate = []
          this.totalSalaryTemplate = 0
        })
    },
    getList() {
      this.listLoading = true
      listSalaryAction(this.listQuery)
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
        label: 'online',
        status: 'wait'
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
          createSalaryAction(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加薪酬发放成功'
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
          updateSalaryAction(this.dataForm)
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
                message: '更新薪酬发放成功'
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
      deleteSalaryAction(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除薪酬发放成功'
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
