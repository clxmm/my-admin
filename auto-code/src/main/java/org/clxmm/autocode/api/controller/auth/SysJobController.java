package org.clxmm.autocode.api.controller.auth;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.api.util.ConvertPageCommon;
import org.clxmm.autocode.api.vo.auth.Condition.SysJobCondition;
import org.clxmm.autocode.api.vo.auth.SysJobStaVo;
import org.clxmm.autocode.api.vo.auth.SysJobVo;
import org.clxmm.autocode.api.vo.common.CommonPageData;
import org.clxmm.autocode.autocode.entity.SysJob;
import org.clxmm.autocode.autocode.service.SysJobService;
import org.clxmm.autocode.common.Result;
import org.clxmm.autocode.system.operatelog.core.annotations.OperateLog;
import org.clxmm.autocode.system.scheduling.config.CronTaskRegistrar;
import org.clxmm.autocode.system.scheduling.config.SchedulingRunnable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 定时任务管理表 前端控制器
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
@Api(tags = "定时任务管理")
@RestController
@RequestMapping("/autocode/sys-job")
@Slf4j
public class SysJobController {

    @Autowired
    SysJobService sysJobService;


    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    @ApiOperation("分页查询")
    @PostMapping("list")
    @OperateLog(enable = false)
    public Result list(@RequestBody SysJobCondition sysJobCondition) {
        Page<SysJob> page = ConvertPageCommon.getPage(sysJobCondition);

        IPage<SysJob> sysJobIPage = sysJobService.listPage(page, sysJobCondition);

        CommonPageData data = ConvertPageCommon.getCommonPageData(sysJobIPage);

        List<SysJobVo> list = sysJobIPage.getRecords().stream().map(e -> {
            SysJobVo sysJobVo = new SysJobVo();
            BeanUtil.copyProperties(e, sysJobVo);
            return sysJobVo;
        }).collect(Collectors.toList());
        data.setData(list);
        return Result.ok(data);
    }


    @ApiOperation("添加/更新.定时任务")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody @Validated SysJobVo sysJobVo) {
        // 如果 bean名称， cron表达式，方法名，和参数  已经添加过了，则任务是同一个任务，不能重复添加
        if (sysJobVo.getJobId() == null) {
            Boolean flag = sysJobService.findRepeat(sysJobVo.getBeanName(), sysJobVo.getCronExpression(), sysJobVo.getMethodName(), sysJobVo.getMethodParams());
            if (!flag) {
                return Result.error("添加失败，任务重复");
            }
        }
        SysJob job = new SysJob();
        BeanUtils.copyProperties(sysJobVo, job);
        job.setDeleted(0);
        Boolean flag = sysJobService.saveOrUpdateSysJob(job);
        if (flag) {
            log.info("开启定时任务");
            //添加成功，如果新加的job是开启状态，就顺便开启
            if (job.getJobStatus() == 0) {
                SchedulingRunnable schedulingRunnable = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(schedulingRunnable, job.getCronExpression());
                return Result.ok("启动定时任务成功");
            }
        }
        return Result.ok(sysJobVo.getJobId());
    }


    //更改状态
    @ApiOperation("更改状态")
    @PostMapping("changeJobStatus")
    public Result changeJobStatus(@RequestBody @Validated SysJobStaVo sysJobStaVo) {
        boolean f = sysJobService.changeJobStatus(sysJobStaVo.getJobId(), sysJobStaVo.getJobStatus());
        if (f) {
            changeTaskJobStatus(sysJobStaVo.getJobId(), sysJobStaVo.getJobStatus());
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }


    // 添加/移除 线程里的定时任务
    private void changeTaskJobStatus(Integer jobId, Integer status) {
        SysJob job = sysJobService.getById(jobId);
        if (job == null) {
            log.error("定时修改启动失败，定时任务不存在." + jobId);
            return;
        }
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
        // 移除
        if (status == 1) {
            cronTaskRegistrar.removeCronTask(schedulingRunnable);
        } else { // 启动
            cronTaskRegistrar.addCronTask(schedulingRunnable,job.getCronExpression());
        }

    }

}
