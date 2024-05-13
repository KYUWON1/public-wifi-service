<%@page import="db.publicAPI" %>
<%@page import="java.util.*" %>
<%@page import="org.json.JSONObject" %>
<%@page import="org.json.JSONArray" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>서울시 공공와이파이 정보 제공 서비스</title>
<script>
// AJAX를 사용해 북마크를 추가하는 함수
    function getWifiData() {
    	var xhr = new XMLHttpRequest();
        xhr.open("GET", "CRUDHistory", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                try {
                    alert("데이터 가져오기 성공!");
                } catch (e) {
                    alert("데이터 처리 중 오류 발생: " + e);
                }
            }
        };
        xhr.send(null);
    }
</script>
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
    <h1>서울시 공공와이파이 정보 제공 서비스</h1>

    <div class="menu-bar">
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="getApi.jsp">OPEN API Wifi 정보 가져오기</a></li>
            <li><a href="top20wifi.jsp">와이파이 리스트 목록</a></li>
            <li><a href="history.jsp">나의 북마크 목록</a></li>
            <li><a href="bookmark.jsp">와이파이 북마크 목록 보기</a></li>
            <li><a href="bmAdd.jsp">북마크 추가하기</a></li>
        </ul>
    </div>
    <button onclick="getWifiData()">와이파이 데이터 가져오기</button>
    <p><%= request.getAttribute("count") %>개의 데이터를 데이터베이스에 저장했습니다.</p>
    <a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>