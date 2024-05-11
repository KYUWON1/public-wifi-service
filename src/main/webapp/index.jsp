<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����� ������������ ���� ���� ����</title>
<style>
    /* �޴��� ��Ÿ�� */
    .menu-bar {
        width: 100%;
        background-color: #f2f2f2; /* �޴��� ���� */
        overflow: hidden;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* �׸��� ȿ�� */
    }
    .menu-bar ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        text-align: center;
    }
    .menu-bar li {
        display: inline; /* �׸���� ���η� ���� */
    }
    .menu-bar a {
        text-decoration: none;
        color: black; /* ��ũ �ؽ�Ʈ ���� */
        padding: 15px 20px; /* �޴� �׸� ���� ���� */
        display: inline-block;
    }
    .menu-bar a:hover {
        background-color: #ddd; /* ���콺 ���� �� ���� ���� */
        color: #333; /* ���콺 ���� �� �ؽ�Ʈ ���� ���� */
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
                alert("��ġ ������ �������µ� �����߽��ϴ�. ���� �ڵ�: " + error.code);
            });
        } else {
            alert("�� ������������ Geolocation�� �������� �ʽ��ϴ�.");
        }
    }
</script>
</head>
<body>
    <h1>����� ������������ ���� ���� ����</h1>

    <div class="menu-bar">
        <ul>
            <li><a href="index.jsp">Ȩ</a></li>
            <li><a href="top20wifi.jsp">����� �������� ����Ʈ ���</a></li>
            <li><a href="history.jsp">��ġ �����丮 ���</a></li>
            <li><a href="bookmark.jsp">�ϸ�ũ ��� ����</a></li>
            <li><a href="bmAdd.jsp">�ϸ�ũ �߰��ϱ�</a></li>
        </ul>
    </div>
    
    <form action="top20wifi.jsp" method="GET">
    	<h3>ã����� ��ġ�� ��ǥ�� �Է��ϼ���</h3>
        <h3>LAT(����): <input type="text" id="latInput" name="LAT"></h3>
        <h3>LNT(�浵): <input type="text" id="lngInput" name="LNT"></h3>
        <button type="button" onclick="fillLocation()">���� ��ġ ���</button>
        <input type="submit" value="Ȯ��">
    </form>
</body>
</html>