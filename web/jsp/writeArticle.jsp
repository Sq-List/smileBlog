<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>owner</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/writeArtical.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/owner.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/container.css"/> ">
    <link rel="stylesheet" type="text/css" href="./css/messageNumber.css">
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
                <li><a href="./new" target="_self">NEW<span id="message-number"></span></a></li>
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
		<div class="main-textarea">
			<form action="<c:url value='/UpdateUserArticleNumberServlet' />" id="editor" method="post">
				<input type="text" id="title" name="title" placeholder="填写文章标题">
				<script id="container" name="content" type="text/plain">请输入正文</script>
                <input type="hidden" id="contentTxt" name="contentTxt">
                <input type="hidden" name="operate" value="add">
				<input type="submit" id="submitBtn" value="发布">
				<%--<input type="button" onclick="submitArtical()" value="11">--%>
			</form>
		</div>
	</div>
</div>
<script src="<c:url value='/js/jquery-3.2.1.min.js' />" charset="utf-8"></script>
<c:choose>
    <c:when test="${not empty user}">
        <script type="text/javascript" src="./js/messageNumber.js"></script>
    </c:when>
</c:choose>
<script type="text/javascript" src="./js/toSearch.js"></script>
<script type="text/javascript" src="<c:url value='/js/search.js' />"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<c:url value='/neditor.config.js' />"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<c:url value='/neditor.all.js' />"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container',
	{
        toolbars:
        [
          	['fontsize', 'bold', 'italic', 'underline', 'blockquote', 'insertcode', 'insertorderedlist', 'insertunorderedlist', 'link', 'simpleupload', 'removeformat', 'drafts']
        ],
        initialFrameHeight: 600,
	    autoHeightEnabled: false,
	    //autoFloatEnabled:false
	    zIndex: 5,
      	autoClearinitialContent: true,
      	enableAutoSave: false
    });

    $("form#editor").submit(
        function()
        {
//          获取文章的文字内容 并写入#contentTxt中提交
            $("#contentTxt").val(UE.getEditor('container').getContentTxt());
            return true;
        }
    );

	function submitArtical()
	{
      console.log(UE.getEditor('container').getContent());
      console.log(UE.getEditor('container').getContentTxt());
	}
</script>
</body>
</html>
