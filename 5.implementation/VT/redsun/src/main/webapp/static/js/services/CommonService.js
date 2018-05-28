

/**
 * Controller for ${entity.name}
 **/


app.factory('commonService', function($http, $rootScope, $window) {
	return {
	
		// Insert.
		getPermission: function() {
			var urlServer = $rootScope.MAIN_DOMAIN+ "/system/get-system-permission/";
			return $http.get(urlServer)
			.success(function(response, status, headers, config) {
				if(response){
					$window.localStorage['systemPermissions'] = JSON.stringify(response);
				}
			})
			.error(function(response, status, headers, config) {
				$window.alert('Cannot get current user permission');
			});
		}
		
	}// return.
});