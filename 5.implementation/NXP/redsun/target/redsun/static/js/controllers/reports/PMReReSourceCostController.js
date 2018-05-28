app.controller('pmReResourceCostCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                               		'$window', 'pmReportService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService) {

	$scope.initLanguage = function(){
		$translate.use($location.search().lang);
	}
	$scope.initRSC = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
			//alert('Please, choose a project');
		else{
			$scope.getResourceCost();
		}
	}
	
	$scope.changeToDate = function(toDate) {
	    $scope.endDate = $filter('date')(new Date(toDate), "yyyy-MM-dd");
	}
	$scope.changeFromDate = function(fromDate) {
	    $scope.startDate = $filter('date')(new Date(fromDate), "yyyy-MM-dd");
	}
	$scope.getResourceCost = function(){
		pmReportService.getResourceCost($scope);
	}
	$scope.$on('getResourceCostAlready', function() {
		if ($scope.data) {
			$scope.resourceCost = $scope.data.result.resourceCost;
			drawRSCMulBarChart();
			drawRSCPieChar();
		}
	});	
	//resource cost chart
	var totalARSCByDu = [];
	var drawRSCMulBarChart = function(){
		$scope.labelRSCMulBar = [];
		var totalPRSCByDu = [];
		totalARSCByDu = [];
		for(var i=0; i<$scope.resourceCost.length; i++){
			$scope.labelRSCMulBar.push($scope.resourceCost[i].resourceName);
			totalPRSCByDu.push($scope.resourceCost[i].totalPlanningCost);
			totalARSCByDu.push($scope.resourceCost[i].totalActuallyCost);
		}
		$scope.seriesRSCMulBar = ['Actual', 'Planning'];
		$scope.optionRSCMulBar = { title: {display: true, text: "Cost Overview"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		$scope.colorRSCMulBar = ['#678BC7', '#E78C07'];
		$scope.dataRSCMulBar = [totalARSCByDu, totalPRSCByDu];
	}
	
	var drawRSCPieChar = function(){
		var totalAAllRSCByDu = 0;
		var totalPCostByProject = $scope.project.amount;
		for(var i=0;i<totalARSCByDu.length; i++)
			totalAAllRSCByDu +=  totalARSCByDu[i];
		$scope.labelRSCPie = ["Complete", "Future task"];
		
		$scope.optionRSCPie = { title: {display: true, text: "Cost Distribution"}, legend: { display: true }, 
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
		//$scope.optionPie = { title: {display: true, text: "Cost Distribution"}, legend: { display: true }}; // show legend
		$scope.colorRSCPie = ['#678BC7', '#E78C07'];
		$scope.dataRSCPie = [totalAAllRSCByDu, totalPCostByProject];
	}
	$scope.exportResourceCostPDF = function(fileName, canvasId){
		createPDF(fileName, canvasId);
	}
}]);




	

	