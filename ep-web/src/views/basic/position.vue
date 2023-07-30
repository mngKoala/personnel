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
              <el-table-column align="center" label="全称" prop="fullName" sortable />
              <el-table-column align="center" label="简称" prop="shortName" />
              <el-table-column align="center" label="所属组织" prop="hrmOrganization">
                  <template slot-scope="scope">
                      <span v-if="scope.row.hrmOrganization.shortName !=''">{{scope.row.hrmOrganization.shortName}}</span>
                      <span v-else>{{scope.row.hrmOrganization.fullName}}</span>
                  </template>
              </el-table-column>
              <el-table-column align="center" label="岗位代码" prop="code" />
              <el-table-column align="center" label="岗位类型" prop="type" />
              <el-table-column align="center" label="是否有效" prop="valid">
                  <template slot-scope="scope">
                    <el-switch
                      class="switchStyleTab"
                      v-model="scope.row.valid"
                      :active-value="1"
                      :inactive-value="0"
                      active-color="#13ce66"
                      inactive-color="#ff4949"  active-text="有效" inactive-text="无效">
                    </el-switch>
                  </template>              
              </el-table-column>
              <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
                <template slot-scope="scope">
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
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="100px" style="width: 600px; margin-left:50px;">
        <el-form-item label="所属组织">
          {{this.orgName}}
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
        <el-form-item label="岗位类型" prop="type">
          <el-input v-model="dataForm.type" />
        </el-form-item>
        <el-form-item label="是否有效" prop="valid">
          <el-switch
            class="switchStyle"
            v-model="dataForm.valid"
            active-color="#13ce66"
            :active-value="1"
            :inactive-value="0"
            inactive-color="#ff4949"  active-text="有效" inactive-text="无效">
          </el-switch>
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
import { tree, getName } from '@/api/org'
import { createAdmin, updateAdmin, deleteAdmin } from '@/api/admin'
import { listPosition, createPosition, readminPosition, updatePosition, deletePosition} from '@/api/basic'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Admin',
  components: { Pagination },
  data() {
    return {
      data: null,
      defaultProps: {
        children: 'children',
        label: function(data,node){
            if(data.shortName != "" && data.shortName != null ){
              return data.shortName
            }else{
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
        id:undefined,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      orgName: undefined,
      orgId: undefined,
      dataForm: {
        orgId: undefined,
        fullName: undefined,
        shortName: undefined,
        type: undefined,
        valid: 1
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
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
    this.getTree()
    this.getList()
  
    roleOptions()
      .then(response => {
        this.roleOptions = response.data.data.list
      })
  },
  methods: {
    handleNodeClick(data) {
      //console.log(data)
      this.orgId = data.id

      this.listQuery.id =  data.id;
      this.getList()
      this.getName(data.id)
    },
    formatRole(roleId) {
      for (let i = 0; i < this.roleOptions.length; i++) {
        if (roleId === this.roleOptions[i].value) {
          return this.roleOptions[i].label
        }
      }
      return ''
    },
    getName(id){
      this.orgName = ""
      getName(id)
        .then(response => {
          this.findName(response.data.data[0])
        })
        .catch(() => {
        })  
    },
    findName(obj){
      
      if(this.orgName ===""){
        this.orgName = obj.fullName;
      }else{
        this.orgName =  obj.fullName +  "/" + this.orgName ;
      }

      if(obj.hrmOrganization != null){
        this.findName(obj.hrmOrganization)
      }
    },
    getTree(){
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

      listPosition(this.listQuery)
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
        orgId: undefined,
        fullName: undefined,
        shortName: undefined,
        type: undefined,
        valid: 1        
      }
    },
    uploadAvatar: function(response) {
      this.dataForm.avatar = response.data.url
    },
    handleCreate() {
      if(this.orgId === undefined){
          this.$notify.error({
            title: '提示',
            message: '请选择公司'
          })

          return false
      }

      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.dataForm.orgId = this.orgId
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createPosition(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加岗位成功'
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
          updatePosition(this.dataForm)
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
                message: '更新岗位成功'
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
      deletePosition(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除岗位成功'
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
