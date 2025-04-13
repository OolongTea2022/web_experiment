<template>
    <div class="login-container">
      <h2 class="login-title">登录</h2>
      
      <div class="social-login">
        <button class="social-btn qq" @click="handleSocialLogin('qq')">
          QQ登录
        </button>
        <button class="social-btn wechat" @click="handleSocialLogin('wechat')">
          微信登录
        </button>
        <button class="social-btn alipay" @click="handleSocialLogin('alipay')">
          支付宝登录
        </button>

        
      </div>
      
      <div class="divider">
        <span class="divider-text">或</span>
      </div>
      
      <form class="login-form" @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="username">用户名</label>
          <input
            id="username"
            v-model="form.username"
            type="text"
            placeholder="请输入用户名"
            required
          />
        </div>
        
        <div class="form-group">
          <label for="password">密码</label>
          <input
            id="password"
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
            required
          />
        </div>
        
        <button type="submit" class="login-btn">登录</button>
      </form>
      
      <div class="login-footer">
        <a href="#" class="footer-link" @click.prevent="handleRegister">注册用户</a>
        <a href="#" class="footer-link" @click.prevent="handleResetPassword">修改密码</a>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive } from 'vue'
  import { useRouter } from 'vue-router'
  
  const router = useRouter()
  
  const form = reactive({
    username: '',
    password: ''
  })
  
  const handleSubmit = () => {
    console.log('登录表单提交:', form)
    // 这里添加实际的登录逻辑
    // 例如调用API验证用户名密码
  }
  
  const handleSocialLogin = (type) => {
    console.log('第三方登录:', type)
    // 这里添加第三方登录逻辑
    // 例如跳转到对应的OAuth授权页面
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
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #fff;
  }
  
  .login-title {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
  }
  
  .social-login {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  
  .social-btn {
    flex: 1;
    padding: 10px;
    /* margin: 0 5px; */
    margin: 10px;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    transition: opacity 0.3s;
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
  
  .divider {
    position: relative;
    margin: 20px 0;
    text-align: center;
  }
  
  .divider::before {
    content: "";
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    height: 1px;
    background-color: #eee;
    z-index: 1;
  }
  
  .divider-text {
    position: relative;
    padding: 0 10px;
    background-color: #fff;
    z-index: 2;
    color: #999;
  }
  
  .login-form {
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 5px;
    color: #666;
  }
  
  .form-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
  }
  
  .login-btn {
    width: 100%;
    padding: 10px;
    background-color: #1677ff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .login-btn:hover {
    background-color: #4096ff;
  }
  
  .login-footer {
    display: flex;
    justify-content: space-between;
  }
  
  .footer-link {
    color: #1677ff;
    text-decoration: none;
    cursor: pointer;
  }
  
  .footer-link:hover {
    text-decoration: underline;
  }
  </style>