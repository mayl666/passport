/* ------------------------- config ------------------------- */

/** jQuery default speed */
$.fx.speeds._default = 200;

$.jgrid.defaults.url = '/admin/grid.do'; // 数据来源url
// $.jgrid.defaults.mtype = 'post'; // 请求数据时用post请求
$.jgrid.defaults.datatype = 'json'; // 数据使用json格式返回
$.jgrid.defaults.gridview = true; // 数据分批加载(不是一次读取所有数据)
// $.jgrid.defaults.altRows = true; // 隔行变色
// $.jgrid.defaults.altclass = 'alt-row'; // 隔行变色class
$.jgrid.defaults.rowNum = 20;
$.jgrid.defaults.rowList = [20,200,2000];
$.jgrid.defaults.viewrecords = true;
$.jgrid.defaults.multiselect = false; // 最前面是否有checkbox
$.jgrid.defaults.sortable = true;
$.jgrid.defaults.pager = '#pager'; // 翻页框的id

$.jgrid.defaults.width = 'auto'; // 表格总宽度
$.jgrid.defaults.height = 'auto';
//$.jgrid.defaults.autowidth = true; // 自动缩放到合适大小
//$.jgrid.defaults.shrinkToFit = true; // 自动缩放到合适大小

$.jgrid.defaults.cmTemplate = {
	align: 'center',
	sorttype: function(val) {
	    return parseFloat((''+val).replace(/[,%]/g, ''));
	}
};



/* ------------------------- util.dateInput ------------------------- */

$(function() {
	$('input[data-input=date]').each(function() {
		var format = $(this).attr('data-format') || 'yy-mm-dd';
		$(this).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: format
		});
	});
});



/* ------------------------- util.buildSimpleGrid ------------------------- */

function downloadExcel(sql) {
	var $form = $('<form action="/admin/excel.do" method="POST" target="_blank"><input type="hidden" name="sql"></form>');
	$form[0].sql.value=sql;
	$('body').append($form);
	$form.submit();
	$form.remove();
}

function buildTableGrid($div, data, sql,sortName,sortOrder) {
	 

	// build colodel
	var colModel = [];
	for (var i = 0; i < data.cols.length; i++) {
		colModel.push({ name:data.cols[i] });
	}
	var rowLen = data.rows.length;
	for (var i = 0; i < colModel.length; i++) {
		for (var row = 0; row < rowLen; row++) {
			var val = data.rows[row][i];
			if (val && !/^[0-9.,%]*$/.test(val)) {
				colModel[i].sorttype = 'text';
				break;
			}
		}
	}
	alert(sortName);
	if(!sortName){//没有排序
		// tableToGrid
		tableToGrid('#'+table.id, {
			pager:null,
			rowNum:data.rows.length, // 否则排序后会截断
			colModel:colModel
		});
	}else{//增加排序
		// tableToGrid
		for(var i = 0; i < colModel.length; i ++){
			//alert(colModel[i].name);
		}
		//设置表格
		$($div).jqGrid({
			url: '/admin/grid.do',
			postData: {
				job: 'simpleOrder',
				sql: sql
			},
			pager:null,rowNum:null,
			colModel:colModel,
			sortname:	sortName,
			sortorder:	sortOrder
		});
	}
}

$.fn.buildSimpleGrid = function(sql) {
	var $this = this;
	$.ajax({
		url: '/admin/simplegrid.do',
		data: { sql:sql },
		type:'POST',
		dataType: 'json',
		success: function(data) {
			buildTableGrid($this, data, sql);
		},
		error: function() {
			$this.html('err');
		}
	});
}


$.fn.buildSimpleGrid = function(sql,sortName,sortOrder) {
	var $this = this;
	$.ajax({
		url: '/admin/simplegrid.do',
		data: { sql:sql },
		type:'POST',
		dataType: 'json',
		success: function(data) {
			buildTableGrid($this, data, sql,sortName,sortOrder);
		},
		error: function() {
			$this.html('err');
		}
	});
}



/* ------------------------- util.buildSimpleChart ------------------------- */

//修正时区问题
Highcharts.setOptions({
	credits: { enabled: false },
	global: {
		useUTC: false
	},
	title: { text: '' },
	plotOptions: {
		spline: {
			marker: { enabled: false }
		}
	}
});


// 从jqGrid格式转为纯数据系列
function processChartData(data) {
	var seriesCache = {};
	var series = [];
	for(var i = 0; i < data.rows.length; i++) {
		var entry = data.rows[i];
		var time = entry[0];
		var num = entry[2];
		var label = entry[1];

		var row = seriesCache[label];
		if (!row) {
			row = { name: label, data: [] };
			seriesCache[label] = row;
			series.push(row);
		}

		row.data.push([time, num]);
	}
	return series;
}

function drawTimeChart($div, data, yTitle) {
	if ($div.attr('id') == '')
		$div.attr('id', 'chart' + Math.floor(Math.random()*1000000))
	var params = {
		chart: { renderTo: $div.attr('id'), type: 'spline' },
		xAxis: {
			type: 'datetime'//,
			//dateTimeLabelFormats: { month: '%b.%e' }
		},
		yAxis: { title: yTitle, min: 0 },
		series: data
	};
	window.chart = new Highcharts.Chart(params);
}

// select time, key, value
$.fn.buildTimeChart = function(sql, yTitle) {
	var $this = this;
	$.ajax({
		url: '/admin/simplegrid.do',
		data: { sql:sql },
		dataType: 'json',
		success: function(data) {
			data = processChartData(data);
			drawTimeChart($this, data, yTitle || 'value');
		},
		error: function() {
			alert('查询出错');
		}
	});
}