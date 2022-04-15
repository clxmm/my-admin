package org.clxmm.autocode.autocode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.autocode.api.vo.auth.Condition.AdminCondition;
import org.clxmm.autocode.autocode.entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
public interface AdminService extends IService<Admin>, UserDetailsService {

    String login(String username, String password);


    /**
     * 根据用户吗查找
     *
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);


    /**
     * 用户分页查询
     *
     * @param adminPage
     * @param adminCondition
     * @return
     */
    IPage<Admin> list(Page<Admin> adminPage, AdminCondition adminCondition);

    /**
     * 保存，或更新
     * @param admin
     */
    void saveOrUpdateAdmin(Admin admin);
}
