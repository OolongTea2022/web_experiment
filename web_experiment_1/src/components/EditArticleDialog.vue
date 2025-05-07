<template>
    <el-dialog v-model="visible" title="编辑文章" width="50%">
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
        <el-button type="primary" @click="handleSubmit">保存</el-button>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { ElMessage } from 'element-plus'
  
  const visible = ref(false)
  const formRef = ref()
  
  const form = ref({
    id: null,
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
  
  const open = (row) => {
    visible.value = true
    form.value = { ...row }
  }
  
  const handleSubmit = async () => {
    try {
      await formRef.value.validate()
      emit('success', { ...form.value })
      visible.value = false
    } catch (e) {
      ElMessage.warning('请完善表单信息')
    }
  }
  
  defineExpose({ open })
  </script>