app.controller('dashboardCtrl', ['$scope', '$rootScope', '$http', 'dashboardService', function($scope, $rootScope, $http, dashboardService) {

	var init = function() {
		if(!$scope.category){
			$scope.category = "1";
		}
		// Get all categories
		dashboardService.getAllCategories($scope, $rootScope, $http);
	};

	$scope.getData = function(v){
		alert(v);
	}
	$scope.getPieData = function() {
		// Get all categories
		dashboardService.getPieData($scope, $rootScope, $http);
	};
	
	init();

	$scope.$on('getCategoriesAlready', function() {
		$scope.getPieData();
	});
	
	$scope.$on('getPieChartDataAlready', function() {
		
		var pieChartData = [];

		angular.forEach($scope.chartData, function(value, key) {

			pieChartData.push({
				key : value.statusName,
				value : value.vendorStatusCount
			});
		});
		
		drawChart(pieChartData);
	});

	
	var drawChart = function(pieChartData){
		
		// $scope.pieChartData;

		var height = 400;
		var width = 400;
		nv.addGraph(function() {
			var chart = nv.models.pieChart()
			.x(function(d) { return d.key})
			.y(function(d) { return d.value})
			.width(width)
			.height(height)
			.showTooltipPercent(true)
			.showLabels(true) // Display pie labels
			.labelThreshold(.05) // Configure the minimum slice size for labels to show up
			.labelType("percent"); // Configure what type of data to show in the label. Can be "key", "value" or "percent"
			
			d3.select("#pieChart").datum(pieChartData)
			.transition()
			.duration(1200)
			.attr('width', width)
			.attr('height', height)
			.call(chart);
			
			return chart;
		});
	}



}]);