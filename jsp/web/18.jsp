<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/29
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 引入核心标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--核心标签库中的if--%>
<%--
  test: 判断条件
      true: 条件为true时，标签中的内容会输出
      false: 条件为false时，标签中的内容不会输出
  var: 保存判断的结果
  scope: 保存结果var的作用域:  page, request, session, application
--%>
<c:if test="${!empty param.username}" var="v" scope="page">
    <h1>欢迎${param.username}</h1>
</c:if>
<%--将page域中的v取出--%>
${v}