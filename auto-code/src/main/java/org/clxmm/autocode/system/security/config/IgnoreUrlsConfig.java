package org.clxmm.autocode.system.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();


    public List<String> defaultUrls() {
        List list = new ArrayList();

        List<String> urls = Arrays.asList("/swagger-ui.html",
                "/swagger-resources/**",
                "/swagger/**",
                "/**/v2/api-docs",
                "/webjars/springfox-swagger-ui/**",
                "/doc.html",
                "/v2/api-docs"
        );

        list.addAll(this.urls);
        list.addAll(urls);
//        list.addAll(staticUrls());
        return list;
    }


    public List<String> staticUrls() {
        return Arrays.asList(
                "test",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/static/**",
                "/**/*.png",
                "/ws/**",
                "/**/*.ico");
    }


}
