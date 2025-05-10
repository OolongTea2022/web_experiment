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
  {
    path: "/Register",
    //路由懒加载
    component: () => import("../views/Register.vue"),
  },
  {
    path: "/UpdatePassword",
    //路由懒加载
    component: () => import("../views/UpdatePassword.vue"),
  },
  {
    path: "/Home",
    //路由懒加载
    component: () => import("../views/Home.vue"),
    children: [
      {
        path: "Info", // 注意这里没有斜杠
        component: () => import("../views/Info.vue"),
      },
      {
        path: "HomeDefault", // 注意这里没有斜杠
        component: () => import("../views/HomeDefault.vue"),
      },
      {
        path: "UserManagement", 
        component: () => import("../views/UserContactManagement.vue"),
      },
      {
        path: "ArticleManagement", 
        component: () => import("../views/ArticleManagement.vue"),
      },
      // 新增作者文章管理动态路由
      {
        path: "ArticleManagement/:authorId", // 动态路由
        component: () => import("../views/AuthorArticles.vue"),
        props: true // 将路由参数作为props传递给组件
      },

      //测试页面
      {
        path: "test",
        component: () => import("../views/Test/Test1.vue"),
      },
      {
        path: "test2",
        component: () => import("../views/Test/Test2.vue"),
      },
    ],

  },




//测试
  {
    path: "/Test",
    //路由懒加载
    component: () => import("../views/Test/Test1.vue"),
  },
  {
    path: "/Test2",
    //路由懒加载
    component: () => import("../views/Test/Test2.vue"),
  },

];

// 创建路由
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 导出路由
export default router;