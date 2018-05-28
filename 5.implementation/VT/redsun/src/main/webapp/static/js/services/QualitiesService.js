

/**
 * Controller for ${entity.name}
 **/


app.factory('qualitieService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(qualitie) {
			var urlServer = $rootScope.makePostURL("/qualitie/insert");
			return $http.post(urlServer, qualitie);
		}
		
		// Update.
		, update: function(qualitieId, qualitie) {
			var urlServer = $rootScope.makePostURL("/qualitie/update/" + qualitieId);
			return $http.put(urlServer, qualitie);
		}
		
		// Delete.
		, delete: function(qualitieId) {
			var urlServer = $rootScope.makePostURL("/qualitie/delete/" + qualitieId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(qualitieId) {
			var url = $rootScope.makeGetURL("/qualitie/getbyid/" + qualitieId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, qualitie) {
			var url = $rootScope.makePostURL("/qualitie/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: qualitie });
		}
		
	}// return.
});