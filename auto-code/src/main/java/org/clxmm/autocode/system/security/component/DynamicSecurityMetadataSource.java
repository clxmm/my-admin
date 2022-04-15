package org.clxmm.autocode.system.security.component;

import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 动态权限数据源，用于获取动态权限规则
 */
@Slf4j
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;


    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    // 加载资源
    @PostConstruct
    public void loadDataSource() {
        log.info("start ---> 总资源");
        configAttributeMap = dynamicSecurityService.loadDataSource();
        log.info("end   ---> 总资源");

        Set<String> strings = configAttributeMap.keySet();
        for (String entry : strings) {

            ConfigAttribute configAttribute = configAttributeMap.get(entry);
            log.info("entry:" + configAttribute.getAttribute());
        }



    }


    /**
     * 当资源有变化时，清除map中的缓存。
     */
    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null) {
            this.loadDataSource();
        }

        // 测试：每次都去加载资源好了，线上注释掉，
        // TODO 应该在没权限有变动的时候去调用
        this.loadDataSource();

        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();

        Iterator<String> iterator = configAttributeMap.keySet().iterator();

        //获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }

        // 未设置操作请求权限，返回空集合
//        configAttributes.add()
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
