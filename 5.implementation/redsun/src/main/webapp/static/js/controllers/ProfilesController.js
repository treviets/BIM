

/**
 * Controller for Profiles
 **/

app.controller('profileController', ['$scope', '$rootScope', '$http', '$log', '$window', '$translate', '$location', 'profileService',function($scope, $rootScope, $http, $log, $window, $translate, $location, profileService){
	
	var msgSaveSuccess = '';
	var msgSaveFail = '';
	var msgConfirmFail = '';
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
	
	setLang = function(langKey) {
        // You can change the language during runtime
        $translate.use(langKey).then(function () {
    		msgSaveSuccess = $translate.instant('profile_msg_change_password_success');
    		msgSaveFail = $translate.instant('profile_msg_change_password_fail');
    		msgConfirmFail = $translate.instant('profile_msg_confirm_password_fail');
        });
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
		if($scope.frmProfile.$invalid) {
			$scope.frmProfile.$dirty = true;
			return;
		}
		var result;
		if(id === -1) {
			result = profileService.add($scope.profile);
		} else {
			$scope.profile.id = id;
			result = profileService.update(id, $scope.profile);
		}
		var profileId = id;
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				if(profileId === -1) {
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
			var result = profileService.delete(id)
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
		profileService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.profiles) {
				$scope.profile = response.result.profiles[0];
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
		profileService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.profile)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.profiles) {
				$scope.profiles = response.result.profiles;
				$scope.totalCount = response.result.profiles[0].totalCount;
			} else {
				$window.alert('failed');
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$window.alert('error');
		});
	}
	
	$scope.changePassword = function(){
		var lan = $location.search().lang;
		if(lan!=undefined)
			setLang(lan);
		else setLang('en');
		if($scope.newPassword == $scope.confirmPassword)
			profileService.changePassword($scope);
		else
			$scope.confirmPasswordError= msgConfirmFail;
	}
	
	$scope.$on('updatePasswordAlready', function() {
		if ($scope.data == 1){
			alert(msgSaveSuccess);
			$window.location.href = "/redsun/login";
		}
		else
			$scope.currentPasswordError = msgSaveFail;
	});
	
	/*Extend functions*/
	
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
	
}]);