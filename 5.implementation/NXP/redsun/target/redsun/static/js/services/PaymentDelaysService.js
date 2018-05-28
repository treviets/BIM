

/**
 * Controller for ${entity.name}
 **/


app.factory('paymentDelayService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(paymentDelay) {
			var urlServer = $rootScope.makePostURL("/paymentdelay/insert");
			return $http.post(urlServer, paymentDelay);
		}
		
		// Update.
		, update: function(paymentDelayId, paymentDelay) {
			var urlServer = $rootScope.makePostURL("/paymentdelay/update/" + paymentDelayId);
			return $http.put(urlServer, paymentDelay);
		}
		
		// Delete.
		, delete: function(paymentDelayId) {
			var urlServer = $rootScope.makePostURL("/paymentdelay/delete/" + paymentDelayId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(paymentDelayId) {
			var url = $rootScope.makeGetURL("/paymentdelay/getbyid/" + paymentDelayId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, paymentDelay) {
			var url = $rootScope.makeGetURL("/paymentdelay/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: paymentDelay });
		}
		
	}// return.
});