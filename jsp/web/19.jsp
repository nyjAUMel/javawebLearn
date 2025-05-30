<%@ page import="com.bjpowernode.javaweb.jsp.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/30
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
  循环标签
  var: 循环变量
  begin: 循环的起始位置
  end: 循环的结束位置
  step: 步长
--%>
<%--实际上，底层会将i存储到pageContext域当中--%>
<c:forEach var="i" begin="1" end="10" step="1">
    <%--所以这里才会使用EL表达式将其取出。EL表达式一定是从某个域当中取出--%>
    ${i}<br>
</c:forEach>

<%
    // 创建List集合
    List<Student> stuList = new ArrayList<>();
    // 创建Student对象
    Student s1 = new Student("1", "丁程鑫");
    Student s2 = new Student("2", "马嘉祺");
    Student s3 = new Student("3", "宋亚轩");
    // 添加到List集合中
    stuList.add(s1);
    stuList.add(s2);
    stuList.add(s3);
    // 将list集合保存到request域中
    request.setAttribute("stuList", stuList);
%>

<%--stu代表集合中的每个对象--%>
<%--
  varStatus: 循环变量的状态对象
    该对象下的属性：
      index: 索引，从0开始
      count: 索引，从1开始
      first: 是否是第一个元素
      last: 是否是最后一个元素
      current: 当前元素
--%>
<c:forEach var="stu" items="${stuList}" varStatus="abc">
    编号:${abc.count}：属性id: ${stu.id}、name: ${stu.name} <br>
</c:forEach>