<script lang="ts" setup>
import { Book } from './BaDataStruct/Book';
import { ref, computed } from 'vue';
import { ElButton } from 'element-plus'; // 引入 Element Plus 按钮组件
import 'element-plus/theme-chalk/el-button.css'; // 引入样式

// 接收 props
const props = defineProps<{
  book: Book;
}>();



// 按钮点击事件
const handleButtonClick = () => {
  
};

// 书籍描述截断逻辑
const truncatedDescription = computed(() => {
  const maxLength = 100; // 限制字数
  return props.book.description.length > maxLength
    ? props.book.description.slice(0, maxLength) + '...'
    : props.book.description;
});
</script>

<template>
  <div class="Mainbox">
    <!-- 展示书籍信息 -->
    <div class="book-info">
      <h2>{{ book.title }}</h2>
      <p><strong>Author:</strong> {{ book.author }}</p>
      <p><strong>Price:</strong> ${{ book.price }}</p>
      <p><strong>ISBN:</strong> {{ book.isbn }}</p>
      <p><strong>Category:</strong> {{ book.category }}</p>
      <p><strong>Stock:</strong> {{ book.stock }} available</p>
      <p><strong>Description:</strong> {{ truncatedDescription }}</p>
    </div>

    <!-- 按钮 -->
    <ElButton type="primary" @click="handleButtonClick">更多</ElButton>
  </div>
</template>

<style lang="css" scoped>
.Mainbox {
  height: 300px; /* 高度固定 */
  width: 700px; /* 宽度增加并固定 */
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #f9f9f9;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border-radius: 15px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column; /* 主轴改为列方向 */
  gap: 20px; /* 各区块间距 */
}

.book-info {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.book-info h2 {
  margin: 0;
  color: #333;
}

.book-info p {
  margin: 0;
  color: #555;
}

button {
  align-self: center;
}
</style>
