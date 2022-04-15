package org.clxmm.autocode.api.controller.test;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "资料文件管理")

public interface LoginController {
    @ApiOperation(value = "登录", notes = "用户名登录")
    String login(String name);
}
