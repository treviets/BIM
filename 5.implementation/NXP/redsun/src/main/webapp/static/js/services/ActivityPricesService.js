

/**
 * Controller for ${entity.name}
 **/


app.factory('activityPriceService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(activityPrice) {
			var urlServer = $rootScope.makePostURL("/activityprice/insert");
			return $http.post(urlServer, activityPrice);
		}
		
		// Update.
		, update: function(activityPriceId, activityPrice) {
			var urlServer = $rootScope.makePostURL("/activityprice/update/" + activityPriceId);
			return $http.put(urlServer, activityPrice);
		}
		
		// Delete.
		, delete: function(activityPriceId) {
			var urlServer = $rootScope.makePostURL("/activityprice/delete/" + activityPriceId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(activityPriceId) {
			var url = $rootScope.makeGetURL("/activityprice/getbyid/" + activityPriceId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, activityPrice) {
			var url = $rootScope.makeGetURL("/activityprice/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: activityPrice });
		}
		
	}// return.
});