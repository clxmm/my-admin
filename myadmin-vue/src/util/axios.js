import axios from "axios";

import router from "@/router";
import {Message} from 'element-ui'

axios.defaults.baseURL = "http://localhost:9009"


const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    }
})


request.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem("token")
    return config
})

request.interceptors.response.use(
    response => {

        console.log("response ->" + response)

        let res = response.data

        if (res.code === 200) {
            return response.data
        } else {
            Message.error(response.data.msg)
            // this.$message.error(response.data.msg)
            return Promise.reject(response.data.msg)
        }
    },
    error => {

        console.log(error)

        if (error.response.data) {
            error.massage = error.response.data.msg
        }

        if (error.response.status === 401) {
            Message.error(error.massage)
            router.push("/login")
        }

        Message.error(error.massage)
        return Promise.reject(error)
    }
)

export default request
