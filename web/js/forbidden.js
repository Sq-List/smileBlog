//覆盖当前界面，处于禁用状态
function setForbidden(){
	var Width=document.body.clientWidth;
	var Height=document.body.clientHeight;
	var cover=document.createElement("div");
	cover.setAttribute("class","cover");
	cover.style.width=Width+"px";
	cover.style.height=Height+"px";
	document.body.appendChild(cover);
}