<script lang="ts" setup>
import { ref } from 'vue';
import loginsmall from './loginsmall.vue'; // 登录组件
import setsmall from './setsmall.vue';     // 注册组件

// 当前选中的选项
const value = ref('登录'); // 默认选中登录
const options = ['登录', '注册']; // 选项

// 根据选项值动态切换组件
const currentComponent = ref<typeof loginsmall | typeof setsmall>(loginsmall);

// 处理切换逻辑
const handleSegmentedChange = (val: string) => {
  if (val === '登录') {
    currentComponent.value = loginsmall;
  } else if (val === '注册') {
    currentComponent.value = setsmall;
  }
};
</script>

<template>
  <div class="L1">
    <!-- 切换按钮 -->
    <div class="head">
      <el-segmented
        v-model="value"
        :options="options"
        size="large"
        @change="handleSegmentedChange"
      />
    </div>

    <!-- 动态组件显示登录或注册 -->
    <div class="body">
      <component :is="currentComponent"></component>
    </div>
  </div>
</template>

<style lang="css" scoped>
.L1 {
  height: 500px;
  width: 1000px;
  overflow: auto;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgb(167, 204, 237);
  border-radius: 15px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.head {
  width: 100%;
  padding: 20px 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.body {
  flex: 1;
  width: 100%;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>
