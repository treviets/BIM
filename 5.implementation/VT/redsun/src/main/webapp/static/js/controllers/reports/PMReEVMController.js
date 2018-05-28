app.controller('pmReEVMCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                          		'$window', 'pmReportService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService) {
	
	$scope.m = 0;
	$scope.y = new Date().getFullYear();
	$scope.initCostExpenses = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getCostExpenses();
			$translate.use($location.search().lang);
		}
	}
	
	$scope.gotoUrl = function(report) {
		$scope.reportPanel =  "/redsun/static/partials/" + report.file_name +".html";
	}	
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
	$scope.changeMonth = function(m){
		$scope.m = m;
	}
	$scope.changeYear = function(y){
		$scope.y = y;
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
	$scope.exportCostExpPDF = function(fileName, canvasId){
		createPDF(fileName, canvasId);
	}
}]);




	

	