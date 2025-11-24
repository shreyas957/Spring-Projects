package com.shreyas.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class MyCustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyCustomFilter inside");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyCustomFilter complete");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
