<%--
  Created by IntelliJ IDEA.
  User: window
  Date: 2023-07-16
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
pageContext.setAttribute("aa","aa");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%=request.getAttribute("result")%> 입니다.
    ${result} 입니다.<br>
    ${names[2]}<br>
    ${notice.title} <br>
    ${header.cookie} <br>
    ${header.accept} <br>
    ${aa} <br>
    ${pageContext.request.method} <br>
    ${param.n ge 3} <br>
    ${empty param.n?'값이 비어 있습니다.':param.n} <br>
    ${param.n/2} <br>

</body>
</html>
