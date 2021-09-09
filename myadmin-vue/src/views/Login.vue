<template>
  <div class="login">
    <div class="bag">
      <img :src="bag" width="100%" height="100%">
    </div>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="loginForm">

      <el-form-item label="用户名" prop="name" class="item1">
        <el-input class="form-input" v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input class="form-input" v-model="ruleForm.password"></el-input>
      </el-form-item>

      <el-form-item label="验证码" prop="code">
        <el-input class="code-input" v-model="ruleForm.code"></el-input>
        <el-image class="code-img" :src="captchaImg" @click="getCaptcha"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>

import request from "@/util/axios";

export default {
  name: "Login",
  data() {
    return {
      imageCodeKey: '',
      captchaImg: null,
      bag: require('../assets/img/bag.jpeg'),
      ruleForm: {
        name: 'clxmm',
        password: '123456',
        code: '1111',
      },
      rules: {
        name: [
          {required: true, message: '   请输入用户名', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur'}
        ],
        code: [
          {required: true, message: '请输入验证码', trigger: 'blur'},
          {min: 4, max: 4, message: '长度4字符', trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 发送登陆请求
          request.post("http://localhost:9009/login", {
            userName: this.ruleForm.name,
            password: this.ruleForm.password,
            code: this.ruleForm.code,
            imageCodeKey: this.imageCodeKey
          }).then(
              response => {
                console.log(response)
                if (response.code != 200) {
                  this.$alert(response.data.msg, '标题名称', {
                    confirmButtonText: '确定',
                    // todo
                  });
                } else {

                  console.log('login success')
                  this.$router.push("/")

                }

              },
              error => {
                console.log(error)
              },)
        } else {

          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    getCaptcha() {
      request.get('http://localhost:9009/captcha').then(res => {
        console.log("/captcha")
        console.log(res)
        this.captchaImg = res.data.captchaImg
        this.imageCodeKey = res.data.imageCode
      })
    }
  },
  created() {
    this.getCaptcha()
  }
}
</script>

<style scoped>
.code-input {
  width: 40%;
  float: left;
  border-radius: 5px;
}

.code-img {
  float: left;
  height: 40px;
  width: 30%;
  background-color: red;
  margin-left: 5px;
  border-radius: 5px;

}

.form-input {
  width: 65%;
  border-radius: 4px;
}


.item1 {
  margin-top: 10%;

}

.loginForm {
  margin-top: 10%;
  margin-left: 60%;
  width: 30%;
  height: 40%;
  position: absolute;
  background-color: rgba(0, 0, 0, 0.7)
}

.bag {
  width: 100%; /**宽高100%是为了图片铺满屏幕 */
  height: 100%;
  z-index: -1;
  position: absolute;

}
</style>