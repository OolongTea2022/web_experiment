<template>
    <div class="reset-password-container">
      <div class="reset-title">
        <h2>修改密码</h2>
      </div>
  
      <div class="reset-content">
        <!-- 左侧：用户名和旧密码 -->
        <div class="left-section">
          <el-form 
            label-position="top"
            class="reset-form"
          >
            <el-form-item label="用户名">
              <el-input 
                v-model="form.username" 
                placeholder="请输入用户名"
                :prefix-icon="User"
              />
            </el-form-item>
            
            <el-form-item label="旧密码">
              <el-input 
                v-model="form.oldPassword" 
                type="password" 
                placeholder="请输入旧密码"
                :prefix-icon="Lock"
                show-password
              />
            </el-form-item>
          </el-form>
        </div>
  
        <!-- 右侧：新密码和确认新密码 -->
        <div class="right-section">
          <el-form 
            label-position="top"
            class="reset-form"
          >
            <el-form-item label="新密码">
              <el-input 
                v-model="form.newPassword" 
                type="password" 
                placeholder="请输入新密码"
                :prefix-icon="Lock"
                show-password
              />
            </el-form-item>
            
            <el-form-item label="确认新密码">
              <el-input 
                v-model="form.confirmPassword" 
                type="password" 
                placeholder="请再次输入新密码"
                :prefix-icon="Lock"
                show-password
              />
            </el-form-item>
          </el-form>
        </div>
      </div>
  
      <!-- 底部按钮 -->
      <div class="reset-actions">
        <el-button 
          type="primary" 
          class="reset-btn"
          @click="handleResetPassword"
        >
          修改密码
        </el-button>
        <el-button 
          class="back-btn"
          @click="handleBackToLogin"
        >
          返回登录
        </el-button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive } from 'vue'
  import { useRouter } from 'vue-router'
  import { 
    User, 
    Lock
  } from '@element-plus/icons-vue'
  
  const router = useRouter()
  
  const form = reactive({
    username: '',
    oldPassword: '',
    newPassword: '',
    confirmPassword: ''
  })
  
  const handleResetPassword = () => {
    console.log('修改密码表单提交:', form)
    // 实际修改密码逻辑
    if (form.newPassword !== form.confirmPassword) {
      alert('两次输入的新密码不一致')
      return
    }
    // 调用API修改密码...
  }
  
  const handleBackToLogin = () => {
    router.push('/login')
  }
  </script>
  
  <style scoped>
  .reset-password-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #f5f5f5;
    /* padding: 20px; */
  }
  
  .reset-content {
    display: flex;
    width: 80%;
    max-width: 900px;
    background-color: #fff;
    border-radius: 8px;
    overflow: hidden;
  }
  
  .reset-title {
    text-align: center;
    color: #333;
    background-color: #fff;
    width: 80%;
    max-width: 900px;
    /* padding: 20px 0; */
    margin-bottom: 0;
  }
  
  .reset-title h2 {
    margin-bottom: 0;
  }
  
  .left-section,
  .right-section {
    flex: 1;
    padding: 20px;
  }
  
  .right-section {
    background-color: #fff;
  }
  
  .reset-form {
    width: 100%;
  }
  
  .reset-actions {
    background-color: #fff;
    width: 80%;
    max-width: 900px;
    display: flex;
    /* justify-content: space-between; */
    /* margin-top: 20px; */

    /* padding: 20px 40px; */
    /* border-radius: 0 0 8px 8px; */
  }
  
  .reset-btn {
    border-radius: 0px !important;
    margin-left: 0 !important;
    width: 50%;
    height: 40px;
  }
  
  .back-btn {
    border-radius: 0px !important;
    margin-left: 0 !important;
    width: 50%;
    height: 40px;
  }
  
  /* 响应式布局 - 小于800px时变为上下布局 */
  @media (max-width: 800px) {
    .reset-content {
      flex-direction: column;
    }
    
    .left-section,
    .right-section {
      padding: 30px;
    }
    
    .right-section {
      border-top: 1px solid #eee;
    }
    
    .reset-actions {
      flex-direction: column;
      /* gap: 10px; */
    }
    
    .reset-btn,
    .back-btn {
      width: 100%;
    }
  }
  </style>