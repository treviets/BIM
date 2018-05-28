app.controller('pmReportCtrl',['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                       		'$window', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window) {

	// Navigate to url.
	$scope.modules = [
		{id:0, name:"---Select modules---"},
		{id:1, name:"HR management"},
		{id:2, name:"Project management"}
	];
	$scope.categories = [
		{id:1, name:"Cost", module_id:2},
		{id:2, name:"Planning", module_id:2},
		{id:3, name:"Costs", module_id:2}
	];
	$scope.reports = [
		{id:1, name:"Resource cost overview", file_name:"resource-cost-overview", cat_id:1},
		{id:2, name:"work monthly", file_name:"member-area", cat_id:1},
		{id:3, name:"work yearly", file_name:"risk-area", cat_id:1}
	];
	$scope.module = $scope.modules[0];
	$scope.getCategory = function(moduleId) {
		$scope.categoriesByModule = [];
		for(var i=0; i<$scope.categories.length; i++)
			if($scope.categories[i].module_id==moduleId)
				$scope.categoriesByModule.push($scope.categories[i]);
	}
	$scope.getReports = function(cateId) {
		$scope.reportsByCategory = [];
		for(var i=0; i<$scope.reports.length; i++)
			if($scope.reports[i].cat_id==cateId)
				$scope.reportsByCategory.push($scope.reports[i]);
	}
	$scope.gotoUrl = function(url) {
		$scope.reportPanel =  "/redsun/static/partials/" + url +".html";
	}
	
}]);




	

	