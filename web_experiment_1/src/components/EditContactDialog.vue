<template>
    <el-dialog
      v-model="dialogVisible"
      :title="`修改联系人 - ${formData.name}`"
      width="600px"
      :before-close="handleClose"
    >
      <el-form
        ref="formRef"
        :model="formData"
        :rules="rules"
        label-width="80px"
        label-position="right"
      >
        <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="formData.date"
            type="datetime"
            placeholder="选择日期时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
        </el-form-item>
  
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名" />
        </el-form-item>
  
        <el-form-item label="地区" required>
          <el-col :span="11">
            <el-form-item prop="province">
              <el-select
                v-model="formData.province"
                placeholder="请选择省份"
                style="width: 100%"
                @change="handleProvinceChange"
              >
                <el-option
                  v-for="item in provinces"
                  :key="item"
                  :label="item"
                  :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="2" style="text-align: center">-</el-col>
          <el-col :span="11">
            <el-form-item prop="city">
              <el-select
                v-model="formData.city"
                placeholder="请选择城市"
                style="width: 100%"
                :disabled="!formData.province"
              >
                <el-option
                  v-for="item in currentCities"
                  :key="item"
                  :label="item"
                  :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
  
        <el-form-item label="地址" prop="address">
          <el-input
            v-model="formData.address"
            type="textarea"
            placeholder="请输入地址"
          />
        </el-form-item>
  
        <el-form-item label="邮编" prop="zip">
          <el-input v-model="formData.zip" placeholder="请输入邮编" />
        </el-form-item>
      </el-form>
  
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleConfirm"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </template>
  
  <script setup>
  import { ref, reactive, computed } from 'vue'
  import { ElMessage } from 'element-plus'
  
    // 省份数据
    const provinces = ref(["北京市", "天津市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "上海省", "江苏省", "浙江省", "安徽省", "福建省", "江西省",
	"山东省", "河南省",
	"湖北省", "湖南省", "广东省", "广西省", "海南省", "重庆省", "四川省", "贵州省", "云南省", "西藏省", "陕西省", "甘肃省", "青海省", "宁夏省", "新疆维吾尔省"
]);
  
  // 城市数据
  const citiesData = ref([
	["北京市"],
	["天津市"],
	["石家庄市", "唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市", "张家口市", "承德市", "沧州市", "廊坊市", "衡水市"],
	["太原市", "大同市", "阳泉市", "长治市", "晋城市", "朔州市", "忻州市", "吕梁市", "晋中市", "临汾市", "运城市"],
	["呼和浩特市", "包头市", "乌海市", "赤峰市", "呼伦贝尔市", "兴安盟市", "通辽市", "锡林郭勒盟市", "乌兰察布盟市", "伊克昭盟市", "巴彦淖尔盟市", "阿拉善盟市"],
	["沈阳市", "大连市", "鞍山市", "抚顺市", "本溪市", "丹东市", "锦州市", "营口市", "阜新市", "辽阳市", "盘锦市", "铁岭市", "朝阳市", "葫芦岛市", "其他"],
	["长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "***市", "白城市", "延边朝鲜族自治州市", "其他"],
	["哈尔滨市", "齐齐哈尔市", "鹤岗市", "双鸭山市", "鸡西市", "大庆市", "伊春市", "牡丹江市", "佳木斯市", "七台河市", "黑河市", "绥化市", "大兴安岭地区市", "其他"],
	["上海"],
	["南京市", "苏州市", "无锡市", "常州市", "镇江市", "南通市", "泰州市", "扬州市", "盐城市", "连云港市", "徐州市", "淮安市", "宿迁市", "其他"],
	["杭州市", "宁波市", "温州市", "嘉兴市", "湖州市", "绍兴市", "金华市", "衢州市", "舟山市", "台州市", "丽水市", "其他"],
	["合肥市", "芜湖市", "蚌埠市", "淮南市", "马鞍山市", "淮北市", "铜陵市", "安庆市", "黄山市", "滁州市", "阜阳市", "宿州市", "巢湖市", "六安市", "亳州市",
		"池州市", "宣城市", "其他"
	],
	["福州市", "厦门市", "莆田市", "三明市", "泉州市", "漳州市", "南平市", "龙岩市", "宁德市", "其他"],
	["南昌市", "景德镇市", "萍乡市", "九江市", "新余市", "鹰潭市", "赣州市", "吉安市", "宜春市", "抚州市", "上饶市", "其他"],
	["济南市", "青岛市", "淄博市", "枣庄市", "东营市", "烟台市", "潍坊市", "济宁市", "泰安市", "威海市", "日照市", "莱芜市", "临沂市", "德州市", "聊城市", "滨州市",
		"菏泽市", "其他"
	],
	["郑州市", "开封市", "洛阳市", "平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市", "许昌市", "漯河市", "三门峡市", "南阳市", "商丘市", "信阳市",
		"周口市", "驻马店市", "焦作市",
		"其他"
	],
	["武汉市", "黄石市", "十堰市", "荆州市", "宜昌市", "襄樊市", "鄂州市", "荆门市", "孝感市", "黄冈市", "咸宁市", "随州市", "恩施土家族苗族自治州市", "仙桃市",
		"天门市", "潜江市",
		"神农架林区市", "其他"
	],
	["长沙市", "株洲市", "湘潭市", "衡阳市", "邵阳市", "岳阳市", "常德市", "张家界市", "益阳市", "郴州市", "永州市", "怀化市", "娄底市", "湘西土家族苗族自治州市",
		"其他"],
	["广州市", "深圳市", "东莞市", "中山市", "潮州市", "揭阳市", "云浮市", "珠海市", "汕头市", "韶关市", "佛山市", "江门市", "湛江市", "茂名市", "肇庆市", "惠州市",
		"梅州市", "汕尾市",
		"河源市", "阳江市", "清远市"
	],
	["南宁市", "柳州市", "桂林市", "梧州市", "北海市", "防城港市", "钦州市", "贵港市", "玉林市", "百色市", "贺州市", "河池市", "来宾市", "崇左市", "其他"],
	["海口市", "三亚市", "五指山市", "琼海市", "儋州市", "文昌市", "万宁市", "东方市", "澄迈县市", "定安县市", "屯昌县市", "临高县市", "白沙黎族自治县市",
		"昌江黎族自治县市", "乐东黎族自治县市",
		"陵水黎族自治县市", "保亭黎族苗族自治县市", "琼中黎族苗族自治县市", "其他"
	],
	["重庆市"],
	["成都市", "自贡市", "攀枝花市", "泸州市", "德阳市", "绵阳市", "广元市", "遂宁市", "内江市", "乐山市", "南充市", "眉山市", "宜宾市", "广安市", "达州市",
		"雅安市", "巴中市", "资阳市",
		"阿坝藏族羌族自治州市", "甘孜藏族自治州市", "凉山彝族自治州市", "其他"
	],
	["贵阳市", "六盘水市", "遵义市", "安顺市", "铜仁地区市", "毕节地区市", "黔西南布依族苗族自治州市", "黔东南苗族侗族自治州市", "黔南布依族苗族自治州市", "其他"],
	["昆明市", "曲靖市", "玉溪市", "保山市", "昭通市", "丽江市", "普洱市", "临沧市", "德宏傣族景颇族自治州市", "怒江傈僳族自治州市", "迪庆藏族自治州市", "大理白族自治州市",
		"楚雄彝族自治州市",
		"红河哈尼族彝族自治州市", "文山壮族苗族自治州市", "西双版纳傣族自治州市", "其他"
	],
	["拉萨市", "那曲地区市", "昌都地区市", "林芝地区市", "山南地区市", "日喀则地区市", "阿里地区市", "其他"],
	["西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市", "延安市", "汉中市", "榆林市", "安康市", "商洛市", "其他"],
	["兰州市", "嘉峪关市", "金昌市", "白银市", "天水市", "武威市", "酒泉市", "张掖市", "庆阳市", "平凉市", "定西市", "陇南市", "临夏回族自治州市", "甘南藏族自治州市",
		"其他"
	],
	["西宁市", "海东地区市", "海北藏族自治州市", "海南藏族自治州市", "黄南藏族自治州市", "果洛藏族自治州市", "玉树藏族自治州市", "海西蒙古族藏族自治州市", "其他"],
	["银川市", "石嘴山市", "吴忠市", "固原市", "中卫市", "其他"],
	["乌鲁木齐市", "克拉玛依市", "吐鲁番地区市", "哈密地区市", "和田地区市", "阿克苏地区市", "喀什地区市", "克孜勒苏柯尔克孜自治州市", "巴音郭楞蒙古自治州市", "昌吉回族自治州市",
		"博尔塔拉蒙古自治州市",
		"石河子市", "阿拉尔市", "图木舒克市", "五家渠市", "伊犁哈萨克自治州市", "其他"
	]
]);
  
  
  const formRef = ref()
  const dialogVisible = ref(false)
  
  const formData = reactive({
    id: '',
    date: '',
    name: '',
    province: '',
    city: '',
    address: '',
    zip: ''
  })
  
  const rules = reactive({
    date: [{ required: true, message: '请选择日期', trigger: 'change' }],
    name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
    province: [{ required: true, message: '请选择省份', trigger: 'change' }],
    city: [{ required: true, message: '请选择城市', trigger: 'change' }],
    address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
    zip: [
      { required: true, message: '请输入邮编', trigger: 'blur' },
      { pattern: /^\d{6}$/, message: '邮编必须为6位数字', trigger: 'blur' }
    ]
  })
  
  // 计算当前省份对应的城市列表
  const currentCities = computed(() => {
    if (!formData.province) return []
    const index = provinces.value.indexOf(formData.province)
    return index !== -1 ? citiesData.value[index] : []
  })
  
  // 省份变化时更新城市选择
  const handleProvinceChange = () => {
    formData.city = ''
  }
  
  // 打开对话框并填充数据
  const open = (row) => {
    Object.assign(formData, {
      id: row.id,
      date: row.date,
      name: row.name,
      province: row.province,
      city: row.city,
      address: row.address,
      zip: row.nickname // 注意：mock数据中邮编字段是nickname
    })
    dialogVisible.value = true
  }
  
  // 关闭对话框
  const handleClose = () => {
    formRef.value?.resetFields()
    dialogVisible.value = false
  }
  
  // 确认提交
  const handleConfirm = () => {
    formRef.value.validate((valid) => {
      if (valid) {
        // TODO这里可以调用API提交修改
        ElMessage.success('修改联系人成功')
        dialogVisible.value = false
        emit('success', formData)
      }
    })
  }
  
  // 暴露方法给父组件
  defineExpose({ open })
  
  // 定义事件
  const emit = defineEmits(['success'])
  </script>
  
  <style scoped>
  .dialog-footer {
    display: flex;
    justify-content: flex-end;
  }
  </style>