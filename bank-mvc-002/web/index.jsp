<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/6/2
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>转账</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/bank/transfer" method="post">
    转出账户:<input type="text" name="outAccount"><br>
    转入账户:<input type="text" name="inAccount"><br>
    转账金额:<input type="text" name="money"><br>
    <input type="submit" value="转账">
</form>
</body>
</html>
