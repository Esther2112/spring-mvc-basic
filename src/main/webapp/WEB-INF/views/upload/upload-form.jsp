<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .upload-box {
            width: 150px;
            height: 150px;
            border: 3px dashed orange;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #img-input {
            display: none;
        }
    </style>
</head>
<body>

<h1>파일 업로드 예제</h1>

<div class = "upload-box">파일첨부</div>
<form action="/upload-file" method="post" enctype="multipart/form-data">
    <input id = "img-input" type="file" name = "thumbnail" accept="image/*" multiple>
    <button type = "submit">전송</button>
</form>

<script>
    const $box = document.querySelector('.upload-box');
    const $input = document.getElementById('img-input');

    $box.onclick = e => {
        $input.click();
    }

</script>
</body>
</html>