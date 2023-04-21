<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/assets/css/modify.css">
</head>
<body>
    <form action = "/board/modified" method = "get" class="card">
        <input type="hidden" name = "boardNo" value = "${b.boardNo}" >
        <div class="card-title-wrapper">
            # 제목: <input name = "title" placeholder="${b.title}" autofocus>        
        </div>
        <div class="card-content">
            <textarea name = "textContent" placeholder="${b.textContent}"></textarea>
            
        </div>
        <button class = "back" onclick="history.back()">back</button>
        <button class = "regist">re-regist</button>
    </form>

</body>
</html>