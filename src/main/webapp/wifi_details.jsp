	<%@page import="db.model.Wifilocate" %>
<%@page import="db.model.Bookmark" %>
<%@page import="db.model.Wifidetail" %>
<%@page import="db.WifiService" %>
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>서울시 공공와이파이 정보 제공 서비스</title>
<style>
    body {
        font-family: 'Arial', sans-serif; /* 기본 폰트 설정 */
        margin: 0;
        padding: 0;
    }
    h1, h2, h3 {
        color: #333; /* 제목 색상 */
    }
    .menu-bar {
        width: 100%;
        background-color: #f2f2f2;
        overflow: hidden;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    }
    .menu-bar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        text-align: center;
    }
    .menu-bar li {
        display: inline;
    }
    .menu-bar a {
        text-decoration: none;
        color: black;
        padding: 15px 20px;
        display: inline-block;
    }
    .menu-bar a:hover {
        background-color: #ddd;
        color: #333;
    }
    table {
        width: 100%; /* 테이블 너비 */
        border-collapse: collapse; /* 테두리 간격 없애기 */
        margin-top: 20px; /* 상단 여백 */
    }
    th, td {
        border: 1px solid #ddd; /* 셀 테두리 */
        padding: 10px; /* 셀 내부 여백 */
        text-align: left; /* 텍스트 정렬 */
    }
    th {
        background-color: #f9f9f9; /* 헤더 배경색 */
        font-weight: bold; /* 헤더 폰트 굵기 */
    }
    button {
        background-color: #4CAF50; /* 버튼 배경색 */
        color: white; /* 버튼 텍스트 색상 */
        border: none;
        padding: 10px 20px;
        cursor: pointer; /* 마우스 오버 시 커서 변경 */
        border-radius: 5px; /* 버튼 둥근 모서리 */
    }
    button:hover {
        background-color: #45a049; /* 마우스 오버 시 버튼 색상 변경 */
    }
    input[type="text"] {
        padding: 8px;
        margin: 5px 0; /* 상하 여백 */
        box-sizing: border-box; /* 박스 크기 계산 방식 */
        border: 1px solid #ccc; /* 테두리 색상 */
        border-radius: 4px; /* 입력 필드 둥근 모서리 */
    }
    select {
        padding: 8px;
        border-radius: 4px;
        margin-bottom: 10px;
    }
</style>
<script>
    function saveWifiToBookmark(wifiId) {
        var bookmarkId = document.getElementById('bookmarkSelect').value;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "CRUDHistory", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("와이파이 정보 저장: " + xhr.responseText);
                window.location.href = 'http://localhost:8080/bookmark.jsp';
            }
        };
        xhr.send("action="+"addWifi"+"&wifiId=" + wifiId + "&bookmarkId=" + encodeURIComponent(bookmarkId));
    }
</script>
</head>
<body>
	<% 
        WifiService ws = new WifiService(); 
        
        String id = request.getParameter("id");
        
        Wifidetail wd = new Wifidetail();
        wd = ws.getDetail(id);
        
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks = ws.getBmList();
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
    <h2>와이파이:<%=wd.getName() %>의 상세정보</h2>
    <% if(bookmarks != null){ %>
    <label for="bookmarkSelect">북마크 선택:</label>
    <select id="bookmarkSelect">
        <% for (Bookmark bm : bookmarks) { %>
            <option value="<%= bm.getName() %>"><%= bm.getName() %></option>
        <% } %>
    </select>
    <button onclick="saveWifiToBookmark('<%= wd.getId() %>')">저장</button>
    <% } else { %>
    <p>등록된 북마크가 없습니다. <a href="bmAdd.jsp"><button>북마크 그룹 이름 추가하기</button></a></p>
    <% } %>
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
            <td>와이파이 이름:</td>
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