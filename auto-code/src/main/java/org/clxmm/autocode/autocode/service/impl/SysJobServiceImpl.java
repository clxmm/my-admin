package org.clxmm.autocode.autocode.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.api.vo.auth.Condition.SysJobCondition;
import org.clxmm.autocode.autocode.entity.SysJob;
import org.clxmm.autocode.autocode.mapper.SysJobMapper;
import org.clxmm.autocode.autocode.service.SysJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.autocode.system.security.util.SecurityUserUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务管理表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {


    @Override
    public List<SysJob> getJobsByStatus(int i) {
        LambdaQueryWrapper<SysJob> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysJob::getJobStatus, i);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysJob> listPage(Page<SysJob> page, SysJobCondition sysJobCondition) {

        LambdaQueryWrapper<SysJob> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysJobCondition.getBeanName())) {
            queryWrapper.like(SysJob::getBeanName, sysJobCondition.getBeanName());
        }

        if (sysJobCondition.getJobStatus() != null) {
            queryWrapper.like(SysJob::getJobStatus, sysJobCondition.getJobStatus());
        }

        IPage<SysJob> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public Boolean findRepeat(String beanName, String cronExpression, String methodName, String methodParams) {
        LambdaQueryWrapper<SysJob> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysJob::getBeanName, beanName)
                .eq(SysJob::getCronExpression, cronExpression)
                .eq(SysJob::getMethodName, methodName)
                .eq(SysJob::getMethodParams, methodParams);


        List<SysJob> sysJobs = baseMapper.selectList(queryWrapper);

        return CollectionUtil.isEmpty(sysJobs);
    }


    @Override
    public boolean saveOrUpdateSysJob(SysJob job) {

        if (job.getJobId() == null) {
            job.setCreateTime(new Date());
            job.setCreateId(SecurityUserUtil.getAdmin().getId());
            job.setDeleted(0);
        } else {
            job.setUpdateTime(new Date());
            job.setUpdateId(SecurityUserUtil.getAdmin().getId());
        }
        return this.saveOrUpdate(job);
    }

    @Override
    public boolean changeJobStatus(Integer jobId, Integer jobStatus) {
     /*   SysJob sysJob = new SysJob();
        sysJob.setJobId(jobId);
        sysJob.setJobStatus(jobStatus);
        return this.updateById(sysJob);*/
        SysJob sysJob = new  SysJob();
        sysJob.setJobId(jobId);
        sysJob.setJobStatus(jobStatus);
        boolean b = updateById(sysJob);
        return b;
    }

    @Override
    public void getAll() {
        baseMapper.selectList(null);
    }


}
