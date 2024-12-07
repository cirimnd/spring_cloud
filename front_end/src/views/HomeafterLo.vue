HomeafterLo.vue:<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import BookSimple from '@/components/BookSimple.vue';
import { Book } from '@/components/BaDataStruct/Book';
import { apiGetBooksByName } from '@/apis/getBooksByName'; // 书名查询 API
import { apiGetBookByIsbn } from '@/apis/getBookByIsbn'; // ISBN查询 API
import { apiGetBookByAuthor } from '@/apis/getBookByAuthor'; // 作者查询 API
import { useRoute, useRouter } from 'vue-router';
import { apiGetUserByName } from '@/apis/getUserByName';
import { apiGetBooksByCate } from '@/apis/getBooksByCate'; // 新增分类查询 API

const route = useRoute();
const router = useRouter();

let { userName, password } = route.params;
let userId = ref(0);

// 动态加载书籍数据
const books = ref<Book[]>([]);

async function loadBooks() {
  try {
    const res = await apiGetBooksByName('');  // 初始化时可以加载所有书籍（或者传入空字符串）
    if (res.code === 1) {
      books.value = res.data.map((book: any) => new Book(book)); // 将接口数据转换为 Book 实例
    }
  } catch (error) {
    console.error('加载书籍数据失败：', error);
  }
}
async function getId() {
  const res = await apiGetUserByName(userName as string);
  if (res.code === 1) {
    const { userid } = res.data;
    userId.value = userid;
  }
}

// 在组件挂载时加载书籍
onMounted(async() => {
  await loadBooks();
  await getId();
});

// 筛选选择值
const checked1 = ref(false);
const checked2 = ref(false);
const checked3 = ref(false);
const checked4 = ref(false);

const value = ref('');
const options = [
  { value: '作者', label: '作者' },
  { value: '书名', label: '书名' },
  { value: 'ISBN编号', label: 'ISBN编号' },
];

const input1 = ref('');

// 查询书籍
const searchBooks = async () => {
  if (value.value === '书名') {
    const bookName = input1.value.trim();
    if (bookName) {
      const res = await apiGetBooksByName(bookName);
      if (res.code === 1) {
        books.value = res.data.map((book: any) => new Book(book)); // 更新书籍列表
      }
    }
  } else if (value.value === 'ISBN编号') {
    const bookIsbn = input1.value.trim();
    if (bookIsbn) {
      const res = await apiGetBookByIsbn(bookIsbn);
      if (res.code === 1) {
        books.value = [new Book(res.data)]; // 更新书籍列表为单本书籍
      }
    }
  } else if (value.value === '作者') {
    const authorName = input1.value.trim();
    if (authorName) {
      const res = await apiGetBookByAuthor(authorName);
      if (res.code === 1) {
        books.value = res.data.map((book: any) => new Book(book)); // 更新书籍列表
      }
    }
  }
}

// 跳转到个人中心
function toPerson() {
  router.push({
    path: '/personal',
    query: { userName: userName, password: password },
  });
}

// 跳转到购物车
function toCart() {
  router.push({
    path: '/cart',
    query: { userName: userName, password: password, userId: userId.value },
  });
}

// 分类筛选
const categories = ref({
  '编程技术': false,
  '科幻': false,
  '经典文学': false,
  '当代文学': false,
});

const filterBooksByCategory = async () => {
  // 获取选中的分类
  const selectedCategories = Object.keys(categories.value).filter(
    (category) => categories.value[category]
  );

  if (selectedCategories.length > 0) {
    // 调用 API，并将选中的类别作为参数
    const res = await apiGetBooksByCate(selectedCategories.join(','));
    if (res.code === 1) {
      books.value = res.data.map((book: any) => new Book(book)); // 更新书籍列表
    } else {
      console.error('分类筛选失败：', res.data);
    }
  } else {
    // 如果没有选择任何分类，恢复显示所有书籍
    await loadBooks();
  }
};
</script>

<template>
  <div class="Mainbox">
    <div class="head">
      <div class="head1">
        <el-text class="text">Bookmarket</el-text>
        <el-button class="button" type="primary">首页</el-button>
      </div>
      <div class="head2">
        <div class="head21">
          <div class="flex flex-wrap gap-4 items-center">
            <el-select
              v-model="value"
              placeholder="Select"
              size="large"
              style="width: 240px"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </div>
          <el-input v-model="input1" style="width: 240px" size="large" />
          <el-button size="large" @click="searchBooks">
            <el-icon><Search /></el-icon>Search
          </el-button>
        </div>
        <div class="head22">
          <el-button round size="large" @click="toCart">购物车</el-button>
          <el-button round size="large" @click="toPerson">个人</el-button>
        </div>
      </div>
    </div>

    <div class="content">
      <div class="body">
        <div class="book-container">
          <BookSimple
            v-for="book in books"
            :key="book.Id"
            :book="book"
            :userId="userId"
          />
        </div>
      </div>
      <div class="aside">
        <el-text class="text">分类</el-text>
        <div class="checkbox-group">
          <el-checkbox v-model="categories['编程技术']" label="编程技术" border />
          <el-checkbox v-model="categories['科幻']" label="科幻" border />
          <el-checkbox v-model="categories['经典文学']" label="经典文学" border />
          <el-checkbox v-model="categories['当代文学']" label="当代文学" border />
        </div>
        <el-button @click="filterBooksByCategory">筛选</el-button>
      </div>
    </div>
  </div>
</template>



<style lang="css" scoped>
/* 主容器 Mainbox */
.Mainbox {
  height: 930px;
  width: 100%; /* 设置为100%宽度，避免容器宽度固定 */
  max-width: 1600px; /* 设置最大宽度，避免在大屏幕上过宽 */
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: aliceblue;
  background-size: cover; /* 背景图片覆盖容器 */
  background-position: center; /* 背景图片居中 */
  background-repeat: no-repeat; /* 背景图片不重复 */
  border-radius: 15px;
  padding: 20px; /* 增加内边距 */
  display: flex;
  flex-direction: column; /* 垂直排列 */
  gap: 20px; /* 各区块间距 */
  box-sizing: border-box; /* 确保padding不会影响容器宽度 */
}

/* 头部区域 head */
.head {
  width: 100%; /* 占据整个宽度 */
  height: 15%; /* 占据容器高度的15% */
  background-color: #e3f2fd; /* 浅蓝背景色 */
  border-radius: 10px; /* 圆角 */
  display: flex; /* Flex 布局 */
  flex-direction: row; /* 使子元素水平排列 */
  justify-content: center; /* 内容水平居中 */
  align-items: center; /* 内容垂直居中 */
  text-align: center; /* 文本居中 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

.head1 {
  width: 20%;
  height: 100%;
  background-color: rgb(246, 243, 238);
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  gap: 20px;
}

.text {
  font-size: 24px;
  font-weight: bold;
  color: #2c3e50;
}

/* 按钮样式 */
.button {
  font-size: 16px;
  padding: 5px 15px;
  border-radius: 5px;
  background-color: #3498db;
  color: white;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease;
}

.button:hover {
  background-color: #2980b9;
  cursor: pointer;
}

.head2 {
  width: 80%;
  height: 100%;
  background-color: rgb(251, 250, 251);
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.head21 {
  flex: 3;
  height: 100%;
  background-color: #f0f8ff;
  border-radius: 8px;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.head22 {
  flex: 1;
  height: 100%;
  background-color: #e8f5e9;
  border-radius: 8px;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  text-align: center;
}

/* 主体和侧边栏父容器 content */
.content {
  display: flex;
  flex: 1;
  width: 100%;
  gap: 20px;
  flex-wrap: wrap;
}

/* 主体区域 body */
.body {
  flex: 3;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  /* justify-content: center; */
  /* align-items: center; */
  flex-direction: column;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

/* 侧边栏区域 aside */
.aside {
  flex: 1;
  background-color: #c8e6c9;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  gap: 20px;
  padding: 20px;
}

/* 多选框组 checkbox-group */
.checkbox-group {
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 80%;
}

.book-container {
  display: flex;
  flex-wrap: wrap;  /* 保证书籍能换行 */
  gap: 20px;        /* 每本书之间的间距 */
}

.book-container .book {
  flex: 1 1 calc(25% - 20px); /* 4列布局，每列宽度为25%减去间距 */
  box-sizing: border-box; /* 确保宽度计算时包含间距 */
}

</style>