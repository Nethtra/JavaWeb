<template>
  <div>
    <!-- 使用element-view标签  下面js会自动将自己写的.vue组件导入 即相当于导入到了根组件 -->
  <element-view>

  </element-view>
<!-- 因为main.js默认引入根组件，所以要先在根组件引入自己的组件才能显示 -->

    <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view/> -->
  </div>
</template>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
<script>
import ElementView from './views/Elements/ElementView.vue'
export default {
  components: { ElementView },
  
}
</script>