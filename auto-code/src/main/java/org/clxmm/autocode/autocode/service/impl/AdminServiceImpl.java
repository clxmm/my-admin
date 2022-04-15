package org.clxmm.autocode.autocode.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.api.vo.auth.Condition.AdminCondition;
import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.mapper.AdminMapper;
import org.clxmm.autocode.autocode.service.AdminLoginLogService;
import org.clxmm.autocode.autocode.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.common.exception.ServiceException;
import org.clxmm.autocode.system.security.dto.AdminUserDetails;
import org.clxmm.autocode.system.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private AdminLoginLogService adminLoginLogService;

    @Autowired
    private ResourceService resourceService;



    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new ServiceException("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                throw new ServiceException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            adminLoginLogService.insert(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;


    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        Admin admin = getAdminByUsername(username);
        if (admin != null) {
            List<Resource> resourceList = resourceService.getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }


        throw new UsernameNotFoundException("用户名或密码错误");
    }


    @Override
    public Admin getAdminByUsername(String username) {

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        List<Admin> admins = baseMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(admins)) {
            return null;
        }

        return admins.get(0);
    }

    @Override
    public IPage<Admin> list(Page<Admin> adminPage, AdminCondition adminCondition) {

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(adminCondition.getUsername())) {
            queryWrapper.like(Admin::getNickName, adminCondition.getUsername());
        }

        IPage<Admin> page = baseMapper.selectPage(adminPage, queryWrapper);

        return page;
    }


    @Override
    public void saveOrUpdateAdmin(Admin admin) {
        if (admin.getId() == null) { // 添加
            admin.setCreateTime(new Date());
            admin.setStatus(Admin.STATUS_ENABLE);
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        } else  {
            Admin admin0 = this.getById(admin.getId());
            if (admin0 == null) {
                throw  new ServiceException("用户id不存在");
            }

            if (StringUtils.isNotBlank(admin.getPassword())) {
                // 密码不相同，进行加密
                if (!admin.getPassword().endsWith(admin0.getPassword())) {
                    log.info("密码加密");
                    admin.setPassword(passwordEncoder.encode(admin.getPassword()));
                }
            }
        }
        this.saveOrUpdate(admin);
    }
}
