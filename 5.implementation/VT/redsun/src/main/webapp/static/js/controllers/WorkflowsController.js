

/**
 * Controller for Workflows
 **/

app.controller('workflowController', ['$scope', '$rootScope', '$http', '$log', '$window', 'workflowService', function($scope, $rootScope, $http, $log, $window, workflowService){
		
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
		if($scope.frmWorkflow.$invalid) {
			$scope.frmWorkflow.$dirty = true;
			return;
		}
		var result;
		if(id === -1) {
			result = workflowService.add($scope.workflow);
		} else {
			$scope.workflow.id = id;
			result = workflowService.update(id, $scope.workflow);
		}
		var workflowId = id;
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				if(workflowId === -1) {
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
			var result = workflowService.delete(id)
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
		workflowService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.workflows) {
				$scope.workflow = response.result.workflows[0];
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
		workflowService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.workflow)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.workflows) {
				$scope.workflows = response.result.workflows;
				$scope.totalCount = response.result.workflows[0].totalCount;
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
	
	$scope.doClick = function(event){

		 var x = event.clientX;
		 var y = event.clientY;
		 var offsetX = event.offsetX;
		 var offsetY = event.offsetY;

		 /// These are the 2 new lines, see you target the canvas element then apply it to getContext
		 var canvasElement = document.getElementById("workflowCanvas");
		 
		 
		 var ctx = canvasElement.getContext("2d");

		  //draw a circle
		  ctx.beginPath();
		  ctx.moveTo(offsetX, offsetY);
		  ctx.lineTo(x, y);
		  ctx.stroke();

		};
}]);