function test() {

	var wrappedImage = Ext.create('Ext.Img', {
				src : '../../../../resource/images/commodity/18.jpg',
				autoEl : 'div' // wrap in a div
			});
	win = Ext.create('widget.window', {
				id : "addComWin",
				title : '增添商品',
				width : 600,
				height : 430,
				closable : true,
				resizable : false,
				// animateTarget : btnID,
				modal : true,
				items : [{
							xtype : "form",
							labelWidth : 60,
							id : "addComForm",
							frame : true,
							bodyStyle : "padding:5px 5px 0",
							border : false,
							waitMsgTarget : true,
							labelAlign : "right",
							labelPad : 10,
							defaultType : "textfield",
							items : [wrappedImage]
						}],
				buttonAlign : 'center',
				minButtonWidth : 100,
				buttons : [{
					text : "提交",
					tooltip : "提交数据",
					handler : function() {
						if (Ext.getCmp("addComForm").getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
							Ext.getCmp("addComForm").getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据……", // 提交表单时进度条的信息
								url : '../../../../fileload//fileload.action', // 提交地址
								method : "POST", // 提交方式，需要大写
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "提交商品信息成功……",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addComWin").close(); // 根据id获取到表单的窗口，然后将其关闭
									_grid.getStore().reload(); //
									// 提交成功后，需要刷新GridPanel数据，
									console.log(action.result.success);
									// console.log("===" +
									// respText.get("success"));

								},

								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("警告", "数据提交失败，请核对...",
											"msg-box-error");
								}
							});
						} else {// 如果表单验证未通过则提示用户骓未通过。

							Ext.example.msg("提示", "请填写完整、合法的商品信息...",
									"msg-box-error");
						}

					}

				}, {
					text : "取消",
					tooltip : "取消此操作",
					handler : function() {
						Ext.getCmp("addComWin").close();// 取消实际上就是关闭窗口
					}
				}]
			});
	return win;
}