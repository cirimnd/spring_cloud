<script>
 import { ref } from 'vue';
 const inputName=ref('');
const inputPassword=ref('');
const inputPassAgain=ref('');

</script>


<template>
    <div class="L11">

    <div class="L111">
              
         <el-input v-model="inputName" style="width: 240px" placeholder="用户名" />
         <el-input v-model="inputPassword" style="width: 240px" placeholder="密码" />
         <el-input v-model="inputPassAgain" style="width: 240px" placeholder="再次输入密码" />
    </div>

    <div class="L112">
        <el-button type="success" round >注册</el-button>
    </div>

    </div>

</template>

<style lang="css" scoped>
.L11{
     width: 80%; /* 设置宽度为父容器的80% */
  max-width: 800px; /* 限制最大宽度 */
  margin: 20px auto; /* 上下外边距20px，水平居中 */
  padding: 20px; /* 内边距 */
  background-color: #ffffff; /* 白色背景 */
  border-radius: 10px; /* 圆角边框 */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 添加轻微阴影 */
  display: flex; /* 设置为flex布局 */
  flex-direction: column; /* 使子元素垂直排列 */
  align-items: center; /* 子元素水平居中 */
  justify-content: center; /* 子元素垂直居中 */
  text-align: center; /* 文本居中 */
  overflow: hidden; /* 隐藏溢出内容 */
  background-size: cover; /* 背景适应内容 */
  background-position: center; /* 背景居中 */
     
}

.L111 {
  width: 100%;
  flex: 4; /* 占主容器剩余空间的3份 */
  background-color: #f0f4ff; /* 浅蓝背景色 */
  display: flex; /* Flex 布局 */
  flex-direction: column; /* 子元素垂直排列 */
  align-items: center; /* 水平居中 */
  justify-content: center; /* 垂直居中 */
  border-radius: 10px; /* 圆角 */
  text-align: center;
  padding: 20px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1); /* 内阴影 */
}

/* L112 区块：较小的区块 */
.L112 {
  width: 100%;
  flex: 1; /* 占主容器剩余空间的1份 */
  background-color: #e0e7f7; /* 浅灰蓝背景色 */
  display: flex; /* Flex 布局 */
  flex-direction: column; /* 子元素垂直排列 */
  align-items: center; /* 水平居中 */
  justify-content: center; /* 垂直居中 */
  border-radius: 10px; /* 圆角 */
  text-align: center;
  padding: 20px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1); /* 内阴影 */
}
</style>

