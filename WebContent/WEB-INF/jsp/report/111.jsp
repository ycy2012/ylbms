<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('#singleBar').highcharts({
			chart : {
				type : 'bar'
			},
			title : {
				text : '压力表具根据状态分布图'
			},
			xAxis : {
				categories : [ '未入库', '待租', '已租', '在检', '报废' ],
				title : {
					text : null
				}
			},
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
				y : 100,
				floating : true,
				borderWidth : 1,
				backgroundColor : '#FFFFFF',
				shadow : true
			},
			credits : {
				enabled : false
			},
			series : [ {
				name : '当前数量',
				data : [ 107, 31, 1635, 203, 2 ]
			} ]
		});
	});
</script>
</head>
<body>
	<div id="singleBar"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>