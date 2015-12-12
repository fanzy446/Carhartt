var mesg_state = 3;

var alterFeedback = function(value) {
	var record = Ext.getCmp('grid_feedbackmanager').getSelectionModel()
			.getSelection();
	Ext.Ajax.request({
		url : "../../../../synMan/alterFeedbackById.action",
		method : "POST",
		params : {
			"messageId" : record[0].data.messageId,
			"remark" : value
			// remark = 1 为已阅, 0或空为未读, 2为收藏
		},
		success : function() {
			record[0].set("remark", value);
			if (mesg_state != 3) {
				Ext.getCmp('grid_feedbackmanager').getStore().remove(record[0]);
			}
			Ext.getCmp('grid_feedbackmanager').getStore().commitChanges();
		}
	})
}

var replyFeedback = function() {
	var record = Ext.getCmp('grid_feedbackmanager').getSelectionModel()
			.getSelection();
	var notifiedTitlefield = new Ext.form.TextField({
				fieldLabel : '回复标题',
				allowfalse : false,
				allowBlank : false,
				name : 'notifiedTitle'
			});
	var notifiedContentfield = new Ext.form.TextArea({
				fieldLabel : '回复内容',
				grow : true,
				allowBlank : false,
				name : 'notifiedContent',
				height : 150
			});

	var form = new Ext.form.FormPanel({
				frame : true,
				border : false,
				items : [notifiedTitlefield, notifiedContentfield],
				buttons : [{
					text : '确定',
					handler : function() {
						if (form.getForm().isValid()) {// 对表单进行验证(根据配置的项进行配置)
							form.getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
								url : "../../../../synMan/addNotification.action", // 提交地址
								method : "POST", // 提交方式，需要大写
								params : {
									'userId' : parseInt(record[0].data.userId),
									"messageId" : record[0].data.messageId
								},
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "发送成功",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addNotificationWindow").close(); // 根据id获取到表单的窗口，然后将其关闭
									record[0].set("isReply", 1);
									Ext.getCmp('grid_feedbackmanager').getStore().commitChanges();
								},
								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("提交失败", action.result.error,
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addNotificationWindow").close();
								}
							});
						}else{
							Ext.example.msg("提示", "请填写完整、合法的信息", "msg-box-error");
						}
					}
				}, {
					text : '关闭',
					handler : function() {
						Ext.getCmp('addNotificationWindow').close();
					}
				}]
			})
	if (Ext.getCmp('addNotificationWindow')) {
		Ext.getCmp('addNotificationWindow').close();
	}
	new Ext.Window({
				title : '修改信息',
				id : 'addNotificationWindow',
				width : 300,
				height : 200,
				layout : 'fit',
				items : [form]
			}).show();
}

var feedbackManFun = function() {
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

	var f_btn_announce = new Ext.Button({
				text : "公告管理",
				tooltip : "公告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(1);
				}
			});
	var f_btn_advertise = new Ext.Button({
				text : "广告管理",
				tooltip : "广告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(0);
				}
			});
	var f_btn_feedback = new Ext.Button({
				text : "反馈管理",
				tooltip : "反馈管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(2);
				},
				disabled : true
			});
	var pageSize = 25;
	Ext.define('feedback', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "messageId",
							type : "string"
						}, {
							name : "userId",
							type : "string"
						}, {
							name : "mesgType",
							type : "string"
						}, {
							name : "mesgTitle",
							type : "string"
						}, {
							name : "mesgContent",
							type : "string"
						}, {
							name : "mesgTime",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}, {
							name : "isReply",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'feedback',
				storeId : 'feedbackStore',
				proxy : {
					type : 'ajax',
					url : '../../../../synMan/searchFeedback.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'feedbackList',
						totalProperty : 'totalCount',
						idProperty : 'messageId'
					}
				},
				listeners : {
					'beforeload' : function(store, op, options) {
						var params = {
							// 参数
							userId : Ext.getCmp('DEA_SYN_userId').getValue(),
							mesgTitle : Ext.getCmp('DEA_SYN_mesgTitle')
									.getValue(),
							mesgType : Ext.getCmp('DEA_SYN_mesgType')
									.getValue(),
							remark : Ext.getCmp('DEA_SYN_mesgState').getValue(),
							isReply : Ext.getCmp('DEA_SYN_mesgIsReply')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var mesgTypeRender = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>一般留言</span>"
		} else if (value == 1) {
			return "<span style='color:blue;font-weight:bold;'>网站意见</span>"
		} else if (value == 2) {
			return "<span style='color:blue;font-weight:bold;'>公司意见</span>"
		} else if (value == 3) {
			return "<span style='color:blue;font-weight:bold;'>合作意见</span>"
		} else if (value == 4) {
			return "<span style='color:blue;font-weight:bold;'>产品投诉</span>"
		} else if (value == 5) {
			return "<span style='color:blue;font-weight:bold;'>服务投诉</span>"
		}
	}

	var mOperations = function(value) {
		var links = '';
		if (value == 1) {
			links = '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="alterFeedback(2)">收藏</a></span>'
		} else if (value == 2) {
			links = '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="alterFeedback(1)">取消收藏</a></span>'
		} else {
			links = '<span style="color:red;font-weight:bold;">'
					+ '<a onclick="alterFeedback(1)">已阅</a></span>'
		}
		return links;
	}

	var sOperations = function(value) {
		var links = '';
		if (value == 0) {
			links = '<span style="color:blue;font-weight:bold;">'
					+ '<a onclick="replyFeedback()">回复</a></span>'
		} else {
			links = '<span style="color:blue;font-weight:bold;">'
					+ '<a>已回复</a></span>'
		}
		return links;
	}

	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
				title : '反馈管理',
				id : "grid_feedbackmanager",
				selType : 'rowmodel',
				store : Ext.data.StoreManager.lookup('feedbackStore'),
				selModel : _sm,
				columnLines : true,
				region : 'center',
				forceFit : true,
				columns : [{
							text : '留言编号',
							width : 20,
							dataIndex : 'messageId'
						}, {
							text : '用户账号',
							width : 20,
							dataIndex : 'userId'
						}, {
							text : '留言类型',
							width : 20,
							dataIndex : 'mesgType',
							renderer : mesgTypeRender
						}, {
							text : '留言主题',
							width : 20,
							dataIndex : 'mesgTitle',
							renderer : function(value, metaData, data) {
								var cname = value;
								if (value.length > 8) {
									metaData.tdAttr = 'data-qtip="' + value
											+ '"';
									cname = value.substring(0, 8) + "...";
								}
								return cname;
							}
						}, {
							text : '留言内容',
							width : 120,
							dataIndex : 'mesgContent',
							renderer : function(value, metaData, data) {
								var cname = value;
								if (value.length > 50) {
									metaData.tdAttr = 'data-qtip="' + value
											+ '"';
									cname = value.substring(0, 50) + "...";
								}
								return cname;
							}
						}, {
							text : '留言时间',
							width : 30,
							dataIndex : 'mesgTime'
						}, {
							header : "状态",
							dataIndex : 'isReply',
							width : 15,
							renderer : sOperations
						}, {
							header : "操作",
							dataIndex : 'remark',
							width : 15,
							renderer : mOperations
						}],

				frame : true,
				tbar : [
						'用户账号:',
						{
							xtype : 'numberfield',
							id : 'DEA_SYN_userId',
							width : 100
						},
						'留言主题 :',
						{
							xtype : 'textfield',
							id : 'DEA_SYN_mesgTitle',
							width : 100
						},
						"留言类型",
						{
							id : 'DEA_SYN_mesgType',
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
										data : [['一般留言', 0], ['网站意见', 1],
												['公司意见', 2], ['合作意向', 3],
												['产品投诉', 4], ['服务投诉', 5],
												['全部', 6]]
									}),
							selectOnFocus : true,
							triggerAction : 'all',
							value : 6,
							width : 60,
							listeners : {
								'select' : function(combo, record) {
									Ext.data.StoreManager
											.lookup('feedbackStore').load({
														params : {
															start : 0,
															limit : pageSize
														}
													});
								}
							}
						},
						"留言状态",
						{
							id : 'DEA_SYN_mesgState',
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
										data : [['未读', 0], ['已阅', 1],
												['收藏', 2], ['全部', 3]]
									}),
							selectOnFocus : true,
							triggerAction : 'all',
							value : 3,
							width : 60,
							listeners : {
								'select' : function(combo, record) {
									mesg_state = combo.getValue();
									Ext.data.StoreManager
											.lookup('feedbackStore').load({
														params : {
															start : 0,
															limit : pageSize
														}
													});
								}
							}
						},
						"回复状态",
						{
							id : 'DEA_SYN_mesgIsReply',
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
										data : [['未回复', 0], ['已回复', 1],
												['全部', -1]]
									}),
							selectOnFocus : true,
							triggerAction : 'all',
							value : -1,
							width : 60,
							listeners : {
								'select' : function(combo, record) {
									Ext.data.StoreManager
											.lookup('feedbackStore').load({
														params : {
															start : 0,
															limit : pageSize
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
								Ext.data.StoreManager.lookup('feedbackStore')
										.load({
													params : {
														start : 0,
														limit : pageSize
													}
												});
							}
						}, '->', f_btn_advertise, '-', f_btn_announce, '-',
						f_btn_feedback, '-', btn_logout],
				bbar : new Ext.PagingToolbar({
							id : "feedback_toolbar1",
							store : Ext.data.StoreManager
									.lookup('feedbackStore'),
							pageSize : pageSize,
							displayInfo : true,
							displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
							emptyMsg : "没有记录"
						})
			});

	Ext.data.StoreManager.lookup('feedbackStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
	return _grid;
}