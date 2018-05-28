

/**
 * Controller for ${entity.name}
 **/


app.factory('planningModeService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(planningMode) {
			var urlServer = $rootScope.makePostURL("/planningmode/insert");
			return $http.post(urlServer, planningMode);
		}
		
		// Update.
		, update: function(planningModeId, planningMode) {
			var urlServer = $rootScope.makePostURL("/planningmode/update/" + planningModeId);
			return $http.put(urlServer, planningMode);
		}
		
		// Delete.
		, delete: function(planningModeId) {
			var urlServer = $rootScope.makePostURL("/planningmode/delete/" + planningModeId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(planningModeId) {
			var url = $rootScope.makeGetURL("/planningmode/getbyid/" + planningModeId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, planningMode) {
			var url = $rootScope.makeGetURL("/planningmode/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: planningMode });
		}
		
	}// return.
});