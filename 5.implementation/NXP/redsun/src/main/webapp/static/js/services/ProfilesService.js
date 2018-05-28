

/**
 * Controller for ${entity.name}
 **/


app.factory('profileService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(profile) {
			var urlServer = $rootScope.makePostURL("/profile/insert");
			return $http.post(urlServer, profile);
		}
		
		// Update.
		, update: function(profileId, profile) {
			var urlServer = $rootScope.makePostURL("/profile/update/" + profileId);
			return $http.put(urlServer, profile);
		}
		
		// Delete.
		, delete: function(profileId) {
			var urlServer = $rootScope.makePostURL("/profile/delete/" + profileId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(profileId) {
			var url = $rootScope.makeGetURL("/profile/getbyid/" + profileId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, profile) {
			var url = $rootScope.makePostURL("/profile/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: profile });
		}
		, getClientById: function($scope){
			var url = $rootScope.makeGetURL("/modules/getclient");
			$http.get(url).then(function(response) {
				if(response.data){
					$scope.data = response.data;
				}
				
				$scope.$broadcast('getClientAlready');
			});
			
			return true;
		}
		, changePassword: function($scope){
			var url = $rootScope.makeGetURL("/system/changepassword/" + $scope.currentPassword + "/" + $scope.newPassword);
			$http.get(url).then(function(response) {
				if(response.data){
					$scope.data = response.data;
				}
				
				$scope.$broadcast('updatePasswordAlready');
			});
			
			return true;
		}
	}// return.
});