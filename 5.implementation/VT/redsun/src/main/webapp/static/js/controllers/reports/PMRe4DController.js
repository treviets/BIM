var pmRe4DCtrlScope = {}; 
app.controller('pmRe4DCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'pmReportService', 'projectService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService, projectService) {
			
	// Keep scope for global.
	pmRe4DCtrlScope = $scope;

	$scope.m = 0;
	$scope.y = new Date().getFullYear();
	$scope.totalTaskCostPCostPerReByDu = [];
	$scope.totalTaskCostACostPerReByDu = [];
	
	$scope.initParam = function(projectId){
		$scope.projectId = projectId;
		getProjectById($scope);
	}
	
	$scope.initTaskCost = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getTaskCost();
			$translate.use($location.search().lang);
		}
	}
		
	$scope.getTaskCost = function(){
		pmReportService.getTaskCost($scope);
	}
	$scope.$on('getTaskCostAlready', function() {
		if ($scope.data) {
			$scope.taskCost = $scope.data.result.taskCost;
			drawTaskCostPieChar();
		}
	});	
	
	$scope.getCostExpenses = function(){
		pmReportService.getCostExpenses($scope);
	}
	$scope.$on('getCostExpensesAlready', function() {
		if ($scope.data) {
			$scope.CostExpenses = $scope.data.result.listCostExpenses;
			drawCostExpMultiLineChart();
			drawVarianceMultiLineChart();
			drawIndexMultiLineChart();
		}
	});	
	
	// get project by id
	getProjectById = function() {
		projectService.getByIdProject($scope);
	};
	$scope.$on('getProjectAlready', function() {
		if ($scope.project) {
			$scope.startDate = $filter('date')(new Date($scope.project.stringStartDate), "yyyy-MM-dd");
			$scope.endDate = $filter('date')(new Date(), "yyyy-MM-dd");
			$scope.initTaskCost();
			$scope.getCostExpenses();
		}
	});	
	
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
	
	//task cost chart
	var drawCostExpMultiLineChart = function(){
		$scope.labelCostExpMultiLine = [];
		$scope.totalCostPCostExp = [];
		$scope.totalCostACostExp = [];
		$scope.bcwp = [];
		for(var i=0; i<$scope.CostExpenses.length; i++){
			$scope.labelCostExpMultiLine.push($scope.CostExpenses[i].monthly);
			$scope.totalCostPCostExp.push($scope.CostExpenses[i].totalPlanningCost);
			$scope.totalCostACostExp.push($scope.CostExpenses[i].totalActuallyCost);
			$scope.bcwp.push($scope.CostExpenses[i].bcwp);
		}
		$scope.seriesCostExpMultiLine = ['ACWP', 'BCWS', 'BCWP'];
		$scope.optiopCostExpMultiLine = { title: {display: true, text: "Earned Value Over Time"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		//$scope.optionTaskCostMultiBar = { legend: { display: true } }; // show legend
		$scope.colorCostExpMultiLine = ['#678BC7', '#E78C07', '#E13C01'];
		$scope.dataCostExpMultiLine = [$scope.totalCostACostExp, $scope.totalCostPCostExp, $scope.bcwp];
	}
	//CV, SV chart
	var drawVarianceMultiLineChart = function(){
		$scope.labelVarianceLine = [];
		$scope.cv = [];
		$scope.sv = [];
		for(var i=0; i<$scope.CostExpenses.length; i++){
			$scope.labelVarianceLine.push($scope.CostExpenses[i].monthly);
			$scope.cv.push($scope.CostExpenses[i].cv);
			$scope.sv.push($scope.CostExpenses[i].sv);
		}
		$scope.seriesVarianceMultiLine = ['CV', 'SV'];
		$scope.optiopVarianceMultiLine = { title: {display: true, text: "Variance Over Time"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		//$scope.optionTaskCostMultiBar = { legend: { display: true } }; // show legend
		$scope.colorVarianceMultiLine = ['#678BC7', '#E78C07'];
		$scope.dataVarianceMultiLine = [$scope.cv, $scope.sv];
	}
	//CPI, SPI chart
	var drawIndexMultiLineChart = function(){
		$scope.labelIndexLine = [];
		$scope.cpi = [];
		$scope.spi = [];
		for(var i=0; i<$scope.CostExpenses.length; i++){
			$scope.labelIndexLine.push($scope.CostExpenses[i].monthly);
			$scope.cpi.push($scope.CostExpenses[i].cpi);
			$scope.spi.push($scope.CostExpenses[i].spi);
		}
		$scope.seriesIndexMultiLine = ['CPI', 'SPI'];
		$scope.optiopIndexMultiLine = { title: {display: true, text: "Indices Over Time"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		//$scope.optionTaskCostMultiBar = { legend: { display: true } }; // show legend
		$scope.colorIndexMultiLine = ['#678BC7', '#E78C07'];
		$scope.dataIndexMultiLine = [$scope.cv, $scope.sv];
	}
}]);