<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.boardDomain.title}</title>
    <link rel="stylesheet" href="/res/css/detail.css">
    <link rel="stylesheet" href="/res/css/common.css">
    <script defer src="/res/js/detail.js"></script>
    <script defer src="/res/js/common.js"></script>
</head>
<body>
<div><a href="#" onclick="goBack();">돌아가기</a></div>
<div>글번호 : ${requestScope.boardDomain.iboard}</div>
<div>작성자 : <c:out value="${requestScope.boardDomain.writerNm}"/> | 작성일 : ${requestScope.boardDomain.regdt}</div>
<div><c:out value="${requestScope.boardDomain.ctnt}"/></div>
<div>${requestScope.boardDomain.ctnt}</div>

<c:if test="${not empty sessionScope.loginUser}">
    <div>
        <form id="cmtFrm" onsubmit="return false;" >
            <!--action="#"--이 폼을 날릴 방법이 없음-->
            <input type="text" id="cmt" placeholder="댓글" value="">
            <input type="button" value="댓글달기" onclick="regCmt();">
        </form>
    </div>
</c:if>
<div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}"
     data-iboard="${param.iboard}">
    <table>
        <tr>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>비고</th>
        </tr>
    </table>
</div>
<!--innerHTML / innerText / append() / appendChild() -->
<!--dataset이라고 부름,data-login-user-pk 로 넣으면 js에서 loginUserPk로 받으면 된대요-->

<div id="modal" class="displayNone">
    <div class="modal_content">
        <form id="cmtModFrm" action="#">
            <input type="hidden" id="icmt">
            <input type="text" id="modCmt">
        </form>
        <input type="button" value="댓글 수정" onclick="modAjax();">
        <input type="button" value="취소" onclick="closeModModal();">
    </div>
</div>

</body>
</html>
