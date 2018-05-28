app.controller('pmReportCtrl',['$scope', '$compile', '$rootScope', '$filter', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                       		'$window', 'projectService', function($scope, $compile, $rootScope, $filter, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, projectService) {

	// Navigate to url.
	$scope.modules = [
		{id:1, name:"Construction management", type:false},
		{id:2, name:"Design management", type:true}
	];
	$scope.categories = [
		{id:1, name:"Costs", module_id:1},
		{id:2, name:"Materials", module_id:1},
		{id:3, name:"Labors", module_id:1},
		{id:4, name:"Equipments", module_id:1}
	];
	$scope.reports = [
		{id:1, name:"Resource cost overview", file_name:"resource-cost-overview", controller_name:"pmReResourceCostCtrl", cat_id:1},
		{id:2, name:"Task cost per month", file_name:"task-cost-per-month", controller_name:"pmReResourceCostCtrl", cat_id:1},
		{id:3, name:"Task cost overview", file_name:"task-cost-overview", controller_name:"pmReResourceCostCtrl", cat_id:1},
		{id:4, name:"Expense", file_name:"expense-overview", controller_name:"pmReExpenseCtrl", cat_id:1},
		{id:5, name:"Material used", file_name:"material-sheet", controller_name:"pmReExpenseCtrl", cat_id:2},
		{id:6, name:"Provide materia by duration", file_name:"planning-material-sheet", controller_name:"pmReExpenseCtrl", cat_id:2},
		{id:7, name:"Earn value management", file_name:"earn-value", controller_name:"pmReEVMCtrl", cat_id:1},
		{id:8, name:"Labor In Project", file_name:"labor-sheet", controller_name:"pmReExpenseCtrl", cat_id:3},
		{id:9, name:"Time sheet In Project", file_name:"timesheet-sheet", controller_name:"pmReExpenseCtrl", cat_id:3},
		{id:10, name:"Equipment In Project", file_name:"equipment-sheet", controller_name:"pmReExpenseCtrl", cat_id:4}
	];
	
	$scope.getProjectType = function(projectType){
		if(projectType == 5)
			isDesignManagement = false;
		if(projectType == 4)
			isDesignManagement = true;
		$rootScope.isDesignManagement = isDesignManagement;
		$window.localStorage['isDesignManagement'] = isDesignManagement;
		$scope.getAllProject();
	}
	
	// get all project
	$scope.getAllProject = function() {
		projectService.getAllProject($scope);
	};
	$scope.$on('getAllAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
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
	$scope.getProject = function(project){
		$scope.project = project;
		$scope.projectId = project.id;
		$scope.fromDate = new Date($scope.project.stringStartDate);
		$scope.startDate = $filter('date')($scope.fromDate, "yyyy-MM-dd");
		$scope.toDate = new Date();
		$scope.endDate = $filter('date')(new Date($scope.toDate), "yyyy-MM-dd");
	}
	$scope.gotoUrl = function(report) {
		$scope.reportPanel =  "/redsun/static/partials/" + report.file_name +".html";
	}
}]);	