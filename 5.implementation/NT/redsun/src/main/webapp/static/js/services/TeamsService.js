

/**
 * Controller for ${entity.name}
 **/


app.factory('teamService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(team) {
			var urlServer = $rootScope.makePostURL("/team/insert");
			return $http.post(urlServer, team);
		}
		
		// Update.
		, update: function(teamId, team) {
			var urlServer = $rootScope.makePostURL("/team/update/" + teamId);
			return $http.put(urlServer, team);
		}
		
		// Delete.
		, delete: function(teamId) {
			var urlServer = $rootScope.makePostURL("/team/delete/" + teamId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(teamId) {
			var url = $rootScope.makeGetURL("/team/getbyid/" + teamId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, team) {
			var url = $rootScope.makeGetURL("/team/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: team });
		}
		
	}// return.
});