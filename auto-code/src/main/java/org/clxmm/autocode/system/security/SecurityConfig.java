package org.clxmm.autocode.system.security;

import org.clxmm.autocode.autocode.entity.Resource;
import org.clxmm.autocode.autocode.service.AdminService;
import org.clxmm.autocode.autocode.service.ResourceService;
import org.clxmm.autocode.system.security.component.DynamicAccessDecisionManager;
import org.clxmm.autocode.system.security.component.DynamicSecurityMetadataSource;
import org.clxmm.autocode.system.security.component.DynamicSecurityService;
import org.clxmm.autocode.system.security.config.IgnoreUrlsConfig;
import org.clxmm.autocode.system.security.filter.DynamicSecurityFilter;
import org.clxmm.autocode.system.security.filter.JwtAuthenticationTokenFilter;
import org.clxmm.autocode.system.security.handle.RestAuthenticationEntryPoint;
import org.clxmm.autocode.system.security.handle.RestfulAccessDeniedHandler;
import org.clxmm.autocode.system.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;


    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;


    @Autowired
    DynamicSecurityFilter dynamicSecurityFilter;


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //????????????????????????
        return username -> adminService.loadUserByUsername(username);
    }






    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        for (String url : ignoreUrlsConfig().staticUrls()) {
            ignoring.antMatchers(url);
        }
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();

        //??????????????????????????????????????????
        for (String url : ignoreUrlsConfig().defaultUrls()) {
            System.out.println(url);
            registry.antMatchers(url).permitAll();
        }

        //?????????????????????OPTIONS??????
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();


        // ??????????????????????????????
        registry.and()
                .cors().and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // ????????????????????????????????????session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // ??????????????????????????????
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                // ????????????????????????JWT?????????
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        //?????????????????????????????????????????????????????????
        if (dynamicSecurityService != null) {
            registry.and().addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
        }
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }


    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }


}
