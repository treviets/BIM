

/**
 * Controller for ${entity.name}
 **/


app.factory('workflowService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(workflow) {
			var urlServer = $rootScope.makePostURL("/workflow/insert");
			return $http.post(urlServer, workflow);
		}
		
		// Update.
		, update: function(workflowId, workflow) {
			var urlServer = $rootScope.makePostURL("/workflow/update/" + workflowId);
			return $http.put(urlServer, workflow);
		}
		
		// Delete.
		, delete: function(workflowId) {
			var urlServer = $rootScope.makePostURL("/workflow/delete/" + workflowId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(workflowId) {
			var url = $rootScope.makeGetURL("/workflow/getbyid/" + workflowId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, workflow) {
			var url = $rootScope.makeGetURL("/workflow/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: workflow });
		}
		
	}// return.
});