package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-05 16:48
 * @Version: 1.0
 */
public class GetServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("        <html lang=\"en\">");
        writer.println("        <head>");
        writer.println("        <meta charset=\"UTF-8\">");
        writer.println("        <title>Title</title>");
        writer.println("        </head>");
        writer.println("        <body>");
        writer.println("        <h1>GET Servlet</h1>");
        writer.println("        </body>");
        writer.println("        </html>");
    }
}
