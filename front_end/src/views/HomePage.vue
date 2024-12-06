<script lang="ts" setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter()

//筛选选择值
const checked1 = ref(false);
const checked2 = ref(false);
const checked3 = ref(false);
const checked4 = ref(false);


const value = ref('')
const options = [
  {
    value: '作者',
    label: '作者',
  },
  {
    value: '书名',
    label: '书名',
  },
  {
    value: 'ISBN编号',
    label: 'ISBN编号',
  },
  
]


//
const input1 = ref('');

function loginOrRegister(){
  router.push({
    path:"/login"
  })
}

</script>

<template>
    <div class="Mainbox">
        <div class="head">
           <div class="head1">
            <el-text class="text">Bookmarket</el-text>
        <el-button class="button" type="primary">首页</el-button>
           </div>
              
           <div class="head2">
         

            <div class="head21">
                <div class="flex flex-wrap gap-4 items-center">
    <el-select
      v-model="value"
      placeholder="Select"
      size="large"
      style="width: 240px"
    >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>
         </div>

          <el-input v-model="input1" style="width: 240px" size="large" />
           <el-button size="large" >
            <el-icon><Search /></el-icon>Search</el-button>
            </div>

            <div class="head22">
                <!-- <el-button round size="large">购物车</el-button> -->
                <el-button round size="large" @click="loginOrRegister">注册/登录</el-button>
            </div>
              
                  
           </div>
            
        </div>


        <div class="content">
            <div class="body">
                <!-- 总览 -->
            </div>
            <div class="aside">
                <el-text class="text">分类</el-text>
                <div class="checkbox-group">
                    <el-checkbox v-model="checked1" label="自然科学" border />
                    <el-checkbox v-model="checked2" label="社会科学" border />
                    <el-checkbox v-model="checked3" label="艺术" border />
                    <el-checkbox v-model="checked4" label="文学" border />
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="css" scoped>
/* 主容器 Mainbox */
.Mainbox {
  height: 930px;
  width: 1650px;
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: aliceblue;
  background-size: cover; /* 背景图片覆盖容器 */
  background-position: center; /* 背景图片居中 */
  background-repeat: no-repeat; /* 背景图片不重复 */
  border-radius: 15px;
  padding: 10px; /* 添加内边距 */
  display: flex;
  flex-direction: column; /* 垂直排列 */
  gap: 20px; /* 各区块间距 */
}

/* 头部区域 head */
.head {
  width: 100%; /* 占据整个宽度 */
  height: 15%; /* 占据容器高度的15% */
  background-color: #e3f2fd; /* 浅蓝背景色 */
  border-radius: 10px; /* 圆角 */
  display: flex; /* Flex 布局 */
   flex-direction: row; /* 使子元素水平排列 */
  justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}
.head1{
    width: 20%;
    height: 100%;
    background-color:rgb(246, 243, 238);
    border-radius: 10px; /* 圆角 */
  display: flex; /* Flex 布局 */
   flex-direction: row; /* 使子元素垂直排列 */
  justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  gap: 20px; 
}

.text {
    font-size: 24px; /* 字体大小 */
    font-weight: bold; /* 加粗字体 */
    color: #2c3e50; /* 深蓝色 */
}

/* 按钮样式 */
.button {
    font-size: 16px; /* 按钮字体大小 */
    padding: 5px 15px; /* 按钮内边距 */
    border-radius: 5px; /* 按钮圆角 */
    background-color: #3498db; /* 按钮背景色 */
    color: white; /* 按钮文字颜色 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 按钮阴影效果 */
    transition: background-color 0.3s ease; /* 动态效果 */
}

/* 按钮悬停效果 */
.button:hover {
    background-color: #2980b9; /* 更深的蓝色 */
    cursor: pointer; /* 鼠标悬停效果 */
}

.head2{
       width: 80%;
    height: 100%;
    background-color:rgb(251, 250, 251);
    border-radius: 10px; /* 圆角 */
    display: flex; /* Flex 布局 */
   flex-direction: row; /* 使子元素水平排列 */
  align-items: center; /* 内容垂直居中 */
   justify-content: center; /* 内容水平居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
 
}

.head21{
 flex: 3; /* 占父容器宽度的 3/4 */
    height: 100%; /* 高度与父容器一致 */
    background-color: #f0f8ff; /* 浅蓝背景色 */
    border-radius: 8px; /* 圆角 */
    padding: 10px; /* 内边距 */
    display: flex; /* Flex 布局 */
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); /* 内阴影效果 */
    text-align: center; /* 文本居中 */
}

.head22{
     flex: 1; /* 占父容器宽度的 1/4 */
    height: 100%; /* 高度与父容器一致 */
    background-color: #e8f5e9; /* 浅绿色背景色 */
    border-radius: 8px; /* 圆角 */
    padding: 10px; /* 内边距 */
    display: flex; /* Flex 布局 */
    align-items: center; /* 垂直居中 */
    justify-content: center; /* 水平居中 */
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); /* 内阴影效果 */
    text-align: center; /* 文本居中 */
}

/* 主体和侧边栏父容器 content */
.content {
  display: flex; /* 水平排列子元素 */
  flex: 1; /* 占据剩余空间 */
  width: 100%; /* 占据容器的全部宽度 */
  gap: 20px; /* 子元素间距 */
}

/* 主体区域 body */
.body {
  flex: 3; /* 占父容器的 3/4 宽度 */
  background-color: #ffffff; /* 白色背景 */
  border-radius: 10px; /* 圆角 */
  display: flex; /* Flex 布局 */
  justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  flex-direction: column; /* 使子元素垂直排列 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

/* 侧边栏区域 aside */
.aside {
  flex: 1; /* 占父容器的 1/4 宽度 */
  background-color: #c8e6c9; /* 浅绿色背景 */
  border-radius: 10px; /* 圆角 */
  display: flex; /* Flex 布局 */
  justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  flex-direction: column; /* 使子元素垂直排列 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  gap: 20px;
}

/* 多选框组 checkbox-group */
.checkbox-group {
  display: flex;
  flex-direction: column; /* 竖直排列 */
  gap: 15px; /* 多选框之间的间距 */
  padding: 20px; /* 添加内边距 */
  background-color: #f5f5f5; /* 淡灰色背景 */
  border-radius: 10px; /* 圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  width: 80%; /* 控制宽度 */
}
</style>
