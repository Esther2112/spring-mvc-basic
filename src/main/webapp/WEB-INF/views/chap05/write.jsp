<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/assets/css/write.css">
</head>
<body>
<form action = "/board/regist" method = "post" class="card">
    <div class="card-title-wrapper">
        # 제목: <input name = "title" placeholder="제목을 입력하세요" autofocus>
    </div>
    <div class="card-content">
        <textarea name = "textContent"></textarea>

    </div>
    <button class = "back" onclick="history.back()">back</button>
    <button class = "regist">regist</button>
</form>

</script>
</body>
</html>