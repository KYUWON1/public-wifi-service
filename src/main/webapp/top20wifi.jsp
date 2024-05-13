<%@page import="db.model.Wifilocate" %>
<%@page import="db.WifiService" %>
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Insert title here</title>
<style>
    /* 메뉴바 스타일 */
    .menu-bar {
        width: 100%;
        background-color: #f2f2f2; /* 메뉴바 배경색 */
        overflow: hidden;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* 그림자 효과 */
    }
    .menu-bar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        text-align: center;
    }
    .menu-bar li {
        display: inline; /* 항목들을 가로로 나열 */
    }
    .menu-bar a {
        text-decoration: none;
        color: black; /* 링크 텍스트 색상 */
        padding: 15px 20px; /* 메뉴 항목 내부 여백 */
        display: inline-block;
    }
    .menu-bar a:hover {
        background-color: #ddd; /* 마우스 오버 시 배경색 변경 */
        color: #333; /* 마우스 오버 시 텍스트 색상 변경 */
    }
</style>
</head>
<body>
	<% 
		WifiService ws = new WifiService(); 
		
		String lat = request.getParameter("LAT");
		String lnt = request.getParameter("LNT");
		
		List<Wifilocate> result = new ArrayList<>();
		result = ws.gettop20(lat, lnt);
	%>
	<h1>서울시 공공와이파이 정보 제공 서비스</h1>
    <div class="menu-bar">
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="top20wifi.jsp">와이파이 리스트 목록</a></li>
            <li><a href="history.jsp">나의 북마크 목록</a></li>
            <li><a href="bookmark.jsp">와이파이 북마크 목록 보기</a></li>
            <li><a href="bmAdd.jsp">북마크 추가하기</a></li>
        </ul>
    </div>
	<h1>가까운 와이파이 리스트 (Top 20)</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>와이파이 ID</th>
                <th>와이파이 이름</th>
                <th>LAT</th>
                <th>LNT</th>
            </tr>
        </thead>
        <tbody>
            <% for (Wifilocate wifi : result) { %>
                <tr>
                    <td><%= wifi.getId() %></td>
                    <td><a href="wifi_details.jsp?id=<%= wifi.getId() %>"><%= wifi.getName() %></a></td>
                    <td><%= wifi.getLat() %></td>
                    <td><%= wifi.getLnt() %></td>
                </tr>
            <% } %>
        </tbody>
        
    </table>
</body>
</html>