<template>
  <div class="login-container">
      <div class="login-title">
          <h2>登录</h2>
      </div>
      

    <div class="login-content">
      <!-- 左侧：社交登录 -->
      <div class="social-section">

        <div class="social-buttons">
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

      <!-- 右侧：表单登录 -->
      <div class="form-section">
        <el-form 
          :model="form" 
          @submit.prevent="handleSubmit"
          label-position="top"
        >
          <el-form-item label="用户名">
            <el-input 
              v-model="form.userName" 
              placeholder="请输入用户名"
              :prefix-icon="User"
            />
          </el-form-item>
          
          <el-form-item label="密码">
            <el-input 
              v-model="form.userPassword" 
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
          <el-link type="primary" @click="handleRegister">注册用户</el-link>
          <el-link type="primary" @click="handleResetPassword">修改密码</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { userLogin } from "../api/user"

import { 
  User, 
  Lock, 
  // Alipay, 
  // Wechat, 
  // QQ 
} from '@element-plus/icons-vue'

const router = useRouter()

const form = reactive({
  userName: '',
  userPassword: ''
})

const handleSubmit = async () => {
  console.log('登录表单提交:', form)

  const params = {
    userName:form.userName,
    userPassword:form.userPassword
  }


  const res = await userLogin(params);
  console.log(res);
  if(res.code == 0){
    router.push("/Home")
  }
  // 实际登录逻辑
}

const handleSocialLogin = (type) => {
  console.log('第三方登录:', type)
  // 第三方登录逻辑
}

const handleRegister = () => {
  router.push('/register')
}

const handleResetPassword = () => {
  router.push('/UpdatePassword')
}
</script>

<style scoped>
.login-container {
  width: 100%;
  min-height: 100vh;
  display: flex;

  flex-direction: column;    /* */

  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  /* padding: 20px; */
}

.login-content {
  display: flex;
  width: 80%;
  max-width: 900px;
  /* gap: 40px; */
}

.social-section,
.form-section {
  flex: 1;
  background-color: #fff;
  padding: 40px;
  border-radius: 8px;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); */
}

.login-title {
  text-align: center;
  /* margin-bottom: 30px; */
  color: #333;
  background-color: #fff;
  width: 80%;
  max-width: 900px;
}

.login-title h2{
      margin-bottom: 0px;
  }

.social-buttons {
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
  margin-top: 20px;
}

/* 响应式布局 - 小于800px时变为上下布局 */
@media (max-width: 800px) {
  .login-content {
    flex-direction: column;
    gap: 20px;
  }
  
  .social-section,
  .form-section {
    padding: 30px;
  }
}
</style>