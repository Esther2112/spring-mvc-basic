<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .infobox {
            padding-left: 20px;
            display: flex;
            justify-content: left;
            flex-wrap: wrap;
            margin: 100px auto;
            width: 400px;
            border: 5px solid;
            border-image:linear-gradient(90deg, #fff429 0%, #ff8a0d 100%);
            border-image-slice: 1;
            background: #fff linear-gradient(90deg, #fff 0%, #fff 100%);
            border-radius: 10px;
            box-shadow: 5px 5px 5px gray;
            position: relative;
        }
        .scoreName {
            width: fit-content;
        }
        .scorebox {
            margin-left: 30px;
            font-size: 20px;
            line-height: 1.7em;
            width: fit-content;
            margin-bottom: 50px;
        }
        .back {
            width: 50px;
            display: flex;
            justify-content: center;
            text-decoration: none;
            position: absolute;
            bottom: 15px;
            left: 20px;
            color: #fff;
            background: yellowgreen;
            padding: 5px 10px;
            border-radius: 10px;
        }
        .modify {
            width: 50px;
            display: flex;
            justify-content: center;
            text-decoration: none;
            position: absolute;
            bottom: 15px;
            left: 100px;
            color: #fff;
            background: yellowgreen;
            padding: 5px 10px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div class = "infobox">
    <h1 class = "scoreName">${score.name}님의 성적 정보</h1>
    <div class="scorebox">
        # 국어: ${score.kor}점<br>
        # 영어: ${score.eng}점<br>
        # 수학: ${score.math}점<br>
        # 총점: ${score.total}점<br>
        # 평균: ${score.average}점<br>
        # 학점: ${score.grade}<br>
    </div>
    <br>
    <a class = "back" href = "/score/list">back</a>
    <a class = "modify" href = "/score/update?stuNum=${score.stuNum}">modify</a>
</div>

</body>
</html>