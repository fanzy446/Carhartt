<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/feedback.css"
	type="text/css"></link>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript">
	function toFeedbackInput() {
		var ofip = document.getElementById('fip');
		var ofrp = document.getElementById('frp_page');
		ofip.style.display = 'block';
		ofrp.style.display = 'none';
	}

	function toFeedbackMsg() {
		var ofip = document.getElementById('fip');
		var ofrp = document.getElementById('frp_page');
		ofip.style.display = 'none';
		ofrp.style.display = 'block';
	}
	function submitMessage() {
		//检测是否符合提交条件
		var canSubmit = true;

		if ($("#inputTitle").val() == "" || $("#inputTitle").val().length > 10
				|| $("#inputContent").val() == ""
				|| $("#inputContent").val().length < 10) {
			canSubmit = false;
		}
		if (canSubmit == true) {

			var obj = document.getElementsByName("feedback_type");
			var i = 0;
			for (; i < obj.length; i++) {
				if (obj[i].checked == true) {
					break;
				}
			}

			var userParam = {
				"mesgContent" : $("#inputContent").val(),
				"mesgTitle" : $("#inputTitle").val(),
				"mesgType" : $("#input" + i).val()
			};
			$.ajax({
				url : '../../../addmessage',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					alert("提交成功！感谢您宝贵的意见！");
					location.reload();
				}
			});

		} else {
			alert("请正确填写反馈信息！");
			alert("提交不成功！");
		}
	}
	function getmessage(obj) {
		var page = obj;
		alert(page);
		var para ={
			"page":page
		};
		$.ajax({
					url : '../../../getoneuserallmessage',
					data : para,
					type : 'post',
					dataType : 'json',
					success : function(data) {
						var text;
						text = '<table class="table table-hover" cellspacing="0"><tr class="tr_head"><td><span>反馈类型</span></td><td><span>反馈主题</span></td><td><span>反馈时间</span></td><td><span>反馈状态</span></td></td></tr>';
						for (var i =0; i < 10; i = i + 1) {
							text += '<tr><td><span>';
							var datatext = data[i]['Type'];
							var state0 = "0";
							var state1 = "1";
							var state2 = "2";
							var state3 = "3";
							var state4 = "4";
							if (datatext == state0) {
								text += "一般留言";
							} else if (datatext == state1) {
								text += "网站意见";
							} else if (datatext == state2) {
								text += "公司意见";
							} else if (datatext == state3) {
								text += "合作意向";
							} else if (datatext == state4) {
								text += "产品投诉";
							} else
								text += "服务投诉";
							text += '</span></td><td><span><a href="user_center_feedback_det.jsp?messageid='
									+ data[i]['messageid']
									+ '">'
									+ data[i]['Title']
									+ '</a></span></td><td><span>'
									+ data[i]['time']
									+ '</span></td><td><span>';
							text += "未回复";
							text += '</span></td></tr>';
						}
						text += "</table>";
						document.getElementById("frp").innerHTML = text;
					}
				});
	}
	function addpage()
	{
		page+=1;
		getmessage(page);
		return ture;
	};
	function delpage()
	{
		if(page==1)
		{
			alert("已经是第一页！");
			return false;
		}
		page-=1;
		getmessage(page);
		return ture;
	}
	window.onload = function() {
		var page=1;
		getmessage(page);
	};

	$(document).ready(function() {
		// 反馈详情不能为空
		$("#inputTitle").blur(function() {
			if ($("#inputTitle").val() == "") {
				$("#inputTitleTip").css("color", "#F33");
				$("#inputTitleTip").html("*必填项！");
			} else if ($("#inputTitle").val().length > 10) {
				$("#inputTitleTip").css("color", "#F33");
				$("#inputTitleTip").html("最多10字！");
			} else {
				$("#inputTitleTip").html("");
			}
		});

		$("#inputTitle").focus(function() {
			$("#inputTitleTip").html("");
		});

		// 反馈详情不能为空
		$("#inputContent").blur(function() {
			if ($("#inputContent").val() == "") {
				$("#inputContentTip").css("color", "#F33");
				$("#inputContentTip").html("*必填项！");
			} else if ($("#inputContent").val().length < 10) {
				$("#inputContentTip").css("color", "#F33");
				$("#inputContentTip").html("最少10字！");
			} else {
				$("#inputContentTip").html("");
			}
		});

		$("#inputContent").focus(function() {
			$("#inputContentTip").html("");
		});
	});
</script>

<div class="feedback_main">
	<div class="feedback_top_ad">
		<img src="../../source/image/add.jpg"></img>
	</div>

	<div class="feedback_intro">
		<img src="../../source/image/feedback.png"></img>
	</div>

	<div class="select_card">
		<input type="button" value="填写反馈" class="btn"
			onclick="toFeedbackInput()" /> <input type="button" value="我的反馈"
			class="btn" onclick="toFeedbackMsg()" />
	</div>
	<div id="fip" class="feedback_input_pane">
		<table cellspacing="0">
			<tr>
				<td width="100px"><span><strong
						style="color: orange;font-size: 25px;">*</strong>反馈类型:</span>
				</td>
				<td id="sel_feed_type"><input id="input0" value="0"
					type="radio" name="feedback_type" checked="checked" /><span>&nbsp;一般留言&nbsp;</span><input
					id="input1" value="1" type="radio" name="feedback_type" /><span>&nbsp;网站意见&nbsp;</span>
					<input id="input2" value="2" type="radio" name="feedback_type" /><span>公司意见&nbsp;</span>
					<input id="input3" value="3" type="radio" name="feedback_type" /><span>合作意向&nbsp;</span>
					<input id="input4" value="4" type="radio" name="feedback_type" /><span>产品投诉&nbsp;</span>
					<input id="input5" value="5" type="radio" name="feedback_type" /><span>服务投诉&nbsp;</span>
				</td>
			</tr>
			<tr>
				<td><span><strong style="color: orange;font-size: 25px;">*</strong>反馈主题:</span>
				</td>
				<td><input id="inputTitle" class="input_phone" type="text" />
					<h5 id="inputTitleTip" style="display: inline;"></h5></td>
			</tr>
			<tr>
				<td><span><strong style="color: orange;font-size: 25px;">*</strong>反馈详情:</span>
				</td>
				<td rowspan="4"><textarea id="inputContent" class="txt"></textarea>
					<h5 id="inputContentTip" style="display: inline;"></h5></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><span></span>
				</td>
				<td><input class="btn" type="button" onclick="submitMessage()"
					value="提交反馈" />
				</td>
			</tr>
		</table>
	</div>

	<div id="frp_page">
		<div id="frp" class="feedback_read_pane"></div>
		<div id="frpbutton" class="select_card">
			<input type="button" value="上一页" class="btn" onclick="addpage()"/>
			<input type="button" value="下一页" class="btn" onclick="delpage()"/>;
		</div>
	</div>
	<div class="ok"></div>
	<div class="feedback_ad">
		<img src="../../source/image/add2.jpg"></img>
	</div>
	<div class="ok"></div>
</div>
