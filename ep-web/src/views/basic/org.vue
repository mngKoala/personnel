<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入组织简称" />
      <el-button v-permission="['GET /basic/org/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /basic/org/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" element-loading-text="正在查询中。。。" border fit highlight-current-row lazy row-key="id">
      <el-table-column align="left" label="全称" prop="fullName" sortable />
      <el-table-column align="center" label="简称" prop="shortName" />
      <!--
      <el-table-column align="center" label="所属组织" prop="hrmOrganization">
          <template slot-scope="scope">
              <span v-if="scope.row.hrmOrganization.shortName !=''">{{scope.row.hrmOrganization.shortName}}</span>
              <span v-else>{{scope.row.hrmOrganization.fullName}}</span>
          </template>
      </el-table-column>
      -->
      <el-table-column align="center" label="岗位代码" prop="code" />
      <!--
      <el-table-column align="center" label="岗位类型" prop="type" />
      -->
      <el-table-column align="center" label="是否有效" prop="valid">
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
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /basic/org/create']" type="primary" size="mini" @click="handleCreate(scope.row)">添加</el-button>
          <el-button v-permission="['POST /basic/org/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /basic/org/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item v-if="this.orgId != undefined" label="所属组织">
          {{ this.orgName }}
        </el-form-item>
        <el-form-item label="全称" prop="fullName">
          <el-input v-model="dataForm.fullName" />
        </el-form-item>
        <el-form-item label="简称" prop="shortName">
          <el-input v-model="dataForm.shortName" />
        </el-form-item>
        <el-form-item label="代码" prop="code">
          <el-input v-model="dataForm.code" />
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

.classIcon{
  background: url("../../assets/gongsi.png") center no-repeat;
  padding: 1px 8px !important;
}
</style>

<script>
import { tree, getCompany, createOrg, updateOrg, deleteOrg } from '@/api/org'
import { createAdmin, updateAdmin, deleteAdmin } from '@/api/admin'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Org',
  components: { Pagination },
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: function(data, node) {
          if (data.shortName != '' && data.shortName != null) {
            return data.shortName
          } else {
            return data.fullName
          }
        }
      },
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      orgName: undefined,
      orgId: undefined,
      hierarchy: undefined,
      dataForm: {
        parentId: undefined,
        fullName: undefined,
        shortName: undefined,
        type: 'com',
        hierarchy: undefined,
        valid: 1
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑公司',
        create: '创建公司'
      },
      rules: {
        fullName: [
          { required: true, message: '全称不能为空', trigger: 'blur' }
        ],
        valid: [
          { required: true, message: '是否有效不能为空', trigger: 'blur' }
        ]
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
      getCompany(this.listQuery)
        .then(response => {
          this.list = response.data.data
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
        orgId: undefined,
        fullName: undefined,
        shortName: undefined,
        type: 'com',
        hierarchy: undefined,
        valid: 1
      }
    },
    handleCreate(row) {
      if (row != null) {
        this.orgId = row.id
        this.orgName = row.shortName
      } else {
        this.orgId = undefined
        this.orgName = undefined
      }
      this.hierarchy = row.hierarchy
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      if (this.orgId === undefined) { this.dataForm.parentId = '0' } else { this.dataForm.parentId = this.orgId }

      this.dataForm.hierarchy = this.hierarchy
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createOrg(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加公司成功'
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
      this.getList()
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
          updateOrg(this.dataForm)
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
                message: '更新公司成功'
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
      this.getList()
    },
    handleDelete(row) {
      deleteOrg(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除公司成功'
          })
          this.getList()
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
      this.list = []
      this.getList()
    }
  }
}
</script>
