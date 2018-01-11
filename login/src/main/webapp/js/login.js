$(function(){

	// 验证用户名
	$("#userName").blur(function(){
		validateName($(this));		
	});

	// 验证密码
	$("#password").blur(function(){
		validatePassword($(this));
	});
	//验证重复密码
    $("#passwordRepeater").blur(function(){
        validatePasswordRepeater($(this));
    });

	// 验证验证码
	$("#authCode").blur(function(){
		validateCode($(this));		
	});

	// 验证工资
	$("#salary").blur(function(){
		validateSalary($(this));
	});


	// 切换验证码
	$("#validateCode").click(function(){
		$(this).attr("src", '/user/validateCode.do?date='+(new Date()).toString());
	});

	// 上传图片
	$("#imgSize1File").change(function(){
        submitImgSize1Upload();
	});

	// 登陆系统
	$("#submit1").submit(function(){
		var flagName = validateName($("#userName"));
		var flagPwd = validatePassword($("#password"));
        validateCode($("#authCode"));
		var flagAuth = codeHi();
		if(flagAuth && flagName && flagPwd) {
			return true;
		}
		return false;
	});


	// 注册用户
	$("#registering").submit(function(){
        var flagName = validateName($("#userName"));
        var flagPwd = validatePassword($("#password"));
        var flagPwdR = validatePasswordRepeater($("#passwordRepeater"));
		if (flagPwd && flagName && flagPwdR) {
			var pwd = $("#password").val();
			$("#userPassword").val(pwd);
			return true ;
		}
		return false;

	});
	
});
/*验证用户名*/
function validateName(obj){
	var name=obj.val().trim();
	var pattern=/^[0-9a-zA-Z\u4e00-\u9fa5_]{2,5}$/;	
	if(!pattern.test(name)){
		obj.next().remove();
		obj.after("<font color='red'>×：请输入用户名2-5位之间</font>");
		return false;
	}
	obj.next().remove();
	obj.after("<font color='green'>√</font>");
	return true;
		
}
/*验证密码*/
function validatePassword(obj){
	var pwd = obj.val().trim();
	var pattern = /^\w{6,13}$/;
	if (!pattern.test(pwd)) {
		obj.next().remove();
		obj.after("<font color='red'>×：密碼在6-13位之间</font>");
		return false;
	}
	obj.next().remove();
	obj.after("<font color='green'>√</font>");
	return true;	
	
}

/*重复密码验证*/
function validatePasswordRepeater(obj){
    var pwd = $("#password").val().trim();
    var pwdr = obj.val().trim();
    var pattern = /^\w{6,13}$/;
    if (!pattern.test(pwdr)) {
        obj.next().remove();
        obj.after("<font color='red'>×：密碼在6-13位之间</font>");
        return false;
    }
    if (pwdr != pwd) {
        obj.next().remove();
        obj.after("<font color='red'>×：两次密码不一致</font>");
        return false;

    }

    obj.next().remove();
    obj.after("<font color='green'>√</font>");
    return true;

}

/*验证数字*/
function validateSalary(obj){
	var salary = obj.val();
	var pattern = /^[1-9]\d{0,5}$/;
	if (!pattern.test(salary)) {
        obj.next().remove();
        obj.after("<font color='red'>×：工资是由6位数字组成</font>");
        return false;
	}

    obj.next().remove();
    obj.after("<font color='green'>√</font>");
    return true;
}

/*验证验证码*/
function validateCode(obj){
	var code = obj.val();
    $.ajax({
		type:"POST",
		url: '/user/authCode.do',
		dataType:"text",
		data:{"authCode":code},
		success: function(result){
			if (result == "false") {
				obj.next().remove();
				obj.after("<font color='red'>×：验证码错误</font>");
				$("#codeHi").val("1");
			} else {
                obj.next().remove();
                $("#codeHi").val("2");
                obj.after("<font color='green'>√</font>");

            }
    }});
}
// 隐藏验证码
function codeHi(){
	var flag = $("#codeHi").val();
	if (flag == "1") {
		return false;
	}
	return true;

}

// 提交图片
function submitImgSize1Upload() {
    var option = {
        type : 'POST',
        url : 'uploadPic.do',
        dataType : 'text',
        data : {
            fileName : 'imgSize1File'
        },
        success : function(data) {

            //把json格式的字符串转换成json对象
            var jsonObj = $.parseJSON(data);
            //返回服务器图片路径，把图片路径设置给img标签
            $("#imgSize1ImgSrc").attr("src", jsonObj.fullPath);
            //数据库保存相对路径
            $("#imgSize1").val(jsonObj.relativePath);
        }

    };

    $("#registering").ajaxSubmit(option);

}

