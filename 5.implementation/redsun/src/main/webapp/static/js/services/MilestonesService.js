

/**
 * Controller for ${entity.name}
 **/


app.factory('milestoneService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(milestone) {
			var urlServer = $rootScope.makePostURL("/milestone/insert");
			return $http.post(urlServer, milestone);
		}
		
		// Update.
		, update: function(milestoneId, milestone) {
			var urlServer = $rootScope.makePostURL("/milestone/update/" + milestoneId);
			return $http.put(urlServer, milestone);
		}
		
		// Delete.
		, delete: function(milestoneId) {
			var urlServer = $rootScope.makePostURL("/milestone/delete/" + milestoneId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(milestoneId) {
			var url = $rootScope.makeGetURL("/milestone/getbyid/" + milestoneId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, milestone) {
			var url = $rootScope.makeGetURL("/milestone/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: milestone });
		}
		
	}// return.
});