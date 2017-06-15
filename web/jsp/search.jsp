<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>search</title>
	<link rel="stylesheet" type="text/css" href="./css/search.css">
	<link rel="stylesheet" type="text/css" href="./css/container.css">
	<link rel="stylesheet" type="text/css" href="./css/cover.css">
    <link rel="stylesheet" type="text/css" href="./css/unlogin.css">
</head>
<body>
	<div class="container">
        <c:choose>
            <c:when test="${not empty user}">
                <div class="menu">
                    <h3><a href="<c:url value="/index?uid=${user.uid}" />">HOME</a></h3>
                    <div class="menu-headpicture">
                        <img src="<c:url value="${user.headPic}" />">
                    </div>
                    <br>
                    <div class="menu-personmessage">
                        <div>${user.nickname}</div>
                        <div>${user.lable}</div>
                    </div>
                    <div class="menu-list">
                        <ul>
                            <li><a href="./new" target="_self">NEW</a></li>
                            <li><a href="./collection" target="_self">COLLECTION</a></li>
                            <li><a href="./tool" target="_self">TOOL</a></li>
                            <li><a href="./exit" target="_self">EXIT</a></li>
                        </ul>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="menu">
                    <h3><a href="<c:url value="/home" />">HOME</a></h3>
                    <div class="menu-headpicture"></div><br>
                    <div class="menu-personmessage">
                        <div></div>
                        <div></div>
                    </div>
                    <div class="menu-list">
                        <ul>
                            <li id="login">LOGIN</li>
                            <li id="register">REGISTER</li>
                        </ul>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="notmenu">&nbsp;</div>
        <div class="main">
            <form class="main-search" id="to-search" action="./search" method="get">
                <input type="search" name="search" placeholder="search">
                <div class="icon" id="searchIcon">
                    <img src="./image/search_icon.png">
                </div>
            </form>
            <div class="classify">
                <div>ARTICLE</div>
                <div>AUTHOR</div>
            </div>
            <div class="AUTHOR">
                <c:forEach items="${userList}" var="user">
                    <div class="users-message">
                        <div class="user-picture"><img src="${user.headPic}"></div>
                        <div class="user-data">
                            <div class="user-name author"><div><a href="./index?uid=${user.uid}" >${user.nickname}</a></div></div>
                            <div class="user-lable"><div>${user.lable}</div></div>
                            <div class="user-article">
                                <div>article quantity</div>
                                <div>${user.articleNumber}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="ARTICLE">
                <c:forEach items="${articleList}" var="article">
                    <div class="article-messgae">
                        <div class="passage">
                            <div class="passage-name title"><a href="article?aid=${article.aid}" >${article.title}</a></div>
                            <div class="passage-author author">BY <a href="./index?uid=${article.ownuid}" >${article.ownNickname}</a></div>
                            <div class="passage-time">${article.createTime.year + 1900}-${article.createTime.month + 1}-${article.createTime.date} ${article.createTime.hours}:${article.createTime.minutes}</div>
                        </div>
                        <div class="passage-abstract">
                            <div class="contextTxt">
                                ${article.contentTxt}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
	<c:choose>
        <c:when test="${not empty user}">
            <script type="text/javascript" src="./js/container.js"></script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript" src="./js/unlogin.js"></script>
        </c:otherwise>
    </c:choose>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="./js/searchKeyword.js"></script>
	<script type="text/javascript" src="./js/search.js"></script>
	<script type="text/javascript" src="./js/chgopacity.js"></script>
	<script type="text/javascript" src="./js/forbidden.js"></script>
    <script type="text/javascript" src="./js/toSearch.js"></script>
</body>
</html>
