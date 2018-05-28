
/**
 * Service for Roles
 **/


app.factory('roleService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(role) {
			var urlServer = $rootScope.makePostURL("/role/insert");
			return $http.post(urlServer, role);
		}
		
		// Update.
		, update: function(roleId, role) {
			var urlServer = $rootScope.makePostURL("/role/update/" + roleId);
			return $http.put(urlServer, role);
		}
		
		// Delete.
		, delete: function(roleId) {
			var urlServer = $rootScope.makePostURL("/role/delete/" + roleId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(roleId) {
			var url = $rootScope.makeGetURL("/role/getbyid/" + roleId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, role) {
			var url = $rootScope.makeGetURL("/role/list-extend/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: role });
		}
		
	}// return.
});
