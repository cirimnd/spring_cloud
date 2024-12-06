<script lang="ts" setup>
import { defineProps, defineEmits } from 'vue';
import { ElButton, ElCard } from 'element-plus';
import 'element-plus/theme-chalk/el-button.css';
import 'element-plus/theme-chalk/el-card.css';

// 接收父组件传递的订单数据
const props = defineProps<{
  cartItem: {
    cartId: number;
    userId: number;
    bookId: number;
    bookTitle: string;
    bookAuthor: string;
    quantity: number;
    totalPrice: number;
  };
}>();

// 定义事件，用于通知父组件更新状态
const emit = defineEmits(['updateStatus']);

// 按钮点击事件：通知父组件更新订单状态
const handleButtonClick = () => {
  emit('updateStatus', props.cartItem.cartId); // 向父组件发送 cartId
};
</script>

<template>
  <div class="Mainbox">
    <ElCard shadow="hover" class="order-card">
      <h3>{{ cartItem.bookTitle }}</h3>
      <p><strong>Author:</strong> {{ cartItem.bookAuthor }}</p>
      <p><strong>Quantity:</strong> {{ cartItem.quantity }}</p>
      <p><strong>Total Price:</strong> ¥{{ cartItem.totalPrice }}</p>
    </ElCard>
    <ElButton
      type="primary"
      :disabled="false"
      @click="handleButtonClick"
    >
      结算
    </ElButton>
  </div>
</template>

<style lang="css" scoped>
.Mainbox {
  height: 250px;
  width: 100%; /* 让子组件宽度自适应父组件 */
  max-width: 400px; /* 限制最大宽度以保持一致 */
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
