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
</head>
<body>
<div class="container">
    <div class="menu">
        <h3><a href="<c:url value='/index?uid=${sessionScope.user.uid}'/>">HOME</a></h3>
        <div class="menu-headpicture">
            <%--挑出框--%>
            <img src="${user.headPic}">
        </div>
        <br>
        <div class="menu-personmessage">
            <div>${user.nickname}</div>
            <div>${uesr.lable}</div>
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
			<form action="<c:url value='/submitArticleServlet' />" id="editor" method="post">
				<input type="text" id="title" name="title" placeholder="填写文章标题">
				<script id="container" name="content" type="text/plain">请输入正文</script>
				<input type="submit" value="发布">
				<%--<input type="button" onclick="submitArtical()" value="11">--%>
			</form>
		</div>
	</div>
</div>
<script src="<c:url value='/js/jquery-3.2.1.min.js' />" charset="utf-8"></script>
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
          	['bold', 'italic', 'underline', 'blockquote', 'insertcode', 'insertorderedlist', 'insertunorderedlist', 'link', 'simpleupload', 'removeformat', 'drafts']
        ],
        initialFrameHeight: 600,
	    autoHeightEnabled: false,
	    //autoFloatEnabled:false
	    zIndex: 5,
      	autoClearinitialContent: true,
      	enableAutoSave: false
    });

	function submitArtical()
	{
      console.log(UE.getEditor('container').getContent());
	}
</script>
</body>
</html>
