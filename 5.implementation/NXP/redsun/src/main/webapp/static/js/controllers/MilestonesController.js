

/**
 * Controller for Milestones
 **/

app.controller('milestoneController', ['$scope', '$rootScope', '$http', '$log', '$window', 'milestoneService', function($scope, $rootScope, $http, $log, $window, milestoneService){
	
	$scope.itemsPerPage = 4; //this could be a dynamic value from a drop down
	$scope.totalCount = 0;
	
	// Init for list.
	$scope.initList = function() {
		$scope.getsForPageAndFilter(1);
	}
	
	// Init for edit.
	$scope.initEdit = function(id) {
		if(id > -1) {
			$scope.getById(id);
		}
	}
	
	// Show add screen.
	$scope.add = function() {
		$window.location.href = 'add';
	}
	
	// Show edit screen.
	$scope.edit = function(id) {
		$window.location.href = 'edit/' + id;
	}
	
	// Save.
	$scope.save = function(id) {
		if($scope.frmMilestone.$invalid) {
			$scope.frmMilestone.$dirty = true;
			return;
		}
		var result;
		if(id === -1) {
			result = milestoneService.add($scope.milestone);
		} else {
			$scope.milestone.id = id;
			result = milestoneService.update(id, $scope.milestone);
		}
		var milestoneId = id;
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				if(milestoneId === -1) {
					$window.location.href = "list";
				} else {
					$window.alert('saved');
				}
			} else {
				$window.alert('failed');
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$window.alert('error');
	});
	}
	
	// Delete.
	$scope.delete = function(id){
		if($window.confirm('Are you sure to delete?')) {
			var result = milestoneService.delete(id)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$window.alert('deleted');
					$scope.getsForPageAndFilter(1);
				} else {
					$window.alert('failed');
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$window.alert('error');
			});
		}
	} 
	
	// Get by Id.
	$scope.getById = function(id) {
		milestoneService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.milestones) {
				$scope.milestone = response.result.milestones[0];
			} else {
				$window.alert('failed');
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$window.alert('error');
		});
	}
	
	// List for page and filter.
	$scope.getsForPageAndFilter = function(pageNo) {
		milestoneService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.milestone)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.milestones) {
				$scope.milestones = response.result.milestones;
				$scope.totalCount = response.result.milestones[0].totalCount;
			} else {
				$window.alert('failed');
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$window.alert('error');
		});
	}
	
	/*Extend functions*/
	
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
}]);