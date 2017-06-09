var register=document.getElementById("head-register");
var login=document.getElementById("head-login");
var search=document.getElementsByClassName("head-mouse")[0];
var face=document.getElementsByClassName("face")[0];
var trlist=document.getElementsByTagName("tr");
var setRegister=document.getElementsByClassName("set-register")[0];
var setlogin=document.getElementsByClassName("set-login")[0];
var setsearch=document.getElementsByClassName("set-search")[0];
var cancel=document.getElementsByClassName("cancel")[0];
//注册
register.onmouseover=function(){
	showwords(this);
}

register.onmouseout=function(){
	hiddenwords(this);
}
register.onclick=function(){
	//显示注册界面
	 showregister();
}
//登录
login.onmouseover=function(){
	showwords(this);
}

login.onmouseout=function(){
	hiddenwords(this);
}
login.onclick=function(){
	 showlogin();
}
//搜索
search.onmouseover=function(){
	showwords1(this);
}

search.onmouseout=function(){
	hiddenwords1(this);
}
search.onclick=function(){
	showsearch();
}

//取消搜索
cancel.onclick=function(){
	setsearch.style.display='none';
}
//注册、登录显示
function showwords(obj){
	obj.children[0].style.opacity = 1;
	obj.children[0].style.top = "0";
}

function hiddenwords(obj){
	obj.children[0].style.opacity = 0;
	obj.children[0].style.top = "-60%";
}
//search显示
function showwords1(obj){
	obj.children[0].style.opacity = 1;
	obj.children[0].style.top = "0";
}

function hiddenwords1(obj){
	obj.children[0].style.opacity = 0;
	obj.children[0].style.top = "100%";
}

function showregister(){
	setRegister.style.display='block';
}

function cancelregister(){
	setRegister.style.display='none';
    $("[id$='Error']").text(" ");
}

function showlogin(){
	setlogin.style.display='block';
}

function cancellogin(){
	setlogin.style.display='none';
    $("[id$='Error']").text(" ");
}

function showsearch(){
	setsearch.style.display='block';
}

function setclear(obj){
	obj.value="";
}

function writepassword(obj){
	obj.value="";
	obj.type="password";
}

// function setcontainer(){
// 	var contain=document.createElement("div");
// 	contain.setAttribute("class","set-container");
// 	face.appendChild(contain);
// }

// function showregister(){
// 	var =document.createElement("table");
// }

$(
    function()
    {
        setRegisterBlur();
        setLoginBlur();

        $("#register").click(
            function()
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
                if(!validateNickname())
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
        $("#login").click(
            function()
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
                    $("#set-login").submit();
                }
            }
        )
    }
)

function setRegisterBlur()
{
    // 输入框失去焦点时候进行检验
    $('input[type!="button"][name]').blur(function()
    {
        var id = $(this).attr("id");
        //得到对应的校验函数名
        var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";
        //执行函数调用
        eval(funName);
    })
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
            url: "../AjaxValidateVerifyCodeServlet",
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
