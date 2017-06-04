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
		<h3>HOME</h3>
		<div class="menu-headpicture">
			<img src="${user.headPic}">
		</div>
		<br>
		<div class="menu-personmessage">
			<div>${user.nickname}</div>
			<div>${user.lable}</div>
		</div>
		<div class="menu-list">
			<ul>
				<li>NEW</li>
				<li>COLLECTION</li>
				<li>TOOL</li>
				<li>EXIT</li>
			</ul>
		</div>
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
				<img src="../image/screen.jpg">
			</div>
			<div id="write"></div>
			<div class="text1">
				<img src="../image/text1.jpg"></img>
				<div class="title">
					<div class="time">2017-5-12</div>
					<div class="titlename">Dream</div>
				</div>
			</div>
			<div class="text1">
				<img src="../image/1.jpg"></img>
				<div class="title">
					<div class="time">2017-5-11</div>
					<div class="titlename">Music</div>
				</div>
			</div>
			<div class="text1">
				<img src="../image/2.jpg"></img>
				<div class="title">
					<div class="time">2017-5-10</div>
					<div class="titlename">Life</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/owner.js"></script>
<script type="text/javascript" src="../js/search.js"></script>
</body>
</html>
