<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.code" clearable class="filter-item" style="width: 200px;" placeholder="请输入薪酬参数的编号" />
      <el-button v-permission="['GET /salary/parameter/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /salary/parameter/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="编码" prop="code" sortable />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="类型" prop="type">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 'type_str'">字符串</span>
          <span v-if="scope.row.type === 'type_double'">浮点</span>
          <span v-if="scope.row.type === 'type_enum'">枚举</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否参与" prop="valueEn">
        <template slot-scope="scope">
          <span v-if="scope.row.valueEn === '1'">是</span>
          <span v-if="scope.row.valueEn === '0'">否</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="字符串数值" prop="valueStr" />
      <el-table-column align="center" label="浮点数值" prop="valueD" />
      <el-table-column align="center" label="备注" prop="notes" />
      <el-table-column align="center" label="创建时间" prop="createTime" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="220">
        <template slot-scope="scope">
          <el-button v-permission="['POST /salary/month/detail']" type="primary" size="mini" @click="handleDetail(scope.row)">查看</el-button>
          <el-button v-permission="['POST /salary/month/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /salary/month/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style="margin-left:10px;margin-right:50px;">
        <el-form-item label="编号" prop="code">
          <el-input v-model="dataForm.code" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="dataForm.name" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="dataForm.type">
            <el-radio label="type_enum" :disabled="dialogStatus === 'detail'">枚举</el-radio>
            <el-radio label="type_str" :disabled="dialogStatus === 'detail'">字符串</el-radio>
            <el-radio label="type_double" :disabled="dialogStatus === 'detail'">浮点</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否参与" prop="valueEn">
          <el-radio-group v-model="dataForm.valueEn">
            <el-radio label="1" :disabled="dialogStatus === 'detail'">是</el-radio>
            <el-radio label="0" :disabled="dialogStatus === 'detail'">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="字符串数值" prop="valueStr">
          <el-input v-model="dataForm.valueStr" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
        <el-form-item label="浮点数值" prop="valueD">
          <el-input v-model="dataForm.valueD" :disabled="dialogStatus === 'detail'" />
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input v-model="dataForm.notes" type="textarea" :rows="5" :disabled="dialogStatus === 'detail'" />
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
.el-dialog__body{
  padding: 20px 10px ;
}
</style>

<script>
import { listSalaryParameter, createSalaryParameter, updateSalaryParameter, deleteSalaryParameter } from '@/api/salary'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Template from './template.vue'

export default {
  name: 'SalaryParameter',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        code: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        type: 'type_enum',
        valueEn: '1'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑薪酬参数',
        create: '创建薪酬参数',
        detail: '查看薪酬参数'
      },
      rules: {
        code: [
          { required: true, message: '编号不能为空', trigger: 'blur' }
        ],
        name: [{ required: true, message: '名称不能为空', trigger: 'blur' }]
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
      listSalaryParameter(this.listQuery)
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
        type: 'type_enum',
        valueEn: '1'
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
          createSalaryParameter(this.dataForm)
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
          updateSalaryParameter(this.dataForm)
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
      deleteSalaryParameter(row)
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
