package org.clxmm.autocode.api.controller.auth;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.api.util.ConvertPageCommon;
import org.clxmm.autocode.api.vo.auth.AdminVo;
import org.clxmm.autocode.api.vo.common.CommonPageData;
import org.clxmm.autocode.api.vo.auth.Condition.AdminCondition;
import org.clxmm.autocode.api.vo.auth.AdminPassVo;
import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.service.AdminService;
import org.clxmm.autocode.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.clxmm.autocode.api.config.GlobalControllerAdvice.BAD_REQUEST_MSG;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
@Api(tags = "后台用户管理")
@RestController
@RequestMapping("/autocode/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ApiOperation("获取用户列表")
    @PostMapping("list")
    public Result list(@RequestBody(required = false) AdminCondition adminCondition) {

        Page<Admin> page = ConvertPageCommon.getPage(adminCondition);

        IPage<Admin> list = adminService.list(page, adminCondition);
//        adminService

        CommonPageData data = ConvertPageCommon.getCommonPageData(list);

        List<AdminVo> collect = list.getRecords().stream().map(e -> {
            AdminVo adminVo = new AdminVo();
            BeanUtil.copyProperties(e, adminVo);
            return adminVo;
        }).collect(Collectors.toList());

        data.setData(collect);

        return Result.ok(data);
    }

    @ApiOperation("查询用户名是否可用")
    @GetMapping("checkUserName")
    public Result checkUserName(String userName) {

        if(StringUtils.isBlank(userName)) {
            return Result.error("用户名不能为空");
        }

        Admin admin = adminService.getAdminByUsername(userName);
        Map<String, Boolean> result = new HashMap<>();

        result.put("flag", admin == null);
        return  Result.ok(result);
    }





    @ApiOperation("保存/更新用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@Validated @RequestBody AdminPassVo adminPassVo) {

        // 新增时，密码必填校验
        if (adminPassVo.getId() == null  && StringUtils.isBlank(adminPassVo.getPassword())) {
            List<String> list = Arrays.asList("参数password:不能为空");
            return Result.error(list,BAD_REQUEST_MSG);
        }

        // 校验用户名重复
        Admin adminByUsername = adminService.getAdminByUsername(adminPassVo.getUsername());
        if (adminByUsername!=null) {
            return  Result.error("用户名已经存在");
        }






        Admin admin = new Admin();
        System.out.println(adminPassVo);
        BeanUtil.copyProperties(adminPassVo,admin);
//        adminService.saveOrUpdate()



        adminService.saveOrUpdateAdmin(admin);

        return Result.okAddOrUpdate(adminPassVo.getId());

    }








}
