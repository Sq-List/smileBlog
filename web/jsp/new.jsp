<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>new</title>
	<link rel="stylesheet" type="text/css" href="./css/container.css">
	<link rel="stylesheet" type="text/css" href="./css/new.css">
	<link rel="stylesheet" type="text/css" href="./css/cover.css">
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
            <form class="main-search" id="to-search" action="./search" method="get">
                <input type="search" name="search" placeholder="search">
                <div class="icon" id="searchIcon">
                    <img src="./image/search_icon.png">
                </div>
            </form>
			<div id="classify">
				<div>COMMENT</div>
				<div>SMILE</div>
				<div>COLLECTED</div>
			</div>
			<div class="timelist" id="timelist">

			</div>
			<div id="newslist" id="newslist">

			</div>
		</div>
	</div>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/new.js"></script>
	<script type="text/javascript" src="./js/container.js"></script>
	<script type="text/javascript" src="./js/chgopacity.js"></script>
	<script type="text/javascript" src="./js/forbidden.js"></script>
    <script type="text/javascript" src="./js/toSearch.js"></script>
</body>
</html>
