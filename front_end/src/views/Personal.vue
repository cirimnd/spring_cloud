<script lang="ts" setup>
import { onBeforeMount, ref } from 'vue';
import { User, Role } from '@/components/BaDataStruct/Usrs';
import { ElMessage } from 'element-plus';
import { Order } from '@/components/BaDataStruct/orderAll';
import ordersmall from '@/components/ordersmall.vue';
import { useRouter,useRoute } from 'vue-router';
import { apiGetUserByName } from '@/apis/getUserByName';
import { apiUpdateUser } from '@/apis/updateUser';
import { apiGetOrderByUserId } from '@/apis/getOrderByUserId';
const router = useRouter()
const route = useRoute()

const{userName,password}=route.query
let userId = 0
const exampleUser = ref(
  new User({
    userid: 10,
    userName: userName as string,
    password: password as string,
    phone: '18277098765',
    role: Role.USER,
  })
);

const orders = ref<Order[]>([
  // new Order({ orderId: 1, userId: 101, bookId: 201, bookPrice: 120, state: 0, orderDatetime: new Date(), address: '天河', bookname: '三体' }),
  // new Order({ orderId: 2, userId: 102, bookId: 202, bookPrice: 80, state: 0, orderDatetime: new Date(), address: '越秀', bookname: '围城' }),
  // new Order({ orderId: 3, userId: 103, bookId: 203, bookPrice: 100, state: 0, orderDatetime: new Date(), address: '白云', bookname: '红楼梦' }),
]);
onBeforeMount(() => {
  async function main() {
    try {
      // 获取用户信息
      const res = await apiGetUserByName(userName as string);
      if (res.code === 1) {
        const { userid, userName, phone } = res.data;
        userId=userid
        exampleUser.value.setPhone(phone);
        exampleUser.value.setUserid(userid);

        // 获取订单信息
        const orderRes = await apiGetOrderByUserId(userid);
        if (orderRes.code === 1) {
          // 映射接口返回的订单数据到 Order 类型
          orders.value = orderRes.data.map((order: any) => 
            new Order({
              orderId: order.orderId,
              userId: userid,
              bookId: 0, // 如果需要书籍 ID，请补充到后端接口中
              bookPrice: order.bookPrice,
              state: order.orderState,
              orderDatetime: new Date(order.orderDatetime),
              address: order.address,
              bookname: order.bookTitle,
            })
          );
        } else {
          ElMessage.error("加载订单失败，请稍后重试！");
        }
      } else {
        alert("用户信息加载失败，请联系管理员！");
      }
    } catch (error) {
      console.error(error);
      ElMessage.error("系统错误，请稍后重试！");
    }
  }
  main();
});
const props = defineProps<{
 
  

}>();


// 弹窗相关状态
const isDialogVisible = ref(false); // 弹窗显示状态
const tempUser = ref(new User()); // 临时用户信息（用于表单）

// 修改个人信息按钮事件
const openEditDialog = () => {
  // 同步当前信息到临时用户
  tempUser.value = { ...exampleUser.value };
  isDialogVisible.value = true; // 显示弹窗
};

// 确认修改事件
const confirmEdit = async () => {
  // 更新用户信息
  let flg=0
  if(exampleUser.value.userName!=tempUser.value.userName||
  exampleUser.value.password!=tempUser.value.password){
    flg=1
  }
  exampleUser.value = { ...tempUser.value };
  let param = {
    userid:tempUser.value.userid,
    userName:tempUser.value.userName,
    password:tempUser.value.password,
    phone:tempUser.value.phone
  }
  let res = await apiUpdateUser(param)
  if(res.code==1&&flg==0){
    isDialogVisible.value = false; // 关闭弹窗
    ElMessage.success('个人信息更新成功');
  }
  else if(res.code==1&&flg==1){
    ElMessage.success('个人信息更新成功,请重新登录！');
    router.push("/login");
  }
  else ElMessage.error("更新失败！");
  
};

// 取消修改事件
const cancelEdit = () => {
  isDialogVisible.value = false; // 关闭弹窗
};

function back()
{
  router.go(-1)
}
function toCart()
{
  router.push({
    path:"/cart",
    query:{
      userName:userName,
      password:password,
      userId:userId
    }
  })
}
</script>

<template>
  <div class="Mainbox">
    <div class="head">
      <div class="mb-4">
        <el-button round @click="back">首页</el-button>
        <el-button round @click="toCart">购物车</el-button>
      </div>
    </div>

    <div class="content">
      <div class="aside">
        <el-avatar size="100px" class="avatar"> 用户 </el-avatar>
        <div class="user-info">
          <div class="info-item">
            <span class="info-label">用户名：</span>
            <span class="info-value">{{ exampleUser.userName }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">手机号：</span>
            <span class="info-value">{{ exampleUser.phone }}</span>
          </div>
        </div>
        <div class="btn-container">
          <el-button round @click="openEditDialog">修改个人信息</el-button>
        </div>
      </div>
            <div class="body">
        <div class="order-header">
          <h2 class="order-title">
            <i class="el-icon-document"></i> 订单列表
          </h2>
          <hr class="header-divider" />
        </div>
        <div class="order-container">
          <ordersmall
            v-for="order in orders"
            :key="order.orderId"
            :order="order"
          />
        </div>
      </div>
    </div>
    

    <!-- 修改个人信息弹窗 -->
    <el-dialog v-model="isDialogVisible" title="修改个人信息" width="30%">
      <el-form :model="tempUser" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="tempUser.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="tempUser.password" placeholder="请输入密码" type="password" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="tempUser.phone" placeholder="请输入手机号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelEdit">取消</el-button>
        <el-button type="primary" @click="confirmEdit">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style>
/* 样式保持不变 */
/* 主容器样式 */
.Mainbox {
  height: 95vh; /* 页面高度占满视口 */
  width: 90vw; /* 页面宽度为视口宽度的90% */
  max-width: 1200px; /* 限制最大宽度，保持统一外观 */
  margin: 0 auto; /* 居中对齐 */
  overflow: auto;
  background-color: #f5f5f5;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* 添加阴影效果 */
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* 顶部区域样式 */
.head {
  width: 100%;
  height: 60px; /* 适中高度 */
  background-color: #e3f2fd;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: space-between; /* 按钮分散排列 */
  padding: 0 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.head .el-button {
  font-size: 14px;
  padding: 6px 12px;
}

/* 主内容区域 */
.content {
  display: flex;
  flex: 1;
  gap: 20px;
  align-items: flex-start; /* 子内容顶部对齐 */
}

/* 左侧侧边栏 */
.aside {
  flex: 0 0 250px; /* 固定宽度为250px */
  background-color: #c8e6c9;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.avatar {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  background-color: #ffccbc;
  color: #ffffff;
  border-radius: 50%;
}

.user-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.info-item {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 标签和值分布排列 */
}

.info-label {
  font-weight: bold;
  font-size: 14px;
  color: #4caf50;
}

.info-value {
  font-size: 14px;
  color: #555555;
}

/* 主体订单列表 */
.body {
  flex: 1;
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 标题容器样式 */
.order-header {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 标题左对齐 */
  gap: 10px;
  padding: 10px 20px;
  background-color: #f0f4f8; /* 标题背景色 */
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

/* 标题文字样式 */
.order-title {
  font-size: 26px;
  font-weight: bold;
  color: #333333;
  margin: 0;
  display: flex;
  align-items: center;
  gap: 10px;
}

/* 图标样式 */
.order-title i {
  font-size: 24px;
  color: #4caf50; /* 使用绿色与标题呼应 */
}

/* 分割线样式 */
.header-divider {
  width: 100%;
  height: 2px;
  background: linear-gradient(to right, #4caf50, #81c784); /* 渐变分割线 */
  border: none;
}

/* 订单网格容器样式 */
.order-container {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* 自适应网格布局 */
  gap: 16px; /* 卡片之间的间距 */
  padding-top: 10px; /* 上方增加一点间距 */
}

/* 弹窗按钮居中对齐 */
.btn-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

/* 订单卡片 */
.order-card {
  position: relative;
  padding: 16px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  transition: transform 0.2s, box-shadow 0.2s;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.order-card:hover {
  transform: translateY(-5px); /* 鼠标悬停时抬起 */
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.order-card h3 {
  font-size: 16px;
  color: #333;
  margin-bottom: 8px;
}

.order-card p {
  margin: 0;
  color: #666;
  font-size: 14px;
  line-height: 1.6;
}

.order-card strong {
  color: #4caf50; /* 突出关键字段 */
}

</style>
