

/**
 * Controller for ${entity.name}
 **/


app.factory('healthService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(health) {
			var urlServer = $rootScope.makePostURL("/health/insert");
			return $http.post(urlServer, health);
		}
		
		// Update.
		, update: function(healthId, health) {
			var urlServer = $rootScope.makePostURL("/health/update/" + healthId);
			return $http.put(urlServer, health);
		}
		
		// Delete.
		, delete: function(healthId) {
			var urlServer = $rootScope.makePostURL("/health/delete/" + healthId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(healthId) {
			var url = $rootScope.makeGetURL("/health/getbyid/" + healthId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, health) {
			var url = $rootScope.makeGetURL("/health/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: health });
		}
		
	}// return.
});