package org.clxmm.autocode.api.controller.auth;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.autocode.api.vo.auth.AdminVo;
import org.clxmm.autocode.api.vo.auth.UmsAdminLoginParam;
import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.service.AdminService;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.system.idempotent.annotation.Idempotent;
import org.clxmm.autocode.system.security.SecurityConfig;
import org.clxmm.autocode.system.security.util.SecurityUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.clxmm.autocode.util.servlet.ServletUtils.getUserAgent;

@Api(tags = "认证")
@RestController
@RequestMapping("/")
public class SysAuthController {


    @Resource
    private AdminService adminService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @Idempotent(timeout = 5)
    public Result login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return Result.error("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
//        tokenMap.put("tokenHead", tokenHead);
        return Result.ok(tokenMap);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
//    @Idempotent(timeout = 50)
    public Result getUserInfo() {
        Admin admin = SecurityUserUtil.getAdmin();
        AdminVo adminVo = new AdminVo();
        BeanUtil.copyProperties(admin,adminVo);

        adminService.getById(1);

        new Thread(() -> test1()).start();
        return Result.ok(adminVo);
    }

    private void test1() {

        for (int i = 0; i < 10; i++) {
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
            System.out.println(i);
        }

    }













}
