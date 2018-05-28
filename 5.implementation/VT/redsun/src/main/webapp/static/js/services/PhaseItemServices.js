/*
 * Redsun - BPMN
 * Service for Design Process screen
 */

app.factory('phaseItemService', function($http, $rootScope) {
			return {
				getAll: function($scope) {
					var url = $rootScope.makeGetURL("/all");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getPhaseItemAlready');
					});

					return true;
				},
				
				getAllById: function($scope) {
					var url = $rootScope.makeGetURL("/all/" + $scope.projectId);
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getPhaseItemAlready');
					});

					return true;
				},
				
				updatePhase: function($scope) {
					var url = $rootScope.makePostURL("/update/" + $scope.projectId + "/" + $scope.currentStepId + "/" + $scope.actionType);
					$http.put(url, $scope.phaseItemUpdate).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('updatePhaseAlready');
					});

					return true;
				},
				
				uploadFile: function($scope, formData) {
					var url = $rootScope.makePostURL("/design/upfile/" + $scope.phase.docName);
					$http.post(url, formData, {	   
				    	headers : {
				    		'Content-Type' : undefined
				    	}}).success(function() {
				    		console.log('success in upload file');
				    		$rootScope.create($scope);
				    	}).error(function() {
				    		console.log('error in upload file');
				    		create($scope);
				    	});
					return true;
				},
				
				getFile: function($scope) {
					return true;
				},
				
				deleteFile: function($scope) {
					var url = $rootScope.makePostURL("/design/deletefile/");
					
					$http.post(url, $scope.deleteDocument).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('deleteDocumentSuccessfully');
					});
					return true;
				},
				
				getProjects : function($scope) {
					var url = $rootScope.makeGetURL("/project/list");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getProjectsAlready');
					});

					return true;
				},
				
				// Get Document for project
				getDocuments: function($scope, projectId) {
					var url = $rootScope.makeGetURL("/design/allfile/" + projectId);
					return $http.get(url);
				},
				
				getUploadedDocuments: function($scope) {
					var url = $rootScope.makeGetURL("/design/allfile/" + $scope.projectId);
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getUploadedDocumentAlready');
					});

					return true;
				},
				
				addDocument: function($scope) {
					var url = $rootScope.makePostURL("/design/addfile");
					$http.post(url, $scope.createdDocument).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
					});
					return true;
				},
				
				deleteDocument: function($scope) {
					var url = $rootScope.makePostURL("/design/document/delete");
					$http.post(url, $scope.objDeleteDocument).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('deleteDocumentForStepSuccessfully');
					});
					return true;
				},
				
				getSelectedResources: function($scope, $rootScope) {
					var url = $rootScope.makeGetURL("/design/selected/resource/" + $rootScope.projectId + "/" + $rootScope.stepId);
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getSelectedResourcesAlready');
					});

					return true;
				},
				
				getProjectResources: function($scope,$rootScope) {
					var url = $rootScope.makeGetURL("/projectresource/listforoneproject/" + $rootScope.projectId);
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
					}).finally(function() {
						$scope.$broadcast('getProjectResourcesAlready');
				    });

					return true;
				},
				
				getProjectDocuments: function($scope) {
					var url = $rootScope.makeGetURL("/design/document/" + $scope.projectId + "/" + $rootScope.stepId);
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getProjectDocumentAlready');
					});

					return true;
				},
				
				getStandardDiagram: function($scope) {
					var url = $rootScope.makeGetURL("/design/diagram/standard");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getStandardDiagramAlready');
					});

					return true;
				},
				
				getCustomDiagram: function($scope) {
					var url = $rootScope.makeGetURL("/design/diagram/custom");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getCustomDiagramAlready');
					});

					return true;
				},
				
				getStandardProcess: function($scope) {
					var url = $rootScope.makeGetURL("/design/process/standard");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getStandardProcessAlready');
					});

					return true;
				},
				
				getCustomProcess: function($scope) {
					var url = $rootScope.makeGetURL("/design/process/custom");
					$http.get(url).then(function(response) {
						if (response.data) {
							$scope.data = angular.fromJson(response.data);
						}
						$scope.$broadcast('getCustomProcessAlready');
					});

					return true;
				}
				
			}
	});
