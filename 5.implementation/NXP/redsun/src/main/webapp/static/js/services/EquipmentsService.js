

/**
 * Controller for Equipments
 */


app.factory('equipmentService', function($http, $rootScope) {
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
		checkExistEquipment : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/checkexist/" + $scope.equipmentId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('checkExistEquipmentAlready');
			});

			return true;
		}
		,
		//add
		createEquipment : function($scope){
			var urlServer = $rootScope.makePostURL("/equipment/add");
			$http.post(urlServer, $scope.equipment).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('saveEquipmentAlready');
				}
			});
			return true;
		}
		// getEquipmentForPag.
		, getEquipmentForPage: function(itemsPerPage, pageNo, equipment) {
			var url = $rootScope.makeGetURL("/equipment/list/page/" + itemsPerPage + "/" + pageNo);
			return $http.get(url);
		}
		,
		getEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getEquipmentsAlready');
			});
	
			return true;
		}
		,
		countEquipment : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/count");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('countEquipmentAlready');
			});
	
			return true;
		}
		
		,
		getClientId : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/getclientid");
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
		getByIdEquipment : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/getbyid/" + $scope.equipmentId);
			$http.get(url).then(function(response) {
				if (response.data) {
				response = angular.fromJson(response);
				$scope.equipment = response.data.result.equipments;
				}
			});
			return false;
		}
		// Update.
		,
		updateEquipment : function($scope) {
			var urlServer = $rootScope.makePostURL("/equipment/update/" + $scope.equipmentId);
			$http.put(urlServer, $scope.equipment).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('saveEquipmentAlready');
				}
			});
			return true;
		}
		// Delete.
		, deleteEquipment: function($scope) {
			var urlServer = $rootScope.makePostURL("/equipment/delete/" + $scope.resourceId);
			$http.delete(urlServer);
			return true;
		}
		
	}
});