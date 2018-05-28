app.factory('customerService', function() {
	// Might use a resource here that returns a JSON array
			return {
				getCustomers : function($scope, $rootScope, $http, pageNo) {
					var url = $rootScope.makeGetURL("/customer/"+$scope.code+"/"+$scope.name+"/"+$scope.itemsPerPage+"/"+pageNo);
					$http.get(url).then(function(response) {
						if(response.data){
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('checkPermissionAlready');
					});

					return true;
				}
			};
	});