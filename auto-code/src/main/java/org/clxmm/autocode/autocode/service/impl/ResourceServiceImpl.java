package org.clxmm.autocode.autocode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.autocode.api.vo.auth.Condition.ResourceCondition;
import org.clxmm.autocode.api.vo.auth.ResourceVo;
import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.mapper.ResourceMapper;
import org.clxmm.autocode.autocode.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<Resource> getResourceList(Long adminId) {
        return baseMapper.getResourceList(adminId);
    }


    @Override
    public IPage<Resource> listPage(Page<Resource> page, ResourceCondition resourceCondition) {

        LambdaQueryWrapper<Resource> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(resourceCondition.getResourceName())) {
            lambdaQueryWrapper.like(Resource::getName, resourceCondition.getResourceName());
        }
        return baseMapper.selectPage(page, lambdaQueryWrapper);

    }

    @Override
    public List<ResourceVo> getResourceListsAll(String parentId) {
        List<Resource> list = baseMapper.selectList(null);
        List<ResourceVo> resourceVoList = BeanUtil.copyToList(list, ResourceVo.class);
        List<ResourceVo> ResourceVo = getChildren(resourceVoList, 0L);
        return ResourceVo;
    }









    private void getChildPerms(List<ResourceVo> resourceVoList, String parentId) {
        List<ResourceVo> rootList = new ArrayList<>();

        Iterator<ResourceVo> lt = resourceVoList.iterator();
        while (lt.hasNext()) {
            ResourceVo resourceVo = lt.next();
            if (resourceVo.getParentId().equals(parentId)) {
                rootList.add(resourceVo);
                lt.remove();
            }
        }
        for (ResourceVo resourceVo : rootList) {
            resourceVo.setChildren(getChildren(resourceVoList, resourceVo.getId()));
        }

    }

    private List<ResourceVo> getChildren(List<ResourceVo> allResource, Long id) {
        List<ResourceVo> children = new ArrayList<>();
        Iterator<ResourceVo> iterator = allResource.iterator();
        while (iterator.hasNext()) {
            ResourceVo resourceVo = iterator.next();
            if (resourceVo.getParentId().equals(id.toString())) {
                children.add(resourceVo);
                iterator.remove();
            }
        }

        for (ResourceVo resourceVo : children) {
            resourceVo.setChildren(getChildren(allResource,resourceVo.getId()));
        }
        return children;
    }
}
