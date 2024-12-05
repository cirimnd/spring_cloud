<script lang="ts" setup>
import { ref } from 'vue';
import { Book } from '@/components/BaDataStruct/Book';

// 创建 Book 实例
const myBook = new Book({
  title: 'The Great Gatsby',
  author: 'F. Scott Fitzgerald',
  price: 19.99,
  isbn: '1911245',
  category: 'Fiction',
  stock: 20,
  description: 'A classic novel of the Jazz Age.',
});

// 按钮点击事件处理
const handleButtonClick = () => {
  alert('返回首页功能未实现');
};

// 订单生成部分
const num = ref(1); // 默认数量为 1
const costT = ref(myBook.price); // 初始化总价格
const address = ref('');

// 数量变化时更新总价格
const handleChange = () => {
  costT.value = num.value * myBook.price;
};
</script>

<template>
  <div class="Mainbox">
    <div class="body">
      <!-- Head1 区域 -->
      <div class="head1">
        <button @click="handleButtonClick">返回首页</button>
      </div>

      <!-- Body1 和 Aside3 -->
      <div class="content">
        <!-- Body1 区域 -->
        <div class="body1">
          <p>暂无图片</p>
        </div>
        <!-- Aside3 区域 -->
        <div class="aside3">
          <h2>{{ myBook.title }}</h2>
          <p>作者: <span>{{ myBook.author }}</span></p>
          <p>价格: <span>${{ myBook.price.toFixed(2) }}</span></p>
          <p>分类: <span>{{ myBook.category }}</span></p>
          <p>ISBN: <span>{{ myBook.isbn }}</span></p>
          <p>库存: <span>{{ myBook.stock }}</span></p>
          <p>描述: <span>{{ myBook.description }}</span></p>
        </div>
      </div>
    </div>
    <!-- Aside 区域 -->
    <div class="aside">
      <div class="aside1">
        <div class="aside11">
          <h3>购买信息</h3>
          <p>数量：<el-input-number v-model="num" :min="1" :max="999" @change="handleChange" /></p>
          <p>总价格：<span class="price">{{ costT.toFixed(2) }}</span></p>
          <p>地址：<el-input v-model="address" style="width: 240px" placeholder="请输入地址" /></p>
        </div>
        <div class="aside12">
          <button class="buy-btn">购买</button>
          <button class="cart-btn">加入购物车</button>
        </div>
      </div>
      <div class="aside2">
        <p>评论区，待开发</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.Mainbox {
  height: 930px;
  width: 1650px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: row;
   padding: 10px; /* 添加内边距 */
  gap: 20px;
}

.body {
  flex: 2;
  background-color: #eeadad;
  border-radius: 10px; /* 圆角 */
  display: flex;
  flex-direction: column;
  gap: 10px;
}

/* Head1 样式 */
.head1 {
  height: 15%;
  background-color: #f0f8ff;
  display: flex;
  align-items: center;
  border-radius: 10px;
  gap:20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
 padding: 10px; /* 添加内边距 */
}

.head1 button {
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #6c9ccf;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.head1 button:hover {
  background-color: #3690f0;
}

/* Body1 和 Aside1 的容器 */
.content {
  flex: 1;
  display: flex;
  gap: 10px;
}

/* Body1 样式 */
.body1 {
  flex: 2;
  background-color: #fdfdfd;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  font-size: 18px;
  color: #999;
}

/* Aside1 样式 */
.aside3 {
  flex: 1;
  background-color: #e3f2fd;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* .aside1 h2 {
  font-size: 22px;
  color: #007bff;
  margin-bottom: 10px;
}

.aside1 p {
  font-size: 16px;
  color: #555;
  margin-bottom: 5px;
}

.aside1 p span {
  font-weight: bold;
  color: #333;
} */

/* 原有 Aside 样式 */
.aside {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 20px;
  border-radius: 10px;
  justify-content: center;
  align-items: center;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.aside1 {
  width:100%;
  height:30%;
  background-color: #9ac3ed;
  border-radius: 10px; /* 圆角 */
   justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  flex-direction: column; /* 使子元素垂直排列 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  gap:20px;
}

.aside11{
    margin-bottom: 20px;
  text-align: left;
  gap:20px;
  padding-left: 20px;
  .aside11 h3 {
  font-size: 18px;
  color: #007bff;
  margin-bottom: 10px;
}

.aside11 p {
  margin: 5px 0;
  font-size: 16px;
  color: #333;
}

.aside11 .price {
  font-weight: bold;
  color: #d9534f;
}
}
.aside12{
    padding: 20px;
    flex-direction: row; /* 使子元素垂直排列 */
    gap:20px;
}
.buy-btn,
.cart-btn {
  padding: 10px 20px;
  font-size: 14px;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.buy-btn {
  background-color: #28a745;
}

.buy-btn:hover {
  background-color: #218838;
}

.cart-btn {
  background-color: #ffc107;
}

.cart-btn:hover {
  background-color: #e0a800;
}

.aside2 {
 width:100%;
  height:70%;
  background-color: #89eea7;
  border-radius: 10px; /* 圆角 */
   justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  flex-direction: column; /* 使子元素垂直排列 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  gap:20px;
}
</style>
