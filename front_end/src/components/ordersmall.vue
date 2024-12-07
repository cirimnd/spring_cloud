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
  height: auto;
  width: 300px; /* 自适应父容器宽度 */
  max-height: 250px;
  height: auto; /* 根据内容调整高度 */
  padding: 16px;
  border-radius: 12px;
  background-color: #ffffff; /* 背景为白色 */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 适中的阴影 */
  display: flex;
  flex-direction: column;
  gap: 6px; /* 控制元素间距 */
  margin: 10px auto; /* 居中 */
}

.order-card {
  padding: 16px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 添加柔和阴影 */
  transition: transform 0.3s, box-shadow 0.3s;
  background-color: #f9f9f9;
}

.order-card:hover {
  transform: translateY(-4px); /* 悬停时略微上移 */
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2); /* 增强阴影效果 */
}

h3, p {
  margin: 0 0 8px;
  color: #333;
  font-size: 16px;
}

p strong {
  color: #4caf50; /* 绿色突出关键字段 */
}

button {
  align-self: center; /* 按钮居中 */
  margin-top: 12px;
}
</style>