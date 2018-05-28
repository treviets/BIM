

/**
 * Controller for Products
 **/

app.controller('productController', ['$scope', '$rootScope', '$http', '$log', '$window', 'productService', function($scope, $rootScope, $http, $log, $window, productService){
	
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
		if($scope.frmProduct.$invalid) {
			$scope.frmProduct.$dirty = true;
			return;
		}
		var result;
		if(id === -1) {
			result = productService.add($scope.product);
		} else {
			$scope.product.id = id;
			result = productService.update(id, $scope.product);
		}
		var productId = id;
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				if(productId === -1) {
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
			var result = productService.delete(id)
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
		productService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.products) {
				$scope.product = response.result.products[0];
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
		productService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.product)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.products) {
				$scope.products = response.result.products;
				$scope.totalCount = response.result.products[0].totalCount;
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