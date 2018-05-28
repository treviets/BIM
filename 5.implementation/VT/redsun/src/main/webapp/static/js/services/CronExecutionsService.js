

/**
 * Controller for ${entity.name}
 **/


app.factory('cronExecutionService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(cronExecution) {
			var urlServer = $rootScope.makePostURL("/cronexecution/insert");
			return $http.post(urlServer, cronExecution);
		}
		
		// Update.
		, update: function(cronExecutionId, cronExecution) {
			var urlServer = $rootScope.makePostURL("/cronexecution/update/" + cronExecutionId);
			return $http.put(urlServer, cronExecution);
		}
		
		// Delete.
		, delete: function(cronExecutionId) {
			var urlServer = $rootScope.makePostURL("/cronexecution/delete/" + cronExecutionId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(cronExecutionId) {
			var url = $rootScope.makeGetURL("/cronexecution/getbyid/" + cronExecutionId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, cronExecution) {
			var url = $rootScope.makeGetURL("/cronexecution/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: cronExecution });
		}
		
	}// return.
});