<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>administrator</title>
	<link rel="stylesheet" type="text/css" href="./css/administrator.css">
	<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<div class="container">
		<div class="menu">
			<h3>HOME</h3>
			<div class="menu-list">
				<ul>
					<li>USERS</li>
					<li>ARTICLES</li>
					<li>TOOLS</li>
					<li><a href="./adminExit" target="_self">EXIT</a></li>
				</ul>
			</div>
		</div>
		<div class="notmenu">&nbsp;</div>
		<div class="main">
			<div class="main-search">
				<div id="classify"></div>
				<%--<input type="search" name="search" id="search-input" placeholder="search">--%>
				<%--<div class="icon"><img src="./image/search_icon.png"></div>--%>
			</div>


			<div class="first-show">
				<div class="buttons">
					<button class="happy-button">:)</button>
					<button class="normal-button">:|</button>
					<button class="angry-button">:(</button>
				</div>
				<div class="smiley">
					<div class="eyes">
						<div class="eye"></div>
						<div class="eye"></div>
					</div>
					<div class="mouth"></div>
				</div>
			</div>

            <div id="USERS">
                <c:forEach items="${userList}" var="user">
                    <div class="user-message">
                        <div class="user-picture"><a href="./index?uid=${user.uid}" ><img src="${user.headPic}"></a></div>
                        <div class="user-data">
                            <div class="user-name"><div><a href="./index?uid=${user.uid}" >${user.nickname}</a></div></div>
                            <div class="user-lable"><div>${user.label}</div></div>
                            <div class="user-article">
                                <div>article quantity</div>
                                <div>${user.articleNumber}</div>
                            </div>
                        </div>
                        <div class="user-button">
                            <c:choose>
                                <c:when test="${user.status == 0}">
                                    <input class="limit-btn" classification="User" classification-id="${user.uid}" type="button" name="" value="limit">
                                </c:when>
                                <c:otherwise>
                                    <input class="limit-btn" classification="User" classification-id="${user.uid}" type="button" name="" value="unfreeze">
                                </c:otherwise>
                            </c:choose>
                            <input class="delete-btn" classification="User" classification-id="${user.uid}" type="button" name="" value="delete">
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="ARTICLES">
			    <c:forEach items="${articleList}" var="article">
                    <div class="article-messgae">
                        <div class="article-data">
                            <div class="passage">
                                <div class="passage-name"><a href="./article?aid=${article.aid}" >${article.title}</a></div>
                                <div class="passage-author">BY <a href="./index?uid=${article.ownuid}" >${article.ownNickname}</a></div>
                                <div class="passage-time">${article.createTime.year + 1990}-${article.createTime.month + 1}-${article.createTime.date} ${article.createTime.hours}:${article.createTime.minutes}</div>
                            </div>
                            <div class="passage-abstract">
                                <div>
                                    ${article.contentTxt}
                                </div>
                            </div>
                        </div>
                        <div class="article-button">
                            <input class="delete-btn" classification="Article" classification-id="${article.aid}" type="button" name="" value="delete">
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="TOOLS">
			    <c:forEach items="${toolList}" var="tool">
                    <div class="tool-message">
                        <div class="list-message">
                            <div class="tool-name">${tool.name}</div>
                            <div class="tool-author">BY <a href="./index?uid=${tool.uid}" >${tool.nickname}</a></div>
                            <div class="tool-time">${tool.createTime.year + 1990}-${tool.createTime.month + 1}-${tool.createTime.date} ${tool.createTime.hours}:${tool.createTime.minutes}</div>
                        </div>
                        <div class="tool-button">
                            <input class="delete-btn" classification="Tool" classification-id="${tool.tid}" type="button" name="" value="delete">
                        </div>
                    </div>
                </c:forEach>
            </div>
		</div>
	</div>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/administrator.js"></script>
	<script type="text/javascript" src="./js/candt.js"></script>
	<script type="text/javascript" src="./js/chgopacity.js"></script>
	<script type="text/javascript" src="./js/index.js"></script>
</body>
</html>
