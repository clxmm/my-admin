package org.clxmm.autocode.autocode.service;

import org.clxmm.autocode.autocode.entity.SysOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.autocode.system.operatelog.core.service.OperateLogFrameworkService;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-01
 */
public interface SysOperateLogService extends IService<SysOperateLog>, OperateLogFrameworkService {

}
