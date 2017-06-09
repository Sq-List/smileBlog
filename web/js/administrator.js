var liarray=document.getElementsByTagName("li");
var firstshow=document.getElementsByClassName("first-show")[0];
var home=document.getElementsByTagName("h3")[0];
for(i=0;i<liarray.length-1;i++){
	liarray[i].onclick=function(){
		//将之前的信息隐藏
		document.getElementById("USERS").style.display='none';
		document.getElementById("ARTICLES").style.display='none';
		document.getElementById("TOOLS").style.display='none';
		firstshow.style.display='none';
		set(this);
	}
}
//点击相应的标签获取相应的信息
function set(obj){
	var list=obj.innerHTML;
	document.getElementById("classify").innerHTML=list;
	document.getElementById(list).style.display='block';
}
//回到原始页面
home.onclick=function(){
	document.getElementById("USERS").style.display='none';
	document.getElementById("ARTICLES").style.display='none';
	document.getElementById("TOOLS").style.display='none';
	document.getElementById("classify").innerHTML='';
	firstshow.style.display='block';
}