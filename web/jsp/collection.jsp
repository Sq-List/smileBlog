<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>collection</title>
	<link rel="stylesheet" type="text/css" href="./css/container.css">
	<link rel="stylesheet" type="text/css" href="./css/collection.css">
	<link rel="stylesheet" type="text/css" href="./css/cover.css">
	<link rel="stylesheet" type="text/css" href="./css/collectionSmile.css">
</head>
<body>
	<div class="container">
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
		<div class="notmenu">&nbsp;</div>
		<div class="main">
			<div class="main-search">
				<input type="search" name="search" value="search" onclick="clearcontent(this)">
				<div class="icon"><img src="./image/search_icon.png"></div>
			</div>
			<div id="classify">
				<div>COLLECTION</div>
			</div>
			<div class="list">
				<c:choose>
                    <c:when test="${not empty articleList}">
                        <c:forEach var="i" begin="0" end="${fn:length(articleList) - 1}">
                            <div class="collect-message">
                                <div class="like">
                                    <div class="smiley" aid="${articleList[i].aid}">
                                        <div class="eyes">
                                            <div class="eye1"></div>
                                            <div class="eye2"></div>
                                        </div>
                                        <div class="mouth"></div>
                                    </div>
                                </div>
                                <div class="list-message">
                                    <div class="passage-message">
                                        <div class="passage-name">
                                            <a href="./article?aid=${articleList[i].aid}">${articleList[i].title}</a>
                                        </div>
                                        <div class="passage-author">
                                            BY <a href="./index?uid=${articleList[i].ownuid}">${userList[i].nickname}</a>
                                        </div>
                                    </div>
                                    <div class="abstract">
                                        <div>${articleList[i].contentTxt}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
			</div>
		</div>
	</div>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/container.js"></script>
	<script type="text/javascript" src="./js/candt.js"></script>
	<script type="text/javascript" src="./js/chgopacity.js"></script>
	<script type="text/javascript" src="./js/forbidden.js"></script>
	<script type="text/javascript" src="./js/smile.js"></script>
</body>
</html>
