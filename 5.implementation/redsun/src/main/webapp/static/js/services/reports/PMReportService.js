app.factory('pmReportService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getResourceCost : function($scope) {
			var url = $rootScope.makeGetURL("/planningelement/list/"
					+ $scope.projectId + "/" + $scope.startDate + "/"
					+ $scope.endDate);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourceCostAlready');
			});
			return true;
		},
		getTaskCost : function($scope) {
			var url = $rootScope.makeGetURL("/planningelement/list/taskcost/"
					+ $scope.projectId + "/" + $scope.startDate + "/"
					+ $scope.endDate);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTaskCostAlready');
			});
			return true;
		},
		getCostExpenses : function($scope) {
			var url = $rootScope.makeGetURL("/planningelement/list/costexpenses/"
					+ $scope.projectId + "/" + $scope.m + "/"
					+ $scope.y);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getCostExpensesAlready');
			});
			return true;
		},
		getMaterialUsage : function($scope){
			var url = $rootScope.makeGetURL("/planningelement/list/materialusage/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMaterialUsageAlready');
			});
			return true;
		},
		getMaterialProvided : function($scope){
			var url = $rootScope.makeGetURL("/planningelement/list/materialprovided/"
					+ $scope.projectId + "/" + $scope.startDate + "/"
					+ $scope.endDate);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMaterialProvidedAlready');
			});
			return true;
		},
		getLaborInProject : function($scope){
			var url = $rootScope.makeGetURL("/planningelement/list/labor/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getLaborAlready');
			});
			return true;
		},
		getEquipmentInProject : function($scope){
			var url = $rootScope.makeGetURL("/planningelement/list/equipment/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getEquipmentAlready');
			});
			return true;
		},
		getTimeSheetInProject : function($scope){
			var url = $rootScope.makeGetURL("/planningelement/list/timesheet/"
					+ $scope.projectId + "/" + $scope.m + "/"
					+ $scope.y);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTimeSheetAlready');
			});
			return true;
		}
	};
});