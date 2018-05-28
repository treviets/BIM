app.factory('fileSharingServices', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		createFileSharing : function($scope) {
			var url = $rootScope.makePostURL("/file-sharing/upfile/" + $scope.projectId+"/"+$scope.project.title+"/"+$scope.project.filedescription+"/"+$scope.selectedGroup.id+"/");
			var formData = new FormData();
			formData.append('file', $scope.fileUpload);
        	
        	$http.post(url, formData, {headers : {'Content-Type' : undefined}})
        	.then(function(response) {
				if (response.data.result) {
					$scope.fileSharing = angular.fromJson(response.data.result.fileSharing);
					$scope.fileSharings.push($scope.fileSharing);
					$scope.project.title = "";
					$scope.project.filedescription = "";
				}
			});
			return true;
			
		},
		getSocialFileForProject : function($scope) {
			var url = $rootScope.makeGetURL("/file-sharing/list-by-group/" + $scope.projectId + "/" + $scope.selectedGroup.id+ "/");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getSocialFileForProjectAlready');
			});

			return true;
		},
		// Delete.
		deleteFileSharing: function($scope) {
			var urlServer = $rootScope.makePostURL("/file-sharing/delete/" + $scope.fileId);
			return $http.delete(urlServer).then(function(response){
				$scope.fileSharings.splice($scope.index, 1);
			});
		}
	};
	
});