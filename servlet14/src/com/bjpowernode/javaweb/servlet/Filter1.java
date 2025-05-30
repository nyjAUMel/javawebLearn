package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/*
* Servlet对象默认情况下，在服务器启动的时候*不会*新建对象
* Filter对象默认情况下，在服务器启动的时候*会*新建对象
* Servlet和Filter都是单例的
* */

//@WebFilter("/abc")
// 把这个过滤器的请求路径写成跟Servlet一样的
//@WebFilter("/a.do")
//@WebFilter({"/a.do", "/b.do"})
/* 直接模糊匹配，任何路径都会走到这个过滤器，然后进入doFilter()后通过请求的路径进入下一层请求

   *.do和/*
   请求URL              *.do匹配     /*匹配
    /login.do             √           √
    /user/add.do          √           √
    /user/list.do         √           √
    /login                ×           √
    /user/list            ×           √
    /images/logo.jpg      ×           √
    /css/style.css        ×           √
    /js/main.js           ×           √
 */
@WebFilter("*.do") // 补充：*.do路径属于模糊匹配中的扩展匹配，以 * 开始，不能带斜杠
public class Filter1 implements Filter {

    public Filter1() {
        System.out.println("Filter1 constructor method execute");
    }

    // 初始化方法，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init() execute");
    }

    // 该方法只要发送一次请求就会执行一次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //  请求开始执行
        System.out.println("doFilter() Start");
        /**
         * 将请求传递给过滤器链中的下一个过滤器或最终的Servlet/JSP
         * (简单说就是：将请求传给下一个过滤器，如果下一个不是过滤器了就是目标Servlet)
         *  1. 传递控制权
              没有这行代码，请求就会在当前过滤器"停止"
              有了这行代码，请求才能继续向下传递
         */
        filterChain.doFilter(servletRequest, servletResponse);

        // 响应结束后执行
        System.out.println("doFilter() End");
    }

    //  销毁方法，只执行一次
    @Override
    public void destroy() {
        System.out.println("destroy() execute");
    }
}
