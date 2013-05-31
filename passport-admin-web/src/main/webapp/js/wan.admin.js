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
$.jgrid.defaults.rowList = [ 20, 200, 2000 , 5000 , 10000 ];
$.jgrid.defaults.viewrecords = true;
$.jgrid.defaults.multiselect = false; // 最前面是否有checkbox
$.jgrid.defaults.sortable = true;
$.jgrid.defaults.pager = '#pager'; // 翻页框的id

$.jgrid.defaults.width = 'auto'; // 表格总宽度
$.jgrid.defaults.height = 'auto';
// $.jgrid.defaults.autowidth = true; // 自动缩放到合适大小
// $.jgrid.defaults.shrinkToFit = true; // 自动缩放到合适大小

$.jgrid.defaults.cmTemplate = {
	align : 'center',
	sorttype : function(val) {
		return parseFloat(('' + val).replace(/[,%]/g, ''));
	}
};

var largeNumArray = ['一','二','三','四','五','六','七','八','九','十'];

/* ------------------------- util.dateInput ------------------------- */

$(function() {
	$('input[data-input=date]').each(function() {
		var format = $(this).attr('data-format') || 'yy-mm-dd';
		$(this).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : format
		});
	});
});

/* ------------------------- util.buildSimpleGrid ------------------------- */

function downloadExcel(sql) {
	//修改直接从resin上面下载.
	var $form = $('<form action="http://10.14.131.49:8084/admin/excel.do" method="POST" target="_blank"><input type="hidden" name="sql"></form>');
	$form[0].sql.value = sql;
	$('body').append($form);
	$form.submit();
	$form.remove();
}



Date.prototype.format = function(format) {
    /*
     * eg:format="yyyy-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    // millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}



function  changeDayToWeek(dayStr) {
	var  weekArray = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
	var dayInfo = convertStrDayToDate(dayStr);
	var cols = dayInfo.day;
	var week = weekArray[cols];
	return week;
}
	
function  convertStrDayToDate(dayStr) {
	dayStr = dayStr.replace(/-0/g,"-");
	var dateArray=dayStr.split("-");
	var year=dateArray[0];
	var month=dateArray[1];//.replace("0","");
	var day=dateArray[2];
	var date=new Date(year,month-1,day);
	var weekDay = date.getDay();
	var thisMonth={};
	thisMonth.year=year;
	thisMonth.month=month;
	thisMonth.date=day;
	thisMonth.time = date;
    thisMonth.day = weekDay;
	return thisMonth;
}


function  patternDate(dayStr) {
	var pattern =  /^(\d){4}(-)[0-1][0-9](-)[0-3][0-9]$/;
	if(pattern.test(dayStr)){
		return true;
	}else {
		return false;
	}

}



function buildTableGrid($div, data, sql) {
	var table = document.createElement('table');
	table.id = 'simple_grid' + Math.round(1000 * Math.random());
	// sum table
	var sum_table = document.createElement('table');
	sum_table.id = 'simple_sum_table' + Math.round(1000 * Math.random());

	// build header
	var thead = document.createElement('thead');
	table.appendChild(thead);
	var tr = document.createElement('tr');
	thead.appendChild(tr);
    var  isContentDateColumn = false;
    var   dateColumnNum = 0;
	for ( var i = 0; i < data.cols.length; i++) {
		var th = document.createElement('th');
		if(data.cols[i] == '日期') {
			isContentDateColumn = true;
			dateColumnNum = i;
		}
		th.innerHTML = data.cols[i]; // doc.createTextNode()
		//th.title = 'ssssssssssssss';
		//th.setAttribute('title', 'after click')
		tr.appendChild(th);
		//if(console != null){console.log(tr.innerHTML)}
	}

	// build body
	var tbody = document.createElement('tbody');
	table.appendChild(tbody);
	// sum body
	var sum_tbody = document.createElement('tbody');
	sum_table.appendChild(sum_tbody);

	// 判断是否有汇总字段.
	var has_sum_table = false;
	var isWeeked = false;
	// loop each
	for ( var l = 0; l < data.rows.length; l++) {
		var tr = document.createElement('tr');
		tbody.appendChild(tr);
		var row = data.rows[l];
		// 第0个是汇总字符.
		if (row[0] != '汇总') {
			// 正常字符串.
			for ( var i = 0; i < data.cols.length; i++) {
				var td = document.createElement('td');
				if(isContentDateColumn) {
					if(i == dateColumnNum) {
						if(patternDate(row[i])) {
							var week = changeDayToWeek(row[i]);
							row[i] = row[i] + week;
							if(week =='星期六' || week == '星期日' ) {
								isWeeked = true;
							}
							
						}
					}
				}
				if(i == 0 && isWeeked) {
					td.innerHTML = "<div style='background: #f3f9ff;width: 100%;color: #000;'>" + row[i] + "</div>"
				}else {
					td.innerHTML = row[i];
				}
				tr.appendChild(td);
			}
			isWeeked = false;
		}
	}

	// build <img exportExcel>
	var $img = $('<img src="/img/admin/excel.png" />').css('cursor', 'pointer');
	$img.click(function() {
		downloadExcel(sql);
	});

	// show <table>
	$div.html(null).append(table);

	// build colodel
	var colModel = [];
	for ( var i = 0; i < data.cols.length; i++) {
		colModel.push({
			name : data.cols[i]
		});
	}
	
	var rowLen = data.rows.length;
	for ( var i = 0; i < colModel.length; i++) {
		for ( var row = 0; row < rowLen; row++) {
			var val = data.rows[row][i];
			if (val && !/^[0-9.,%]*$/.test(val)) {
				colModel[i].sorttype = 'text';
				break;
			}
		}
	}

	// tableToGrid
	tableToGrid('#' + table.id, {
		pager : null,
		rowNum : data.rows.length, // 否则排序后会截断
		colModel : colModel,
		loadComplete: function() {
			var thd = $("thead:first", table.id.hdiv)[0];
			for ( var i = 0; i < data.cols.length; i++) {
				var columnTip = window.stat_column_tip_dic[data.cols[i]];
				if(columnTip != null && columnTip != ''){
					//找到统计项对应的说明.然后将说明放到title里面显示.
					$("#"+table.id + '_' + data.cols[i]).attr("title", columnTip);
				}else{
					//如果没有标题设置一个默认的.
					$("#"+table.id + '_' + data.cols[i]).attr("title", data.cols[i]);
				}
				//if(console != null){console.log($("#"+table.id + '_' + data.cols[i]).html())}
			}
		}
	});

	// ######################################sum_table after
	// table.######################################
	/**
	 * 解决汇总排序问题.在执行了 tableToGrid 函数之后 增加一行table 在最数据的底下.tableToGrid 会对 table
	 * 增加排序.而 执行完成之后的sum_table不会添加排序事件。而且。宽度和上面的table一致。table可以对齐。
	 * 
	 * 使用两条sql增加汇总功能是因为。导出也要保持汇总记录。
	 * 
	 * 汇总行不参与排序。想了很久才解决。2012.04.01.
	 * 
	 */
	// 取到宽度。
	var div_width = parseInt($(table).attr('style').replace('width:', '')
			.replace('px', ''));

	// alert(div_width);
	for ( var l = 0; l < data.rows.length; l++) {
		var row = data.rows[l];
		// 第0个是汇总字符.
		if (row[0] == '汇总') {
			has_sum_table = true;
			// 汇总字符串
			var sum_tr = document.createElement('tr');
			// 增加样式.
			$(sum_tr).addClass('ui-widget-content jqgrow ui-row-ltr');
			sum_tbody.appendChild(sum_tr);
			for ( var i = 0; i < data.cols.length; i++) {
				// 合并tr 和 td 列.
				var sum_td = document.createElement('td');
				// style="text-align: center;"
				$(sum_td).css('text-align', 'center');
				// Math.round(aw*cw/($t.p.tblwidth-brd*vc-gw));
				// 设置宽度。比较精确的计算，比不设置要对齐的好，
				if (i != (data.cols.length - 1)) {
					// 设置平均宽度.
					$(sum_td).css('width',
							Math.round(div_width / data.cols.length) + 'px');
				} else {
					// 设置最后的一个宽度.最后一个用来解决不能整除问题.
					var tmp_width = div_width - (data.cols.length - 1)
							* Math.round(div_width / data.cols.length);
					$(sum_td).css('width', tmp_width + 'px');
				}
				sum_td.innerHTML = row[i];
				sum_tr.appendChild(sum_td);
			}
		}

	}
	// style="width: 1000px;" class="ui-jqgrid-btable" tabindex="1"
	// cellspacing="0" cellpadding="0" border="0"
	// alert($div.css('width'));
	// (parseInt($div.css('width').replace('px',''))-28);
	// alert(div_width);
	if(!$.browser.mozilla){//判断是非火狐浏览器.
		$(sum_table).css('width', div_width + "px");
	}
	$(sum_table).addClass('ui-jqgrid-btable');
	$(sum_table).attr('tabindex', '1');
	$(sum_table).attr('cellspacing', '0');
	$(sum_table).attr('cellpadding', '0');
	$(sum_table).attr('border', '0');

	if (has_sum_table) {// 如果没有汇总就显示.
		// var sum_div = document.createElement('div');
		// $(sum_div).addClass("ui-jqgrid ui-widget ui-widget-content
		// ui-corner-all");
		// $(sum_div).css('width', div_width+"px");;
		// sum_div.appendChild(sum_table);
		// $div.children(".ui-jqgrid-bdiv").append(sum_table);
		$(table).after(sum_table);
	}
	// download excel.
	$div.append($img);
}

$.fn.buildSimpleGrid = function(sql) {
	var $this = this;
	$.ajax({
		url : '/admin/simplegrid.do',
		data : {
			sql : sql
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			buildTableGrid($this, data, sql);
		},
		error : function() {
			$this.html('err');
		}
	});


}