<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入租户名称" />
      <el-button v-permission="['GET /basic/tenant/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /basic/tenant/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="租户别名" prop="alias" />
      <el-table-column align="center" label="是否有效">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.valid"
            class="switchStyleTab"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="有效"
            inactive-text="无效"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="租户" prop="" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /basic/tenant/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /basic/tenant/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style="width: 600px; margin-left:50px;">
        <el-form-item label="租户别名" prop="alias" placeholder="请输入租户别名">
          <el-input v-model="dataForm.alias" />
        </el-form-item>
        <el-form-item label="是否有效" prop="valid">
          <el-switch
            v-model="dataForm.valid"
            class="switchStyle"
            active-color="#13ce66"
            :active-value="1"
            :inactive-value="0"
            inactive-color="#ff4949"
            active-text="有效"
            inactive-text="无效"
          />
        </el-form-item>
        <el-form-item label="租户" prop="companyId">
          <el-select v-model="dataForm.companyId" placeholder="请选择租户">
            <el-option
              v-for="(item, index) in companyOption"
              :key="index"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
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

.switchStyle .el-switch__label {
  position: absolute;
  display: none;
  color: #fff;
}
.switchStyle .el-switch__label--left {
  z-index: 9;
  left: 22px;
}
.switchStyle .el-switch__label--right {
  z-index: 9;
  left: 0px;
}
.switchStyle .el-switch__label.is-active {
  display: block;
}
.switchStyle.el-switch .el-switch__core,
.el-switch .el-switch__label {
  width: 60px !important;
}

.switchStyleTab .el-switch__label {
  position: absolute;
  display: none;
  color: #fff;
}
.switchStyleTab .el-switch__label--left {
  z-index: 9;
  left: 8px;
}
.switchStyleTab .el-switch__label--right {
  z-index: 9;
  left: -16px;
}
.switchStyleTab .el-switch__label.is-active {
  display: block;
}
.switchStyleTab.el-switch .el-switch__core,
.el-switch .el-switch__label {
  width: 60px !important;
}
</style>

<script>
import { listCompany, listTenant, createTenant, updateTenant, deleteTenant } from '@/api/company'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Company',
  components: { Pagination },
  data() {
    return {
      uploadPath,
      list: null,
      total: 0,
      roleOptions: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      listQueryCompany: {
        page: 1,
        limit: 1000000,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      companyOption: [],
      dataForm: {
        alias: undefined,
        valid: 1,
        companyId: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑租户',
        create: '添加租户'
      },
      /** ,
      rules: {
        name: [
          { required: true, message: '租户名称不能为空', trigger: 'blur' }
        ]
      }, */
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
    this.getListCompany()
  },
  methods: {
    handleNodeClick(data) {
      console.log(data)
    },
    formatRole(roleId) {
      for (let i = 0; i < this.roleOptions.length; i++) {
        if (roleId === this.roleOptions[i].value) {
          return this.roleOptions[i].label
        }
      }
      return ''
    },
    getListCompany() {
      listCompany(this.listQueryCompany)
        .then(response => {
          this.companyOption = response.data.data.list
        })
        .catch(() => {
        })
    },
    getList() {
      this.listLoading = true
      listTenant(this.listQuery)
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
        alias: undefined,
        valid: 1,
        companyId: undefined
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
          createTenant(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加租户成功'
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
          updateTenant(this.dataForm)
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
                message: '更新租户成功'
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
      deleteTenant(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除租户成功'
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
