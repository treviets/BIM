app.controller('projectBaseLineCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'projectBaselineService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, projectBaselineService) {

	var msgSaveSuccess = '';
	var msgSaveFail = '';
	initLanguage = function(){
		var lan = $location.search().lang;
		msgSaveSuccess = $translate.instant('record.save.sucess');
		msgSaveFail = $translate.instant('record.save.fail');
		if(lan!=undefined)
			$translate.use(lan);
		else $translate.use('en');
		$scope.frmDirty = false;
	}
	
	$scope.baselineType = [
		{id:1, name:"entire project"},
		{id:2, name:"labor"},
		{id:3, name:"equipment"},
		{id:4, name:"material"},
		{id:5, name:"task"},
		{id:6, name:"task resource"},
		{id:7, name:"task equipment"},
		{id:8, name:"task material"},
	];
	$scope.baseline = {};
	$scope.createProjectBaseline = function(){
		if($scope.frmBL.$invalid) {
			$scope.frmBL.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.baseline.name = $scope.name;
		$scope.baseline.baseline_type = $scope.type;
		$scope.baseline.description = $scope.description;
		if($scope.type == 2)
			projectBaselineService.createProjectLaborBaseline($scope);
		if($scope.type == 3)
			projectBaselineService.createProjectEquipmentBaseline($scope);
		if($scope.type == 4)
			projectBaselineService.createProjectMaterialBaseline($scope);
		if($scope.type == 5)
			projectBaselineService.createProjectTaskBaseline($scope);
		if($scope.type == 6)
			projectBaselineService.createProjectTaskResourceBaseline($scope);
		if($scope.type == 7)
			projectBaselineService.createProjectTaskEquipmentBaseline($scope);
		if($scope.type == 8)
			projectBaselineService.createProjectTaskMaterialBaseline($scope);
	}
	$scope.$on('addProjectLaborBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectMaterialBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectEquipmentBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectTaskBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectTaskResourceBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectTaskMaterialBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.$on('addProjectTaskEquipmentBLAlready', function() {
		if ($scope.data.result.id===1) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
		}
		else
			$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
	});
	$scope.showMessage = function(message, cssName, autoHide) {
		$scope.alertMessage = message;
		$('#alertMessageBL').addClass(cssName);
		$('#alertMessageBL').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessageBL').slideUp(500, function() {
						$('#alertMessageBL').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}
	initLanguage();
}]);


	

	