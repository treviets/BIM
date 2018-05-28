
/**
 * Controller for ${entity.name}
 */

app.factory('documentService', function($http, $rootScope) {
	return {
		
		//---------------------------Document----------------------------//
		getDocumentsOneProject : function($scope) {
			var url = $rootScope.makeGetURL("/document/list/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDocumentsOnePrjectAlready');
			});

			return true;
		}
		//add document folder
		,
		createDocument : function($scope) {
			var url = $rootScope.makePostURL("/document/add");
			$http.post(url, $scope.document).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
					$scope.$broadcast('createDocumentAlready');
				}
			});
			return true;
		}
		
		//---------------------------Document Version----------------------------//
		,
		getByCodeDocument : function($scope) {
			var url = $rootScope.makeGetURL("/documentversion/getbydocumentid/"+ $scope.docId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getByCodeDocumentAlready');
				$scope.$broadcast('getDocVersionAlready');
			});
			return true;
		},
		getDocumentVersions : function($scope) {
			var url = $rootScope.makeGetURL("/documentversion/list/"+ $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getDocumentVersionsAlready');
			});

			return true;
		}
		
		,
		createDocumentVersion : function($scope) {
			var url = $rootScope.makePostURL("/documentversion/add");
			$http.post(url, $scope.docversion).then(function(response) {
				if (response.data) {

					$scope.data = angular.fromJson(response.data);
				}
			});
			return true;
		}
		
		// Get by Id.
		,
		getByIdDocVersion : function($scope) {
			var url = $rootScope.makeGetURL("/documentversion/getbyid/"
					+ $scope.docVersionId);
			$http.get(url).then(function(response) {
				if (response.data) {
					response = angular.fromJson(response);
					$scope.docversion = response.data.result.documentversions;
				}
			});
			return false;
		}
		
		//---------------------------extra----------------------------//
		,
		getTypes : function($scope) {
			var url = $rootScope.makeGetURL("/type/listall?typevalue=Document");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getTypesAlready');
			});

			return true;
		}
		,
		getStatuses : function($scope) {
			var url = $rootScope.makeGetURL("/statuses/getbyscope?scopeValue=Document");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getStatusAlready');
			});

			return true;
		}
		,
		// Download documents.
		downloadDocuments: function($scope) {
			var url = $rootScope.makeGetURL('/documentversion/download/' + $scope.id + "/" + $scope.fileName);
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		,
		// Delete.
		deleteDocument: function($scope) {
			var urlServer =$rootScope.makePostURL("/documentversion/delete");
			return $http.post(urlServer, $scope.document);
		}
	}
});