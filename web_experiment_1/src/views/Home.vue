
<template>

    <div id="app">
        <el-container style="height: 7vh ">
            <el-header style="height: 100%; background-color: #333333;">
                <!-- <img src="./assets/vue.svg" alt="图标" style="position: relative;top: 10px; "> -->
                <!-- <span style="font-size: 20px;margin-left: 15px;color: white;">旅游管理系统</span> -->
            </el-header>
    
        </el-container>
    
        <el-container style="height: 93vh;">
            <el-aside style="overflow: hidden;min-height: 100%;background-color: #555555;  width: 200px;">
                <el-menu
            router
            active-text-color="#ffd04b"
            background-color="#555555"
            class="el-menu-vertical-demo"
            :default-active="$router.path"
            text-color="#fff"
            @open="handleOpen"
            @close="handleClose"
            >
    
            <el-menu-item index="/Home/HomeDefault">
              <el-icon><HomeFilled  /></el-icon>
              <span>首页</span>
            </el-menu-item>
    
            <el-sub-menu index="1">
              <template #title>
                <el-icon><Location /></el-icon>
                <span>功能菜单</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/Home/UserManagement">用户管理</el-menu-item>
                <el-menu-item index="/Home/ArticleManagement">文章管理</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
    
            <el-sub-menu index="2">
              <template #title>
                <el-icon><Document /></el-icon>
                <span>主题切换</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/Home/BlueStyle">蓝色风格</el-menu-item>
                <el-menu-item index="/Home/YellowStyle">黄色风格</el-menu-item>

              </el-menu-item-group>
    
            </el-sub-menu>
                
            <el-sub-menu index="3">
              <template #title>
                <el-icon><Menu /></el-icon>
                <span>导航3</span>
              </template>
              <el-menu-item-group title="测试">
                <el-menu-item index="/Home/Test">Test</el-menu-item>

              </el-menu-item-group>
    
            </el-sub-menu>

                
            <el-sub-menu index="4">
              <template #title>
                <el-icon><Menu /></el-icon>
                <span>导航4</span>
              </template>
              <el-menu-item-group title="测试">
                <el-menu-item index="/Home/Test2">Test2</el-menu-item>
              </el-menu-item-group>
    
            </el-sub-menu>


          </el-menu>
            </el-aside>
    
    
    
    
            <!-- TODO 主页面设计 -->
            <el-main>
              <router-view/>
            </el-main>
        </el-container>
    
    
    
    </div>
    
    </template>
     
    <script setup>
    import {
    Document,
    Menu,
    Location,
    Setting,
    HomeFilled,
    } from '@element-plus/icons-vue'

    </script>
     
    <style scoped>
    .el-menu{
        border-right: none !important;
    }
    .el-main{
        padding: 0;
    }
    </style>