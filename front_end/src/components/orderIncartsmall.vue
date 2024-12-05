<script lang="ts" setup>
import { defineProps } from 'vue';
import { Order } from './BaDataStruct/orderAll';
import { ref } from 'vue';
import { ElButton, ElCard } from 'element-plus';
import 'element-plus/theme-chalk/el-button.css';
import 'element-plus/theme-chalk/el-card.css';

// 接收父组件传递的订单数据
const props = defineProps<{
  order: Order;
}>();

// 按钮点击事件：修改订单状态
const handleButtonClick = () => {
  props.order.state = 1; // 修改状态为已付款
};
</script>

<template>
  <div class="Mainbox">
    <ElCard shadow="hover" class="order-card">
      <h3>{{ order.bookname }}</h3>
      <p><strong>Price:</strong> ${{ order.bookPrice }}</p>
      <p><strong>Order Date:</strong> {{ order.orderDatetime.toLocaleString() }}</p>
      <p><strong>Address:</strong> {{ order.address }}</p>
      <p>
        <strong>Status:</strong>
        <span v-if="order.state === 0" style="color: red;">未付款</span>
        <span v-else style="color: green;">已付款</span>
      </p>
    </ElCard>
    <ElButton
      type="primary"
      :disabled="order.state === 1"
      @click="handleButtonClick"
    >
      {{ order.state === 0 ? '付款' : '已付款' }}
    </ElButton>
  </div>
</template>

<style lang="css" scoped>
.Mainbox {
  height: 250px;
  width: 400px;
  overflow: hidden;
  position: relative;
  background-color: #f9f9f9;
  border-radius: 10px;
  padding: 15px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.order-card {
  flex: 1;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: white;
}

h3 {
  margin: 0 0 5px;
  color: #333;
  font-size: 16px;
}

p {
  margin: 0;
  color: #555;
  font-size: 14px;
}

button {
  align-self: center;
}
</style>
