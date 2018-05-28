app.factory('docManagementService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	var directoryIds = '';
	var node = {};
	return {
		getDirectory : function($scope) {
			var url = $rootScope.makeGetURL("/directory/list");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDirectoryAlready');
			});
			return true;
		},
		getDefaultDirectory : function($scope) {
			var url = $rootScope.makeGetURL("/directory/listdefault");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDefaultDirectoryAlready');
			});
			return true;
		},
		getDirectoryByUser : function($scope) {
			var url = $rootScope.makeGetURL("/directory/list/" + $scope.userName);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDirectoryByUserAlready');
			});
			return true;
		},
		
		getDirectoryFromTrash : function($scope) {
			var url = $rootScope.makeGetURL("/directory/list/fromtrash");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDirectoryFromTrashAlready');
			});
			return true;
		},
		
		getResourceAssigned : function($scope) {
			var url = $rootScope.makeGetURL("/directory/list/resourceassigned");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourceAssignedAlready');
			});
			return true;
		},
		
		getResourceAssignedByDirectoryId : function($scope) {
			var url = $rootScope.makeGetURL("/directoryresource/list/resourceassignedbyid/" + $scope.directoryId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getResourceAssignedByIdAlready');
			});
			return true;
		},
		
		getDocument : function($scope) {
			var url = $rootScope.makeGetURL("/directory/doc/" + $scope.directoryId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDocumentAlready');
			});
			return true;
		},
		// Insert.
		add: function(directory) {
			var urlServer = $rootScope.makePostURL("/directory/insert");
			return $http.post(urlServer, directory);
		},
		
		addDef: function(directory) {
			var urlServer = $rootScope.makePostURL("/directory/insertdef");
			return $http.post(urlServer, directory);
		}
		
		// Update.
		, update: function(directoryId, directory) {
			var urlServer = $rootScope.makePostURL("/directory/update/" + directoryId);
			return $http.put(urlServer, directory);
		},
		
		updateDef: function(directoryId, directory) {
			var urlServer = $rootScope.makePostURL("/directory/updatedef/" + directoryId);
			return $http.put(urlServer, directory);
		},
		
		moveFolderToTrash: function($scope){
			var urlServer = $rootScope.makePostURL("/directory/updatetrash/" + $scope.directoryId + "/" + $scope.location);
			$http.put(urlServer).then(function(response){
				if(response){
					$scope.data = angular.fromJson(response);
				}
				$scope.$broadcast('moveToTrashSuccess');
			});
			return true;
		},
		
		undoFromTrash: function($scope, directoryIds){
			var urlServer = $rootScope.makePostURL("/directory/undofromtrash/" + directoryIds);
			$http.put(urlServer).then(function(response){
				if(response){
					$scope.data = angular.fromJson(response);
				}
				$scope.$broadcast('undoFromTrashSuccess');
			});
			return true;
		}
		
		// Delete.
		, delete: function(directoryId, location) {
			var urlServer =$rootScope.makePostURL("/directory/delete/" + directoryId + "/" + location);
			return $http.delete(urlServer);
		},
		
		deleteDef: function(directoryId, location) {
			var urlServer =$rootScope.makePostURL("/directory/deletedef/" + directoryId + "/" + location);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(directoryId) {
			var url = $rootScope.makeGetURL("/directory/getbyid/" + directoryId);
			return $http.get(url);
		}
		, getDefById: function(directoryId) {
			var url = $rootScope.makeGetURL("/directory/getdefbyid/" + directoryId);
			return $http.get(url);
		}
		
		// List all projects for selection.
		, listProjectsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-projects");
			return $http.get(url);
		},
		
		getDirectoryIds: function(){
			
			return directoryIds;
		},
		
		setDirectoryIds: function($scope){
			directoryIds =  $scope.ids;
		},
		
		getNode: function(){
			
			return node;
		},
		
		setNode: function(n){
			node = n;
		},
		////////////////////////////////////////
		// Documnet.
		////////////////////////////////////////
		
		
		
		// Insert.
		addDocument: function(document) {
			var urlServer = $rootScope.makePostURL("/document-management/insert");
			return $http.post(urlServer, document);
		}
		
		// Update.
		, updateDocument: function(documentId, document) {
			var urlServer = $rootScope.makePostURL("/document-management/update/" + documentId);
			return $http.put(urlServer, document);
		}
		
		// Delete.
		, deleteDocument: function($scope) {
			var urlServer =$rootScope.makePostURL("/document-management/delete");
			return $http.post(urlServer, $scope.document);
		}
		
		// Get by Id.
		, getDocumentById: function(documentId) {
			var url = $rootScope.makeGetURL("/document-management/getbyid/" + documentId);
			return $http.get(url);
		}

		

		////////////////////////////////////////
		// Upload and download document.
		////////////////////////////////////////

		
		
		// Upload documents.
		, upload: function (documents, location) {
            var request = {
    	            method: 'POST',
    	            url: $rootScope.makePostURL("/document-management/upload") + "&location=" + encodeURIComponent(location),
    	            data: documents,
    	            headers: {
    	                'Content-Type': undefined
    	            }
    	        };
    	        return $http(request);
        }
		, uploadDocuments: function(documents, location) {
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/document-rest/upload-forward") + "&location=" + encodeURIComponent(location),
	            data: documents,
	            headers: {
	                'Content-Type': undefined
	            }
	        };
	        return $http(request);
	    }
		
		// Download documents.
		, downloadDocuments: function($scope) {
			var url = $rootScope.makeGetURL('/directory/download/' + $scope.id + "/" + $scope.fileName+"/");
			return $http.get(url, { responseType: 'arraybuffer' });
		},
		
		// Insert.
		addFolderToResource: function($scope) {
			var urlServer = $rootScope.makePostURL("/directoryresource/insert");
			var data = {'list': $scope.assignToResources}
			return $http.post(urlServer, data);
		},
		
		sendEmail : function($scope) {
			var url = $rootScope.makeGetURL("/document-management/send/document?toEmail="
					+ $scope.toEmail + '&subject=' + $scope.subject + '&content=' + $scope.content);
			return $http.get(url);
		}

	};
});