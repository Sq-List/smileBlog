var login=document.getElementById("login");
var register=document.getElementById("register");
var main=document.getElementsByClassName("main")[0];

register.onclick=function(){
	setreg();
	setForbidden();
    setRegisterBlur();
}

login.onclick=function(){
	setlogin();
	setForbidden();
    setLoginBlur();
}

function clearcontent(obj){
	obj.value="";
}

function setclear(obj){
    obj.value="";
}

function writepassword(obj){
    obj.value="";
    obj.type="password";
}

//弹出注册界面
function setreg(){
	var reg = '<div class="face" id="face">' +
        '<form class="set-register" id="set-register" action="./RegisterServlet" method="post">'+
        '<div class="register">'+
        '<table>'+
        '<tr>'+
        '<td>UserName</td>'+
        '<td><input type="text" name="username" id="username" value="unchangeable" onclick="setclear(this)"></td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="usernameError"></td>'+
        '</tr>'+
        '<tr>'+
        '<td>Password</td>'+
        '<td><input type="password" name="password" id="password" value="unchangeable" onclick="writepassword(this)"></td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="passwordError"></td>'+
        '</tr>'+
        '<tr>'+
        '<td>ConfirmPasswrd</td>'+
        '<td><input type="password" name="confirmPassword" id="confirmPassword" onclick="setclear(this)"></td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="confirmPasswordError"></td>'+
        '</tr>'+
        '<tr>'+
        '<td>VerifyCode</td>'+
        '<td>'+
        '<input type="text" name="verifyCode" id="verifyCode" value=""  class="code" onclick="setclear(this)">'+
        '<div class="code-img"><img src="./VerifyCodeServlet" id="imgVerifyCodeReg" ></div>'+
        '<input type="button" name="imgVerifyCodeReg" value="change" class="next" onclick="_hyz(this.name)">'+
        '</td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="verifyCodeError"></td>'+
        '</tr>'+
        '</table>'+
        '<div class="buttonarry">'+
        '<input type="button" name="register" id="registerBtn" value="submit">'+
        '<input type="button" name="" value="cancel" onclick="cancel()">'+
        '</div>'+
        '</div>'+
        '</form>' +
        '</div>';
	main.innerHTML+=reg;
}

//弹出登录界面

function setlogin(){
    var log = '<div class="face" id="face">' +
        '<form class="set-login" id="set-login" action="./LoginServlet" method="post">'+
        '<div class="login">'+
        '<table>'+
        '<tr>'+
        '<td>UserName</td>'+
        '<td><input type="text" name="username" id="loginUsername" value="" onclick="setclear(this)"></td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="loginUsernameError"></td>'+
        '</tr>'+
        '<tr>'+
        '<td>Password</td>'+
        '<td><input type="password" name="password" id="loginPassword" value="" onclick="writepassword(this)"></td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="loginPasswordError"></td>'+
        '</tr>'+
        '<tr>'+
        '<td>VerifyCode</td>'+
        '<td>'+
        '<input type="text" name="loginVerifyCode" id="loginVerifyCode" value=""  class="code" onclick="setclear(this)">'+
        '<div class="code-img"><img src="./VerifyCodeServlet" id="imgVerifyCodeLogin"></div>'+
        '<input type="button" name="imgVerifyCodeLogin" value="change" class="next" onclick="_hyz(this.name)">'+
        '</td>'+
        '</tr>'+
        '<tr class="message">'+
        '<td></td>'+
        '<td id="loginVerifyCodeError"></td>'+
        '</tr>'+
        '</table>'+
        '<div class="buttonarry">'+
        '<input type="button" name="login" id="loginBtn" value="login">'+
        '<input type="button" name="" value="cancel" onclick="cancel()">'+
        '</div>'+
        '</div>'+
        '</form>' +
        '</div>';
    // var log='<div class="face" id="face">'+
    //     '<div class="set-login">'+
    //     '<div class="login">'+
    //     '<table>'+
    //     '<tr>'+
    //     '<td>UserName</td>'+
    //     '<td><input type="text" name="" value="" onclick="setclear(this)"></td>'+
    //     '</tr>'+
    //     '<tr class="message">'+
    //     '<td></td>'+
    //     '<td id="uesrname"></td>'+
    //     '</tr>'+
    //     '<tr>'+
    //     '<td>Password</td>'+
    //     '<td><input type="text" name="" value="" onclick="writepassword(this)"></td>'+
    //     '</tr>'+
    //     '<tr class="message">'+
    //     '<td></td>'+
    //     '<td id="passwod"></td>'+
    //     '</tr>'+
    //     '<tr>'+
    //     '<td>VerifyCode</td>'+
    //     '<td>'+
    //     '<input type="text" name="" value=""  class="code" onclick="setclear(this)">'+
    //     '<div class="code-img">1234</div>'+
    //     '<input type="button" name="" value="change" class="next">'+
    //     '</td>'+
    //     '</tr>'+
    //     '<tr class="message">'+
    //     '<td></td>'+
    //     '<td id="verify-code"></td>'+
    //     '</tr>'+
    //     '</table>'+
    //     '<div class="buttonarry">'+
    //     '<input type="submit" name="" value="login">'+
    //     '<input type="button" name="" value="cancel"  onclick="cancel()">'+
    //     '</div>'+
    //     '</div>'+
    //     '</div>'+
    //     '</div>';

	main.innerHTML+=log;
}
//删除结点 取消禁用
function cancel(){
	main.removeChild(document.getElementById("face"));
	document.body.removeChild(document.getElementsByClassName("cover")[0]);
}

function setRegisterBlur()
{
    // 输入框失去焦点时候进行检验
    $('input[type!="button"][name][name!="search"]').blur(function()
    {
        var id = $(this).attr("id");
        //得到对应的校验函数名
        var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
        //执行函数调用
        eval(funName);
    })

    $(document).on("click", "#registerBtn", function()
        {
            var bool = true;
            if(!validateUsername())
            {
                bool = false;
            }
            if(!validatePassword())
            {
                bool = false;
            }
            if(!validateConfirmPassword())
            {
                bool = false;
            }
            if(!validateVerifyCode())
            {
                bool = false;
            }
            if(bool)
            {
                $("#set-register").submit();
            }
        }
    )
}
function setLoginBlur()
{
    // 输入框失去焦点时候进行检验
    $('input[type!="button"][id^="login"]').blur(function()
    {
        var id = $(this).attr("id");
        //得到对应的校验函数名
        var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
        //执行函数调用
        eval(funName);
    })

    $(document).on("click", "#loginBtn", function()
        {
            var bool = true;
            if(!validateLoginUsername())
            {
                bool = false;
            }
            if(!validateLoginPassword())
            {
                bool = false;
            }
            if(!validateLoginVerifyCode())
            {
                bool = false;
            }
            if(bool)
            {
                var username = $("#loginUsername").val();
                var password = $("#loginPassword").val();
                $.ajax(
                    {
                        url : "./LoginServlet",
                        data: "username=" + username + "&password=" + password,
                        dateType : "json",
                        type : "get",

                        success :function(result)
                        {
                            if(result)
                            {
                                window.location = location;
                            }
                        }
                    }
                )
            }
        }
    )
}
function _hyz(obj)
{
    var img = document.getElementById(obj);
    // 需要给出一个参数，这个参数每次都不同，这样清楚浏览器缓存!
    img.src = "./VerifyCodeServlet?a=" + new Date().getTime();
}
// 登陆时候的检验方法
// username检验方法
function validateLoginUsername()
{
    var id = 'loginUsername';
    var $username = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$username)
    {
        $validate.text('username has not been empty!');
        return false;
    }
    $validate.text('').css('visibility', 'hidden');
    return true;
}
// password检验方法
function validateLoginPassword()
{
    var id = 'loginPassword';
    var $password = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$password)
    {
        $validate.text('password has not been empty!');
        return false;
    }
    $validate.text('').css('visibility', 'hidden');
    return true;
}
// 验证码检验
function validateLoginVerifyCode()
{
    var id = "loginVerifyCode";
    var $verifyCode = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$verifyCode)
    {
        $validate.text('verifyCode has not been empty!');
        return false;
    }
    $.ajax(
        {
            type: "POST",
            url: "./AjaxValidateVerifyCodeServlet",
            datatype: "json",
            data: "verifyCode=" + $verifyCode,
            success: function(result)
            {
                result = eval(result);
                if(!result)
                {
                    $validate.text('verifyCode is wrong!').css('visibility', 'visible');
                    return false;
                }
            }
        }
    )
    $validate.text('').css('visibility', 'hidden');
    return true;
}
// 注册时候的检验方法
// username检验方法
function validateUsername()
{
    var id = 'username';
    var $username = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$username)
    {
        $validate.text('username has not been empty!');
        return false;
    }
    /**
     * 长度检验
     */
    if($username.length < 4 || $username.length > 15)
    {
        $validate.text('The length of username should between 4 and 15!');
        return false;
    }
    /**
     * 合法性检验
     */
    var pat = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){3,14}$/;
    if(!pat.test($username))
    {
        $validate.text('The first of username should be letter, the rest of username could be _, '
            + 'number, letter!');
        return false;
    }
    /**
     * 是否注册检验
     */

    $.ajax(
        {
            type: "POST",
            url: "./AjaxValidateUsernameServlet",
            datatype: "json",
            data: "username=" + $username,
            success: function(result)
            {
                // 转换成boolean
                result = eval(result);
                if(!result)
                {
                    $validate.text('username has been used!').css('visibility', 'visible');
                    return false;
                }
            }
        }
    )
    $validate.text('').css('visibility', 'hidden');
    return true;
}
// password检验方法
function validatePassword()
{
    var id = "password";
    var $password = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$password)
    {
        $validate.text('password has not been empty!');
        return false;
    }
    /**
     * 长度检验
     */
    if($password.length < 6 || $password.length > 15)
    {
        $validate.text('The length of username should between 6 and 15!');
        return false;
    }
    $validate.text(' ').css('visibility', 'hidden');
    return true;
}
// confirmPassword检验方法
function validateConfirmPassword()
{
    var id = "confirmPassword";
    var $confirmPassword = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$confirmPassword)
    {
        $validate.text('confirmPassword has not been empty!');
        return false;
    }
    /**
     * 两次密码输入是否一样
     */
    if($confirmPassword != $('#password').val())
    {
        $validate.text('Two input are different!').css('visibility', 'visible');
        return false;
    }
    $validate.text(' ').css('visibility', 'hidden');
    return true;
}

// 验证码检验
function validateVerifyCode()
{
    var id = "verifyCode";
    var $verifyCode = $('#' + id).val();
    var $validate = $('#' + id + 'Error').css('visibility', 'visible');
    /**
     * 非空检验
     */
    if(!$verifyCode)
    {
        $validate.text('verifyCode has not been empty!');
        return false;
    }
    $.ajax(
        {
            type: "POST",
            url: "./AjaxValidateVerifyCodeServlet",
            datatype: "json",
            data: "verifyCode=" + $verifyCode,
            success: function(result)
            {
                result = eval(result);
                if(!result)
                {
                    $validate.text('verifyCode is wrong!').css('visibility', 'visible');
                    return false;
                }
            }
        }
    )
    $validate.text('').css('visibility', 'hidden');
    return true;
}
