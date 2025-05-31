package oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();
        HttpSession session = req.getSession(false);

        // 判定这些请求是因为，重定向也会走过滤器的。不这么写会一直转发停不下来
        if ("/welcome".equals(servletPath) || "/index.jsp".equals(servletPath)
                || "/user/login".equals(servletPath)|| "/user/logout".equals(servletPath)|| "/error.jsp".equals(servletPath)
                || session != null && session.getAttribute("username") != null) {
            // 继续往下走
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
