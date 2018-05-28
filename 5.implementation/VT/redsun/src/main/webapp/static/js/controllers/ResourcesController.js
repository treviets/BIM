/**
 * Controller for Resources
 */

app.controller('resourceController',['$scope', '$rootScope', '$http', '$log', '$window', 'resourceService', function($scope, $rootScope, $http, $log, $window, resourceService) {

	$scope.itemsPerPage = 1000; // this could be a dynamic value from a drop down
	$scope.totalCount = 0;
	$scope.resourceActivationgTab = 'active-users';
	// ------------------------Resources--------------------------------

	$scope.setActiveTabName = function(activeTabName){
		$scope.resourceActivationgTab = activeTabName;
	}
	// Init for list internal.
	$scope.initListResource = function() {
		$scope.getsForPageResource(1);
	}
	// back list internal.
	$scope.backListResource = function() {
		$window.location.href = $rootScope.makeGetURL("/hr");
	}
	// Init for list exterior.
	$scope.initListExterior = function() {
		$scope.getsForPageExterior(1);
	}
	// back list exterior.
	$scope.backListExterior = function() {
		$window.location.href = $rootScope.makeGetURL("/hr/exterior");
	}
	// exterior
	$scope.exteriorManagement = function() {
		$window.location.href = $rootScope
				.makeGetURL("/hr/exterior");
	};
	$scope.internalManagement = function() {
		$window.location.href = 'listpage';
	};
	// Init for edit.
	$scope.initEditResource = function(id) {
		$scope.resource = {};
		$scope.resourceId = id;
		if ($scope.resourceId > -1) {
			resourceService.getByIdResource($scope);
		}
	}

	$scope.exportResource = function(){
		var fullNameOfElement = $scope.resourceActivationgTab+"-area";
		createExcel($scope.resourceActivationgTab, fullNameOfElement);
	}
	// Show add screen.
	$scope.newCreateResource = function() {
		$window.location.href = $rootScope
				.makeGetURL('/hr/showform');
	}
	// Show add screen.
	$scope.newCreateExterior = function() {
		$window.location.href = $rootScope
				.makeGetURL('/hr/showformexterior');
	}

	// get resource
	$scope.getResources = function() {
		resourceService.getResources($scope);
	};
	
	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	
	// get deleted resource
	$scope.getDeletecResources = function() {
		resourceService.getDeletedResources($scope);
	};
	$scope.$on('getDeletedResourcesAlready', function() {
		if ($scope.data) {
			$scope.deletedResources = $scope.data.result.resources;
		}
	});
	
	$scope.restoreResource = function(resourceId) {
		if ($window.confirm('Do you want to restore this user?')) {
			$scope.resourceId = resourceId;
			resourceService.restoreResource($scope);
		}
	};
	$scope.$on('restoreResourcesAlready', function() {
		$scope.getDeletecResources($scope);
		$scope.initListResource();
	});
	
	// get resource
	$scope.getResourcesExist = function() {
		resourceService.getResourcesExist($scope);
	};
	$scope.$on('getResourcesExistAlready', function() {
		if ($scope.data) {
			$scope.resourcesexists = $scope.data.result.resourcesexists;
		}
	});

	// count to set code for HR
	$scope.countResource = function() {
		resourceService.countResource($scope);
	};
	$scope.$on('countAlready', function() {
		if ($scope.data) {
			$scope.count = $scope.data.result.count;
		}
	});

	// Show edit screen.
	$scope.editResource = function(id) {
		$window.location = $rootScope.makeGetURL('/hr/edit/' + id);
	}
	// Show edit screen.
	$scope.editResourceExterior = function(id) {
		$window.location = $rootScope.makeGetURL('/hr/editexterior/' + id);
	}
	// Save.
	$scope.saveResource = function(id) {
		$scope.resourceId = id;

		if ($scope.resourceId && $scope.resourceId > 0) {
			var hasNoError = true;
			$("#result").text("");
			var email = $("#email").val();
			if ($scope.validateEmail(email)) {
				hasNoError = true;
			} else if(email !== ''){
				$("#result").text(email + " is not valid!");
				$("#result").css("color", "red");
			}
			$("#resultNumberMobile").text("");
			$("#resultNumberPhone").text("");
			var number = $("#mobile").val();
			var numbers = $("#phone").val();
			if (number != null) {
				hasNoError = true;
				if (numbers != null) {
					hasNoError = true;
				} else {
					$("#resultNumberPhone").text(numbers + " must number!");
					$("#resultNumberPhone").css("color", "red");
					hasNoError = false;
				}
			} else {
				$("#resultNumberMobile").text(number + " must number!");
				$("#resultNumberMobile").css("color", "red");
				hasNoError = false;
			}
			if (!$scope.resource.name) {
				$scope.resourceNameError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.idCard) {
				$scope.IdCardError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.title) {
				$scope.titleError = 'has-error';
				hasNoError = false;
			}

			for (var i = 0; i<$scope.resourcesexists.length;i++){
				if (($scope.resourcesexists[i].code == $scope.resource.code)){
					alert($scope.resource.code + " has already been used. Try another code");
					hasNoError = false;
				}
			}
			if (hasNoError == true) {
				resourceService.updateResource($scope);
			}
		}

		// new resource
		if ($scope.resourceId == null) {
			var hasNoError = true;
			$("#result").text("");
			var email = $("#email").val();
			
			if ($scope.validateEmail(email)) {
				hasNoError = true;
			} else if(email !==''){
				$("#result").text(email + " is not valid!");
				$("#result").css("color", "red");
			}
			$("#resultNumberMobile").text("");
			$("#resultNumberPhone").text("");
			var number = $("#mobile").val();
			var numbers = $("#phone").val();
			if (number >= 0 || number <= 9) {
				hasNoError = true;
				if (numbers >= 0 || numbers <= 9) {
					hasNoError = true;
				} else {
					$("#resultNumberPhone").text(numbers + " must number!");
					$("#resultNumberPhone").css("color", "red");
					hasNoError = false;
				}
			} else {
				$("#resultNumberMobile").text(number + " must number!");
				$("#resultNumberMobile").css("color", "red");
				hasNoError = false;
			}

			if (!$scope.resource.name) {
				$scope.resourceNameError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.title) {
				$scope.titleError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.mobile) {
				$scope.mobileError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.code) {
				$scope.codeError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.idCard) {
				$scope.IdCardError = 'has-error';
				hasNoError = false;
			}
			if($scope.resource.code!=null){
				for (var i = 0; i<$scope.resources.length;i++){
					if ($scope.resources[i].code == $scope.resource.code){
						alert($scope.resource.code + " has already been used. Try another code");
						hasNoError = false;
					}
				}
			}
			if (hasNoError == true) {
				resourceService.createResource($scope)
			}
		}
	}
	// Save exterior
	$scope.saveExterior = function(id) {
		$scope.resourceId = id;
		// new resource
		if ($scope.resourceId == null) {
			$scope.resource.isContact = '1';
			var hasNoError = true;
			$("#result").text("");
			var email = $("#email").val();
			if ($scope.validateEmail(email)) {
				hasNoError = true;
			} else {
				$("#result").text(email + " is not valid!");
				$("#result").css("color", "red");
			}
			$("#resultNumberMobile").text("");
			$("#resultNumberPhone").text("");
			var number = $("#mobile").val();
			var numbers = $("#phone").val();
			if (number >= 0 || number <= 9) {
				hasNoError = true;
				if (numbers >= 0 || numbers <= 9) {
					hasNoError = true;
				} else {
					$("#resultNumberPhone").text(numbers + " must number");
					$("#resultNumberPhone").css("color", "red");
					hasNoError = false;
				}
			} else {
				$("#resultNumberMobile").text(number + " must number");
				$("#resultNumberMobile").css("color", "red");
				hasNoError = false;
			}

			if (!$scope.resource.name) {
				$scope.resourceNameError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.idCard) {
				$scope.idCardError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.mobile) {
				$scope.mobileError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.code) {
				$scope.codeError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.title) {
				$scope.titleError = 'has-error';
				hasNoError = false;
			}
			if($scope.resource.code!=null){
				
				for (var i = 0; i<$scope.resources.length;i++){
					if ($scope.resources[i].code == $scope.resource.code){
						alert($scope.resource.code + " has already been used. Try another code");
						hasNoError = false;
					}
				}
			}
			if (hasNoError == true) {
				resourceService.createResource($scope)
				$window.location.href = $rootScope.makeGetURL("/hr/exterior");
			}
		}
		
		//update
		if ( $scope.resourceId){

			$scope.resource.isContact = '1';
			var hasNoError = true;
			$("#result").text("");
			var email = $("#email").val();
			if ($scope.validateEmail(email)) {
				hasNoError = true;
			} else {
				$("#result").text(email + " is not valid!");
				$("#result").css("color", "red");
			}
			$("#resultNumberMobile").text("");
			$("#resultNumberPhone").text("");
			var number = $("#mobile").val();
			var numbers = $("#phone").val();
			if (number >= 0 || number <= 9) {
				hasNoError = true;
				if (numbers >= 0 || numbers <= 9) {
					hasNoError = true;
				} else {
					$("#resultNumberPhone").text(numbers + " must number");
					$("#resultNumberPhone").css("color", "red");
					hasNoError = false;
				}
			} else {
				$("#resultNumberMobile").text(number + " must number");
				$("#resultNumberMobile").css("color", "red");
				hasNoError = false;
			}

			if (!$scope.resource.name) {
				$scope.resourceNameError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.idCard) {
				$scope.idCardError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.mobile) {
				$scope.mobileError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.code) {
				$scope.codeError = 'has-error';
				hasNoError = false;
			}
			if (!$scope.resource.title) {
				$scope.titleError = 'has-error';
				hasNoError = false;
			}
			if($scope.resource.code!=null){
				
				for (var i = 0; i<$scope.resources.length;i++){
					if ($scope.resources[i].code == $scope.resource.code){
						alert($scope.resource.code + " has already been used. Try another code");
						hasNoError = false;
					}
				}
			}
			if (hasNoError == true) {
				$scope.resource.country = "Việt Nam"
				resourceService.updateResource($scope)
				$window.location.href = $rootScope.makeGetURL("/hr/exterior");
			}
		
		}
	}
	$scope.validateEmail = function(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}
	// Delete.
	$scope.deleteResource = function(id) {
		if ($window.confirm('Are you sure to delete?')) {
			$scope.resourceId = id;
			for (var i = 0; i<$scope.resources.length;i++){
				if ($scope.resources[i].id == $scope.resourceId){
					$scope.resources[i].isTrash = '1';
					$scope.resource = $scope.resources[i];
					resourceService.updateResource($scope);
					$window.location.href = $rootScope.makeGetURL("/hr");
				}
			}
		}
	}
	
	// Delete.
	$scope.removeResource = function(id) {
		if ($window.confirm('Do you want to permanent remove this user?')) {
			$scope.resourceId = id;
			resourceService.deleteResource($scope);
		}
	}
	$scope.$on('removedResourceAlready', function() {
		$scope.getDeletecResources($scope);
	});
	
	// List for page and filter.
	$scope.getsForPageResource = function(pageNo) {
		$scope.pageNo = pageNo;
		resourceService.getsForPageResource($scope);
	}
	// List for page and filter.
	$scope.getsForPageExterior = function(pageNo) {
		$scope.pageNo = pageNo;
		resourceService.getsForPageExterior($scope);
	}

	/* Extend functions */
	// Import member from file.
	$scope.fileResourceSelected = function(element) {
		$scope.fileResource = element.files[0];
	}
	$scope.importResource = function() {
		if(!$scope.fileResource) {
			alert('Choose a file');
		} else {
			$scope.importResourceFile = new FormData();
			$scope.importResourceFile.append('file', $scope.fileResource);
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/hr/import-resource") + "&filePath=null&projectId=0",
	            data: $scope.importResourceFile,
	            headers: {
	                'Content-Type': undefined
	            }
	        };			
			
			$http(request).success(function(response) {
				alert('Done');
				$window.location.href = $window.location;
			})
			.error(function(response) {
				alert('Failed');
			});
			
		}
	}
	
	// Sort by.
	$scope.sortBy = function(keyName) {
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}

	// get Roles
	$scope.getRoles = function() {
		resourceService.getRoles($scope);
	};
	$scope.$on('getRolesAlready', function() {
		if ($scope.data) {
			$scope.roles = $scope.data.result.roles;
		}
	});

	// get Roles
	$scope.getClientId = function() {
		resourceService.getClientId($scope);
	};
	$scope.$on('getClientIdAlready', function() {
		if ($scope.data) {
			$scope.clientId = $scope.data;
		}
	});
}]);