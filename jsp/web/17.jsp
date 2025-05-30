<%@ page import="com.bjpowernode.javaweb.jsp.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--

 JSTL标签的用处就是：让JSP中的Java代码消失

    引包说明：
        Java EE 9+规范只需要引入jakarta.servlet.jsp.jstl.jar和jakarta.servlet.jsp.jstl-api.jar两个即可
        而非Java EE 9-规范需要引入jakarta.servlet.jsp.jstl-api.jar和taglibs-standard-impl和taglibs-standard-spec.jar三个
 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入标签库，这里引入的是jstl的核心标签库--%>
<%--
 prefix: 定义标签库的前缀，用于在页面中调用标签时区分不同库的标签。
    用途：避免不同标签库的标签名冲突。
 uri: 唯一标识标签库的地址（逻辑标识，非真实 URL），用于定位库的 TLD 文件。
    用途：告诉 JSP 容器到哪里查找标签库的定义文件（TLD）。
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

<%-- 将List集合中的元素遍历，输出学生信息到浏览器--%>
<%
    // 从request域中获取List集合
    List<Student> stus = (ArrayList<Student>) request.getAttribute("stuList");
    // 编写for循环遍历集合
    for (Student stu : stus) {
%>
id: <%=stu.getId()%>、name: <%=stu.getName()%> <br>
<%
    }
%>

<hr>
<%-- 使用core标签库中forEach标签。对List集合进行遍历--%>
<%--
    items: 要遍历的集合
    var: 集合中的每一个元素
    begin: 遍历的起始位置
    end: 遍历的结束位置
    step: 遍历的步长
--%>
<c:forEach items="${stuList}" var="stu">
    id: ${stu.id}、name: ${stu.name} <br>
</c:forEach>