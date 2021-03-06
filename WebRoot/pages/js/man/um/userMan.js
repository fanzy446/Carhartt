var checkorderdetail = function() {
	var record = Ext.getCmp('grid_showorder').getSelectionModel()
			.getSelection();
	var orderId = record[0].data.orderId;
	Ext.define('orderDetailCom', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "detailid",
							type : "string"
						}, {
							name : "item",
							type : "string"
						}, {
							name : "price",
							type : "string"
						}, {
							name : "count",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}]
			});

	var orderDetailComStore = Ext.create('Ext.data.Store', {
				model : 'orderDetailCom',
				storeId : 'orderDetailComStore',
				autoLoad : false,
				proxy : {
					type : 'ajax',
					url : '../../../../getalldetails2.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'orderDetailCom'
					}
				}
			});
	var pageSize = 25;
	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
		id : "grid_orderdetailmanager",
		selType : 'rowmodel',
		store : Ext.data.StoreManager.lookup('orderDetailComStore'),
		selModel : _sm,
		columnLines : true,
		region : 'center',
		forceFit : true,
		columns : [{
					text : '详情id',
					width : 10,
					dataIndex : 'detailid'
				}, {
					text : '商品',
					width : 10,
					dataIndex : 'item'
				}, {
					text : '价格',
					width : 10,
					dataIndex : 'price'
				}, {
					text : '数目',
					width : 10,
					dataIndex : 'count'
				}, {
					text : '备注',
					width : 10,
					dataIndex : 'remark'
				}],
		frame : true,
		bbar : new Ext.PagingToolbar({
					store : Ext.data.StoreManager.lookup('orderDetailComStore'),
					pageSize : pageSize,
					displayInfo : true,
					displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
					emptyMsg : "没有记录"
				})
	});

	Ext.create('Ext.window.Window', {
				id : "showDetialUserOrderWin",
				title : '用户订单详情',
				width : 800,
				constrain : true,
				constrainHeader : true,
				layout : 'fit',
				closeAction : 'destroy',
				resizable : false,
				modal : true,// 设置此Window为模式窗口,
				border : false,
				items : [_grid],
				buttonAlign : 'center',
				minButtonWidth : 80,
				buttons : [{
							text : "完成",
							tooltip : "完成操作",
							handler : function() {
								Ext.getCmp("showDetialUserOrderWin").close();// 取消实际上就是关闭窗口
							}
						}]
			}).show();

	orderDetailComStore.load({
				params : {
					orderid : orderId
				}
			});
}

var checkuserorder = function() {
	var pageSize = 20;
	var record = Ext.getCmp('grid_usermanager').getSelectionModel()
			.getSelection();
	Ext.define('order', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "orderId",
							type : "string"
						}, {
							name : "consignee",
							type : "string"
						}, {
							name : "user",
							type : "string"
						}, {
							name : "totalPrice",
							type : "string"
						}, {
							name : "createTime",
							type : "string"
						}, {
							name : "orderState",
							type : "string"
						}, {
							name : "deliveryTime",
							type : "string"
						}, {
							name : "receivingTime",
							type : "string"
						}, {
							name : "evaluationGrade",
							type : "string"
						}, {
							name : "extraEstimate",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'order',
				storeId : 'orderStore',
				proxy : {
					type : 'ajax',
					url : '../../../../orderMan/getOrdersByUserId.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'orders',
						totalProperty : 'totalCount',
						idProperty : 'orderId'
					}
				},
				listeners : {
					'load' : function(records, successful, operation, eOpts) {
						if (this.getTotalCount() == 0) {
							Ext.example.msg("提示", "用户仍未有订单.", "msg-box-error");
						} else {
							Ext.getCmp("showUserOrderWin").show();
						}
					},
					'beforeload' : function(store, op, options) {
						var params = {
							orderState : Ext.getCmp('DEA_UM_orderState')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var showOrderState = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>未发货</span>"
		} else if (value == 1) {
			return "<span style='color:blue;font-weight:bold;'>发货未确认</span>"
		} else if (value == 2) {
			return "<span style='color:blue;font-weight:bold;'>订单取消</span>"
		} else {
			return "<span style='color:blue;font-weight:bold;'>订单成功</span>"
		}
	}

	var showEvaState = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>不满意</span>"
		} else if (value == 1) {
			return "<span style='color:blue;font-weight:bold;'>较满意</span>"
		} else {
			return "<span style='color:blue;font-weight:bold;'>很满意</span>"
		}
	}

	var orderOperations = function(value) {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="checkorderdetail()">查看详情</a></span>';
		return links;
	}
	var order_sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});

	Ext.create('Ext.window.Window', {
				id : "showUserOrderWin",
				title : '用户订单',
				width : 1300,
				constrain : true,
				constrainHeader : true,
				layout : 'fit',
				closeAction : 'destroy',
				resizable : false,
				modal : true,// 设置此Window为模式窗口,
				animateTarget : 'grid_usermanager',// 当指定一个id或元素,window打开时会与元素之间产生动画效果
				border : false,
				items : {
					xtype : "grid",
					height : 380,
					id : "grid_showorder",
					selType : 'rowmodel',
					store : Ext.data.StoreManager.lookup('orderStore'),
					selModel : order_sm,
					columnLines : true,
					forceFit : true,
					columns : [{
								text : '订单编号',
								width : 10,
								dataIndex : 'orderId'
							}, {
								text : '收货人编号',
								width : 10,
								dataIndex : 'consignee'
							}, {
								text : '用户编号',
								width : 10,
								dataIndex : 'user'
							}, {
								text : '总价格',
								width : 10,
								dataIndex : 'totalPrice'
							}, {
								text : '订单生成时间',
								width : 30,
								dataIndex : 'createTime'
							}, {
								text : '订单状态',
								width : 25,
								dataIndex : 'orderState',
								renderer : showOrderState
							}, {
								text : '发货时间',
								width : 30,
								dataIndex : 'deliveryTime'
							}, {
								text : '收货时间',
								width : 30,
								dataIndex : 'receivingTime'
							}, {
								text : '评价等级',
								width : 20,
								dataIndex : 'evaluationGrade',
								renderer : showEvaState
							}, {
								text : '附加评价',
								width : 20,
								dataIndex : 'extraEstimate'
							}, {
								text : '备注',
								width : 20,
								dataIndex : 'remark'
							}, {
								header : "操作",
								dataIndex : 'orderState',
								width : 30,
								renderer : orderOperations
							}],
					frame : true,
					tbar : ["订单状态", {
						id : 'DEA_UM_orderState',
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
									data : [['发货未确认', 1], ['未发货', 0],
											['订单取消', 2], ['订单成功', 3], ['全部', 4]]
								}),
						selectOnFocus : true,
						triggerAction : 'all',
						value : 4,
						width : 80,
						listeners : {
							'select' : function(combo) {
								Ext.data.StoreManager.lookup('orderStore')
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
							Ext.data.StoreManager.lookup('orderStore').load({
										params : {
											start : 0,
											limit : pageSize
										}
									});
						}
					}],
					bbar : new Ext.PagingToolbar({
								store : Ext.data.StoreManager
										.lookup('orderStore'),
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
								Ext.getCmp("showUserOrderWin").close();// 取消实际上就是关闭窗口
							}
						}]
			});
	Ext.data.StoreManager.lookup('orderStore').load({
				params : {
					start : 0,
					limit : pageSize,
					orderState : 4,
					userId : record[0].data.userId
				}
			});
}

var alteruser = function() {
	var record = Ext.getCmp('grid_usermanager').getSelectionModel()
			.getSelection();
	var remarkfield = new Ext.form.TextArea({
				id : "user_remark_id",
				fieldLabel : '备注',
				allowfalse : false,
				grow : true,
				name : 'remark',
				value : record[0].data.remark,
				width : 250,
				height : 100
			});
	var form = new Ext.form.FormPanel({
				frame : true,
				border : false,
				items : [{
							id : 'Window_userRole',
							xtype : 'combo',
							fieldLabel : '用户角色',
							emptyText : '请选择',
							name : 'userRole',
							width : 250,
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
										data : [['初级会员', 0], ['中级会员', 1],
												['高级会员', 2]]
									}),
							mode : 'local',
							selectOnFocus : true,
							triggerAction : 'all',
							value : parseInt(record[0].data.userRole)
						}, remarkfield],
				buttons : [{
					text : '确定',
					handler : function() {
						if (form.getForm().isValid()) {
							form.getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
								url : "../../../../userMan/UpdateUserInfo.action", // 提交地址
								method : "POST", // 提交方式，需要大写
								params : {
									'userId' : record[0].data.userId
								},
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "修改成功",
											"msg-box-success");// 相应的提示信息
									record[0].set("remark", Ext
													.getCmp('user_remark_id')
													.getValue());
									record[0].set("userRole", Ext
													.getCmp('Window_userRole')
													.getValue());
									Ext.getCmp("grid_usermanager").getStore()
											.commitChanges();
									Ext.getCmp("alteruserinfoWindow").close(); // 根据id获取到表单的窗口，然后将其关闭
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
						Ext.getCmp('alteruserinfoWindow').close();
					}
				}]
			})
	if (Ext.getCmp('alteruserinfoWindow')) {
		Ext.getCmp('alteruserinfoWindow').close();
	}
	new Ext.Window({
				title : '修改信息',
				id : 'alteruserinfoWindow',
				width : 300,
				height : 200,
				layout : 'fit',
				// closeAction : 'hide',
				items : [form]
			}).show();
}

var frozenuser = function() {
	Ext.MessageBox.confirm('确认', '是否确定冻结此用户?', function(btn) {
				if (btn == 'yes') {
					var record = Ext.getCmp('grid_usermanager')
							.getSelectionModel().getSelection();
					Ext.Ajax.request({
								url : "../../../../userMan/FrozenUserById.action",
								method : "POST",
								params : {
									"userId" : record[0].data.userId,
									"type" : 1
								},
								success : function() {
									record[0].set("userState", 2);
									Ext.getCmp('grid_usermanager').getStore()
											.commitChanges();
								}
							})
				}
			})
}

var unfrozenuser = function() {
	Ext.MessageBox.confirm('确认', '是否确定解冻此用户?', function(btn) {
				if (btn == 'yes') {
					var record = Ext.getCmp('grid_usermanager')
							.getSelectionModel().getSelection();
					Ext.Ajax.request({
								url : "../../../../userMan/FrozenUserById.action",
								method : "POST",
								params : {
									"userId" : record[0].data.userId,
									"type" : 0
								},
								success : function() {
									record[0].set("userState", 0);
									Ext.getCmp('grid_usermanager').getStore()
											.commitChanges();
								}
							})
				}
			})
}

var checkuserdetail = function() {
	var record = Ext.getCmp('grid_usermanager').getSelectionModel()
			.getSelection();

	var creaditsfield = Ext.create('Ext.form.DisplayField', {
				fieldLabel : '消费积分',
				allowfalse : false,
				name : 'userCredits',
				readOnly : true,
				value : record[0].data.userCredits
			});

	var codefield = Ext.create('Ext.form.DisplayField', {
				fieldLabel : '用户编号',
				allowfalse : false,
				name : 'userId',
				readOnly : true,
				value : record[0].data.userId
			});
	var namefield = new Ext.form.DisplayField({
				fieldLabel : '姓名',
				allowfalse : false,
				name : 'realName',
				readOnly : true,
				value : record[0].data.realName
			});
	var hintfield = new Ext.form.DisplayField({
				fieldLabel : '密码提示',
				allowfalse : false,
				name : 'passwHint',
				readOnly : true,
				value : record[0].data.passwHint
			});
	var answerfield = new Ext.form.DisplayField({
				fieldLabel : '提示答案',
				allowfalse : false,
				name : 'passAnswer',
				readOnly : true,
				value : record[0].data.passAnswer
			});
	var sexfield = new Ext.form.DisplayField({
				fieldLabel : '性别',
				allowfalse : false,
				name : 'userGender',
				readOnly : true,
				value : record[0].data.userGender
			});
	var idcardfield = new Ext.form.DisplayField({
				fieldLabel : '身份证号',
				allowfalse : false,
				name : 'idCard',
				readOnly : true,
				value : record[0].data.idCard
			});
	var phonefield = new Ext.form.DisplayField({
				fieldLabel : '用户电话',
				allowfalse : false,
				name : 'userPhone',
				readOnly : true,
				value : record[0].data.userPhone
			});
	var infoform = new Ext.form.FormPanel({
				labelWidth : 10,
				frame : true,
				border : false,
				items : [{
					anchor : '100%',
					layout : 'column',
					items : [{
								columnWidth : .5,
								layout : 'form',
								defaults : {
									anchor : '95%'
								},
								items : [codefield, namefield, hintfield,
										answerfield]
							}, {
								columnWidth : .5,
								layout : 'form',
								defaults : {
									anchor : '95%',
									readOnly : true
								},
								items : [sexfield, idcardfield, phonefield,
										creaditsfield]
							}]
				}]
			});
	if (Ext.getCmp('UserInfoWindowShow')) {
		Ext.getCmp('UserInfoWindowShow').close();
	}
	new Ext.Window({
				title : '详细信息查询',
				id : 'UserInfoWindowShow',
				width : 600,
				height : 164,
				layout : 'fit',
				items : [infoform],
				buttons : [{
							text : '关闭',
							handler : function() {
								Ext.getCmp('UserInfoWindowShow').close();
							}
						}]
			}).show();

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
												+ respText.userName + "</span>"
												+ '已成功退出系统!', function(id) {
											if (id == "ok") {
												window.location.reload();
											}
										})
							}
						})
			}
		});
var scheManFun = function() {
	var pageSize = 20;
	Ext.define('user', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "userId",
							type : "string"
						}, {
							name : "userName",
							type : "string"
						}, {
							name : "userEmail",
							type : "string"
						}, {
							name : "registerTime",
							type : "string"
						}, {
							name : "userRole",
							type : "string"
						}, {
							name : "userState",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}, {
							name : "passwHint",
							type : "string"
						}, {
							name : "passAnswer",
							type : "string"
						}, {
							name : "realName",
							type : "string"
						}, {
							name : "idCard",
							type : "string"
						}, {
							name : "userPhone",
							type : "string"
						}, {
							name : "userGender",
							type : "string"
						}, {
							name : "userCredits",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'user',
				storeId : 'userStore',
				proxy : {
					type : 'ajax',
					url : '../../../../userMan/searchUser.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'users',
						totalProperty : 'totalCount',
						idProperty : 'userId'
					}
				},
				listeners : {
					'beforeload' : function(store, op, options) {
						var params = {
							// 参数
							userId : Ext.getCmp('DEA_UM_userId').getValue(),
							userEmail : Ext.getCmp('DEA_UM_userEmail')
									.getValue(),
							userState : Ext.getCmp('DEA_UM_userState')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var showState = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>正常</span>"
		} else if(value == 1){
			return "<span style='color:red;font-weight:bold;'>未激活</span>"
		} else {
			return "<span style='color:red;font-weight:bold;'>已冻结</span>"
		}
		
	}

	var showuserRole = function(value) {
		if (value == 2) {
			return "<span style='color:navy;font-weight:bold;'>高级会员</span>"
		} else if (value == 1) {
			return "<span style='color:orange;font-weight:bold;'>中级会员</span>"
		} else {
			return "<span style='color:blue;font-weight:bold;'>初级会员</span>"
		}
	}

	var eOperations = function(value) {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="checkuserdetail()">查看详情</a></span>'
				+ "&nbsp;&nbsp;&nbsp;"
				+ '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="checkuserorder()">查看用户订单</a></span>'
				+ "&nbsp;&nbsp;&nbsp;"
				+ '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="alteruser()">修改</a></span>'
		if (value == 0) {
			links = links + "&nbsp;&nbsp;&nbsp;"
					+ '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="frozenuser()">冻结用户</a></span>';
		} else if (value == 2) {
			links = links + "&nbsp;&nbsp;&nbsp;"
					+ '<span style="color:blue;font-weight:bold;">'
					+ '<a onclick="unfrozenuser()">解冻用户</a></span>';
		} 
		return links;
	}
	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
				title : '用户管理',
				id : "grid_usermanager",
				selType : 'rowmodel',
				store : Ext.data.StoreManager.lookup('userStore'),
				selModel : _sm,
				columnLines : true,
				region : 'center',
				forceFit : true,
				columns : [{
							text : '用户编号',
							width : 10,
							dataIndex : 'userId'
						}, {
							text : '昵称',
							width : 20,
							dataIndex : 'userName'
						}, {
							text : '登陆邮箱',
							width : 25,
							dataIndex : 'userEmail'
						}, {
							text : '注册时间',
							width : 20,
							dataIndex : 'registerTime'
						}, {
							text : '用户角色',
							width : 15,
							dataIndex : 'userRole',
							renderer : showuserRole
						}, {
							text : '用户状态',
							width : 15,
							dataIndex : 'userState',
							renderer : showState
						}, {
							text : '备注',
							width : 15,
							dataIndex : 'remark'
						}, {
							header : "操作",
							dataIndex : 'userState',
							width : 40,
							renderer : eOperations
						}],

				frame : true,
				tbar : ['用户编号:', {
							xtype : 'numberfield',
							id : 'DEA_UM_userId',
							width : 100
						}, '登陆邮箱:', {
							xtype : 'textfield',
							id : 'DEA_UM_userEmail',
							width : 100
						}, "用户状态", {
							id : 'DEA_UM_userState',
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
										data : [['已冻结', 1], ['未冻结', 0],
												['全部', 2]]
									}),
							selectOnFocus : true,
							triggerAction : 'all',
							value : 2,
							width : 80,
							listeners : {
								'select' : function(combo, record) {
									Ext.data.StoreManager.lookup('userStore')
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
								Ext.data.StoreManager.lookup('userStore').load(
										{
											params : {
												start : 0,
												limit : pageSize
											}
										});
							}
						}, '->', btn_logout],
				bbar : new Ext.PagingToolbar({
							store : Ext.data.StoreManager.lookup('userStore'),
							pageSize : pageSize,
							displayInfo : true,
							displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
							emptyMsg : "没有记录"
						})
			});

	Ext.data.StoreManager.lookup('userStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	return _grid;
}