<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>administrator-login</title>
	<link rel="stylesheet" type="text/css" href="./css/administrator-login.css">
</head>
<body>
	<div class="container">
		<div class="L">
			<div>SMILEBLOG</div>
		</div>
		<form class="face" action="./AdminLoginServlet" method="post">
			<div class="set-login">
				<div class="login">
					<table>
						<tr>
							<td>UserName</td>
							<td><input type="text" name="username" value="" onclick="setclear(this)"></td>
						</tr>
						<tr class="message">
							<td></td>
							<td id="uesrname"></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" value="" onclick="writepassword(this)"></td>
						</tr>
						<tr class="message">
							<td></td>
							<td id="passwod"></td>
						</tr>
						<tr>
							<td>VerifyCode</td>
							<td>
								<input type="text" name="" value=""  class="code" onclick="setclear(this)">
                                <div class="code-img"><img src="./VerifyCodeServlet" id="imgVerifyCodeReg" ></div>
                                <input type="button" name="imgVerifyCodeReg" value="change" class="next" onclick="_hyz(this.name)">
							</td>
						</tr>
						<tr class="message">
							<td></td>
							<td id="verify-code"></td>
						</tr>
					</table>
					<div class="buttonarry">
						<input type="submit" name="" value="login">
						<input type="reset" name="" value="reset" >
					</div>
				</div>
			</div>
        </form>
		<div class="some">
			<div>ABOUT</div>
			<div>CONTACT</div>
			<div>HELP</div>
		</div>
	</div>
	<script type="text/javascript" src="./js/home.js"></script>
</body>
</html>
