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
  router.push({
      name:"home",
      params:{
        userName:userName as string,
        password:password as string,
      }
    })
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

<style scoped>
/* 主容器样式 */
.Mainbox {
  height: 930px;
  width: 1650px;
  max-width: 1600px;
  margin: 20px auto;
  background-color: #ffffff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 20px;
  overflow: hidden;
}

/* 顶部导航栏 */
.head {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  background-color: #f7f9fc;
  padding: 10px 15px;
  border-radius: 8px;
  gap: 10px;
}

.head .el-button {
  font-size: 14px;
  padding: 8px 14px;
  color: #4caf50;
  border-color: #4caf50;
}

.head .el-button:hover {
  background-color: #4caf50;
  color: #ffffff;
}

/* 主内容区域 */
.content {
  display: flex;
  gap: 25px;
  flex: 1;
  overflow: hidden;
}

/* 侧边栏 */
.aside {
  flex: 0 0 250px;
  background-color: #f9f9f9;
  border-radius: 10px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.avatar {
  width: 90px;
  height: 90px;
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #4caf50;
  color: #ffffff;
  border-radius: 50%;
}

.user-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.info-item {
  display: flex;
  justify-content: space-between;
  font-size: 15px;
}

.info-label {
  font-weight: bold;
  color: #333333;
}

.info-value {
  color: #555555;
}

.btn-container {
  width: 100%;
  display: flex;
  justify-content: center;
}

.btn-container .el-button {
  font-size: 15px;
  padding: 8px 15px;
  background-color: #4caf50;
  color: #ffffff;
  border-radius: 6px;
}

.btn-container .el-button:hover {
  background-color: #388e3c;
}

/* 订单展示区域 */
.body {
  flex: 1;
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  display: flex;
  flex-direction: column; 
  gap: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
}

.order-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.order-title {
  font-size: 20px;
  color: #4caf50;
  display: flex;
  align-items: center;
  gap: 10px;
}

.order-title i {
  font-size: 24px;
}

.header-divider {
  margin-top: 5px;
  height: 2px;
  background-color: #e0e0e0;
  border: none;
}

.order-container {
  display: grid;
  width: 320px;
  gap: 10px;
}

.order-card {
  padding: 15px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 12px;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.order-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.order-card h3 {
  font-size: 18px;
  color: #333333;
  margin: 0;
}

.order-card p {
  font-size: 15px;
  color: #666666;
  margin: 0;
}

.order-card strong {
  color: #4caf50;
}

/* 弹窗 */
.el-dialog {
  border-radius: 12px;
  max-width: 400px;
}

.dialog-footer {
  text-align: right;
  padding: 10px 15px;
}

.el-button {
  border-radius: 6px;
  font-size: 15px;
}

/* 响应式布局 */
@media (max-width: 768px) {
  .Mainbox {
    width: 100%;
  }

  .content {
    flex-direction: column;
  }

  .aside {
    width: 100%;
    margin-bottom: 20px;
  }

  .body {
    width: 100%;
  }
}

</style>
