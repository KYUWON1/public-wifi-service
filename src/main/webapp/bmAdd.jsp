<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>Insert title here</title>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("bookmarkForm").addEventListener("submit", function(event) {
            event.preventDefault(); // 폼 제출 동작을 중지
            
            var bookmarkName = document.getElementById("bookmarkName").value;
            var bookmarkSequence = document.getElementById("bookmarkSequence").value;
            
            // AJAX를 사용하여 데이터를 백엔드로 전달
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "CRUDHistory", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    alert("북마크 추가 결과: " + xhr.responseText);
                    window.location.href = 'http://localhost:8080/history.jsp';
                }
            };
            xhr.send("action="+"addNew"+"&bookmarkName=" + encodeURIComponent(bookmarkName) +
                      "&bookmarkSequence=" + encodeURIComponent(bookmarkSequence));
        });
    });
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
            <li><a href="top20wifi.jsp">와이파이 리스트 목록</a></li>
            <li><a href="history.jsp">나의 북마크 목록</a></li>
            <li><a href="bookmark.jsp">와이파이 북마크 목록 보기</a></li>
            <li><a href="bmAdd.jsp">북마크 추가하기</a></li>
        </ul>
    </div>
<h1>북마크 추가하기</h1>
<form id="bookmarkForm" action="submitBookmark" method="post">
    <label for="bookmarkName">북마크 이름:</label>
    <input type="text" id="bookmarkName" name="bookmarkName" required><br>
    <label for="bookmarkSequence">북마크 순서:</label>
    <input type="number" id="bookmarkSequence" name="bookmarkSequence" required><br>
    <button type="submit">추가하기</button>
</form>
</body>
</html>
