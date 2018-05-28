

/**
 * Controller for ${entity.name}
 **/


app.factory('likelihoodService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(likelihood) {
			var urlServer = $rootScope.makePostURL("/likelihood/insert");
			return $http.post(urlServer, likelihood);
		}
		
		// Update.
		, update: function(likelihoodId, likelihood) {
			var urlServer = $rootScope.makePostURL("/likelihood/update/" + likelihoodId);
			return $http.put(urlServer, likelihood);
		}
		
		// Delete.
		, delete: function(likelihoodId) {
			var urlServer = $rootScope.makePostURL("/likelihood/delete/" + likelihoodId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(likelihoodId) {
			var url = $rootScope.makeGetURL("/likelihood/getbyid/" + likelihoodId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, likelihood) {
			var url = $rootScope.makeGetURL("/likelihood/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: likelihood });
		}
		
	}// return.
});