package org.clxmm.autocode.system.scheduling.config;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.system.scheduling.util.SpringContextUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 将来每一个定时任务执行的时候，我们都开启一个新的线程去执行这个定时任务，SchedulingRunnable 就是关于这个线程的配置
 */
@Slf4j
public class SchedulingRunnable implements Runnable {

    /**
     * 我们需要传入相应的 beanName、methodName 以及 params 参数，传入后就来到这里了。
     * 另外还有 targetBean 和 method 分别表示 beanName 对应的对象以及 methodName 对应的对象，
     * 其中 targetBean 通过 beanName 从 Spring 容器中查找，method 则通过 methodName 从 targetBean 中查找。
     */

    private String beanName;

    private String methodName;

    private String params;

    private Object targetBean;

    private Method method;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        init();
    }

    private void init() {
        try {
            targetBean = SpringContextUtils.getBean(beanName);

            if (StrUtil.isNotBlank(params)) {
                method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = targetBean.getClass().getDeclaredMethod(methodName);
            }
        } catch (Exception e) {

        }
    }


    @Override
    public void run() {
        log.info("定时任务开始执行 - bean：{}，方法：{}，参数：{}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        try {
            if (StringUtils.hasText(params)) {
                method.invoke(targetBean, params);
            } else {
                method.invoke(targetBean);
            }
        } catch (Exception ex) {
            log.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", beanName, methodName, params), ex);
        }

        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName, methodName, params, times);

    }

    /**
     * 这里重写了 equals 和 hashCode 方法，这两个方法主要是比较了 beanName、methodName 以及 params 三个属性，
     * 换言之，如果这三个属性相同，则认为这是同一个对象（这三个属性相同表示这是同一个定时任务）
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }

}
