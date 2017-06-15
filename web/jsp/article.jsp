<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>artical</title>
	<link rel="stylesheet" type="text/css" href="./css/article.css">
	<link rel="stylesheet" type="text/css" href="./css/container.css">
	<link rel="stylesheet" type="text/css" href="./css/cover.css">
	<link rel="stylesheet" type="text/css" href="./css/smile.css">
    <link rel="stylesheet" type="text/css" href="./css/unlogin.css">
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
                                <li><a href="./new" target="_self">NEW</a></li>
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
			<img class="main-picture" src="./image/0.jpg"></img>
			<div class="textcontent">
                <div class="text-title">${article.title}</div>
                <div class="text-date">${article.createTime.year + 1990}-${article.createTime.month + 1}-${article.createTime.date}&nbsp;</div>
				<div class="text-like">
                    <%--如果登陆了未点赞或者未登录， 则显示不点赞的状态；否则显示点赞状态--%>
					<c:choose>
                        <c:when test="${(likeFlag != 0) && (likeFlag != null)}">
                            <div id="like" style="color: rgb(206, 0, 0)" aid="${article.aid}">&#10084;</div>
                        </c:when>
                        <c:otherwise>
                            <div id="like" aid="${article.aid}">&#10084;</div>
                        </c:otherwise>
                    </c:choose>
					<div id="like-number">${article.likeNumber}</div>
				</div>
				<div class="text-collect">
					<div id="collect">
						<div class="smiley" aid="${article.aid}">
						    <div class="eyes">
								<div class="eye1"></div>
								<div class="eye2"></div>
							</div>
                            <%--如果登陆了未收藏或者未登录， 则显示为收藏的状态；否则显示收藏状态--%>
                            <c:choose>
                                <c:when test="${(collectionFlag != 0) && (collectionFlag != null)}">
                                    <div class="mouth" style="width: 12px; height: 6px; border-bottom-left-radius: 6px; border-bottom-right-radius: 6px;"></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="mouth" style="width: 12px; height: 3px; border-bottom-left-radius: 0px; border-bottom-right-radius: 0px;"></div>
                                </c:otherwise>
                            </c:choose>

						</div>
					</div>
					<div id="collect-number">${article.collectionNumber}</div>
				</div>
                <%--如果文章的作者是登录的用户则显示删除按键--%>
				<c:choose>
                    <c:when test="${user.uid == thisUser.uid}">
                        <div class="text-delete">
                            <input type="button" id="deleteBtn" name="" value="delete">
                        </div>
                    </c:when>
                </c:choose>
                <br><br><br>
				<div class="text-area" id="text-area">
                    ${article.content}
                </div>
				<div class="text-keywords">links:</div>
                <c:choose>
                    <c:when test="${not empty preArticle}">
                        <div class="text-previous"><a href='<c:url value="/article?aid=${preArticle.aid}"/>'>&#60;&#60;Previous</a></div>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty nextArticle}">
                        <div class="text-next"><a href="<c:url value="/article?aid=${nextArticle.aid}"/>">Next&#62;&#62;</a></div>
                    </c:when>
                </c:choose>
			</div>
			<div class="commentlist" id="commentlist">
                <%--遍历评论列表--%>
                <c:choose>
                    <c:when test="${not empty article.commentList}">
                        <c:forEach items="${article.commentList}" var="comment">
                            <div class="comment" comid="${comment.comid}">
                                <div class="commentator">
                                    <a href="./index?uid=${comment.operateUid}">
                                        <div class="person-picture"><img src="${comment.operateHeadPic}"></div>
                                        <div class="person-name">${comment.operateNickname}</div>
                                    </a>
                                </div>
                                <div class="message">
                                    <div class="time">${comment.createTime.year + 1900}-${comment.createTime.month + 1}-${comment.createTime.date} ${comment.createTime.hours}:${comment.createTime.minutes}</div>
                                    <div class="content">${comment.content}</div>
                                </div>
                                <c:choose>
                                    <c:when test="${(not empty user) && (comment.operateUid == user.uid)}">
                                        <div class="deletebutton">
                                            <input type="button" name="" value="delete">
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
			</div>
			<!-- <div class="commentarea"><input type="text" name="" value="发表评论"></input></div> -->
		</div>
	</div>
    <%--如果未登录则不显示评论框--%>
    <c:choose>
        <c:when test="${not empty user}">
            <div id="frame">
                <div class="half" >Say</div>
            </div>
        </c:when>
    </c:choose>
</div>
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="./js/container.js"></script>
<script type="text/javascript" src="./js/forbidden.js"></script>
<script type="text/javascript" src="./js/toSearch.js"></script>
<c:choose>
    <c:when test="${not empty user}">
        <script type="text/javascript" src="./js/article.js"></script>
        <script type="text/javascript" src="./js/smile.js"></script>
        <script type="text/javascript" src="./js/container.js"></script>
    </c:when>
    <c:otherwise>
        <script type="text/javascript" src="./js/unlogin.js"></script>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${(empty user) || (user.uid != thisUser.uid)}">
        <script>
            menuHeadpicture.onclick=function()
            {

            }
        </script>
    </c:when>
</c:choose>
<script src="<c:url value="/neditor.parse.js"/>"></script>
<script type="text/javascript">
    uParse('#text-area', {
        rootPath: './'
    })
</script>
</body>
</html>
