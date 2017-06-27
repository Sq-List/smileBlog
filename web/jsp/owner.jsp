<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>owner</title>
	<link rel="stylesheet" type="text/css" href="./css/owner.css">
	<link rel="stylesheet" type="text/css" href="./css/container.css">
	<link rel="stylesheet" type="text/css" href="./css/cover.css">
    <link rel="stylesheet" type="text/css" href="./css/unlogin.css">
    <link rel="stylesheet" type="text/css" href="./css/messageNumber.css">
</head>
<body>
<div class="container">
	<div class="menu">
        <%--home链接指向--%>
        <c:choose>
            <%--如果登录了， home则链接到自己的主页--%>
            <c:when test="${not empty user}">
                <h3><a href="<c:url value="/index?uid=${user.uid}" />">HOME</a></h3>
            </c:when>
            <%--如果没有登录， Home则链接到/home--%>
            <c:otherwise>
                <h3><a href="<c:url value="/home" />">HOME</a></h3>
            </c:otherwise>
        </c:choose>

        <%--图片显示--%>
        <c:choose>
            <c:when test="${(not empty user) && (user.uid == thisUser.uid)}">
                <div class="menu-headpicture">
                    <img src="<c:url value="${thisUser.headPic}" />">
                </div>
            </c:when>
            <c:otherwise>
                <div class="menu-headpicture">
                    <a href="<c:url value="/index?uid=${thisUser.uid}" />">
                        <img src="<c:url value="${thisUser.headPic}" />">
                    </a>
                </div>
            </c:otherwise>
        </c:choose>

        <br>
        <div class="menu-personmessage">
            <div>${thisUser.nickname}</div>
            <div>${thisUser.lable}</div>
        </div>

        <%--工具栏显示--%>
        <c:choose>
            <%--如果未登录，则显示登录或者注册--%>
            <c:when test="${empty user}">
                <div class="menu-list">
                    <ul>
                        <li id="login">LOGIN</li>
                        <li id="register">REGISTER</li>
                    </ul>
                </div>
            </c:when>
            <%--如果登录了， 则比对当前用户是否为登录用户--%>
            <c:otherwise>
                <c:choose>
                    <c:when test="${user.uid == thisUser.uid}">
                        <div class="menu-list">
                            <ul>
                                <li><a href="./new" target="_self">NEW<span id="message-number"></span></a></li>
                                <li><a href="./collection" target="_self">COLLECTION</a></li>
                                <li><a href="./tool" target="_self">TOOL</a></li>
                                <li><a href="./exit" target="_self">EXIT</a></li>
                            </ul>
                        </div>
                    </c:when>
                </c:choose>
            </c:otherwise>
        </c:choose>

	</div>
	<div class="notmenu">&nbsp;</div>
	<div class="main">
        <form class="main-search" id="to-search" action="./search" method="get">
            <input type="search" name="search" placeholder="search">
            <div class="icon" id="searchIcon">
                <img src="./image/search_icon.png">
            </div>
        </form>
		<div class="main-textarea">

            <%--如果登录了且当前用户为登录用户则显示--%>
            <c:choose>
                <c:when test="${(not empty user) && (user.uid == thisUser.uid)}">
                    <div class="owner">
                        <img src="./image/screen.jpg">
                    </div>
                    <a href="<c:url value="/writeArticle"/> ">
                        <div id="write"></div>
                    </a>
                </c:when>
            </c:choose>

            <%
                Random random = new Random(System.currentTimeMillis());
            %>
            <c:forEach items="${articleList}" var="article">
                <div class="text1">
                    <a href="<c:url value='/article?aid=${article.aid}'/>"><img src="./image/<%= random.nextInt(8)%>.jpg"></img></a>
                    <div class="title">
                        <div class="time">${article.createTime.year + 1900}-${article.createTime.month + 1}-${article.createTime.date}</div>
                        <div class="titlename">${article.title}</div>
                    </div>
                </div>
            </c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./js/container.js"></script>
<script type="text/javascript" src="./js/owner.js"></script>
<script type="text/javascript" src="./js/forbidden.js"></script>
<script type="text/javascript" src="./js/unlogin.js"></script>
<script type="text/javascript" src="./js/toSearch.js"></script>
<c:choose>
    <c:when test="${not empty user}">
        <script type="text/javascript" src="./js/messageNumber.js"></script>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${(empty user) || (user.uid != thisUser.uid)}">
        <script>
            menuHeadpicture.onclick=function(){

            }
        </script>
    </c:when>
</c:choose>
</body>
</html>
