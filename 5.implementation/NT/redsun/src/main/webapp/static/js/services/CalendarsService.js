

/**
 * Controller for ${entity.name}
 **/


app.factory('calendarService', function($http, $rootScope) {
	return {
	
		// Insert.
		create: function($scope) {
			var urlServer = $rootScope.makePostURL("/calendars/add");
			
			$http.post(urlServer, $scope.calendar).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		},
		
		list: function($scope){
			var url =$rootScope.makePostURL("/calendars/list");S
			console.log("fff");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data.result.calendars);
				}
				$scope.$broadcast('getCalendarsAlready');
			});
			return true;
		}
		
		/*// Update.
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
		
		 List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, calendarDefinition) {
			var url = $rootScope.makeGetURL("/calendardefinition/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: calendarDefinition });
		}*/
		
	}// return.
});