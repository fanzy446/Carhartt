
// 点击"保存密码"按钮时触发
function checkpasseditform() {
	var canSubmit = true;

	if ($("#input_old_psw").val() == "") {
		canSubmit = false;
	}
	if ($("#input_new_psw").val() == "") {
		canSubmit = false;
	}
	if ($("#input_new_psw_confirm").val() == "") {
		canSubmit = false;
	}
	if ($("#input_vali_code").val() == "") {
		canSubmit = false;
	}
	if ($("#input_new_psw").val().length < 6) {
		canSubmit = false;
	}
	if ($("#input_new_psw_confirm").val() != $("#input_new_psw").val()) {
		canSubmit = false;
	}

	if (canSubmit == true) {
		alert("提交成功！");
	} else {
		alert("提交不成功！");
	}
}

$(document).ready(
		function() {
			// 原密码不能为空
			$("#input_old_psw").blur(function() {
				if ($("#input_old_psw").val() == "") {
					$("#oldpasstip").css("color", "#F33");
					$("#oldpasstip").html("*必填项！");
				} else if ($("#input_old_psw").val().length < 6) {
					$("#oldpasstip").css("color", "#F33");
					$("#oldpasstip").html("密码长度必须大于6位！");
				} else {
					$("#oldpasstip").html("");
				}
			});

			$("#input_old_psw").focus(function() {
				$("#oldpasstip").html("");
			});

			// 新密码不能为空
			$("#input_new_psw").blur(function() {
				if ($("#input_new_psw").val() == "") {
					$("#newpasstip").css("color", "#F33");
					$("#newpasstip").html("*必填项！");
				} else if ($("#input_new_psw").val().length < 6) {
					$("#newpasstip").css("color", "#F33");
					$("#newpasstip").html("密码长度必须大于6位！");
				} else {
					$("#newpasstip").css("color", "#61C923");
					$("#newpasstip").html("ok！");
				}
			});

			$("#input_new_psw").focus(function() {
				$("#newpasstip").html("");
			});

			// 新密码确认不能为空，确认密码需要与新密码一致
			$("#input_new_psw_confirm").blur(
					function() {
						if ($("#input_new_psw_confirm").val() == "") {
							$("#newpassconfirmtip").css("color", "#F33");
							$("#newpassconfirmtip").html("*必填项！");
						} else if ($("#input_new_psw_confirm").val() != $(
								"#input_new_psw").val()) {
							$("#newpassconfirmtip").css("color", "#F33");
							$("#newpassconfirmtip").html("两次密码不一致！");
						} else {
							$("#newpassconfirmtip").css("color", "#61C923");
							$("#newpassconfirmtip").html("ok！");
						}
					});

			$("#input_new_psw_confirm").focus(function() {
				$("#newpassconfirmtip").html("");
			});

			// 验证码不能为空
			$("#input_vali_code").blur(function() {
				if ($("#input_vali_code").val() == "") {
					$("#valicodetip").css("color", "#F33");
					$("#valicodetip").html("*必填项！");
				} else {
					$("#valicodetip").html("");
				}
			});

			$("#input_vali_code").focus(function() {
				$("#valicodetip").html("");
			});
		});