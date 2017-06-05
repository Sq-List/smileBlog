function like(){
	var textLike=document.getElementById("text-like");
	if(textLike.style.color=='rgb(206, 0, 0)')
		textLike.style.color='#FFFFFF';
	else
	{
		textLike.style.color='#CE0000';
	}
}

// function chgpersondata(){
// 	document.getElementById("personal-date").style.display='block';
// }

// $(
//     function()
//     {
//         var aid = getUrlParam("aid");
//         var uid = getUrlParam("uid");
//         $.ajax(
//             {
//                 type : "POST",
//                 url : "../AjaxGetArticleServlet",
//                 datatype : "json",
//                 data : "aid=" + aid + "&uid=" + uid,
//
//                 success : function()
//                 {
//
//                 }
//             }
//         )
//     }
// )

var menuHeadpicture=document.getElementsByClassName("menu-headpicture")[0].getElementsByTagName("img")[0];
var myurl=menuHeadpicture.src;
var personmessageArray=document.getElementsByClassName("menu-personmessage")[0].getElementsByTagName("div");
var main=document.getElementsByClassName("main")[0];

menuHeadpicture.onclick=function(){
    set();
}
function set()
{
    var personalDate = document.createElement("div");
    var personalHeadpicture = document.createElement("div");
    var img = document.createElement("img");
    var changeHeadpicture = document.createElement("div");
    personalDate.setAttribute("class", "personal-date");
    personalHeadpicture.setAttribute("class", "personal-headpicture");
    img.setAttribute("src", myurl);
    changeHeadpicture.setAttribute("class", "change-headpicture");
    changeHeadpicture.innerHTML = '修改头像';
    personalHeadpicture.appendChild(img);
    personalHeadpicture.appendChild(changeHeadpicture);
    personalDate.appendChild(personalHeadpicture);
    var personalName = document.createElement("div");
    var name1 = document.createElement("div");
    var name2 = document.createElement("div");
    personalName.setAttribute("class", "personal-name");
    name1.innerHTML = 'NAME';
    name2.innerHTML = personmessageArray[0].innerHTML;
    personalName.appendChild(name1);
    personalName.appendChild(name2);
    personalDate.appendChild(personalName);
    var personalLable = document.createElement("div");
    var lable1 = document.createElement("div");
    var lable2 = document.createElement("div");
    personalLable.setAttribute("class", "personal-lable");
    lable1.innerHTML = 'LABLE';
    lable2.innerHTML = personmessageArray[1].innerHTML;
    personalLable.appendChild(lable1);
    personalLable.appendChild(lable2);
    personalDate.appendChild(personalLable);
    var input1 = document.createElement("input");
    var input2 = document.createElement("input");
    input1.setAttribute("value", "提交");
    input2.setAttribute("value", "取消");
    input1.setAttribute("type", "submit");
    input2.setAttribute("type", "submit");
    personalDate.appendChild(input1);
    personalDate.appendChild(input2);
    main.appendChild(personalDate);
}
