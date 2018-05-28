app.factory('systemService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
			return {
				getRole : function($scope ) {
					var url = $rootScope.makeGetURL("/system/get/role/"+$scope.selectedRoleId+"/");
					$http.get(url).then(function(response) {
						$scope.selectedRole = null;
						if(response.data){
							var jsonData = angular.fromJson(response.data);
						
							$scope.newRole.id = jsonData.id;
							$scope.newRole.name = jsonData.name;
							$scope.newRole.description = jsonData.description;
							$scope.newRole.menus = jsonData.menus;
							$scope.backupRole = {};
							angular.copy(angular.fromJson(response.data), $scope.backupRole);
							// reset tempMenuRole property
							for(var propertyName in $scope.newRole.tempMenus){
								if($scope.newRole.tempMenus.hasOwnProperty(propertyName)){
									var tempMItem = $scope.newRole.tempMenus[propertyName];
									var menuId = tempMItem.menuId;
									$scope.newRole.tempMenus[propertyName] ={
											menuId: menuId 
									};
									
								}
								 
							}
							// update role permission menu;
							for(var i =0; i< $scope.newRole.menus.length; i++){
								var menuItem = $scope.newRole.menus[i];
								for(var propertyName in $scope.newRole.tempMenus){
									if($scope.newRole.tempMenus.hasOwnProperty(propertyName)){
										var tempMItem = $scope.newRole.tempMenus[propertyName];
										if(menuItem.menuId == tempMItem.menuId){
											if(menuItem.permission.indexOf('1')>=0){
												tempMItem.read=1;
											}
											if(menuItem.permission.indexOf('2')>=0){
												tempMItem.create=2;
											}
											if(menuItem.permission.indexOf('3')>=0){
												tempMItem.update=3;
											}
											if(menuItem.permission.indexOf('4')>=0){
												tempMItem["delete"]=4;
											}
											break;
										}
									}
								}

							}
							
						}
						
						$scope.$broadcast('loadRoleDataAlready');
					});
					
					return false;
				},
				getUser : function($scope ) {
					var url = $rootScope.makeGetURL("/system/get/user/"+$scope.selectedUsername+"/");
					$http.get(url).then(function(response) {
						if(response.data){
							$scope.user = angular.fromJson(response.data);
							$scope.backupUser = {};
							angular.copy(angular.fromJson(response.data), $scope.backupUser);
						}
						
						$scope.$broadcast('loadUserDataAlready');
					});
					
					return false;
				},
				addUser : function($scope ) {
					var url = $rootScope.makePostURL("/system/add/user");
					$http({
						method  : 'POST',
						url     : url,
						data    : $.param($scope.user),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					 }).success(function(response) {
						$scope.addUserResult = response;
					}).finally(function() {
						$scope.$broadcast('addUserAlready');
					});
					
					return false;
				},
				updateUser : function($scope ) {
					var url = $rootScope.makePostURL("/system/edit/user");
					$http({
						method  : 'POST',
						url     : url,
						data    : $.param($scope.user),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					 }).success(function(response) {
						 $scope.editUserResult = response;
					}).finally(function() {
						$scope.$broadcast('updateUserAlready');
					});
					
					return false;
				},
				// update menu
				updateMenu: function($scope ) {
					var url = $rootScope.makePostURL("/system/edit/menu");
					$http({
						method  : 'POST',
						url     : url,
						data    : $.param($scope.menu),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					 }).success(function(response) {
						 $scope.editMenuResult = response;
					}).finally(function() {
						$scope.$broadcast('updateMenuAlready');
					});
					
					return false;
				},

				// add menu
				addMenu : function($scope ) {
					var url = $rootScope.makePostURL("/system/add/menu");
					$http({
						method  : 'POST',
						url     : url,
						data    : $.param($scope.menu),
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
					 }).success(function(response) {
						$scope.addMenuResult = response;
					}).finally(function() {
						$scope.$broadcast('addMenuAlready');
					});
					
					return false;
				},
				checkUsernameExisting : function($scope ) {
					var url = $rootScope.makeGetURL("/system/get/user/"+$scope.user.username+"/");
					$http.get(url).then(function(response) {
						$scope.checkUserResult = 0;
						if(response.data){
							$scope.checkUserResult = 1;
						}
						
						$scope.$broadcast('checkUserAlready');
					});					
					return false;
				},
				getMenu : function($scope ) {
					var url = $rootScope.makeGetURL("/system/get/menu/"+$scope.selectedMenuId+"/");
					$http.get(url).then(function(response) {
						if(response.data){
							$scope.menu = angular.fromJson(response.data);
						}
						
						$scope.$broadcast('loadMenuDataAlready');
					});
					
					return false;
				},
				
				addRole: function($scope ){
					var url = $rootScope.makePostURL("/system/add/role");
					$http({
						method: "POST",
						url : url,
						data: JSON.stringify($scope.newRole),
						headers: {'Content-Type': 'application/json', 'dataType': 'json',}
					}).success(function(response){
						$scope.addNewRoleResult = response;
					}).finally(function(){
						$scope.$broadcast('addRoleAlready');
					});
					return false;
				},
				updateRole: function($scope ){
					var url = $rootScope.makePostURL("/system/edit/role");
					$http({
						method: "POST",
						url : url,
						data: JSON.stringify($scope.newRole),
						headers: {'Content-Type': 'application/json', 'dataType': 'json',}
					}).success(function(response){
						$scope.addNewRoleResult = response;
					}).finally(function(){
						$scope.$broadcast('updateRoleAlready');
					});
					return false;
				},
				getMenuList : function($scope ) {
					var url = $rootScope.makeGetURL("/system/list/menus");
					$http.get(url).then(function(response) {
						if(response.data){
							$scope.data = response.data;
						}
						
						$scope.$broadcast('getMenuListAlready');
					});
					
					return false;
				}
			};
	});