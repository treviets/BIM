/**
 * Controller for Resources
 */

app.controller('HRListController', [ '$scope', '$rootScope', '$http', '$log',
		'$window', 'HRListService','$templateRequest', '$mdDialog',
		function($scope, $rootScope, $http, $log, $window, HRListService, $templateRequest, $mdDialog) {
			$scope.pageLoad = function() {
				HRListService.getListStaff()
				.success(function(response, status, headers, config) {
					response = angular.fromJson(response);
					if(response && response.status === 1 && response.result.listStaffs) {
						$scope.lstHR  = response.result.listStaffs;
					} else {
						$window.alert('failed');
					}
				})
				// error.
				.error(function(response, status, headers, config) {
					$window.alert('error');
				});
				
			}
			$scope.pageLoad();
			$scope.goDetail = function() {

				$window.location.href = '/redsun/humanresource/HRDetail'
			}
			$scope.addNew = function(){
				$templateRequest("/redsun/static/partials/project-tabs-add.html").then(function(html){
					$compile(html)($scope);
					var addProjectPopup = $mdDialog.confirm({
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
						
					});
					$mdDialog.show(addProjectPopup);
			}
		} ]);