

/**
 * Controller for ${entity.name}
 **/


app.factory('referencableService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(referencable) {
			var urlServer = $rootScope.makePostURL("/referencable/insert");
			return $http.post(urlServer, referencable);
		}
		
		// Update.
		, update: function(referencableId, referencable) {
			var urlServer = $rootScope.makePostURL("/referencable/update/" + referencableId);
			return $http.put(urlServer, referencable);
		}
		
		// Delete.
		, delete: function(referencableId) {
			var urlServer = $rootScope.makeGetURL("/referencable/delete/" + referencableId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(referencableId) {
			var url = $rootScope.makeGetURL("/referencable/getbyid/" + referencableId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, referencable) {
			var url = $rootScope.makeGetURL("/referencable/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: referencable });
		}
		
	}// return.
});