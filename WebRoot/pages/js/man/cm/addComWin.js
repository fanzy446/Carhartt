function addComWin(btnID) {
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

	broadCombo = Ext.create("Ext.form.ComboBox", {
				id : 'broadCombo',
				anchor : '100%',
				name : 'commodity.broadClassName',
				fieldLabel : '所属大类',
				store : storeBroadClass,
				displayField : 'broadClassName',
				valueField : 'broadClassName',
				// valueField : 'broadClassId',
				forceSelection : true,
				allowBlank : false,
				listeners : {
					change : function(broadCombo) {
						var broadClass = broadCombo.getValue();
						var subStore = Ext.getCmp("subCombo").getStore();
						subStore.clearFilter();
						subStore.filter("broadClassName", broadClass);
						subCombo.clearValue();
					}
				}
			});

	// 所属小类
	Ext.regModel('SubClass', {
				fields : [{
							type : "string",
							name : "broadClassName"
						}, {
							type : "string",
							name : "subClassName"
						}]
			});
	var storeSubClass = Ext.create('Ext.data.Store', {
				model : 'SubClass',
				autoLoad : true,
				proxy : {
					type : 'ajax',
					url : '../../../../comMan/findAllSubClass.action',
					reader : {
						type : 'json',
						root : 'subClass'
					}
				}
			});
	subCombo = Ext.create("Ext.form.ComboBox", {
				id : 'subCombo',
				anchor : '100%',
				name : 'commodity.subClassName',
				fieldLabel : '所属小类',
				displayField : 'subClassName',
				valueField : 'subClassName',
				store : storeSubClass,
				allowBlank : false,
				forceSelection : true
			});
	win = Ext.create('widget.window', {
				id : "addComWin",
				title : '增添商品',
				width : 600,
				height : 430,
				closable : true,
				resizable : false,
				animateTarget : btnID,
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
					items : [{
								xtype : 'textfield',
								anchor : '100%',
								name : 'commodity.itemName',
								fieldLabel : '商品名称',
								allowBlank : false,
								emptyText : '请输入商品名称'
							}, broadCombo, subCombo, {
								xtype : 'datefield',
								anchor : '100%',
								name : 'commodity.itemPubDate',
								value : new Date(),
								format : 'Y-m-d',
								allowBlank : false,
								fieldLabel : '发布时间'
							}, {
								xtype : 'filefield',
								anchor : '100%',
								name : 'upload',
								fieldLabel : '商品照片',
								validator : function(value) {
									var arr = value.split('.');
									if (arr[arr.length - 1] != 'jpg'
											&& arr[arr.length - 1] != 'JPG'
											&& arr[arr.length - 1] != 'JPEG') {
										return "上传图片格式仅限于jpg";
									} else {
										return true;
									}
								}
							}, {
								xtype : 'numberfield',
								anchor : '100%',
								name : 'commodity.itemPrice',
								allowBlank : false,
								fieldLabel : '会员价格'
							}, {
								xtype : 'numberfield',
								anchor : '100%',
								name : 'commodity.itemCount',
								fieldLabel : '商品数量',
								allowBlank : false,
								value : 100,
								minValue : 0,
								maxValue : 100000000000
							}, {
								xtype : 'textfield',
								anchor : '100%',
								name : 'commodity.itemUnit',
								fieldLabel : '商品单位'
							}, {
								xtype : 'textareafield',
								anchor : '100%',
								name : 'commodity.itemDesc',
								fieldLabel : '商品介绍',
								emptyText : '请输入商品介绍'
							}, {
								xtype : 'textareafield',
								anchor : '100%',
								name : 'commodity.remark',
								fieldLabel : '备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
								emptyText : '请输入商品备注'
							}]
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
								url : '../../../../comMan/addCommodity.action', // 提交地址
								method : "POST", // 提交方式，需要大写
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "提交商品信息成功",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addComWin").close(); // 根据id获取到表单的窗口，然后将其关闭
									_grid.getStore().reload(); //
									// 提交成功后，需要刷新GridPanel数据，

								},

								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("警告", "数据提交失败，请核对...",
											"msg-box-error");
								}
							});
						} else {// 如果表单验证未通过则提示用户骓未通过。

							Ext.example.msg("提示", "请填写完整、合法的商品信息",
									"msg-box-error");
						}

					}

				}, {
					text : "取消",
					tooltip : "取消此操作",
					handler : function() {
						Ext.getCmp("addComWin").close();// 取消实际上就是关闭窗口
						Ext.example.msg("取消", "取消增加操作");
					}
				}]
			});
	return win;
}