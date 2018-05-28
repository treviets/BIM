app.factory('priorityService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getPriorities : function($scope, $rootScope, $http, pageNo) {
			var url = $rootScope.makeGetURL("/priority/" + $scope.name + "/" + $scope.itemsPerPage + "/"+ pageNo);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getPrioritiesAlready');
			});

			return true;
		}

		,
		getById : function($scope, $rootScope, $http) {
			var url = $rootScope.makeGetURL("/priority/getbyid/" + $scope.priorityId);
			$http.get(url).then(function(response) {
				response = angular.fromJson(response);
				$scope.priority = response.data.result.priorities;
				$scope.$broadcast('getPriorityDetailAlready');
			})
			return false;
		}
		// Update.
		,
		update : function($scope, $rootScope, $http) {
			var urlServer = $rootScope.makePostURL("/priority/update/" +  $scope.priorityId);
			$http.put(urlServer, $scope.priority).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		,
		delete : function($scope, $rootScope, $http) {
			var urlServer = $rootScope.makePostURL("/priority/delete/" +  $scope.priorityId);
			return $http.delete(urlServer);
		}
		

	};
});