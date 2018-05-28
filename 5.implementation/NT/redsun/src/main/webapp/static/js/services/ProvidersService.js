

/**
 * Controller for ${entity.name}
 **/


app.factory('providerService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(provider) {
			var urlServer = $rootScope.makePostURL("/provider/insert");
			return $http.post(urlServer, provider);
		}
		
		// Update.
		, update: function(providerId, provider) {
			var urlServer = $rootScope.makePostURL("/provider/update/" + providerId);
			return $http.put(urlServer, provider);
		}
		
		// Delete.
		, delete: function(providerId) {
			var urlServer = $rootScope.makePostURL("/provider/delete/" + providerId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(providerId) {
			var url = $rootScope.makeGetURL("/provider/getbyid/" + providerId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, provider) {
			var url = $rootScope.makeGetURL("/provider/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: provider });
		}
		
	}// return.
});