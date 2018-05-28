app.factory('riskService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getRisks : function($scope) {
			var url = $rootScope.makeGetURL("/risk/"+$scope.name + "/" + $scope.itemsPerPage + "/" + $scope.pageNo);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getRisksAlready');
			});

			return true;
		}
		,
		getRisksOnePrject : function($scope) {
			var url = $rootScope.makeGetURL("/risk/listforoneproject/" +  $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getRisksOnePrjectAlready');
			});
	
			return true;
		}
		,
		getByIdRisk : function($scope) {
			var url = $rootScope.makeGetURL("/risk/getbyid/" + $scope.riskId);
			$http.get(url).then(function(response) {
				if (response.data) {
				response = angular.fromJson(response);
				$scope.risk = response.data.result.risks;
				}
			});
			return false;
		}
		,
		getTypes : function($scope) {
			var url = $rootScope.makeGetURL("/type/listall?typevalue=Risk");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTypesAlready');
			});

			return true;
		}
		,
		getLikelihoods : function($scope) {
			var url = $rootScope.makeGetURL("/likelihood/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getLikelihoodsAlready');
			});

			return true;
		}
		,
		getSeverities : function($scope) {
			var url = $rootScope.makeGetURL("/severities/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getSeveritiesAlready');
			});

			return true;
		}
		,
		getCriticalities : function($scope) {
			var url = $rootScope.makeGetURL("/criticalities/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getCriticalitiesAlready');
			});

			return true;
		}
		,
		getStatuses : function($scope) {
			var url = $rootScope.makeGetURL("/statuses/getbyscope?scopeValue=Risk");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getStatusAlready');
			});

			return true;
		}
		,
		getPriority : function($scope) {
			var url = $rootScope.makeGetURL("/priority/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getPriorityAlready');
			});

			return true;
		}
		
		// add
		,
		createRisk : function($scope){
			var url =$rootScope.makePostURL("/risk/add");
			$http.post(url, $scope.risk).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.risk.id = $scope.data.result.risks;
					$scope.risks.push($scope.risk);
				}
			});
			return true;
		}
		// Update.
		,
		updateRisk : function($scope) {
			var urlServer = $rootScope.makePostURL("/risk/update/" +  $scope.riskId);
			$http.put(urlServer, $scope.risk).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		
		// delete
		,
		deleteRisk : function($scope) {
			var urlServer = $rootScope.makePostURL("/risk/delete/" +  $scope.riskId);
			return $http.delete(urlServer).then(function(){
				$scope.risks.splice($scope.index, 1);
			});
		}

	};
});