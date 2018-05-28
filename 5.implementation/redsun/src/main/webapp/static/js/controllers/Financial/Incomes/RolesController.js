


/**
 * Controller for Roles
 **/

app.controller('roleController', ['$scope', '$rootScope', '$http', '$log', '$window', '$location', 'roleService', function($scope, $rootScope, $http, $log, $window, $location, roleService) {
	
	// CKEditor options.
	CKEDITOR.config.extraPlugins = "base64image";
	$scope.options = {
		language: 'en',
	    allowedContent: true,
	    entities: false
	};
	// Paging.
	$scope.itemsPerPage = 4;
	$scope.totalCount = 0;
	
	//$location.url($window.location.pathname);
	$scope.role = {};
	
	// Init for list.
	$scope.initList = function() {
		$scope.getsForPageAndFilter(1);
	}
	
	// Init for edit.
	$scope.initEdit = function(id) {
		$scope.role.id = id;
		if($scope.role.id > -1) {
			$scope.getById($scope.role.id);
		}
		else {
			$scope.role.attachments = [];
		}
		$scope.frmDirty = false;
	}
	
	// Show list screen.
	$scope.list = function() {
		$window.location.href = $rootScope.makeGetURL('/role/list');
	}
	
	// Show add screen.
	$scope.add = function() {
		$window.location.href = $rootScope.makeGetURL('/role/form?id');
	}
	
	// Show edit screen.
	$scope.edit = function(id) {
		$window.location.href = $rootScope.makeGetURL('/role/form?id=' + id);
	}
	
	// Create new.
	$scope.createNew = function() {
		$scope.role = {id: -1}
		$scope.role.attachments = [];
		$scope.frmDirty = false;
	}
	
	// Save.
	$scope.save = function() {
		if($scope.frmRole.$invalid) {
			$scope.frmRole.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.showMessage('Saving!', 'alert-success', false);
		var result;
		if($scope.role.id > -1) {
			result = roleService.update($scope.role.id, $scope.role);
		} else {
			result = roleService.add($scope.role);
		}
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				$scope.role.id = response.result.id;
				$scope.showMessage('Saved!', 'alert-success', true);
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
	});
	}
	
	// Delete.
	$scope.delete = function(id){
		if($window.confirm('Are you sure to delete?')) {
			var result = roleService.delete(id)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$scope.showMessage('Deleted!', 'alert-success', true);
					$scope.getsForPageAndFilter(1);
				} else {
					$scope.showMessage('Fail!', 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.showMessage('Error!', 'alert-danger', true);
			});
		}
	} 
	
	// Get by Id.
	$scope.getById = function(id) {
		roleService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.roles !== undefined) {
				if(response.result.roles.length > 0) {
					$scope.role = response.result.roles[0];
				}
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
	}
	
	// List for page and filter.
	$scope.getsForPageAndFilter = function(pageNo) {
		roleService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.role)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.roles !== undefined) {
				$scope.roles = response.result.roles;
				$scope.totalCount = 0;
				if(response.result.roles.length > 0) {
					$scope.totalCount = response.result.roles[0].totalCount;
				}
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
	}
	
	/*Extend functions*/
	
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}

	// Show message.
	$scope.showMessage = function(message, cssName, autoHide) {
		$scope.alertMessage = message;
		$('#alertMessage').addClass(cssName);
		$('#alertMessage').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessage').slideUp(500, function() {
						$('#alertMessage').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}

}]);
