<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: window
  Date: 2023-07-16
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String temp = request.getParameter("cnt");
    int cnt = 100;
    if(temp != null && !temp.equals("")){
        cnt = Integer.parseInt(temp);
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        for (int i = 0; i < cnt; i++) {
    %>
    안녕 Servlet!!<br >
    <%
        }
    %>
</body>
</html>
