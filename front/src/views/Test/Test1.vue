<template>
    <div class="login-container">
      <el-row :gutter="40" class="login-row">
        <!-- 社交登录部分 -->
        <el-col :xs="12" :sm="12" :md="12" class="social-col">
          <div class="social-login-section">
            <h2 class="login-title">登录</h2>
            <div class="social-login-buttons">
              <el-button 
                class="social-btn qq" 
                @click="handleSocialLogin('qq')"
                :icon="QQ"
              >
                QQ登录
              </el-button>
              <el-button 
                class="social-btn wechat" 
                @click="handleSocialLogin('wechat')"
                :icon="Wechat"
              >
                微信登录
              </el-button>
              <el-button 
                class="social-btn alipay" 
                @click="handleSocialLogin('alipay')"
                :icon="Alipay"
              >
                支付宝登录
              </el-button>
            </div>
          </div>
        </el-col>
  
        <!-- 表单登录部分 -->
        <el-col :xs="12" :sm="12" :md="12" class="form-col">
          <div class="form-login-section">
            <el-form 
              :model="form" 
              @submit.prevent="handleSubmit"
              label-position="top"
            >
              <el-form-item label="用户名">
                <el-input 
                  v-model="form.username" 
                  placeholder="请输入用户名"
                  :prefix-icon="User"
                />
              </el-form-item>
              
              <el-form-item label="密码">
                <el-input 
                  v-model="form.password" 
                  type="password" 
                  placeholder="请输入密码"
                  :prefix-icon="Lock"
                  show-password
                />
              </el-form-item>
              
              <el-button 
                type="primary" 
                native-type="submit" 
                class="login-btn"
              >
                登录
              </el-button>
            </el-form>
  
            <div class="login-footer">
              <el-link class="login-link" type="primary" @click="handleRegister">注册用户</el-link>
              <el-link class="login-link" type="primary" @click="handleResetPassword">修改密码</el-link>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </template>
  
  <script setup>
  import { reactive } from 'vue'
  import { useRouter } from 'vue-router'
//   import { 
//     User, 
//     Lock, 
//     Alipay, 
//     Wechat, 
//     QQ 
//   } from '@element-plus/icons-vue'
  
  const router = useRouter()
  
  const form = reactive({
    username: '',
    password: ''
  })
  
  const handleSubmit = () => {
    console.log('登录表单提交:', form)
    // 这里添加实际的登录逻辑
  }
  
  const handleSocialLogin = (type) => {
    console.log('第三方登录:', type)
    // 这里添加第三方登录逻辑
  }
  
  const handleRegister = () => {
    router.push('/register')
  }
  
  const handleResetPassword = () => {
    router.push('/reset-password')
  }
  </script>
  
  <style scoped>
  .login-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f5f5f5;
    /* padding: 20px; */
  }
  
  .login-row {
    width: 100%;
    max-width: 900px;
  }
  
  .social-login-section,
  .form-login-section {
    background-color: #fff;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    height: 100%;
  }
  
  .login-title {
    text-align: center;
    margin-bottom: 30px;
    color: #333;
  }
  
  .social-login-buttons {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  
  .social-btn {
    margin-left: 0 !important;
    margin-right: 0 !important;

    width: 100%;
    height: 40px;
    color: white;
    border: none;
  }
  
  .social-btn:hover {
    opacity: 0.9;
  }
  
  .qq {
    background-color: #12b7f5;
  }
  
  .wechat {
    background-color: #07c160;
  }
  
  .alipay {
    background-color: #1677ff;
  }
  
  .login-btn {
    width: 100%;
    height: 40px;
    margin-top: 10px;
  }
  
  .login-footer {
    display: flex;
    justify-content: space-between;
    /* margin-top: 20px; */
  }
  
  /* 响应式调整 - 小于800px时变为上下布局 */
  @media (max-width: 800px) {
    .login-row {
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
    
    .social-col,
    .form-col {
      width: 100%;
      margin-bottom: 20px;
    }
    .login-footer{
        flex-direction: column;
    }

    .login-footer .login-link{
        margin-top: 20px;
    }
    .social-login-section,
    .form-login-section {
      padding: 30px;
    }
  }
  </style>