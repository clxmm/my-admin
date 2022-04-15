package org.clxmm.autocode.autocode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.autocode.api.vo.auth.Condition.RoleCondition;
import org.clxmm.autocode.autocode.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
public interface RoleService extends IService<Role> {

    IPage<Role> listPage(Page<Role> page, RoleCondition roleCondition);
}
