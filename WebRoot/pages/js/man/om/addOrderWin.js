function addOrderWin(btnID) {
	Ext.regModel('BroadClass', {
				fields : [{
							type : "int",
							name : "broadClassId"
						}, {
							type : "string",
							name : "broadClassName"
						}]
			});

	var storeBroadClass = Ext.create('Ext.data.Store', {
				autoLoad : true,
				model : 'BroadClass',
				proxy : {
					type : 'ajax',
					url : '../../../../comMan/findAllBroadClass.action',
					reader : {
						type : 'json',
						root : 'broadClass'
					}
				}
			});
	var broadclassOrderbo = Ext.create('Ext.form.field.OrderboBox', {
		id : "broadclassOrderbo",
		name : 'commodity.broadClassName',
		fieldLabel : '所属大类',
		store : storeBroadClass,
		queryMode : 'remote',
		displayField : 'broadClassName'
			// valueField : 'broadClassCode'
			// 加上id就只能响应一次
		});

	function onBroadclassSelected() {
		var broadclass = broadclassOrderbo.getValue()
//		storeSubClass.load({
//					params : {
//						broadClassName : broadclass
//					}
//				});
		subclassOrderbo.clearValue();
	}
	broadclassOrderbo.on('select', onBroadclassSelected, null);

	// 所属小类
	Ext.regModel('SubClass', {
				fields : [{
							type : "int",
							name : "subClassId"
						}, {
							type : "string",
							name : "subClassName"
						}]
			});
	var storeSubClass = Ext.create('Ext.data.Store', {
				model : 'SubClass',
				autoLoad : false,
				proxy : {
					type : 'ajax',
					url : '../../../../comMan/findSubClassByBroad.action',
					reader : {
						type : 'json',
						root : 'subClass'
					}
				}
			});

	var subclassOrderbo = Ext.create('Ext.form.field.OrderboBox', {
		id : "subclassOrderbo",
		name : 'commodity.subClassName',
		fieldLabel : '所属小类',
		store : storeSubClass,
//		queryMode : 'remote',
		displayField : 'subClassName'
			// valueField : 'broadClassCode'
			// 加上id就只能响应一次
		});
	subclassOrderbo.clearListeners();
//	function onSubclassSelected() {
//		alert("sad");
//	}
//	subclassOrderbo.on('select', onSubclassSelected, null);
	win = Ext.create('widget.window', {
				title : '增添商品',
				id : "addWin",
				closable : true,
				animateTarget : btnID,
				width : 600,
				modal : true,
				minWidth : 350,
				height : 450,
				layout : {
					type : 'form',
					padding : 5
				},
				bodyStyle : "padding:5px 5px 0 10px",
				labelAlign : "left",
				labelWidth : 60,
				items : [{
							xtype : 'textfield',
							name : 'commodity.itemName',
							fieldLabel : '商品名称',
							value : 'Text field value'
						}, broadclassOrderbo, subclassOrderbo, {
							xtype : 'datefield',
							name : 'commodity.itemPubDate',
							value : new Date(),
							format : 'Y-m-d',
							fieldLabel : '发布时间'
						}, {
							xtype : 'filefield',
							name : 'commodity.itemPhoto',
							fieldLabel : '商品照片'
						}, {
							xtype : 'numberfield',
							name : 'commodity.itemCount',
							fieldLabel : '会员价格'
						}, {
							xtype : 'numberfield',
							name : 'commodity.itemCount',
							fieldLabel : '商品数量',
							value : 100,
							minValue : 0,
							maxValue : 1000
						}, {
							xtype : 'textfield',
							name : 'commodity.itemUnit',
							fieldLabel : '商品单位'
						}, {
							xtype : 'textareafield',
							name : 'commodity.itemDesc',
							fieldLabel : '商品介绍',
							value : 'Textarea value'
						}, {
							xtype : 'textareafield',
							name : 'commodity.remark',
							fieldLabel : '备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
							value : 'Textarea value'
						}],
				buttonAlign : 'center',
				minButtonWidth : 100,
				buttons : [{
					text : "提交",
					tooltip : "提交数据",
					handler : function() {
						if (Ext.getCmp("addOrderForm").getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
							Ext.getCmp("addOrderForm").getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据……", // 提交表单时进度条的信息
								url : _url, // 提交地址
								method : "POST", // 提交方式，需要大写
								success : function() { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "提交商品信息成功……",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addOrderWin").close(); // 根据id获取到表单的窗口，然后将其关闭
									_grid.getStore().reload(); //
									// 提交成功后，需要刷新GridPanel数据，

								},

								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("警告", "数据提交失败，请核对...",
											"msg-box-error");
								}
							});
						} else {// 如果表单验证未通过则提示用户骓未通过。

							Ext.example.msg("提示", "请填写完整、合法的日程信息...",
									"msg-box-error");
						}

					}

				}, {
					text : "取消",
					tooltip : "取消此操作",
					handler : function() {
						Ext.getCmp("addWin").close();// 取消实际上就是关闭窗口
					}
				}]
			});
	return win;
}