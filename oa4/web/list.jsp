<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bjpowernode.oa.bean.Dept" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niuyujie
  Date: 2025/5/16
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门列表页面</title>
</head>

<%--
    base标签
    作用：
        为页面上所有相对路径提供基准URL(也就是不以斜杠开头的路径)
--%>
<%--
    pageContext.request.scheme:当前请求的协议
    pageContext.request.serverName:当前服务器的名称
    pageContext.request.serverPort:当前服务器的端口号
    pageContext.request.contextPath:当前项目的访问路径
--%>
<%--${pageContext.request.scheme} 输出 http --%>
<%--${pageContext.request.serverName} 输出 localhost --%>
<%--${pageContext.request.serverPort} 输出 8080 --%>
<%--${pageContext.request.contextPath} 输出 /oa4--%>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<body>
<p>欢迎:${sessionScope.username}</p>
<%--<p><a href="${pageContext.request.contextPath}/user/logout">退出</a></p>--%>
<p><a href="user/logout">退出</a></p>
<h1 style="text-align: center;">部门列表</h1>
<hr/>
<table border="1" align="center" width="50%">
    <thead>
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${deptList}" var="d" varStatus="vStatus">
        <tr>
            <td>${vStatus.count}</td>
            <td>${d.deptNo}</td>
            <td>${d.deptName}</td>
            <td> <a href="javascript:void(1)">删除</a>
                <a href="${pageContext.request.contextPath}/dept/detail?f=edit&deptno=${d.deptNo}">修改</a>
                <a href="${pageContext.request.contextPath}/dept/detail?f=detail&deptno=${d.deptNo}">详情</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<hr/>
<%--因为上面使用了base标签--%>
<%--<a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>--%>
<%--所以可以改成--%>
<a href="add.jsp">新增部门</a>
<script>
    const deptNos = document.querySelectorAll("tr td:nth-child(2)")
    const tds = document.querySelectorAll("tr td:nth-child(4)")
    tds.forEach((td, index) => {
        td.children[0].addEventListener("click", function (e) {
            e.preventDefault()
            if (confirm("确定要删除吗？bro")) {
                // HTML的base标签对JS不一定起作用
                window.location.href = '${pageContext.request.contextPath}/dept/delete?deptNo=' + deptNos[index].innerText;
            } else {
                // 用户取消删除，什么都不做
            }
        })
    })
</script>
</body>
</html>