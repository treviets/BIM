

/**
 * Controller for ${entity.name}
 **/


app.factory('trendService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(trend) {
			var urlServer = $rootScope.makePostURL("/trend/insert");
			return $http.post(urlServer, trend);
		}
		
		// Update.
		, update: function(trendId, trend) {
			var urlServer = $rootScope.makePostURL("/trend/update/" + trendId);
			return $http.put(urlServer, trend);
		}
		
		// Delete.
		, delete: function(trendId) {
			var urlServer = $rootScope.makePostURL("/trend/delete/" + trendId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(trendId) {
			var url = $rootScope.makeGetURL("/trend/getbyid/" + trendId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, trend) {
			var url = $rootScope.makeGetURL("/trend/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: trend });
		}
		
	}// return.
});