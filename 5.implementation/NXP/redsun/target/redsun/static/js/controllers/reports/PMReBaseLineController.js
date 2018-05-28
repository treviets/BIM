app.controller('pmReBaselineCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                                		'$window', 'projectBaselineService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, projectBaselineService) {

	
	$scope.initLanguage = function(){
		$translate.use($location.search().lang);
	}
	$scope.initBaseLine = function(){
		$translate.use($location.search().lang);
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			getBaseLineByType();
		}
	}
	$scope.getProjectLaborBaseline = function(){
		projectBaselineService.getProjectLaborBaseline($scope);
	}
	$scope.$on('getProjectLaborBLAlready', function() {
		if ($scope.data) {
			$scope.labors = $scope.data.result.projectRessources;
		}
	});
	$scope.getBaseLineId = function(baseline){
		 $scope.baselineId = baseline.id;
	}
	/*
	getBaseLineByType = function(){
		$scope.type = 2;
		projectBaselineService.getBaseLineByType($scope);
	}
	$scope.$on('getProjectBaseLineTypeAlready', function() {
		if ($scope.data) {
			$scope.baselines = $scope.data.result.listBaselines;
		}
	});
	*/
	$scope.exportMaterialUsageExcel = function(fileName, canvasId){
		createExcel(fileName, canvasId);
	}
	$scope.initLanguage();
}]);




	

	