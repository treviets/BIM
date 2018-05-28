

/**
 * Controller for ${entity.name}
 **/


app.factory('deliveryModeService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(deliveryMode) {
			var urlServer = $rootScope.makePostURL("/deliverymode/insert");
			return $http.post(urlServer, deliveryMode);
		}
		
		// Update.
		, update: function(deliveryModeId, deliveryMode) {
			var urlServer = $rootScope.makePostURL("/deliverymode/update/" + deliveryModeId);
			return $http.put(urlServer, deliveryMode);
		}
		
		// Delete.
		, delete: function(deliveryModeId) {
			var urlServer = $rootScope.makePostURL("/deliverymode/delete/" + deliveryModeId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(deliveryModeId) {
			var url = $rootScope.makeGetURL("/deliverymode/getbyid/" + deliveryModeId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, deliveryMode) {
			var url = $rootScope.makeGetURL("/deliverymode/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: deliveryMode });
		}
		
	}// return.
});