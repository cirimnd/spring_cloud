<script lang="ts" setup>
import { onBeforeMount, ref } from 'vue';
import { User, Role } from '@/components/BaDataStruct/Usrs';
import { ElMessage } from 'element-plus';
import { Order } from '@/components/BaDataStruct/orderAll';
import ordersmall from '@/components/ordersmall.vue';
import { useRouter,useRoute } from 'vue-router';
import { apiGetUserByName } from '@/apis/getUserByName';
import { apiUpdateUser } from '@/apis/updateUser';
const router = useRouter()
const route = useRoute()

const{userName,password}=route.query
const exampleUser = ref(
  new User({
    userid: 10,
    userName: userName as string,
    password: password as string,
    phone: '18277098765',
    role: Role.USER,
  })
);
onBeforeMount(()=>{
  async function main() {
    let res=await apiGetUserByName(userName as string)
    if(res.code==1){
      const {userid,userName,password,phone,role} = res.data
      exampleUser.value.setPhone(phone)
      exampleUser.value.setUserid(userid)
    }
    else alert("请联系管理员！");
  }
  main()
})

// 将 exampleUser 包装为 ref 类型

const orders = ref<Order[]>([
  new Order({ orderId: 1, userId: 101, bookId: 201, bookPrice: 120, state: 0, orderDatetime: new Date(), address: '天河', bookname: '三体' }),
  new Order({ orderId: 2, userId: 102, bookId: 202, bookPrice: 80, state: 0, orderDatetime: new Date(), address: '越秀', bookname: '围城' }),
  new Order({ orderId: 3, userId: 103, bookId: 203, bookPrice: 100, state: 0, orderDatetime: new Date(), address: '白云', bookname: '红楼梦' }),
]);

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
</script>

<template>
  <div class="Mainbox">
    <div class="head">
      <div class="mb-4">
        <el-button round @click="back">首页</el-button>
        <el-button round>购物车</el-button>
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
        <span>订单</span>
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
.Mainbox {
  height: 930px;
  width: 1650px;
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: aliceblue;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border-radius: 15px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.head {
  width: 100%;
  height: 5%;
  background-color: #e3f2fd;
  border-radius: 10px;
  display: flex;
  align-items: center;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.content {
  display: flex;
  flex: 1;
  gap: 20px;
}

.aside {
  flex: 1;
  height: 40%;
  background-color: #c8e6c9;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
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
}

.info-label {
  font-weight: bold;
  font-size: 16px;
  color: #4caf50;
  margin-right: 10px;
}

.info-value {
  font-size: 16px;
  color: #555555;
}

.btn-container {
  display: flex;
  justify-content: center; /* 按钮居中 */
  width: 100%;
}

.body {
  flex: 3;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
