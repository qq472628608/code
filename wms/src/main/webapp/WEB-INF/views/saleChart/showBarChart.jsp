
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>图表分析</title>

<style type="text/css">
</style>
</head>
<body>
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script src="/js/jquery/plugins/highCharts/highcharts.js"></script>
	<script src="/js/jquery/plugins/highCharts/exporting.js"></script>
	<script src="/js/jquery/plugins/highCharts/export-data.js"></script>

	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	<script type="text/javascript">
		var opener = window.opener.document.querySelector("#searchForm");
		$.get("/saleChart_getJson?" + $(opener).serialize(),function(data) {
		var names = data.groupNames;
		var amounts = data.amounts;

		Highcharts.chart(
						'container',
						{
							chart : {
							type : 'column'
						},
						title : {
							text : '采购报表图表分析'
						},
						subtitle : {
							text : 'Source: recentlydata'
						},
						xAxis : {
							categories : names,
							crosshair : true
						},
						yAxis : {
							min : 0,
							title : {
							text : '交易金额(元)'
							}
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
							footerFormat : '</table>',
							shared : true,
							useHTML : true
						},
						plotOptions : {
							column : {
							pointPadding : 0.2,
							borderWidth : 0
							}
						},
						series : [ {
							name : '金额',
							data : amounts

							} ]
						});
				});
	</script>
</body>
</html>
