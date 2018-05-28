

/**
 * Controller for ${entity.name}
 */


app.factory('resourceService', function($http, $rootScope, $window) {
	return {

		createResource : function($scope){
			var urlServer = $rootScope.makePostURL("/hr/add");
			$http.post(urlServer, $scope.resource).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$window.alert('Successful created!');
					$window.location.href = $rootScope.makeGetURL("/hr");
				}
			});
			return true;
		}
		
		,
		countResource : function($scope) {
			var url = $rootScope.makeGetURL("/hr/count");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('countAlready');
			});
	
			return true;
		}
		,
		getClientId : function($scope) {
			var url = $rootScope.makeGetURL("/hr/getclientid");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getClientIdAlready');
			});
	
			return true;
		}
		
		,
		getResources : function($scope) {
			var url = $rootScope.makeGetURL("/hr/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourcesAlready');
			});
	
			return true;
		}
		,
		getResourceAllType : function($scope) {
			var url = $rootScope.makeGetURL("/hr/list-all-type/");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourceAllTypeAlready');
			});
	
			return true;
		}
		,
		getDeletedResources : function($scope) {
			var url = $rootScope.makeGetURL("/hr/deleted/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDeletedResourcesAlready');
			});
	
			return true;
		}
		,
		restoreResource : function($scope) {
			var url = $rootScope.makeGetURL("/hr/restore/"+$scope.resourceId);
			$http.get(url).then(function(response) {
				$scope.$broadcast('restoreResourcesAlready');
			});
	
			return true;
		}
		,
		getResourcesExist : function($scope) {
			var url = $rootScope.makeGetURL("/hr/checkexist/"+ $scope.resourceId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourcesExistAlready');
			});
	
			return true;
		}
		//get roles
		,
		getRoles : function($scope) {
			var url = $rootScope.makeGetURL("/role/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getRolesAlready');
			});
	
			return true;
		}
		// Update.
		,
		updateResource : function($scope) {
			var urlServer = $rootScope.makePostURL("/hr/update/" + $scope.resourceId);
			$http.put(urlServer, $scope.resource).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$window.alert('Successful executed!');
					$window.location.href = $rootScope.makeGetURL("/hr");
				}
			});
			return true;
		}
		
		// Delete.
		, deleteResource: function($scope) {
			var urlServer = $rootScope.makePostURL("/hr/delete/" + $scope.resourceId);
			return $http.delete(urlServer).then(function(response) {
				$scope.$broadcast('removedResourceAlready');
			});
		}
		
		// get by id
		,
		getByIdResource : function($scope) {
			var url = $rootScope.makeGetURL("/hr/getbyid/" + $scope.resourceId);
			$http.get(url).then(function(response) {
				if (response.data) {
				response = angular.fromJson(response);
				$scope.resource = response.data.result.resources;
				}
			});
			return false;
		}
		, getsForPageResource: function($scope) {
			var url = $rootScope.makeGetURL("/hr/list/page/" + $scope.itemsPerPage + "/" + $scope.pageNo);
			return $http.get(url).success(
				function(response, status, headers, config) {
					response = angular.fromJson(response);
					if (response && response.status === 1 && response.result.resources) {
						$scope.resources = response.result.resources;
						$scope.totalCount = response.result.resources[0].totalCount;
					} else {
						$window.alert('failed');
					}
				}
			).error(
				function(response, status, headers, config) {
					$window.alert('error');
				}
			);
		}
		// List for exterior
		, getsForPageExterior: function($scope) {
			var url = $rootScope.makeGetURL("/hr/exterior/page/" + $scope.itemsPerPage + "/" + $scope.pageNo);
			return $http.get(url)
			.success(
				function(response, status, headers, config) {
					response = angular.fromJson(response);
					if (response && response.status === 1 && response.result.resources) {
						if(response.result.resources){
							$scope.resources = response.result.resources;
							if(response.result.resources.length > 0){
								$scope.totalCount = response.result.resources[0].totalCount;
							}
							
						}
						
					} else {
						$window.alert('failed');
					}
				}
			).error(
				function(response, status, headers, config) {
					$window.alert('error');
				}
			);
		}
		
	}
});