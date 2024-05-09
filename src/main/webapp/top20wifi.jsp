<%@page import="db.model.Wifilocate" %>
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
<script>
    // AJAX를 사용해 북마크를 추가하는 함수
    function insertBookmark(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "insertBookmark", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("북마크 추가: " + xhr.responseText);
            }
        };
        xhr.send("id=" + id);
    }
</script>
	<% 
		WifiService ws = new WifiService(); 
		
		String lat = request.getParameter("LAT");
		String lnt = request.getParameter("LNT");
		
		List<Wifilocate> result = new ArrayList<>();
		result = ws.gettop20(lat, lnt);
	%>
	
	<h1>가까운 와이파이 리스트 (Top 20)</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>와이파이 ID</th>
                <th>와이파이 이름</th>
                <th>LAT</th>
                <th>LNT</th>
                <th>북마크 추가</th>
            </tr>
        </thead>
        <tbody>
            <% for (Wifilocate wifi : result) { %>
                <tr>
                    <td><a href="wifi_details.jsp?id=<%= wifi.getId() %>"><%= wifi.getId() %></a></td>
                    <td><%= wifi.getName() %></td>
                    <td><%= wifi.getLat() %></td>
                    <td><%= wifi.getLnt() %></td>
                    <td><button onclick="insertBookmark('<%= wifi.getId() %>')">북마크 추가</button></td>
                </tr>
            <% } %>
        </tbody>
        
    </table>
   
    <button onclick=<% ws.savehistory(result); %>>히스토리에 저장하기</button>
</body>
</html>