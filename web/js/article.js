var l=document.getElementById("like");
var frame=document.getElementById("frame");
var half=document.getElementsByClassName("half")[0];
var main=document.getElementsByClassName("main")[0];
var list=document.getElementsByClassName("commentlist")[0];
var comment=document.getElementsByClassName("comment");
var deletebutton=document.getElementsByClassName("deletebutton");

//点赞
l.onclick=function(){
	like(this);
}
//点击评论出现评论框
half.onclick=showOK;

function showOK(){
	fpushout();
	frame.style.transform='translate(-1740%)';
	frame.style.transition='1.5s';
	this.onclick = "";
}
//点赞效果
function like(obj){
	if(obj.style.color=='rgb(206, 0, 0)')
		obj.style.color='#FFFFFF';
	else
	{
		obj.style.color='#CE0000';
	}
}
//点击评论时生成结点
function fpushout(){
	var textarea=document.createElement("textarea");//评论区域
	textarea.setAttribute("class","pushout");
	textarea.id = "textarea";
	frame.appendChild(textarea);
	var buttonArray=document.createElement("div");//按钮区域
	var button1=document.createElement("input");
	var button2=document.createElement("input");
	button1.setAttribute("type","submit");
	button2.setAttribute("type","button");
	button1.setAttribute("value","OK");
	button2.setAttribute("value","back");
	buttonArray.setAttribute("class","pushout-button");
	buttonArray.id = "buttonArray";
	button1.setAttribute("class","button-array");
	button2.setAttribute("class","button-array");
	buttonArray.appendChild(button1);
	buttonArray.appendChild(button2);
	frame.appendChild(buttonArray);

	button1.onclick=function(){
		//生成评论
		var node='<div class="comment"><div class="commentator"><div class="person-picture"><img src="../image/picture1.png"></div><div class="person-name">zzz</div></div><div class="message"><div class="time">2017-6-4 13:57</div><div class="content">XXXXXXX</div></div></div>';
		list.innerHTML+=node;
		back();
	}

	button2.onclick=function(){
		back();
	}
}
//评论框回到原处，释放结点
function back(){
	frame.style.transform='translate(0)';
    frame.style.transition='1.5s';
    setTimeout(function()
    	{
    		var textarea = document.getElementById("textarea");
    		var buttonArray = document.getElementById("buttonArray");
    		console.log(textarea + ", " + buttonArray);
    		frame.removeChild(textarea);
    		frame.removeChild(buttonArray);
    	}, 1500);
    half.onclick = showOK;
}
//鼠标移到评论区上会出现删除按钮
for(var i=0;i<comment.length;i++){
	comment[i].i = i;
	comment[i].onmouseover=function(){
		setop(this);
		deletebutton[this.i].style.display='block';
	}
	comment[i].onmouseout=function(){
		cancelop(this);
		deletebutton[this.i].style.display='none';
	}
}
//改变评论区的透明度
function setop(obj){
	obj.style.opacity='1';
}

function cancelop(obj){
	obj.style.opacity='0.7';
}