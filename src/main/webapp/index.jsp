<%@ page import="com.lee.zeromiddleproject1.dto.WifiDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="${path}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<h1><%= "와이파이 정보 구하기" %>
</h1>
<br/>
    <a href="index.jsp">홈 | </a>
    <a href="history-servlet">위치 히스토리 목록 | </a>
    <a href="load-wifi">Open API 와이파이 정보 가져오기</a>


    <form action="/getWifiInfo" method="get" id="getWifiInfo" onsubmit="return validateForm()">
        LAT : <input type="text" id="lat" name="lat">,
        LNT : <input type="text" id="lnt" name="lnt">
        <input type="button" value="내 위치 가져오기" onclick="getLocation()"/>
        <input type="submit" value="근처 WIFI 정보 가져오기"/>
    </form>

    <br>
        <table border="1">
            <th>거리</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI 접속 환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
            <%
                if (request.getAttribute("wifiList") == null) {
            %>
                <tr>
                    <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
                </tr>
            <%
                } else {
            %>
            <%
                ArrayList<WifiDto> wifiList = (ArrayList<WifiDto>) request.getAttribute("wifiList");
                    for (int i = 0; i < wifiList.size(); i++) {
                        WifiDto wifiDto = wifiList.get(i);
            %>
            <tr>
                <td><%=Math.round(wifiDto.getDistance() * 10000) / 10000.000%></td>
                <td><%=wifiDto.getMgr_no()%></td>
                <td><%=wifiDto.getWrdofc()%></td>
                <td><%=wifiDto.getMain_nm()%></td>
                <td><%=wifiDto.getAdres1()%></td>
                <td><%=wifiDto.getAdres2()%></td>
                <td><%=wifiDto.getInstl_floor()%></td>
                <td><%=wifiDto.getInstl_ty()%></td>
                <td><%=wifiDto.getInstl_mby()%></td>
                <td><%=wifiDto.getSvc_se()%></td>
                <td><%=wifiDto.getCmcwr()%></td>
                <td><%=wifiDto.getCnstc_year()%></td>
                <td><%=wifiDto.getInout_door()%></td>
                <td><%=wifiDto.getRemars3()%></td>
                <td><%=wifiDto.getLat()%></td>
                <td><%=wifiDto.getLnt()%></td>
                <td><%=wifiDto.getWork_dttm()%></td>
            </tr>
            <%
                    }
            %>
            <%
                }
            %>
        </table>
    <script>
        function getLocation() {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    document.getElementById("lat").value = position.coords.latitude;
                    document.getElementById("lnt").value = position.coords.longitude;
                }
            )
        }

        function validateForm() {
            let lat = document.getElementById("lat").value;
            let lnt = document.getElementById("lnt").value;
            if (lat == "" || lnt == "") {
                alert("좌표 값을 입력해주세요.");
                return false;
            }
        }
    </script>
</body>
</html>