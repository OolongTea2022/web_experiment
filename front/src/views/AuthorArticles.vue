<template>
  
  <div class="main_container">
    <add-article-dialog ref="addDialog" @success="handleAddSuccess" />
    <edit-article-dialog ref="editDialog" @success="handleEditSuccess" />

    <div class="upper_container">
      <div class="profile-container">
        <!-- 左侧用户信息 -->
        <div class="profile-side">
          <div class="user-info">
            <!-- <h1 class="welcome-text">欢迎 {{ userInfo.name }}</h1> -->
            <div class="avatar-container">
              <img :src="userInfo.avatar" alt="用户头像" class="avatar" />
            </div>
          </div>
        </div>
    
        
        <div class="info-side">
          <h1>作者基本信息</h1>
          <!-- <el-descriptions border title="作者基本信息" :column="1"> -->
            <el-descriptions border :column="1">
            <el-descriptions-item label="用户ID">{{ userInfo.id }}</el-descriptions-item>
            <el-descriptions-item label="用户名">{{ userInfo.name }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
            <el-descriptions-item label="生日">{{ userInfo.birthday || '未设置' }}</el-descriptions-item>
            <el-descriptions-item label="余额">
              <el-tag type="success">{{ userInfo.money }}元</el-tag>
            </el-descriptions-item>
          </el-descriptions>

          <el-button type="primary" @click="return_button_click">返回</el-button>
        </div>



      </div>
    </div>



    <!-- 文章部分 -->
    <div class="lower_container">

      <div class="table-header">
        <el-button type="primary" @click="handleAdd">
          <el-icon><Plus /></el-icon>新增
        </el-button>
        <el-input
          v-model="searchKeyword"
          placeholder="请输入文章关键字"
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
        <el-table-column prop="title" label="文章标题" width="200" align="center" />
        <el-table-column prop="content" label="文章内容" align="center" />



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
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :total="total"
          :page-sizes="[5, 10, 20, 50]"
          layout="total, sizes, prev, pager, next, jumper"
        />
      </div>

    </div>
  </div>



  </template>
  
  <script setup>
  import { ref, computed, onMounted, reactive } from 'vue'
  import { useRoute,useRouter } from 'vue-router'
  import { Plus, Refresh, Search } from '@element-plus/icons-vue'
  import { ElMessage, ElMessageBox } from 'element-plus'

  import { getUserVOById } from "../api/user"
  import { getArticlesById, deleteArticle, saveArticle, updateArticle } from "../api/article"



  const router = useRouter()
  const route = useRoute()
  const authorId = ref(null)
  const authorName = ref('')

  // 添加对话框组件引入
  import AddArticleDialog from '../components/AddArticleDialog.vue'
  import EditArticleDialog from '../components/EditArticleDialog.vue'

  // 对话框引用
  const addDialog = ref()
  const editDialog = ref()

  // 响应式数据
  const articleList = ref([])
  const searchKeyword = ref('')
  const currentPage = ref(1)
  const pageSize = ref(5)
  const total = ref(0)

  const userInfo = ref({
    avatar: "https://wtwlc-first-bucket.oss-cn-beijing.aliyuncs.com/avatar/4857773d-3ebd-4f56-983e-8b3424337a6d-steam_favicon.gif",
    birthday: "2025-05-06T15:35:32.500Z",
    email: "xxx@126.com",
    id: 0,
    money: 5.5,
    name: "test"
  })

  // const mockArticleData = [
  //   {
  //     id: 1,
  //     title: 'Vue3入门指南',
  //     content: '本文介绍了Vue3的基本概念和使用方法，包括组合式API、响应式原理等核心内容。',
  //   },
  //   {
  //     id: 2,
  //     title: 'Element Plus实战',
  //     content: '详细讲解如何使用Element Plus构建企业级前端界面，包含表单、表格等常见组件示例。',
  //   },
  //   {
  //     id: 3,
  //     title: '前端工程化实践',
  //     content: '分享现代前端工程化的最佳实践，包括Webpack配置、代码规范、自动化测试等内容。',
  //   },
  //   {
  //     id: 4,
  //     title: 'TypeScript进阶',
  //     content: '深入TypeScript高级特性，如泛型、装饰器、类型推断等，帮助提升代码质量。',
  //   },
  //   {
  //     id: 5,
  //     title: '响应式设计原理',
  //     content: '解析响应式编程在前端开发中的应用，比较不同框架的实现方式。',
  //   },
  //   {
  //     id: 6,
  //     title: '前端性能优化',
  //     content: '从加载、渲染、缓存等多维度介绍前端性能优化的实用技巧和工具。',
  //   },
  //   {
  //     id: 7,
  //     title: '微前端架构',
  //     content: '探讨微前端架构的设计思路和实现方案，解决大型前端应用的模块化问题。',
  //   },
  // ]
  
  onMounted(() => {
    authorId.value = route.params.authorId
    // 根据 authorId 获取作者信息和文章列表
    loadAuthorData()

  //   setTimeout(() => {
  //   articleList.value = mockArticleData
  //   total.value = mockArticleData.length
  // }, 500)

  })
  
  const loadAuthorData = async () => {
    // 这里调用API获取作者信息和文章
    const res1 = await getUserVOById(authorId.value);
    console.log(res1)

    if(res1.code == 0){
      userInfo.value = res1.data
    }else{
      console.log(res1.message);
    }
    
    const res2 = await getArticlesById(authorId.value)
    console.log(res2)

    if(res2.code == 0){
      articleList.value = res2.data
      total.value = articleList.length
    }
    //TODO 根据用户ID获取文章列表


    // 示例数据
    // if (authorId.value === '1') {
    //   authorName.value = 'tom1'
    // } else if (authorId.value === '2') {
    //   authorName.value = 'tom'
    // }
    // ...
  }

  const return_button_click = () =>{
    router.back();

    //TODO 需要更好的路由管理
    // router.push("/ArticleManagement")
  }

  // 计算属性 - 过滤后的文章列表
  const filteredUserList = computed(() => {
    let result = articleList.value
    if (searchKeyword.value) {
      result = result.filter(article => 
        article.title.includes(searchKeyword.value) || 
        article.content.includes(searchKeyword.value)
      )
    }

    total.value = result.length
    return result.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value)
  })

  // 新增文章
  const handleAdd = () => {
    addDialog.value.open(userInfo)
  }

  // 编辑文章
  const handleEdit = (row) => {
    editDialog.value.open(row)
  }

  // 删除文章
  const handleDelete = (row) => {
    console.log('尝试删除文章', row)
    
    ElMessageBox.confirm(
      '确认删除这篇文章吗?',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    ).then(async () => {

      const params = {
        id: row.id
      }

      const res = await deleteArticle(params);
      console.log("删除结果",res);

      if(res.code == 0){
        articleList.value = articleList.value.filter(item => item.id !== row.id)
        ElMessage.success('删除成功')
        total.value -= 1
        // 如果当前页没有数据且不是第一页，则返回上一页
        if (filteredUserList.value.length === 0 && currentPage.value > 1) {
          currentPage.value -= 1
        }
      }else{
        ElMessage.error(res.message || "删除失败")
      }

    }).catch(() => {
      ElMessage.info('取消删除')
    })
  }

  // 新增成功回调
  const handleAddSuccess = (newArticle) => {
    // 生成新ID
    const maxId = Math.max(...articleList.value.map(a => a.id), 0)
    newArticle.id = maxId + 1
    
    articleList.value.unshift(newArticle)
    ElMessage.success('文章添加成功')
    //刷新
    loadAuthorData()
  }

  // 编辑成功回调
  const handleEditSuccess = (editedData) => {
    const index = articleList.value.findIndex(item => item.id === editedData.id)
    if (index !== -1) {
      articleList.value[index] = editedData
    }
    ElMessage.success('文章修改成功')
    //刷新
    loadAuthorData()
  }

  // 搜索
  const handleSearch = () => {
    currentPage.value = 1
  }

  </script>
  
  <style scoped>
  .info-side{
    flex: 1;
    max-width: 60%;
    padding: 30px;
    background-color: #fff;

    display: flex;
    flex-direction: column;
    /* justify-content: center; */

    margin: 10px;
  }


  .info-side h1{
    text-align: center;
  }

  .user-profile {
    max-width: 600px;
    margin: 20px;
    flex: 2;
    width: 500px;
  }
  h3 {
    margin-bottom: 16px;
    color: #333;
  }
  .el-descriptions {
    margin-top: 20px;
  }

  .profile-container {
    display: flex;
    height: 100%;
    /* background-color: #ebb812; */
  }
  
  .profile-side {
    flex: 1;
    max-width: 35%;
    padding: 30px;
    background-color: #fff;

    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    margin: 10px;
  }
  

  
  .user-info {
    text-align: center;
  }
  
  .welcome-text {
    font-size: 32px;
    font-weight: bold;
    color: #333;

    /* margin-bottom: 40px; */
  }
  
  .avatar-container {
    background-color: #fff;
    max-width: 200px;
    max-height: 200px;
    /* width: 200px;
    height: 200px; */
    /* TODO头像缩放底下有白边 */

    margin: 20px auto;

    /* border-radius: 50%; */
    overflow: hidden;
    border: 5px solid #e6e6e6;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
  }
  
  .avatar {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }


/* lower */
.lower_container{
  padding-top: 20px;
}

  /*  */

  .table-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.search-input {
  width: 300px;
}
.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
  </style>