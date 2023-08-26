<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入人员名称" />
      <el-button v-permission="['GET /person/practice/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="工号" prop="jobNumber" />
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
      <el-table-column align="center" label="入职日期" prop="employmentDate" />
      <el-table-column align="center" label="员工部门" prop="orgId" />
      <el-table-column align="center" label="员工岗位" prop="positionCode" />
      <el-table-column align="center" label="备注" prop="note" />
      <el-table-column align="center" label="创建时间" prop="createTime" />
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width" width="220">
        <template slot-scope="scope">
          <el-button v-permission="['POST /person/practice/update']" type="primary" size="mini" @click="handleDetail(scope.row)">查看</el-button>
          <el-button v-permission="['POST /person/practice/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-scrollbar :style="{ 'height': 'calc(80vh - 150px)' }">
        <el-divider content-position="left"><span style="font-size:16px;">基本信息</span></el-divider>
        <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="right" label-width="120px" style="margin-right:20px;">
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名" prop="name">
                <el-input v-model="dataForm.name" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="人员类型" prop="employeeTypeCode">
                <el-select v-model="dataForm.employeeTypeCode" placeholder="请选择人员类型">
                  <el-option label="合同工" value="contract_worker" />
                  <el-option label="派遣工" value="dispatcher" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="身份证" prop="identityCard">
                <el-input v-model="dataForm.identityCard" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="出生日期" prop="birthday">
                <el-date-picker
                  v-model="dataForm.birthday"
                  type="date"
                  placeholder="选择出生日期"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工状态" prop="employeeStatus">
                <el-radio-group v-model="dataForm.employeeStatus">
                  <el-radio label="online">在职</el-radio>
                  <el-radio label="offline">离职</el-radio>
                  <el-radio label="non_employee">非员工</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="民族" prop="nationCode">
                <el-input v-model="dataForm.nationCode" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工标记" prop="employeeIdentityStatus">
                <el-select v-model="dataForm.employeeIdentityStatus" placeholder="请选择员工标记">
                  <el-option label="正式" value="formal" />
                  <el-option label="试用" value="ontrial" />
                  <el-option label="实习" value="practice" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="籍贯" prop="nativePlaceCode">
                <el-input v-model="dataForm.nativePlaceCode" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="工号" prop="jobNumber">
                <el-input v-model="dataForm.jobNumber" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工" prop="employeeId">
                <!--<i class="el-icon-search search-icon">请选择人员</i>--->
                <el-button class="el-icon-search search-icon" style="width:100%;" @click="selectEmployee">
                  <span v-if="dataForm.employeeName ===undefined">    请选择人员</span>
                  <span v-else>    {{ this.dataForm.employeeName }}</span>
                </el-button>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="性别" prop="sexCode">
                <el-select v-model="dataForm.sexCode" placeholder="请选择性别">
                  <el-option label="男" value="O" />
                  <el-option label="女" value="F" />
                  <el-option label="其它" value="T" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="婚姻" prop="maritalStatus">
                <el-select v-model="dataForm.maritalStatus" placeholder="请选择婚姻状况">
                  <el-option label="已婚" value="married" />
                  <el-option label="离婚" value="unmarried" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">个人情况</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="联系方式" prop="contactInformation">
                <el-input v-model="dataForm.contactInformation" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="电子邮件" prop="email">
                <el-input v-model="dataForm.email" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="紧急联系人" prop="emergencyContact">
                <el-input v-model="dataForm.emergencyContact" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="紧急人电话" prop="emergencyContactNo">
                <el-input v-model="dataForm.emergencyContactNo" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="家庭地址" prop="homeAddress">
                <el-input v-model="dataForm.homeAddress" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="微信号" prop="weixinId">
                <el-input v-model="dataForm.weixinId" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="学历" prop="educationCode">
                <el-select v-model="dataForm.educationCode" placeholder="请选择学历">
                  <el-option label="高中" value="high" />
                  <el-option label="大专" value="junior_college" />
                  <el-option label="初中" value="middle" />
                  <el-option label="研究生" value="postgraduate" />
                  <el-option label="小学" value="primary" />
                  <el-option label="本科" value="undergraduate" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="毕业院校" prop="graduationSchool">
                <el-input v-model="dataForm.graduationSchool" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="所学外语" prop="foreignLanguage">
                <el-input v-model="dataForm.foreignLanguage" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="外语证书" prop="foreignLanguageLevel">
                <el-input v-model="dataForm.foreignLanguageLevel" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="计算机能力" prop="computerAbility">
                <el-input v-model="dataForm.computerAbility" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="计算机等级" prop="computerLevel">
                <el-input v-model="dataForm.computerLevel" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="政治面貌" prop="politicCountenanceCode">
                <el-select v-model="dataForm.politicCountenanceCode" placeholder="请选择政治面貌">
                  <el-option label="党员" value="dy" />
                  <el-option label="群众" value="qz" />
                  <el-option label="团员" value="ty" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="入党时间" prop="joinPartDate">
                <el-date-picker
                  v-model="dataForm.joinPartDate"
                  type="date"
                  placeholder="选择入职日期"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="血型" prop="bloodType">
                <el-select v-model="dataForm.bloodType" placeholder="请选择血型">
                  <el-option label="A" value="A" />
                  <el-option label="B" value="B" />
                  <el-option label="AB" value="AB" />
                  <el-option label="O" value="O" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="身高" prop="bodyHeight">
                <el-input v-model="dataForm.bodyHeight" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="体重" prop="bodyWeight">
                <el-input v-model="dataForm.bodyWeight" />
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">工作信息</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="入职日期" prop="employmentDate">
                <el-date-picker
                  v-model="dataForm.employmentDate"
                  type="date"
                  placeholder="选择入职日期"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="初次日期" prop="firstEmploymentDate">
                <el-date-picker
                  v-model="dataForm.firstEmploymentDate"
                  type="date"
                  placeholder="选择初次日期"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="转正日期" prop="employmentConfirmDate">
                <el-date-picker
                  v-model="dataForm.employmentConfirmDate"
                  type="date"
                  placeholder="选择转正日期"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="参加工作时间" prop="firstWorkDate">
                <el-date-picker
                  v-model="dataForm.firstWorkDate"
                  type="date"
                  placeholder="选择参加工作日期"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工岗位" prop="positionCode">
                <el-input v-model="dataForm.positionCode" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工职称" prop="employeeTitleCode">
                <el-input v-model="dataForm.employeeTitleCode" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="离职日期" prop="leaveDate">
                <el-date-picker
                  v-model="dataForm.leaveDate"
                  type="date"
                  placeholder="选择离职日期"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工职级" prop="rankCode">
                <el-input v-model="dataForm.rankCode" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="离职原因" prop="leaveReson">
                <el-input v-model="dataForm.leaveReson" />
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">工资信息</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="是否发放" prop="salaryPayOut">
                <el-radio-group v-model="dataForm.salaryPayOut">
                  <el-radio label="1">是</el-radio>
                  <el-radio label="0">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="工资卡开户行" prop="payrollCardBankCode">
                <el-input v-model="dataForm.payrollCardBankCode" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="薪酬模版" prop="salaryTplId">
                <el-input v-model="dataForm.salaryTplId" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="工资卡帐号" prop="payrollCard">
                <el-input v-model="dataForm.payrollCard" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="薪酬备注" prop="salaryNotes">
                <el-input v-model="dataForm.salaryNotes" />
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
        </el-form>
      </el-scrollbar>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

    <!-- 详情对话框 -->
    <el-dialog title="查看人员信息" :visible.sync="detailDialogVisible">
      <el-scrollbar :style="{ 'height': 'calc(80vh - 150px)' }">
        <el-divider content-position="left"><span style="font-size:16px;">基本信息</span></el-divider>
        <el-form :data="personDetail" status-icon label-position="right" label-width="120px" style="margin-right:20px;">
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名">
                <span>{{ personDetail.name }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="人员类型">
                <el-select v-model="personDetail.employeeTypeCode" placeholder="请选择人员类型" disabled>
                  <el-option label="合同工" value="contract_worker" />
                  <el-option label="派遣工" value="dispatcher" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="身份证">
                <span>{{ personDetail.identityCard }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="出生日期">
                <el-date-picker
                  v-model="personDetail.birthday"
                  type="date"
                  placeholder="选择出生日期"
                  disabled
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工状态">
                <el-radio-group v-model="personDetail.employeeStatus" disabled>
                  <el-radio label="online">在职</el-radio>
                  <el-radio label="offline">离职</el-radio>
                  <el-radio label="non_employee">非员工</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="民族">
                <span>{{ personDetail.nationCode }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工标记">
                <el-select v-model="personDetail.employeeIdentityStatus" placeholder="请选择员工标记" disabled>
                  <el-option label="正式" value="formal" />
                  <el-option label="试用" value="ontrial" />
                  <el-option label="实习" value="practice" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="籍贯">
                <span>{{ personDetail.nativePlaceCode }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="工号">
                <span>{{ personDetail.jobNumber }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工">
                <!--<i class="el-icon-search search-icon">请选择人员</i>--->
                <span v-if="personDetail.hrmEmployee != null">{{ personDetail.hrmEmployee.hrmPerson.name }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="性别">
                <el-select v-model="personDetail.sexCode" placeholder="请选择性别" disabled>
                  <el-option label="男" value="O" />
                  <el-option label="女" value="F" />
                  <el-option label="其它" value="T" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="婚姻">
                <el-select v-model="personDetail.maritalStatus" placeholder="请选择婚姻状况" disabled>
                  <el-option label="已婚" value="married" />
                  <el-option label="离婚" value="unmarried" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">个人情况</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="联系方式">
                <span>{{ personDetail.contactInformation }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="电子邮件">
                <span>{{ personDetail.email }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="紧急联系人">
                <span>{{ personDetail.emergencyContact }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="紧急人电话">
                <span>{{ personDetail.emergencyContactNo }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="家庭地址">
                <span>{{ personDetail.homeAddress }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="微信号">
                <span>{{ personDetail.weixinId }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="学历">
                <el-select v-model="personDetail.educationCode" placeholder="请选择学历" disabled>
                  <el-option label="高中" value="high" />
                  <el-option label="大专" value="junior_college" />
                  <el-option label="初中" value="middle" />
                  <el-option label="研究生" value="postgraduate" />
                  <el-option label="小学" value="primary" />
                  <el-option label="本科" value="undergraduate" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="毕业院校">
                <span>{{ personDetail.graduationSchool }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="所学外语">
                <span>{{ personDetail.foreignLanguage }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="外语证书">
                <span>{{ personDetail.foreignLanguageLevel }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="计算机能力">
                <span>{{ personDetail.computerAbility }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="计算机等级">
                <span>{{ personDetail.computerLevel }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="政治面貌">
                <el-select v-model="personDetail.politicCountenanceCode" placeholder="请选择政治面貌" disabled>
                  <el-option label="党员" value="dy" />
                  <el-option label="群众" value="qz" />
                  <el-option label="团员" value="ty" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="入党时间">
                <el-date-picker
                  v-model="personDetail.joinPartDate"
                  type="date"
                  placeholder="选择入职日期"
                  disabled
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="血型">
                <el-select v-model="personDetail.bloodType" placeholder="请选择血型" disabled>
                  <el-option label="A" value="A" />
                  <el-option label="B" value="B" />
                  <el-option label="AB" value="AB" />
                  <el-option label="O" value="O" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="身高">
                <span>{{ personDetail.bodyHeight }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="体重">
                <span>{{ personDetail.bodyWeight }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">工作信息</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="入职日期">
                <el-date-picker
                  v-model="personDetail.employmentDate"
                  type="date"
                  placeholder="选择入职日期"
                  disabled
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="初次日期">
                <el-date-picker
                  v-model="personDetail.firstEmploymentDate"
                  type="date"
                  placeholder="选择初次日期"
                  disabled
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="转正日期">
                <el-date-picker
                  v-model="personDetail.employmentConfirmDate"
                  type="date"
                  placeholder="选择转正日期"
                  disabled
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="参加工作时间">
                <el-date-picker
                  v-model="personDetail.firstWorkDate"
                  type="date"
                  placeholder="选择参加工作日期"
                  disabled
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="员工岗位">
                <span>{{ personDetail.positionCode }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工职称">
                <span>{{ personDetail.employeeTitleCode }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="离职日期">
                <el-date-picker
                  v-model="personDetail.leaveDate"
                  type="date"
                  placeholder="选择离职日期"
                  disabled
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="员工职级">
                <span>{{ personDetail.rankCode }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="离职原因">
                <span>{{ personDetail.leaveReson }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
          <el-divider content-position="left"><span style="font-size:16px;">工资信息</span></el-divider>
          <el-row>
            <el-col :span="12">
              <el-form-item label="是否发放">
                <el-radio-group v-model="personDetail.salaryPayOut">
                  <el-radio label="1" disabled>是</el-radio>
                  <el-radio label="0" disabled>否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="工资卡开户行">
                <span>{{ personDetail.payrollCardBankCode }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="薪酬模版">
                <span>{{ personDetail.salaryTplId }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="工资卡帐号">
                <span>{{ personDetail.payrollCard }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="薪酬备注">
                <span>{{ personDetail.salaryNotes }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
        </el-form>
      </el-scrollbar>

      <div slot="footer" class="dialog-footer">
        <el-button @click="detailDialogVisible = false">取消</el-button>
      </div>
    </el-dialog>

    <!-- 员工对话框 -->
    <el-dialog title="请选择人员" :visible.sync="dialogVisible" :append-to-body="true" width="40%">
      <el-scrollbar :style="{ 'height': 'calc(80vh - 150px)' }">
        <!-- 查询和其他操作 -->
        <div class="filter-container">
          <el-input v-model="listQueryEmployee.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入管理员名称" />
          <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
        </div>

        <!-- 查询结果 -->
        <el-table v-loading="listLoading" :data="listEmployee" element-loading-text="正在查询中。。。" border fit highlight-current-row style="width:95%;">
          <el-table-column width="55">
            <template slot-scope="scope">
              <el-radio v-model="checkRadio" :label="scope.$index">{{ '' }}</el-radio>
            </template>
          </el-table-column>
          <el-table-column align="center" label="工号" prop="badge" sortable />
          <el-table-column align="center" label="姓名" prop="hrmPerson">
            <template slot-scope="scope">
              <span v-if="scope.row.hrmPerson != null">{{ scope.row.hrmPerson.name }}</span>
            </template>
          </el-table-column>
        </el-table>
        <pagination v-show="totalEmployee>0" :total="totalEmployee" :page.sync="listQueryEmployee.page" :limit.sync="listQueryEmployee.limit" @pagination="getListEmployee" />
      </el-scrollbar>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="selectEmployeeId">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
/**隐藏底部原始滚动条**/
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}
</style>

<script>
import { readPerson, listEmployee, updatePerson } from '@/api/register'
import { listPersonPractice } from '@/api/person'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'PersonPractice',
  components: { Pagination },
  data() {
    return {
      personDetail: {},
      detailDialogVisible: false,
      dialogVisible: false,
      checkRadio: null,
      listQueryEmployee: {
        page: 1,
        limit: 20,
        id: undefined,
        username: undefined,
        type: 'com',
        sort: 'add_time',
        order: 'desc'
      },
      personType: [{
        value: 'contract_worker',
        label: '合同工'
      }, {
        value: 'dispatcher',
        label: '派遣工'
      }
      ],
      list: null,
      total: 0,
      listEmployee: null,
      totalEmployee: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑人员信息',
        create: '添加人员信息'
      },
      dataForm: {}
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
    this.getListEmployee()
  },
  methods: {
    getListEmployee() {
      this.checkRadio = undefined
      listEmployee(this.listQueryEmployee)
        .then(response => {
          this.listEmployee = response.data.data.list
          this.totalEmployee = response.data.data.total
        })
        .catch(() => {
          this.listEmployee = []
          this.totalEmployee = 0
        })
    },
    selectEmployeeId() {
      if (this.checkRadio != undefined) {
        this.dataForm.employeeId = this.listEmployee[this.checkRadio].id

        if (this.listEmployee[this.checkRadio].hrmPerson != null) { this.dataForm.employeeName = this.listEmployee[this.checkRadio].hrmPerson.name } else { this.dataForm.employeeName = '名字异常' }

        this.dialogVisible = false
      } else {
        this.$notify.warning({
          title: '请选择人员',
          message: ''
        })
        return
      }
    },
    selectEmployee() {
      this.dialogVisible = true
    },
    getList() {
      this.listLoading = true
      listPersonPractice(this.listQuery)
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
    handleDetail(row) {
      readPerson(row.id)
        .then(response => {
          this.personDetail = response.data.data
        })
        .catch(response => {
          this.personDetail = []
        })
      this.detailDialogVisible = true
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
          updatePerson(this.dataForm)
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
                message: '更新人员成功'
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
    }
  }
}
</script>
