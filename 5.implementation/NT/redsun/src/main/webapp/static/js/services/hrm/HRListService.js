

/**
 * Controller for ${entity.name}
 **/


app.factory('HRListService', function($http, $rootScope) {
	return {			
		// Get All.
		 getListStaff: function() {
			var url = $rootScope.makeGetURL("/humanresource/list/staffs/");
			return $http.get(url);
		},
		
		getIconStaff: function(fileName, fileType) {
			var url = $rootScope.makeGetURL("/hrm-rest/download/" + fileName + "/" + fileType);
			return $http.get(url);
		}
		
		// List for page and filter.
//		, getsForPageAndFilterClient: function(itemsPerPage, pageNo, client) {
//			var url = $rootScope.makeGetURL("/client/list/page/filter/" + itemsPerPage + "/" + pageNo);
//			return $http.get(url, { params: client });
//		}
		
	}// return.
});