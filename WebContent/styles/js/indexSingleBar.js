var indexSinleBar =function(){
	return{
		init:function(){
			$.post("report/sinlgeBar", function(jsonData, textStatus) {
				$('#indexSingleBar').highcharts({
					chart : {
						type : 'bar',
						borderColor :'#ABABAB'
					},
					title : {
						text : '压力表具状态数量分布图'
					},
					xAxis : {
						categories : [ '未入库', '待租', '已租', '在检', '报废' ],
						title : {
							text : null
						}
					},
					colors : [ '#1874CD','#668B8B','#A2CD5A', '#778899' ],
					yAxis : {
						min : 0,
						title : {
							text : '数量 (只)',
							align : 'high'
						},
						labels : {
							overflow : 'justify'
						}
					},
					tooltip : {
						valueSuffix : ' 只'
					},
					plotOptions : {
						bar : {
							dataLabels : {
								enabled : true
							}
						}
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'top',
						x : -100,
						y : 80,
						floating : true,
						borderWidth : 1,
						backgroundColor : '#FFFFFF',
						shadow : true
					},
					credits : {
						enabled : false
					},
					series : [ {
						name : '压力表具',
						data : jsonData
					} ]
				});
			}, "json");
		}
	}
}();

$(function() {
	indexSinleBar.init();
});