package org.clxmm.autocode.api.controller.auth;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.autocode.api.util.ConvertPageCommon;
import org.clxmm.autocode.api.vo.auth.Condition.RoleCondition;
import org.clxmm.autocode.api.vo.auth.ResourceVo;
import org.clxmm.autocode.api.vo.auth.RoleVo;
import org.clxmm.autocode.api.vo.common.CommonPageData;
import org.clxmm.autocode.autocode.entity.Role;
import org.clxmm.autocode.autocode.entity.RoleResourceRelation;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.autocode.service.RoleResourceRelationService;
import org.clxmm.autocode.autocode.service.RoleService;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.system.operatelog.core.annotations.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/autocode/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;


    @Autowired
    private RoleResourceRelationService roleResourceRelation;


    @ApiOperation("根据用户id，获取用户角色")
    @GetMapping("getRoleByAdminId/{adminId}")
    public Result getRoleByAdminId(@PathVariable Long adminId) {


        return Result.ok();
    }


    @ApiOperation("根据id获取角色详情")
    @GetMapping("get/{roleId}")
    public Result getRoleById(@PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        RoleVo roleVo = new RoleVo();
        BeanUtil.copyProperties(role, roleVo);
        return Result.ok(roleVo);
    }


    @ApiOperation("分页获取全部角色")
    @PostMapping("list")
    @OperateLog(enable = false)
    public Result list(@RequestBody RoleCondition roleCondition) {
        Page<Role> page = ConvertPageCommon.getPage(roleCondition);
        IPage<Role> iPage = roleService.listPage(page, roleCondition);
        CommonPageData data = ConvertPageCommon.getCommonPageDataResult(iPage, new RoleVo());
        return Result.ok(data);
    }


    @ApiOperation("根据角色id获取角色关联的资源列表")
    @GetMapping("/getResourcesByRoleId/{roleId}")
    public Result getResourcesByRoleId(@PathVariable String roleId) {
        List<ResourceVo> resourceList = resourceService.getResourceListsAll(roleId);
//        resourceList.stream().forEach(System.out::println);

        List<RoleResourceRelation> relationList = roleResourceRelation.getByRoleId(roleId);
//         List<Integer>  resourceIdSelectList = relationList.stream().map(RoleResourceRelation::getResourceId).collect(Collec)
        List<Long> resourceIdSelectList = relationList.stream().map(RoleResourceRelation::getResourceId).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("resourceList",resourceList);
        map.put("resourceIdSelectList",resourceIdSelectList);
        return Result.ok(map);
    }


    public static void main(String[] args) {
        String s  = "十二、";
        System.out.println(s.substring(0,s.indexOf("、")+1));

    }


}
