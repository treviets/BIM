app.factory('memberService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getMemberOneProject : function($scope) {
			var url = $rootScope.makeGetURL("/projectresource/listforoneproject/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMemberOneProjectAlready');
			});

			return true;
		}
	//get roles
	,
	getRoles : function($scope) {
		var url = $rootScope.makeGetURL("/role/listall");
		$http.get(url).then(function(response) {
			if (response.data) {
				$scope.data = angular.fromJson(response.data);
			}
			$scope.$broadcast('getRolesAlready');
		});

		return true;
	}
	,
	getFilterMember : function($scope) {
		var url = $rootScope.makeGetURL("/hr/filtermember/" + $scope.projectId);
		$http.get(url).then(function(response) {
			if (response.data) {
				$scope.data = angular.fromJson(response.data);
			}
			$scope.$broadcast('getFilterMemberAlready');
		});

		return true;
	}
	// add
	,
	create : function(member){
		var url =$rootScope.makePostURL("/projectresource/add");
		$http.post(url, member).then(function(response) {
			if(response.member){
				projectresources= angular.fromJson(response.member);
			}
		});
		return true;
	}
	,
	createConfigurations : function(congifuration){
		var url =$rootScope.makePostURL("/configurations/add");
		$http.post(url, congifuration).then(function(response) {
			if(response.congifuration){
				congifurations= angular.fromJson(response.congifuration);
			}
		});
		return true;
	},
		
	};
});