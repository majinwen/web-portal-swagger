package com.toutiao.web.apiimpl.conf;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter("/searchapiv2")
public class PathFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.getRequestDispatcher(req.getServletPath().replace("/searchapiv2/", "/")).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
