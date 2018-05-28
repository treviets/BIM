

/**
 * Controller for ${entity.name}
 **/


app.factory('DepartmentService', function($http, $rootScope) {
	return {			
		// Get All.
		 getDepartment: function() {
			var url = $rootScope.makeGetURL("/departmentservice/list/departments");
			return $http.get(url);
		}
		
		// List for page and filter.
//		, getsForPageAndFilterClient: function(itemsPerPage, pageNo, client) {
//			var url = $rootScope.makeGetURL("/client/list/page/filter/" + itemsPerPage + "/" + pageNo);
//			return $http.get(url, { params: client });
//		}
		
	}// return.
});