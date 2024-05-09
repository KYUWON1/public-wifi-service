<%@page import="db.model.Wifilocate" %>
<%@page import="db.model.Wifidetail" %>
<%@page import="db.WifiService" %>
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Insert title here</title>
</head>
<body>
	<% 
		WifiService ws = new WifiService(); 
		
		String id = request.getParameter("id");
		
		Wifidetail wd = new Wifidetail();
		wd = ws.getDetail(id);
	%>
	
	<h1>관리번호:<%=id %>의 상세정보</h1>
    
    <table border="1">
        <tr>
            <td>와이파이 ID:</td>
            <td><%= wd.getId() %></td>
        </tr>
        <tr>
            <td>도시:</td>
            <td><%= wd.getCity() %></td>
        </tr>
        <tr>
            <td>이름:</td>
            <td><%= wd.getName() %></td>
        </tr>
        <tr>
            <td>거리:</td>
            <td><%= wd.getStreet() %></td>
        </tr>
        <tr>
            <td>주소:</td>
            <td><%= wd.getAddress() %></td>
        </tr>
        <tr>
            <td>층:</td>
            <td><%= wd.getFloor() %></td>
        </tr>
        <tr>
            <td>건물 유형:</td>
            <td><%= wd.getBulid_type() %></td>
        </tr>
        <tr>
            <td>건설사:</td>
            <td><%= wd.getBulider() %></td>
        </tr>
        <tr>
            <td>서비스 유형:</td>
            <td><%= wd.getService_type() %></td>
        </tr>
        <tr>
            <td>와이파이 유형:</td>
            <td><%= wd.getWifi_type() %></td>
        </tr>
        <tr>
            <td>설치일:</td>
            <td><%= wd.getSet_date() %></td>
        </tr>
        <tr>
            <td>실내/실외:</td>
            <td><%= wd.getInout_door() %></td>
        </tr>
        <tr>
            <td>와이파이 환경:</td>
            <td><%= wd.getWifi_environ() %></td>
        </tr>
        <tr>
            <td>작성일:</td>
            <td><%= wd.getWork_date() %></td>
        </tr>
    </table>
</body>
</html>