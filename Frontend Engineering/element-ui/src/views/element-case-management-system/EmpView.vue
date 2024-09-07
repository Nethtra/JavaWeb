<template>
  <div>
    <!-- 引入布局容器 -->
    <!-- 分别cv head side main -->
    <el-container style="height: 1000px; border: 2px solid #eee">
      <el-header width="200px" style="background-color: rgb(238, 241, 246); font-size: 30px">管理系统</el-header>
      <el-container>
        <el-aside width="200px" style="border: 2px solid #eee">
          <el-menu :default-openeds="['1', '3']">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-message"></i>导航一
              </template>
              <!-- 2使用router link指定跳转的url -->
              <el-menu-item index="1-1">
                <router-link to="/emp">员工管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-2">
                <router-link to="/dept">部门管理</router-link>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <!-- Form 如果标签绑定了数据模型但没有在js中声明的话 网页可能会变成空白 变量名也不能错 -->
          <el-form :inline="true" :model="formSearch">
            <el-form-item label="姓名">
              <el-input v-model="formSearch.user" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-select v-model="formSearch.gender" placeholder="性别">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="2"></el-option>
              </el-select>
            </el-form-item>
            <!-- 多添加一个日期选择器 -->
            <el-form-item label="任职日期">
              <el-date-picker
                v-model="formSearch.date"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-form>
          <br />
          <br />
          <br />

          <!-- Table -->
          <el-table :data="tableData" border>
            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="image" label="图像" width="180">
              <!-- 解决图像问题 src绑定scope-->
              <template slot-scope="scope">
                <img :src="scope.row.image" width="120px" height="90px" />
              </template>
            </el-table-column>
            <el-table-column prop="gender" label="性别" width="140">
              <!-- 解决性别问题  使用插槽 scope.row代表一行的数据 -->
              <template slot-scope="scope">{{scope.row.gender == 1 ? "男" : "女"}}</template>
            </el-table-column>
            <el-table-column prop="job" label="职位" width="140"></el-table-column>
            <el-table-column prop="entrydate" label="入职日期" width="180"></el-table-column>
            <el-table-column prop="updatetime" label="最后操作时间" width="230"></el-table-column>
            <el-table-column label="操作">
              <el-button type="primary" size="mini">编辑</el-button>
              <el-button type="danger" size="mini">删除</el-button>
            </el-table-column>
          </el-table>
          <!-- Pagination -->
          <!-- 将之前写的分页cv过来 -->
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            background
            layout="sizes,prev, pager, next,jumper,total"
            :total="1000"
          ></el-pagination>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style></style>
<script>
//先npm inatall安装axios  再引入
import axios from "axios";
export default {
  data() {
    return {
      tableData: [],
      formSearch: {
        user: "",
        gender: "",
        date: [], //因为有开始日期和结束日期所以date实际上是一个数组
      },
    };
  },
  methods: {
    handleSizeChange(val) {
      alert("现在每页显示" + val);
    },
    handleCurrentChange(val) {
      alert("现在是第" + val);
    },
    onSubmit() {
      alert("查询" + JSON.stringify(this.formSearch));
    },
  },
  //钩子方法 页面加载完成后发送异步请求获取数据，然后给到tableData，然后html渲染展示数据
  mounted() {
    axios
      .get("https://mock.apifox.cn/m1/3128855-0-default/emp/list")
      .then((result) => {
        this.tableData = result.data.data;
      });
  },
};
</script>
