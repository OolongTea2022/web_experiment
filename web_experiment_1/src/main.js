import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import { createPinia } from 'pinia' // 导入Pinia
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// 创建Pinia实例
const pinia = createPinia()
// 添加持久化插件
pinia.use(piniaPluginPersistedstate)



import './style.css'
import App from './App.vue'

import router from './router'

const app = createApp(App)

// app.use(ElementPlus, {
//     breakpoints: {
//       xs: 0,
//       sm: 600,  // 原768改为600
//       md: 800,  // 原992改为800
//       lg: 1200,
//       xl: 1920
//     }
//   })


// 使用Pinia
app.use(pinia)
app.use(ElementPlus)
app.use(router).mount('#app')
