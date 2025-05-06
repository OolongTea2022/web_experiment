<template>
    <div id="app" :class="currentTheme">
      <!-- 顶部导航栏 -->
      <el-container style="height: 7vh">
        <el-header style="height: 100%; background-color: var(--header-bg-color)">
          <!-- 汉堡菜单按钮 -->
          <el-icon 
            class="hamburger" 
            @click="toggleMenu"
            v-show="showHamburger"
          >
            <component :is="isCollapsed ? Expand : Fold" />
          </el-icon>
          <span class="header-title">WEB实验</span>
        </el-header>
      </el-container>
  
      <!-- 移动端遮罩层 -->
      <div 
        v-show="maskVisible"
        class="mobile-mask"
        @click="closeMenu"
      ></div>
  
      <!-- 主体布局 -->
      <el-container style="height: 93vh;">
        <!-- 侧边栏 -->
        <el-aside 
          :style="{
            width: '200px',
            left: asidePosition,
            backgroundColor: 'var(--aside-bg-color)'
          }"
          class="aside-transition"
        >
          <el-menu
            router
            :collapse-transition="false"
            active-text-color="var(--menu-active-color)"
            background-color="var(--aside-bg-color)"
            class="side-menu"
            :default-active="activeMenu"
            text-color="var(--menu-text-color)"
          >
            <!-- 个人信息展示（默认最初进入界面） -->
            <el-menu-item index="/Home/Info">
              <el-icon><HomeFilled  /></el-icon>
              <span>系统首页</span>
            </el-menu-item>


            <!-- 首页菜单项 -->
            <el-menu-item index="/Home/HomeDefault">
              <el-icon><HomeFilled /></el-icon>
              <template #title><span>首页</span></template>
            </el-menu-item>
  
            <!-- 功能菜单 -->
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
  
            <!-- 主题切换 -->
            <el-sub-menu index="2">
              <template #title>
                <el-icon><Document /></el-icon>
                <span>主题切换</span>
              </template>
              <el-menu-item-group>
                <el-menu-item @click="changeTheme('blue')">蓝色风格</el-menu-item>
                <el-menu-item @click="changeTheme('yellow')">黄色风格</el-menu-item>
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
  
        <!-- 主要内容区域 -->
        <el-main :style="{ marginLeft: mainMargin }">
          <router-view/>
        </el-main>
      </el-container>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, onMounted, onUnmounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { Expand, Fold } from '@element-plus/icons-vue';
  import {
    Document,
    Menu,
    Location,
    HomeFilled,
  } from '@element-plus/icons-vue'
  
  // 响应式状态
  const currentTheme = ref('blue-theme');
  const isCollapsed = ref(true);
  const windowWidth = ref(window.innerWidth);
  const maskVisible = ref(false);
  const route = useRoute();
  
  // 计算属性
  const isMobile = computed(() => windowWidth.value < 800);
  const showHamburger = computed(() => isMobile.value);
  const asidePosition = computed(() => {
    return isCollapsed.value ? '-200px' : '0';
  });
  const activeMenu = computed(() => route.path);
  const mainMargin = computed(() => {
    return isMobile.value ? '0' : '200px';
  });
  
  // 方法
  const toggleMenu = () => {
    isCollapsed.value = !isCollapsed.value;
    maskVisible.value = !isCollapsed.value;
  };
  
  const closeMenu = () => {
    isCollapsed.value = true;
    maskVisible.value = false;
  };
  
  const changeTheme = (theme) => {
    currentTheme.value = `${theme}-theme`;
    localStorage.setItem('app-theme', currentTheme.value);
    // closeMenu(); // 切换主题后自动关闭菜单
  };
  
  // 响应式处理
  const handleResize = () => {
    windowWidth.value = window.innerWidth;
    if (!isMobile.value) {
      isCollapsed.value = false;
      maskVisible.value = false;
    } else {
      isCollapsed.value = true;
    }
  };
  
  // 生命周期
  onMounted(() => {
    window.addEventListener('resize', handleResize);
    const savedTheme = localStorage.getItem('app-theme');
    if (savedTheme) currentTheme.value = savedTheme;
    handleResize(); // 初始检测
  });
  
  onUnmounted(() => {
    window.removeEventListener('resize', handleResize);
  });
  </script>
  
  <style scoped>
  /* 侧边栏动画 */
  .aside-transition {
    position: fixed;
    top: 7vh;
    bottom: 0;
    z-index: 1001;
    transition: left 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  }
  
  /* 汉堡按钮样式 */
  .hamburger {
    color: var(--menu-text-color);
    font-size: 24px;
    cursor: pointer;
    margin-right: 20px;
    transition: transform 0.3s ease;
    position: absolute;
    left: 15px;
  }
  
  .hamburger:hover {
    transform: scale(1.1);
  }
  
  .header-title {
    color: var(--menu-text-color);
    font-size: 1.2rem;
    letter-spacing: 2px;
    margin-left: 50px;
  }
  
  /* 移动端遮罩层 */
  .mobile-mask {
    position: fixed;
    top: 7vh;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 1000;
  }
  
  /* 移动端优化 */
  @media screen and (max-width: 800px) {
    .el-header {
      padding-left: 15px !important;
    }
    
    .el-main {
      margin-left: 0 !important;
      transition: margin 0.3s ease;
    }
    
    .side-menu {
      width: 200px !important;
    }
  }
  </style>
  
  <style>
  /* 全局主题样式 */
  :root, .blue-theme {
    --header-bg-color: #333333;
    --aside-bg-color: #555555;
    --menu-text-color: #ffffff;
    --menu-active-color: #ffd04b;
    --main-bg-color: #f5f5f5;
  }
  
  .yellow-theme {
    --header-bg-color: #8B8000;
    --aside-bg-color: #D4AF37;
    --menu-text-color: #2c3e50;
    --menu-active-color: #34495e;
    --main-bg-color: #FFFACD;
  }
  
  #app {
    height: 100vh;
  }
  
  .el-header {
    display: flex;
    align-items: center;
    background-color: var(--header-bg-color) !important;
    position: relative;
  }
  
  .el-aside {
    overflow: hidden;
    background-color: var(--aside-bg-color) !important;
  }
  
  .el-menu {
    border-right: none !important;
    background-color: var(--aside-bg-color) !important;
  }
  
  .el-menu-item {
    color: var(--menu-text-color) !important;
  }
  
  .el-menu-item.is-active {
    color: var(--menu-active-color) !important;
  }
  
  .el-sub-menu__title {
    color: var(--menu-text-color) !important;
  }
  
  .el-main {
    padding: 20px;
    background-color: var(--main-bg-color);
    transition: margin 0.3s ease;
  }
  </style>