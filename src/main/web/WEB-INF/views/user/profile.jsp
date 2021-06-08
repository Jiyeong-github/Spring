<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-08
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>프로필</title>
</head>
<body>
<c:choose>
    <c:when test="${empty sessionScope.loginUser.profileImg}">
        <c:set var="img" value="/res/img/noprofile.jpeg"/>
    </c:when>
    <c:otherwise>
        <c:set var="img" value="/img/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileImg}"/>
    </c:otherwise>
</c:choose>
<div>
    <form action="profile" method="post" enctype="multipart/form-data" id="frm" onsubmit="return imgChk();">
        이미지변경 : <input type="file" name="profileImg" accept="image/*">
        <input type="submit" value="이미지 업로드">
    </form>
</div>
<div>
    <div><img src="${img}"></div>
    <div>PK : ${sessionScope.loginUser.iuser}</div>
    <div>ID : ${sessionScope.loginUser.uid}</div>
    <div>Name : ${sessionScope.loginUser.unm}</div>
</div>

<script>
    var frmElem = document.querySelector('#frm');

    function imgChk() {
        if (frmElem.profileImg.files.length === 0) {
            alert('이미지를 선택해 주세요.');
            return false;
        }
    }
</script>
</body>
</html>
