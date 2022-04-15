package org.clxmm.autocode.system.security.util;


import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.system.security.dto.AdminUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 *  获取用户信息
 */
public class SecurityUserUtil {


    /**
     * 获取 AdminUserDetails
     * @return
     */
    public static AdminUserDetails getAdminUserDetail() {

        return  (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取用户信息 admin
     * @return
     */
    public static Admin getAdmin() {
        AdminUserDetails adminUserDetails = getAdminUserDetail();
        return adminUserDetails.getAdmin();
    }

    /**
     * 获取用户的权限列表 Resource
     * @return
     */
    public static List<Resource> getAdminResource() {
        return getAdminUserDetail().getResourceList();
    }

}
