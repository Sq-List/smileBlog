$(
    function()
    {
        getComment();
    }
)

function getComment()
{
    $.ajax(
        {
            url: "./new",
            type: "POST",
            data: "classification=comment",
            dataType: "json",

            success: function(list)
            {
                list = list[0];
                insertInformation(list, "comment");
            }
        }
    )
}

function getLike()
{
    $.ajax(
        {
            url: "./new",
            type: "POST",
            data: "classification=like",
            dataType: "json",

            success: function(list)
            {
                list = list[0];
                insertInformation(list, "like");
            }
        }
    )
}

function getCollection()
{
    $.ajax(
        {
            url: "./new",
            type: "POST",
            data: "classification=collection",
            dataType: "json",

            success: function(list)
            {
                list = list[0];
                insertInformation(list, "collection");
            }
        }
    )
}

function insertInformation(list, type)
{
    var $timelist = $("#timelist");
    var $newslist = $("#newslist");
    $timelist.text(" ");
    $newslist.text(" ");
    if(type == "collection")
    {
        type = "collect";
    }

    for(var i = 0; i < list.length; i++)
    {
        var timeDiv =
            '<div class="messagetime">'+
            '<div class="borderone"></div>'+
            '<div class="time">' + (list[i].createTime.year + 1990) + '-' +
            (list[i].createTime.month + 1) + '-' + (list[i].createTime.date) + '</div>'+
            '<div class="bordertwo"></div>'
        '</div>';
        // console.log(list[i]);
        $timelist.append(timeDiv);

        var messageDiv =
            '<div class="comment-message">'+
            '<div class="newmessage">'+
            '<div class="person-picture"><img src="' + list[i].operateHeadPic + '"></div>'+
            '<div class="person-name"><div><a href="./index?uid=' + list[i].operateUid + '"> ' + list[i].operateNickname + '</a></div></div>'+
            '<div id="reply">' + type + '</div>';

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
		if(this.innerHTML == "COMMENT")
        {
            getComment();
        }
        else if(this.innerHTML == "SMILE")
        {
            getLike();
        }
        else if(this.innerHTML == "COLLECTED")
        {
            getCollection();
        }
	}
}
//改变点击时标签的样式
function news(obj) {
	obj.style.opacity='1';
	// obj.style.backgroundColor="#8E8B8B";
}
