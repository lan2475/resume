package com.lanxiansen.resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lanshuangyang
 * @date 2019/7/12 15:11
 */
@Component
@WebFilter(urlPatterns = "/**",filterName = "loginFilter")
public class LoginFilter implements Filter{
    Logger log = LoggerFactory.getLogger("访问日志");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String path = ((HttpServletRequest) servletRequest).getServletPath();
        log.info("访问IP为"+CusAccessObjectUtil.getIpAddress(request));
        if(path.startsWith("/static")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            servletRequest.getRequestDispatcher("/static/UniverseCube.htm").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
