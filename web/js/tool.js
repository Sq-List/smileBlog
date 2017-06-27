// var deletearry=document.getElementsByClassName("delete");
var upload=document.getElementsByClassName("f-upload")[0];
var main=document.getElementsByClassName("main")[0];
// for(var i=0;i<deletearry.length;i++){
// 	deletearry[i].onclick=function(){
// 		setForbidden();
// 		down(this);
// 	}
// }

//绑定删除按键的点击事件
$(document).on("click", ".delete", function()
{
    setForbidden();
    down(this);
});

//选择上传文件后显示名字
function changeName()
{
    // console.log($("#xFile").val().split("\\"));
    $("#filename").text($("#xFile").val().split("\\")[2]);
}

//弹出是否删除框
function down(obj){
    var downloadmessage=document.createElement("div");
    var button1=document.createElement("input");
    var button2=document.createElement("input");
    button1.setAttribute("type","submit");
    button2.setAttribute("type","button");
    button1.setAttribute("value","delete");
    button2.setAttribute("value","cancle");
    downloadmessage.setAttribute("class","download-message");
    downloadmessage.appendChild(button1);
    downloadmessage.appendChild(button2);
    main.appendChild(downloadmessage);

    button1.onclick = function()
    {
        //点击删除按键触发事件
        $.ajax(
            {
                //方式为POTS
                type : "POST",
                //请求的地址为“./AjaxDelectToolServlet”
                url : "./AjaxDelectToolServlet",
                //请求的数据
                data : "filename=" + $(obj).prev().text() + "&type=tool",
                //服务器返回数据的接收方式
                dataType : "text",

                //服务器成功返回数据执行的函数
                success : function(result)
                {
                    if(result.indexOf("true") != -1)
                    {
                        //提示删除成功
                        alert("delect success!");

                        //移除确认删除框
                        main.removeChild(downloadmessage);
                        //移除禁用背景
                        document.body.removeChild(document.getElementsByClassName("cover")[0]);
                        //获取被删除工具结点的后面部分结点
                        var $other = $(obj).parent(".list-message").nextAll();
                        //被删除工具结点动画
                        $(obj).parent(".list-message").animate(
                            {
                                top : "-56px",
                                // left : "500px",
                                opacity : "0"
                            }, 1500, function()
                            {
                                $(this).parent(".list-message").remove();
                            });
                        //被删除工具结点的后面部分结点动画
                        $other.animate(
                            {
                                top : "-56px"
                            }, 1500, function()
                            {
                                $(this).css("top", "0");
                                // console.log($(obj).parent(".list-message").get(0));
                                $(obj).parent(".list-message").remove();
                            });
                    }
                    else
                    {
                        //否则提示错误消息
                        alert(result);
                    }
                }
            }
        )
    }
    //点击取消，取消禁用
    button2.onclick=function(){
        main.removeChild(downloadmessage);
        document.body.removeChild(document.getElementsByClassName("cover")[0]);
    }
}


//点击"upload"按键后显示上传框
function setup(){
    var up = '<div class="upload-chose">'+
        '<div class="chose">'+
        '<label for="xFile">choose</label>' +
        '<span name="filename" id="filename">← to choose file</span>' +
        '<form  action="./UploadServlet" method="post" id="uploadForm" enctype="multipart/form-data">' +
        '<input type="file" name="xFile" id="xFile" style="position:absolute;clip:rect(0 0 0 0);" onchange="changeName()">' +
        '</form>'+
        '</div>'+
        '<div class="progress">' +
        '<div id="progress-line">' +
        '<div id="line"></div>' +
        '</div>' +
        '</div>'+
        '<div class="buttonArr">'+
        '<input type="submit" name="" id="upload" value="upload">'+
        '<input type="button" name="" value="cancle" onclick="cancelup()">'+
        '</div>'+
        '</div>';
    main.innerHTML+=up;
}

//弹出上传界面
$(document).on("click", "#f-upload", function()
{
    setup();
});

//绑定触发上传
$(document).on("click", "#upload", function()
    {
        //判断是否有选择文件
        if($("#xFile").val() == "")
        {
            //没有选择文件则显示提示
            alert("choose file first!");
            return ;
        }

        //获取上传的表单
        var form = document.getElementById("uploadForm");
        //新建表单对象
        var formData = new FormData(form);
        console.log(formData);

        //ajax异步请求
        $.ajax(
            {
                //请求路径为"./AjaxUploadServlet"
                url: "./AjaxUploadServlet",
                //请求的数据为
                data: formData,
                //请求的方式为post
                type: "POST",
                //规定通过请求发送的数据是否转换为查询字符串
                processData: false,
                //设置发送数据到服务器时所使用的内容类型
                contentType: false,
                //接收服务器返回数据的类型
                dataType: "json",

                success: function(tool)
                {
                    //清楚定时器
                    clearInterval(getRate);
                    //将进度条的长度设为100%
                    $("#line").css("width", "100%");
                    //取消上传框
                    cancelup();
                    //提示上传成功
                    alert("upload success!");

                    //新建工具结点
                    var $newDiv = $('<div class="list-message">'+
                        '<div class="file-name">' + tool.name + '</div>'+
                        '<div class="delete">delete</div>'+
                        '<div class="download">'+
                        '<a href="./DownloadToolServlet?filename=' + tool.name + '">download</a>'+
                        '</div>'+
                        '<div class="file-time">' + (tool.createTime.year + 1990) + '-' + (tool.createTime.month + 1) + '-' + tool.createTime.date + '</div>'+
                        '</div>');
                    var $list = $("#list").append($newDiv);

                    //设置新建工具结点的css样式并进行动画
                    $newDiv.css(
                        {
                            opacity : "0",
                            left : "812px"
                        }
                    ).animate(
                        {
                            opacity : "1",
                            left : "0"
                        }, 1500
                    );

                    //绑定各个工具删除按键的点击事件
                    $(document).on("click", ".delete", function()
                    {
                        setForbidden();
                        down(this);
                    });
                },
                //服务器返回错误时执行的函数
                error: function(result)
                {
                    console.log("error : " + result);
                    clearInterval(getRate);
                }
            }
        );

        //设置定时器每隔一段通过ajax请求进度
        //类型为get, 因为post为上传的方式
        var getRate = setInterval(
            function()
            {
                //ajax异步请求
                $.ajax(
                    {
                        //请求路径为"./AjaxUploadServlet"
                        url: "./AjaxUploadServlet",
                        //请求方式为get
                        type: "GET",
                        //接收服务器返回数据的类型
                        dataType: "text",

                        success: function(progress)
                        {
                            //每次拿到上传进度并更改进度条的长度
                            $("#line").css("width", progress + "%");
                        }
                    }
                )
            }
            , 150);
    }
)

//取消上传界面
function cancelup(){
    main.removeChild(document.getElementsByClassName("upload-chose")[0]);
}
