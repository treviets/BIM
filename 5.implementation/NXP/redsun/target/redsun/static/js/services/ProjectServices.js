app.factory('projectService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getProjects : function($scope) {
			var url = $rootScope.makeGetURL("/project/list/100/1");
			if($rootScope.isDesignManagement){
				url = $rootScope.makeGetURL("/project/design/list/100/1");
			}
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectsAlready');
			});
			return true;
		}
		,
		getResources : function($scope) {
			var url = $rootScope.makeGetURL("/hr/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourcesAlready');
			});
	
			return true;
		}
		
		,
		getWorkers : function($scope) {
			var url = $rootScope.makeGetURL("/hr/filtertitle");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getWorkerAlready');
			});
	
			return true;
		}
		,
		getRoles : function($scope) {
			var url = $rootScope.makeGetURL("/role/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getRolesAlready');
			});
	
			return true;
		}
		,
		getFilterMember : function($scope) {
			var url = $rootScope.makeGetURL("/hr/filtermember/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterMemberAlready');
			});

			return true;
		}
		,
		getFilterEquipment : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/filterequipment/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterEquipmentAlready');
			});

			return true;
		}
		,
		getFilterMaterial : function($scope) {
			var url = $rootScope.makeGetURL("/material/filtermaterial/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterMaterialAlready');
			});

			return true;
		}
		,
		getEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/equipment/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getEquipmentsAlready');
			});
	
			return true;
		}
		,
		getProjectEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/projectequipment/list/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectEquipmentsAlready');
			});
	
			return true;
		}
		,
		getProjectPermissionsForAllUser: function($scope){
			var url = $rootScope.makeGetURL("/project/permissions/" +$scope.projectId);
			$http.get(url).then(function(response){
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectPermissionsForAllUserAlready');
			});
			return true;
		}
		,
		getMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/material/listall");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMaterialsAlready');
			});
	
			return true;
		}
		,
		getProjectMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/projectmaterial/list/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectMaterialsAlready');
			});
	
			return true;
		}
		,
		getByIdProject : function($scope) {
			var url = $rootScope.makeGetURL("/project/getbyid/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					response = angular.fromJson(response);
					$scope.project = response.data.result.projects;
					$rootScope.projectId = $scope.projectId;
				}
				$scope.$broadcast('getProjectAlready');
			});
			return false;
		}
		,
		getCustomers : function($scope) {
			var url = $rootScope.makeGetURL("/client/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getCustomersAlready');
			});
	
			return true;
		}
		,
		getProjectResources : function($scope) {
			var url = $rootScope.makeGetURL("/projectresource/listforoneproject/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectResourcesAlready');
			});

			return true;
		}
		,
		getParentsProject : function($scope) {
			var url = $rootScope.makeGetURL("/project/list");
			if($rootScope.isDesignManagement){
				url = $rootScope.makeGetURL("/project/design/list/100/1");
			}
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getParentsProjectAlready');
			});

			return true;
		}
		,
		getStatuses : function($scope) {
			var url = $rootScope.makeGetURL("/statuses/getbyscope?scopeValue=Project");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getStatusAlready');
			});
	
			return true;
		}
		,
		getTypes : function($scope) {
			var url = $rootScope.makeGetURL("/type/listall?typevalue=Project");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTypesAlready');
			});
	
			return true;
		}
		,
		getAllProject : function($scope) {
			var url = $rootScope.makeGetURL("/project/list");
			if($rootScope.isDesignManagement){
				url = $rootScope.makeGetURL("/project/design/list/100/1");
			}
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getAllAlready');
			});
	
			return true;
		}
		,
		createProject : function($scope){
			var url = $rootScope.makePostURL("/project/add");
			if($rootScope.isDesignManagement){
				url = $rootScope.makeGetURL("/project/design/add");
			}
			$http.post(url, $scope.project).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
					$scope.proId = response.data.result.projects;
					$scope.$broadcast('addProjectAlready');
				}
			});
			return true;
		}
		
		,
		createMember : function(member){
			var url =$rootScope.makePostURL("/projectresource/add");
			$http.post(url, member).then(function(response) {
				if(response.member){
					member= angular.fromJson(response.member);
				}
			});
			return true;
		}
		,
		createEquipment : function(equipment){
			var url =$rootScope.makePostURL("/projectequipment/add");
			$http.post(url, equipment).then(function(response) {
				if(response.equipment){
					equipment= angular.fromJson(response.equipment);
				}
			});
			return true;
		}
		,
		createMaterial : function(material){
			var url =$rootScope.makePostURL("/projectmaterial/add");
			$http.post(url, material).then(function(response) {
				if(response.material){
					material= angular.fromJson(response.material);
				}
			});
			return true;
		}
		// Update.
		,
		updateProject : function($scope) {
			var urlServer = $rootScope.makePostURL("/project/update/" +  $scope.project.id);
			$http.put(urlServer, $scope.project).then(function(response) {
				if(response.data){
					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		// Update quantity HR.
		,
		updateQuantityPHR : function(projectresources) {
			var hrId  = projectresources.id;
			var urlServer = $rootScope.makePostURL("/projectresource/updatehr/" +  hrId);
			$http.put(urlServer, projectresources).then(function(response) {
				if(response.projectresources){
					projectresources = angular.fromJson(response.projectresources);
				}
			});
			return true;
		}

		// delete project_resources
		,
		deleteProjectResources : function(selectedProjectResource) {
			var hrId  = selectedProjectResource.id;
			var urlServer = $rootScope.makePostURL("/projectresource/delete/" + hrId);
			return $http.delete(urlServer);
		}
		// Update quantity Equipment.
		,
		updateQuantityPE : function(projectequipments) {
			var equipmentId  = projectequipments.id;
			var urlServer = $rootScope.makePostURL("/projectequipment/update/" +  equipmentId);
			$http.put(urlServer, projectequipments).then(function(response) {
				if(response.projectequipments){
					projectequipments = angular.fromJson(response.projectequipments);
				}
			});
			return true;
		}
		// delete project_equipment
		,
		deleteProjectEquipments : function(selectedProjectEquipment) {
			var equipmentId  = selectedProjectEquipment.id;
			var urlServer = $rootScope.makePostURL("/projectequipment/delete/" + equipmentId);
			return $http.delete(urlServer);
		}
		// Update quantity material.
		,
		updateQuantityPM : function(projectmaterials) {
			var materialsId  = projectmaterials.id;
			var urlServer = $rootScope.makePostURL("/projectmaterial/update/" +  materialsId);
			$http.put(urlServer, projectmaterials).then(function(response) {
				if(response.projectmaterials){
					projectmaterials = angular.fromJson(response.projectmaterials);
				}
			});
			return true;
		}
		// delete project_material
		,
		deleteProjectMaterials : function(selectedProjectMaterial) {
			var materialsId  = selectedProjectMaterial.id;
			var urlServer = $rootScope.makePostURL("/projectmaterial/delete/" + materialsId);
			return $http.delete(urlServer);
		}
		
		// delete project
		,
		deleteProject : function($scope) {
			var urlServer = $rootScope.makePostURL("/project/delete/" +  $scope.project.id);
			return $http.delete(urlServer);
		}
		,
		getProjectGanttData : function($scope) {
			var url = $rootScope.makeGetURL("/project/ganttchart/data/"+$scope.projectId);
			$http.get(url).then(function(response) {
				$scope.taskData = null;
				if(response.data){
					$scope.taskData = angular.fromJson(response.data).result.taskData;
				}
				
				$scope.$broadcast('getProjectGanttChartDataAlready');
			});
			
			return true;
		}
		,
		createDocumentVersion : function(docVer) {
			var url = $rootScope.makePostURL("/documentversion/add");
			$http.post(url, docVer).then(function(response) {
				if (response.docVer) {
					docVer = angular.fromJson(response.docVer);
				}
			});
			return true;
		}
		
	};
	
});