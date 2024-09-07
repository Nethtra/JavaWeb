<template>
  <div>
<!-- 因为main.js默认引入根组件，所以要先在根组件引入自己的组件才能显示 -->
    <!-- 使用element-view标签  下面js会自动将自己写的.vue组件导入 即相当于导入到了根组件 -->
    
    <!-- 如果要取消引入  则三个标1的地方都要取消 -->
  <!-- 1<element-view>

  </element-view> -->


  <!-- 实测所以这个标签的名字要跟.vue组件对应  所以.vue必须驼峰命名 -->
  <!-- <emp-view>

  </emp-view> -->
  
  
  <!-- 因为最终使用的是app.vue所以在这里使用router view  根据url决定要展示的组件 -->
  <router-view></router-view>


  </div>
</template>

<style>
</style>
<script>
// import EmpView from './views/element-case-management-system/EmpView.vue'
// 1import ElementView from './views/Elements/ElementView.vue'
export default {
  components: { /*1ElementView,*/ /* EmpView*/ },
  
}
</script>