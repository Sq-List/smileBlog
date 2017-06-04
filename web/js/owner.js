window.onload = function showtime()
{
    var myDate = new Date();
    var mytime = myDate.toLocaleDateString();
    document.getElementById("write").innerHTML = mytime;

    ajaxGetArticleList();
}

// 拿到地址栏的参数
function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function ajaxGetArticleList()
{
    var $uid = getUrlParam("uid");
    $.ajax(
        {
            type : "POST",
            url : "../AjaxGetArticleListServlet",
            datatype : "json",
            data : "uid=" + $uid,

            success : function(ArticalList)
            {
                ArticalList = eval("("+ArticalList+")");
                console.log(ArticalList);
                var $mainTextarea = $("#main-textarea");
                for(var i = 0; i < ArticalList.length; i++)
                {
                    var article = ArticalList[i];
                    var node = '<div class="text1"><a href="../html/artical.html?aid=' + article.aid + '"><img src="../image/screen.jpg"></img></a><div class="title"><div class="time">' + (article.createTime.year + 1990) + '-' + (article.createTime.month + 1) + '-' + article.createTime.date + '</div><div class="titlename">' + article.title + '</div></div></div>';
                    $mainTextarea.append(node);
                }
            }
        }
    )
}
