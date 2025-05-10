<template>
    <div class="profile-container">
      <!-- 左侧用户信息 -->
      <div class="profile-side">
        <div class="user-info">
          <h1 class="welcome-text">欢迎 {{ userInfo.name }}</h1>
          <div class="avatar-container">
            <img :src="userInfo.avatar" alt="用户头像" class="avatar" />
            <!-- <img :src="avatarUrl" alt="用户头像" class="avatar" /> -->
          </div>
        </div>
      </div>
  
      <!-- 右侧个人信息展示 -->
      <!-- <div class="user-profile">
        <h3>用户基本信息</h3>
        <el-descriptions border :column="1">
        <el-descriptions-item label="用户名">{{ userInfo.username }}</el-descriptions-item>
        <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
        <el-descriptions-item label="余额">{{ userInfo.balance }}</el-descriptions-item>
        <el-descriptions-item label="生日">
            <el-icon><calendar /></el-icon>
            {{ userInfo.birthday }}
        </el-descriptions-item>
        </el-descriptions>
      </div> -->
      <div class="info-side">
        <!-- <h1 class="welcome-text">用户信息</h1> -->
    <el-descriptions border title="用户信息" :column="1">
      <el-descriptions-item label="用户ID">{{ userInfo.id }}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{ userInfo.name }}</el-descriptions-item>
      <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
      <el-descriptions-item label="生日">{{ userInfo.birthday || '未设置' }}</el-descriptions-item>
      <el-descriptions-item label="余额">
        <el-tag type="success">{{ userInfo.money }}元</el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </div>



    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { Calendar } from '@element-plus/icons-vue'
  
  import { useUserStore } from '../stores/user'
  import { storeToRefs } from 'pinia'

  const userStore = useUserStore()
  const { userInfo } = storeToRefs(userStore) // 保持响应式


  // 用户数据
//   const userName = ref('OolongTea')
//   const avatarUrl = ref('../../oolongtea.png')
//   const props = defineProps({
//     userInfo: {
//         type: Object,
//         required: true,
//         default: () => ({
//         username: '',
//         email: '',
//         balance: 0,
//         birthday: ''
//         })
//     }
//   })



// "data": {
//     "avatar": "string",
//     "birthday": "2025-05-06T15:35:32.500Z",
//     "email": "string",
//     "id": 0,
//     "money": 0,
//     "name": "string"
//   },


  </script>
  
  <style scoped>
.info-side{
    flex: 1;
    max-width: 35%;
    padding: 40px;
    /* background-color: #fff; */

    display: flex;
    flex-direction: column;
    /* justify-content: center; */
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
    padding: 40px;
    /* background-color: #fff; */

    display: flex;
    flex-direction: column;
    /* justify-content: center; */
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
  

  
  </style>