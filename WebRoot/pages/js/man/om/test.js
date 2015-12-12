function test() {
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
	storeBroadClass.load({
				scope : this,
				callback : function(records, operation, success) {
					console.log(storeBroadClass.getCount()
							+ storeBroadClass.getAt(1).get("broadClassName"));
				}
			});

	var broadclassCombo = Ext.create('Ext.form.field.ComboBox', {
		id : "broadclassCombo",
		name : 'commodity.broadClassName',
		fieldLabel : '所属大类',
		store : storeBroadClass,
		queryMode : 'remote',
		displayField : 'broadClassName'
			// valueField : 'broadClassCode'
			// 加上id就只能响应一次
		});

	schoolCombo = Ext.create("Ext.form.ComboBox", {
				id : 'schoolCombo',
				fieldLabel : '所属大类',
				labelAlign : 'right',
				store : storeBroadClass,
				displayField : 'broadClassName',
				listeners : {
					change : function(schoolCombo) {
						var school = schoolCombo.getValue()
						var studentStore = Ext.getCmp("studentCombo")
								.getStore()
						studentStore.clearFilter();
						studentStore.filter("broadClassName", school)
					}
				}
			});

	// 所属小类
	Ext.regModel('SubClass', {
				fields : [{
							type : "string",
							name : "broadClassName"
						}, {
							type : "string",
							name : "subClassName"
						}]
			});
	var storeSubClass = Ext.create('Ext.data.Store', {
				model : 'SubClass',
				autoLoad : true,
				proxy : {
					type : 'ajax',
					url : '../../../../comMan/findAllSubClass.action',
					reader : {
						type : 'json',
						root : 'subClass'
					}
				}
			});
	storeSubClass.load({
				scope : this,
				callback : function(records, operation, success) {
					console.log(storeSubClass.getCount()
							+ storeSubClass.getAt(1).get("subClassName"));
				}
			});

	// var subclassCombo = Ext.create('Ext.form.field.ComboBox', {
	// id : "subclassCombo",
	// name : 'commodity.subClassName',
	// fieldLabel : '所属小类',
	// store : storeSubClass,
	// // queryMode : 'remote',
	// displayField : 'subClassName'
	// // valueField : 'broadClassCode'
	// // 加上id就只能响应一次
	// });
	studentCombo = Ext.create("Ext.form.ComboBox", {
				id : 'studentCombo',
				fieldLabel : '所属小类',
				labelAlign : 'right',
				displayField : 'subClassName',
				store : storeSubClass
			});
	win = Ext.create('widget.window', {
				title : '测试',
				id : "addWin",
				closable : true,
				// animateTarget : btnID,
				width : 600,
				modal : true,
				minWidth : 350,
				height : 450,
				layout : {
					type : 'form',
					padding : 5
				},
				bodyStyle : "padding:5px 5px 0 10px",
				labelAlign : "left",
				labelWidth : 60,
				items : [schoolCombo, studentCombo]
			});
	return win;
}