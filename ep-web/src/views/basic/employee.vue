<template>
  <div class="app-container">
    <el-row>
      <el-col span="6">
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick">
          <!--
                <template #default="{ node, data }">
                  <span><img :src="require('@/assets/gongsi.png')" /></span>
                  <span>{{ node.label }}</span>
              </template>-->
        </el-tree>
      </el-col>
      <el-col span="18">
        <!-- 查询和其他操作 -->
        <div class="filter-container">
          <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入管理员名称" />
          <el-button v-permission="['GET /admin/admin/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
          <el-button v-permission="['POST /admin/admin/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
          <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
        </div>

        <!-- 查询结果 -->
        <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
          <el-table-column align="center" label="工号" prop="badge" sortable width="150px" />
          {{ list.personList }}
          <el-table-column align="center" label="姓名" prop="hrmPerson" width="150px">
            <template slot-scope="scope">
              {{ scope.row.hrmPerson.name }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="性别" prop="hrmPerson">
            <template slot-scope="scope">
              <span v-if="scope.row.hrmPerson.sex=== 'F'">男</span>
              <span v-else-if="scope.row.hrmPerson.sex=== 'O'">女</span>
              <span v-else>{{ scope.row.hrmPerson.sex }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="手机号" prop="phone" width="100px" />
          <el-table-column align="center" label="部门" prop="" />
          <el-table-column align="center" label="主岗" prop="hrmPositionList">
            <template slot-scope="scope">
              <span v-for="(item,index) in scope.row.hrmPositionList" :key="index">
                <span v-if="item.isPrimary === 1">
                  {{ item.fullName }}
                </span>
              </span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="状态" prop="status">
            <template slot-scope="scope">
              <span v-if="scope.row.status=== 'active'">在职</span>
              <span v-else>{{ scope.row.status }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="类型" prop="type" />
          <el-table-column align="center" label="直属领导" prop="directLeader">
            <template slot-scope="scope">
              <span v-if="scope.row.directLeader != null">
                {{ scope.row.directLeader.hrmPerson.name }}
              </span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="身份证" prop="hrmPerson" width="150px">
            <template slot-scope="scope">
              {{ scope.row.hrmPerson.identity }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="兼岗" prop="">
            <template slot-scope="scope">
              <span v-for="(item,index) in scope.row.hrmPositionList" :key="index">
                <span v-if="item.isPrimary === 0">
                  {{ item.fullName }}
                </span>
              </span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="240px">
            <template slot-scope="scope">
              <el-button v-permission="['POST /admin/admin/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">查看</el-button>
              <el-button v-permission="['POST /admin/admin/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
              <el-button v-permission="['POST /admin/admin/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
      </el-col>
    </el-row>
    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="姓名" prop="name" required="true">
              <el-input v-model="dataForm.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="工号" prop="badge" required="true">
              <el-input v-model="dataForm.badge" placeholder="请输入工号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="类型" prop="type">
              <el-select v-model="dataForm.type" clearable placeholder="请选择类型">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="性别" prop="sex" required="true">
              <el-radio-group v-model="dataForm.sex">
                <el-radio label="F">男</el-radio>
                <el-radio label="O">女</el-radio>
                <el-radio label="T">其它</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="手机号" prop="phone" required="true">
              <el-input v-model="dataForm.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="状态" prop="status" required="true">
              <el-radio-group v-model="dataForm.status">
                <el-radio label="active">在职</el-radio>
                <el-radio label="leave">离职</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="身份证" prop="identity">
              <el-input v-model="dataForm.identity" placeholder="请输入身份证" />
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="直属领导" prop="directLeaderId">
              <el-input v-model="dataForm.directLeaderId" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="主岗" prop="positionPrimaryId" required="true">
              <el-button type="primary" size="mini" @click="handleShow">{{ this.positionPrimaryName }}</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="兼岗" prop="positionPartId">
              <el-input v-model="dataForm.positionPartId" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">保存</el-button>
        <el-button v-else type="primary" @click="updateData">保存</el-button>
      </div>
    </el-dialog>

    <!-- 主岗对话框 -->
    <el-dialog :title="请选择主岗" :visible.sync="dialogVisible" append-to-body="true" width="20%">
      <div>
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClickPosition">
          <span slot-scope="{ node, data }">
            <el-radio
              v-if="data.type === 'position'"
              v-model="radio"
              :label="data.id"
              @change="changePosition(data)"
            >
              {{ node.label }}
            </el-radio>
            <el-radio
              v-else
              v-model="radio"
              :label="data.id"
              disabled
              @change="changePosition(data)"
            >
              {{ node.label }}
            </el-radio>
          </span>
        </el-tree>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="selectPosition">确定</el-button>
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
</style>

<script>
import { tree } from '@/api/org'
import { createAdmin, updateAdmin, deleteAdmin } from '@/api/admin'
import { listEmployee, createEmployee } from '@/api/basic'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Employee',
  components: { Pagination },
  data() {
    return {
      options: [{
        value: '1',
        label: '前台员工'
      }, {
        value: '2',
        label: '后台员工'
      }, {
        value: '3',
        label: '临时员工'
      }],
      value: '',
      radio: 3,
      radioStatus: 3,
      data: null,
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
      uploadPath,
      list: null,
      total: 0,
      roleOptions: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined,
        type: 'com',
        sort: 'add_time',
        order: 'desc'
      },
      selectedPosition: undefined,
      positionPrimaryName: '请选择主岗',
      dataForm: {
        badge: '',
        type: '',
        phone: '',
        status: 'active',
        directLeaderId: '',
        positionPrimaryId: '',
        positionPartId: [],
        name: '',
        sex: 'F',
        identity: ''
      },
      dialogFormVisible: false,
      dialogVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        badge: [{ required: true, message: '工号不能为空', trigger: 'blur' }],
        sex: [{ required: true, message: '性别不能为空', trigger: 'blur' }],
        status: [{ required: true, message: '请选择状态', trigger: 'blur' }],
        phone: [{ required: true, message: '手机号不能为空', trigger: 'blur' }],
        positionPrimaryId: [{ required: true, message: '请选择主岗', trigger: 'blur' }]
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
    this.listQuery.id = '0'
    this.getList()
    this.getTree()
  },
  methods: {
    selectPosition() {
      if (this.selectedPosition != undefined) {
        this.dataForm.positionPrimaryId = this.selectedPosition.id
        this.positionPrimaryName = (this.selectedPosition.shortName === '' ? this.selectedPosition.fullName : this.selectedPosition.shortName)
      }

      this.dialogVisible = false
    },
    changePosition(data) {
      this.selectedPosition = data
    },
    handleNodeClick(data) {
      this.listQuery.id = data.id
      this.listQuery.type = data.type
      this.getList()
    },
    handleNodeClickPosition(data) {
      if (data.type === 'position') {
        this.dataForm.positionPrimaryId = data.id
      }
    },
    getTree() {
      this.listLoading = true
      tree()
        .then(response => {
          this.data = response.data.data
          this.listLoading = false
        })
        .catch(() => {
          this.listLoading = false
        })
    },
    getList() {
      this.listLoading = true
      listEmployee(this.listQuery)
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
      this.positionPrimaryName = '请选择主岗'
      this.dataForm = {
        badge: '',
        type: '',
        phone: '',
        status: 'active',
        directLeaderId: '',
        positionPrimaryId: '',
        positionPartId: '',
        name: '',
        sex: 'F',
        identity: ''
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
    handleShow() {
      this.dialogVisible = true
      this.selectedPosition = undefined
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createEmployee(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加员工成功'
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
                message: '更新员工成功'
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
            message: '删除员工成功'
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
