// 引入需要的模块
import { createRouter, createWebHistory } from "vue-router";

// import component from "element-plus/es/components/tree-select/src/tree-select-option.mjs";


// 下面使用了es6的对象增强写法，命名必须是routes
const routes = [

//   {
//     //默认展示，这是主页面路由必须加斜杠 /
//     path: "/",
//     component: () => import("../views/MainPanel.vue"),
//   },
{
    path: "/Login",
    //路由懒加载
    component: () => import("../views/Login.vue"),
  },

//测试
  {
    path: "/Test",
    //路由懒加载
    component: () => import("../views/Test.vue"),
  },
  {
    path: "/Test2",
    //路由懒加载
    component: () => import("../views/Test2.vue"),
  },

];

// 创建路由
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 导出路由
export default router;