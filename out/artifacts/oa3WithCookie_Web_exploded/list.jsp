<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bjpowernode.oa.bean.Dept" %><%--
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

<body>
<p>欢迎:<%=request.getSession().getAttribute("username")%></p>
<p><a href="<%=request.getContextPath()%>/user/logout">退出</a></p>
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
    <%
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        int i = 0;
        // 循环遍历
        for (Dept dept : deptList) {
    %>
    <tr>
        <td><%=++i%>
        </td>
        <td><%=dept.getDeptNo()%>
        </td>
        <td><%=dept.getDeptName()%>
        </td>
        <td>
            <a href="javascript:void(1)">删除</a>
            <a href="<%=request.getContextPath()%>/dept/detail?f=edit&deptno=<%=dept.getDeptNo()%>">修改</a>
            <a href="<%=request.getContextPath()%>/dept/detail?f=detail&deptno=<%=dept.getDeptNo()%>">详情</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<hr/>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
<script>
    const deptNos = document.querySelectorAll("tr td:nth-child(2)")
    const tds = document.querySelectorAll("tr td:nth-child(4)")
    tds.forEach((td, index) => {
        td.children[0].addEventListener("click", function (e) {
            e.preventDefault()
            if (confirm("确定要删除吗？bro")) {
                window.location.href = '<%=request.getContextPath()%>/dept/delete?deptNo=' + deptNos[index].innerText;
            } else {
                // 用户取消删除，什么都不做
            }
        })
    })
</script>
</body>
</html>