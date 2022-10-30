Ext.onReady(function() {
	Ext.create('Ext.form.Panel', {
		renderTo: Ext.getBody(),
		title: 'Add Film',
		height: 450,
		width: 500,
		x: 350,
		y: 50,
		bodyPadding: 10,
		defaultType: 'textfield',
		buttonAlign: 'center',
		items: [{
			fieldLabel: 'Title',
			name: 'title',
			anchor: '100%',
		}, {
			fieldLabel: 'Release Year',
			name: 'release_year',
			anchor: '100%',
		}, {
			xtype: 'combobox',
			fieldLabel: 'Special Features',
			name: 'special_features',
			store: Ext.create('Ext.data.Store', {
				fields: ['abbr', 'name'],
				data: [{
					'abbr': 'Trailers',
					'name': 'Trailers'
				}, {
					'abbr': 'Commentaries',
					'name': 'Commentaries'
				}, {
					'abbr': 'Deleted Scenes',
					'name': 'Deleted Scenes'
				}, {
					'abbr': 'Behind the Scenes',
					'name': 'Behind the Scenes',
				},
				]
			}),
			valueField: 'abbr',
			displayField: 'name',
			anchor: '100%',
		}, {

			xtype: 'combobox',
			fieldLabel: 'Ratings',
			name: 'rating',
			store: Ext.create('Ext.data.Store', {
				fields: ['abbr', 'name'],
				data: [{
					'abbr': 'G',
					'name': 'G'
				}, {
					'abbr': 'PG',
					'name': 'PG'
				}, {
					'abbr': 'PG-13',
					'name': 'PG-13'
				}, {
					'abbr': 'R',
					'name': 'R',
				}, {
					'abbr': 'NC-17',
					'name': 'NC-17',
				},
				]
			}),
			valueField: 'abbr',
			displayField: 'name',
			anchor: '100%',

		}, {
			xtype: 'combobox',
			fieldLabel: 'Language',
			name: 'langauge_id',
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
				},
				]
			}),
			valueField: 'abbr',
			displayField: 'name',
			anchor: '100%',

		}, {
			fieldLabel: 'Director Name',
			name: 'director',
			anchor: '100%',
		}, {
			xtype: 'textarea',
			fieldLabel: 'Description',
			name: 'description',
			anchor: '100%',
		},

		],

		buttons: [{
			text: 'Save',
			listeners: { //Implement listener
				click: function() {
					var form = Ext.getCmp('newForm');
					var values = form.getValues(); //get values from form id
					console.log(values);
					Ext.Ajax.request({
						url: 'http://localhost:8081/Sakila15926/addRow', //Defined path of function defined in MVC 
						method: 'POST',
						params: {

							title: values.title,
							release_year: values.release_year,
							special_features: values.special_features,
							rating: values.rating,
							language: values.language_id,
							director: values.director,
							description: values.description,
							confirmPassword: values.confirmPassword,
						},
						success: function() {
							store.reload(),
								// alert('success');
								Ext.Msg.alert('Sucess', 'Film Added');
						},
						failure: function() {
							alert('fail');
						}
					});
				}
			}
		}, {
			text: 'Cancel',

		}]
	});
});
