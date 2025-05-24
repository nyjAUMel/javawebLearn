<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    向浏览器输入变量，该变量out在方法在service声明，是内置对象
    九大内置对象内置在service方法中，也就是说只能在<%%>里使用
--%>
<%
    String name = "zhangsan";
    out.write(name);
%>

<%-- <%=%最后会被翻译为out.print();，所以100 + 200会被放到括号里面 --%>
<%-- 什么时候会用这种语法呢？就是输出的内容里面有java变量，是一个动态的内容，不是一个死的字符串 --%>
<%=100 + 200%>  <%-- 翻译成：out.print(100 + 200);--%>
<%-- 要是没变量直接写就行 --%>
他们说这个地方有XX

<%
    String username = "DingZhen";
%>
<%="欢迎:" + username%> <%-- 翻译成：out.print("欢迎:" + username);--%>
