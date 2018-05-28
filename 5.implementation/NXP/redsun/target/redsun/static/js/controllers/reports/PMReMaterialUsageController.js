app.controller('pmReMaterialUsageCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                                		'$window', 'pmReportService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReportService) {

	var tmpTimeSheets = [];
	$scope.workingUi = [];
	$scope.m = new Date().getMonth()+1;;
	$scope.y = new Date().getFullYear();
	dayInMonth = function(month, year){
		$scope.days = [];
		var numOfDays = new Date(year, month, 0).getDate();
		for(var i=1; i<=numOfDays; i++)
			$scope.days.push(i);
	}
	$scope.initLanguage = function(){
		$translate.use($location.search().lang);
	}
	$scope.initMaterialUsage = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getMaterialUsage();
		}
	}
	$scope.initMaterialprovided = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getMaterialProvided();
		}
	}
	$scope.initLaborInProject = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getLaborInProject();
		}
	}
	$scope.initEquipmentInProject = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getEquipmentInProject();
		}
	}
	$scope.initTimeSheetInProject = function(){
		if($scope.projectId==undefined)
			alert($translate.instant('report_pm_cost_msg_project'));
		else{
			$scope.getTimeSheetInProject();
		}
	}
	$scope.gotoUrl = function(report) {
		$scope.reportPanel =  "/redsun/static/partials/" + report.file_name +".html";
	}
	$scope.changeToDate = function(toDate) {
	    $scope.endDate = $filter('date')(new Date(toDate), "yyyy-MM-dd");
	}
	$scope.changeMonth = function(m){
		$scope.m = m;
		dayInMonth($scope.m, $scope.y);
	}
	$scope.changeYear = function(y){
		$scope.y = y;
		dayInMonth($scope.m, $scope.y);
	}
	$scope.changeFromDate = function(fromDate) {
	    $scope.startDate = $filter('date')(new Date(fromDate), "yyyy-MM-dd");
	}
	$scope.getMaterialUsage = function(){
		pmReportService.getMaterialUsage($scope);
	}
	$scope.$on('getMaterialUsageAlready', function() {
		if ($scope.data) {
			$scope.materialUsage = $scope.data.result.listMaterials;
		}
	});	
	$scope.getMaterialProvided = function(){
		pmReportService.getMaterialProvided($scope);
	}
	$scope.$on('getMaterialProvidedAlready', function() {
		if ($scope.data) {
			$scope.materials = $scope.data.result.listMaterials;
		}
	});
	$scope.getLaborInProject = function(){
		pmReportService.getLaborInProject($scope);
	}
	$scope.$on('getLaborAlready', function() {
		if ($scope.data) {
			$scope.labors = $scope.data.result.listLabors;
		}
	});
	$scope.getEquipmentInProject = function(){
		pmReportService.getEquipmentInProject($scope);
	}
	$scope.$on('getEquipmentAlready', function() {
		if ($scope.data) {
			$scope.equipments = $scope.data.result.listEquipments;
		}
	});
	$scope.getTimeSheetInProject = function(){
		pmReportService.getTimeSheetInProject($scope);
	}
	$scope.$on('getTimeSheetAlready', function() {
		$scope.timeSheetUi = [];
		if ($scope.data) {
			$scope.timesheets = $scope.data.result.listTimeSheet;
			if($scope.timesheets.length>0){
				tmpTimeSheets = angular.copy($scope.timesheets);
				var dictTimeSheets = [];
				var sameTimeSheets = [];
				dictTimeSheets.push(angular.copy(tmpTimeSheets[0]));
				 remove(tmpTimeSheets[0]);   
				while(tmpTimeSheets.length>0){
					for(m=0; m<dictTimeSheets.length; m++)
						if(dictTimeSheets[m].code == tmpTimeSheets[0].code){
							sameTimeSheets.push(tmpTimeSheets[0]);
							break;
						}
						else if(m==dictTimeSheets.length-1){
								dictTimeSheets.push(angular.copy(tmpTimeSheets[0]));
								break;
						}
					remove(tmpTimeSheets[0]);
				}
				for(var i=0; i<dictTimeSheets.length; i++){
					var timeSheet = {code:'', name:'', overtime:0, startDate:'', duration: 0, working:[{day:0, effort:0}]};
					timeSheet.code = dictTimeSheets[i].code;
					timeSheet.name = dictTimeSheets[i].resourceName;
					timeSheet.overtime = dictTimeSheets[i].salary;
					timeSheet.startDate = dictTimeSheets[i].startDate;
					timeSheet.duration = dictTimeSheets[i].totalActuallyTime;
					$scope.timeSheetUi.push(timeSheet);
				}
				for(var i=0; i<$scope.timeSheetUi.length; i++){
					for(var x=0; x<$scope.days.length; x++){
						var working = {};
						if(x==0)
							$scope.timeSheetUi[i].working[0].day = $scope.days[x];
						else{
							working.day = $scope.days[x];
							working.effort = 0;
							$scope.timeSheetUi[i].working.push(working);
						}
					}
					
				}
				for(var i=0; i<$scope.timeSheetUi.length; i++){
					var arrWorking = $scope.timeSheetUi[i].working;
					var d = ($scope.timeSheetUi[i].startDate).substring(8, 10);
					for(m=0; m<arrWorking.length; m++)
						if(parseInt(d) == arrWorking[m].day){
							arrWorking[m].effort = $scope.timeSheetUi[i].duration;
							break;
						}
				}
				for(var i=0; i<$scope.timeSheetUi.length; i++){
					var ov = $scope.timeSheetUi[i].overtime;
					for(j=0; j<sameTimeSheets.length; j++)
						if($scope.timeSheetUi[i].code == sameTimeSheets[j].code){
							ov = ov + sameTimeSheets[j].salary
							var d = (sameTimeSheets[j].startDate).substring(8, 10);
							var arrWorking = $scope.timeSheetUi[i].working;
							for(m=0; m<arrWorking.length; m++)
								if(parseInt(d) == arrWorking[m].day)
									$scope.timeSheetUi[i].working[m].effort = $scope.timeSheetUi[i].working[m].effort +  sameTimeSheets[j].totalActuallyTime;
						}
					$scope.timeSheetUi[i].overtime = ov;	
				}
			}
		}
	});	
	
	remove=function(item){ 
	      var index=tmpTimeSheets.indexOf(item);
	      tmpTimeSheets.splice(index,1);     
	}
	$scope.exportMaterialUsageExcel = function(fileName, canvasId){
		createExcel(fileName, canvasId);
	}
	dayInMonth($scope.m, $scope.y);
}]);




	

	