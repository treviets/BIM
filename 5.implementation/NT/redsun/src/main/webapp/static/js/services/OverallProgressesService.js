

/**
 * Controller for ${entity.name}
 **/


app.factory('overallProgresseService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(overallProgresse) {
			var urlServer = $rootScope.makePostURL("/overallprogresse/insert");
			return $http.post(urlServer, overallProgresse);
		}
		
		// Update.
		, update: function(overallProgresseId, overallProgresse) {
			var urlServer = $rootScope.makePostURL("/overallprogresse/update/" + overallProgresseId);
			return $http.put(urlServer, overallProgresse);
		}
		
		// Delete.
		, delete: function(overallProgresseId) {
			var urlServer = $rootScope.makePostURL("/overallprogresse/delete/" + overallProgresseId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(overallProgresseId) {
			var url = $rootScope.makeGetURL("/overallprogresse/getbyid/" + overallProgresseId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, overallProgresse) {
			var url = $rootScope.makeGetURL("/overallprogresse/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: overallProgresse });
		}
		
	}// return.
});