app.controller('commonCtrl',['$scope', '$rootScope', '$window', '$location', 'commonService',function($scope, $rootScope, $window, $location, commonService) {

	
	$scope.commonInit = function(){
		
		$rootScope.MAIN_DOMAIN = '/redsun';
		$rootScope.isFullscreen = false;
		$rootScope.CSRF_HEADER = $('meta[name="_csrf_header"]').attr('content');
		$rootScope.CSRF_TOKEN = $('meta[name="_csrf_token"]').attr('content');
		$rootScope.isDesignManagement = false;
		if($window.localStorage['isDesignManagement']){
			$rootScope.isDesignManagement = $window.localStorage['isDesignManagement'] === 'true';
		}
		var menuSelected = $location.path();
		
		commonService.getPermission();
		
	}
	
	$scope.getProjectIncharging = function() {
		commonService.getProjectIncharging();
	};
	
	$scope.fullscreen = function(){
		$scope.isFullscreen = true;
		var menuList = angular.element(document.querySelector( '#menuList'));
		$(menuList).hide();
		var mainBody = angular.element(document.querySelector( '#mainBody'));
		mainBody.removeClass("col-lg-10");
		mainBody.removeClass("col-md-10");
		mainBody.addClass("col-lg-12");
		mainBody.addClass("col-md-12");
		var ganttChart = angular.element(document.querySelector( '#ganttChart'));
		ganttChart.removeClass("col-lg-12");
		ganttChart.addClass("col-lg-11");
			
	}
	
	$scope.exitFullscreen = function(){
		$scope.isFullscreen = false;
		var menuList = angular.element(document.querySelector('#menuList'));
		$(menuList).show();
		var mainBody = angular.element(document.querySelector('#mainBody'));
		mainBody.removeClass("col-lg-12");
		mainBody.removeClass("col-md-12");
		mainBody.addClass("col-lg-10");
		mainBody.addClass("col-md-10");
		var ganttChart = angular.element(document.querySelector( '#ganttChart'));
		ganttChart.removeClass("col-lg-11");
		ganttChart.addClass("col-lg-12");
	}

	$scope.detailFullscreen = function(seletedObjectId){
		$scope.fullscreen();
		$scope.isDetailFullscreen = true;
		//hide all sub areas first
		$(".detail-sub-area").hide();
		//just show selected area
		$("#"+seletedObjectId).show();
		var selectedAread = angular.element(document.querySelector('#'+seletedObjectId));
		selectedAread.addClass("full-width");
		
	}
	
	$scope.exitDetailFullscreen = function(seletedObjectId){
		$scope.exitFullscreen();
		$scope.isDetailFullscreen = false;
		//show all sub areas first
		$(".detail-sub-area").show();
		var selectedAread = angular.element(document.querySelector('#'+seletedObjectId));
		selectedAread.removeClass("full-width");

	}
	
	$rootScope.makePostURL = function(path){
		var fullPostURL = $rootScope.MAIN_DOMAIN + path + "?_csrf_header=" + $rootScope.CSRF_HEADER + "&_csrf=" + $rootScope.CSRF_TOKEN;
		return fullPostURL;
	}
	
	$scope.checkPermission = function(permissionName){
		var systemPermissions = angular.fromJson($window.localStorage['systemPermissions']);
		var log = [];
		var hasPermission = false;
		angular.forEach(systemPermissions, function(value, key) {
		  if(value.authority === permissionName){
			  hasPermission = true;
		  }
		}, log);
		
		return hasPermission;
		
	}
	
	
	$rootScope.makeGetURL = function(path){
		var fullGetURL = $rootScope.MAIN_DOMAIN + path;
		return fullGetURL;
	}

}]);




	

	