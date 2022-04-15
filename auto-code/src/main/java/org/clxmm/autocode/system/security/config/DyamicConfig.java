package org.clxmm.autocode.system.security.config;

import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.system.security.component.DynamicAccessDecisionManager;
import org.clxmm.autocode.system.security.component.DynamicSecurityMetadataSource;
import org.clxmm.autocode.system.security.component.DynamicSecurityService;
import org.clxmm.autocode.system.security.filter.DynamicSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DyamicConfig {


    @Autowired
    private ResourceService resourceService;

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<Resource> resourceList = resourceService.list();
            for (Resource resource : resourceList) {
                map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }


    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }


    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

    // 基于路由的权限控制
    // 条件注入
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }


}
