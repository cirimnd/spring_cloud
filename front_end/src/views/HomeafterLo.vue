HomeafterLo.vue:<script lang="ts" setup>
import { ref } from 'vue';
import BookSimple from '@/components/BookSimple.vue';
import { Book } from '@/components/BaDataStruct/Book';
import BookSimpleNL from '@/components/BookSimpleNL.vue';
import { Order } from '@/components/BaDataStruct/orderAll';

// 模拟 books 数据
const books = ref<Book[]>([
  new Book({
    title: 'The Three-Body Problem',
    author: 'Cixin Liu',
    price: 45.0,
    isbn: '9780765382030',
    category: 'Science Fiction',
    stock: 20,
    description: 'A hard science fiction novel that begins humanity’s first contact with an alien civilization.',
    Id: 1,
  }),
  new Book({
    title: 'Pride and Prejudice',
    author: 'Jane Austen',
    price: 30.0,
    isbn: '9780199535569',
    category: 'Classic Literature',
    stock: 12,
    description: 'A timeless romance novel about Elizabeth Bennet and Mr. Darcy.',
    Id: 2,
  }),
  new Book({
    title: 'Sapiens: A Brief History of Humankind',
    author: 'Yuval Noah Harari',
    price: 50.0,
    isbn: '9780062316097',
    category: 'History',
    stock: 25,
    description: 'A groundbreaking narrative of humanity’s creation and evolution.',
    Id: 3,
  }),
  new Book({
    title: 'Clean Code',
    author: 'Robert C. Martin',
    price: 60.0,
    isbn: '9780132350884',
    category: 'Programming',
    stock: 8,
    description: 'A handbook of agile software craftsmanship.',
    Id: 4,
  }),
  new Book({
    title: 'To Kill a Mockingbird',
    author: 'Harper Lee',
    price: 40.0,
    isbn: '9780061120084',
    category: 'Classic Literature',
    stock: 15,
    description: 'A classic novel of racism and injustice in the American South.',
    Id: 5,
  }),
]);

//筛选选择值
const checked1 = ref(false);
const checked2 = ref(false);
const checked3 = ref(false);
const checked4 = ref(false);


const value = ref('')
const options = [
  {
    value: '作者',
    label: '作者',
  },
  {
    value: '书名',
    label: '书名',
  },
  {
    value: 'ISBN编号',
    label: 'ISBN编号',
  },
  
]


//
const input1 = ref('');


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
          <el-button size="large">
            <el-icon><Search /></el-icon>Search
          </el-button>
        </div>
        <div class="head22">
          <el-button round size="large">购物车</el-button>
          <el-button round size="large">个人</el-button>
        </div>
      </div>
    </div>

    <div class="content">
      <div class="body">
       <div class="book-container">
        <!-- <BookSimple
         v-for="book in books"
         :key="book.Id"
         :book="book"
         /> -->

       </div>
        
       
       
      </div>
      <div class="aside">
        <el-text class="text">分类</el-text>
        <div class="checkbox-group">
          <el-checkbox v-model="checked1" label="自然科学" border />
          <el-checkbox v-model="checked2" label="社会科学" border />
          <el-checkbox v-model="checked3" label="艺术" border />
          <el-checkbox v-model="checked4" label="文学" border />
        </div>
      </div>
    </div>
  </div>
</template>


<style lang="css" scoped>
/* 主容器 Mainbox */
.Mainbox {
  height: 930px;
  width: 1650px;
  max-width: 100%; /* 容器宽度自适应 */
  max-height: 100%; /* 容器高度自适应 */
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
   float: left;
   overflow: auto; /* 自动显示滚动条 */
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
  width: 100%;
  display: flex;
  flex-direction: column; /* 垂直排列订单卡片 */
  gap: 10px;
}

</style>