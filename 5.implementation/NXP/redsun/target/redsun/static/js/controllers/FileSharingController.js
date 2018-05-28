/**
 * Controller for File Sharing
 **/

app.controller('fileSharingController', ['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout','$log',
                              		'$window', 'fileSharingServices', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, fileSharingServices){

	$scope.totalCount = 0;
	$scope.fileSharing = {};
	$scope.fileSharings = [];
	$scope.uploadFile = function(){
		$templateRequest("/redsun/static/partials/social/social-upload-popup.html").then(function(html){
			$compile(html)($scope);
			var uploadFilePopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		          //social upload file
		        	$scope.saveUpload = function(socialFile){
		        		$scope.fileUpload = $scope.project.socialFile;
		        		fileSharingServices.createFileSharing($scope);
		        		$mdDialog.hide();
		        	}
				}
			});
			$mdDialog.show(uploadFilePopup);

		}); 
	};
	$scope.getSocialFileForProject = function(){
		fileSharingServices.getSocialFileForProject($scope);
	};
	$scope.$on('getSocialFileForProjectAlready', function() {
		if ($scope.data) {
			$scope.fileSharings = $scope.data.result.fileSharing;
		}
	});
	// delete Project
	$scope.deleteFileSharing = function(id, index) {
		$scope.fileId= id;
		$scope.index = index;
		if($window.confirm('Do you want to delete file?') == true){		
			fileSharingServices.deleteFileSharing($scope);
		}
	};
}]);