<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>图表分析</title>
</head>
<body>
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script src="/js/jquery/plugins/highCharts/highcharts.js"></script>
	<script src="/js/jquery/plugins/highCharts/exporting.js"></script>
	<script src="/js/jquery/plugins/highCharts/export-data.js"></script>
	
	<div id="container"></div>
	
	<script type="text/javascript">
	var opener = window.opener.document.querySelector("#searchForm");
	$.get("/saleChart_getJson?"+$(opener).serialize(),function(data){
	var	names = data.groupNames;
	var	amounts = data.amounts;
		
		Highcharts.chart('container', {
			chart : {
				type : 'line'
			},
			title : {
				text : '采购报表图表分析'
			},
			subtitle : {
				text : 'Source: recentlydata'
			},
			xAxis : {
				categories : names
			},
			yAxis : {
				title : {
					text : '交易金额 (元)'
				}
			},
			plotOptions : {
				line : {
					dataLabels : {
						enabled : true
					},
					enableMouseTracking : false
				}
			},
			series : [
					{
						name : '金额',
						data : amounts
					}]
		});
	});
	</script>
</body>
</html>
