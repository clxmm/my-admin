<template>
  <div>
    <el-button>用户中心</el-button>
    <el-form class="userForm" :rules="rules" ref="userIfo" :label-position="labelPosition" label-width="80px" :model="userIfo">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="userIfo.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userIfo.password"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="password1">
        <el-input v-model="userIfo.password1"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('userIfo')">提交</el-button>
        <el-button @click="resetForm('userIfo')">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>

import axios from "@/util/axios";


export default {
  name: "UserCenter",
  data() {
    return {
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      // 用户信息
      userIfo: {
        name: '',
        password: '',
        password1:'',
        img: '',
      },
      rules:{
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password1: [
          { required: true, message: '请输入确认密码', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {

    },


    getUserInfo() {

    }


  },

  created() {
    // 进入页面的时候去加载用户信息


  }


}
</script>

<style scoped>
.userForm {
  width: 50%;
  margin: 5% auto auto;
}

</style>