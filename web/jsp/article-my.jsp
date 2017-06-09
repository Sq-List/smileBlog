<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>artical</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/artical.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/container.css"/>">
	<script src="<c:url value="/neditor.parse.js"/>"></script>
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

        <div class="menu-headpicture">
            <a href="<c:url value="/index?uid=${thisUser.uid}" />">
                <img src="<c:url value="${thisUser.headPic}" />">
            </a>
        </div>
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
                                <li><a href="new.html" target="_self">NEW</a></li>
                                <li><a href="collection.html" target="_self">COLLECTION</a></li>
                                <li><a href="tool.html" target="_self">TOOL</a></li>
                                <li><a href="home.html" target="_self">EXIT</a></li>
                            </ul>
                        </div>
                    </c:when>
                </c:choose>
            </c:otherwise>
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
			<img class="main-picture" src="<c:url value="/image/text1.jpg"/>"></img>
			<div class="textcontent">
				<div class="text-title">${article.title}</div>
				<div class="text-date">${article.createTime.year + 1990}-${article.createTime.month + 1}-${article.createTime.date}&nbsp;</div>
                <%--${article.createTime.hours}:${article.createTime.minutes}:${article.createTime.seconds}--%>
				<div id="text-like" onclick="like()">&#10084;</div>
				<%--<div class="text-area">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>--%>
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

            <%--<div class="textcontent">--%>
                <%--<div class="text-title">Dream</div>--%>
                <%--<div class="text-date">2017-5-22</div>--%>
                <%--<div id="text-like" onclick="like()">&#10084;</div>--%>
                <%--&lt;%&ndash;<div class="text-area">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>&ndash;%&gt;--%>
                <%--<div class="text-area" id="text-area">--%>

                <%--</div>--%>
                <%--<div class="text-keywords">links:</div>--%>
                <%--<div class="text-previous">&#60;&#60;Previous</div>--%>
                <%--<div class="text-next">Next&#62;&#62;</div>--%>
            <%--</div>--%>
			<%--<div id="personal-date">--%>
				<%--<div class="personal-headpicture">--%>
					<%--<img src="<c:url value="/image/polygon.jpg" />"></img>--%>
					<%--<div id="change-headpicture">上传头像</div>--%>
				<%--</div>--%>
				<%--<div class="personal-name">--%>
					<%--<div>NAME</div>--%>
					<%--<div>A七</div>--%>
				<%--</div>--%>
				<%--<div class="personal-lable">--%>
					<%--<div>LABLE</div>--%>
					<%--<div>for better</div>--%>
				<%--</div>--%>
				<%--<input type="submit" name="submit" value="提交">--%>
				<%--<input type="submit" name="cancle" value="取消">--%>
			<%--</div>--%>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/js/artical.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/search.js"/>"></script>
<script type="text/javascript">
  uParse('#text-area', {
    rootPath: '../'
  })
</script>
</body>
</html>
