var deletearry=document.getElementsByClassName("delete");
var upload=document.getElementsByClassName("f-upload")[0];
var main=document.getElementsByClassName("main")[0];
for(var i=0;i<deletearry.length;i++){
	deletearry[i].onclick=function(){
		setForbidden();
		down(this);
	}
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
                data : "filename=" + $(obj).prev().text(),
                dataType : "text",
                success : function(result)
                {
                    if(result.indexOf("true") != -1)
                    {
                        alert("delect success!");

                        main.removeChild(downloadmessage);
                        document.body.removeChild(document.getElementsByClassName("cover")[0]);
                        var $other = $(obj).parent(".list-message").siblings();
                        $(obj).parent(".list-message").animate(
                                {
                                    top : "-56px",
                                    // left : "500px",
                                    opacity : "0"
                                }, 1500, function()
                            {

                            });
                        $other.animate(
                            {
                                top : "-56px"
                            }, 1500, function()
                            {
                                $(this).css("top", "0");
                                $(obj).parent(".list-message").remove();
                            });
                        //
                        // console.log($(obj).parent(".list-message").get(0));
                        // console.log($("#list").get(0));
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
//弹出上传界面
upload.onclick=function(){
	setup();
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
	main.innerHTML+=up;
}
//取消上传界面
function cancelup(){
	main.removeChild(document.getElementsByClassName("upload-chose")[0]);
}
