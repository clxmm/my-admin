package org.clxmm.autocode.api.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.clxmm.autocode.api.vo.DatasourceConfigVo;
import org.clxmm.autocode.autocode.entity.DatasourceConfig;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.system.idempotent.annotation.Idempotent;
import org.clxmm.autocode.system.operatelog.core.annotations.OperateLog;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 数据源配置表 前端控制器
 * </p>
 *
 * @author clxmmTest
 * @since 2021-08-31
 */
@RestController
@RequestMapping("/autocode/datasource-config")
@Api(tags = "数据源配置")

public class DatasourceConfigController {


    @ApiOperationSupport(author = "xiaoymin@foxmail.com")
    @ApiOperation(value = "测试hello")
    @GetMapping("sayHello")
    @Idempotent(timeout = 100)
    public Result sayHello() {
        return Result.ok("hello");
    }


    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated DatasourceConfigVo datasourceConfigVo) {

        System.out.println(datasourceConfigVo);

        DatasourceConfig datasourceConfig = new DatasourceConfig();
        BeanUtil.copyProperties(datasourceConfigVo, datasourceConfig);
        System.out.println(datasourceConfig);

        return Result.ok(datasourceConfigVo);
    }


}
