<%@page session="false" %>

<%--指定当前页面出错后跳转的页面--%>
<%@page errorPage="/error.jsp" %>

<%
    String s = null;
    s.toString();
%>


<%--JSP九大内置对象--%>
pageContext：页面上下文，可以获取其他所有内置对象。
request：获取请求数据，如参数、属性、请求头等。
response：设置响应内容或控制响应行为（如重定向）。
session：保存用户会话信息，跨请求共享数据。
application：在整个 Web 应用中共享数据。
config：获取当前 JSP 对应 Servlet 的初始化参数。
page：代表当前 JSP 转换后的 Servlet 实例。
exception：在错误页面中获取异常信息（需 <%@ page isErrorPage="true" %>）。
out：向浏览器输出内容，相当于带缓冲的 PrintWriter。