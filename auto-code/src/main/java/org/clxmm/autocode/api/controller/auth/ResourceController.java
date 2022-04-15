package org.clxmm.autocode.api.controller.auth;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.clxmm.autocode.api.util.ConvertPageCommon;
import org.clxmm.autocode.api.vo.auth.Condition.ResourceCondition;
import org.clxmm.autocode.api.vo.auth.ResourceVo;
import org.clxmm.autocode.api.vo.common.CommonPageData;
import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "后台资源表表管理")
@RestController
@RequestMapping("/autocode/resource")
public class ResourceController {


    @Autowired
    private ResourceService resourceService;

    @ApiOperation("根据id获取详情")
    @GetMapping("getDetail/{id}")
    public Result getDetail(@PathVariable Long id) {
        Resource resource = resourceService.getById(id);
        ResourceVo resourceVo = new ResourceVo();
        BeanUtil.copyProperties(resource, resourceVo);
        return Result.ok(resourceVo);
    }

    @ApiOperation("分页查询")
    @PostMapping("list")
    public Result list(@RequestBody ResourceCondition resourceCondition) {
        Page<Resource> page = ConvertPageCommon.getPage(resourceCondition);
        IPage<Resource> list = resourceService.listPage(page, resourceCondition);
        CommonPageData commonPageData = ConvertPageCommon.getCommonPageData(list);
        List<ResourceVo> data = list.getRecords().stream().map(e -> {
            ResourceVo resourceVo = new ResourceVo();
            BeanUtil.copyProperties(e, resourceVo);
            return resourceVo;
        }).collect(Collectors.toList());
        commonPageData.setData(data);
        return Result.ok(commonPageData);
    }


    @ApiOperation("保存或更新")
    @PostMapping("save")
    public Result sav(@RequestBody ResourceVo resourceVo) {
        Resource resource = new Resource();
        BeanUtil.copyProperties(resourceVo, resource);
        resourceService.saveOrUpdate(resource);
        return Result.ok("操作成功");
    }


    @ApiOperation("根据id删除")
    @DeleteMapping("delete/{id}")
    public Result del(@PathVariable Long id) {
        boolean b = resourceService.removeById(id);
        if (b) {
            return Result.okDelSuccess();
        }
        return Result.okDelFail();
    }


}
