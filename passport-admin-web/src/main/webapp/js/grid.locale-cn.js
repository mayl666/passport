;(function($){
/**
 * jqGrid Chinese Translation for v3.6
 * waiting 2010.01.18
 * http://waiting.javaeye.com/
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 * 
 * update 2010.05.04
 *		add double u3000 SPACE for search:odata to fix SEARCH box display err when narrow width from only use of eq/ne/cn/in/lt/gt operator under IE6/7
**/
$.jgrid = {
	defaults : {
		recordtext: "{0} - {1}\u3000\u5171 {2} \u6761",	// \u5171\u5b57\u524d\u662f\u5168\u89d2\u7a7a\u683c
		emptyrecords: "\u65e0\u6570\u636e\u663e\u793a",
		loadtext: "\u8bfb\u53d6\u4e2d...",
		pgtext : " {0} \u5171 {1} \u9875"
	},
	search : {
		caption: "\u641c\u7d22...",
		Find: "\u67e5\u627e",
		Reset: "\u91cd\u7f6e",
		odata : ['\u7b49\u4e8e\u3000\u3000', '\u4e0d\u7b49\u3000\u3000', '\u5c0f\u4e8e\u3000\u3000', '\u5c0f\u4e8e\u7b49\u4e8e','\u5927\u4e8e\u3000\u3000','\u5927\u4e8e\u7b49\u4e8e', 
			'\u5f00\u59cb\u4e8e','\u4e0d\u5f00\u59cb\u4e8e','\u5c5e\u4e8e\u3000\u3000','\u4e0d\u5c5e\u4e8e','\u7ed3\u675f\u4e8e','\u4e0d\u7ed3\u675f\u4e8e','\u5305\u542b\u3000\u3000','\u4e0d\u5305\u542b'],
		groupOps: [	{ op: "AND", text: "\u6240\u6709" },	{ op: "OR",  text: "\u4efb\u4e00" }	],
		matchText: " \u5339\u914d",
		rulesText: " \u89c4\u5219"
	},
	edit : {
		addCaption: "\u6dfb\u52a0\u8bb0\u5f55",
		editCaption: "\u7f16\u8f91\u8bb0\u5f55",
		bSubmit: "\u63d0\u4ea4",
		bCancel: "\u53d6\u6d88",
		bClose: "\u5173\u95ed",
		saveData: "\u6570\u636e\u5df2\u6539\u53d8\uff0c\u662f\u5426\u4fdd\u5b58\uff1f",
		bYes : "\u662f",
		bNo : "\u5426",
		bExit : "\u53d6\u6d88",
		msg: {
			required:"\u6b64\u5b57\u6bb5\u5fc5\u9700",
			number:"\u8bf7\u8f93\u5165\u6709\u6548\u6570\u5b57",
			minValue:"\u8f93\u503c\u5fc5\u987b\u5927\u4e8e\u7b49\u4e8e ",
			maxValue:"\u8f93\u503c\u5fc5\u987b\u5c0f\u4e8e\u7b49\u4e8e ",
			email: "\u8fd9\u4e0d\u662f\u6709\u6548\u7684e-mail\u5730\u5740",
			integer: "\u8bf7\u8f93\u5165\u6709\u6548\u6574\u6570",
			date: "\u8bf7\u8f93\u5165\u6709\u6548\u65f6\u95f4",
			url: "\u65e0\u6548\u7f51\u5740\u3002\u524d\u7f00\u5fc5\u987b\u4e3a ('http://' \u6216 'https://')",
			nodefined : " \u672a\u5b9a\u4e49\uff01",
			novalue : " \u9700\u8981\u8fd4\u56de\u503c\uff01",
			customarray : "\u81ea\u5b9a\u4e49\u51fd\u6570\u9700\u8981\u8fd4\u56de\u6570\u7ec4\uff01",
			customfcheck : "Custom function should be present in case of custom checking!"
			
		}
	},
	view : {
		caption: "\u67e5\u770b\u8bb0\u5f55",
		bClose: "\u5173\u95ed"
	},
	del : {
		caption: "\u5220\u9664",
		msg: "\u5220\u9664\u6240\u9009\u8bb0\u5f55\uff1f",
		bSubmit: "\u5220\u9664",
		bCancel: "\u53d6\u6d88"
	},
	nav : {
		edittext: "",
		edittitle: "\u7f16\u8f91\u6240\u9009\u8bb0\u5f55",
		addtext:"",
		addtitle: "\u6dfb\u52a0\u65b0\u8bb0\u5f55",
		deltext: "",
		deltitle: "\u5220\u9664\u6240\u9009\u8bb0\u5f55",
		searchtext: "",
		searchtitle: "\u67e5\u627e",
		refreshtext: "",
		refreshtitle: "\u5237\u65b0\u8868\u683c",
		alertcap: "\u6ce8\u610f",
		alerttext: "\u8bf7\u9009\u62e9\u8bb0\u5f55",
		viewtext: "",
		viewtitle: "\u67e5\u770b\u6240\u9009\u8bb0\u5f55"
	},
	col : {
		caption: "\u9009\u62e9\u5217",
		bSubmit: "\u786e\u5b9a",
		bCancel: "\u53d6\u6d88"
	},
	errors : {
		errcap : "\u9519\u8bef",
		nourl : "\u6ca1\u6709\u8bbe\u7f6eurl",
		norecords: "\u6ca1\u6709\u8981\u5904\u7406\u7684\u8bb0\u5f55",
		model : "colNames \u548c colModel \u957f\u5ea6\u4e0d\u7b49\uff01"
	},
	formatter : {
		integer : {thousandsSeparator: " ", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: " ", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
		         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			],
			monthNames: [
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},
			srcformat: 'Y-m-d',
			newformat: 'm-d-Y',
			masks : {
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				ShortDate: "Y/j/n",
				LongDate: "l, F d, Y",
				FullDateTime: "l, F d, Y g:i:s A",
				MonthDay: "F d",
				ShortTime: "g:i A",
				LongTime: "g:i:s A",
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				YearMonth: "F, Y"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
};
})(jQuery);
