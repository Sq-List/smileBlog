$(
    function showtime()
    {
        var myDate = new Date();
        var mytime = myDate.toLocaleDateString();
        document.getElementById("write").innerHTML = mytime;

        //ajaxGetArticleList();
    }
)

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
                    var node = ''+ $uid + '<div class="text1"><a href="../html/artical-noUse.html?uid=&aid=">' +
                        '<img src="../image/screen.jpg"></img></a><div class="title"><div class="time">' +
                        article.aid + '' + (article.createTime.year + 1990) + '-' + (article.createTime.month + 1)
                        + '-' + article.createTime.date + '</div><div class="titlename">'
                        + article.title + '</div></div></div>';
                    $mainTextarea.append(node);
                }
            }
        }
    )
}
