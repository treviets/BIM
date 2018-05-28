/*
 * Redsun - BPMN
 * Service for Design Process screen
 */

app.factory('phaseService', function($http, $rootScope) {
			return {
				getAll: function($scope) {
					var url = $rootScope.makeGetURL("/all");
					return $http.get(url);
				},
				
				update: function($scope) {
					var url = $rootScope.makeGetURL("/update");
					
				}
			}
	});
