<template>
  <div class="user-management-container">
    <h1>联系人管理</h1>
    <add-contact-dialog ref="addDialog" @success="handleAddSuccess" />
    <edit-contact-dialog ref="editDialog" @success="handleEditSuccess" />

    <div class="table-header">
      <el-button type="primary" @click="handleAdd">
        <el-icon><Plus /></el-icon>新增
      </el-button>
      <el-input
        v-model="searchKeyword"
        placeholder="请输入昵称"
        class="search-input"
        clearable
      >
        <template #append>
          <el-button :icon="Search" @click="handleSearch" type="primary" >搜索</el-button><!--TODO为什么按钮不变蓝-->
        </template>
      </el-input>
    </div>
    
    <el-table :data="filteredUserList" border style="width: 100%">
      <el-table-column prop="id" label="序号" width="50" align="center" />
      <el-table-column prop="date" label="日期" width="180" align="center" />
      <el-table-column prop="name" label="姓名" width="120" align="center" />
      <el-table-column prop="province" label="省份" width="80" align="center" />
      <el-table-column prop="city" label="市区" width="120" align="center" />
      <el-table-column prop="address" label="地址"   align="center" />
      <el-table-column prop="zip" label="邮编" width="100"  align="center" />
      <el-table-column label="操作" width="150" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <div class="pagination-container">
      <el-pagination
        current-page="currentPage"
        page-size="pageSize"
        :total="total"
        :page-sizes="[5, 10, 20, 50]"
        layout="total, sizes, prev, pager, next, jumper"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { Plus, Search } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'

import { getAllUserContact, deleteUserContact, updateUserContact}from "../api/userContact"


import AddContactDialog from '../components/AddContactDialog.vue'
import EditContactDialog from '../components/EditContactDialog.vue'

// 对话框引用
const addDialog = ref()
// 编辑对话框引用
const editDialog = ref()

// 模拟数据
const mockData = [
  {
    id: 1,
    date: '2016-05-03 08:00:00',
    name: '王小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '邮编',
  },
  {
    id: 2,
    date: '2016-05-03 08:00:00',
    name: '张小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 3,
    date: '2016-05-03 08:00:00',
    name: '刘小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 4,
    date: '2016-05-03 08:00:00',
    name: '李小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },
  {
    id: 5,
    date: '2016-05-03 08:00:00',
    name: '赵小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: '200333',
  },


  // 可以添加更多模拟数据
]

// 响应式数据
const userList = ref([])
const searchKeyword = ref('')
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(0)

// 计算属性 - 过滤后的用户列表
const filteredUserList = computed(() => {
  let result = userList.value
  if (searchKeyword.value) {
    result = result.filter(user => 
      user.name.includes(searchKeyword.value)
    )
  }
  return result.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value)
})


const refresh = async ()=>{
  const res = await getAllUserContact()
  console.log(res)
  if(res.code == 0){
    userList.value = res.data.records
    total.value = res.data.total
  }else{
    console.log("getAllUserContact",出错了)
  }
}
// 初始化数据
onMounted(async () => {
  // 模拟API请求
  refresh()
  
  // setTimeout(() => {
  //   userList.value = mockData
  //   total.value = mockData.length
  // }, 500)
})

// 修改handleAdd方法
const handleAdd = () => {
  addDialog.value.open()
}

// 新增成功回调
const handleAddSuccess = async () => {
  // 这里可以刷新表格数据
  refresh()

  console.log('新增成功，刷新数据')
}

// 日期格式转换
const formatDateToISO = (dateString) => {
  // 将 "yyyy-MM-dd HH:mm:ss" 转换为 "yyyy-MM-dd'T'HH:mm:ss"
  return dateString.replace(' ', 'T');
};

// 编辑成功回调
const handleEditSuccess = (editedData) => {
  // 更新本地数据
  const index = userList.value.findIndex(item => item.id === editedData.id)
  if (index !== -1) {
    userList.value[index] = {
      ...userList.value[index],
      date: formatDateToISO(editedData.date), // 转换日期格式
      name: editedData.name,
      province: editedData.province,
      city: editedData.city,
      address: editedData.address,
      zip: editedData.zip
    }
  }
  ElMessage.success('联系人修改成功')
}


const handleEdit = (row) => {
  console.log('编辑用户', row)
  editDialog.value.open(row)
}

 
const handleDelete = async (row) => {
  console.log('删除用户', row)
    // 二次确认弹窗
    await ElMessageBox.confirm(
      '此操作将永久删除该联系人，是否继续？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    ).then(async ()=>{
      // 用户点击确定后执行删除
      const params = {
        id: row.id
      }
      

      const res = await deleteUserContact(params);
      console.log(res)

      if (res.code === 0) {
        // 从本地数据中移除
        userList.value = userList.value.filter(item => item.id !== row.id)
        total.value -= 1
        ElMessage.success('删除成功')
        
        // 如果当前页没有数据且不是第一页，则返回上一页
        if (filteredUserList.value.length === 0 && currentPage.value > 1) {
          currentPage.value -= 1
        }
      } else {
        ElMessage.error(res.message || '删除失败')
      }
    }).catch(()=>{
      ElMessage.error('删除失败')
    })
}


const handleSearch = () => {
  // 搜索逻辑已在计算属性中处理
  currentPage.value = 1 // 搜索后重置到第一页
}
</script>

<style scoped>
.user-management-container {
  padding: 20px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}


.table-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-input {
  width: 300px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.el-table {
  margin-top: 20px;
}

.el-button + .el-button {
  margin-left: 8px;
}
</style>