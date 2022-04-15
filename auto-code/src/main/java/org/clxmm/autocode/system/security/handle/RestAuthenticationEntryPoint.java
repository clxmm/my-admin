package org.clxmm.autocode.system.security.handle;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.common.Result;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 * Created by macro on 2018/5/14.
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        authException.printStackTrace();


        authException.printStackTrace();
        log.info(authException.getClass().toString());


        response.getWriter().println(JSONUtil.parse(Result.error(authException.getMessage())));
        response.getWriter().flush();
    }
}