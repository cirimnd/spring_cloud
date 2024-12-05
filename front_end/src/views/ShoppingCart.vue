<script lang="ts" setup>
import { ref } from 'vue';
import { Order } from '@/components/BaDataStruct/orderAll';
import orderIncartsmall from '@/components/orderIncartsmall.vue';

// 创建测试订单数组
const orders = ref<Order[]>([
  new Order({ orderId: 1, userId: 101, bookId: 201, bookPrice: 120, state: 0, orderDatetime: new Date(), address: '天河', bookname: '三体' }),
  new Order({ orderId: 2, userId: 102, bookId: 202, bookPrice: 80, state: 0, orderDatetime: new Date(), address: '越秀', bookname: '围城' }),
  new Order({ orderId: 3, userId: 103, bookId: 203, bookPrice: 100, state: 0, orderDatetime: new Date(), address: '白云', bookname: '红楼梦' }),
]);

// 统一支付功能
const handlePayAll = () => {
  orders.value.forEach((order) => {
    order.state = 1; // 修改状态为已付款
  });
};
</script>

<template>
  <div class="Mainbox">
    <!-- 头部 -->
    <div class="head">
      <div class="mb-4">
        <el-button round>首页</el-button>
        <el-button round>个人</el-button>
      </div>
    </div>

    <!-- 中部展示订单 -->
    <div class="body">
      <div class="order-container">
        <orderIncartsmall
          v-for="order in orders"
          :key="order.orderId"
          :order="order"
        />
      </div>
    </div>

    <!-- 尾部 -->
    <div class="tail">
      <el-button round :disabled="orders.every(order => order.state === 1)" @click="handlePayAll">
        统一支付
      </el-button>
    </div>
  </div>
</template>

<style>
.Mainbox {
  height: 930px;
  width: 1650px;
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: aliceblue;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border-radius: 15px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.head {
  width: 100%;
  height: 5%;
  background-color: #e3f2fd;
  border-radius: 10px;
  display: flex;
  align-items: center;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.body {
  width: 100%;
  height: 80%;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  justify-content: flex-start; /* 子元素左对齐 */
  align-items: flex-start; /* 子元素顶部对齐 */
  flex-direction: column; /* 子元素从上到下排列 */
  text-align: left; /* 文字左对齐 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px; /* 添加内边距 */
  gap: 10px; /* 卡片之间的间距 */
  overflow-y: auto; /* 启用垂直滚动条 */
}

.order-container {
  width: 100%;
  display: flex;
  flex-direction: column; /* 垂直排列订单卡片 */
  gap: 10px;
}

.tail {
  width: 100%;
  height: 5%;
  background-color: #e3f2fd;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}


</style>
