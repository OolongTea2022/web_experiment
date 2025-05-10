# Web Experiment Project

这是一个基于Vue 3和Spring Boot的全栈Web实验项目。

## 项目结构

```
web_experiment/
├── front/          # 前端项目目录
│   ├── src/        # 源代码
│   ├── public/     # 静态资源
│   └── package.json # 前端依赖配置
└── back/           # 后端项目目录
    ├── src/        # 源代码
    └── pom.xml     # 后端依赖配置
```

## 技术栈

### 前端 (front/)
- Vue 3 - 渐进式JavaScript框架
- Vite - 现代前端构建工具
- Element Plus - UI组件库
- Pinia - 状态管理
- Vue Router - 路由管理
- ECharts - 数据可视化
- Axios - HTTP客户端

### 后端 (back/)
- Spring Boot 2.6.13 - Java Web框架
- MyBatis Plus - ORM框架
- MySQL - 数据库
- Swagger - API文档
- Aliyun OSS - 对象存储服务
- Lombok - 简化Java代码

## 运行说明

### 前端运行
1. 进入前端目录：
```bash
cd front
```

2. 安装依赖：
```bash
npm install
```

3. 启动开发服务器：
```bash
npm run dev
```

### 后端运行
1. 确保已安装Java 8和Maven
2. 进入后端目录：
```bash
cd back
```

3. 使用Maven构建项目：
```bash
mvn clean install
```

4. 运行Spring Boot应用：
```bash
mvn spring-boot:run
```

## 功能特性
- 前后端分离架构
- RESTful API设计
- 数据可视化展示
- 文件上传功能（阿里云OSS）
- API文档自动生成（Swagger）

## 注意事项
- 前端默认运行在 http://localhost:5173（ 登录界面位于http://localhost:5173/login ）
- 后端默认运行在 http://localhost:8080
- 后端启动后swagger接口文档在 http://localhost:8080/swagger-ui/index.html#/
- 请确保MySQL服务已启动并正确配置（三个表的建表语句位于back\src\main\resources\db.sql）
- 使用前请在.yml中配置好自己的阿里云OSS的相关参数


