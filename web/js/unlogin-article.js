var login=document.getElementById("login");
var register=document.getElementById("register");

if(register != null)
{
    register.onclick=function(){
        setreg();
    }
}

if(login != null)
{
    login.onclick=function(){
        setlogin();
    }
}

function clearcontent(obj){
	obj.value="";
}

function setreg(){
	var reg='<div class="face" id="face">'+
				'<div class="set-register">'+
					'<div class="register">'+
						'<table>'+
							'<tr>'+
								'<td>UserName</td>'+
								'<td><input type="text" name="" value="unchangeable" onclick="setclear(this)"></td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="uesrname"></td>'+
							'</tr>'+
							'<tr>'+
								'<td>Password</td>'+
								'<td><input type="text" name="" value="unchangeable" onclick="writepassword(this)"></td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="passwod"></td>'+
							'</tr>'+
							'<tr>'+
								'<td>ConfirmPasswrd</td>'+
								'<td><input type="password" name="" onclick="setclear(this)"></td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="confirm-passwrd"></td>'+
							'</tr>'+
							'<tr>'+
								'<td>VerifyCode</td>'+
								'<td>'+
									'<input type="text" name="" value=""  class="code" onclick="setclear(this)">'+
									'<div class="code-img">1234</div>'+
									'<input type="button" name="" value="change" class="next">'+
								'</td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="verify-code"></td>'+
							'</tr>'+
						'</table>'+
						'<div class="buttonarry">'+
							'<input type="submit" name="" value="submit">'+
							'<input type="button" name="" value="cancel" onclick="cancel()">'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>';
	main.innerHTML+=reg;
}

function setlogin(){
	var log='<div class="face" id="face">'+
				'<div class="set-login">'+
					'<div class="login">'+
						'<table>'+
							'<tr>'+
								'<td>UserName</td>'+
								'<td><input type="text" name="" value="" onclick="setclear(this)"></td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="uesrname"></td>'+
							'</tr>'+
							'<tr>'+
								'<td>Password</td>'+
								'<td><input type="text" name="" value="" onclick="writepassword(this)"></td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="passwod"></td>'+
							'</tr>'+
							'<tr>'+
								'<td>VerifyCode</td>'+
								'<td>'+
									'<input type="text" name="" value=""  class="code" onclick="setclear(this)">'+
									'<div class="code-img">1234</div>'+
									'<input type="button" name="" value="change" class="next">'+
								'</td>'+
							'</tr>'+
							'<tr class="message">'+
								'<td></td>'+
								'<td id="verify-code"></td>'+
							'</tr>'+
						'</table>'+
						'<div class="buttonarry">'+
							'<input type="submit" name="" value="login">'+
							'<input type="button" name="" value="cancel"  onclick="cancel()">'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>';
	main.innerHTML+=log;
}

function cancel(){
	main.removeChild(document.getElementById("face"));
}
