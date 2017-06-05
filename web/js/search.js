function clearcontent(obj){
	obj.value="";
}

// 拿到地址栏的参数
function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null)
    {
        return unescape(r[2]);
    }

    return null;
}

// $(
//     function()
//     {
//         var $uid = getUrlParam("uid");
//         $.ajax(
//             {
//                 type : "POST",
//                 url : "../AjaxGetUserServlet",
//                 datatype : "json",
//                 data : "uid=" + $uid,
//
//                 success : function(obj)
//                 {
//                     obj = eval('(' + obj + ')');
//                     var user = obj.user;
//                     var $userDiv = $('<div class="menu"><h3><a href=' + user.uid + '"../html/owner-noUse.html?uid=">HOME</a></h3><div class="menu-headpicture"><img src="' + user.headPic + '"></div><br><div class="menu-personmessage"><div>' + user.nickname + '</div><div>' + user.lable + '</div></div></div>');
//                     if(eval(obj.flag))
//                     {
//                         $userDiv.append('<div class="menu-list"><ul><li>NEW</li><li>COLLECTION</li><li>TOOL</li><li>EXIT</li></ul></div>');
//                     }
//
//                     $($(".container")[0]).prepend($userDiv);
//                 }
//             }
//         )
//     }
// )
