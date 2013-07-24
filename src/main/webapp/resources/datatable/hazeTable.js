function createTable(options) {
	var divId = options.divId;
	var url = options.url;
	var columns = options.columns;
	if (columns == null ) {
		alert("未定义表格列");
		return;
	}
	var grid =  $('#'+divId).dataTable({
		"bProcessing": true,
	    "bServerSide": true,   
		"sAjaxSource": url,
		"bFilter": false,
		"bInfo":true,
		"bSort":true,
		"bAutoWidth":true,
		"sPaginationType": "full_numbers",
		"aoColumns":columns,
		"oLanguage": {                          
            "sLengthMenu": "每页显示 _MENU_ 条记录",  
            "sZeroRecords": "没有检索到数据",  
            "sInfo": "显示第 _START_ - _END_ 条记录；共 _TOTAL_ 条记录",  
            "sInfoEmpty": "",  
            "sProcessing": "正在加载数据...",  
            "sSearch":"检索：",
            "oPaginate": {  
                "sFirst": "首页",  
                "sPrevious": "上一页",  
                "sNext": "下一页",  
                "sLast": '尾页'
            }  
        }  ,
		"fnServerData": function(sSource,aoData,fnCallback) {   
			var json = "";
			for (var i = 0; i < aoData.length;i++) {
				
				var ob = aoData[i];
					json=json+ob.name+"="+ob.value+"&";
			}
			var queryVariables = getQueryVairables();
			json =json+queryVariables;
		        $.ajax( {    
		            "type": "post",     
		            "url": sSource, 
		            "dataType":"json",
		            "data":json,
		            "success": function(resp) {
		                fnCallback(resp);   
		            }    
		        });    
		    }    
	});
	return grid;
}

/**
 * 组装页面中列表查询参数
 * @returns {String}
 */
function getQueryVairables() {
	var queryVariables = "";
	$(".databatle_query").each(function() {
		var name = $(this).attr("name");
		var value = $(this).val();
		if (value != "" && value != null) {
			queryVariables +="&queryVairables['"+name+"']="+value;
		}
	});
	alert(queryVariables);
	return queryVariables;
}

