var frozenorder = function() {
	Ext.MessageBox.confirm('确认', '是否确定取消此订单?', function(btn) {
		if (btn == 'yes') {
			var record = Ext.getCmp('grid_ordermanager').getSelectionModel()
					.getSelection();
			Ext.Ajax.request({
				url : "../../../../orderMan/orderCancle.action",
				method : "POST",
				params : {
					"orderId" : record[0].data.orderId
				},
				success : function() {
					Ext.getCmp('grid_ordermanager').getStore().reload();
				}
			})
		}
	})
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

var checkorderdetail = function() {
	var record = Ext.getCmp('grid_ordermanager').getSelectionModel()
			.getSelection();
	var orderId = record[0].data.orderId;
//	Ext.define('orderDetail', {
//		extend : 'Ext.data.Model',
//		fields : [ {
//			name : "orderId",
//			type : "string"
//		},{
//			name : "createTime",
//			type : "string"
//		}, {
//			name : "orderState",
//			type : "string"
//		}, {
//			name : "consigneeName",
//			type : "string"
//		}, {
//			name : "consigneePhone",
//			type : "string"
//		}, {
//			name : "address",
//			type : "string"
//		}, {
//			name : "postCode",
//			type : "string"
//		} ]
//	});
//
//	Ext.create('Ext.data.Store', {
//		model : 'orderDetail',
//		storeId : 'orderDetailStore',
//		autoLoad : false,
//		proxy : {
//			type : 'ajax',
//			url : '../../../../getoneorder2.action',
//			method : 'POST',
//			reader : {
//				type : 'json',
//				root : 'orderDetail',
//				totalProperty : 'totalCount',
//				idProperty:'orderId'
//			}
//		}
//	});

	Ext.define('orderDetailCom', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : "detailid",
			type : "string"
		} ,{
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
		} ]
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
	orderDetailComStore.load({
		params : {
			orderid : orderId
		}
	});

//	orderDetailStore.load({
//		params : {
//			orderid : orderId
//		}
//	});
//	Ext.data.StoreManager.lookup('orderDetailStore').load({
//		params : {
//			orderid : orderId
//		}
//	});
//	console.log(Ext.data.StoreManager.lookup('orderDetailStore'));
//	console.log(Ext.data.StoreManager.lookup('orderDetailStore').getCount());
//	console.log(Ext.data.StoreManager.lookup('orderDetailStore').findRecord("orderId","1"));
//	var createTimefield = Ext.create('Ext.form.DisplayField', {
//		fieldLabel : '订单生成时间',
//		allowfalse : false,
//		name : 'CreateTime',
//		readOnly : true,
//		value : orderDetailStore.findRecord("orderId",1)
//	});
//
//	var orderStatefield = Ext.create('Ext.form.DisplayField', {
//		fieldLabel : '订单状态',
//		allowfalse : false,
//		name : 'OrderState',
//		readOnly : true,
//		value : orderDetailStore.findRecord("orderState")
//	});
//	var consigneeNamefield = new Ext.form.DisplayField({
//		fieldLabel : '收货人姓名',
//		allowfalse : false,
//		name : 'ConsigneeName',
//		readOnly : true,
//		value : orderDetailStore.findRecord("consigneeName")
//	});
//	var consigneePhonefield = new Ext.form.DisplayField({
//		fieldLabel : '收货人电话',
//		allowfalse : false,
//		name : 'ConsigneePhone',
//		readOnly : true,
//		value : orderDetailStore.findRecord("consigneePhone")
//	});
//	var addressfield = new Ext.form.DisplayField({
//		fieldLabel : '收货人地址',
//		allowfalse : false,
//		name : 'Address',
//		readOnly : true,
//		value : orderDetailStore.findRecord("address")
//	});
//	var postCodefield = new Ext.form.DisplayField({
//		fieldLabel : '邮编',
//		allowfalse : false,
//		name : 'PostCode',
//		readOnly : true,
//		value : orderDetailStore.findRecord("postCode")
//	});
	// var idcardfield = new Ext.form.DisplayField({
	// fieldLabel : '身份证号',
	// allowfalse : false,
	// name : 'idCard',
	// readOnly : true,
	// value : record[0].data.idCard
	// });
	// var phonefield = new Ext.form.DisplayField({
	// fieldLabel : '用户电话',
	// allowfalse : false,
	// name : 'userPhone',
	// readOnly : true,
	// value : record[0].data.userPhone
	// });
	// var creaditsfield = new Ext.form.DisplayField({
	// fieldLabel : '消费积分',
	// allowfalse : false,
	// name : 'userCredits',
	// readOnly : true,
	// value : record[0].data.userCredits
	// });
//	var infoform = new Ext.form.FormPanel({
//		labelWidth : 10,
//		frame : true,
//		border : false,
//		items : [ {
//			anchor : '100%',
//			layout : 'column',
//			items : [
//					{
//						columnWidth : .5,
//						layout : 'form',
//						defaults : {
//							anchor : '95%'
//						},
//						items : [ createTimefield, orderStatefield,
//								consigneeNamefield ]
//					},
//					{
//						columnWidth : .5,
//						layout : 'form',
//						defaults : {
//							anchor : '95%',
//							readOnly : true
//						},
//						items : [ consigneePhonefield, addressfield,
//								postCodefield ]
//					} ]
//		} ]
//	});
//	if (Ext.getCmp('UserInfoWindowShow')) {
//		Ext.getCmp('UserInfoWindowShow').close();
//	}
//	new Ext.Window({
//		title : '订单详情查询',
//		id : 'UserInfoWindowShow',
//		width : 600,
//		height : 164,
//		layout : 'fit',
//		items : [ infoform ],
//		buttons : [ {
//			text : '关闭',
//			handler : function() {
//				Ext.getCmp('UserInfoWindowShow').close();
//			}
//		} ]
//	}).show();
	var pageSize = 25;
	var _sm = new Ext.selection.RowModel({
		mode : 'SINGLE'
	});
	var _grid = Ext.create('Ext.grid.Panel', {
//		title : '订单管理',
		id : "grid_orderdetailmanager",
		selType : 'rowmodel',
		store : Ext.data.StoreManager.lookup('orderDetailComStore'),
		selModel : _sm,
		columnLines : true,
		region : 'center',
		forceFit : true,
		columns : [ {
			text : '详情id',
			width : 7,
			dataIndex : 'detailid'
		}, {
			text : '商品',
			width : 7,
			dataIndex : 'item'
		}, {
			text : '价格',
			width : 7,
			dataIndex : 'price'
		}, {
			text : '数目',
			width : 7,
			dataIndex : 'count'
		}, {
			text : '备注',
			width : 7,
			dataIndex : 'remark'
		} ],

		frame : true,
//		tbar : [
//				'订单号:',
//				{
//					xtype : 'textfield',
//					id : 'DEA_UM_orderId',
//					width : 100
//				},
//				'日期:',
//				{
//					id : 'DEA_UM_orderDate',
//					xtype : 'datefield',
//					format : 'Y-m-d',
//					width : 100,
//					editable : false
//				},
//				"订单状态",
//				{
//					id : 'DEA_UM_orderState',
//					xtype : 'combo',
//					editable : false,
//					displayField : 'stateName',
//					valueField : 'stateValue',
//					mode : 'local',
//					store : new Ext.data.ArrayStore({
//						fields : [ {
//							name : 'stateName',
//							type : 'string'
//						}, {
//							name : 'stateValue',
//							type : 'int'
//						} ],
//						data : [ [ '发货未确认', 1 ], [ '未发货', 0 ], [ '订单取消', 2 ],
//								[ '订单成功', 3 ], [ '全部', 4 ] ]
//					}),
//					selectOnFocus : true,
//					triggerAction : 'all',
//					value : 4,
//					width : 80,
//					listeners : {
//						'select' : function(combo, record) {
//							Ext.data.StoreManager.lookup('orderStore').load(
//									{
//										params : {
//											start : 0,
//											limit : pageSize,
//											orderId : Ext.getCmp(
//													'DEA_UM_orderId')
//													.getValue(),
//											orderDate : Ext.getCmp(
//													'DEA_UM_orderDate')
//													.getValue(),
//											orderState : Ext.getCmp(
//													'DEA_UM_orderState')
//													.getValue()
//										}
//									});
//						}
//					}
//				},
//				'-',
//				{
//					text : '&nbsp;&nbsp;&nbsp;查询',
//					iconCls : 'icon-btn-search',
//					handler : function() {
//						Ext.data.StoreManager.lookup('orderStore').load(
//								{
//									params : {
//										start : 0,
//										limit : pageSize,
//										orderId : Ext.getCmp('DEA_UM_orderId')
//												.getValue(),
//										orderDate : Ext.getCmp(
//												'DEA_UM_orderDate').getValue(),
//										orderState : Ext.getCmp(
//												'DEA_UM_orderState').getValue()
//									}
//								});
//					}
//				} ],
		// tbar : ['->', btn_del, '-', btn_mod, '-', btn_add],
		bbar : new Ext.PagingToolbar({
			id : "toolbar1",
			store : Ext.data.StoreManager.lookup('orderDetailComStore'),
     		pageSize : pageSize,
			displayInfo : true,
			displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
			emptyMsg : "没有记录"
		})
	});
	
	Ext.create('Ext.window.Window',{
		id : "userOrderWin",
		title : '用户订单详情',
		width : 1300,
		constrain : true,
		constrainHeader : true,
		layout: 'fit',
		closeAction : 'destroy',
		resizable : false,
		modal : true,// 设置此Window为模式窗口,
		animateTarget : 'grid_ordermanager',// 当指定一个id或元素,window打开时会与元素之间产生动画效果
		border : false,
		items : [_grid],
		buttonAlign : 'center',
		minButtonWidth : 80,
		buttons : [{
			text : "完成",
			tooltip : "完成操作",
			handler : function() {
				Ext.getCmp("userOrderWin").close();// 取消实际上就是关闭窗口
			}
		}]
	}).show();
}

var orderDeliver = function() {
	Ext.MessageBox.confirm('确认', '是否确定发货?', function(btn) {
		if (btn == 'yes') {
			var record = Ext.getCmp('grid_ordermanager').getSelectionModel()
					.getSelection();
			Ext.Ajax.request({
				url : "../../../../orderMan/orderDeliver.action",
				method : "POST",
				params : {
					"orderId" : record[0].data.orderId
				},
				success : function() {
					Ext.getCmp('grid_ordermanager').getStore().reload();
				}
			})
		}
	})
}

var scheManFun = function() {
	var pageSize = 25;
	var addFn = function(_url, rec, param, titleText) {

		new Ext.Window(
				{
					id : "addOrderWin",
					title : titleText,
					width : 1000,
					height : 480,
					resizable : false,
					modal : true,
//					animateTarget : 'addOrdermodity',
					listeners : {
						'show' : function() {
							btn_add.disable();
						},
						'close' : function() {
							btn_add.enable();
						}
					},
					items : [ {
						xtype : "form",
						labelWidth : 60,
						id : "addOrderForm",
						frame : true,
						bodyStyle : "padding:5px 5px 0",
						border : false,
						waitMsgTarget : true,
						labelAlign : "right",
						labelPad : 10,
						defaults : {
							width : 280
						},
						defaultType : "textfield",

						items : [ {
							anchor : '100%',
							fieldLabel : '商品编号',
							name : 'commodity.itemId'
						}, {
							anchor : '100%',
							fieldLabel : '商品名称',
							name : 'commodity.itemName'
						}, {
							id : "comboBroad",
							anchor : '100%',
							fieldLabel : '所属大类',
							xtype : 'combo',
							emptyText : '请选择',
							allowBlank : false,
							listeners : {
								select : {
									// scope : this,
									fn : function() {
										alert("sad");
									}
								}
							},
							store : storeBroadClass,
							displayField : 'broadClassName',
							valueField : 'broadClassCode',
							mode : 'remote',
							triggerAction : 'all',
							name : 'commodity.broadClassName'
						}, {
							anchor : '100%',
							fieldLabel : '所属小类',
							name : 'commodity.subClassName'
						}, {
							anchor : '100%',
							fieldLabel : '发布时间',
							xtype : 'datefield',
							value : new Date(),
							format : 'Y-m-d',
							name : 'commodity.itemPubDate'
						}, {
							anchor : '100%',
							fieldLabel : '商品照片',
							name : 'commodity.itemPhoto'
						}, {
							anchor : '100%',
							fieldLabel : '会员价格',
							allowBlank : false,
							name : 'commodity.itemPrice'
						}, {
							anchor : '100%',
							fieldLabel : '商品数量',
							name : 'commodity.itemCount'
						}, {
							anchor : '100%',
							fieldLabel : '商品单位',
							name : 'commodity.itemUnit'
						}, {
							xtype : 'textarea',
							anchor : '100%',
							fieldLabel : '商品介绍',
							name : 'commodity.itemDesc',
							emptyText : "请输入商品介绍..."
						}, {
							xtype : 'textarea',
							anchor : '100%',
							fieldLabel : "备注",
							name : "commodity.remark",
							emptyText : "请输入备注..."
						} ]

					} ],
					buttonAlign : 'center',
					minButtonWidth : 100,
					buttons : [
							{
								text : "提交",
								tooltip : "提交数据",
								handler : function() {
									if (Ext.getCmp("addOrderForm").getForm()
											.isValid()) {// 对表单进行验证(根据配置的项进行配置)
										Ext
												.getCmp("addOrderForm")
												.getForm()
												.submit(
														{// 利用表单的submit方法提交表单
															waitTitle : "请稍候", // 提交表单时进度条的标题
															waitMsg : "正在提交数据……", // 提交表单时进度条的信息
															url : _url, // 提交地址
															method : "POST", // 提交方式，需要大写
															success : function() { // 如果提交成功后处理的方法
																Ext.example
																		.msg(
																				"提交成功",
																				"提交商品信息成功……",
																				"msg-box-success");// 相应的提示信息
																Ext
																		.getCmp(
																				"addOrderWin")
																		.close(); // 根据id获取到表单的窗口，然后将其关闭
																_grid
																		.getStore()
																		.reload(); //
																// 提交成功后，需要刷新GridPanel数据，

															},

															failure : function(
																	form,
																	action) { // 提交指失败进处理的方法
																Ext.example
																		.msg(
																				"警告",
																				"数据提交失败，请核对...",
																				"msg-box-error");
															}
														});
									} else {// 如果表单验证未通过则提示用户骓未通过。

										Ext.example.msg("提示",
												"请填写完整、合法的日程信息...",
												"msg-box-error");
									}

								}

							}, {
								text : "取消",
								tooltip : "取消此操作",
								handler : function() {
									Ext.getCmp("addWin").close();// 取消实际上就是关闭窗口
								}
							} ]
				}).show();

	}

	function modifyFn(btnID) {
		var win = addOrderWin(btnID);
		win.show();
	}
	function deleteFn() {
		var win = test();
		win.show();
	}

	var btn_del = new Ext.Button({
		text : "删除用户",
		tooltip : "删除用户",
		id : "delorder",
		iconCls : 'icon-btn-del',
		handler : function() {
			// new deleteFn();
			alert("删除用户");
		}
	});

	var btn_mod = new Ext.Button({
		text : "更改用户信息",
		tooltip : "更改用户信息",
		id : "modorder",
		iconCls : 'icon-btn-add',
		handler : function() {
			alert("更改用户信息");
		}
	});

	var btn_add = new Ext.Button({
		text : "增添用户",
		tooltip : "增添用户",
		id : "addorder",
		iconCls : 'icon-btn-add',
		handler : function() {
			// var titleText = '增添用户';
			alert("增添用户");
			// false,
			// false, titleText);
		}
	});

	Ext.define('order', {
		extend : 'Ext.data.Model',
		fields : [ {
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
		} ]
	});

	Ext.create('Ext.data.Store', {
		model : 'order',
		storeId : 'orderStore',
		proxy : {
			type : 'ajax',
			url : '../../../../orderMan/getOrders.action',
			method : 'POST',
			reader : {
				type : 'json',
				root : 'orders',
				totalProperty : 'totalCount',
				idProperty : 'orderId'
			}
		},
		listeners : {
			'beforeload' : function(store, op, options) {
				var params = {
					// 参数
				orderId : Ext.getCmp(
						'DEA_UM_orderId')
						.getValue(),
				orderDateFrom : Ext.getCmp(
												'tbar_dateFrom').getValue(),
                orderDateTo : Ext.getCmp(
												'tbar_dateTo').getValue(),
				orderState : Ext.getCmp(
						'DEA_UM_orderState')
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

	var eOperations = function(value) {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="checkorderdetail()">查看详情</a></span>'

		if (value == 0) {
			links = links + "&nbsp;&nbsp;&nbsp;"
					+ '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="frozenorder()">取消订单</a></span>'
					+ "&nbsp;&nbsp;&nbsp;"
					+ '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="orderDeliver()">确认发货</a></span>';
		}
		return links;
	}
	var _sm = new Ext.selection.RowModel({
		mode : 'SINGLE'
	});
	var _grid = Ext.create('Ext.grid.Panel', {
		title : '订单管理',
		id : "grid_ordermanager",
		selType : 'rowmodel',
		store : Ext.data.StoreManager.lookup('orderStore'),
		selModel : _sm,
		columnLines : true,
		region : 'center',
		forceFit : true,
		columns : [ {
			text : '订单编号',
			width : 20,
			dataIndex : 'orderId'
		}, {
			text : '收货人编号',
			width : 20,
			dataIndex : 'consignee'
		}, {
			text : '用户编号',
			width : 20,
			dataIndex : 'user'
		}, {
			text : '总价格',
			width : 20,
			dataIndex : 'totalPrice'
		}, {
			text : '订单生成时间',
			width : 20,
			dataIndex : 'createTime'
		}, {
			text : '订单状态',
			width : 20,
			dataIndex : 'orderState',
			renderer : showOrderState
		}, {
			text : '发货时间',
			width : 20,
			dataIndex : 'deliveryTime'
		}, {
			text : '收货时间',
			width : 20,
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
			width : 50,
			renderer : eOperations
		} ],

		frame : true,
		tbar : [
				'订单号:',
				{
					xtype : 'textfield',
					id : 'DEA_UM_orderId',
					width : 100
				},
				'发布时间:', {
								id : 'tbar_dateFrom',
								xtype : 'datefield',
								format : 'Y-m-d',
								width : 90,
								editable : false,
								listeners : {
									change : function(priceFrom, newValue,
											oldValue, eOpts) {
										var dateTo = Ext.getCmp("tbar_dateTo");
										dateTo.setMinValue(newValue);
										if (dateTo.getValue() < newValue) {
											dateTo.setValue("");
										}
									}
								}
							}, '至', {
								id : 'tbar_dateTo',
								xtype : 'datefield',
								width : 90,
								format : 'Y-m-d',
								editable : false
							},
//				'日期:',
//				{
//					id : 'DEA_UM_orderDate',
//					xtype : 'datefield',
//					format : 'Y-m-d',
//					width : 100,
//					editable : false
//				},
				"订单状态",
				{
					id : 'DEA_UM_orderState',
					xtype : 'combo',
					editable : false,
					displayField : 'stateName',
					valueField : 'stateValue',
					mode : 'local',
					store : new Ext.data.ArrayStore({
						fields : [ {
							name : 'stateName',
							type : 'string'
						}, {
							name : 'stateValue',
							type : 'int'
						} ],
						data : [ [ '发货未确认', 1 ], [ '未发货', 0 ], [ '订单取消', 2 ],
								[ '订单成功', 3 ], [ '全部', 4 ] ]
					}),
					selectOnFocus : true,
					triggerAction : 'all',
					value : 4,
					width : 80,
					listeners : {
						'select' : function(combo, record) {
							Ext.data.StoreManager.lookup('orderStore').load(
									{
										params : {
											start : 0,
											limit : pageSize,
											orderId : Ext.getCmp(
													'DEA_UM_orderId')
													.getValue(),
											orderDateFrom : Ext.getCmp(
												'tbar_dateFrom').getValue(),
										    orderDateTo : Ext.getCmp(
												'tbar_dateTo').getValue(),
											orderState : Ext.getCmp(
													'DEA_UM_orderState')
													.getValue()
										}
									});
						}
					}
				},
				'-',
				{
					text : '&nbsp;&nbsp;&nbsp;查询',
					iconCls : 'icon-btn-search',
					handler : function() {
						Ext.data.StoreManager.lookup('orderStore').load(
								{
									params : {
										start : 0,
										limit : pageSize,
										orderId : Ext.getCmp('DEA_UM_orderId')
												.getValue(),
										dateFrom : Ext.getCmp(
												'tbar_dateFrom').getValue(),
										dateTo : Ext.getCmp(
												'tbar_dateTo').getValue(),
										orderState : Ext.getCmp(
												'DEA_UM_orderState').getValue()
									}
								});
					}
				} , '->', btn_logout],
		// tbar : ['->', btn_del, '-', btn_mod, '-', btn_add],
		bbar : new Ext.PagingToolbar({
			id : "toolbar2",
			store : Ext.data.StoreManager.lookup('orderStore'),
			pageSize : pageSize,
			displayInfo : true,
			displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
			emptyMsg : "没有记录"
		})
	});
	
	Ext.data.StoreManager.lookup('orderStore').load({
		params : {
			start : 0,
			limit : pageSize,
			orderState : 4
		}
	});

	return _grid;
}