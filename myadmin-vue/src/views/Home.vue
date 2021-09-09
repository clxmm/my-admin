<template>
  <el-container>
    <SideMenu/>

    <el-container>

      <el-header>
        <strong>VueAdmin后台管理系统</strong>
        <div class="header-avatar">
          <el-avatar size="medium"
                     :src="userIfo.img"></el-avatar>
          <el-dropdown>
						<span class="el-dropdown-link">
					    {{ userIfo.name }} <i class="el-icon-arrow-down el-icon--right"></i>
						</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <router-link to="/userCenter">个人中心</router-link>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <el-link href="https://markerhub.com" target="_blank">网站</el-link>
          <el-link href="https://space.bilibili.com/13491144" target="_blank">B站</el-link>

        </div>

      </el-header>

      <el-main>

        <router-view/>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
import SideMenu from "@/views/inc/SideMenu";
import Index from "@/views/Index";
import axios from "@/util/axios";

export default {
  name: "Home",
  components: {
    SideMenu,
    Index
  },
  data() {
    return {
      isCollapse: false,
      asideWidth: '200px',
      userIfo: {
        name: '',
        img: '',
      }
    };
  },
  created() {
    this.getUserInfo()
  },
  methods: {

    // todo 获取用户的信息，
    getUserInfo() {
      let token = window.localStorage.getItem("token")
      //发起请求获取 token

      // 模拟数据
      this.userIfo = {
        name: 'clxmm',
        img: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      }
    }


  },

}
</script>

<style scoped>

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}


.el-menu-vertical-demo span {
  color: #D3DCE6;
}

.color-gray {
  background-color: rgb(84, 92, 100);
}


.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}

.header-avatar {
  float: right;
  width: 210px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-menu-item {
  color: #D3DCE6;
  background-color: rgb(84, 92, 100);
}

.el-header {
  background-color: #17B3A3;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: rgb(84, 92, 100);
  color: #333;
  line-height: 200px;
}

.el-main {
  color: #333;
  padding: 0;
}

a {
  text-decoration: none;
}

a > li {
  text-decoration: none !important;
}

</style>