<script lang="ts" setup>
import { Order } from './BaDataStruct/orderAll';
import { ref } from 'vue';
import { ElButton, ElCard } from 'element-plus'; // 引入 Element Plus 组件
import 'element-plus/theme-chalk/el-button.css'; // 按钮样式
import 'element-plus/theme-chalk/el-card.css'; // 卡片样式

// 示例订单对象
const props = defineProps<{
  order: Order;
}>();

// 按钮点击事件：修改订单状态

</script>

<template>
  <div class="Mainbox">
    <!-- 使用 Element Plus 卡片组件展示订单信息 -->
    <ElCard shadow="hover" class="order-card">
      <h3><strong>书名:</strong>{{ order.bookname }}</h3>
      <p><strong>价格:</strong> ${{ order.bookPrice }}</p>
      <p><strong>日期:</strong> {{ order.orderDatetime.toLocaleString() }}</p>
      <p><strong>地址:</strong> {{ order.address }}</p>
      
    </ElCard>
   
  </div>
</template>

<style lang="css" scoped>
.Mainbox {
  height: 250px; /* 固定高度，紧凑化 */
  width: 400px; /* 固定宽度，紧凑化 */
  overflow: hidden; /* 超出隐藏 */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
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
