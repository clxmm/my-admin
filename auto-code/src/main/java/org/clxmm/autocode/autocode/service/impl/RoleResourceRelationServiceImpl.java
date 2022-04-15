package org.clxmm.autocode.autocode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.clxmm.autocode.autocode.entity.RoleResourceRelation;
import org.clxmm.autocode.autocode.mapper.RoleResourceRelationMapper;
import org.clxmm.autocode.autocode.service.RoleResourceRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台角色资源关系表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2022-01-14
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements RoleResourceRelationService {

    @Override
    public List<RoleResourceRelation> getByRoleId(String roleId) {
        LambdaQueryWrapper<RoleResourceRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleResourceRelation::getRoleId, roleId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
