//点击笑脸按钮 样式发生变化
var mouse=document.getElementsByClassName("mouth")[0];
$(".smiley").click(
    function(){
        if(mouse.style.height=='6px' || mouse.style.height==''){
            mouse.style.width='12px';
            mouse.style.height='3px';
            mouse.style.borderBottomLeftRadius='0px';
            mouse.style.borderBottomRightRadius='0px';
            ajaxChangeCollection($(this).attr("aid"), "delete");
        }
        else{
            mouse.style.width='12px';
            mouse.style.height='6px';
            mouse.style.borderBottomLeftRadius='6px';
            mouse.style.borderBottomRightRadius='6px';
            ajaxChangeCollection($(this).attr("aid"), "add");
        }
    }
)

function ajaxChangeCollection(aid, operate)
{
    // alert(aid + ", " + operate);
    var classification = "collection";
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
                        document.getElementById("collect-number").innerHTML = Number(document.getElementById("collect-number").innerHTML) + 1;
                    }
                    else
                    {
                        document.getElementById("collect-number").innerHTML = Number(document.getElementById("collect-number").innerHTML) - 1;
                    }
                }
            }
        }
    )
}
