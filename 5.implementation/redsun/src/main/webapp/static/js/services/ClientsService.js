

/**
 * Controller for ${entity.name}
 **/


app.factory('clientService', function($http, $rootScope, $window) {
	return {
	
		// Insert.
		addClient: function(client) {
			var urlServer = $rootScope.makePostURL("/client/insert");
			return $http.post(urlServer, client)
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status > 0){
					$window.location.href =  $rootScope.makeGetURL("/client/listpage");
				} else {
					$window.alert(response.description);
				}
			})
			.error(function(response, status, headers, config) {
				$window.alert('Some issues occured, contact Admin for more details.');
			});
		}
		
		// Update.
		, updateClient: function($scope) {
			var urlServer = $rootScope.makePostURL("/client/update/" + $scope.client.id);
			return $http.put(urlServer, $scope.client)
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status > 0){
					$window.location.href =  $rootScope.makeGetURL("/client/listpage");
				} else {
					$window.alert(response.description);
				}
			})
			.error(function(response, status, headers, config) {
				$window.alert('Some issues occured, contact Admin for more details.');
			});
		}
		
		// Delete.
		, deleteClient: function(clientId) {
			var urlServer = $rootScope.makePostURL("/client/delete/" + clientId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getByIdClient: function(clientId) {
			var url = $rootScope.makeGetURL("/client/getbyid/" + clientId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilterClient: function(itemsPerPage, pageNo, client) {
			var url = $rootScope.makeGetURL("/client/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: client });
		}
		
	}// return.
});