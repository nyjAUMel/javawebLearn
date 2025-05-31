package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter2() Start");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter2() End");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
