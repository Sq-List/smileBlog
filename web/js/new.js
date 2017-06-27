$(
    function()
    {
        //进入页面后首先加载评论
        getComment();
    }
);

function getComment()
{
    //ajax方法
    $.ajax(
        {
            //请求路径为"./new"
            url: "./new",
            //请求方式为:"post"
            type: "POST",
            //请求的数据
            data: "classification=comment",
            //服务器返回的数据接收方式
            dataType: "json",

            //服务器成功返回数据执行的函数
            success: function(list)
            {
                list = list[0];
                //插入评论消息
                insertInformation(list, "comment");
            }
        }
    )
}

function getLike()
{
    //ajax方法
    $.ajax(
        {
            //请求路径为"./new"
            url: "./new",
            //请求方式为:"post"
            type: "POST",
            //请求的数据
            data: "classification=like",
            //服务器返回的数据接收方式
            dataType: "json",

            //服务器成功返回数据执行的函数
            success: function(list)
            {
                list = list[0];
                //插入点赞消息
                insertInformation(list, "like");
            }
        }
    )
}

function getCollection()
{
    //ajax方法
    $.ajax(
        {
            //请求路径为"./new"
            url: "./new",
            //请求方式为:"post"
            type: "POST",
            //请求的数据
            data: "classification=collection",
            //服务器返回的数据接收方式
            dataType: "json",

            //服务器成功返回数据执行的函数
            success: function(list)
            {
                list = list[0];
                //插入收藏消息
                insertInformation(list, "collection");
            }
        }
    )
}

function insertInformation(list, type)
{
    //获取时间部分结点和消息列表结点
    var $timelist = $("#timelist");
    var $newslist = $("#newslist");
    //清空
    $timelist.text(" ");
    $newslist.text(" ");

    //如果为收藏类型，则更改类型名称
    if(type == "collection")
    {
        type = "collect";
    }

    //遍历插入数据
    for(var i = 0; i < list.length; i++)
    {
        //生成时间结点
        var timeDiv =
            '<div class="messagetime">'+
            '<div class="borderone"></div>'+
            '<div class="time">' + (list[i].createTime.year + 1990) + '-' +
            (list[i].createTime.month + 1) + '-' + (list[i].createTime.date) + '</div>'+
            '<div class="bordertwo"></div>'
        '</div>';
        // console.log(list[i]);
        //将生成的时间结点插入到时间部分中
        $timelist.append(timeDiv);

        //生成消息结点
        var messageDiv =
            '<div class="comment-message">'+
            '<div class="newmessage">'+
            '<div class="person-picture"><img src="' + list[i].operateHeadPic + '"></div>'+
            '<div class="person-name"><div><a href="./index?uid=' + list[i].operateUid + '"> ' + list[i].operateNickname + '</a></div></div>'+
            '<div id="reply">' + type + '</div>';

        //如果为评论，则多加一部分评论的内容
        if(type == "comment")
        {
            messageDiv += '<div class="content">' + list[i].content + '</div>';
        }

        messageDiv +=
            '<div class="detialtime">' + list[i].createTime.hours + ':' + list[i].createTime.minutes + '</div>'+
            '</div>'+
            '<div class="mymessage">'+
            '<div class="reply">my article</div>'+
            '<div class="mycontent"><a href="./article?aid=' + list[i].aid + '"> ' + list[i].title + '</a></div>'+
            '</div>'+
            '</div>';
        //将消息结点插入消息列表
        $newslist.append(messageDiv);
    }
}

var classify=document.getElementById("classify").getElementsByTagName("div");
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
		if(this.innerHTML.indexOf("COMMENT") != -1)
        {
            getComment();
        }
        else if(this.innerHTML.indexOf("SMILE") != -1)
        {
            getLike();
        }
        else if(this.innerHTML.indexOf("COLLECTED") != -1)
        {
            getCollection();
        }
        $(this).children(".number").text("");
	}
}
//改变点击时标签的样式
function news(obj) {
	obj.style.opacity='1';
	// obj.style.backgroundColor="#8E8B8B";
}
