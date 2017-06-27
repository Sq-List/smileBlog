var l=document.getElementById("like");
var frame=document.getElementById("frame");
var main=document.getElementsByClassName("main")[0];
var list=document.getElementsByClassName("commentlist")[0];
var comment=document.getElementsByClassName("comment");
var deletebutton=document.getElementsByClassName("deletebutton");

function ajaxChangeLike(aid, operate)
{
    console.log(aid);
    var classification = "like";
    $.ajax(
        {
            type : "POST",
            url : "./UpdateArticleNumberServlet",
            data : "aid=" + aid + "&operate=" + operate + "&classification=" + classification,
            dataType : "json",
            success : function(result)
            {
                if(result)
                {
                    alert(operate + " success!");
                    if(operate == "add")
                    {
                        document.getElementById("like-number").innerHTML = Number(document.getElementById("like-number").innerHTML) + 1;
                    }
                    else
                    {
                        document.getElementById("like-number").innerHTML = Number(document.getElementById("like-number").innerHTML) - 1;
                    }
                }
            }
        }
    )
}

//点赞
l.onclick=function(){
	like(this);
}


//点赞效果
function like(obj){
	if(obj.style.color == 'rgb(206, 0, 0)')
    {
        obj.style.color='#FFFFFF';
        ajaxChangeLike($(obj).attr("aid"), "delete");
        $("#like-number").text(Number($("#like-number").text()) - 1);
    }
	else
	{
		obj.style.color='#CE0000';
        ajaxChangeLike($(obj).attr("aid"), "add");
        $("#like-number").text(Number($("#like-number").text()) + 1);
	}
}

//提交评论
function changeComment(operate, content, obj)
{
    //设定类别为“comment”，用于服务器识别，转发
    var classification = "comment";
    //从url中获取文章的aid
    var aid = getQueryString("aid");
    //从comment中获取评论的comid，用于删除评论时用
    var comid = $(obj).parent(".comment").attr("comid");
    console.log(comid);

    //ajax异步请求技术
    $.ajax(
        {
            //请求方式为POST
            type : "POST",
            //请求的地址是“./UpdateArticleNumberServlet”
            url : "./UpdateArticleNumberServlet",
            //请求所发的数据
            data : "aid=" + aid + "&operate=" + operate + "&classification=" + classification + "&commentContent=" + content + "&comid=" + comid,
            //服务器返回数据的接收方式
            dataType : "json",

            //ajax请求成功所执行的函数
            success : function(result)
            {
                if(result)
                {
                    console.log(result);
                    // alert(operate + " success!");
                    //如果操作为添加评论
                    if(operate == "add")
                    {
                        // comment = {"aid":0,"comid":6,"content":"let try!","createTime":{"date":13,"day":2,"hours":12,"minutes":26,"month":5,"seconds":6,"time":1497327966000,"timezoneOffset":-480,"year":117},"operateHeadPic":"./image/userHeadPic/1.jpg","operateNickname":"Sq_List","operateUid":1,"title":"","uid":0}
                        //获取服务器传回的数据
                        var comment = result;
                        //生成评论的节点
                        var $node = $('<div class="comment" comid="' + comment.comid + '">' +
                            '<div class="commentator">' +
                            '<a href="./index?uid=' + comment.operateUid + '">' +
                            '<div class="person-picture">' +
                            '<img src="' + comment.operateHeadPic + '">' +
                            '</div>' +
                            '<div class="person-name">' + comment.operateNickname + '</div>' +
                            '</a>' +
                            '</div>' +
                            '<div class="message">' +
                            '<div class="time">' + (comment.createTime.year + 1990) + '-' + (comment.createTime.month + 1) + '-' + (comment.createTime.date) + ' ' + (comment.createTime.hours) + ':' + (comment.createTime.minutes) + '</div>' +
                            '<div class="content">' + comment.content + '</div>' +
                            '</div>' +
                            '<div class="deletebutton">'+
                            '<input type="button" name="" value="delete">'+
                            '</div>' +
                            '</div>');

                        //在评论列表添加上述节点
                        $("#commentlist").append($node);
                        //设置评论节点的css，调整位置和透明度
                        $($node).css(
                            {
                                position : "relative",
                                left : "870px",
                            }
                        ).animate(
                            //进行动画，并设定动画的时间为1.5s，并指定回调函数
                            {
                                left : "0px",
                            }, 1500, function()
                            {
                                position : "static"
                            }
                        )
                    }
                    //如果操作为删除评论
                    else if(operate == "delete")
                    {
                        //设置被删除评论的节点的css使其position为relative，用于后面的动画
                        $(obj).parent(".comment").css("position", "relative");
                        //获取呗删除评论节点的后面部分节点并设置其position为relative，用于后面的动画
                        var $other = $(obj).parent(".comment").nextAll().css("position", "relative");
                        //被删除节点的动画
                        $(obj).parent(".comment").animate(
                            {
                                top : "-102px",
                                opacity : "0"
                            }, 1500, function()
                            {

                            });
                        //被删除节点的后面部分节点的动画
                        $other.animate(
                            {
                                top : "-102px"
                            }, 1500, function()
                            {
                                $(this).css("top", "0");
                                $(obj).parent(".comment").remove();
                            });
                    }
                }
            }
        }
    )
}

var half=document.getElementsByClassName("half")[0];

//点击评论出现评论框
if(half != null)
{
    half.onclick=showOK;
}

function showOK(){
    //先生成评论框在右侧
    fpushout();
    //向左侧移动
    frame.style.transform='translate(-1740%)';
    frame.style.transition='1.5s';
    //使评论的按钮不在触发添加评论框
    this.onclick = "";
}

//点击评论时生成结点
function fpushout(){
	var textarea=document.createElement("textarea");//评论区域
	textarea.setAttribute("class","pushout");
	textarea.id = "textarea";
	frame.appendChild(textarea);

	var buttonArray=document.createElement("div");//按钮区域
	var button1=document.createElement("input");
	var button2=document.createElement("input");
	button1.setAttribute("type","submit");
	button2.setAttribute("type","button");
	button1.setAttribute("value","OK");
	button2.setAttribute("value","back");
	buttonArray.setAttribute("class","pushout-button");
	buttonArray.id = "buttonArray";
	button1.setAttribute("class","button-array");
	button2.setAttribute("class","button-array");
	buttonArray.appendChild(button1);
	buttonArray.appendChild(button2);
	frame.appendChild(buttonArray);

    //点击“ok”时触发的事件
	button1.onclick=function(){
        //判断评论框中是否有内容，如若没有内容则提示输入，并给输入区域焦点
        if(textarea.value == "" || textarea.value.length <= 0)
        {
            alert("please input!");
            console.log(textarea);
            textarea.focus();
            return ;
        }

        //如果有评论，则调用函数利用ajax异步请求极速向服务器添加数据
        changeComment("add", textarea.value, this);
        //并取消评论框
		back();
	}

	//点击“BACK”时，退回评论框
	button2.onclick=function(){
		back();
	}
}
//评论框回到原处，释放结点
function back(){
	frame.style.transform='translate(0)';
    frame.style.transition='1.5s';
    //设置定时器，防止在动画执行完之前，评论框就消失
    setTimeout(function()
    	{
    		var textarea = document.getElementById("textarea");
    		var buttonArray = document.getElementById("buttonArray");
    		// console.log(textarea + ", " + buttonArray);
    		frame.removeChild(textarea);
    		frame.removeChild(buttonArray);
    	}, 1500);
    half.onclick = showOK;
}

//绑定删除评论的按钮的点击事件
$(document).on("click", ".deletebutton", function()
{
    console.log($(this).parent(".comment"));
    changeComment("delete", " ", this);
});

$(document).on("click", "#deleteBtn", function()
{
    var aid = getQueryString("aid");
    // alert(aid);
    $.ajax(
        {
            url : "./UpdateUserArticleNumberServlet",
            type : "POST",
            data : "aid=" + aid + "&operate=delete",
            dataType : "json",

            success : function(result)
            {
                if(result)
                {
                    alert("delete success!");
                    window.location = "./index";
                }
                else
                {
                    alert("delete fail!")
                }
            }
        }
    )
});



$(document).on("click", "#hiddenBtn", function()
{
    if(this.value.indexOf("show") != -1)
    {
        changeArticleStatus(this, "show");
    }
    else
    {
        changeArticleStatus(this, "hidden");
    }
});

function changeArticleStatus(obj, operate)
{
    var aid = getQueryString("aid");
    console.log(aid);
    $.ajax(
        {
            url : "./HiddenArticleServlet",
            data : "aid=" + aid + "&operate=" + operate,
            type : "post",
            dataType : "json",

            success : function(result)
            {
                if(result)
                {
                    alert(operate + " success!");
                    if(operate.indexOf("show") != -1)
                    {
                        $(obj).val("hidden");
                    }
                    else
                    {
                        $(obj).val("show");
                    }
                }
                else
                {
                    alert(operate + "fail!");
                }
            }
        }
    )
}
