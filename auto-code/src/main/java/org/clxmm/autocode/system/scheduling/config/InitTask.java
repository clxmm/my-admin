package org.clxmm.autocode.system.scheduling.config;


import org.clxmm.autocode.autocode.entity.SysJob;
import org.clxmm.autocode.autocode.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统启动的时候初始化定时任务
 */
@Component
public class InitTask implements CommandLineRunner {

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    SysJobService sysJobService;


    @Override
    public void run(String... args) throws Exception {
        List<SysJob> list = sysJobService.getJobsByStatus(0);
        for (SysJob sysJob : list) {
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());
        }
    }

    
}
