<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">
            合同总数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.total }}
          </div>
          <div class="text item">
            当前人员合同总数
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">
            履约合同数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.online }}
          </div>
          <div class="text item">
            履约合同总数
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">
            未生效合同数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.offline }}
          </div>
          <div class="text item">
            未生效合同数
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div class="text item">
            未转正合同数
            <span style="float:right;background-color:#1e9fff;border-radius:0.25em;padding:0.2em 0.6em 0.3em;">实时</span>
          </div>
          <div class="text item">
            {{ this.dataForm.transfer_to_regular }}
          </div>
          <div class="text item">
            未转正合同数
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>合同年份统计</span>
      </div>
      <div class="chart-container">
        <div id="chartYear" class="chart" style="height:300px;width:100%" />
      </div>
    </el-card>
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
import { listStat, listYear } from '@/api/contract'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'PersonStat',
  components: { Pagination },
  data() {
    return {
      chartYear: null,
      title: '',
      list: null,
      listYear: null,
      listLoading: true,
      xAxis: [],
      yAxis: [],
      dataForm: {
        transfer_to_regular: 0,
        offline: 0,
        online: 0,
        total: 0
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
    this.getListYear()
  },
  mounted() {
    setTimeout(() => {
      this.showChartYear()
    }, 1000)
  },
  methods: {
    // 设置图标参数
    showChartYear() {
      // 基于准备好的dom，初始化echarts实例
      this.chartYear = echarts.init(document.getElementById('chartYear'))
      // 指定图表的配置项和数据
      var option = {
        legend: {
          data: ['数量'],
          itemGap: 5
        },
        xAxis: {
          type: 'category',
          data: this.xAxis
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '数量',
            data: this.yAxis,
            type: 'bar'
          }
        ]
      }
      this.chartYear.setOption(option, true)
    },
    getList() {
      listStat()
        .then(response => {
          this.list = response.data.data.list

          for (let index = 0; index < this.list.length; index++) {
            const element = this.list[index]

            if (element.name === 'transfer_to_regular') {
              this.dataForm.transfer_to_regular = element.value
            }

            if (element.name === 'offline') {
              this.dataForm.offline = element.value
            }

            if (element.name === 'online') {
              this.dataForm.online = element.value
            }

            if (element.name === 'total') {
              this.dataForm.total = element.value
            }
          }
        })
        .catch(() => {
          this.list = []
        })
    },
    getListYear() {
      listYear()
        .then(response => {
          this.listYear = response.data.data.list

          for (let index = 0; index < this.listYear.length; index++) {
            const element = this.listYear[index]

            this.xAxis.push(element.name)
            this.yAxis.push(element.value)
          }
        })
        .catch(() => {
          this.listYear = []
        })
    }
  }
}
</script>
