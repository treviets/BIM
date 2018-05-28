

/**
 * Controller for ${entity.name}
 **/


app.factory('recipientService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(recipient) {
			var urlServer = $rootScope.makePostURL("/recipient/insert");
			return $http.post(urlServer, recipient);
		}
		
		// Update.
		, update: function(recipientId, recipient) {
			var urlServer = $rootScope.makePostURL("/recipient/update/" + recipientId);
			return $http.put(urlServer, recipient);
		}
		
		// Delete.
		, delete: function(recipientId) {
			var urlServer = $rootScope.makePostURL("/recipient/delete/" + recipientId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(recipientId) {
			var url = $rootScope.makeGetURL("/recipient/getbyid/" + recipientId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, recipient) {
			var url = $rootScope.makeGetURL("/recipient/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: recipient });
		}
		
	}// return.
});