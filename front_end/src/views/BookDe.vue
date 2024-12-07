<script lang="ts" setup>
import { onBeforeMount, ref } from 'vue';
import { Book } from '@/components/BaDataStruct/Book';
import { useRoute,useRouter } from 'vue-router';
import { apiGetBookById } from '@/apis/getBookById';
import { apiAddCart } from '@/apis/addCart';
import { apiAddOrder } from '@/apis/addOrder';
import { ElMessage } from 'element-plus';
import {watch} from 'vue'
const route=useRoute();
const router=useRouter();
const{bookId,userId}=route.query

// 创建 Book 实例
// let myBook = ref<Book>(new Book());

let myBook = ref<Book>(new Book({
  title: '',
  author: '',
  price: 0,
  category: '',
  isbn: '',
  stock: 0,
  description: '',
}));

async function loadBook() {
  try {
    const res = await apiGetBookById(bookId as any);
    if (res.code === 1) {
      myBook.value = new Book(res.data)// 将接口数据转换为 Book 实例
      costT.value = myBook.value.price;
    }
  } catch (error) {
    console.error('加载书籍数据失败：', error);
  }
}

onBeforeMount(async()=>{
  loadBook();
})

// watch(
//   () => route.query.bookId,
//   (newBookId) => {
//     bookId = newBookId;
//     loadBook(); // 数据重新加载
//   }
// );

// 按钮点击事件处理
const handleButtonClick = () => {
  router.go(-1)
};

// 订单生成部分
const num = ref(1); // 默认数量为 1
const costT = ref(myBook.value.price); // 初始化总价格
const address = ref('');

// 数量变化时更新总价格
const handleChange = () => {
  costT.value = num.value * myBook.value.price;
};

async function addToCart()
{
  let res = await apiAddCart(userId as any,bookId as any,num.value)
  try {
    if(res.code==1) ElMessage.success("加入成功！");
    else ElMessage.error("加入失败")
  } catch (error) {
    console.log(error)
  }
};
async function addToOrder()
{
  let param={
    userId:userId as any,
    bookId:bookId as any,
    bookPrice:costT.value,
    state:1,
    address:address.value  
  }
  console.log(param)
  let res = await apiAddOrder(param)
  try {
    if(res.code==1) ElMessage.success("购买成功！");
    else ElMessage.error("购买失败")
  } catch (error) {
    console.log(error)
  }
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
          <h2>{{ myBook?.title || '加载中...' }}</h2>
          <p>作者: <span>{{ myBook?.author || '加载中...' }}</span></p>
          <p>价格: <span>${{ myBook?.price?.toFixed(2) || '0.00' }}</span></p>
          <p>分类: <span>{{ myBook?.category || '加载中...' }}</span></p>
          <p>ISBN: <span>{{ myBook?.isbn || '加载中...' }}</span></p>
          <p>库存: <span>{{ myBook?.stock || 0 }}</span></p>
          <p>描述: <span>{{ myBook?.description || '加载中...' }}</span></p>
        </div>
      </div>
    </div>
    <!-- Aside 区域 -->
    <div class="aside">
      <div class="aside1">
        <div class="aside11">
          <h3>购买信息</h3>
          <p>数量：<el-input-number v-model="num" :min="1" :max=myBook.stock @change="handleChange" /></p>
          <p>总价格：<span class="price">{{ costT.toFixed(2) }}</span></p>
          <p>地址：<el-input v-model="address" style="width: 240px" placeholder="请输入地址" /></p>
        </div>
        <div class="aside12">
          <button class="buy-btn" @click="addToOrder">购买</button>
          <button class="cart-btn" @click="addToCart">加入购物车</button>
        </div>
      </div>
      <div class="aside2">
        <p>评论区，待开发</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 主容器样式 */
.Mainbox {
  height: 930px;
  width: 1650px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: row;
  padding: 20px;
  gap: 30px;
  background-color: #f9f9f9; /* 提升整体背景舒适度 */
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}

/* 左侧内容区域 */
.body {
  flex: 2;
  background-color: #ffffff;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
}

/* 顶部区域样式 */
.head1 {
  height: 10%;
  display: flex;
  align-items: center;
  gap: 20px;
  background-color: #f1f5f9;
  border-radius: 10px;
  padding: 10px 20px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
}

.head1 button {
  padding: 12px 24px;
  font-size: 16px;
  color: #ffffff;
  background-color: #007bff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.head1 button:hover {
  background-color: #0056b3;
}

/* 中间内容和侧边栏容器 */
.content {
  display: flex;
  gap: 20px;
  flex: 1;
}

/* 图片区域 */
.body1 {
  flex: 2;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f9fafb;
  border-radius: 10px;
  font-size: 18px;
  color: #888;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

/* 书籍信息区域 */
.aside3 {
  flex: 1;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.aside3 h2 {
  font-size: 20px;
  color: #333333;
  margin-bottom: 15px;
}

.aside3 p {
  font-size: 16px;
  color: #555555;
  margin-bottom: 10px;
}

.aside3 p span {
  font-weight: bold;
  color: #000000;
}

/* 右侧功能区域 */
.aside {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* 购买信息容器 */
.aside1 {
  width: 100%;
  background-color: #f4f6f8;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
}

.aside11 {
  margin-bottom: 20px;
}

.aside11 h3 {
  font-size: 18px;
  color: #007bff;
  margin-bottom: 15px;
}

.aside11 p {
  margin: 5px 0;
  font-size: 16px;
  color: #333333;
}

.aside11 .price {
  font-weight: bold;
  color: #e63946;
}

/* 按钮组 */
.aside12 {
  display: flex;
  gap: 20px;
  justify-content: space-between;
}

.buy-btn,
.cart-btn {
  padding: 12px 24px;
  font-size: 14px;
  color: #ffffff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
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

/* 评论区域 */
.aside2 {
  width: 100%;
  height: auto;
  background-color: #f8f9fa;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  padding: 20px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  font-size: 16px;
  color: #666;
}
</style>
