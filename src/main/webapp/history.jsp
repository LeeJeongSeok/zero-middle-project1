<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lee.zeromiddleproject1.dto.HistoryDto" %><%--
  Created by IntelliJ IDEA.
  User: jeongseok
  Date: 2022/12/07
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link href="${path}/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <h1><%= "위치 히스토리 목록" %></h1>

    <a href="index.jsp">홈 | </a>
    <a href="history-servlet">위치 히스토리 목록 | </a>
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a>

    <table border="1">
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
            <%
                ArrayList<HistoryDto> histories = (ArrayList<HistoryDto>) request.getAttribute("histories");
                for (int i = 0; i < histories.size(); i++) {
                    HistoryDto historyDto = histories.get(i);
            %>
            <tr>
                <td><%=historyDto.getId()%></td>
                <td><%=historyDto.getLat()%></td>
                <td><%=historyDto.getLnt()%></td>
                <td><%=historyDto.getSearch_date()%></td>
                <td>
                    <form action="/deleteHistoryServlet" method="get">
                        <input type="hidden" name="id" value="<%=historyDto.getId()%>">
                        <input type="submit" value="삭제">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
    </table>
</body>
</html>
