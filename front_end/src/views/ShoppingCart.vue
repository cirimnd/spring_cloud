<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import orderIncartsmall from '@/components/orderIncartsmall.vue';
import { apiGetCartByUserId } from '@/apis/getCartByUserId';
import { useRoute, useRouter } from 'vue-router';
import { apiDelCartByCartId } from '@/apis/delCartByCartId';
import { apiAddOrder } from '@/apis/addOrder';
const route= useRoute()

const{userId,userName,password}=route.query
interface CartItem {
  cartId: number;
  userId: number;
  bookId: number;
  bookTitle: string;
  bookAuthor: string;
  quantity: number;
  totalPrice: number;
}

// 定义购物车列表
const cartItems = ref<CartItem[]>([]);

// 获取用户 ID（假设从路由参数获取）
 // 根据实际情况动态获取用户 ID

// 加载购物车数据
const loadCartData = async () => {
  try {
    const response = await apiGetCartByUserId(userId as any);
    if (response.code === 1) {
      cartItems.value = response.data;
    } else {
      ElMessage.error('获取购物车数据失败，请稍后再试！');
    }
  } catch (error) {
    console.error(error);
    ElMessage.error('网络异常，请检查您的网络连接！');
  }
};

// 页面加载时调用
onMounted(() => {
  loadCartData();
});

// 统一支付功能
const handlePayAll = () => {
  // ElMessage.success('支付成功！');
  // cartItems.value = cartItems.value.map((item) => ({ ...item, paid: true })); // 假设增加了支付状态
  for (let i = cartItems.value.length - 1; i >= 0; i--) {
    updateCartStatus(cartItems.value[i].cartId);
  }
};

const updateCartStatus = async (cartId: number) => {
  // 根据 cartId 处理订单状态更新
  const cart = cartItems.value.find((item) => item.cartId === cartId);
  if (cart) {
    // 处理结算逻辑，如更新状态或发起支付请求
    const idx = cartItems.value.findIndex((item) => item.cartId === cartId)
    cartItems.value.splice(idx,1)
    let res = await apiDelCartByCartId(cartId);
    let param={
      userId:cart.userId,
      bookId:cart.bookId,
      bookPrice:cart.totalPrice,
      state:1,
      address:"待处理"
    }
    if(res.code==1) { 
      let orderRes = await apiAddOrder(param)
      if(orderRes.code==1) ElMessage.success(`结算成功！订单 ID: ${cartId}`)
      else {ElMessage.error("结算失败！")}
    }
    else {ElMessage.error("结算失败！")}
  }
};
</script>

<template>
  <div class="Mainbox">
    <!-- 头部 -->
    <div class="head">
      <div class="mb-4">
        <el-button round>首页</el-button>
        <el-button round>个人</el-button>
      </div>
    </div>

    <!-- 中部展示购物车 -->
    <div class="body">
      <div class="order-container">
        <orderIncartsmall
          v-for="item in cartItems"
          :key="item.cartId"
          :cartItem="item"
          @updateStatus="updateCartStatus"
        />
      </div>
    </div>

    <!-- 尾部 -->
    <div class="tail">
      <el-button round :disabled="!cartItems.length" @click="handlePayAll">
        统一支付
      </el-button>
    </div>
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

.body {
  width: 100%;
  height: 80%;
  background-color: #ffffff;
  border-radius: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  flex-direction: column;
  text-align: left;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
  gap: 10px;
  overflow-y: auto;
}

.order-container {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr)); /* 每列最小400px，自动填充 */
  gap: 20px; /* 卡片之间的间距 */
}

.tail {
  width: 100%;
  height: 5%;
  background-color: #e3f2fd;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>