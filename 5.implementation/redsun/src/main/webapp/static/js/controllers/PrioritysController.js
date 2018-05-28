app.controller('priorityCtrl', ['$scope', '$rootScope', '$http', '$timeout', '$window', 'priorityService', function($scope, $rootScope, $http, $timeout, $window, priorityService) {
	var pageno = 1; // initialize page no to 1

	$scope.totalCount = 0;
	$scope.itemsPerPage = 4; // this could be a dynamic value from a drop
	// down

	$scope.getPriorities = function(pageNo) {
		// Get priority by page
		priorityService.getPriorities($scope, $rootScope, $http, pageNo);
	};
	$scope.$on('getPrioritiesAlready', function() {
		if ($scope.data) {
			$scope.priority = $scope.data.result.priorities;
			$scope.totalCount = $scope.priority[0].totalCount;
		}

	});
	$scope.sort = function(keyName) {
		$scope.sortKey = keyName; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; // if true make it false and vice
		// versa
	}

	// Init for edit.
	$scope.initEditPriority = function(id) {
		$scope.priority = {};
		if (id > -1) {
			$scope.getById(id);
		}
	}
	// Show edit screen.
	$scope.edit = function(id) {
		$scope.priorityId = id;
		priorityService.getById($scope, $rootScope, $http);
	}

	// Save.
	$scope.save = function() {
		if ($scope.priorityId &&  $scope.priorityId > 0) {
			priorityService.update($scope, $rootScope, $http);
		}
	}
	
	// delete
	$scope.delete = function(id) {
		$scope.priorityId = id;
		priorityService.delete($scope, $rootScope, $http);
	}


}]);
