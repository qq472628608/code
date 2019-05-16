
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
		$.get("/orderChart_getPieJson?" + $(opener).serialize(),function(data) {
		var json = data;
		console.debug(json);

		Highcharts.chart('container', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: '采购报表图表分析'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                style: {
		                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                }
		            }
		        }
		    },
		    series: [{
		        name: '金额',
		        colorByPoint: true,
		        data: json
		    }]
		});
				});
	</script>
</body>
</html>
