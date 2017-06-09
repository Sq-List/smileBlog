var classify=document.getElementsByClassName("classify")[0].getElementsByTagName("div");
var author=document.getElementsByClassName("AUTHOR");
var article=document.getElementsByClassName("ARTICLE");
var usersmessage=document.getElementsByClassName("users-message");
var articlemessgae=document.getElementsByClassName("article-messgae");
//点击文章和作者的标签，出现相应的信息界面
for (var i=0;i<classify.length;i++) {
	classify[i].i = i;
	classify[i].onclick=function(){
		var i = this.i;
		for (var j=0;j<classify.length;j++) {
			classify[j].style.opacity='0.7';
			// classify[j].style.backgroundColor="#6C6C6C";
		}
		if(i==0){
			for(n=0;n<article.length;n++){
				article[n].style.display='block';
			}
			for(n=0;n<author.length;n++){
				author[n].style.display='none';
			}
		}
		else{
			for(n=0;n<article.length;n++){
				article[n].style.display='none';
			}
			for(n=0;n<author.length;n++){
				author[n].style.display='block';
			}
		}
		news(this);
	}
}
//改变点击时标签的样式
function news(obj) {
	obj.style.opacity='1';
	// obj.style.backgroundColor="#8E8B8B";
}

for(var m=0;m<usersmessage.length;m++){
	usersmessage[m].onmouseover=function(){
		fun1(this);
	}
	usersmessage[m].onmouseout=function () {
		fun2(this);
	}
}
for(var m=0;m<articlemessgae.length;m++){
	articlemessgae[m].onmouseover=function(){
		fun1(this);
	}
	articlemessgae[m].onmouseout=function () {
		fun2(this);
	}
}