var liarray=document.getElementsByTagName("li");
var firstshow=document.getElementsByClassName("first-show")[0];
var home=document.getElementsByTagName("h3")[0];

function _hyz(obj)
{
    var img = document.getElementById(obj);
    // 需要给出一个参数，这个参数每次都不同，这样清楚浏览器缓存!
    img.src = "./VerifyCodeServlet?a=" + new Date().getTime();
}

for(i=0;i<liarray.length-1;i++){
	liarray[i].onclick=function(){
		//将之前的信息隐藏
		document.getElementById("USERS").style.display='none';
		document.getElementById("ARTICLES").style.display='none';
		document.getElementById("TOOLS").style.display='none';
		firstshow.style.display='none';
		set(this);
	}
}

//点击相应的标签获取相应的信息
function set(obj){
    $(".main-search").show();
	var list=obj.innerHTML;
	document.getElementById("classify").innerHTML=list;
	document.getElementById(list).style.display='block';
}

//回到原始页面
home.onclick=function(){
	document.getElementById("USERS").style.display='none';
	document.getElementById("ARTICLES").style.display='none';
	document.getElementById("TOOLS").style.display='none';
	document.getElementById("classify").innerHTML='';
	$(".main-search").hide();
	firstshow.style.display='block';
};

$(document).on("click", ".delete-btn", function()
{
    var classification = $(this).attr("classification");
    var id = $(this).attr("classification-id");
    var $thisBtn = $(this);

    if(confirm("Do you want to delete?"))
    {
        $.ajax(
            {
                url : "./AdminDelete" + classification + "Servlet",
                data : "id=" + id,
                type : "post",
                dataType : "json",

                success : function(result)
                {
                    if(result)
                    {
                        alert("delete success!");
                        $thisBtn.parent().parent().remove();
                    }
                    else
                    {
                        alert("delete fail!");
                    }
                },

                error : function()
                {
                    alert("delete fail!");
                }
            }
        )
    }
});

$(document).on("click", ".limit-btn", function()
{
    var classification = $(this).attr("classification");
    var id = $(this).attr("classification-id");
    var operate = $(this).val();
    var $thisBtn = $(this);

    if(confirm("Do you want to limit?") == true)
    {
        $.ajax(
            {
                url : "./AdminLimitUserServlet",
                data : "id=" + id + "&operate=" + operate,
                type : "post",
                dataType : "json",

                success : function(result)
                {
                    if(result)
                    {
                        alert(operate + " success!");
                        if(operate.indexOf("limit") != -1)
                        {
                            $thisBtn.val("unfreeze");
                        }
                        else
                        {
                            $thisBtn.val("limit");
                        }
                    }
                    else
                    {
                        alert(operate + " fail!");
                    }
                },

                error : function()
                {
                    alert(operate + " fail!");
                }
            }
        )
    }
});

$(
    function()
    {
        $(".main-search").hide();
    }
);
