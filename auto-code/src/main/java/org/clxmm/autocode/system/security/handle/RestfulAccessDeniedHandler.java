package org.clxmm.autocode.system.security.handle;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.autocode.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.clxmm.autocode.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

@Slf4j
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        e.printStackTrace();
//        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.error(FORBIDDEN)));
        response.getWriter().flush();
    }
}
