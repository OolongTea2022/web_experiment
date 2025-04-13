import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


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

app.use(ElementPlus)
app.use(router).mount('#app')
