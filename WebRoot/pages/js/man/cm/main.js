Ext.onReady(function() {

	var _top = new Ext.Toolbar({
		style : 'background-color:Blue; background-image:url(../../../resources/images/logo_bg.jpg);',
		height : 40,
		region : 'north',
		items : ["<h1 class='logo_header'>Carhartt商城管理系统</h1>"]
	});

	var _center = Ext.create('Ext.panel.Panel', {
				layout : "border",
				region : 'center',
				items : [scheManFun()]
			});
	var _left = Ext.create('Ext.panel.Panel', {
//				layout : "border",
				region : 'west',
				items : [classManFun()]
			});

	var _bottom = new Ext.Panel({
				region : "south",
				frame : false,
				autoHeight : true,
				items : new Ext.Toolbar({
							height : 20,
							items : [{
										xtype : 'label',
										text : '版权所有，翻版必究'
									}]
						})

			});

	var _vp = new Ext.Viewport({
				layout : "border",
				items : [_top, _center,_left, _bottom]
			});

});