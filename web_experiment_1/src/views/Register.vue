<template>
    <div class="register-container">
      <div class="register-title">
        <h2>注册新用户</h2>
      </div>
  
      <div class="register-content">
        <!-- 左侧：表单部分 -->
        <div class="form-section">
          <el-form 
            :model="form" 
            @submit.prevent="handleSubmit"
            label-position="top"
          >
            <el-form-item label="用户名">
              <el-input 
                v-model="form.name" 
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
            
            <el-form-item label="邮箱">
              <el-input 
                v-model="form.email" 
                type="email" 
                placeholder="请输入邮箱"
                :prefix-icon="Message"
              />
            </el-form-item>
            
            <el-form-item label="年 / 月 / 日">
              <el-date-picker
                v-model="form.birthday"
                type="date"
                placeholder="选择日期"
                format="YYYY / MM / DD"
                value-format="YYYY-MM-DD"
              />
            </el-form-item>
          </el-form>
        </div>
  
        <!-- 右侧：头像上传部分 -->
        <div class="avatar-section">
          <div class="avatar-upload-container">
            <div class="avatar-label">请上传头像</div>
            <el-upload
              class="avatar-uploader"
              action="#"
              :show-file-list="false"
              :auto-upload="false"
              :on-change="handleAvatarChange"
              accept="image/*"
            >
              <img v-if="form.avatar" :src="form.avatar" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </div>
        </div>
      </div>
  
      <!-- 底部按钮 -->
      <div class="register-actions">
        <el-button 
          type="primary" 
          class="register-btn"
          @click="handleSubmit"
        >
          注册用户
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
    Lock, 
    Message,
    Plus
  } from '@element-plus/icons-vue'

  import{ userRegister ,getOssUploadCredential} from "../api/user.js"
  import axios from 'axios';

  const router = useRouter()
  
  const form = reactive({
    name: '',
    password: '',
    email: '',
    birthday: '',
    avatar: ''
  })
  
  const handleSubmit = async () => {
    console.log('注册表单提交:', form)
    const res = await userRegister(form);
    console.log(res);

    if(res.code == 0){
      alert("注册成功！");
      //TODO 换一种提醒方式
    }
    // 实际注册逻辑
  }
  
  const handleAvatarChange = async (file) => {
    //Base64方法
    // const reader = new FileReader()
    // reader.onload = (e) => {
    //   form.avatar = e.target.result
    // }
    // reader.readAsDataURL(file.raw)
    // console.log(reader);
    // console.log(form);


    try{
      console.log(file.name);
      
      const uploadUrl = await getOssUploadCredential(file.name,file.raw.type);

      console.log(uploadUrl);
      console.log(file.raw.type);

      // 直传文件到 OSS
      await axios.put(uploadUrl, file.raw,{
        headers:{
          'Content-Type': file.raw.type,
          'x-oss-forbid-overwrite': 'true'
        },
        transformRequest: [] // 禁止axios修改Content-Type
      });


      // 存储 URL 到表单（去掉签名参数）
      form.avatar = uploadUrl.split('?')[0];


      return false; // 阻止默认上传

    } catch (error) {
      console.error('上传失败', error);
      return false;
    }
  }


  
  const handleBackToLogin = () => {
    router.push('/login')
  }
  </script>
  
  <style scoped>
  .register-container {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #f5f5f5;
    /* padding: 20px; */
  }
  
  .register-content {
    display: flex;
    width: 80%;
    max-width: 900px;
    background-color: #fff;
    border-radius: 8px;
    overflow: hidden;
  }
  
  .register-title {
    text-align: center;
    color: #333;
    background-color: #fff;
    width: 80%;
    max-width: 900px;
    /* padding: 20px 0; */
    margin-bottom: 0;
  }
  
  .register-title h2 {
    margin-bottom: 0;
  }
  
  .form-section {
    flex: 1;
    padding: 20px;
  }
  
  .avatar-section {
    flex: 1;
    padding: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #fff;
  }
  
  .avatar-upload-container {
    width: 100%;
    text-align: center;
  }
  
  .avatar-label {
    margin-bottom: 20px;
    font-size: 14px;
    color: var(--el-text-color-regular);
  }
  
  .avatar-uploader {
    width: 100%;
    display: flex;
    justify-content: center;
  }
  
  .avatar-uploader :deep(.el-upload) {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    width: 200px;
    height: 200px;
  }
  
  .avatar-uploader :deep(.el-upload:hover) {
    border-color: var(--el-color-primary);
  }
  
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 200px;
    height: 200px;
    text-align: center;
    line-height: 200px;
  }
  
  .avatar {
    width: 200px;
    height: 200px;
    display: block;
    object-fit: cover;
  }
  
  .register-actions {
    background-color: #fff;
    width: 80%;
    max-width: 900px;
    display: flex;
    /* justify-content: space-between;  */
    /* margin-top: 20px; */

    /* padding: 20px 40px; */
    /* /* border-radius: 0 0 8px 8px; */
  }
  
  .register-btn {
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
    .register-content {
      flex-direction: column;
    }
    
    .form-section,
    .avatar-section {
      padding: 20px;
    }
    
    .avatar-section {
      border-top: 1px solid #eee;
    }
    
    .register-actions {
      flex-direction: column;
      /* gap: 10px; */
    }
    
    .register-btn,
    .back-btn {
      width: 100%;
    }
  }
  </style>