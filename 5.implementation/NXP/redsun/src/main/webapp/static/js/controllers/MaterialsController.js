

/**
 * Controller for Materials
 */

app.controller('materialController', ['$scope', '$rootScope', '$http', '$log', '$window', 'materialService', function($scope, $rootScope, $http, $log, $window, materialService){
	
	$scope.itemsPerPage = 10000000; // this could be a dynamic value from a drop
								// down
	$scope.totalCount = 0;
	
	
	$scope.backListMaterial = function() {
		$window.location.href = $rootScope.makeGetURL("/material");
	}
	// get units
	$scope.getUnits = function() {
		materialService.getUnits($scope);
	};
	$scope.$on('getUnitsAlready', function() {
		if ($scope.data) {
			$scope.units = $scope.data.result.units;
		}
	});
	
	$scope.exportMaterials = function(elementId){
		createExcel(elementId,elementId);
	}
	
	// check exist
	$scope.checkExistMaterial = function() {
		materialService.checkExistMaterial($scope);
	};
	$scope.$on('checkExistMaterialAlready', function() {
		if ($scope.data) {
			$scope.materialexist = $scope.data.result.materialexist;
		}
	});
	// equipment
	$scope.equipmentManagement = function() {
		$window.location.href = $rootScope.makeGetURL("/devices");
	};

	// List materials
	$scope.initListMaterials = function() {
		$scope.getMaterialForPage(1);
	}
	
	// Init for edit.
	$scope.initEditMaterial = function(id) {
		$scope.material = {};
		$scope.materialId = id;
		if($scope.materialId > -1) {
			materialService.getByIdMaterial($scope);
		}
	}
	
	// Show edit screen.
	$scope.editMaterial = function(id) {
		$window.location.href = 'material/edit/' + id;
	}
	// List materials
	$scope.getMaterialForPage = function(pageNo) {
		materialService.getMaterialForPage($scope.itemsPerPage, pageNo, $scope.material)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.materials) {
				$scope.materials = response.result.materials;
				if(response.result.materials[0]){
					$scope.totalCount = response.result.materials[0].totalCount;
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
	
	//------------------insert-------------------
	// Show add screen.
	$scope.addMaterial = function() {
		$window.location.href = $rootScope.makeGetURL("/material/showform");
	}
	// get material
	$scope.getMaterials = function() {
		materialService.getMaterials($scope);
	};
	$scope.$on('getMaterialsAlready', function() {
		if ($scope.data) {
			$scope.materials = $scope.data.result.materials;
		}
	});
	// Save.
	$scope.saveMaterial = function(id) {
		$scope.materialId = id;
		var hasNoError = true;
		if($scope.materialId == null){
			 if(!$scope.material.name){
				  $scope.materialErrorName = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.material.unit){
				  $scope.materialErrorUnit = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.material.code){
				  $scope.materialErrorCode = 'has-error';
				  hasNoError = false;
			  }
			 
			 for(var i = 0;i<$scope.materials.length;i++){
				 if ($scope.material.code == $scope.materials[i].code){
					 hasNoError = false;
					 alert($scope.material.code + " has already been used. Try another code!");
				 }
			 }
			 
			 if(hasNoError == true){
				 materialService.createMaterial($scope);
				 $window.location.href = $rootScope.makeGetURL("/material");
			 }
		} else if  ($scope.materialId && $scope.materialId > 0){
			
			 if(!$scope.material.name){
				  $scope.materialErrorName = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.material.code){
				  $scope.materialErrorCode = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.material.unit){
				  $scope.materialErrorUnit = 'has-error';
				  hasNoError = false;
			  }
			  for(var i = 0;i<$scope.materialexist.length;i++){
				 if ($scope.material.code == $scope.materialexist[i].code){
					 hasNoError = false;
					 alert($scope.material.code + " has already been used. Try another code!");
				 }
			 }
			if ( hasNoError == true){
				
				materialService.updateMaterial($scope);
				$window.location.href = $rootScope.makeGetURL("/material");
			}
		}
		}
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
	
	
	// count to set code for HR
	$scope.countMaterial = function() {
		materialService.countMaterial($scope);
	};
	$scope.$on('countMaterialAlready', function() {
		if ($scope.data) {
			$scope.count = $scope.data.result.count;
		}
	});
	
	//get Roles
	$scope.getClientId = function() {
		materialService.getClientId($scope);
	};
	$scope.$on('getClientIdAlready', function() {
		if ($scope.data) {
			$scope.clientId = $scope.data;
		}
	});
	$scope.deleteMaterial = function(id){
		if($window.confirm('Are you sure to delete?') == true ) {
			materialService.deleteMaterial(id);
			$scope.getMaterialForPage(1);
		}
	} 
}]);