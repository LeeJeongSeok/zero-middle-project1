<%--
  Created by IntelliJ IDEA.
  User: jeongseok
  Date: 2022/12/07
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int totalCount = (int) request.getAttribute("totalCount");
    %>

    <div style="text-align:center"><h1><%=totalCount%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1></div>
    <div style="text-align:center"><a href="index.jsp"> 홈으로 가기</a></div>

</body>
</html>
