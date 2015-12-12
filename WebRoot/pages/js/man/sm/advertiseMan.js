var showPicture = function() {
	var record = Ext.getCmp('grid_advertisemanager').getSelectionModel()
			.getSelection();
	var form = new Ext.form.FormPanel({
				frame : true,
				border : false,
				buttonAlign : 'center',
				items : [{
					xtype : 'box',
					rowspan : 4,
					width : 200,
					height : 200,
					autoEl : {
						tag : 'img',
						src : '../../../source/image/advertisement/'
								+ record[0].data.adphoto + ".jpg"
					}

				}],
				buttons : [{
							text : '关闭',
							handler : function() {
								Ext.getCmp('showPictureWindow').close();
							}
						}]
			})
	if (Ext.getCmp('showPictureWindow')) {
		Ext.getCmp('showPictureWindow').close();
	}
	new Ext.Window({
				title : '广告图片',
				id : 'showPictureWindow',
				width : 220,
				height : 250,
				layout : 'fit',
				items : [form]
			}).show();
}
var showKind = function(value) {
	if (value == 0) {
		return "<span style='color:blue;font-weight:bold;'>主页</span>"
	} else if (value == 1) {
		return "<span style='color:blue;font-weight:bold;'>搜索界面</span>"
	} else if (value == 2) {
		return "<span style='color:blue;font-weight:bold;'>商品详情界面</span>"
	} else if (value == 3) {
		return "<span style='color:blue;font-weight:bold;'>个人中心界面</span>"
	}
}

var alterAdPosition = function() {
	var record = Ext.getCmp('grid_showadposition').getSelectionModel()
			.getSelection();
	var adpositionIdfield = new Ext.form.DisplayField({
				fieldLabel : '广告位编码',
				allowfalse : false,
				vtype : 'alphanum',
				name : 'adpositionId',
				value : record[0].data.adpositionId
			});
	var pricefield = new Ext.form.NumberField({
				fieldLabel : '租金价格',
				allowfalse : false,
				name : 'price',
				allowBlank : false,
				value : record[0].data.price
			});
	var ratiofield = new Ext.form.NumberField({
				fieldLabel : '长宽比',
				name : 'ratio',
				allowBlank : false,
				value : record[0].data.ratio
			});
	var positionfield = new Ext.form.NumberField({
				fieldLabel : '位置序号',
				name : 'position',
				allowBlank : false,
				value : record[0].data.position
			});
	var form = new Ext.form.FormPanel({
				frame : true,
				border : false,
				items : [adpositionIdfield, pricefield, ratiofield, {
					id : 'select_kind',
					xtype : 'combo',
					fieldLabel : '广告位类型',
					emptyText : '请选择',
					name : 'kind',
					editable : false,
					displayField : 'RoleName',
					valueField : 'RoleValue',
					store : new Ext.data.ArrayStore({
								fields : [{
											name : 'RoleName',
											type : 'string'
										}, {
											name : 'RoleValue',
											type : 'int'
										}],
								data : [['主页', 0], ['搜索界面', 1], ['商品详情界面', 2],
										['个人中心界面', 3]]
							}),
					mode : 'local',
					selectOnFocus : true,
					triggerAction : 'all',
					value : parseInt(record[0].data.kind)
				}, positionfield],
				buttons : [{
					text : '确定',
					handler : function() {
						if (form.getForm().isValid()) {
							form.getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
								url : "../../../../synMan/UpdateAdPosition.action", // 提交地址
								method : "POST", // 提交方式，需要大写
								params : {
									'adpositionId' : record[0].data.adpositionId
								},
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "修改成功",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("alteradpositionWindow").close(); // 根据id获取到表单的窗口，然后将其关闭

									Ext.getCmp("grid_showadposition")
											.getStore().reload();
								},
								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("提交失败", "提交失败，请检查格式……",
											"msg-box-success");// 相应的提示信息
								}
							});
						} else {
							Ext.example.msg("提示", "请填写完整、合法的信息",
									"msg-box-error");
						}

					}
				}, {
					text : '关闭',
					handler : function() {
						Ext.getCmp('alteradpositionWindow').close();
					}
				}]
			})
	if (Ext.getCmp('alteradpositionWindow')) {
		Ext.getCmp('alteradpositionWindow').close();
	}
	new Ext.Window({
				title : '修改广告位',
				id : 'alteradpositionWindow',
				width : 280,
				height : 220,
				layout : 'fit',
				items : [form]
			}).show();
}

var adpositionMan = function() {
	var pageSize = 25;
	Ext.define('adpostion', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "adpositionId",
							type : "string"
						}, {
							name : "price",
							type : "string"
						}, {
							name : "kind",
							type : "string"
						}, {
							name : "ratio",
							type : "string"
						}, {
							name : "position",
							type : "string"
						}, {
							name : "advertisementId",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'adpostion',
				storeId : 'adpostionStore',
				proxy : {
					type : 'ajax',
					url : '../../../../synMan/getAdPosition.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'adpostionList',
						totalProperty : 'totalCount',
						idProperty : 'adpositionId'
					}
				},
				listeners : {
					'beforeload' : function(store, op, options) {
						var params = {
							kind : Ext.getCmp('DEA_UM_kind').getValue(),
							adpositionId : Ext.getCmp('DEA_SYN_adpositionId')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var adpostionOperations = function(value) {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="alterAdPosition()">修改</a></span>';
		return links;
	}
	var order_sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});

	Ext.create('Ext.window.Window', {
		id : "adPostionWin",
		title : '广告位管理',
		width : 800,
		constrain : true,
		constrainHeader : true,
		layout : 'fit',
		closeAction : 'destroy',
		resizable : false,
		modal : true,// 设置此Window为模式窗口,
		animateTarget : 'grid_advertisemanager',// 当指定一个id或元素,window打开时会与元素之间产生动画效果
		border : false,
		items : {
			xtype : "grid",
			height : 380,
			id : "grid_showadposition",
			selType : 'rowmodel',
			store : Ext.data.StoreManager.lookup('adpostionStore'),
			selModel : order_sm,
			columnLines : true,
			forceFit : true,
			columns : [{
						text : '广告位编号',
						width : 15,
						dataIndex : 'adpositionId'
					}, {
						text : '价格',
						width : 10,
						dataIndex : 'price'
					}, {
						text : '类型',
						width : 25,
						dataIndex : 'kind',
						renderer : showKind
					}, {
						text : '总价格',
						width : 10,
						dataIndex : 'price'
					}, {
						text : '长宽比',
						width : 10,
						dataIndex : 'ratio'
					}, {
						text : '位置序号',
						width : 10,
						dataIndex : 'position'
					}, {
						text : '目前在播广告',
						width : 20,
						dataIndex : 'advertisementId'
					}, {
						header : "操作",
						width : 25,
						renderer : adpostionOperations
					}],
			frame : true,
			tbar : ['广告位编号:', {
						xtype : 'numberfield',
						id : 'DEA_SYN_adpositionId',
						width : 100
					}, "广告位类型:", {
						id : 'DEA_UM_kind',
						xtype : 'combo',
						editable : false,
						displayField : 'stateName',
						valueField : 'stateValue',
						mode : 'local',
						store : new Ext.data.ArrayStore({
									fields : [{
												name : 'stateName',
												type : 'string'
											}, {
												name : 'stateValue',
												type : 'int'
											}],
									data : [['主页', 0], ['搜索界面', 1],
											['商品详情界面', 2], ['个人中心界面', 3],
											['全部', 4]]
								}),
						selectOnFocus : true,
						triggerAction : 'all',
						value : 4,
						width : 100,
						listeners : {
							'select' : function(combo, record) {
								Ext.data.StoreManager.lookup('adpostionStore')
										.load({
													params : {
														start : 0,
														limit : pageSize
													}
												});
							}
						}
					}, '-', {
						text : '&nbsp;&nbsp;&nbsp;查询',
						iconCls : 'icon-btn-search',
						handler : function() {
							Ext.data.StoreManager.lookup('adpostionStore')
									.load({
												params : {
													start : 0,
													limit : pageSize
												}
											});
						}
					}],
			bbar : new Ext.PagingToolbar({
						id : "toolbar1",
						store : Ext.data.StoreManager.lookup('adpostionStore'),
						pageSize : pageSize,
						displayInfo : true,
						displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
						emptyMsg : "没有记录"
					})
		},
		buttonAlign : 'center',
		minButtonWidth : 80,
		buttons : [{
			text : "完成",
			tooltip : "完成操作",
			handler : function() {
				if (Ext.getCmp('alter_adpositionId') != null) {
					var record = Ext.getCmp('grid_showadposition')
							.getSelectionModel().getSelection();
					if (record[0] != null) {
						Ext.getCmp('alter_adpositionId')
								.setValue(record[0].data.adpositionId)
						Ext.getCmp("adPostionWin").close();// 取消实际上就是关闭窗口
					} else {
						Ext.example.msg("警告", "请先选择一条数据.", "msg-box-error");
					}
				} else {
					Ext.getCmp("adPostionWin").close();// 取消实际上就是关闭窗口
				}
			}
		}, {
			text : "取消",
			tooltip : "取消",
			handler : function() {
				Ext.getCmp("adPostionWin").close();// 取消实际上就是关闭窗口
			}

		}]
	}).show();

	Ext.data.StoreManager.lookup('adpostionStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

}

var alterAdvertise = function() {
	var record = Ext.getCmp('grid_advertisemanager').getSelectionModel()
			.getSelection();
	win = Ext.create('widget.window', {
		id : "alterAdvertiseWindow",
		title : '修改广告',
		width : 320,
		height : 255,
		closable : true,
		resizable : false,
		modal : true,
		layout : 'fit',
		items : [{
			xtype : "form",
			labelWidth : 60,
			id : "alterAdvertiseForm",
			frame : true,
			bodyStyle : "paltering:5px 5px 0",
			border : false,
			waitMsgTarget : true,
			labelAlign : "right",
			labelPad : 10,
			defaultType : "textfield",
			items : [{
						xtype : 'datefield',
						fieldLabel : '开始时间',
						labelAlign : "right",
						name : 'startTime',
						value : record[0].data.startTime,
						editable : false,
						allowBlank : false,
						blankText : '不可为空',
						format : 'Y-m-d',
						listeners : {
							change : function(priceFrom, newValue, oldValue,
									eOpts) {
								var dateTo = Ext.getCmp("alter_enddate");
								dateTo.setMinValue(newValue);
								if (dateTo.getValue() < newValue) {
									dateTo.setValue("");
								}
							}
						}
					}, {
						xtype : 'datefield',
						id : "alter_enddate",
						fieldLabel : '结束时间',
						labelAlign : "right",
						format : 'Y-m-d',
						name : 'endTime',
						allowBlank : false,
						blankText : '不可为空',
						editable : false,
						value : record[0].data.endTime
					}, {
						xtype : 'filefield',
						anchor : '90%',
						name : 'upload',
						labelAlign : "right",
						fieldLabel : '广告照片',
						emptyText : '请选择新的商品图片',
						validator : function(value) {
							console.log(value != "");
							var arr = value.split('.');
							if (value != "" && arr[arr.length - 1] != 'jpg') {
								return "上传图片格式仅限于jpg";
							} else {
								return true;
							}
						}
					}, {
						xtype : 'textfield',
						fieldLabel : '广告对象',
						allowfalse : false,
						allowBlank : false,
						value : record[0].data.url,
						blankText : '不可为空',
						labelAlign : "right",
						name : 'url'
					}, {
						xtype : 'numberfield',
						fieldLabel : '广告位编号',
						id : 'alter_adpositionId',
						readOnly : true,
						allowBlank : false,
						blankText : '不可为空',
						labelAlign : "right",
						name : 'adpositionId',
						value : record[0].data.adpositionId
					}, {
						xtype : 'button',
						text : "选择广告位",
						tooltip : "请选择广告位",
						iconCls : 'icon-btn-edit',
						handler : function() {
							adpositionMan();
						}
					}, {
						id : 'alter_select_state',
						xtype : 'combo',
						fieldLabel : '广告位状态',
						labelAlign : "right",
						value : parseInt(record[0].data.state),
						allowBlank : false,
						emptyText : '请选择',
						name : 'state',
						editable : false,
						displayField : 'StateName',
						valueField : 'StateValue',
						store : new Ext.data.ArrayStore({
									fields : [{
												name : 'StateName',
												type : 'string'
											}, {
												name : 'StateValue',
												type : 'int'
											}],
									data : [['正在上映', 0], ['已过期', 1],
											['即将上映', 2]]
								}),
						mode : 'local',
						selectOnFocus : true,
						triggerAction : 'all'
					}]
		}],
		buttonAlign : 'center',
		minButtonWidth : 100,
		buttons : [{
			text : "提交",
			tooltip : "提交数据",
			handler : function() {
				if (Ext.getCmp("alterAdvertiseForm").getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
					Ext.getCmp("alterAdvertiseForm").getForm().submit({// 利用表单的submit方法提交表单
						waitTitle : "请稍候", // 提交表单时进度条的标题
						waitMsg : "正在提交数据……", // 提交表单时进度条的信息
						url : '../../../../synMan/alterAdvertise.action', // 提交地址
						method : "POST", // 提交方式，需要大写
						params : {
							"adinadpid" : record[0].data.adInadpId,
							"advertisementId" : record[0].data.advertisementId
						},
						success : function(form, action) { // 如果提交成功后处理的方法
							Ext.example.msg("提交成功", "提交广告信息成功",
									"msg-box-success");// 相应的提示信息
							Ext.getCmp("alterAdvertiseWindow").close(); // 根据id获取到表单的窗口，然后将其关闭
							// 提交成功后，需要刷新GridPanel数据
							Ext.getCmp("grid_advertisemanager").getStore()
									.reload();
						},
						failure : function(form, action) { // 提交指失败进处理的方法
							Ext.example.msg("提交失败", action.result.error,
									"msg-box-success");
						}
					});
				} else {// 如果表单验证未通过则提示用户骓未通过。
					Ext.example.msg("提示", "请填写完整、合法的广告信息", "msg-box-error");
				}

			}

		}, {
			text : "取消",
			tooltip : "取消此操作",
			handler : function() {
				Ext.getCmp("alterAdvertiseWindow").close();// 取消实际上就是关闭窗口
				Ext.example.msg("取消", "取消增加操作");
			}
		}]
	}).show();

}

var advertiseManFun = function() {
	var addAdvertise = function() {
		win = Ext.create('widget.window', {
			id : "AddAdvertiseWindow",
			title : '添加广告',
			width : 320,
			height : 255,
			closable : true,
			resizable : false,
			modal : true,
			layout : 'fit',
			items : [{
				xtype : "form",
				labelWidth : 60,
				id : "addAdvertiseForm",
				frame : true,
				bodyStyle : "padding:5px 5px 0",
				border : false,
				waitMsgTarget : true,
				labelAlign : "right",
				labelPad : 10,
				defaultType : "textfield",
				items : [{
					xtype : 'datefield',
					fieldLabel : '开始时间',
					format : 'Y-m-d',
					labelAlign : "right",
					name : 'startTime',
					editable : false,
					allowBlank : false,
					blankText : '不可为空',
					listeners : {
						change : function(priceFrom, newValue, oldValue, eOpts) {
							var dateTo = Ext.getCmp("add_enddate");
							dateTo.setMinValue(newValue);
							if (dateTo.getValue() < newValue) {
								dateTo.setValue("");
							}
						}
					}
				}, {
					xtype : 'datefield',
					id : "add_enddate",
					fieldLabel : '结束时间',
					format : 'Y-m-d',
					labelAlign : "right",
					name : 'endTime',
					allowBlank : false,
					blankText : '不可为空',
					editable : false
				}, {
					xtype : 'filefield',
					anchor : '90%',
					name : 'upload',
					labelAlign : "right",
					fieldLabel : '广告照片',
					validator : function(value) {
						var arr = value.split('.');
						if (arr[arr.length - 1] != 'jpg') {
							return "上传图片格式仅限于jpg";
						} else {
							return true;
						}
					}
				}, {
					xtype : 'textfield',
					fieldLabel : '广告对象',
					allowfalse : false,
					allowBlank : false,
					blankText : '不可为空',
					labelAlign : "right",
					name : 'url'
				}, {
					xtype : 'numberfield',
					fieldLabel : '广告位编号',
					id : 'alter_adpositionId',
					allowBlank : false,
					blankText : '不可为空',
					labelAlign : "right",
					readOnly : true,
					name : 'adpositionId'
				}, {
					xtype : 'button',
					text : "选择广告位",
					tooltip : "请选择广告位",
					iconCls : 'icon-btn-edit',
					handler : function() {
						adpositionMan();
					}
				}, {
					id : 'add_select_state',
					xtype : 'combo',
					fieldLabel : '广告位状态',
					labelAlign : "right",
					allowBlank : false,
					emptyText : '请选择',
					name : 'state',
					editable : false,
					displayField : 'StateName',
					valueField : 'StateValue',
					store : new Ext.data.ArrayStore({
								fields : [{
											name : 'StateName',
											type : 'string'
										}, {
											name : 'StateValue',
											type : 'int'
										}],
								data : [['正在上映', 0], ['已过期', 1], ['即将上映', 2]]
							}),
					mode : 'local',
					selectOnFocus : true,
					triggerAction : 'all',
					value : 2
				}]
			}],
			buttonAlign : 'center',
			minButtonWidth : 100,
			buttons : [{
				text : "提交",
				tooltip : "提交数据",
				handler : function() {
					if (Ext.getCmp("addAdvertiseForm").getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
						Ext.getCmp("addAdvertiseForm").getForm().submit({// 利用表单的submit方法提交表单
							waitTitle : "请稍候", // 提交表单时进度条的标题
							waitMsg : "正在提交数据……", // 提交表单时进度条的信息
							url : '../../../../synMan/AddAdvertise.action', // 提交地址
							method : "POST", // 提交方式，需要大写
							success : function(form, action) { // 如果提交成功后处理的方法
								Ext.example.msg("提交成功", "提交广告信息成功",
										"msg-box-success");// 相应的提示信息
								Ext.getCmp("AddAdvertiseWindow").close(); // 根据id获取到表单的窗口，然后将其关闭
								// 提交成功后，需要刷新GridPanel数据，
								Ext.getCmp("grid_advertisemanager").getStore()
										.reload();
							},
							failure : function(form, action) { // 提交指失败进处理的方法
								Ext.example.msg("警告", action.result.error,
										"msg-box-error");
							}
						});
					} else {// 如果表单验证未通过则提示用户骓未通过。

						Ext.example.msg("提示", "请填写完整、合法的广告信息", "msg-box-error");
					}

				}

			}, {
				text : "取消",
				tooltip : "取消此操作",
				handler : function() {
					Ext.getCmp("AddAdvertiseWindow").close();// 取消实际上就是关闭窗口
					Ext.example.msg("取消", "取消增加操作");
				}
			}]
		}).show();
	}

	var showState = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>正在上映</span>"
		} else if (value == 1) {
			return "<span style='color:blue;font-weight:bold;'>已过期</span>"
		} else if (value == 2) {
			return "<span style='color:blue;font-weight:bold;'>即将上映</span>"
		}
	}

	var btn_logout = new Ext.Button({
				text : "<span style='color:red;font-weight:bold;'>" + "退出系统"
						+ "</span>",
				tooltip : "退出系统",
				id : "userLogout",
				handler : function() {
					Ext.Ajax.request({
								url : "../../../../basicMan/UserLogout.action",
								method : "get",
								success : function(data) {
									var respText = Ext.JSON
											.decode(data.responseText);
									Ext.MessageBox.alert('确认',
											"<span style='color:red;font-weight:bold;'>"
													+ respText.userName
													+ "</span>" + '已成功退出系统!',
											function(id) {
												if (id == "ok") {
													window.location.reload();
												}
											})
								}
							})
				}
			});

	var a_btn_addann = new Ext.Button({
				text : "增添广告",
				tooltip : "增添广告",
				iconCls : 'icon-btn-add',
				handler : function() {
					addAdvertise();
				}
			});

	var a_btn_adpostion = new Ext.Button({
				text : "广告位管理",
				tooltip : "广告位管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					adpositionMan();
				}
			});

	var a_btn_advertise = new Ext.Button({
				text : "广告管理",
				tooltip : "广告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(0);
				},
				disabled : true
			});

	var a_btn_announce = new Ext.Button({
				text : "公告管理",
				tooltip : "公告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(1);
				}
			});

	var a_btn_feedback = new Ext.Button({
				text : "反馈管理",
				tooltip : "反馈管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(2);
				}
			});

	var pageSize = 25;
	Ext.define('advertise', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "advertisementId",
							type : "string"
						}, {
							name : "startTime",
							type : "string"
						}, {
							name : "endTime",
							type : "string"
						}, {
							name : "adphoto",
							type : "string"
						}, {
							name : "url",
							type : "string"
						}, {
							name : "kind",
							type : "string"
						}, {
							name : "position",
							type : "string"
						}, {
							name : "state",
							type : "string"
						}, {
							name : "adpositionId",
							type : "string"
						}, {
							name : "adInadpId",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'advertise',
				storeId : 'advertiseStore',
				proxy : {
					type : 'ajax',
					url : '../../../../synMan/searchAdvertise.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'advertiseList',
						totalProperty : 'totalCount'
					}
				},
				listeners : {
					'beforeload' : function(store, op, options) {
						var params = {
							kind : Ext.getCmp('DEA_UM_AdvertiseKind')
									.getValue(),
							advertisementId : Ext
									.getCmp('DEA_SYN_advertisementId')
									.getValue(),
							state : Ext.getCmp('DEA_UM_AdvertiseState')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var aOperations = function() {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="alterAdvertise()">修改广告</a></span>'
		links = links + '&nbsp;&nbsp;&nbsp;'
				+ '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="showPicture()">查看图片</a></span>'
		return links;
	}
	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
		title : '广告管理',
		id : "grid_advertisemanager",
		selType : 'rowmodel',
		store : Ext.data.StoreManager.lookup('advertiseStore'),
		selModel : _sm,
		columnLines : true,
		region : 'center',
		forceFit : true,
		columns : [{
					text : '广告编号',
					width : 10,
					dataIndex : 'advertisementId'
				}, {
					text : '开始时间',
					width : 15,
					dataIndex : 'startTime'
				}, {
					text : '结束时间',
					width : 15,
					dataIndex : 'endTime'
				}, {
					text : '广告对象',
					width : 50,
					dataIndex : 'url'
				}, {
					text : '类型',
					width : 10,
					dataIndex : 'kind',
					renderer : showKind
				}, {
					header : "位置序号",
					dataIndex : 'position',
					width : 10
				}, {
					header : "状态",
					dataIndex : 'state',
					width : 10,
					renderer : showState
				}, {
					header : "操作",
					width : 15,
					renderer : aOperations
				}],

		frame : true,
		tbar : [
				'广告编号:',
				{
					xtype : 'numberfield',
					id : 'DEA_SYN_advertisementId',
					width : 80
				},
				"广告位类型:",
				{
					id : 'DEA_UM_AdvertiseKind',
					xtype : 'combo',
					editable : false,
					displayField : 'stateName',
					valueField : 'stateValue',
					mode : 'local',
					store : new Ext.data.ArrayStore({
								fields : [{
											name : 'stateName',
											type : 'string'
										}, {
											name : 'stateValue',
											type : 'int'
										}],
								data : [['主页', 0], ['搜索界面', 1], ['商品详情界面', 2],
										['个人中心界面', 3], ['全部', 4]]
							}),
					selectOnFocus : true,
					triggerAction : 'all',
					value : 4,
					width : 100,
					listeners : {
						'select' : function(combo, record) {
							Ext.data.StoreManager.lookup('advertiseStore')
									.load({
												params : {
													start : 0,
													limit : pageSize
												}
											});
						}
					}
				},
				"状态:",
				{
					id : 'DEA_UM_AdvertiseState',
					xtype : 'combo',
					editable : false,
					displayField : 'stateName',
					valueField : 'stateValue',
					mode : 'local',
					store : new Ext.data.ArrayStore({
						fields : [{
									name : 'stateName',
									type : 'string'
								}, {
									name : 'stateValue',
									type : 'int'
								}],
						data : [['正在上映', 0], ['已过期', 1], ['即将上映', 2], ['全部', 3]]
					}),
					selectOnFocus : true,
					triggerAction : 'all',
					value : 3,
					width : 100,
					listeners : {
						'select' : function(combo, record) {
							Ext.data.StoreManager.lookup('advertiseStore')
									.load({
												params : {
													start : 0,
													limit : pageSize
												}
											});
						}
					}
				}, '-', {
					text : '&nbsp;&nbsp;&nbsp;查询',
					iconCls : 'icon-btn-search',
					handler : function() {
						Ext.data.StoreManager.lookup('advertiseStore').load({
									params : {
										start : 0,
										limit : pageSize
									}
								});
					}
				}, '-', a_btn_addann, '-', a_btn_adpostion, '->', a_btn_advertise,
				'-', a_btn_announce, '-', a_btn_feedback, '-', btn_logout],
		bbar : new Ext.PagingToolbar({
					id : "advertise_toolbar1",
					store : Ext.data.StoreManager.lookup('advertiseStore'),
					pageSize : pageSize,
					displayInfo : true,
					displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
					emptyMsg : "没有记录"
				})
	});

	Ext.data.StoreManager.lookup('advertiseStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	return _grid;
}