function addClassWin(btnID) {
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
		name : 'newClassModel.oldBroadName',
		fieldLabel : '所属大类',
		store : storeBroadClass,
		displayField : 'broadClassName',
		valueField : 'broadClassName',
		// valueField : 'broadClassId',
		disabled : true
			// forceSelection : true
		});

	win = Ext.create('widget.window', {
		id : "addClassWin",
		title : '增添类别',
		width : 300,
		height : 182,
		closable : true,
		resizable : false,
		animateTarget : btnID,
		modal : true,
		items : [{
			xtype : "form",
			labelWidth : 60,
			id : "addClassForm",
			frame : true,
			bodyStyle : "padding:5px 5px 0",
			border : true,
			
			waitMsgTarget : true,
			labelAlign : "right",
			labelPad : 10,
			defaultType : "textfield",
			items : [{
				xtype : "radiogroup",
				fieldLabel : '选择类型',
				id : "classRadio",
				defaultType : 'radiofield',
				defaults : {
					flex : 1
				},
				layout : 'hbox',
				items : [{
							boxLabel : '增加大类',
							name : 'clazz',
							inputValue : 'broad',
							checked : true,
							id : 'broadRadio'
						}, {
							boxLabel : '增加小类',
							name : 'clazz',
							inputValue : 'sub',
							id : 'subRadio'
						}],
				listeners : {
					"change" : function() {
						if (Ext.getCmp("classRadio").getValue().clazz == 'broad') {
							Ext.getCmp("broadText").enable();
							broadCombo.disable();
							broadCombo.clearValue();
							Ext.getCmp("subText").disable();
							Ext.getCmp("subText").setValue("");

						} else {
							Ext.getCmp("broadText").disable();
							Ext.getCmp("broadText").setValue("");
							broadCombo.enable();
							Ext.getCmp("subText").enable();
						}
					}
				}
			}, {
				xtype : 'textfield',
				name : 'newClassModel.newBroadName',
				id : 'broadText',
				fieldLabel : '所属大类',
				anchor : '100%',
				emptyText : '请输入商品大类名称'
			}, broadCombo, {
				xtype : 'textfield',
				name : 'newClassModel.newSubName',
				id : 'subText',
				fieldLabel : '所属小类',
				anchor : '100%',
				disabled : true,
				emptyText : '请输入商品小类名称'
			}]
		}],
		buttonAlign : 'center',
		minButtonWidth : 100,
		buttons : [{
			text : "提交",
			tooltip : "提交数据",
			handler : function() {
				if (Ext.getCmp("addClassForm").getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
					Ext.getCmp("addClassForm").getForm().submit({// 利用表单的submit方法提交表单
						waitTitle : "请稍候", // 提交表单时进度条的标题
						waitMsg : "正在提交数据……", // 提交表单时进度条的信息
						url : '../../../../comMan/addClass.action', // 提交地址
						method : "POST", // 提交方式，需要大写
						success : function() { // 如果提交成功后处理的方法
							Ext.example.msg("提交成功", "提交类别信息成功",
									"msg-box-success");// 相应的提示信息
							Ext.getCmp("addClassWin").close(); // 根据id获取到表单的窗口，然后将其关闭
							 _classTree.getStore().reload(); //
							// 提交成功后，需要刷新GridPanel数据，

						},

						failure : function(form, action) { // 提交指失败进处理的方法
							Ext.example.msg("警告", "数据提交失败，请核对...",
									"msg-box-error");
						}
					});
				} else {// 如果表单验证未通过则提示用户骓未通过。

					Ext.example.msg("提示", "请填写完整、合法的类别信息", "msg-box-error");
				}

			}

		}, {
			text : "取消",
			tooltip : "取消此操作",
			handler : function() {
				Ext.getCmp("addClassWin").close();// 取消实际上就是关闭窗口
				Ext.example.msg("取消", "取消增加操作");
			}
		}]
	});
	return win;
}