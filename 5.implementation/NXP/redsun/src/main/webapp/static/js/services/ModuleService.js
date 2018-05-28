app.factory('moduleService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		// get module properties
		getModuleProperties: function($scope, moduleName) {
			var url = $rootScope.makeGetURL("/modules/moduleproperties/" + moduleName);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getModulePropertisAlready');
			});

			return true;
		},
		// save project role
		saveModulePermission: function($scope,modulePermissionList){
			 var url = $rootScope.makePostURL("/modules/modulepermission/savelist");
			 $http.post(url, JSON.stringify(modulePermissionList)).success(function(response){
				var msgSaving = "Save successfully.";
				$scope.$broadcast('saveModulePermissionAlready');
			}).finally(function(){
				
			});
			return false;
		},
		
		getModulePermission : function($scope) {
			var url = $rootScope.makeGetURL("/modules/modulepermissionlist/" +$scope.module_permission_key);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getModulePermissionAlready');
			});

			return true;
		},

		deleteModulePermission: function($scope,id){
			var url = $rootScope.makePostURL("/modules/modulepermission/delete/" + id);
			$http.delete(url).then(function(response){
				$scope.$broadcast('deleteModulePermissionAlready');
			});

			return false;
		},
		// update module role
		updateModuleRole: function($scope){
			var urlServer = $rootScope.makePostURL("/modules/modulerole_update");
			$http.put(urlServer, $scope.moduleRole).then(function(response){
				if(response.data){
					$scope.data = response.data;
				}
				$scope.$broadcast('updateModuleRoleComplete');
			});
			return true;
		},
		getModuleRole: function($scope){
			var url = $rootScope.makeGetURL("/modules/permission/" +$scope.module_permission_key);
			$http.get(url).then(function(response){
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getModuleRoleAlready');
			});
			return true;
		},
		// get module roles by module permision key.
		getModuleRolesByKey: function($scope){
			var url = $rootScope.makeGetURL("/modules/getmoduleroles/" +$scope.module_permission_key+"/");
			$http.get(url).then(function(response){
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getModuleRolesByKeyAlready');
			});
			return true;
		},
		getModuleRoleForUser: function($scope, username){
			var url = $rootScope.makeGetURL("/modules/getmoduleroles/" +$scope.module_permission_key+"/"+username+"/");
			$http.get(url).then(function(response){
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getModuleRoleForUserAlready');
			});
			return true;
		}
	};
});