package org.clxmm.autocode.autocode.service;

import org.clxmm.autocode.autocode.entity.AdminLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户登录日志表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
public interface AdminLoginLogService extends IService<AdminLoginLog> {

    /**
     * 添加登录记录
     * @param username 用户名
     */
    void insert(String username);


}
