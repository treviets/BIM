app.controller('memberController', ['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                            		'$window', 'memberService', 'moduleService', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, memberService, moduleService) {
	$scope.moduleProperties = [];
	$scope.moduleRoles ={};
	$scope.checkAll = [];
	//get member for one the project
	$scope.getMemberOneProject = function(id) {
		$scope.projectId = id;
		memberService.getMemberOneProject($scope);
		$scope.getProjectRoles();
	};

	$scope.$on('getMemberOneProjectAlready', function() {
		if($scope.data) {
			$rootScope.projectresources = $scope.data.result.projectresources;
			
			$scope.getUserModules();
		}
	});
	// get user module.
	$scope.getUserModules = function(){
		for(var i in $rootScope.projectresources){
			moduleService.getModuleRoleForUser($scope, $rootScope.projectresources[i].resourceCode);
		}
	}
	$scope.$on('getModuleRoleForUserAlready', function(){
		if($scope.data){
			$scope.moduleRoles[$scope.data.username] = $scope.data.modulePermission; 
		}
	})
	//get Roles
	$scope.getRoles = function() {
		memberService.getRoles($scope);
	};
	$scope.$on('getRolesAlready', function() {
		if ($scope.data) {
			$scope.roles = $scope.data.result.roles;
		}
	});
	// get filter resource
	$scope.getFilterMember = function() {
		memberService.getFilterMember($scope);
	};
	$scope.$on('getFilterMemberAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	
	$scope.addMember = function(){
		$templateRequest("/redsun/static/partials/member-add.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Add new member";
			$scope.member = [];
			$scope.congifuration = [];
			var addMemberPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveMember = function(){
		            	var selected = $.grep($scope.resources, function(p1, p2) {
		            		return p1.isSelected;
		            	});
		            	for(var i=0;i<selected.length;i++){
		            		var resourceId = selected[i].id;
		            		var clientId = selected[i].clientId;
		            		$scope.member.push({projectId: $scope.projectId, resourceId: selected[i].id, clientId: selected[i].clientId});
		            		$scope.congifuration.push({projectId: $scope.projectId, level: selected[i].level, factor:1, clientId: selected[i].clientId })
		            	}
		            	if (memberService.create($scope.member)){
		            		memberService.createConfigurations($scope.congifuration);
		            		$window.location.href = $rootScope.makeGetURL("/project/detail/" + $scope.projectId);
		            	}
		            }
				}
			});
			$mdDialog.show(addMemberPopup);
			
		});
	};
	$scope.showProjectRole = function(){
		// get project roles
		$scope.getProjectRoles();
		
		$scope.getModuleProperties('project');
		
		$templateRequest("/redsun/static/partials/project-role.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Project Role";
			$scope.projectRoles = [];
			$scope.congifuration = [];
			var projectRolePopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		            //refresh project role
		            $scope.getProjectRoles();
		              $mdDialog.hide();
		            }
				}
			});
			$mdDialog.show(projectRolePopup);
			
		});
		
	};
	//get Project Role
	$scope.getProjectRoles = function() {
		var key = "project_" + $scope.projectId;
		$scope.module_permission_key = key;
		moduleService.getModulePermission($scope,key);
	};
	// get module properties
	$scope.getModuleProperties = function(moduleName){
		  
		moduleService.getModuleProperties($scope, moduleName);
	}
	$scope.$on('getModulePropertisAlready', function(){
		if($scope.data){
			$scope.moduleProperties = $scope.data;
			
		}
	});
	
	$scope.$on('getModulePermissionAlready', function() {
		if ($scope.data) {
			var tempData = $scope.data;
			$scope.projectRoles = [];
			
			var perName = "";
			var modulePermission = null;
			for(var i = 0; i< tempData.length; i++){
				
				// new permission
				if(tempData[i].name != perName){
					perName = tempData[i].name;
					if(modulePermission !=null){
						$scope.projectRoles.push(modulePermission);
					}
					
					modulePermission = {};
					modulePermission.id = tempData[i].id;
					modulePermission.name = tempData[i].name;
					modulePermission.key = tempData[i].key;
					modulePermission[tempData[i].moduleProperty.item] = tempData[i].permission;
				}else{
					modulePermission[tempData[i].moduleProperty.item] = tempData[i].permission;
				}
			}
			// add last object
			if(modulePermission !=null){
				$scope.projectRoles.push(modulePermission);
			}
		}
	});
	
	// add new role to project role
	$scope.addProjectRole = function(){
		var role = {};
		for(var i =0; i < $scope.moduleProperties.length; i++){
			role[$scope.moduleProperties[i].item] ='';
		}
				
		$scope.projectRoles.push(role);
	}
	
	// save project role
	$scope.saveProjectRole = function(projectRole){
		var modulePermissionList = [];
		
		for (var property in projectRole) {
			
			if (projectRole.hasOwnProperty(property) && property !="$$hashKey") {
		    	var modulePermission = {};
				var moduleProperty = {};
				moduleProperty.name = "project";
		    	moduleProperty.item = property;
		    	
		    	modulePermission.id = projectRole.id;
		    	modulePermission.name = projectRole.name;
		    	modulePermission.permission = projectRole[property];
		    	modulePermission.moduleProperty = moduleProperty;
		    	modulePermission.key = $scope.projectId;
		    	modulePermissionList.push(modulePermission);
		    }
		}
		
		moduleService.saveModulePermission($scope, modulePermissionList);
	}
	$scope.deleteProjectRole = function(modulePermission){
		if(modulePermission.id >0){
			moduleService.deleteModulePermission($scope,modulePermission.id);
		}
		
	}
	$scope.isProjectRolePsCheck = function(value, vCheck){
		if(value == null){
			value = "";
		}
		if(value.indexOf(vCheck)>=0){
			return true;
		}else{
			return false;
		}
	}
	
	$scope.projectRolePsChangeAll = function(projectRole,key,bool){
		$scope.projectRolePsChange(projectRole,key,bool, 1);
		$scope.projectRolePsChange(projectRole,key,bool, 2);
		$scope.projectRolePsChange(projectRole,key,bool, 3);
		$scope.projectRolePsChange(projectRole,key,bool, 4);
	}
	$scope.projectRolePsChange = function(projectRole,key,bool, value){
		var v = value + "|";
		if(projectRole[key]== null){
			projectRole[key] ="";
		}
		if(bool){
			// check if exist or not
			if(projectRole[key].indexOf(v)<0){
				projectRole[key] = v + projectRole[key];
			}
			 
		}else{
			$scope.checkAll[key] = false;
			projectRole[key] = projectRole[key].replace(new RegExp(v, 'g'), '');
		}
	}
	 $scope.$on("saveModulePermissionAlready", function(){
		  $scope.closeDialog();
		  $scope.showProjectRole();
	 })
	 $scope.$on("deleteModulePermissionAlready", function(){
		  $scope.closeDialog();
		  $scope.showProjectRole();
	  })
}]);
