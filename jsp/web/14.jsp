<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
JSP九大内置对象有个 pageContext和request
而pageContext有个getRequest()方法，可以获取request对象。这两个对象是一样的(内存地址行相同)
--%>
<%=pageContext.getRequest()%>
<br>
<%=request%>

<hr>
<%--
 在EL表达式中没有request对象(requestScope只代表“请求范围”不等同于request对象。)
 不过在，EL表达式中有一个隐式对象pageContext。这个pageContext和JSP九大内置对象的pageContext是同一个。
 所以我们可以在JSP中通过pageContext对象获取request对象。
--%>
${request} <%-- 空白 --%>
<br>
<%--通过内置对象pageContext才能获取request--%>
${pageContext.request}
<br>
<%--正常应该是上面的写法，下面这种写法是显示调用getter()方法--%>
${pageContext.getRequest()}

<br>
${pageContext.getRequest().getContextPath()}   <br>
<%--可以改写成--%>
${pageContext.request.contextPath}