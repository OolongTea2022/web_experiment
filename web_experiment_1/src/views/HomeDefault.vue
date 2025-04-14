<template>
    <div class="profile-container">
      <!-- 左侧用户信息 -->
      <div class="profile-side">
        <div class="user-info">
          <h1 class="welcome-text">欢迎 {{ userName }}</h1>
          <div class="avatar-container">
            <img :src="avatarUrl" alt="用户头像" class="avatar" />
          </div>
        </div>
      </div>
  
      <!-- 右侧日历 -->
      <div class="calendar-side">
        <el-calendar v-model="currentDate" class="custom-calendar" />
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { ElCalendar } from 'element-plus'
  
  // 用户数据
  const userName = ref('OolongTea')
  const avatarUrl = ref('../../oolongtea.png')
  const currentDate = ref(new Date())
  </script>
  
  <style scoped>
  .profile-container {
    display: flex;
    height: 100%;
    background-color: #ebb812;
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
  
  .calendar-side {
    flex: 2;
    /* padding: 40px; */
    margin: 10px;
    display: flex;
    /* justify-content: center; */
    /* align-items: center; */
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
  
  .custom-calendar {
    width: 100%;
    max-width: 800px;
    /* border-radius: 8px; */

  }
  
  </style>