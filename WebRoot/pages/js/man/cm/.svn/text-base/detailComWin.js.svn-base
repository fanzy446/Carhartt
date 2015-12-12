function deatailComWin(btnID, selection) {
	var win = Ext.create('widget.window', {
				id : "deatailComWin",
				title : '查看商品详情',
				width : 590,
				height : 440,
				closable : true,
				resizable : false,
				animateTarget : btnID,
				autoScroll:true,
				modal : true,
				items : [{
					xtype : "form",
					labelWidth : 60,
					id : "deatailComForm",
					frame : true,
					bodyStyle : "padding:5px 5px 0",
					border : true,
					waitMsgTarget : true,
					labelAlign : "right",
					labelPad : 10,
					defaultType : "textfield",
					items : [{
						xtype : 'fieldset',
						title : '基本信息',
						layout : {
							type : 'table',
							columns : 3
						},
						defaults : {
							anchor : '100%'
						},
						items : [{
							xtype : 'box',
							rowspan : 4,
							width : 90,
							height : 90,
							autoEl : {
								tag : 'img',
								src : '../../../../'
										+ selection.get('itemPhoto')
							}
						}, {
							xtype : 'displayfield',
							fieldLabel : '商品编号',
							anchor : '100%',
							labelAlign : "right",
							value : selection.get('itemId')
						}, {
							xtype : 'displayfield',
							fieldLabel : '商品名称',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('itemName')
						}, {
							xtype : 'displayfield',
							fieldLabel : '所属大类',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('broadClassName')
						}, {
							xtype : 'displayfield',
							fieldLabel : '所属小类',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('subClassName')
						}, {
							xtype : 'displayfield',
							fieldLabel : '发布时间',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('itemPubDate')
						}, {
							xtype : 'displayfield',
							fieldLabel : '会员价格',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('itemPrice')
						}, {
							xtype : 'displayfield',
							fieldLabel : '商品数量',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('itemCount')
						}, {
							xtype : 'displayfield',
							fieldLabel : '商品单位',
							labelAlign : "right",
							anchor : '100%',
							value : selection.get('itemUnit')
						}]

					}, {
						xtype : 'fieldset',
						title : '商品介绍',
						layout : 'anchor',
						minHeight : 100,
						defaults : {
							anchor : '100%'
						},
						items : [{
									xtype : 'displayfield',
									value : selection.get('itemDesc')
								}]

					}, {
						xtype : 'fieldset',
						title : '备注',
						layout : 'anchor',
						minHeight : 100,
						defaults : {
							anchor : '100%'
						},
						items : [{
									xtype : 'displayfield',
									value : selection.get('remark')
								}]

					}]
				}],
				buttonAlign : 'center',
				minButtonWidth : 100,
				buttons : [{
							text : "关闭",
							tooltip : "关闭窗口",
							handler : function() {
								Ext.getCmp("deatailComWin").close();// 取消实际上就是关闭窗口
							}
						}]
			});
	return win;
}