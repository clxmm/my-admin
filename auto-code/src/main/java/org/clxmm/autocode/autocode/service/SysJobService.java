package org.clxmm.autocode.autocode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.autocode.api.vo.auth.Condition.SysJobCondition;
import org.clxmm.autocode.autocode.entity.SysJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 定时任务管理表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
public interface SysJobService extends IService<SysJob> {


    /**
     *  更具定时任务的状态查询
     * @param i
     * @return
     */
    List<SysJob> getJobsByStatus(int i);

    /**
     * 分页查询
     */
    IPage<SysJob> listPage(Page<SysJob> page, SysJobCondition sysJobCondition);

    /**
     * 如果 bean名称， cron表达式，方法名，和参数  已经添加过了，则任务是同一个任务，不能重复添加
     * @param beanName
     * @param cronExpression
     * @param methodName
     * @param methodParams
     * @return  true 没有重复，false ,有重复
     */
    Boolean findRepeat(String beanName, String cronExpression, String methodName, String methodParams);

    /**
     * 保存或者更新
     * @param job
     */
    boolean saveOrUpdateSysJob(SysJob job);

    /**
     * 修改任务的状态
     * @param jobId
     * @param jobStatus
     */
    boolean changeJobStatus(Integer jobId, Integer jobStatus);

    void getAll();
}
