

/**
 * Controller for ${entity.name}
 **/


app.factory('versionService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(version) {
			var urlServer = $rootScope.makePostURL("/version/insert");
			return $http.post(urlServer, version);
		}
		
		// Update.
		, update: function(versionId, version) {
			var urlServer = $rootScope.makePostURL("/version/update/" + versionId);
			return $http.put(urlServer, version);
		}
		
		// Delete.
		, delete: function(versionId) {
			var urlServer = $rootScope.makePostURL("/version/delete/" + versionId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(versionId) {
			var url = $rootScope.makeGetURL("/version/getbyid/" + versionId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, version) {
			var url = $rootScope.makeGetURL("/version/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: version });
		}
		
	}// return.
});