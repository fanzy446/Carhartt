$(document).ready(function() {
	// 收货人员不能为空
	$("#recvName").blur(function() {
		if ($("#recvName").val() == "") {
			$("#recvNameTip").css("color", "#F33");
			$("#recvNameTip").html("*必填项！");
		} else {
			$("#recvNameTip").css("color", "#61C923");
			$("#recvNameTip").html("ok！");
		}
	});

	$("#recvName").focus(function() {
		$("#recvNameTip").html("");
	});

	// 收货地区不能为空
	$("#recvProvince").blur(function() {
		if ($("#recvProvince").val() == "") {
			$("#recvProvinceTip").css("color", "#F33");
			$("#recvProvinceTip").html("*必填项！");
		} else {
			$("#recvProvinceTip").css("color", "#61C923");
			$("#recvProvinceTip").html("ok！");
		}
	});

	$("#recvProvince").focus(function() {
		$("#recvProvinceTip").html("");
	});

	// 详细地址不能为空
	$("#recvAddress").blur(function() {
		if ($("#recvAddress").val() == "") {
			$("#recvAddressTip").css("color", "#F33");
			$("#recvAddressTip").html("*必填项！");
		} else {
			$("#recvAddressTip").css("color", "#61C923");
			$("#recvAddressTip").html("ok！");
		}
	});

	$("#recvAddress").focus(function() {
		$("#recvAddressTip").html("");
	});

	// 邮政编码不能为空，且必须是6位
	$("#recvZip").blur(function() {
		if ($("#recvZip").val() == "") {
			$("#recvZipTip").css("color", "#F33");
			$("#recvZipTip").html("*必填项！");
		} else if ($("#recvZip").val().length != 6) {
			$("#recvZipTip").css("color", "#F33");
			$("#recvZipTip").html("邮政编码格式不正确！");
		} else {
			$("#recvZipTip").css("color", "#61C923");
			$("#recvZipTip").html("ok！");
		}
	});

	$("#recvZip").focus(function() {
		$("#recvZipTip").html("");
	});

	// 手机号码不能为空，且必须是11位
	$("#recvPhone").blur(function() {
		if ($("#recvPhone").val() == "") {
			$("#recvPhoneTip").css("color", "#F33");
			$("#recvPhoneTip").html("*必填项！");
		} else if ($("#recvPhone").val().length != 11) {
			$("#recvPhoneTip").css("color", "#F33");
			$("#recvPhoneTip").html("手机号码格式不正确！");
		} else {
			$("#recvPhoneTip").css("color", "#61C923");
			$("#recvPhoneTip").html("ok！");
		}
	});

	$("#recvPhone").focus(function() {
		$("#recvPhoneTip").html("");
	});
});

// 点击已有地址
function toOldAddress() {
	var oDiv = document.getElementById('new_address_block');
	oDiv.style.display = 'none';
}

// 点击“添加新地址”
function toNewAddress() {
	var oDiv = document.getElementById('new_address_block');
	oDiv.style.display = 'block';
	document.getElementById("recvName").value = "";
	document.getElementById("recvAddress").value = "";
	document.getElementById("recvZip").value = "";
	document.getElementById("recvPhone").value = "";
	$("#recvNameTip").html("");
	$("#recvAddressTip").html("");
	$("#recvZipTip").html("");
	$("#recvPhoneTip").html("");
};

// 点击“保存地址”，提交数据，保存新增地址(需要保存到数据库)


// 点击“删除”按钮，删除已有地址，(修改数据库)
