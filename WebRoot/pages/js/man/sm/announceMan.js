var changePanel = function(value) {
	var info = Ext.getCmp("feedback_main");
	if (info) {
		info.destroy();
	}
	info = Ext.getCmp("announce_main");
	if (info) {
		info.destroy();
	}
	info = Ext.getCmp("advertise_main");
	if (info) {
		info.destroy();
	}
	// 0:广告管理 1：公告管理 2：反馈管理
	if (value == 0) {
		var new_center = Ext.create('Ext.panel.Panel', {
					layout : "border",
					region : 'center',
					id : "advertise_main",
					items : [advertiseManFun()]
				});
	} else if (value == 1) {
		var new_center = Ext.create('Ext.panel.Panel', {
					layout : "border",
					region : 'center',
					id : "announce_main",
					items : [announceManFun()]
				});
	} else if (value == 2) {
		var new_center = Ext.create('Ext.panel.Panel', {
					layout : "border",
					region : 'center',
					id : "feedback_main",
					items : [feedbackManFun()]
				});
	}
	Ext.getCmp("main_Viewport").add(new_center).show();
	Ext.getCmp("main_Viewport").doLayout();
}

var deleteAnnounce = function() {
	Ext.MessageBox.confirm('确认', '是否确定删除此公告?', function(btn) {
				if (btn == 'yes') {
					var record = Ext.getCmp('grid_announcemanager')
							.getSelectionModel().getSelection();
					Ext.Ajax.request({
								url : "../../../../synMan/DeleteAnnounceById.action",
								method : "POST",
								params : {
									"announceId" : record[0].data.announceId
								},
								success : function(data) {
									Ext.getCmp('grid_announcemanager')
											.getStore().remove(record[0]);
								}
							})
				}
			})
}

var alterAnnounce = function() {
	var record = Ext.getCmp('grid_announcemanager').getSelectionModel()
			.getSelection();
	var announceTitlefield = new Ext.form.TextField({
				fieldLabel : '公告标题',
				allowfalse : false,
				allowBlank : false,
				name : 'announceTitle',
				value : record[0].data.announceTitle
			});
	var announceContentfield = new Ext.form.TextArea({
				fieldLabel : '公告内容',
				grow : true,
				allowBlank : false,
				name : 'announceContent',
				value : record[0].data.announceContent,
				height : 100
			});
	var remarkfield = new Ext.form.TextArea({
				fieldLabel : '备注',
				allowfalse : false,
				name : 'remark',
				value : record[0].data.remark,
				height : 50
			});

	var form = new Ext.form.FormPanel({
				frame : true,
				border : false,
				items : [announceTitlefield, announceContentfield, remarkfield],
				buttons : [{
					text : '确定',
					handler : function() {
						if (form.getForm().isValid()) {
							form.getForm().submit({// 利用表单的submit方法提交表单
								waitTitle : "请稍候", // 提交表单时进度条的标题
								waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
								url : "../../../../synMan/UpdateAnnounceInfo.action", // 提交地址
								method : "POST", // 提交方式，需要大写
								params : {
									'announceId' : record[0].data.announceId
								},
								success : function(form, action) { // 如果提交成功后处理的方法
									Ext.example.msg("提交成功", "修改成功",
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("alterannounceinfoWindow")
											.close(); // 根据id获取到表单的窗口，然后将其关闭
									Ext.getCmp("grid_announcemanager")
											.getStore().reload();
								},
								failure : function(form, action) { // 提交指失败进处理的方法
									Ext.example.msg("提交失败",
											action.result.error,
											"msg-box-success");// 相应的提示信息
									Ext.getCmp("addManagerWindow").close();
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
						Ext.getCmp('alterannounceinfoWindow').close();
					}
				}]
			})
	if (Ext.getCmp('alterannounceinfoWindow')) {
		Ext.getCmp('alterannounceinfoWindow').close();
	}
	new Ext.Window({
				title : '修改信息',
				id : 'alterannounceinfoWindow',
				width : 300,
				height : 280,
				layout : 'fit',
				items : [form]
			}).show();
}

var announceManFun = function() {

	var addAnnounce = function() {
		var announceTitlefield = new Ext.form.TextField({
					fieldLabel : '公告标题',
					allowfalse : false,
					name : 'announceTitle',
					allowBlank : false
				});
		var announceContentfield = new Ext.form.TextArea({
					fieldLabel : '公告内容',
					grow : true,
					name : 'announceContent',
					height : 100,
					allowBlank : false
				});
		var remarkfield = new Ext.form.TextArea({
					fieldLabel : '备注',
					allowfalse : false,
					name : 'remark',
					height : 50
				});

		var form = new Ext.form.FormPanel({
					frame : true,
					border : false,
					items : [announceTitlefield, announceContentfield,
							remarkfield],
					buttons : [{
						text : '确定',
						handler : function() {
							if (form.getForm().isValid()) {
								form.getForm().submit({// 利用表单的submit方法提交表单
									waitTitle : "请稍候", // 提交表单时进度条的标题
									waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
									url : "../../../../synMan/AddAnnounceInfo.action", // 提交地址
									method : "POST", // 提交方式，需要大写
									success : function(form, action) { // 如果提交成功后处理的方法
										Ext.example.msg("提交成功", "修改成功",
												"msg-box-success");// 相应的提示信息
										Ext.getCmp("addAnnounceInfoWindow")
												.close(); // 根据id获取到表单的窗口，然后将其关闭
										Ext.getCmp("grid_announcemanager")
												.getStore().reload();
									},
									failure : function(form, action) { // 提交指失败进处理的方法
										Ext.example.msg("提交失败",
												action.result.error,
												"msg-box-success");// 相应的提示信息
										Ext.getCmp("addAnnounceInfoWindow")
												.close();
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
							Ext.getCmp('addAnnounceInfoWindow').close();
						}
					}]
				})
		if (Ext.getCmp('addAnnounceInfoWindow')) {
			Ext.getCmp('addAnnounceInfoWindow').close();
		}
		new Ext.Window({
					title : '修改信息',
					id : 'addAnnounceInfoWindow',
					width : 300,
					height : 280,
					layout : 'fit',
					items : [form]
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
				text : "增添公告",
				tooltip : "增添公告",
				iconCls : 'icon-btn-add',
				handler : function() {
					addAnnounce();
				}
			});

	var a_btn_advertise = new Ext.Button({
				text : "广告管理",
				tooltip : "广告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(0);
				}
			});

	var a_btn_announce = new Ext.Button({
				text : "公告管理",
				tooltip : "公告管理",
				iconCls : 'icon-btn-edit',
				handler : function() {
					changePanel(1);
				},
				disabled : true
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
	Ext.define('announce', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "announceId",
							type : "string"
						}, {
							name : "announceTitle",
							type : "string"
						}, {
							name : "announceContent",
							type : "string"
						}, {
							name : "announceTime",
							type : "string"
						}, {
							name : "managerId",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'announce',
				storeId : 'announceStore',
				proxy : {
					type : 'ajax',
					url : '../../../../synMan/searchAnnounce.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'announceList',
						totalProperty : 'totalCount',
						idProperty : 'announceId'
					}
				}
			});
	Ext.data.StoreManager.lookup('announceStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	var aOperations = function() {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="alterAnnounce()">修改公告</a></span>'
				+ "&nbsp;&nbsp;&nbsp;"
				+ '<span style="color:red;font-weight:bold;">'
				+ '<a onclick="deleteAnnounce()">删除</a></span>'
		return links;
	}
	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
				title : '公告管理',
				id : "grid_announcemanager",
				selType : 'rowmodel',
				store : Ext.data.StoreManager.lookup('announceStore'),
				selModel : _sm,
				columnLines : true,
				region : 'center',
				forceFit : true,
				columns : [{
							text : '公告编号',
							width : 20,
							dataIndex : 'announceId'
						}, {
							text : '公告标题',
							width : 20,
							dataIndex : 'announceTitle',
							renderer : function(value, metaData, data) {
								var cname = value;
								if (value.length > 10) {
									metaData.tdAttr = 'data-qtip="' + value.substring(10,value.length)
											+ '"';
									cname = value.substring(0, 10) + "...";
								}
								return cname;
							}
						}, {
							text : '公告内容',
							width : 60,
							xtype : 'gridcolumn',
							dataIndex : 'announceContent',
							flex : 1,
							renderer : function(value, metaData, data) {
								var cname = value;
								if (value.length > 40) {
									metaData.tdAttr = 'data-qtip="' + value
											+ '"';
									cname = value.substring(0, 40) + "...";
								}
								return cname;
							}
						}, {
							text : '发布时间',
							width : 20,
							dataIndex : 'announceTime'
						}, {
							text : '发布人',
							width : 20,
							dataIndex : 'managerId'
						}, {
							text : '备注',
							width : 20,
							dataIndex : 'remark',
							renderer : function(value, metaData, data) {
								var cname = value;
								if (value.length > 10) {
									metaData.tdAttr = 'data-qtip="' + value.substring(10,value.length)
											+ '"';
									cname = value.substring(0, 10) + "...";
								}
								return cname;
							}
						}, {
							header : "操作",
							dataIndex : 'managerType',
							width : 20,
							renderer : aOperations
						}],

				frame : true,
				tbar : [a_btn_addann, '->', a_btn_advertise, '-',
						a_btn_announce, '-', a_btn_feedback, '-', btn_logout],
				bbar : new Ext.PagingToolbar({
							id : "announce_toolbar1",
							store : Ext.data.StoreManager
									.lookup('announceStore'),
							pageSize : pageSize,
							displayInfo : true,
							displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
							emptyMsg : "没有记录"
						})
			});
	return _grid;
}