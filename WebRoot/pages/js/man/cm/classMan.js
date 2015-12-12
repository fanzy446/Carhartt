var _classTree;
function classManFun() {



	Ext.define('Class', {
				extend : 'Ext.data.Model',
				fields : [{
							name : 'nodeId',
							type : 'int'
						}, {
							name : 'name',
							type : 'string'
						}, {
							name : 'parent',
							type : 'string'
						}],
				proxy : {
					type : 'ajax',
					api : {
						create : 'createPersons',
						read : '../../../../comMan/readClass.action',
						update : 'updatePersons',
						destroy : 'destroyPersons'
					}
				}
			});

	var store = Ext.create('Ext.data.TreeStore', {
				model : 'Class',
				root : {
					name : '商品类别',
					expanded : true
				}
			});

	_classTree = Ext.create('Ext.tree.Panel', {
		id : 'treePanel',
		width : 250,
		height : 600,
		border : false,
		collapsible : true,
		collapseDirection : 'left',
		title : '类别管理',
		store : store,
		// rootVisible: false,
		displayField : 'name',
		columns : [{
					xtype : 'treecolumn',
					dataIndex : 'name',
					flex : 1
				}],
		listeners : {
			itemdblclick : function(view, record, item, index, e) {
				_grid.getStore().reload({
							params : {
								searchMode : "SEARCH_BY_CLASS",
								nodeName : record.get('name'),
								parentName : record.get('parent')
							}
						});
			}
		},
		tbar : [{
			xtype : 'button',
			text : "删除类别",
			tooltip : "删除类别",
			id : "delClass",
			iconCls : 'icon_btn_delete',
			handler : function() {
				var selectionMode = Ext.getCmp("treePanel").getSelectionModel();
				var modeType = selectionMode.getSelectionMode();// SINGLE, MULTI
				// or SIMPLE
				var selection = selectionMode.getSelection();// 获取选中的值
				if (selection.length == 1) {
					Ext.MessageBox.show({
								animateTarget : "delClass",
								title : '删除类别',
								msg : "确定删除商品类别： '"
										+ selection[0].get('parent') + "/"
										+ selection[0].get('name') + "' ?",
								buttons : Ext.MessageBox.YESNO,
								icon : Ext.MessageBox.INFO,
								fn : deleteNode
							});

					function deleteNode(btn) {
						if (btn == "yes") {
							Ext.Ajax.request({
										url : "../../../../comMan/deleteClass.action",
										params : {
											nodeName : selection[0].get('name'),
											parentName : selection[0]
													.get('parent')
										},
										method : 'POST',
										success : function(response) {
											_classTree.getStore().reload();
											var respText = Ext.JSON
													.decode(response.responseText);
											if (respText.errCode == 0) {
												Ext.example.msg("删除类别", "删除成功",
														"msg-box-success");
											} else {
												Ext.example.msg("警告",
														respText.errMsg,
														"msg-box-error");
											}
										},
										failure : function() {
											Ext.example.msg("警告", "删除类别失败",
													"msg-box-error");
										}
									});
						} else {
							Ext.example.msg("取消", "取消删除操作");
						}

					}

				} else {
					Ext.example.msg("警告", "请选择要删除的类别", "msg-box-error");
				}
			}
		}, {
			xtype : 'button',
			text : "修改类别",
			tooltip : "修改类别",
			id : "modClass",
			iconCls : 'icon_btn_edit',
			handler : function(btn) {
				var selectionMode = Ext.getCmp("treePanel").getSelectionModel();
				var modeType = selectionMode.getSelectionMode();// SINGLE, MULTI
				// or SIMPLE
				var selection = selectionMode.getSelection();// 获取选中的值
				if (selection.length == 1) {
					// 弹出修改窗口
					if (selection[0].get('parent').length == 0) {
						// 弹出修改大类窗口
						modClassWin("modClass", "broad",
								selection[0].get('parent'),
								selection[0].get('name')).show();
					} else {
						// 弹出修改小类窗口
						modClassWin("modClass", "sub",
								selection[0].get('parent'),
								selection[0].get('name')).show();
					}
				} else {
					Ext.example.msg("警告", "请选择要修改的类别", "msg-box-error");
				}
			}
		}, {
			xtype : 'button',
			text : "增添类别",
			tooltip : "添加类别",
			id : "addClass",
			iconCls : 'icon_btn_add',
			handler : function() {
				new addClassWin("addClass").show();
			}
		}]
	});
	return _classTree;
}