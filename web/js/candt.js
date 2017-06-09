//collection和tool界面的鼠标放上与移开的样式
var list=document.getElementsByClassName("list-message");
var data=document.getElementsByClassName("article-data");
for(var i=0;i<list.length;i++){
	list[i].onmouseover=function(){
		fun1(this);
	}
	list[i].onmouseout=function () {
		fun2(this);
	}
}
for(var i=0;i<data.length;i++){
	data[i].onmouseover=function(){
		fun1(this);
	}
	data[i].onmouseout=function () {
		fun2(this);
	}
}