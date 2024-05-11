<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>서울시 공공와이파이 정보 제공 서비스</title>
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
<script>
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
    
    function successFunction(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;
        console.log("Latitude: " + lat + ", Longitude: " + lng);
    }
    
    function errorFunction(error) {
        switch(error.code) {
            case error.PERMISSION_DENIED:
                console.log("User denied the request for Geolocation.");
                break;
            case error.POSITION_UNAVAILABLE:
                console.log("Location information is unavailable.");
                break;
            case error.TIMEOUT:
                console.log("The request to get user location timed out.");
                break;
            case error.UNKNOWN_ERROR:
                console.log("An unknown error occurred.");
                break;
        }
    }

    function fillLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                document.getElementById('latInput').value = position.coords.latitude;
                document.getElementById('lngInput').value = position.coords.longitude;
            }, function(error) {
                alert("위치 정보를 가져오는데 실패했습니다. 에러 코드: " + error.code);
            });
        } else {
            alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
        }
    }
</script>
</head>
<body>
    <h1>서울시 공공와이파이 정보 제공 서비스</h1>

    <div class="menu-bar">
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="top20wifi.jsp">가까운 와이파이 리스트 목록</a></li>
            <li><a href="history.jsp">위치 히스토리 목록</a></li>
            <li><a href="bookmark.jsp">북마크 목록 보기</a></li>
            <li><a href="bmAdd.jsp">북마크 추가하기</a></li>
        </ul>
    </div>
    
    <form action="top20wifi.jsp" method="GET">
    	<h3>찾고싶은 위치의 좌표를 입력하세요</h3>
        <h3>LAT(위도): <input type="text" id="latInput" name="LAT"></h3>
        <h3>LNT(경도): <input type="text" id="lngInput" name="LNT"></h3>
        <button type="button" onclick="fillLocation()">현재 위치 사용</button>
        <input type="submit" value="확인">
    </form>
</body>
</html>