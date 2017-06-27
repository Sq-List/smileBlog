$(
    function()
    {
        //设置定时器，每隔1s就去请求服务器获取消息数
        setInterval(function()
        {
            //ajax异步请求技术
            $.ajax(
                {
                    //请求地址为“./AjaxGetMessageNumberServlet”
                    url : "./AjaxGetMessageNumberServlet",
                    //方式为post
                    type : "post",

                    //服务器请求成功后执行的函数
                    success : function(num)
                    {
                        //如果消息数为0则不显示
                        if(num == "0")
                        {
                            $("#message-number").text(" ").removeClass("message-number");
                        }
                        //如果消息数不为0则显示消息数
                        else
                        {
                            $("#message-number").addClass("message-number").text(num);
                        }
                    }
                }
            )
        }, 1000);
    }
)
