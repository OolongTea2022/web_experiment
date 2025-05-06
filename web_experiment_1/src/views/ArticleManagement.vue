<template>
    <div class="author-management-container">
      <div class="author-list">
        <div class="header">
          <h2>作者列表</h2>
        </div>
        
        <el-table 
          :data="currentPageAuthors" 
          style="width: 100%"
          @row-click="handleAuthorClick"
          highlight-current-row
        >
          <el-table-column prop="index" label="序号" width="80" />
          <el-table-column prop="name" label="作者姓名" width="180" />
          <el-table-column prop="articleCount" label="文章数量" width="120" />
          <el-table-column label="操作">
            <template #default>
              <el-button type="text">进入文章管理</el-button>
            </template>
          </el-table-column>
        </el-table>
        
        <div class="pagination">
          <el-pagination
            v-model:current-page="currentPage"
            :page-size="pageSize"
            :total="authors.length"
            layout="total, prev, pager, next"
            @current-change="handlePageChange"
          />
        </div>
      </div>
      
      <div class="chart-container">
        <h3>作者发布文章统计图表</h3>
        <div ref="chartRef" class="chart" style="width: 100%; height: 400px;"></div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, computed, watch } from 'vue'
  import * as echarts from 'echarts'
  
  // 模拟数据
  const authors = ref([
    { id: 1, name: 'tom1', articleCount: 3 },
    { id: 2, name: 'tom', articleCount: 3 },
    { id: 3, name: 'Admin', articleCount: 1 },
    { id: 4, name: 'Jack', articleCount: 2 },
    { id: 5, name: 'Johnson', articleCount: 2 },
    { id: 6, name: 'Alice', articleCount: 4 },
    { id: 7, name: 'Bob', articleCount: 1 },
    { id: 8, name: 'Charlie', articleCount: 5 },
    { id: 9, name: 'David', articleCount: 2 },
    { id: 10, name: 'Eve', articleCount: 3 },
    { id: 11, name: 'Frank', articleCount: 1 }
  ])
  
  const pageSize = ref(5)
  const currentPage = ref(1)
  const chartRef = ref(null)
  let chartInstance = null
  
  // 计算当前页显示的作者
  const currentPageAuthors = computed(() => {
    const start = (currentPage.value - 1) * pageSize.value
    const end = start + pageSize.value
    return authors.value.slice(start, end).map((author, index) => ({
      ...author,
      index: start + index + 1
    }))
  })
  
  // 初始化图表
  const initChart = () => {
    if (chartInstance) {
      chartInstance.dispose()
    }
    
    chartInstance = echarts.init(chartRef.value)
    
    const option = {
      title: {
        text: '用户发表文章统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      xAxis: {
        type: 'category',
        data: currentPageAuthors.value.map(author => author.name),
        axisLabel: {
          rotate: 45
        }
      },
      yAxis: {
        type: 'value',
        axisLabel: {
          formatter: value => `${value}篇`
        },
        max: Math.max(...authors.value.map(a => a.articleCount)) + 1
      },
      series: [
        {
          name: '文章数量',
          type: 'bar',
          data: currentPageAuthors.value.map(author => ({
            value: author.articleCount,
            itemStyle: {
              color: '#409EFF'
            }
          })),
          label: {
            show: true,
            position: 'top',
            formatter: '{c}篇'
          }
        }
      ],
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      }
    }
    
    chartInstance.setOption(option)
  }
  
  // 处理分页变化
  const handlePageChange = () => {
    updateChart()
  }
  
  // 处理作者点击
  const handleAuthorClick = (row) => {
    // 这里可以添加跳转到作者文章管理的逻辑
    console.log('进入作者文章管理:', row)
  }
  
  // 更新图表数据
  const updateChart = () => {
    if (!chartInstance) return
    
    const option = {
      xAxis: {
        data: currentPageAuthors.value.map(author => author.name)
      },
      series: [
        {
          data: currentPageAuthors.value.map(author => ({
            value: author.articleCount,
            itemStyle: {
              color: '#409EFF'
            }
          }))
        }
      ]
    }
    
    chartInstance.setOption(option)
  }
  
  // 监听窗口大小变化，重新调整图表大小
  const handleResize = () => {
    if (chartInstance) {
      chartInstance.resize()
    }
  }
  
  onMounted(() => {
    initChart()
    window.addEventListener('resize', handleResize)
  })
  
  // 组件卸载时清理
//   onBeforeUnmount(() => {
//     if (chartInstance) {
//       chartInstance.dispose()
//       chartInstance = null
//     }
//     window.removeEventListener('resize', handleResize)
//   })
  
  // 监听当前页作者变化，更新图表
  watch(currentPageAuthors, () => {
    updateChart()
  }, { deep: true })
  </script>
  
  <style scoped>
  .author-management-container {
    display: flex;
    padding: 20px;
    gap: 20px;
  }
  
  .author-list {
    flex: 1;
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  
  .chart-container {
    flex: 1;
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  
  .header {
    margin-bottom: 20px;
  }
  
  .pagination {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
  }
  
  .chart {
    margin-top: 20px;
  }
  
  .el-table {
    cursor: pointer;
  }
  </style>