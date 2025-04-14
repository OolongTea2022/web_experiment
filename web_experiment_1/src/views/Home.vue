<template>
  <div id="app" :class="currentTheme">
    <el-container style="height: 7vh">
      <el-header style="height: 100%; background-color: var(--header-bg-color)">
        <!-- <img src="./assets/vue.svg" alt="图标" style="position: relative;top: 10px; "> -->
        <!-- <span style="font-size: 20px;margin-left: 15px;color: white;">旅游管理系统</span> -->
      </el-header>
    </el-container>

    <el-container style="height: 93vh;">
      <el-aside style="overflow: hidden;min-height: 100%;background-color: var(--aside-bg-color); width: 200px;">
        <el-menu
          router
          active-text-color="var(--menu-active-color)"
          background-color="var(--aside-bg-color)"
          class="el-menu-vertical-demo"
          :default-active="$route.path"
          text-color="var(--menu-text-color)"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-menu-item index="/Home/HomeDefault">
            <el-icon><HomeFilled /></el-icon>
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

      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import {
  Document,
  Menu,
  Location,
  HomeFilled,
} from '@element-plus/icons-vue'

// 当前主题状态
const currentTheme = ref('blue-theme');

// 切换主题方法
const changeTheme = (theme) => {
  currentTheme.value = `${theme}-theme`;
  // 保存到localStorage实现持久化
  localStorage.setItem('app-theme', currentTheme.value);
};

// 初始化时检查保存的主题
onMounted(() => {
  const savedTheme = localStorage.getItem('app-theme');
  if (savedTheme) {
    currentTheme.value = savedTheme;
  }
});

// 获取当前路由
const route = useRoute();

// 菜单打开关闭方法
const handleOpen = (key, keyPath) => {
  console.log('菜单打开', key, keyPath);
};

const handleClose = (key, keyPath) => {
  console.log('菜单关闭', key, keyPath);
};
</script>

<style scoped>
.el-menu {
  border-right: none !important;
}
.el-main {
  padding: 0;
}
</style>

<style>
/* 全局样式 - 定义主题变量 */
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

/* 应用主题变量 */
#app {
  height: 100vh;
}

.el-header {
  background-color: var(--header-bg-color) !important;
}

.el-aside {
  background-color: var(--aside-bg-color) !important;
}

.el-menu {
  background-color: var(--aside-bg-color) !important;
  color: var(--menu-text-color) !important;
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

.el-menu-item-group__title {
  color: var(--menu-text-color) !important;
}

.el-main {
  background-color: var(--main-bg-color);
}
</style>