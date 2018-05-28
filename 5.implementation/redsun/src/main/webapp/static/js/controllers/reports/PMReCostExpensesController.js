app.controller('pmReCostExpCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                          		'$window', 'pmReportService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService) {
	
	$scope.m = 0;
	$scope.y = new Date().getFullYear();
	$scope.initLanguage = function(){
		$translate.use($location.search().lang);
	}
	$scope.initCostExpenses = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getCostExpenses();
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
			drawCostExpMultiBarChart();
		}
	});	
	$scope.changeMonth = function(m){
		$scope.m = m;
	}
	$scope.changeYear = function(y){
		$scope.y = y;
	}
	//task cost chart
	var drawCostExpMultiBarChart = function(){
		$scope.labelCostExpMultiBar = [];
		$scope.totalCostPCostExp = [];
		$scope.totalCostACostExp = [];
		for(var i=0; i<$scope.CostExpenses.length; i++){
			$scope.labelCostExpMultiBar.push($scope.CostExpenses[i].monthly);
			$scope.totalCostPCostExp.push($scope.CostExpenses[i].totalPlanningCost);
			$scope.totalCostACostExp.push($scope.CostExpenses[i].totalActuallyCost);
		}
		$scope.seriesCostExpMultiBar = ['Actual', 'Planning'];
		$scope.optiopCostExpMultiBar = { title: {display: true, text: "Cost Status"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		//$scope.optionTaskCostMultiBar = { legend: { display: true } }; // show legend
		$scope.colorCostExpMultiBar = ['#678BC7', '#E78C07'];
		$scope.dataCostExpMultiBar = [$scope.totalCostACostExp, $scope.totalCostPCostExp];
	}
	$scope.exportCostExpPDF = function(fileName, canvasId){
		createPDF(fileName, canvasId);
	}
}]);




	

	