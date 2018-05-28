

/**
 * Controller for Clients
 **/

app.controller('clientController', ['$scope', '$rootScope', '$http', '$log', '$window', 'clientService', function($scope, $rootScope, $http, $log, $window, clientService){
	
	$scope.itemsPerPage = 10; //this could be a dynamic value from a drop down
	$scope.totalCount = 0;
	$scope.editMode = false;
	// Init for list.
	$scope.initListClient = function() {
		$scope.getsForPageAndFilterClient(1);
	}
	
	// Init for edit.
	$scope.initEditClient = function(id) {
		if(id > -1) {
			$scope.getByIdClient(id);
			$scope.editMode = true;
		}
	}
	
	// Show add screen.
	$scope.addClient = function() {
		$window.location.href = 'add';
	}
	
	// Show edit screen.
	$scope.editClient = function(id) {
		$window.location.href = 'edit/' + id;
	}
	
	// back list internal.
	$scope.backListClient = function() {
		$window.location.href = $rootScope.makeGetURL("/client/listpage");
	}
	
	$scope.exportClient = function(elementId){
		createExcel(elementId,elementId);
	}
	// Save.
	$scope.saveClient = function(id) {
		var hasNoError = true;
		if ($scope.client.clientCode == null){
			 $scope.clientCodeError = 'has-error';
			 hasNoError = false;
		}
		if ($scope.client.name == null){
			 $scope.clientNameError = 'has-error';
			 hasNoError = false;
		}
		var result;
		if(id == -1) {
			if (hasNoError == true){
				result = clientService.addClient($scope.client);
			}
		} else {
			if ($scope.client.clientCode == null){
				 $scope.clientCodeError = 'has-error';
				 hasNoError = false;
			}
			if ($scope.client.name == null){
				 $scope.clientNameError = 'has-error';
				 hasNoError = false;
			}
			$scope.client.id = id;
			if (hasNoError == true){
				result = clientService.updateClient($scope);
			}
		}
	}
	
	// Delete.
	$scope.deleteClient = function(id){
		if($window.confirm('Are you sure to delete?')) {
			var result = clientService.deleteClient(id)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$window.alert('deleted');
					$scope.getsForPageAndFilterClient(1);
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
	$scope.getByIdClient = function(id) {
		clientService.getByIdClient(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.clients) {
				$scope.client = response.result.clients[0];
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
	$scope.getsForPageAndFilterClient = function(pageNo) {
		clientService.getsForPageAndFilterClient($scope.itemsPerPage, pageNo, $scope.client)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.clients) {
				$scope.clients = response.result.clients;
				$scope.totalCount = response.result.clients[0].totalCount;
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