var classify=document.getElementById("classify").getElementsByTagName("div");
var commentlist=document.getElementsByClassName("comment-message");
var smilelist=document.getElementsByClassName("smile-message");
var collectedlist=document.getElementsByClassName("collected-message");
//点击评论、点赞、被收藏的标签，出现相应的信息界面
for (var i=0;i<classify.length;i++) {
	classify[i].i = i;
	classify[i].onclick=function(){
		var i = this.i;
		for (var j=0;j<classify.length;j++) {
			//恢复原来样式
			classify[j].style.opacity='0.7';
			// classify[j].style.backgroundColor="#6C6C6C";
		}
		news(this);
		if(i==0){
			for(n=0;n<commentlist.length;n++){
				commentlist[n].style.display='block';
			}
			for(n=0;n<smilelist.length;n++){
				smilelist[n].style.display='none';
			}
			for(n=0;n<smilelist.length;n++){
				collectedlist[n].style.display='none';
			}
		}
		else if(i==1){
			for(n=0;n<smilelist.length;n++){
				smilelist[n].style.display='block';
			}
			for(n=0;n<commentlist.length;n++){
				commentlist[n].style.display='none';
			}
			for(n=0;n<smilelist.length;n++){
				collectedlist[n].style.display='none';
			}
		}
		else{
			for(n=0;n<smilelist.length;n++){
				collectedlist[n].style.display='block';
			}
			for(n=0;n<commentlist.length;n++){
				commentlist[n].style.display='none';
			}
			for(n=0;n<commentlist.length;n++){
				smilelist[n].style.display='none';
			}
		}
	}
}
//改变点击时标签的样式
function news(obj) {
	obj.style.opacity='1';
	// obj.style.backgroundColor="#8E8B8B";
}

for(var m=0;m<commentlist.length;m++){
	commentlist[m].onmouseover=function(){
		fun1(this);
	}
	commentlist[m].onmouseout=function () {
		fun2(this);
	}
}
for(var m=0;m<smilelist.length;m++){
	smilelist[m].onmouseover=function(){
		fun1(this);
	}
	smilelist[m].onmouseout=function () {
		fun2(this);
	}
}
for(var m=0;m<collectedlist.length;m++){
	collectedlist[m].onmouseover=function(){
		fun1(this);
	}
	collectedlist[m].onmouseout=function () {
		fun2(this);
	}
}