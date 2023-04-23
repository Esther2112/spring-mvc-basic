<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/assets/css/detail.css">
</head>
<body>
<section class="card">
    <div class="card-title-wrapper">
        <h2 class="card-title">${b.title}</h2>
        <div class="time-view-wrapper">
            <div class="time"><i class="far fa-clock"></i>${b.simpleTime}</div>
            <div class="view">
                <i class="fas fa-eye"></i>
                <span class="view-count">${b.viewCount}</span>
            </div>
        </div>
    </div>
    <div class="card-content">
        <p>
            ${b.textContent}
        </p>
    </div>
    <button class = "back"">back</button>
    <button class = "modify">modify</button>
</section>

<script>
    document.querySelector(".back").onclick = () => {
        window.location.href = "/board/list";
    }
    document.querySelector(".modify").onclick = () => {
        window.location.href = "/board/modify?boardNo=${b.boardNo}";
    }
</script>
</body>
</html>