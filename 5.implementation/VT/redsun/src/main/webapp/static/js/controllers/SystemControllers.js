app.controller('systemCtrl', ['$scope', '$rootScope', '$http', '$mdDialog', '$window', 'systemService', function($scope, $rootScope, $http, $mdDialog, $window, systemService) {

	var init = function() {
		$scope.user={};
		$scope.role={};
		$scope.menu={};
		$scope.role.permission={};
		$scope.editMode= false;
		$scope.menuEditMode= false;
		$scope.newRole = {};
	};
	init();
	
	$scope.$on('getResourcesForSystemAlready', function() {
		if ($scope.data) {
			$scope.resourcesForSystem = $scope.data.result.resources;
		}
	});
	$scope.getMenuList = function(){
		systemService.getMenuList($scope);
	};

	$scope.addUser = function(){
		var hasNoError = true;
		// initial data
		$scope.usernameError = '';
		$scope.passwordError = '';
		$scope.confirmPasswordError = '';
		$scope.rolesError = '';
		var errorClass = 'has-error';
		
		// check username
		if(!$scope.user.username){
			$scope.usernameError = errorClass;
			hasNoError = false;
		}
		// check password
		if(!$scope.user.password){
			$scope.passwordError = errorClass;
			hasNoError = false;
		}
		// check confirmPassword
		if(!$scope.user.confirmPassword){
			$scope.confirmPasswordError = errorClass;
			hasNoError = false;
		}
		// check confirmPassword is match
		if($scope.user.password != $scope.user.confirmPassword){
			$scope.passwordError = errorClass;
			$scope.confirmPasswordError = errorClass;
			hasNoError = false;
		}
		
		// check role
		if(!$scope.user.role){
			$scope.rolesError = errorClass;
			hasNoError = false;
		}
		if(hasNoError){
			if(!$scope.editMode){
				systemService.checkUsernameExisting($scope);
			} else {
				if($scope.backupUser.username == $scope.user.username){
					systemService.updateUser($scope);
				} else {
					$scope.usernameError = errorClass;
				}
				
			}
			
		}
	};
	$scope.editUser = function(username){
		$scope.selectedUsername = username;
		systemService.getUser($scope);
	};
	
	$scope.editRole = function(roleId){
		$window.scrollTo(0,0);
		$scope.selectedRoleId = roleId;
		systemService.getRole($scope);
	};
	
	$scope.exitEditMode = function(){
		$scope.editMode= false;
		$scope.user = {};
	}
	
	$scope.$on("loadUserDataAlready",function(){
		$scope.editMode = true;
	});
	
	$scope.$on("loadRoleDataAlready",function(){
		$scope.roleEditMode = true;
	});
	
	$scope.$on("loadMenuDataAlready",function(){
		$scope.menuEditMode = true;
	});
	$scope.exitMenuEditMode = function(){
		$scope.menuEditMode= false;
		$scope.user = {};
	}
	$scope.$on("updateUserAlready",function(){
		var message="Cannot update this user";
		if($scope.editUserResult){
			message = "Update user Successful";
		}
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
		
	});
	$scope.$on("addUserAlready",function(){
		var message="Cannot create this user";
		if($scope.addUserResult){
			message = "Add user Successful";
		}
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
	});
	$scope.$on("addMenuAlready",function(){
		var message="Cannot create this menu";
		if($scope.addMenuResult){
			message = "Add Menu Successful";
		}
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
	});
	$scope.$on("updateMenuAlready",function(){
		var message="Cannot update this menu";
		if($scope.editMenuResult){
			message = "Update menu Successful";
		}
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
		
	});
	$scope.$on("checkUserAlready",function(){
		
		if($scope.checkUserResult){
			alert("Duplicate Username");
		} else {
			systemService.addUser($scope);
		}
	});
	$scope.addRole = function(){
		var hasNoError = true;
		var errorClass = 'has-error';
		// initial data
		$scope.roleNameError = '';
		// check name
		if(!$scope.newRole.name){
			$scope.roleNameError = errorClass;
			hasNoError = false;
		}
		
		$scope.newRole.menus =[];
		angular.forEach($scope.newRole.tempMenus, function(menuItem, key) {
			menuItem.permission ="";
			if(menuItem.read >0){
				menuItem.permission += menuItem.read +"|";
			}
			if(menuItem.create >0){
				menuItem.permission += menuItem.create +"|";
			}
			if(menuItem.update >0){
				menuItem.permission += menuItem.update +"|";
			}
			if(menuItem["delete"] >0){
				menuItem.permission += menuItem["delete"];
			}
			$scope.newRole.menus.push(menuItem);
		});

		
		if(hasNoError){
			// edit mode in role.
			if(!$scope.roleEditMode){
				systemService.addRole($scope);
			}else{
				systemService.updateRole($scope);
			}
		}
	};
	$scope.editMenu = function(id){
		$scope.selectedMenuId = id;
		systemService.getMenu($scope);
	};
	$scope.addMenu = function(){
		var hasNoError = true;
		$scope.menuNameError = '';
		$scope.menuDescriptionError = '';
		$scope.menuUrlError = '';
		$scope.menuIconClassError ='';
		$scope.menuPositionError = '';
		var errorClass = "has-error";
		
		// check name
		if(!$scope.menu.name){
			$scope.menuNameError = errorClass;
			hasNoError = false;
		}
		
		// check description
		if(!$scope.menu.description){
			$scope.menuDescriptionError = errorClass;
			hasNoError = false;
		}
		// check url
		if(!$scope.menu.url){
			$scope.menuUrlError = errorClass;
			hasNoError = false;
		}
		// check iconclass
		if(!$scope.menu.iconClass){
			$scope.menuIconClassError = errorClass;
			hasNoError = false;
		}
		// check position
		if(parseInt($scope.menu.position) <0){
			$scope.menu.position = errorClass;
			hasNoError = false;
		}
		if(hasNoError){
			if(!$scope.menuEditMode){
				systemService.addMenu($scope);
			}else{
				systemService.updateMenu($scope);
			}
		}
	};
	
	$scope.$on("addRoleAlready",function(){
		var message="Cannot add this role";
		if($scope.addNewRoleResult>0){
			message = "Add role Successful";
		}
		
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
		
	});
	$scope.$on("updateRoleAlready",function(){
		var message="Cannot add this role";
		if($scope.addNewRoleResult>0){
			message = "Update role Successful";
		}
		
		var alert = $mdDialog.alert({
	        title: 'Result',
	        textContent: message,
	        ok: 'OK'
	    });
		$mdDialog.show(alert)
		.finally(function() {
			$window.location.reload();
        });
		
	});
}]);