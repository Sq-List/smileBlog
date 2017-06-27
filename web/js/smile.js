//点击笑脸按钮 样式发生变化
$(".smiley").click(
    function(){
        var month = $(this).children("div.mouth").get(0);
        if(month.style.height=='6px' || month.style.height==''){
            month.style.width='12px';
            month.style.height='3px';
            month.style.borderBottomLeftRadius='0px';
            month.style.borderBottomRightRadius='0px';
            ajaxChangeCollection($(this).attr("aid"), "delete");
        }
        else{
            month.style.width='12px';
            month.style.height='6px';
            month.style.borderBottomLeftRadius='6px';
            month.style.borderBottomRightRadius='6px';
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
                    if(document.getElementById("collect-number") != null)
                    {
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
        }
    )
}
