// var deletearry=document.getElementsByClassName("delete");
var upload=document.getElementsByClassName("f-upload")[0];
var main=document.getElementsByClassName("main")[0];
// for(var i=0;i<deletearry.length;i++){
// 	deletearry[i].onclick=function(){
// 		setForbidden();
// 		down(this);
// 	}
// }

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
        $.ajax(
            {
                type : "POST",
                url : "./AjaxDelectToolServlet",
                data : "filename=" + $(obj).prev().text() + "&type=tool",
                dataType : "text",
                success : function(result)
                {
                    if(result.indexOf("true") != -1)
                    {
                        alert("delect success!");

                        main.removeChild(downloadmessage);
                        document.body.removeChild(document.getElementsByClassName("cover")[0]);
                        var $other = $(obj).parent(".list-message").nextAll();
                        $(obj).parent(".list-message").animate(
                                {
                                    top : "-56px",
                                    // left : "500px",
                                    opacity : "0"
                                }, 1500, function()
                            {
                                $(this).parent(".list-message").remove();
                            });
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


function setup(){
	var up='<div class="upload-chose">'+
				'<div class="chose">'+
					'<input type="button" name="" value="chose">'+
				'</div>'+
				'<div class="progress"><div></div></div>'+
				'<div class="buttonArr">'+
					'<input type="submit" name="" value="upload">'+
					'<input type="button" name="" value="cancle" onclick="cancelup()">'+
				'</div>'+
			'</div>';

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
        if($("#xFile").val() == "")
        {
            alert("choose file first!");
            return ;
        }
        var form = document.getElementById("uploadForm");
        var formData = new FormData(form);
        console.log(formData);

        $.ajax(
            {
                url: "./AjaxUploadServlet",
                data: formData,
                type: "POST",
                processData: false,
                contentType: false,
                dataType: "json",

                success: function(tool)
                {
                    clearInterval(getRate);
                    $("#line").css("width", "100%");
                    cancelup();
                    alert("upload success!");

                    var $newDiv = $('<div class="list-message">'+
                        '<div class="file-name">' + tool.name + '</div>'+
                        '<div class="delete">delete</div>'+
                        '<div class="download">'+
                        '<a href="./DownloadToolServlet?filename=' + tool.name + '">download</a>'+
                        '</div>'+
                        '<div class="file-time">' + (tool.createTime.year + 1990) + '-' + (tool.createTime.month + 1) + '-' + tool.createTime.date + '</div>'+
                        '</div>');
                    var $list = $("#list").append($newDiv);

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
                    )

                    $(document).on("click", ".delete", function()
                    {
                        setForbidden();
                        down(this);
                    });
                },
                error: function(result)
                {
                    console.log("error : " + result);
                    clearInterval(getRate);
                }
            }
    );

//            设置定时器每隔一段通过ajax请求进度
//            类型为get, 因为post为上传的方式
        var getRate = setInterval(
            function()
            {
                $.ajax(
                    {
                        url: "./AjaxUploadServlet",
                        type: "GET",
                        dataType: "text",

                        success: function(progress)
                        {
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
