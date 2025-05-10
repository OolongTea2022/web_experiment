<template>
    <el-dialog v-model="visible" title="新增文章" width="50%">
      <el-form :model="form" label-width="80px" :rules="rules" ref="formRef">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item label="文章内容" prop="content">
          <el-input 
            v-model="form.content" 
            type="textarea" 
            :rows="5" 
            placeholder="请输入文章内容" 
          />
        </el-form-item>
      </el-form>
      
      <template #footer>
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { anchorEmits, ElMessage } from 'element-plus'

  import { saveArticle } from "../api/article"
  
  const visible = ref(false)
  const formRef = ref()
  
  const authorInfo = ref({
    avatar: "https://wtwlc-first-bucket.oss-cn-beijing.aliyuncs.com/avatar/4857773d-3ebd-4f56-983e-8b3424337a6d-steam_favicon.gif",
    birthday: "2025-05-06T15:35:32.500Z",
    email: "xxx@126.com",
    id: 0,
    money: 5.5,
    name: "test"
  })

  const form = ref({
    title: '',
    content: ''
  })
  
  const rules = {
    title: [
      { required: true, message: '请输入文章标题', trigger: 'blur' },
      { min: 3, max: 50, message: '长度在3到50个字符', trigger: 'blur' }
    ],
    content: [
      { required: true, message: '请输入文章内容', trigger: 'blur' },
      { min: 10, message: '内容至少10个字符', trigger: 'blur' }
    ]
  }
  
  const emit = defineEmits(['success'])
  
  const open = (userInfo) => {

    authorInfo.value = userInfo.value
    visible.value = true
    form.value = { title: '', content: '' }

    // console.log("!!!!!!!!!!!!!!!!!!!!!!!")
    // console.log(userInfo)
    // console.log(authorInfo)
    // console.log(userInfo.value)
    // console.log(authorInfo.value)

  }
  
  const handleSubmit = async () => {
    try {
      await formRef.value.validate()
      // 准备提交的数据
      const params = {
        title: form.value.title,
        content: form.value.content,
        author: authorInfo.value.name,
        authorId: authorInfo.value.id
      }
      console.log("新增文章参数",params);

      // 调用保存文章的API
      const res = await saveArticle(params);
      if(res.code == 0){
        emit('success', { ...form.value })
        visible.value = false
        ElMessage.success('文章保存成功')
      }else{
        console.error(res.message)
      }
    } catch (e) {
      ElMessage.warning('请完善表单信息')
    }
  }
  
  defineExpose({ open })
  </script>