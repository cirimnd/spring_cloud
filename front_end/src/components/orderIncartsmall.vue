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
  height: auto; /* Allow content to expand naturally */
  width: 100%; /* Ensure the width takes up full available space */
  max-width: 400px; /* Limit the maximum width */
  background-color: #f9f9f9;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 15px; /* Increase spacing between elements */
}

.order-card {
  flex: 1;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Add subtle shadow for depth */
}

h3 {
  margin: 0 0 8px; /* Adjust margin */
  color: #333;
  font-size: 18px; /* Increase font size for better visibility */
  font-weight: bold;
}

p {
  margin: 5px 0;
  color: #555;
  font-size: 14px;
}

.ElButton {
  align-self: center;
  padding: 8px 16px; /* Add padding to make the button look more prominent */
  font-size: 16px;
  border-radius: 5px;
  background-color: #409EFF; /* Customize button color */
  border-color: #409EFF;
}

.ElButton:disabled {
  background-color: #f0f0f0; /* Modify disabled state */
  color: #aaa;
  border-color: #ddd;
}

</style>
