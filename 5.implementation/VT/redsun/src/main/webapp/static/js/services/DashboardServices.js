app.factory('dashboardService', function() {
	// Might use a resource here that returns a JSON array
			return {
				getAllCategories : function($scope, $rootScope, $http) {
					var url = $rootScope.makeGetURL("/category/list");
					$http.get(url).then(function(response) {
						if(response.data){
							$scope.categories = angular.fromJson(response.data);
						}
						$scope.$broadcast('getCategoriesAlready');
					});

					return true;
				},
				getPieData : function($scope, $rootScope, $http) {
					var url = $rootScope.makeGetURL("/category/1/");
					$http.get(url).then(function(response) {
						$scope.chartData = null;
						if(response.data){
							$scope.chartData = angular.fromJson(response.data).result;
						}
						
						$scope.$broadcast('getPieChartDataAlready');
					});
					
					return true;
				}
			};
	});