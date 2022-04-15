package org.clxmm.autocode.autocode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.autocode.entity.SysOperateLog;
import org.clxmm.autocode.autocode.mapper.SysOperateLogMapper;
import org.clxmm.autocode.autocode.service.SysOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.autocode.system.operatelog.core.dto.OperateLogCreateReqDTO;
import org.clxmm.autocode.util.string.StrUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

import static org.clxmm.autocode.autocode.entity.SysOperateLog.JAVA_METHOD_ARGS_MAX_LENGTH;
import static org.clxmm.autocode.autocode.entity.SysOperateLog.RESULT_MAX_LENGTH;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-01
 */
@Service
@Slf4j
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {



    @Override
    @Async
    public Future<Boolean> createOperateLogAsync(OperateLogCreateReqDTO reqVO) {
        boolean success = false;
        try {
            SysOperateLog logDO = new SysOperateLog();
            BeanUtil.copyProperties(reqVO,logDO);
//            SysOperateLog logDO = SysOperateLogConvert.INSTANCE.convert(reqVO);
            logDO.setJavaMethodArgs(StrUtils.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
            logDO.setResultData(StrUtils.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));

            System.out.println(logDO);




            success = baseMapper.insert(logDO) == 1;
        } catch (Throwable throwable) {
            // 仅仅打印日志，不对外抛出。原因是，还是要保留现场数据。
            log.error("[createOperateLogAsync][记录操作日志异常，日志为 ({})]", reqVO, throwable);
        }
        return new AsyncResult<>(success);
    }
}
