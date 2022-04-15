package org.clxmm.autocode.autocode.service;

import org.clxmm.autocode.autocode.entity.RoleResourceRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台角色资源关系表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2022-01-14
 */
public interface RoleResourceRelationService extends IService<RoleResourceRelation> {

    /**
     * 根据角色id获取角色的资源列表
     * @param roleId
     * @return
     */
    List<RoleResourceRelation> getByRoleId(String roleId);


}
