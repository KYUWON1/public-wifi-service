<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����� ������������ ���� ���� ����</title>
</head>
<body>
    <h1>����� ������������ ���� ���� ����</h1>
    
    <!-- �������� ����Ʈ �������� ��ũ -->
    <a href="top20wifi.jsp"><h2>����� �������� ����Ʈ ��������(20��)</h2></a>
    <!-- LAT(Latitude, ����)�� LNT(Longitude, �浵) �Է� �� -->
    <form action="top20wifi.jsp" method="GET">
        <h3>LAT(����): <input type="text" name="LAT"></h3>
        <h3>LNT(�浵): <input type="text" name="LNT"></h3>
        <input type="submit" value="Ȯ��">
    </form>
    
    <!-- �����丮 ��� ���� ��ũ -->
    <a href="history.jsp"><h2>�����丮 ��� ����</h2></a>
    
    <!-- �����丮 ��� ���� ��ũ -->
    <a href="bookmark.jsp"><h2>�ϸ�ũ ��� ����</h2></a>
    
</body>
</html>
