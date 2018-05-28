

/**
 * Controller for ${entity.name}
 **/


app.factory('billLineService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(billLine) {
			var urlServer = $rootScope.makePostURL("/billline/insert");
			return $http.post(urlServer, billLine);
		}
		
		// Update.
		, update: function(billLineId, billLine) {
			var urlServer = $rootScope.makePostURL("/billline/update/" + billLineId);
			return $http.put(urlServer, billLine);
		}
		
		// Delete.
		, delete: function(billLineId) {
			var urlServer = $rootScope.makePostURL("/billline/delete/" + billLineId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(billLineId) {
			var url = $rootScope.makeGetURL("/billline/getbyid/" + billLineId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, billLine) {
			var url = $rootScope.makeGetURL("/billline/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: billLine });
		}
		
	}// return.
});