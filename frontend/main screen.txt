Ext.onReady(function() {
	Ext.create('Ext.form.Panel', {
		renderTo: Ext.getBody(),
		title: 'Movie Advance Search',
		bodyPadding: 10,
		x: 400,
		width: 400,
		defaultType: 'textfield',
		buttonAlign: 'center',
		items: [{
			fieldLabel: 'Movie Name',
			name: 'movieName',
			anchor: '100%',
		}, {
			fieldLabel: 'Director Name',
			name: 'DirectorName',
			anchor: '100%',
		}, {
			xtype: 'datefield',
			fieldLabel: 'Realese Date',
			name: 'releaseDate',
			anchor: '100%',
		}, {

			xtype: 'combobox',
			fieldLabel: 'Language',
			store: Ext.create('Ext.data.Store', {
				fields: ['abbr', 'name'],
				data: [{
					'abbr': 'English',
					'name': 'English'
				}, {
					'abbr': 'Italian',
					'name': 'Italian'
				}, {
					'abbr': 'Japanese',
					'name': 'Japanese'
				}, {
					'abbr': 'Mandarin',
					'name': 'Mandarin',
				}, {
					'abbr': 'French',
					'name': 'French',
				}, {
					'abbr': 'German',
					'name': 'German',
				},]
			}),
			valueField: 'abbr',
			displayField: 'name',
			anchor: '100%',
		}

		],

		buttons: [{
			text: 'Search',
			listeners: { //Implement listener
				click: function() {
					var form = Ext.getCmp('newForm');
					var values = form.getValues(); //get values from form id
					console.log(values);
					Ext.Ajax.request({
						url: 'http://localhost:8081/sakila15926/advanceSearch', //Defined path of function defined in MVC 
						method: 'POST',
						params: {
							username: values.username,
							email: values.email,
							password: values.password,
							confirmPassword: values.confirmPassword,
						},
						success: function() {
							store.reload(),
								// alert('success');
								Ext.Msg.alert('Sucess');
						},
						failure: function() {
							alert('fail');
						}
					});
				}
			}
		}, {
			text: 'Reset',

		}]
	});

	Ext.define('User', {
		extend: 'Ext.data.Model',
		fields: ['title', 'description', 'release_year', 'rating', 'language_id', 'special_features', 'director'],
	});
	var itemsPerPage = 10;
	//STORE
	var store = Ext.create('Ext.data.Store', {
		model: 'User',
		id: 'store',
		pageSize: 10,
		pageSize: itemsPerPage,
		storeId: 'movieData',
		waitTitle: 'Connecting',
		waitMsg: 'Loading',
		proxy: {
			type: 'ajax',
			eanablePaging: true,
			url: 'http://localhost:8081/Sakila15926/dataLoading',
			reader: {
				type: 'json',
				rootProperty: 'response.data',
				totalPropert: 'response.count',
			},
		},
		autoLoad: true,
	});

	//GRID
	var grid = Ext.create('Ext.grid.Panel', {
		title: 'Grid',
		layout: 'fit',
		store: Ext.data.StoreManager.lookup('movieData'),
		dockedItems: [{
			xtype: 'pagingtoolbar',
			displayInfo: true,
			dock: 'top',
			store: store
		}],
		columns: [{
			text: 'Title',
			dataIndex: 'title',
			type: 'string',
			flex: 1,
		}, {
			text: 'Description',
			dataIndex: 'description',
			flex: 3,

		}, {
			text: 'Release Year',
			dataIndex: 'release_year',
			flex: 1,
		}, {
			text: 'Director',
			dataIndex: 'director',
			flex: 1,
		}, {
			text: 'Rating',
			dataIndex: 'rating',
			flex: 1,
		}, {
			text: 'Special Feature',
			dataIndex: 'special_features',
			flex: 1,
		},],
		fullscreen: true,
		selModel: {
			selType: 'checkboxmodel',
		},


		renderTo: Ext.getBody(),

	});
});