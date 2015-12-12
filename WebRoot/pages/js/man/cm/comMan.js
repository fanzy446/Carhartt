var _grid;
var scheManFun = function() {
	var pageSize = 21;

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

	function modifyFn(btnID) {
		var selectionMode = Ext.getCmp("gridPanel").getSelectionModel();
		var modeType = selectionMode.getSelectionMode();
		var selection = selectionMode.getSelection();
		if (selection.length == 0) {
			Ext.example.msg("警告", "请选择一个商品", "msg-box-error");
		} else {
			if (selection.length == 1) {
				var model = _grid.getStore().findRecord('itemId',
						selection[0].get('itemId'));
				console.log(_grid.getStore().getCount());
				console.log(model);
				modComWin("modCommodity", model).show();
			} else {
				Ext.example.msg("警告", "每次只能修改一个商品条目，请重新选择", "msg-box-error");
			}
		}
	}
	function detailFn() {
		var selectionMode = Ext.getCmp("gridPanel").getSelectionModel();
		var modeType = selectionMode.getSelectionMode();
		var selection = selectionMode.getSelection();
		if (selection.length == 0) {
			Ext.example.msg("警告", "请选择一个商品", "msg-box-error");
		} else {
			if (selection.length == 1) {
				var model = _grid.getStore().findRecord('itemId',
						selection[0].get('itemId'));
				deatailComWin("datailCommodity", model).show();
			} else {
				Ext.example.msg("警告", "每次只能查看一个商品条目，请重新选择", "msg-box-error");
			}
		}

	}
	function deleteFn() {
		var selectionMode = Ext.getCmp("gridPanel").getSelectionModel();
		var modeType = selectionMode.getSelectionMode();// SINGLE, MULTI
		// or SIMPLE
		var selection = selectionMode.getSelection();// 获取选中的值
		if (selection.length == 1) {
			Ext.MessageBox.show({
						animateTarget : "delCommodity",
						title : '删除类别',
						msg : "确定删除商品类别： " + selection[0].get('itemName'),
						buttons : Ext.MessageBox.YESNO,
						icon : Ext.MessageBox.INFO,
						fn : deleteNode
					});

			function deleteNode(btn) {
				if (btn == "yes") {
					Ext.Ajax.request({
								url : "../../../../comMan/delCommodity.action",
								params : {
									itemId : selection[0].get('itemId')
								},
								method : 'POST',
								success : function(response) {
									_grid.getStore().reload();
									Ext.example.msg("删除商品", "删除商品成功",
											"msg-box-success");
								},
								failure : function() {
									Ext.example.msg("警告", "删除商品失败",
											"msg-box-error");
								}
							});
				} else {
					Ext.example.msg("取消", "取消删除操作");
				}

			}

		} else {
			if (selection.length == 0) {
				Ext.example.msg("警告", "请选择一个商品", "msg-box-error");
			} else {
				Ext.example.msg("警告", "每次只能删除一个商品条目，请重新选择", "msg-box-error");
			}
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

	var btn_del = new Ext.Button({
				text : "删除商品",
				tooltip : "删除商品",
				id : "delCommodity",
				iconCls : 'icon_btn_delete',
				handler : function() {
					new deleteFn();
				}
			});

	var btn_mod = new Ext.Button({
				text : "更改商品",
				tooltip : "更改商品",
				id : "modCommodity",
				iconCls : 'icon_btn_edit',
				handler : function() {
					new modifyFn("modCommodity");
				}
			});

	var btn_add = new Ext.Button({
				text : "增添商品",
				tooltip : "增添商品",
				id : "addCommodity",
				iconCls : 'icon_btn_add',
				handler : function() {
					var titleText = '增添商品';
					addComWin('addCommodity').show();
				}
			});

	var btn_detail = new Ext.Button({
				text : "查看详情",
				tooltip : "查看详情",
				id : "datailCommodity",
				iconCls : 'icon_btn_detail',
				handler : function() {
					detailFn();
				}
			});
	Ext.define('Commodity', {
				extend : 'Ext.data.Model',
				fields : [{
							name : "itemId",
							type : "string"
						}, {
							name : "itemName",
							type : "string"
						}, {
							name : "broadClassName",
							type : "string"
						}, {
							name : "subClassName",
							type : "string"
						}, {
							name : "itemPubDate",
							type : "string"
						}, {
							name : "itemPhoto",
							type : "string"
						}, {
							name : "itemPrice",
							type : "string"
						}, {
							name : "itemCount",
							type : "string"
						}, {
							name : "itemUnit",
							type : "string"
						}, {
							name : "itemDesc",
							type : "string"
						}, {
							name : "remark",
							type : "string"
						}]
			});

	var abc = Ext.create('Ext.data.Store', {
				model : 'Commodity',
				storeId : 'commodityStore',
				pageSize : pageSize,
				proxy : {
					type : 'ajax',
					url : '../../../../comMan/searchCommodity.action',
					method : 'POST',
					reader : {
						type : 'json',
						root : 'commodities',
						totalProperty : 'totalCount',
						idProperty : 'itemId'
					}
				},
				listeners : {
					'beforeload' : function(store, op, options) {
						var params = {
							// 参数
							tbar_itemName : Ext.getCmp('tbar_itemName')
									.getValue(),
							tbar_dateFrom : Ext.getCmp('tbar_dateFrom')
									.getValue(),
							tbar_dateTo : Ext.getCmp('tbar_dateTo').getValue(),
							tbar_priceFrom : Ext.getCmp('tbar_priceFrom')
									.getValue(),
							tbar_priceTo : Ext.getCmp('tbar_priceTo')
									.getValue(),
							tbar_countFrom : Ext.getCmp('tbar_countFrom')
									.getValue(),
							tbar_countTo : Ext.getCmp('tbar_countTo')
									.getValue()
						};
						Ext.apply(store.proxy.extraParams, params);
					}
				}
			});

	var _sm = new Ext.selection.RowModel({
				mode : 'MULTI'
			});
	_grid = Ext.create('Ext.grid.Panel', {
				title : '商品管理',
				store : Ext.data.StoreManager.lookup('commodityStore'),
				id : 'gridPanel',
				selModel : _sm,
				columnLines : true,
				region : 'center',
				border : false,
				scoll : false,
				forceFit : true,
				columns : [{
							text : '商品编号',
							dataIndex : 'itemId'
						}, {
							text : '商品名称',
							dataIndex : 'itemName'
						}, {
							text : '所属大类',
							dataIndex : 'broadClassName'
						}, {
							text : '所属小类',
							dataIndex : 'subClassName'
						}, {
							text : '发布时间',
							dataIndex : 'itemPubDate'
						}, {
							text : '会员价格',
							dataIndex : 'itemPrice'
						}, {
							text : '商品数量',
							dataIndex : 'itemCount'
						}, {
							text : '商品单位',
							dataIndex : 'itemUnit'
						}],

				frame : true,
				dockedItems : [{
					xtype : 'toolbar',
					dock : 'top',
					items : ['商品名称:', {
								xtype : 'textfield',
								id : 'tbar_itemName',
								width : 80
							}, {
								xtype : 'tbtext',
								text : "&nbsp;&nbsp;&nbsp;&nbsp;"
							}, '发布时间:', {
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
							}, {
								xtype : 'tbtext',
								text : "&nbsp;&nbsp;&nbsp;&nbsp;"
							}, '会员价格:', {
								xtype : 'numberfield',
								minValue : 0,
								width : 50,
								id : 'tbar_priceFrom',
								listeners : {
									change : function(priceFrom, newValue,
											oldValue, eOpts) {
										var priceTo = Ext
												.getCmp("tbar_priceTo");
										priceTo.setMinValue(newValue);
										if (priceTo.getValue() < newValue) {
											priceTo.setValue("");
										}
									}
								}
							}, '至', {
								xtype : 'numberfield',
								width : 50,
								minValue : 0,
								id : 'tbar_priceTo'
							}, {
								xtype : 'tbtext',
								text : "&nbsp;&nbsp;&nbsp;&nbsp;"
							}, '商品数量:', {
								xtype : 'numberfield',
								width : 50,
								id : 'tbar_countFrom',
								listeners : {
									change : function(countFrom, newValue,
											oldValue, eOpts) {
										var countTo = Ext
												.getCmp("tbar_countTo");
										countTo.setMinValue(newValue);
										if (countTo.getValue() < newValue) {
											countTo.setValue("");
										}
									}
								}
							}, '至', {
								xtype : 'numberfield',
								width : 50,
								id : 'tbar_countTo'
							}, {
								xtype : 'tbtext',
								text : "&nbsp;&nbsp;&nbsp;&nbsp;"
							}, '-', {
								text : '查询',
								iconCls : 'icon_btn_search',
								handler : function() {
									Ext.getCmp('toolbar1').moveFirst();
									Ext.data.StoreManager
											.lookup('commodityStore').load({
														params : {
															start : 0,
															limit : pageSize
														}
													});
								}
							}, '-', {
								text : '重置',
								iconCls : 'icon_btn_reset',
								handler : function() {
									Ext.getCmp('tbar_itemName').setValue("");
									Ext.getCmp('tbar_dateFrom').setValue("");
									Ext.getCmp('tbar_dateTo').setValue("");
									Ext.getCmp('tbar_priceFrom').setValue("");
									Ext.getCmp('tbar_priceTo').setValue("");
									Ext.getCmp('tbar_countFrom').setValue("");
									Ext.getCmp('tbar_countTo').setValue("");
								}
							}]
				}, {
					xtype : 'toolbar',
					dock : 'top',
					items : [btn_detail, '-', btn_del, '-', btn_mod, '-',
							btn_add, '->', btn_logout]
				}],

				bbar : new Ext.PagingToolbar({
							id : "toolbar1",
							store : Ext.data.StoreManager
									.lookup('commodityStore'),
							pageSize : pageSize,
							displayInfo : true,
							displayMsg : "第 {0} - {1} 条&nbsp;&nbsp;共 {2} 条",
							emptyMsg : "没有记录"
						})
			});
	Ext.data.StoreManager.lookup('commodityStore').load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
	console.log(Ext.data.StoreManager.lookup('commodityStore'));
	console.log(abc.getCount());
	console.log(Ext.data.StoreManager.lookup('commodityStore').findRecord(
			"itemId", "32755"));
	return _grid;
}