<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/address.css" type="text/css"></link>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript" src="../../js/user/jquery.js"></script>
<script type="text/javascript" src="../../js/user/address.js"></script>
<script type="text/javascript">
	function toSaveAddress() {
		// 判断能否上传数据
		var canSubmit = true;
		if ($("#recvName").val() == "" || $("#recvProvince").val() == ""
				|| $("#recvAddress").val() == "" || $("#recvZip").val() == ""
				|| $("#recvZip").val().length != 6
				|| $("#recvPhone").val() == ""
				|| $("#recvPhone").val().length != 11) {
			canSubmit = false;
		}

		if (canSubmit == true) {
			
			// 将新地址加到上面的table里
			
			var recvName = document.getElementById("recvName").value;
			var recvAddress = document.getElementById("recvAddress").value;
			var recvZip = document.getElementById("recvZip").value;
			var recvPhone = document.getElementById("recvPhone").value;
			var table = document.getElementById("recv_infor_array");
			var newTr = table.insertRow(1);
			var td0 = newTr.insertCell();

			td0.innerHTML = "<tr><td>&nbsp;&nbsp;<span><input type=\"radio\" name=\"addr\"checked=\"checked\" />&nbsp;"
					+ recvName
					+ "&nbsp;"
					+ recvAddress
					+ "&nbsp;"
					+ recvZip
					+ "&nbsp;"
					+ recvPhone
					+ "</span></td></tr>";

			// 将使用新地址框缩回去
			var oDiv = document.getElementById('new_address_block');
			oDiv.style.display = 'none';
			
			
			// ajax保存新地址
			var userParam = {
				"consigneeName" : $("#recvName").val(),
				"consigneePhone" : $("#recvPhone").val(),
				"postcode" : $("#recvZip").val(),
				"fullAddress" : $("#recvAddress").val(),
			};
			$.ajax({
				url : '../../../addaddress',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					var state = "1";
					var returnstate = data['statement'];
					if (returnstate == state) {
						alert("添加成功！");
					}
				}
			});

			
		} else {
			alert('提交失败');
		}
	}

	function getaddress() {
		$
				.ajax({
					url : '../../../getaddress',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						var text;
						text = '<table class="table-hover" cellspacing="0" id="recv_infor_array"><tr><td class="detail_td">&nbsp;收货相关信息<a id="delete_addr" style="float: right;margin-right: 20px;cursor: pointer;" onclick="del();">删除</a></td></tr>';
						for ( var i = 0; i < data['size']; i++) {
							text += '<tr><td>&nbsp;&nbsp;<span><input type="radio" name="addr" checked="checked" />';
							text += '&nbsp;' + data[i]['ConsigneeName']
									+ '&nbsp;' + data[i]['ConsigneeAddress']
									+ '&nbsp;' + data[i]['ConsigneePostcode']
									+ '&nbsp;' + data[i]['ConsigneePhone'];

							text += '</span></td></tr>';
						}
						text += '<tr><td class="new_td">&nbsp;&nbsp;<span><input id="new" type="radio" name="addr" onclick="toNewAddress()" />&nbsp;使用新地址</span></td></tr></table>';
						document.getElementById("address").innerHTML = text;

					}
				});
	}

	function del() {
		var oradio = document.getElementsByName('addr');
		var otable = document.getElementById('recv_infor_array');
		var i = 0;
		var i2 = 0;
		for (i = 0; i < oradio.length - 1; i++) {
			if (oradio[i].checked) {
				alert(i);
				i2 = i;
				otable.deleteRow(i + 1);
			}
		}
		var userParam = {
			"num" : i2
		};
		$.ajax({
			url : '../../../deladdress',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
			}
		});
	}

	function getboardinfo()
	{
		$.ajax({
			url : '../../../getannouncement',
			type : 'post',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
				document.getElementById("board1").value=data[0]['Title'];
				document.getElementById("board2").value=data[1]['Title'];
				document.getElementById("board3").value=data[2]['Title'];
			}
		});
	}

	window.onload = function() {
		getaddress();
		getboardinfo();
	};
</script>


<div class="addr_main">
	<div class="addr_top">
		<img src="../../source/image/add.jpg"></img>
	</div>
	<div class="hello">
		<span>收货相关信息</span><a id="delete_addr" onclick="del()"
			style="cursor:pointer;">删除地址</a>
	</div>
	<div class="addr_pane">
		<div id="address" class="addr_list"></div>

		<div id="new_address_block" class="addr_new">

			<table cellspacing="0">
				<tr>
					<td width="100px"><span><strong
							style="color: orange;font-size: 25px;">*</strong>&nbsp;收货人员:</span>
					</td>
					<td><input class="new_input" type="text" id="recvName" />&nbsp;
						<h5 id="recvNameTip" style="display: inline;"></h5>
					</td>
				</tr>
				<tr>
					<td><span><strong
							style="color: orange;font-size: 25px;">*</strong>&nbsp;详细地址:</span>
					</td>
					<td><input class="new_input" type="text" id="recvAddress" />&nbsp;
						<h5 id="recvAddressTip" style="display: inline;"></h5>
					</td>
				</tr>
				<tr>
					<td><span><strong
							style="color: orange;font-size: 25px;">*</strong>&nbsp;邮政编码:</span>
					</td>
					<td><input class="new_input" type="text" id="recvZip"
						style="ime-mode:disabled"
						onkeydown="if(event.keyCode==13)event.keyCode=9"
						onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />&nbsp;
						<h5 id="recvZipTip" style="display: inline;"></h5>
					</td>
				</tr>
				<tr>
					<td><span><strong
							style="color: orange;font-size: 25px;">*</strong>&nbsp;手机号码:</span>
					</td>
					<td><input class="new_input" type="text" id="recvPhone"
						style="ime-mode:disabled"
						onkeydown="if(event.keyCode==13)event.keyCode=9"
						onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />&nbsp;
						<h5 id="recvPhoneTip" style="display: inline;"></h5>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn" type="button" value="保存信息"
						onclick="toSaveAddress()" />&nbsp;&nbsp;<input class="btn"
						type="button" value="重新设置" />
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="ok"></div>
	<div class="addr_ad">
		<img src="../../source/image/add2.jpg"></img>
	</div>
	<div class="ok"></div>
</div>
