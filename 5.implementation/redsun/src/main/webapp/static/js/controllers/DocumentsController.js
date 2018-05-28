

/**
 * Controller for Documents
 **/

app.controller('documentController', ['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout','$log', '$location', '$translate',
	'$mdDialog', '$window', 'documentService', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout, $log,
                              				$location,  $translate, $mdDialog, $window, documentService){

	$scope.totalCount = 0;
	$scope.document = {};
	var msgTitle = '';
	var msgContent = '';
	var msgOk = '';
	var msgCancel = '';
	var msgDelSuccess = '';
	var msgDelFail = '';
	var msgDelErr = '';
	
	//---------------------------document----------------------------//
	
	//get document for one the project
	$scope.getDocumentsOneProject = function(id) {
		$scope.projectId = id;
		documentService.getDocumentsOneProject($scope);
	};

	$scope.$on('getDocumentsOnePrjectAlready', function() {
		if($scope.data) {
			$scope.documents = $scope.data.result.documents;
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
		}
	});
	
	$scope.addDocument = function(){
		$scope.document.statusId = 26;
		$scope.document.typeId = 54;
		$templateRequest("/redsun/static/partials/document-new.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Create folder Document";
			$scope.document.projectId = $scope.projectId;
			
			var addDocumentPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveAddDocument = function(){
		            	if($window.confirm('Do you want to New Document?') == true){	
			            	documentService.createDocument($scope);
			            	$scope.$on('createDocumentAlready', function() {
			            		$scope.docId = $scope.data.result.documents;
			            		documentService.getByCodeDocument($scope);
				            	$mdDialog.hide();
			            	});
			            	
			            	$scope.$on('getByCodeDocumentAlready', function() {
			            		$scope.getDocumentsOneProject($scope.projectId);
			            	});
		            	}
		            }
				}
			});
			$mdDialog.show(addDocumentPopup);
			
		});
	};
	
	//---------------------------document version----------------------------//
	//get document version
	$scope.getDocumentVersions = function(object) {
		$scope.projectId = object.projectId;
		documentService.getDocumentVersions($scope);
	};


	$scope.$on('getDocumentVersionsAlready', function() {
		if ($scope.data) {
			$scope.documentversions = $scope.data.result.documentversions;
			$scope.totalCount = $scope.documentversions[0].totalCount;
		}
	});
	
	$scope.gotoEditDocumentVersion = function(object){
		$scope.docId = object.id;
		$scope.pageTitle = 'Edit version document';
		$templateRequest("/redsun/static/partials/documentversion-add.html").then(function(html){
			$compile(html)($scope);
			var addPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveDocumentVersion = function(){
		            	if($window.confirm('Do you want to Edit Version?') == true){			
		            		var file = $scope.myFile;
		                	/* console.log('file is ' );
		                	console.dir(file);*/
		                	var uploadUrl = "/redsun/documentversion/upfile/"+$scope.docId;
		                	var fd = new FormData();
		                	fd.append('file', file);
		                	
		                	$http.post(uploadUrl, fd, {
		               
		                	headers : {
		                	'Content-Type' : undefined
		                	}
		                	}).success(function() {
		                		$scope.docversion.documentId = $scope.docId ;
		                		$scope.docversion.projectId = $scope.projectId;
		                		documentService.createDocumentVersion($scope);
				            	documentService.getByCodeDocument($scope);
		                	});
		        		}
		            	$mdDialog.hide();
		            }
				}
			});
			documentService.getByCodeDocument($scope);
			$mdDialog.show(addPopup);
			
		});    
	};

	$scope.$on('getDocVersionAlready', function() {
		if ($scope.data) {
			$scope.documentversions = $scope.data.result.documentversions;
		}
	});
	
	// Init for edit.
	$scope.initEditDocumentVersion = function(object) {
		$scope.docVersionId = object.id;
		if($scope.docVersionId > -1) {
			documentService.getByIdDocVersion($scope);
		}
	}
	
	
	//---------------------------extra----------------------------//
	// get types
	$scope.getTypes = function() {
		documentService.getTypes($scope);
	};
	$scope.$on('getTypesAlready', function() {
		if ($scope.data) {
			$scope.types = $scope.data.result.types;
		}
	});
	// get statuses
	$scope.getStatuses = function() {
		documentService.getStatuses($scope);
	};
	$scope.$on('getStatusAlready', function() {
		if ($scope.data) {
			$scope.statuses = $scope.data.result.statuses;
		}
	});
	// Download Documents.
	$scope.downloadDocuments = function(doc) {
		$scope.name = doc.name;
		$scope.id = doc.id;
		var ex = doc.link;
		ex = ex.substring(ex.lastIndexOf("."), ex.length);
		documentService.downloadDocuments($scope)
		// success.
		.success(function(response, status, headers, config) {
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', doc.name + ex);
			downloadLink[0].click();
		})
		// error.
		.error(function(response, status, headers, config) {
			//$scope.showMessage('Error!', 'alert-danger', true);
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', fileName);
			downloadLink[0].click();
		});
	}
	// delete documents.
	$scope.deleteDocument = function(doc) {
		$scope.document = doc;
		if($window.confirm(msgContent) == true){
			documentService.deleteDocument($scope)
				// success.
				.success(function(response, status, headers, config) {
					$scope.$broadcast('deleteDocument', {done: true});	
				})
				// error.
				.error(function(response, status, headers, config) {
					$scope.$broadcast('deleteDocument', {done: false});
				});
		}
	}
	
	$scope.$on('deleteDocument', function(event, agrs) {
		if(agrs.done) {
			$mdDialog.hide();
			$window.location.href = $window.location;
		}
	});
	setLang = function(langKey) {
        // You can change the language during runtime
        $translate.use(langKey).then(function () {
        	msgTitle = $translate.instant('doc.file.msg.title');
        	msgContent = $translate.instant('doc.file.msg.content');
        	msgOk = $translate.instant('doc.file.msg.ok');
        	msgCancel = $translate.instant('doc.file.msg.cancel');
        	msgDelSuccess = $translate.instant('doc.file.msg.content.delete.folder.msg.success');
    		msgDelFail = $translate.instant('doc.file.msg.content.delete.folder.msg.fail');
    		msgDelErr = $translate.instant('doc.file.msg.content.delete.folder.msg.err');
        });
    }
	
}]);