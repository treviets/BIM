app.factory('taskService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		getTasksOnePrject : function($scope) {
			var url = $rootScope.makeGetURL("/task/listforoneproject/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTasksOnePrjectAlready');
			});

			return true;
		}
	,
	getDocumentVersionByTask : function($scope) {
		var url = $rootScope.makeGetURL("/documentversion/getbytaskid/"
				+ $scope.taskId);
		$http.get(url).then(function(response) {
			if (response.data) {
				$scope.data = angular.fromJson(response.data);
			}
			$scope.$broadcast('getDocumentVersionByTaskAlready');
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
			}
		});
		return false;
	}
		,
		getTaskResources : function($scope) {
			var url = $rootScope.makeGetURL("/taskresources/getbytask/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTaskResourcesAlready');
			});
	
			return true;
		}
		,
		getTaskResourcesOneProject : function($scope) {
			var url = $rootScope.makeGetURL("/taskresources/gettaskresourcesbyproject/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTaskResourcesOneProjectAlready');
			});
	
			return true;
		}
		
		,
		getFilterProjectResources : function($scope) {
			var url = $rootScope.makeGetURL("/projectresource/filterprojectresource/" + $scope.projectId + "/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterProjectResourcesAlready');
			});

			return true;
		}
		,
		getFilterProjectEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/projectequipment/filterprojectequipment/" + $scope.projectId + "/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterProjectEquipmentsAlready');
			});

			return true;
		}
		,
		getFilterProjectMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/projectmaterial/filterprojectmaterial/" + $scope.projectId + "/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getFilterProjectMaterialsAlready');
			});

			return true;
		}
		//get roles
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
		getComments : function($scope) {
			var url = $rootScope.makeGetURL("/comment/getbytaskid/"
					+ $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getCommentsAlready');
			});

			return true;
		}
		,
		getTaskEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/taskequipment/getbytask/" + $scope.taskId + "/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTaskEquipmentsAlready');
			});

			return true;
		}
		,
		getTaskMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/taskmaterial/getbytask/" + $scope.taskId + "/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTaskMaterialsAlready');
			});

			return true;
		},
		getProjectEquipments : function($scope) {
			var url = $rootScope.makeGetURL("/projectequipment/list/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectEquipmentsAlready');
			});

			return true;
		},
		getProjectMaterials : function($scope) {
			var url = $rootScope.makeGetURL("/projectmaterial/list/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectMaterialsAlready');
			});

			return true;
		},
		getTasks : function($scope) {
			var url = $rootScope.makeGetURL("/task/listforoneproject/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTasksAlready');
			});

			return true;
		},
		getByIdTask : function($scope) {
			var url = $rootScope.makeGetURL("/task/getbyid/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					response = angular.fromJson(response);
					$scope.task = response.data.result.tasks;
				}
			});
			return true;
		},
		getTypes : function($scope) {
			var url = $rootScope.makeGetURL("/type/listall?typevalue=Action");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTypesAlready');
			});

			return true;
		}

		,
		getProjects : function($scope) {
			var url = $rootScope.makeGetURL("/project/getbyprojectid/"
					+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getProjectsAlready');
			});

			return true;
		},
		getStatuses : function($scope) {
			var url = $rootScope
					.makeGetURL("/statuses/getbyscope?scopeValue=Task");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getStatusAlready');
			});

			return true;
		},
		getPriorities : function($scope) {
			var url = $rootScope.makeGetURL("/priority/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getPrioritiesAlready');
			});

			return true;
		},
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
		getProjectResources : function($scope) {
			if(!$scope.projectresources){
				var url = $rootScope.makeGetURL("/projectresource/listforoneproject/" + $scope.projectId);
				$http.get(url).then(function(response) {
					if (response.data) {
						$scope.data = angular.fromJson(response.data);
					}
					$scope.$broadcast('getProjectResourcesAlready');
				});
			}
			return true;
		}
		,
		getDocumentByTask : function($scope) {
			var url = $rootScope.makeGetURL("/document/getbytaskid/" + $scope.taskId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDocumentByTaskAlready');
			});

			return true;
		}
		,
		createTask : function($scope) {
			var url = $rootScope.makePostURL("/task/add");
			$http.post(url, $scope.task).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
					$scope.idTask = response.data.result.tasks;
					$rootScope.$broadcast('addTaskAlready');
				}
			});
			return true;
		}
		,
		createTaskResources : function(member){
			var url =$rootScope.makePostURL("/taskresources/add");
			$http.post(url, member).then(function(response) {
				if(response.member){
					member= angular.fromJson(response.member);
				}
			});
			return true;
		}
		,
		createTaskResources1 : function(member1){
			var url =$rootScope.makePostURL("/taskresources/add");
			$http.post(url, member1).then(function(response) {
				if(response.member1){
					member1= angular.fromJson(response.member1);
				}
			});
			return true;
		}
		,
		createEquipment : function(equipment){
			var url =$rootScope.makePostURL("/taskequipment/add");
			$http.post(url, equipment).then(function(response) {
				if(response.equipment){
					equipment= angular.fromJson(response.equipment);
				}
			});
			return true;
		}
		,
		createMaterial : function(material){
			var url =$rootScope.makePostURL("/taskmaterial/add");
			$http.post(url, material).then(function(response) {
				if(response.material){
					material= angular.fromJson(response.material);
				}
			});
			return true;
		}
		
		,
		createResourceTracking : function(taskresourcetracking){
			var url =$rootScope.makePostURL("/taskresourcetracking/add");
			$http.post(url, taskresourcetracking).then(function(response) {
				if(response.taskresourcetracking){
					taskresourcetracking= angular.fromJson(response.taskresourcetracking);
				}
			});
			return true;
		}
		,
		createMaterialTracking: function(taskmaterialtracking){
			var url =$rootScope.makePostURL("/taskmaterialtracking/add");
			$http.post(url, taskmaterialtracking).then(function(response) {
				if(response.taskmaterialtracking){
					taskmaterialtracking= angular.fromJson(response.taskmaterialtracking);
				}
			});
			return true;
		}
		,
		createEquipmentTracking: function(taskequipmenttracking){
			var url =$rootScope.makePostURL("/taskequipmenttracking/add");
			$http.post(url, taskequipmenttracking).then(function(response) {
				if(response.taskequipmenttracking){
					taskequipmenttracking= angular.fromJson(response.taskequipmenttracking);
				}
			});
			return true;
		}
		,
		sendEmail : function(listMail) {
				var url = $rootScope.makeGetURL("/task/send/"+ listMail+"/");
				 $http.post(url);
			return true;
		},
		sendEmailUpdateTask: function(listMail){
			var url = $rootScope.makeGetURL("/task/send/"+ listMail+"/update/");
			 $http.post(url);
		},
		createDocument : function($scope) {
			var url = $rootScope.makePostURL("/document/add");
			$http.post(url, $scope.document).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
					$scope.idDoc = response.data.result.documents;
				}
				$scope.$broadcast('addDocumentAlready');
			});
			return true;
		}
		,
		createDocumentVersion : function($scope, broadCastFlag) {
			var url = $rootScope.makePostURL("/documentversion/add");
			$http.post(url,$scope.docVer).then(function(response) {
				if (response.docVer) {
					$scope.docVer = angular.fromJson(response.docVer);
				}
				if(broadCastFlag){
					$scope.$broadcast('addDocumentVersionAlready');
				}
				
			});
			return true;
		}

		// Update planning
		,
		updateQuantityTM : function(taskmaterials) {
			var materialId  = taskmaterials.id;
			var urlServer = $rootScope.makePostURL("/taskmaterial/updateplanning/" +  materialId);
			$http.put(urlServer, taskmaterials).then(function(response) {
				if(response.taskmaterials){
					taskmaterials = angular.fromJson(response.taskmaterials);
				}
			});
			return true;
		}
		// Update actual material
		,
		updateMaterial : function(updatematerial) {
			var urlServer = $rootScope.makePostURL("/taskmaterial/update/"
					+ updatematerial.id);
			$http.put(urlServer, updatematerial).then(function(response) {
				if (response.updatematerial) {
					$scope.updatematerial = angular.fromJson(response.updatematerial);
				}
			});
			return true;
		}
		,
		updateActualWorkForMember: function(updateActualWorkForMember) {
			var urlServer = $rootScope.makePostURL("/taskresources/update/"
					+ updateActualWorkForMember.id);
			$http.put(urlServer, updateActualWorkForMember).then(function(response) {
				if (response.updateActualWorkForMember) {
					$scope.updateActualWorkForMember = angular.fromJson(response.updateActualWorkForMember);
				}
			});
			return true;
		}
		// delete Material
		,
		deleteTaskMaterial : function(selectedTaskMaterial) {
			var materialId  = selectedTaskMaterial.id;
			var urlServer = $rootScope.makePostURL("/taskmaterial/delete/" + materialId);
			return $http.delete(urlServer);
		}

		// delete task_resources
		,
		deleteTaskResources : function(selectedTaskResource) {
			var hrId  = selectedTaskResource.id;
			var urlServer = $rootScope.makePostURL("/taskresources/delete/" + hrId);
			return $http.delete(urlServer);
		}
		
		// Update task_equipments
		,
		updateQuantityTE : function(taskequipments) {
			var equipmentId  = taskequipments.id;
			var urlServer = $rootScope.makePostURL("/taskequipment/update/" +  equipmentId);
			$http.put(urlServer, taskequipments).then(function(response) {
				if(response.taskequipments){
					taskequipments = angular.fromJson(response.taskequipments);
				}
			});
			return true;
		}
		// delete task_equipments
		,
		deleteTaskEquipment : function(selectedTaskEquipment) {
			var equipmentId  = selectedTaskEquipment.id;
			var urlServer = $rootScope.makePostURL("/taskequipment/delete/" + equipmentId);
			return $http.delete(urlServer);
		}
		// Update.
		,
		updateTask : function($scope) {
			var urlServer = $rootScope.makePostURL("/task/update/"
					+ $scope.taskId);
			$http.put(urlServer, $scope.task).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
					$rootScope.$broadcast('updateTaskAlready');
				}
			});
			return true;
		}
		,
		downloadDocuments: function($scope) {
			var url = $rootScope.makeGetURL('/documentversion/download/' + $scope.id + "/" + $scope.name+"/");
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		,
		// Delete.
		deleteDocument: function($scope) {
			var urlServer =$rootScope.makePostURL("/documentversion/delete");
			return $http.post(urlServer, $scope.document);
		}

	};
});