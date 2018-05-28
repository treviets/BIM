

/**
 * Controller for ${entity.name}
 **/


app.factory('calendarDefinitionService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(calendarDefinition) {
			var urlServer = $rootScope.makePostURL("/calendardefinition/insert");
			return $http.post(urlServer, calendarDefinition);
		}
		
		// Update.
		, update: function(calendarDefinitionId, calendarDefinition) {
			var urlServer = $rootScope.makePostURL("/calendardefinition/update/" + calendarDefinitionId);
			return $http.put(urlServer, calendarDefinition);
		}
		
		// Delete.
		, delete: function(calendarDefinitionId) {
			var urlServer = $rootScope.makePostURL("/calendardefinition/delete/" + calendarDefinitionId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(calendarDefinitionId) {
			var url = $rootScope.makeGetURL("/calendardefinition/getbyid/" + calendarDefinitionId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, calendarDefinition) {
			var url = $rootScope.makeGetURL("/calendardefinition/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: calendarDefinition });
		}
		
	}// return.
});