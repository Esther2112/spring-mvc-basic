<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1 id = "loginInform"></h1>
<a href="/hw/s-login-form">다시 로그인하기</a>

<script>
    const $loginFlag = document.querySelector("#loginInform");
    if(${idFlag}){
        if(${pwFlag}) {
            $loginFlag.textContent = "로그인 성공";
        }else {
            $loginFlag.textContent = "비밀번호가 틀렸어용";
        }
    }else {
        $loginFlag.textContent = "존재하지 않는 아이디에용";
    }
</script>
</body>
</html>