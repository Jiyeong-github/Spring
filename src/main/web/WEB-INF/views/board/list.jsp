<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-03
  Time: 오후 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>List</title>
    <link rel="stylesheet" href="/res/css/list.css">
    <script defer src="/res/js/list.js"></script>
</head>
<body>
<div>로그인 아이디:${sessionScope.loginUser.uid}</div>
<div>
    <span>로그인 아이디 : ${sessionScope.loginUser.uid}</span>
    <span><a href="/user/profile">프로필</a></span>
</div>
<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>

    <c:forEach items="${requestScope.list}" var="item">
        <tr class="record" onclick="moveToDetail(${item.iboard});">
            <td>${item.iboard }</td>
            <td><c:choose>
                <c:when test="${param.searchType eq 1||param.searchType eq 2 }">
                    ${item.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>') }
                </c:when>
                <c:otherwise>
                    ${item.title }
                </c:otherwise>
            </c:choose></td>

            <c:choose>
                <c:when test="${empty item.profileImg }">
                    <c:set var="img" value="/res/img/noprofile.jpeg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="img"
                           value="/img/${item.iuser}/${item.profileImg}"/>
                </c:otherwise>
            </c:choose>

            <td><c:choose>
                <c:when test="${param.searchType eq 4}">
                    ${item.writerNm.replace(param.searchText, '<mark>'+= param.searchText +='</mark>') }
                </c:when>
                <c:otherwise>
                    ${item.writerNm }
                </c:otherwise>
            </c:choose>
                <img src="${img}" class="profileImg">
            </td>
            <td>${item.regdt}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
