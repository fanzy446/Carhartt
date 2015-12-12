var alterManager = function() {
	var record = Ext.getCmp('grid_supermanager').getSelectionModel()
			.getSelection();
	var managerNamefield = new Ext.form.TextField({
				fieldLabel : '登陆账号',
				allowfalse : false,
				vtype : 'alphanum',
				name : 'managerName',
				allowBlank : false,
				value : record[0].data.managerName
			});
	var managerPassfield = new Ext.form.TextField({
				fieldLabel : '登陆密码',
				allowfalse : false,
				vtype : 'alphanum',
				name : 'managerPass',
				allowBlank : false,
				value : record[0].data.managerPass
			});
	var employeeNamefield = new Ext.form.TextField({
				fieldLabel : '员工名称',
				allowfalse : false,
				allowBlank : false,
				name : 'employeeName',
				value : record[0].data.employeeName
			});
	var remarkfield = new Ext.form.TextArea({
				fieldLabel : '备注',
				grow : true,
				name : 'remark',
				value : record[0].data.remark,
				height : 50
			});
	var form = new Ext.form.FormPanel({
		frame : true,
		border : false,
		items : [managerNamefield, managerPassfield, employeeNamefield, {
			id : 'Window_superRole',
			xtype : 'combo',
			fieldLabel : '管理员类型',
			emptyText : '请选择',
			name : 'managerType',
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
						data : [['超级管理员', 0], ['商品管理员', 1], ['订单管理员', 2],
								['综合管理员', 3], ['用户管理员', 4]]
					}),
			mode : 'local',
			selectOnFocus : true,
			triggerAction : 'all',
			value : parseInt(record[0].data.managerType)
		}, remarkfield],
		buttons : [{
			text : '确定',
			handler : function() {
				if (form.getForm().isValid()) {
					form.getForm().submit({// 利用表单的submit方法提交表单
						waitTitle : "请稍候", // 提交表单时进度条的标题
						waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
						url : "../../../../superMan/UpdateManagerInfo.action", // 提交地址
						method : "POST", // 提交方式，需要大写
						params : {
							'managerId' : record[0].data.managerId
						},
						success : function(form, action) { // 如果提交成功后处理的方法
							Ext.example.msg("提交成功", "修改成功", "msg-box-success");// 相应的提示信息
							Ext.getCmp("altersuperinfoWindow").close(); // 根据id获取到表单的窗口，然后将其关闭

							Ext.getCmp("grid_supermanager").getStore().reload();
						},
						failure : function(form, action) { // 提交指失败进处理的方法
							Ext.example.msg("提交失败", "提交失败，请检查格式……",
									"msg-box-success");// 相应的提示信息
						}
					});
				} else {
					Ext.example.msg("提示", "请填写完整、合法的信息", "msg-box-error");
				}
			}
		}, {
			text : '关闭',
			handler : function() {
				Ext.getCmp('altersuperinfoWindow').close();
			}
		}]
	})
	if (Ext.getCmp('altersuperinfoWindow')) {
		Ext.getCmp('altersuperinfoWindow').close();
	}
	new Ext.Window({
				title : '修改信息',
				id : 'altersuperinfoWindow',
				width : 350,
				height : 250,
				layout : 'fit',
				// closeAction : 'hide',
				items : [form]
			}).show();
}

var addManager = function() {
	var managerNamefield = new Ext.form.TextField({
				fieldLabel : '登陆账号',
				allowfalse : false,
				allowBlank : false,
				vtype : 'alphanum',
				name : 'managerName'
			});
	var managerPassfield = new Ext.form.TextField({
				fieldLabel : '登陆密码',
				allowfalse : false,
				allowBlank : false,
				name : 'managerPass'
			});
	var employeeNamefield = new Ext.form.TextField({
				fieldLabel : '员工名称',
				allowfalse : false,
				allowBlank : false,
				name : 'employeeName'
			});
	var remarkfield = new Ext.form.TextArea({
				fieldLabel : '备注',
				grow : true,
				name : 'remark',
				height : 50
			});
	var form = new Ext.form.FormPanel({
		frame : true,
		border : false,
		items : [managerNamefield, managerPassfield, employeeNamefield, {
			id : 'Window_superRole',
			xtype : 'combo',
			fieldLabel : '管理员类型',
			emptyText : '请选择',
			name : 'managerType',
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
						data : [['超级管理员', 0], ['商品管理员', 1], ['订单管理员', 2],
								['综合管理员', 3], ['用户管理员', 4]]
					}),
			mode : 'local',
			selectOnFocus : true,
			value : 2,
			triggerAction : 'all'
		}, remarkfield],
		buttons : [{
			text : '确定',
			handler : function() {
				if (form.getForm().isValid()) {
					form.getForm().submit({// 利用表单的submit方法提交表单
						waitTitle : "请稍候", // 提交表单时进度条的标题
						waitMsg : "正在提交数据，稍后……", // 提交表单时进度条的信息
						url : "../../../../superMan/AddManager.action", // 提交地址
						method : "POST", // 提交方式，需要大写
						success : function(form, action) { // 如果提交成功后处理的方法
							Ext.example.msg("提交成功", "添加成功", "msg-box-success");// 相应的提示信息
							Ext.getCmp("addManagerWindow").close(); // 根据id获取到表单的窗口，然后将其关闭
							Ext.getCmp("grid_supermanager").getStore().reload();
						},
						failure : function(form, action) { // 提交指失败进处理的方法
							Ext.example.msg("提交失败", action.result.error,
									"msg-box-success");// 相应的提示信息
							Ext.getCmp("addManagerWindow").close();
						}
					});
				} else {
					Ext.example.msg("提示", "请填写完整、合法的信息", "msg-box-error");
				}
			}
		}, {
			text : '关闭',
			handler : function() {
				Ext.getCmp('addManagerWindow').close();
			}
		}]
	})
	// if(Ext.getCmp('addManagerWindow')){
	// Ext.getCmp('addManagerWindow').close();
	// }
	new Ext.Window({
				title : '修改信息',
				id : 'addManagerWindow',
				width : 350,
				height : 250,
				layout : 'fit',
				items : [form]
			}).show();
}

var deleteManager = function() {
	Ext.MessageBox.confirm('确认', '是否确定删除此用户?', function(btn) {
		if (btn == 'yes') {
			var record = Ext.getCmp('grid_supermanager').getSelectionModel()
					.getSelection();
			Ext.Ajax.request({
						url : "../../../../superMan/DeleteManagerById.action",
						method : "POST",
						params : {
							"managerId" : record[0].data.managerId,
							"managerName" : record[0].data.managerName,
							"managerPass" : record[0].data.managerPass
						},
						success : function() {
							Ext.getCmp('grid_supermanager').getStore().reload();
						}
					})
		}
	})
}

var scheManFun = function() {
	var pageSize = 25;
	var btn_add = new Ext.Button({
				text : "增添管理员",
				tooltip : "增添管理员",
				id : "addsuper",
				iconCls : 'icon-btn-add',
				handler : function() {
					addManager()
				}
			});
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

	Ext.define('super', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "managerId",
							type : "string"
						}, {
							name : "managerName",
							type : "string"
						}, {
							name : "managerPass",
							type : "string"
						}, {
							name : "managerType",
							type : "string"
						}, {
							name : "employeeName",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}]
			});

	Ext.create('Ext.data.Store', {
				model : 'super',
				storeId : 'superStore',
				proxy : {
					type : 'ajax',
					url : '../../../../superMan/searchSuper.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'managerList',
						totalProperty : 'totalCount',
						idProperty : 'managerId'
					}
				}
			});
	Ext.data.StoreManager.lookup('superStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	var showState = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>未冻结</span>"
		} else {
			return "<span style='color:red;font-weight:bold;'>已冻结</span>"
		}
	}

	var showsuperRole = function(value) {
		if (value == 0) {
			return "<span style='color:blue;font-weight:bold;'>超级管理员</span>"
		} else if (value == 1) {
			return "<span style='color:blue;font-weight:bold;'>商品管理员</span>"
		} else if (value == 2) {
			return "<span style='color:blue;font-weight:bold;'>订单管理员</span>"
		} else if (value == 3) {
			return "<span style='color:blue;font-weight:bold;'>综合管理员</span>"
		} else if (value == 4) {
			return "<span style='color:blue;font-weight:bold;'>用户管理员</span>"
		}
	}

	var eOperations = function() {
		var links = '';
		links = links + '<span style="color:blue;font-weight:bold;">'
				+ '<a onclick="alterManager()">修改信息</a></span>'
				+ "&nbsp;&nbsp;&nbsp;"
				+ '<span style="color:red;font-weight:bold;">'
				+ '<a onclick="deleteManager()">删除</a></span>'
		return links;
	}
	var _sm = new Ext.selection.RowModel({
				mode : 'SINGLE'
			});
	var _grid = Ext.create('Ext.grid.Panel', {
				title : '用户管理',
				id : "grid_supermanager",
				selType : 'rowmodel',
				store : Ext.data.StoreManager.lookup('superStore'),
				selModel : _sm,
				columnLines : true,
				region : 'center',
				forceFit : true,
				columns : [{
							text : '管理员编号',
							width : 20,
							dataIndex : 'managerId'
						}, {
							text : '登陆账号',
							width : 20,
							dataIndex : 'managerName'
						}, {
							text : '管理员类型',
							width : 20,
							dataIndex : 'managerType',
							renderer : showsuperRole
						}, {
							text : '员工名称',
							width : 30,
							dataIndex : 'employeeName'
						}, {
							text : '备注',
							width : 20,
							dataIndex : 'remark'
						}, {
							header : "操作",
							dataIndex : 'managerType',
							width : 20,
							renderer : eOperations
						}],

				frame : true,
				tbar : ['->', btn_add, btn_logout],
				bbar : new Ext.PagingToolbar({
							id : "toolbar1",
							store : Ext.data.StoreManager.lookup('superStore'),
							pageSize : pageSize,
							displayInfo : true,
							displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
							emptyMsg : "没有记录"
						})
			});

	return _grid;
}