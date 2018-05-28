app.controller('pmReTaskCostCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'pmReportService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService) {
	
	$scope.totalTaskCostPCostPerReByDu = [];
	$scope.totalTaskCostACostPerReByDu = [];
	
	$scope.initLanguage = function(){
		$translate.use($location.search().lang);
	}
	$scope.initTaskCost = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getTaskCost();
			$translate.use($location.search().lang);
		}
	}
	
	$scope.changeToDate = function(toDate) {
	    $scope.endDate = $filter('date')(new Date(toDate), "yyyy-MM-dd");
	}
	$scope.changeFromDate = function(fromDate) {
	    $scope.startDate = $filter('date')(new Date(fromDate), "yyyy-MM-dd");
	}
	$scope.gotoUrl = function(report) {
		$scope.reportPanel =  "/redsun/static/partials/" + report.file_name +".html";
	}	
	$scope.getTaskCost = function(){
		pmReportService.getTaskCost($scope);
	}
	$scope.$on('getTaskCostAlready', function() {
		if ($scope.data) {
			$scope.taskCost = $scope.data.result.taskCost;
			drawTaskCostMultiBarChart();
			drawTaskCostPieChar();
		}
	});	

	//task cost chart
	var drawTaskCostMultiBarChart = function(){
		$scope.labelTaskCostMultiBar = [];
		for(var i=0; i<$scope.taskCost.length; i++){
			$scope.labelTaskCostMultiBar.push($scope.taskCost[i].taskName);
			$scope.totalTaskCostPCostPerReByDu.push($scope.taskCost[i].totalPlanningCost);
			$scope.totalTaskCostACostPerReByDu.push($scope.taskCost[i].totalActuallyCost);
		}
		$scope.seriesTaskCostMultiBar = ['Actual', 'Planning'];
		$scope.optionTaskCostMultiBar = { title: {display: true, text: "Cost Status"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		//$scope.optionTaskCostMultiBar = { legend: { display: true } }; // show legend
		$scope.colorTaskCostMultiBar = ['#678BC7', '#E78C07'];
		$scope.dataTaskCostMultiBar = [$scope.totalTaskCostACostPerReByDu, $scope.totalTaskCostPCostPerReByDu];
	}
	
	var drawTaskCostPieChar = function(){
		var totalTaskCostACostByDu = 0;
		var totalTaskCostPCostByDu = 0;
		for(var i=0;i<$scope.totalTaskCostACostPerReByDu.length; i++)
			totalTaskCostACostByDu +=  $scope.totalTaskCostACostPerReByDu[i];
		for(var i=0;i<$scope.totalTaskCostPCostPerReByDu.length; i++)
			totalTaskCostPCostByDu +=  $scope.totalTaskCostPCostPerReByDu[i];
		$scope.labelTaskCostPie = ["Complete", "Future task"];
		$scope.optionTaskCostPie = { title: {display: true, text: "Cost Distribution"}, legend: { display: true }, 
				events: false,
				  animation: {
				    duration: 500,
				    easing: "easeOutQuart",
				    onComplete: function () {
				      var ctx = this.chart.ctx;
				      ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontFamily, 'normal', Chart.defaults.global.defaultFontFamily);
				      ctx.textAlign = 'center';
				      ctx.textBaseline = 'bottom';

				      this.data.datasets.forEach(function (dataset) {

				        for (var i = 0; i < dataset.data.length; i++) {
				          var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model,
				              total = dataset._meta[Object.keys(dataset._meta)[0]].total,
				              mid_radius = model.innerRadius + (model.outerRadius - model.innerRadius)/2,
				              start_angle = model.startAngle,
				              end_angle = model.endAngle,
				              mid_angle = start_angle + (end_angle - start_angle)/2;

				          var x = mid_radius * Math.cos(mid_angle);
				          var y = mid_radius * Math.sin(mid_angle);

				          ctx.fillStyle = '#fff';
				          if (i == 3){ // Darker text color for lighter background
				            ctx.fillStyle = '#444';
				          }
				          var percent = String(Math.round(dataset.data[i]/total*100)) + "%";
				          ctx.fillText(dataset.data[i] + "$", model.x + x, model.y + y);
				          // Display percent in another line, line break doesn't work for fillText
				          ctx.fillText(percent, model.x + x, model.y + y + 15);
				        }
				      });               
				    }
				  }

		}; // show legend
		//$scope.optionTaskCostPie = { legend: { display: true } }; // show legend
		$scope.colorTaskCostPie = ['#678BC7', '#E78C07'];
		$scope.dataTaskCostPie = [totalTaskCostACostByDu, totalTaskCostPCostByDu];
	}
	$scope.exportTaskCostPDF = function(fileName, canvasId){
		createPDF(fileName, canvasId);
	}
}]);




	

	