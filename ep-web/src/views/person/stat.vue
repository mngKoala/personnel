<template>
  <div class="app-container">
    <el-row>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            人员统计
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.total }}
          </div>
          <div class="text item">
            当前人员统计总人数
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            在职人数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.online }}
          </div>
          <div class="text item">
            当前在职人总数
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            实习试用人数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.ontrial }}
          </div>
          <div class="text item">
            当前实习试用人数
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            非员工人数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.non_employee }}
          </div>
          <div class="text item">
            当前非员工人总数
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            离职人数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.offline }}
          </div>
          <div class="text item">
            当前离职人总数
          </div>
        </el-card>
      </el-col>
      <el-col :span="4">
        <el-card class="box-card">
          <div class="text item">
            未设置员工人数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.other }}
          </div>
          <div class="text item">
            未设置员工人数
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top:30px;">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>人员状态分布图</span>
          </div>
          <div class="chart-container">
            <div id="chartStatus" class="chart" style="height:300px;width:100%" />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>人员学历分布</span>
          </div>
          <div class="chart-container">
            <div id="chartEducation" class="chart" style="height:300px;width:100%" />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style>
/**隐藏底部原始滚动条**/
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}

.text {
  font-size: 14px;
  font-weight: 500;
}

.item {
  padding: 8px 0;
}
.box-card{
  margin-left: 5px;
  margin-right: 5px;
}
</style>

<script>
import echarts from 'echarts'
import { listStat, listStatus, listEducation } from '@/api/person'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'PersonStat',
  components: { Pagination },
  data() {
    return {
      chartStatus: null,
      chartEducation: null,
      title: '',
      list: null,
      listStatus: null,
      listEducation: null,
      listLoading: true,
      dataForm: {
        non_employee: 0,
        offline: 0,
        online: 0,
        ontrial: 0,
        total: 0,
        other: 0
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
    this.getListStatus()
    this.getListEducation()
  },
  mounted() {
    setTimeout(() => {
      this.showChartStatus()
      this.showChartEducation()
    }, 1000)
  },
  methods: {
    // 设置图标参数
    showChartStatus() {
      // 基于准备好的dom，初始化echarts实例
      this.chartStatus = echarts.init(document.getElementById('chartStatus'))
      // 指定图表的配置项和数据
      var option = {
        title: {
          // text: 'Referer of a Website',
          // subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            type: 'pie',
            data: this.listStatus,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      this.chartStatus.setOption(option, true)
    },
    // 设置图标参数
    showChartEducation() {
      // 基于准备好的dom，初始化echarts实例
      this.chartEducation = echarts.init(document.getElementById('chartEducation'))
      // console.log(this.chart)
      // 指定图表的配置项和数据
      var option = {
        title: {
          // text: 'Referer of a Website',
          // subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            type: 'pie',
            data: this.listEducation,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      this.chartEducation.setOption(option)
    },
    getList() {
      listStat()
        .then(response => {
          this.list = response.data.data.list

          for (let index = 0; index < this.list.length; index++) {
            const element = this.list[index]

            if (element.name === 'non_employee') {
              this.dataForm.non_employee = element.value
            }

            if (element.name === 'offline') {
              this.dataForm.offline = element.value
            }

            if (element.name === 'online') {
              this.dataForm.online = element.value
            }

            if (element.name === 'ontrial') {
              this.dataForm.ontrial = element.value
            }

            if (element.name === 'total') {
              this.dataForm.total = element.value
            }

            if (element.name === 'other') {
              this.dataForm.other = element.value
            }
          }
        })
        .catch(() => {
          this.list = []
        })
    },
    getListStatus() {
      listStatus()
        .then(response => {
          this.listStatus = response.data.data.list
        })
        .catch(() => {
          this.listStatus = []
        })
    },
    getListEducation() {
      listEducation()
        .then(response => {
          this.listEducation = response.data.data.list
        })
        .catch(() => {
          this.listEducation = []
        })
    }
  }
}
</script>
