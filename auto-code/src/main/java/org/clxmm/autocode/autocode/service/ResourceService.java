package org.clxmm.autocode.autocode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.autocode.api.vo.auth.Condition.ResourceCondition;
import org.clxmm.autocode.api.vo.auth.ResourceVo;
import org.clxmm.autocode.autocode.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
public interface ResourceService extends IService<Resource> {

    /**
     *  根据用户id,获取用户的资源列表
     * @param adminId
     * @return
     */
    public List<Resource> getResourceList(Long adminId) ;

    /**
     * 分页查询
     * @param page
     * @param resourceCondition
     * @return
     */
    IPage<Resource> listPage(Page<Resource> page, ResourceCondition resourceCondition);

    /**
     *  根据角色id获取所对应的资源
     * @param parentId
     */
    List<ResourceVo> getResourceListsAll(String parentId);



}
