package org.clxmm.autocode.autocode.mapper;

import org.clxmm.autocode.autocode.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author clxmmTest
 * @since 2021-09-06
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> getResourceList(Long adminId);

    List<Resource> getResourceListByRoleId(String roleId);
}
