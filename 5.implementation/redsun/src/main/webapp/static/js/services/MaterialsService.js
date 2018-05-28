

/**
 * Controller for materials
 */


app.factory('materialService', function($http, $rootScope) {
	return {

		
	//-------------------------insert---------------------------
		getUnits : function($scope) {
			var url = $rootScope.makeGetURL("/unit/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getUnitsAlready');
			});

			return true;
		}
		,
		checkExistMaterial : function($scope) {
			var url = $rootScope.makeGetURL("/material/checkexist/" + $scope.materialId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('checkExistMaterialAlready');
			});

			return true;
		}
		,
		//add
		createMaterial : function($scope){
			var urlServer = $rootScope.makePostURL("/material/add");
			$http.post(urlServer, $scope.material).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		// getmaterialForPag.
		, getMaterialForPage: function(itemsPerPage, pageNo, material) {
			var url = $rootScope.makeGetURL("/material/list/page/" + itemsPerPage + "/" + pageNo);
			return $http.get(url);
		}
		,
		getMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/material/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMaterialsAlready');
			});
	
			return true;
		}
		,
		countMaterial : function($scope) {
			var url = $rootScope.makeGetURL("/material/count");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('countMaterialAlready');
			});
	
			return true;
		}
		
		,
		getClientId : function($scope) {
			var url = $rootScope.makeGetURL("/material/getclientid");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getClientIdAlready');
			});
	
			return true;
		}
		// get by id
		,
		getByIdMaterial : function($scope) {
			var url = $rootScope.makeGetURL("/material/getbyid/" + $scope.materialId);
			$http.get(url).then(function(response) {
				if (response.data) {
				response = angular.fromJson(response);
				$scope.material = response.data.result.materials;
				}
			});
			return false;
		}
		// Update.
		,
		updateMaterial : function($scope) {
			var urlServer = $rootScope.makePostURL("/material/update/" + $scope.materialId);
			$http.put(urlServer, $scope.material).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		// Delete.
		, deleteMaterial: function(resourceId) {
			var urlServer = $rootScope.makePostURL("/material/delete/" + resourceId);
			return $http.delete(urlServer);
		}
		
	}
});