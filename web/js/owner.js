//获取当前时间
window.onload=function showtime(){
	var myDate=new Date();
	var mytime=myDate.toLocaleDateString(); 
	document.getElementById("write").innerHTML=mytime;
}

$(
    function()
    {
        $(window.document).scroll(
            function()
            {
                console.log(document.body.clientHeight);
                var viewH = $(this).height(),//可见高度
                    contentH = $(".container").get(0).scrollHeight,//内容高度
                    scrollTop = $(this).scrollTop();//滚动高度
                console.log(viewH + ", " + contentH + ", " + scrollTop);
                //if(contentH - viewH - scrollTop <= 100) //到达底部100px时,加载新内容
                if(scrollTop/(contentH -viewH) >= 0.95)
                { //到达底部100px时,加载新内容
                    $(".container").css(
                        {
                            height : "+=300px",
                        }
                    )
                }
                // $(".container").css(
                //     {
                //         height : "+=300px",
                //     }
                // )
            }
        );
    }
)
