function clearcontent(obj){
	obj.value="";
}

var menuHeadpicture=document.getElementsByClassName("menu-headpicture")[0].getElementsByTagName("img")[0];
var myurl=menuHeadpicture.src;
var personmessageArray=document.getElementsByClassName("menu-personmessage")[0].getElementsByTagName("div");
var main=document.getElementsByClassName("main")[0];

// 点击头像
menuHeadpicture.onclick=function(){
	setForbidden();
	set();
}

//显示更改个人信息界面
function set(){
	var personalDate=document.createElement("div");
	//头像区域
	var personalHeadpicture=document.createElement("div");
	var img=document.createElement("img");
	var changeHeadpicture=document.createElement("div");
	personalDate.setAttribute("class", "personal-data");
	personalHeadpicture.setAttribute("class","personal-headpicture");
	img.setAttribute("src",myurl);
	changeHeadpicture.setAttribute("class","change-headpicture");
	changeHeadpicture.innerHTML='change';
	personalHeadpicture.appendChild(img);
	personalHeadpicture.appendChild(changeHeadpicture);
	personalDate.appendChild(personalHeadpicture);
	//姓名区域
	var personalName=document.createElement("div");
	var name1=document.createElement("div");
	var name2=document.createElement("input");
	personalName.setAttribute("class","personal-name");
	name1.innerHTML='NAME';
	name2.setAttribute("type","text");
	name2.setAttribute("value",personmessageArray[0].innerHTML);
	personalName.appendChild(name1);
	personalName.appendChild(name2);
	personalDate.appendChild(personalName);
	//个性签名区域
	var personalLable=document.createElement("div");
	var lable1=document.createElement("div");
	var lable2=document.createElement("textarea");
	personalLable.setAttribute("class","personal-lable");
	lable1.innerHTML='LABLE';
	lable2.innerHTML=personmessageArray[1].innerHTML;
	personalLable.appendChild(lable1);
	personalLable.appendChild(lable2);
	personalDate.appendChild(personalLable);
	//提交、取消
	var input1=document.createElement("input");
	var input2=document.createElement("input");
	input1.setAttribute("value","submit");
	input2.setAttribute("value","cancel");
	input1.setAttribute("type","submit");
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
