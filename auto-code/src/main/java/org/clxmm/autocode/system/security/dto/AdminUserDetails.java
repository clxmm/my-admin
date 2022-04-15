package org.clxmm.autocode.system.security.dto;

import cn.hutool.core.collection.CollectionUtil;
import org.clxmm.autocode.autocode.entity.Admin;
import org.clxmm.autocode.autocode.entity.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {


    private Admin admin;
    private List<Resource> resourceList;

    public AdminUserDetails(Admin umsAdmin, List<Resource> resourceList) {
        this.admin = umsAdmin;
        this.resourceList = resourceList;
    }


    public AdminUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> list = CollectionUtil.isEmpty(resourceList) ? null : resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
        // 返回当前用户操作权限的集合
        return list;
    }


    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }


    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
