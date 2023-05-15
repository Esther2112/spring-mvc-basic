<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" href="/assets/css/list.css">
<%@ include file="../include/static-head.jsp" %>

</head>

<body>

<%@ include file="../include/header.jsp" %>
<div id="wrap">

    <div class="main-title-wrapper">
        <h1 class="main-title">꾸러기 게시판</h1>
        <c:if test = "${login != null}">
            <button class="add-btn">새 글 쓰기</button>
        </c:if>
    </div>

    <div class="top-section">
        <!-- 검색창 영역 -->
        <div class="search">
            <form action="/board/list" method="get">

                <select class="form-select" name="type" id="search-type">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="writer">작성자</option>
                    <option value="tc">제목+내용</option>
                </select>

                <input type="text" class="form-control" name="keyword" value="${s.keyword}">

                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i>
                </button>

            </form>
        </div>
    </div>

    <div class="card-container">
        <c:forEach var="b" items="${bList}">
            <div class="card-wrapper">
                <a href="/board/detail?boardNo=${b.boardNo}&pageNo=${s.pageNo}&type=${s.type}&keyword=${s.keyword}">
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
                    </section>
                </a>
                <c:if test = "${b.account == login.account}">
                <form action="/board/delete?" method="get" class="card-btn-group">
                    <button class="del-btn">
                        <input class="hidden" type="hidden" name="boardNo" value= ${b.boardNo}>
                        <i class="fas fa-times"></i>
                    </button>
                </form>
                </c:if>
            </div>
        </c:forEach>

    </div>

    <!-- 게시글 목록 하단 영역 -->
    <div class="bottom-section">

        <!-- 페이지 버튼 영역 -->
        <nav aria-label="Page navigation example">
            <ul class="pagination pagination-lg pagination-custom">

                <li class="page-item"><a class="page-link" href="/board/list?pageNo=1">&lt;&lt;</a></li>
                <c:if test = "${maker.prev}">
                <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.begin - 1}">prev</a></li>
                </c:if>
                <c:forEach var = "i" begin="${maker.begin}" end = "${maker.end}">
                <li data-page-num="${i}" class="page-item">
                    <a class="page-link" href="/board/list?pageNo=${i}&type=${s.type}&keyword=${s.keyword}">${i}</a>
                </li>
                </c:forEach>
                <c:if test = "${maker.next}">
                <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.end + 1}">next</a></li>
                </c:if>
                <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.getRealEnd()}">&gt;&gt;</a></li>
            </ul>
        </nav>

    </div>

</div>


<script>
    function removeDown(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.removeAttribute('id', 'card-down');
    }

    function removeHover(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.remove('card-hover');

        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '0';
    }

    const $cardContainer = document.querySelector('.card-container');

    $cardContainer.onmouseover = e => {

        if (!e.target.matches('.card-container *')) return;

        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.add('card-hover');

        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '1';
    }

    $cardContainer.onmousedown = e => {

        if (!e.target.matches('.card-container *')) return;

        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.setAttribute('id', 'card-down');
    };

    $cardContainer.onmouseup = removeDown;

    $cardContainer.addEventListener('mouseout', removeDown);
    $cardContainer.addEventListener('mouseout', removeHover);

    // write button event
    document.querySelector('.add-btn').onclick = () => {
        window.location.href = '/board/write';
    };

    //삭제하기
    // document.querySelector('.del-btn').onclick = () => {
    //     let bno = document.querySelector(".hidden").value;
    //     window.location.href = '/board/delete?boardNo=' +bno;
    // };


    //현재 위치한 페이지에 active 스타일 부여하기
    function appendPageActive() {

        // 현재 내가 보고 있는 페이지 넘버
        const curPageNum = '${maker.page.pageNo}';
        // console.log("현재페이지: ", curPageNum);

        // 페이지 li태그들을 전부 확인해서
        // 현재 위치한 페이지 넘버와 텍스트컨텐츠가 일치하는
        // li를 찾아서 class active 부여
        const $ul = document.querySelector('.pagination');

        for (let $li of [...$ul.children]) {
            if (curPageNum === $li.dataset.pageNum) {
                $li.classList.add('active');
                break;
            }
        }

    }

    // 셀렉트옵션 검색타입 태그 고정
    function fixSearchOption() {
        const $select = document.getElementById('search-type');

        for (let $opt of [...$select.children]) {
            if ($opt.value === '${s.type}') {
                $opt.setAttribute('selected', 'selected');
                break;
            }
        }
    }

    appendPageActive();

</script>

</body>

</html>