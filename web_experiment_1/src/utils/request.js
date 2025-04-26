// src/utils/request.js
import axios from 'axios'

// 创建 axios 实例
const myAxios = axios.create({
  baseURL: "/api",  // API 基础路径
  timeout: 10000000,  // 请求超时时间
})

// 请求拦截器
myAxios.interceptors.request.use(
  (config) => {
    // 在请求发送之前处理请求
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`  // 设置 token
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
myAxios.interceptors.response.use(
  (response) => {
    // 成功响应处理
    return response.data
  },
  (error) => {
    // 错误响应处理
    console.error('API Error:', error.response || error.message)
    return Promise.reject(error)
  }
)

export default myAxios
