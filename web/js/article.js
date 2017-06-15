var l=document.getElementById("like");
var frame=document.getElementById("frame");
var half=document.getElementsByClassName("half")[0];
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
//点击评论出现评论框
if(half != null)
{
    half.onclick=showOK;
}

function showOK(){
	fpushout();
	frame.style.transform='translate(-1740%)';
	frame.style.transition='1.5s';
	this.onclick = "";
}
//点赞效果
function like(obj){
	if(obj.style.color == 'rgb(206, 0, 0)')
    {
        obj.style.color='#FFFFFF';
        ajaxChangeLike($(obj).attr("aid"), "delete");
    }
	else
	{
		obj.style.color='#CE0000';
        ajaxChangeLike($(obj).attr("aid"), "add");
	}
}

//提交评论
function changeComment(operate, content, obj)
{
    var classification = "comment";
    var aid = getQueryString("aid");
    var comid = $(obj).parent(".comment").attr("comid");
    console.log(comid);

    $.ajax(
        {
            type : "POST",
            url : "./UpdateArticleNumberServlet",
            data : "aid=" + aid + "&operate=" + operate + "&classification=" + classification + "&commentContent=" + content + "&comid=" + comid,
            dataType : "json",
            success : function(result)
            {
                if(result)
                {
                    console.log(result);
                    // alert(operate + " success!");
                    if(operate == "add")
                    {
                        // comment = {"aid":0,"comid":6,"content":"let try!","createTime":{"date":13,"day":2,"hours":12,"minutes":26,"month":5,"seconds":6,"time":1497327966000,"timezoneOffset":-480,"year":117},"operateHeadPic":"./image/userHeadPic/1.jpg","operateNickname":"Sq_List","operateUid":1,"title":"","uid":0}
                        var comment = result;
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

                        $("#commentlist").append($node);
                        $($node).css(
                            {
                                position : "relative",
                                left : "870px",
                            }
                        ).animate(
                            {
                                left : "0px",
                            }, 1500, function()
                            {
                                position : "static"
                            }
                        )
                    }
                    else if(operate == "delete")
                    {
                        $(obj).parent(".comment").css("position", "relative");
                        var $other = $(obj).parent(".comment").nextAll().css("position", "relative");
                        $(obj).parent(".comment").animate(
                            {
                                // position : "relative",
                                top : "-102px",
                                opacity : "0"
                            }, 1500, function()
                            {

                            });
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

	button1.onclick=function(){
		//生成评论
        if(textarea.value == "" || textarea.value.length <= 0)
        {
            alert("please input!");
            console.log(textarea);
            textarea.focus();
            return ;
        }

        changeComment("add", textarea.value, this);
		back();
	}

	button2.onclick=function(){
		back();
	}
}
//评论框回到原处，释放结点
function back(){
	frame.style.transform='translate(0)';
    frame.style.transition='1.5s';
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
            url : "./DeleteArticleServlet",
            type : "POST",
            data : "aid=" + aid,
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
})

//鼠标移到评论区上会出现删除按钮
// for(var i=0;i<comment.length;i++){
// 	comment[i].i = i;
// 	comment[i].onmouseover=function(){
// 		setop(this);
// 		if(deletebutton[this.i] != null)
//         {
//             deletebutton[this.i].style.display='block';
//         }
// 	}
// 	comment[i].onmouseout=function(){
// 		cancelop(this);
//         if(deletebutton[this.i] != null)
//         {
//             deletebutton[this.i].style.display='none';
//         }
// 	}
// }
//改变评论区的透明度
// function setop(obj){
// 	obj.style.opacity='1';
// }
//
// function cancelop(obj){
// 	obj.style.opacity='0.7';
// }
