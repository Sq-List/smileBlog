<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>home</title>
	<link rel="stylesheet" type="text/css" href="./css/home.css">
</head>
<body>
	<div class="container">
		<div class="L" id="SMILEBLOG">
			<div>SMILEBLOG</div>
		</div>
		<div class="face" id="face">
			<div class="eyes">
				<div id="head-register">
					<div>REGISTER</div>
				</div>
				<div id="head-login">
					<div>LOGIN</div>
				</div>
			</div>
			<div class="head-mouse">
				<div>SEARCH</div>
			</div>
			<form class="set-register" id="set-register" action="./RegisterServlet" method="post">
				<div class="register">
					<table>
						<tr>
							<td>UserName</td>
							<td><input type="text" name="username" id="username" value="unchangeable" onclick="setclear(this)"></td>
						</tr>
						<tr class="message">
							<td colspan="2" id="usernameError"></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" id="password" value="unchangeable" onclick="writepassword(this)"></td>
						</tr>
						<tr class="message">
							<td colspan="2" id="passwordError"></td>
						</tr>
						<tr>
							<td>ConfirmPasswrd</td>
							<td><input type="password" name="confirmPassword" id="confirmPassword" onclick="setclear(this)"></td>
						</tr>
						<tr class="message">
							<td colspan="2" id="confirmPasswordError"></td>
						</tr>
						<tr>
							<td>VerifyCode</td>
							<td>
								<input type="text" name="verifyCode" id="verifyCode" value=""  class="code" onclick="setclear(this)">
								<div class="code-img"><img src="./VerifyCodeServlet" id="imgVerifyCodeReg" ></div>
								<input type="button" name="imgVerifyCodeReg" value="change" class="next" onclick="_hyz(this.name)">
							</td>
						</tr>
						<tr class="message">
							<td colspan="2" id="verifyCodeError"></td>
						</tr>
					</table>
					<div class="buttonarry">
						<input type="button" name="register" id="register" value="submit">
						<input type="button" name="" value="cancel" onclick="cancelregister()">
					</div>
				</div>
            </form>
			<form class="set-login" id="set-login" action="./LoginServlet" method="post">
				<div class="login">
					<table>
						<tr>
							<td>UserName</td>
							<td><input type="text" name="username" id="loginUsername" value="" onclick="setclear(this)"></td>
						</tr>
						<tr class="message">
							<td colspan="2" id="loginUsernameError"></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" id="loginPassword" value="" onclick="writepassword(this)"></td>
						</tr>
						<tr class="message">
							<td colspan="2" id="loginPasswordError"></td>
						</tr>
						<tr>
							<td>VerifyCode</td>
							<td>
								<input type="text" name="loginVerifyCode" id="loginVerifyCode" value=""  class="code" onclick="setclear(this)">
								<div class="code-img"><img src="./VerifyCodeServlet" id="imgVerifyCodeLogin"></div>
								<input type="button" name="imgVerifyCodeLogin" value="change" class="next" onclick="_hyz(this.name)">
							</td>
						</tr>
						<tr class="message">
							<td colspan="2" id="loginVerifyCodeError"></td>
						</tr>
					</table>
					<div class="buttonarry">
						<input type="button" name="login" id="login" value="login">
						<input type="button" name="" value="cancel" onclick="cancellogin()">
					</div>
				</div>
            </form>
			<form class="set-search" id="to-search" action="./search" method="get">
				<div class="cancel">&times;</div>
                <input type="search" name="search" placeholder="SEARCH">
				<div class="icon" id="searchIcon"><img src="<c:url value="/image/search_icon.png"/>"></div>
            </form>
		</div>
		<div class="some" id="some">
			<div>ABOUT</div>
			<div>CONTACT</div>
			<div>HELP</div>
		</div>
	</div>
    <script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="./js/home.js"></script>
    <script type="text/javascript" src="./js/toSearch.js"></script>
</body>
</html>
