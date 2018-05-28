

/**
 * Controller for ${entity.name}
 **/


app.factory('termService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(term) {
			var urlServer = $rootScope.makePostURL("/term/insert");
			return $http.post(urlServer, term);
		}
		
		// Update.
		, update: function(termId, term) {
			var urlServer = $rootScope.makePostURL("/term/update/" + termId);
			return $http.put(urlServer, term);
		}
		
		// Delete.
		, delete: function(termId) {
			var urlServer = $rootScope.makePostURL("/term/delete/" + termId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(termId) {
			var url = $rootScope.makeGetURL("/term/getbyid/" + termId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, term) {
			var url = $rootScope.makeGetURL("/term/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: term });
		}
		
	}// return.
});