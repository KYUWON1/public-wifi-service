<%@page import="db.model.BmWifi" %>
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
    body {
        margin: 0;
        padding: 0;
    }
    table {
        width: 100%; /* 테이블이 화면 전체 너비를 차지하도록 설정 */
        border-collapse: collapse; /* 테두리 간격 없애기 */
        table-layout: fixed; /* 테이블 내 셀 너비 고정 */
    }
    th, td {
        border: 1px solid black; /* 셀 테두리 */
        padding: 8px; /* 내부 여백 */
        text-align: left; /* 텍스트 정렬 */
        word-wrap: break-word; /* 긴 텍스트 줄바꿈 */
    }
    th {
        background-color: #f2f2f2; /* 헤더 배경색 */
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
    	console.log("Inserting bookmark for ID:", id);  // 올바른 로깅 메소드 사용
        var xhr = new XMLHttpRequest();
    	//delete 요청시에는 보통 쿼리값으로 파라메터를 넘겨준다.
        xhr.open("DELETE","CRUDHistory?id=" + encodeURIComponent(id)+"&action="+"deleteOne", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("북마크 제거: " + xhr.responseText);
                location.reload(); // 성공적으로 삭제한 후 페이지를 새로고침
            }
        };
        xhr.send(null);
    }
</script>
	<% 
		WifiService ws = new WifiService(); 
		
		List<BmWifi> result = new ArrayList<>();
		result = ws.getWifitoBm();
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
	<h1>와이파이 북마크 리스트</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>북마크 이름</th>
                <th>와이파이명</th>
                <th>등록일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <% if(result != null) { %>
        <tbody>
            <% for (BmWifi wifi : result) { %>
                <tr>
                    <td><%= wifi.getId() %></td>
                    <td><%= wifi.getBmName() %></td>
                    <td><a href="wifi_details.jsp?id=<%= wifi.getWifiId() %>"><%= wifi.getWifiName() %></a></td>
                    <td><%= wifi.getReg_date() %></td>
                    <td><button onclick="deleteBookmark('<%= wifi.getWifiId() %>')">북마크 제거</button></td>
                </tr>
            <% } %>
        </tbody>
        <% }else { %>
        <tbody>
            <tr>
                <td colspan="5">정보가 존재하지 않습니다.</td>
            </tr>
        </tbody>
        <% } %>
    </table>
</body>
</html>