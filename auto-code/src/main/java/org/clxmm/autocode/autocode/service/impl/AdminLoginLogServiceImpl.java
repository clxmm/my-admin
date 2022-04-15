package org.clxmm.autocode.autocode.service.impl;

import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.entity.AdminLoginLog;
import org.clxmm.autocode.autocode.mapper.AdminLoginLogMapper;
import org.clxmm.autocode.autocode.service.AdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.autocode.autocode.service.AdminService;
import org.clxmm.autocode.util.RequestUtil;
import org.clxmm.autocode.util.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements AdminLoginLogService {

    @Autowired
    private AdminService adminService;


    @Override
    public void insert(String username) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            return;
        }
        AdminLoginLog loginLog = new AdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        String userAgent = ServletUtils.getUserAgent(request);
        System.out.println(userAgent);
        loginLog.setUserAgent(userAgent);
        baseMapper.insert(loginLog);

    }
}
