<script lang="ts" setup>
import { Book } from './BaDataStruct/Book';
import { ref, computed } from 'vue';
import 'element-plus/theme-chalk/el-button.css'; // 引入样式

//创建 Book 对象
const myBook = ref(
  new Book({
    title: 'The Great Gatsby',
    author: 'F. Scott Fitzgerald',
    price: 19.99,
    isbn: '1911245',
    category: 'Fiction',
    stock: 20,
    description: 'A classic novel of the Jazz Age. Explore the complexities of love, wealth, and ambition in this timeless tale.',
  })
);




// const props = defineProps<{
//   book: Book;
// }>();



// 书籍描述截断逻辑
const truncatedDescription = computed(() => {
  const maxLength = 100; // 限制字数
  return myBook.value.description.length > maxLength
    ? myBook.value.description.slice(0, maxLength) + '...'
    : myBook.value.description;
});
</script>

<template>
  <div class="Mainbox">
    <!-- 展示书籍信息 -->
    <div class="book-info">
      <h2>{{ myBook.title }}</h2>
      <p><strong>Author:</strong> {{ myBook.author }}</p>
      <p><strong>Price:</strong> ${{ myBook.price }}</p>
      <p><strong>ISBN:</strong> {{ myBook.isbn }}</p>
      <p><strong>Category:</strong> {{ myBook.category }}</p>
      <p><strong>Stock:</strong> {{ myBook.stock }} available</p>
      <p><strong>Description:</strong> {{ truncatedDescription }}</p>
    </div>
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
