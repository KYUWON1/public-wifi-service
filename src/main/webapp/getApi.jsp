<%@page import="db.publicAPI" %>
<%@page import="java.util.*" %>
<%@page import="org.json.JSONObject" %>
<%@page import="org.json.JSONArray" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>����� ������������ ���� ���� ����</title>
<script>
// AJAX�� ����� �ϸ�ũ�� �߰��ϴ� �Լ�
    function getWifiData() {
    	var xhr = new XMLHttpRequest();
        xhr.open("GET", "CRUDHistory", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                try {
                    alert("������ �������� ����!");
                } catch (e) {
                    alert("������ ó�� �� ���� �߻�: " + e);
                }
            }
        };
        xhr.send(null);
    }
</script>
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
</head>
<body>
    <h1>����� ������������ ���� ���� ����</h1>

    <div class="menu-bar">
        <ul>
            <li><a href="index.jsp">Ȩ</a></li>
            <li><a href="getApi.jsp">OPEN API Wifi ���� ��������</a></li>
            <li><a href="top20wifi.jsp">�������� ����Ʈ ���</a></li>
            <li><a href="history.jsp">���� �ϸ�ũ ���</a></li>
            <li><a href="bookmark.jsp">�������� �ϸ�ũ ��� ����</a></li>
            <li><a href="bmAdd.jsp">�ϸ�ũ �߰��ϱ�</a></li>
        </ul>
    </div>
    <button onclick="getWifiData()">�������� ������ ��������</button>
    <p><%= request.getAttribute("count") %>���� �����͸� �����ͺ��̽��� �����߽��ϴ�.</p>
    <a href="index.jsp">Ȩ���� ���ư���</a>
</body>
</html>