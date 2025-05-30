<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/30
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
    choose标签：
        用于实现条件判断
          when标签：
              test属性：判断条件
              body：当test属性为true时，标签中的内容会输出
          otherwise标签：
              当test属性为false时，标签中的内容会输出
--%>
<c:choose>
    <c:when test="${param.username == 'admin'}">
        <h1>欢迎管理员</h1>
    </c:when>
    <c:when test="${param.username == 'user'}">
        <h1>欢迎普通用户</h1>
    </c:when>
    <c:otherwise>
        <h1>欢迎游客</h1>
    </c:otherwise>
</c:choose>