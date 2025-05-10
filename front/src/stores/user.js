import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  // 用户信息状态
  const userInfo = ref(null)
  
  // 设置用户信息（登录成功后调用）
  const setUserInfo = (data) => {
    userInfo.value = {
      ...data,
      // 转换时间戳为可读日期（可选）
      birthday: data.birthday ? new Date(data.birthday).toLocaleDateString() : null
    }
  }

  // 清除用户信息（退出登录时调用）
  const clearUserInfo = () => {
    userInfo.value = null
  }

  return { 
    userInfo,
    setUserInfo,
    clearUserInfo
  }
}, {
  // 持久化配置
  persist: {
    key: 'user-store',       // 存储的key名称（可选）
    storage: localStorage,   // 指定存储方式（默认localStorage）
    paths: ['userInfo']      // 只持久化userInfo字段（可选）
  }
})