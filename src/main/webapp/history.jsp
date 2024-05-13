<%@page import="db.model.Wifilocate" %>
<%@page import="db.model.Bookmark" %>
<%@page import="db.WifiService" %>
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Insert title here</title>
</head>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
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
<body>
<script>
    // AJAX를 사용해 북마크를 추가하는 함수
    function deleteBookmark(id) {
        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", "CRUDHistory?id="+encodeURIComponent(id)+"&action="+"deleteList", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("북마크 추가: " + xhr.responseText);
                location.reload(); // 성공적으로 삭제한 후 페이지를 새로고침
            }
        };
        xhr.send(null);
    }
    function updateBookmark(id) {
    	var fixName = document.getElementById('name'+id).value;
    	console.log(fixName);
    	
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "CRUDHistory", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("북마크 추가: " + xhr.responseText);
                location.reload(); // 성공적으로 삭제한 후 페이지를 새로고침
            }
        };
        xhr.send("action="+"update"+"&id="+encodeURIComponent(id) + "&name="+encodeURIComponent(fixName));
    }
</script>
	<% 
		WifiService ws = new WifiService(); 
		
		List<Bookmark> bmList = new ArrayList<>();
		
		bmList = ws.getBmList();
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
	<h1>와이파이 북마크 목록</h1>
    <a href="bmAdd.jsp"><button>북마크 그룹 이름 추가하기</button></a>
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>북마크 이름</th>
                <th>번호</th>
                <th>생성 일자</th>
                <th>수정 일자</th>
                <th>비고</th>
            </tr>
        </thead>
        
        <% if (bmList != null) { %>
        	<tbody>
            <% for (Bookmark wifi : bmList) { %>
                <tr>
                    <td><%= wifi.getId() %></td>
                    <td>
		                <input type="text" id="name<%= wifi.getId() %>" value="<%= wifi.getName() %>" />
		            </td>
                    <td><%= wifi.getSequence() %></td>
                    <td><%= wifi.getReg_date() %></td>
                    <td><%= wifi.getMod_date() %></td>
                    <td>
                    	<button onclick="updateBookmark('<%= wifi.getId() %>')">수정</button>
                    	<button onclick="deleteBookmark('<%= wifi.getId() %>')">삭제</button>
                    </td>
                </tr>
            <% } %>
            </tbody>
        <% } else {%>
        <tbody>
            <tr>
                <td colspan="6">정보가 존재하지 않습니다.</td>
            </tr>
        </tbody>
        <% } %>
        
        
    </table>
   
</body>
</html>