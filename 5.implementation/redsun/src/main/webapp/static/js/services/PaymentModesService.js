

/**
 * Controller for ${entity.name}
 **/


app.factory('paymentModeService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(paymentMode) {
			var urlServer = $rootScope.makePostURL("/paymentmode/insert");
			return $http.post(urlServer, paymentMode);
		}
		
		// Update.
		, update: function(paymentModeId, paymentMode) {
			var urlServer = $rootScope.makePostURL("/paymentmode/update/" + paymentModeId);
			return $http.put(urlServer, paymentMode);
		}
		
		// Delete.
		, delete: function(paymentModeId) {
			var urlServer = $rootScope.makePostURL("/paymentmode/delete/" + paymentModeId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(paymentModeId) {
			var url = $rootScope.makeGetURL("/paymentmode/getbyid/" + paymentModeId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, paymentMode) {
			var url = $rootScope.makeGetURL("/paymentmode/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: paymentMode });
		}
		
	}// return.
});