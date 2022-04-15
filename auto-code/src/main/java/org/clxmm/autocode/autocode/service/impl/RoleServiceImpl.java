package org.clxmm.autocode.autocode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.api.vo.auth.Condition.RoleCondition;
import org.clxmm.autocode.autocode.entity.Role;
import org.clxmm.autocode.autocode.mapper.RoleMapper;
import org.clxmm.autocode.autocode.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<Role> listPage(Page<Role> page, RoleCondition roleCondition) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(roleCondition.getRoleName())) {
            lambdaQueryWrapper.like(Role::getName,roleCondition.getRoleName());
        }
        return baseMapper.selectPage(page,lambdaQueryWrapper);
    }
}
