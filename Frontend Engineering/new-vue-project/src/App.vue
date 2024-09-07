<!-- 编写一个简单的.vue组件 -->

<!-- html -->
<template>
<!--   <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view/>
  </div> -->
<div>
{{ message }}
</div>


</template>


<!-- css -->
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


<!-- script -->
<script>
export default {//导出后才能在js中导入
  //定义一个函数  return一个对象（省略了:function），与之前使用vue语法不用 
  data () {
    return {
      message: "hello vue"
    }
  }
  ,
  methods: {
    //也可以定义函数
    }
  }
</script>