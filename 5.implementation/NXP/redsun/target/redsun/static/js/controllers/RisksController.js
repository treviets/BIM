app.controller('riskCtrl', ['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                    		'$window', 'riskService', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, riskService) {
	$scope.pageNo = 1; // initialize page no to 1
	$scope.totalCount = 0;
	$scope.itemsPerPage = 10; // this could be a dynamic value from a drop
	$scope.risk = {};
	// down

	// Get risk by page
	$scope.getRisks = function(pageNo) {
		$scope.pageNo = pageNo;
		riskService.getRisks($scope);

	};
	
	$scope.$on('getRisksAlready', function() {
		if ($scope.data) {
			$scope.risks = $scope.data.result.risks;
			$scope.totalCount = $scope.risks[0].totalCount;
		}

	});

	//get risk for one the project
	$scope.getRisksOnePrject = function(id) {
		$scope.projectId = id;
		riskService.getRisksOnePrject($scope);
	};

	$scope.$on('getRisksOnePrjectAlready', function() {
		if($scope.data) {
			$scope.risks = $scope.data.result.risks;
		}
	});
	
	// get types
	$scope.getTypes = function() {
		riskService.getTypes($scope);
	};
	$scope.$on('getTypesAlready', function() {
		if ($scope.data) {
			$scope.types = $scope.data.result.types;
			$scope.risk.riskTypeId = 1;
		}
	});
	
	// get likelihood
	$scope.getLikelihoods = function() {
		riskService.getLikelihoods($scope);
	};
	$scope.$on('getLikelihoodsAlready', function() {
		if ($scope.data) {
			$scope.likelihoods = $scope.data.result.likelihoods;
		}
	});

	// get severities
	$scope.getSeverities = function() {
		riskService.getSeverities($scope);
	};
	$scope.$on('getSeveritiesAlready', function() {
		if ($scope.data) {
			$scope.severities = $scope.data.result.severities;
		}
	});
	
	// get criticalities
	$scope.getCriticalities = function() {
		riskService.getCriticalities($scope);
	};
	$scope.$on('getCriticalitiesAlready', function() {
		if ($scope.data) {
			$scope.criticalities = $scope.data.result.criticalities;
		}
	});
	
	// get statuses
	$scope.getStatuses = function() {
		riskService.getStatuses($scope);
	};
	$scope.$on('getStatusAlready', function() {
		if ($scope.data) {
			$scope.statuses = $scope.data.result.statuses;
		}
	});
	
	// get priorities
	$scope.getPriority = function() {
		riskService.getPriority($scope);
	};
	$scope.$on('getPriorityAlready', function() {
		if ($scope.data) {
			$scope.priorities = $scope.data.result.priorities;
		}
	});
	
	$scope.gotoRiskDetailPage = function(object){
		$scope.riskId = object.id;
		$scope.risk = object;
		$scope.risk.projectId = $scope.projectId;
		$templateRequest("/redsun/static/partials/risk-add.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Edit risk";
			var addRiskPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveRisk = function(){
		            	riskService.updateRisk($scope);
		            	$mdDialog.hide();
		            }
				}
			});
			$mdDialog.show(addRiskPopup);
			
		});
	};
	$scope.addRisk = function(){
		$scope.risk={};
		$scope.risk.statusId = 32;
		$scope.risk.typeId = 1;
		$scope.risk.criticalityId = 1;
		$scope.risk.likelihoodId = 1;
		$scope.risk.severityId = 1;
		$scope.risk.priorityId = 1;
		$scope.risk.projectId = $scope.projectId;
		$templateRequest("/redsun/static/partials/risk-add.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Add risk";
			$scope.risk.projectId = $scope.projectId;
			var addRiskPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveRisk = function(){
		            	riskService.createRisk($scope);
		            	$mdDialog.hide();
		            }
				}
			});
			$mdDialog.show(addRiskPopup);
			
		});
	};
	
	// sort
	$scope.sort = function(keyName) {
		$scope.sortKey = keyName; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; // if true make it false and vice
		// versa
	}

	// Init for edit or add.
	$scope.initEditRisk = function(id) {
		$scope.riskId = id;
		$scope.risk = {};
		if ($scope.riskId == null || $scope.riskId > 0) {
			riskService.getTypes($scope);
			riskService.getLikelihoods($scope);
			riskService.getSeverities($scope);
			riskService.getStatuses($scope);
			riskService.getCriticalities($scope);
			riskService.getPriority($scope);
			riskService.getByIdRisk($scope);
			
		}
	}
	
	// add
	$scope.createRisk= function(){
		riskService.createRisk($scope);
	}

	// Save.
	$scope.saveRisk = function(id) {
		$scope.riskId = id;
		if ($scope.riskId &&  $scope.riskId > 0) {
			if($window.confirm('Do you want to save this action?') == true){			
				if(riskService.updateRisk($scope)){
					$window.location.href = $rootScope.makeGetURL("/risk/list");
			}
			} else{
				$window.location.href = $rootScope.makeGetURL("/risk/list");
			} 
		}
		if($scope.riskId == null){
			if(riskService.createRisk($scope)){
				$window.location.href = $rootScope.makeGetURL("/risk/list");
			}
		}
	}
	
	// delete
	$scope.deleteRisk= function(id, index) {
		$scope.riskId = id;
		$scope.index = index;
		if($window.confirm('Do you want to delete this risk?') == true){
			riskService.deleteRisk($scope);
		}
	}
}]);
