app.factory('projectBaselineService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getProjectLaborBaseline : function($scope) {
			var url = $rootScope.makeGetURL("/projectbaseline/list/labor/" + $scope.projectId + '/' + $scope.baselineId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectLaborBLAlready');
			});
			return true;
		},
		createProjectLaborBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/labor/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectLaborBLAlready');
				}
			});
			return true;
		},
		createProjectMaterialBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/material/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectMaterialBLAlready');
				}
			});
			return true;
		},
		createProjectEquipmentBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/equipment/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectEquipmentBLAlready');
				}
			});
			return true;
		},
		createProjectTaskBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/task/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectTaskBLAlready');
				}
			});
			return true;
		},
		createProjectTaskResourceBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/taskresource/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectTaskResourceBLAlready');
				}
			});
			return true;
		},
		createProjectTaskMaterialBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/taskmaterial/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectTaskMaterialBLAlready');
				}
			});
			return true;
		},
		createProjectTaskEquipmentBaseline : function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/insertbatch/taskequipment/" + $scope.projectId);
			$http.post(url, $scope.baseline).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('addProjectTaskEquipmentBLAlready');
				}
			});
			return true;
		},
		getBaseLineByType: function($scope){
			var url = $rootScope.makePostURL("/projectbaseline/list/baseline/" + $scope.projectId + '/' + $scope.type);
			$http.get(url).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('getProjectBaseLineTypeAlready');
				}
			});
			return true;
		}
	}
});