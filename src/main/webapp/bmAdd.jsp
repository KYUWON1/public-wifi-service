<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("bookmarkForm").addEventListener("submit", function(event) {
            event.preventDefault(); // �� ���� ������ ����
            
            var bookmarkName = document.getElementById("bookmarkName").value;
            var bookmarkSequence = document.getElementById("bookmarkSequence").value;
            
            // AJAX�� ����Ͽ� �����͸� �鿣��� ����
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "CRUDHistory", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    alert("�ϸ�ũ �߰� ���: " + xhr.responseText);
                    window.location.href = 'http://localhost:8080/history.jsp';
                }
            };
            xhr.send("action="+"addNew"+"&bookmarkName=" + encodeURIComponent(bookmarkName) +
                      "&bookmarkSequence=" + encodeURIComponent(bookmarkSequence));
        });
    });
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
            <li><a href="index.jsp">OPEN API Wifi ���� ��������</a></li>
            <li><a href="top20wifi.jsp">�������� ����Ʈ ���</a></li>
            <li><a href="history.jsp">���� �ϸ�ũ ���</a></li>
            <li><a href="bookmark.jsp">�������� �ϸ�ũ ��� ����</a></li>
            <li><a href="bmAdd.jsp">�ϸ�ũ �߰��ϱ�</a></li>
        </ul>
    </div>
<h1>�ϸ�ũ �߰��ϱ�</h1>
<form id="bookmarkForm" action="submitBookmark" method="post">
    <label for="bookmarkName">�ϸ�ũ �̸�:</label>
    <input type="text" id="bookmarkName" name="bookmarkName" required><br>
    <label for="bookmarkSequence">�ϸ�ũ ����:</label>
    <input type="number" id="bookmarkSequence" name="bookmarkSequence" required><br>
    <button type="submit">�߰��ϱ�</button>
</form>
</body>
</html>
