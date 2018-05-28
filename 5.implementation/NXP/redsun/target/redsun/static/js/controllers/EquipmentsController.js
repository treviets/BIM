

/**
 * Controller for Equipments
 */

app.controller('equipmentController', ['$scope', '$rootScope', '$http', '$log', '$window', 'equipmentService',function($scope, $rootScope, $http, $log, $window, equipmentService){
	
	$scope.itemsPerPage = 10000000; // this could be a dynamic value from a drop
								// down
	$scope.totalCount = 0;
	
	
	// get units
	$scope.getUnits = function() {
		equipmentService.getUnits($scope);
	};
	$scope.$on('getUnitsAlready', function() {
		if ($scope.data) {
			$scope.units = $scope.data.result.units;
		}
	});
	// check exist
	$scope.checkExistEquipment = function() {
		equipmentService.checkExistEquipment($scope);
	};
	$scope.$on('checkExistEquipmentAlready', function() {
		if ($scope.data) {
			$scope.equipmentexist = $scope.data.result.equipmentexist;
		}
	});
	// List equipments
	$scope.initListEquipments = function() {
		$scope.getEquipmentForPage(1);
	}
	
	// Init for edit.
	$scope.initEditEquipment = function(id) {
		$scope.equipment = {};
		$scope.equipmentId = id;
		if($scope.equipmentId > -1) {
			equipmentService.getByIdEquipment($scope);
		}
	}
	
	// materials
	$scope.materialManagement = function() {
		$window.location.href = 'material';
	};
	// back list internal.
	$scope.backListEquipment = function() {
		$window.location.href = $rootScope.makeGetURL("/devices");
	}
	
	$scope.exportEquipments = function(elementId){
		createExcel(elementId,elementId);
	}
	// get equipment
	$scope.getEquipments = function() {
		equipmentService.getEquipments($scope);
	};
	$scope.$on('getEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.equipments = $scope.data.result.equipments;
		}
	});
	// Show edit screen.
	$scope.editEquipment = function(id) {
		$window.location.href = 'equipment/edit/' + id;
	}
	// List equipments
	$scope.getEquipmentForPage = function(pageNo) {
		equipmentService.getEquipmentForPage($scope.itemsPerPage, pageNo, $scope.equipment)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.equipments) {
				$scope.equipments = response.result.equipments;
				if(response.result.equipments[0]){
					$scope.totalCount = response.result.equipments[0].totalCount;
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
	$scope.addEquipment = function() {
		$window.location.href = 'equipment/showform';
	}
	
	// Save.
	$scope.saveEquipment = function(id) {
		$scope.equipmentId = id;
		var hasNoError = true;
		if($scope.equipmentId == null){
			 if(!$scope.equipment.name){
				  $scope.equipmentErrorName = 'has-error';
				  hasNoError = false;
			  }
			 for(var i = 0;i<$scope.equipments.length;i++){
				 if ($scope.equipment.code == $scope.equipments[i].code){
					 hasNoError = false;
					 alert($scope.equipment.code + " has already been used. Try another code");
				 }
			 }
			 if(!$scope.equipment.code){
				  $scope.equipmentErrorCode = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.equipment.unit){
				  $scope.equipmentErrorUnit = 'has-error';
				  hasNoError = false;
			  }
			 if(hasNoError == true){
				 equipmentService.createEquipment($scope);
				 $scope.getEquipmentForPage(1);
			 }
		} else if  ($scope.equipmentId && $scope.equipmentId > 0){
			if(!$scope.equipment.name){
				  $scope.equipmentErrorName = 'has-error';
				  hasNoError = false;
			  }
			 for(var i = 0;i<$scope.equipmentexist.length;i++){
				 if ($scope.equipment.code == $scope.equipmentexist[i].code){
					 hasNoError = false;
					 alert($scope.equipment.code + " has already been used. Try another code");
				 }
			 }
			 if(!$scope.equipment.code){
				  $scope.equipmentErrorCode = 'has-error';
				  hasNoError = false;
			  }
			 if(!$scope.equipment.unit){
				  $scope.equipmentErrorUnit = 'has-error';
				  hasNoError = false;
			  }
			 if(hasNoError == true){
				 equipmentService.updateEquipment($scope);
				 $scope.getEquipmentForPage(1);
			 }
		}
	}
	$scope.$on('saveEquipmentAlready',function(){
		$window.location.href = $rootScope.makeGetURL("/devices");
	});
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
	
	// count to set code for HR
	$scope.countEquipment = function() {
		equipmentService.countEquipment($scope);
	};
	$scope.$on('countEquipmentAlready', function() {
		if ($scope.data) {
			$scope.count = $scope.data.result.count;
		}
	});
	
	//get Roles
	$scope.getClientId = function() {
		equipmentService.getClientId($scope);
	};
	$scope.$on('getClientIdAlready', function() {
		if ($scope.data) {
			$scope.clientId = $scope.data;
		}
	});
	$scope.deleteEquipment = function(id){
		if($window.confirm('Are you sure to delete?') == true ) {
			$scope.resourceId = id;
			equipmentService.deleteEquipment($scope);
			 $scope.getEquipments();
		}
	} 
}]);