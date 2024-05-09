<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>서울시 공공와이파이 정보 제공 서비스</title>
</head>
<body>
    <h1>서울시 공공와이파이 정보 제공 서비스</h1>
    
    <!-- 와이파이 리스트 가져오기 링크 -->
    <a href="top20wifi.jsp"><h2>가까운 와이파이 리스트 가져오기(20개)</h2></a>
    <!-- LAT(Latitude, 위도)와 LNT(Longitude, 경도) 입력 폼 -->
    <form action="top20wifi.jsp" method="GET">
        <h3>LAT(위도): <input type="text" name="LAT"></h3>
        <h3>LNT(경도): <input type="text" name="LNT"></h3>
        <input type="submit" value="확인">
    </form>
    
    <!-- 히스토리 목록 보기 링크 -->
    <a href="history.jsp"><h2>히스토리 목록 보기</h2></a>
    
    <!-- 히스토리 목록 보기 링크 -->
    <a href="bookmark.jsp"><h2>북마크 목록 보기</h2></a>
    
</body>
</html>
