<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>owner</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/owner.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/container.css"/> ">
</head>
<body>
<div class="container">
	<div class="menu">
		<h3><a href="<c:url value='/index?uid=${sessionScope.user.uid}'/>">HOME</a></h3>
		<div class="menu-headpicture">
            <c:choose>
                <c:when test="${sessionScope.user.uid == uid}">
                    <%--挑出框--%>
                    <img src="${thisUser.headPic}">
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/index?uid=${thisUser.uid}" />"><img src="${thisUser.headPic}"></a>
                </c:otherwise>
            </c:choose>
		</div>
		<br>
		<div class="menu-personmessage">
			<div>${thisUser.nickname}</div>
			<div>${thisUser.lable}</div>
		</div>
		<c:choose>
            <c:when test="${sessionScope.user.uid == uid}">
                <div class="menu-list">
                    <ul>
                        <li>NEW</li>
                        <li>COLLECTION</li>
                        <li>TOOL</li>
                        <li>EXIT</li>
                    </ul>
                </div>
            </c:when>
        </c:choose>
	</div>
	<div class="notmenu">&nbsp;</div>
	<div class="main">
		<div class="main-search">
			<select >
				<option>文章</option>
				<option>博主</option>
			</select>
			<input type="text" name="search" value="文章/博主搜索" onclick="clearcontent(this)">
		</div>
		<div class="main-textarea">
			<div class="owner">
				<img src="./image/screen.jpg">
			</div>
			<a href="<c:url value="/writeArticle"/> "><div id="write"></div></a>
            <c:forEach items="${articleList}" var="article">
                <div class="text1">
                    <a href="<c:url value='/article?aid=${article.aid}'/>"><img src="./image/screen.jpg"></img></a>
                    <div class="title">
                        <div class="time">${article.createTime.year + 1990}-${article.createTime.month + 1}-${article.createTime.date}</div>
                        <div class="titlename">${article.title}</div>
                    </div>
                </div>
            </c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./js/owner.js"></script>
<script type="text/javascript" src="./js/search.js"></script>
</body>
</html>
