/**
 * Controller for Resources
 */

app.controller('HRDetailController',['$scope', '$rootScope', '$http', '$log', '$window', 'resourceService','$templateRequest', '$mdDialog', '$compile', function($scope, $rootScope, $http, $log, $window, resourceService, $templateRequest, $mdDialog, $compile) {
	$scope.pageLoad = function(){
	
		
			}
	$scope.pageLoad();
	$scope.addNew = function(){
		$templateRequest("/redsun/static/partials/personal-info.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.saveRelationship = function(){
		$templateRequest("/redsun/static/partials/relationship.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.saveEducation = function(){
		$templateRequest("/redsun/static/partials/education.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.saveWorkInfo = function(){
		$templateRequest("/redsun/static/partials/work-info.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.workingProcess = function(){
		$templateRequest("/redsun/static/partials/working-process.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	
	$scope.projectInfo = function(){
		$templateRequest("/redsun/static/partials/project-info.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	
	$scope.laborContract = function(){
		$templateRequest("/redsun/static/partials/labor-contract.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	
	$scope.salaryInfo = function(){
		$templateRequest("/redsun/static/partials/salary-info.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.salaryProcess = function(){
		$templateRequest("/redsun/static/partials/salary-process.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
	$scope.accountInfo = function(){
		debugger
		$templateRequest("/redsun/static/partials/account-info.html").then(function(html){
			$compile(html)($scope);
			var popup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
				}
		            
		            });
			$mdDialog.show(popup);
			});
			
	}
}]);