<script lang="ts" setup>
import { Book } from './BaDataStruct/Book';
import { ref, computed } from 'vue';
import { ElButton } from 'element-plus'; // 引入 Element Plus 按钮组件
import 'element-plus/theme-chalk/el-button.css'; // 引入样式
import { useRouter } from 'vue-router';
const router=useRouter()

// 接收 props
const props = defineProps<{
  book: Book;
  userId: number;
}>();



// 按钮点击事件
const handleButtonClick = () => {
  router.push(
    {
      path:"/book",
      query:{
        bookId:props.book.Id,
        userId:props.userId
      }
    }
  )
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
  <div class="book-card">
    <!-- 书籍内容展示 -->
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
.book-card {
  width: 280px; /* 调整卡片宽度 */
  height: 350px; /* 调整卡片高度 */
  padding: 15px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 上下内容分布 */
  gap: 10px;
}

/* 书籍信息样式 */
.book-info h2 {
  font-size: 16px;
  color: #333;
  margin-bottom: 8px;
}

.book-info p {
  font-size: 14px;
  color: #555;
  margin: 0;
}

/* 按钮居中 */
.book-card button {
  align-self: center;
}
</style>