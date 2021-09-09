<template>
  <el-aside :width="asideWidth">


    <el-menu default-active="1"
             class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
             :collapse="isCollapse">
      <button class="arbutton" @click="isCollapse = !isCollapse">==</button>
      <el-submenu index="1" class="color-gray">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">导航四</span>
        </template>

        <router-link to="/user">
          <el-menu-item index="1-1">
            <template slot="title">用户管理</template>
          </el-menu-item>
        </router-link>

        <router-link to='/menu'>
          <el-menu-item index="1-2">
            <template slot="title">菜单管理</template>
          </el-menu-item>
        </router-link>

        <router-link to="/role">
          <el-menu-item index="1-3">
            <template slot="title">角色管理</template>
          </el-menu-item>
        </router-link>

      </el-submenu>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">导航四</span>
        </template>

        <router-link to="/role">
          <el-menu-item class="color-gray" index="2-1">
            <template slot="title">选项4</template>
          </el-menu-item>
        </router-link>

        <el-menu-item index="2-2">
          <template slot="title">选项4</template>
        </el-menu-item>
        <el-menu-item index="2-3">
          <template slot="title">选项4</template>
        </el-menu-item>
      </el-submenu>


    </el-menu>


  </el-aside>
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {
      isCollapse: false,
      asideWidth: '200px',
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);

    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
  watch: {
    isCollapse: {
      handler(newValue, _) {
        // console.log(newValue)
        if (!newValue) {
          this.asideWidth = '200px'
        } else {
          this.asideWidth = '60px'
        }
        // console.log(this.asideWidth)
      }
    }
  },
}
</script>

<style scoped>
.el-menu-vertical-demo {
  height: 100%;
  background-color: rgb(84, 92, 100);
}

a {
  text-decoration: none;
}
</style>