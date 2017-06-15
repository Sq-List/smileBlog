function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function clearcontent(obj){
	obj.value="";
}

var menuHeadpicture=document.getElementsByClassName("menu-headpicture")[0].getElementsByTagName("img")[0];
var myurl=menuHeadpicture.src;
var personmessageArray=document.getElementsByClassName("menu-personmessage")[0].getElementsByTagName("div");
var main=document.getElementsByClassName("main")[0];

// 点击头像
if(menuHeadpicture != null)
{
    menuHeadpicture.onclick=function(){
        setForbidden();
        set();
    }
}

//显示更改个人信息界面
function set(){
	var personalDate=document.createElement("form");
    personalDate.setAttribute("class", "personal-data");
    personalDate.setAttribute("id", "persionalDate");
    personalDate.setAttribute("action", "./UpdateUserInfoServlet");
    personalDate.setAttribute("enctype", "multipart/form-data");
    personalDate.setAttribute("method", "post");

	//头像区域
	var personalHeadpicture=document.createElement("div");
    personalHeadpicture.setAttribute("class","personal-headpicture");
    personalHeadpicture.setAttribute("id", "personal-headpicture");

	var img=document.createElement("img");
    img.setAttribute("src",myurl);
    img.setAttribute("id", "headPic");

    var newInput = document.createElement("input");
    newInput.setAttribute("type", "file");
    newInput.setAttribute("name", "fileHeadPic");
    newInput.setAttribute("id", "fileHeadPic");
    newInput.setAttribute("style", "position:absolute;clip:rect(0 0 0 0);");
    newInput.setAttribute("onchange", "preview(this)");
    //<input type="file" name="xFile" id="xFile" style="position:absolute;clip:rect(0 0 0 0);" onchange="changeName()">

    var changeHeadpicture=document.createElement("label");
    changeHeadpicture.appendChild(newInput);
	changeHeadpicture.setAttribute("class","change-headpicture");
	changeHeadpicture.innerHTML+='change';
	changeHeadpicture.setAttribute("for", "fileHeadPic");

	personalHeadpicture.appendChild(img);
	personalHeadpicture.appendChild(changeHeadpicture);
    // personalHeadpicture.appendChild(newForm);
	personalDate.appendChild(personalHeadpicture);

	//姓名区域
	var personalName=document.createElement("div");
    personalName.setAttribute("class","personal-name");

	var name1=document.createElement("div");
    name1.innerHTML='NAME';

	var name2=document.createElement("input");
	name2.setAttribute("type","text");
	name2.setAttribute("name", "nickname")
	name2.setAttribute("value", personmessageArray[0].innerHTML);

	personalName.appendChild(name1);
	personalName.appendChild(name2);
	personalDate.appendChild(personalName);

	//个性签名区域
	var personalLable=document.createElement("div");
    personalLable.setAttribute("class","personal-lable");

	var lable1=document.createElement("div");
    lable1.innerHTML='LABLE';

	var lable2=document.createElement("textarea");
	lable2.setAttribute("name", "label");
	lable2.value = personmessageArray[1].innerHTML;

	personalLable.appendChild(lable1);
	personalLable.appendChild(lable2);
	personalDate.appendChild(personalLable);

	//提交、取消
	var input1=document.createElement("input");
    input1.setAttribute("value","submit");
    input1.setAttribute("type","submit");

	var input2=document.createElement("input");
	input2.setAttribute("value","cancel");
	input2.setAttribute("type","button");

	personalDate.appendChild(input1);
	personalDate.appendChild(input2);

	main.appendChild(personalDate);

	//点击取消按钮，释放结点，禁用解除
	input2.onclick=function(){
		main.removeChild(personalDate);
		document.body.removeChild(document.getElementsByClassName("cover")[0]);
	 }
}

//图片上传进行预览
function preview(file)
{
    var headPicImg = document.getElementById('headPic');
    if (file.files && file.files[0])
    {
        var reader = new FileReader();
        reader.onload = function(evt){
            headPicImg.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else
    {
        headPicImg.style = "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'";
    }
}


