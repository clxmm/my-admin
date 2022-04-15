package org.clxmm.autocode.system.scheduling.taskbean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试的定时任务bean
 */
@Component("schedulingTaskDemo")
@Slf4j
public class SchedulingTaskDemo {


    public void taskWithParams(String params) {
        System.out.println("执行有参示例任务：" + params);
        log.info("执行有参示例任务：" + params);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
        log.info("执行有参示例任务");
    }

}
