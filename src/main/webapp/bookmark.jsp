<%@page import="db.model.Wifibm" %>
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
    function getBookmarkList(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "insertBookmark", true);
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
		
		List<Wifibm> result = new ArrayList<>();
		result = ws.getBm();
	%>
	
	<h1>와이파이 북마크 리스트</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>와이파이 ID</th>
                <th>와이파이 이름</th>
                <th>도시</th>
                <th>도로명</th>
                <th>주소</th>
                <th>LAT</th>
                <th>LNT</th>
            </tr>
        </thead>
        <tbody>
            <% for (Wifibm wifi : result) { %>
                <tr>
                    <td><a href="wifi_details.jsp?id=<%= wifi.getId() %>"><%= wifi.getId() %></a></td>
                    <td><%= wifi.getName() %></td>
                    <td><%= wifi.getCity() %></td>
                    <td><%= wifi.getStreet() %></td>
                    <td><%= wifi.getAddress() %></td>
                    <td><%= wifi.getLat() %></td>
                    <td><%= wifi.getLnt() %></td>
                    <td><button onclick="insertBookmark('<%= wifi.getId() %>')">북마크 추가</button></td>
                </tr>
            <% } %>
        </tbody>
        
    </table>
</body>
</html>