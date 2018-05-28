app.controller('docDirectoryUndoCtrl', ['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                                		'$window', '$state', 'docManagementService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, $state, docManagementService) {

	var msgTitle = '';
	var msgContent = '';
	var msgOk = '';
	var msgCancel = '';
	var msgDelSuccess = '';
	var msgDelFail = '';
	var msgDelErr = '';
	var msgSaveSuccess = '';
	var msgSaveFail = '';
	var msgAlarm = '';
	var alertAlarm = '';
	var arrFolders = [];
	var flag = 1;
	var directoryIds = '';
	$scope.selectedAvailItems = [];
	
	getDirectoryFromTrash = function(){
		var lan = $location.search().lang;
		docManagementService.getDirectoryFromTrash($scope);
	}
	
	getDirectory = function(){
		docManagementService.getDirectory($scope);
	}
	
	$scope.$on('getDirectoryAlready', function() {
		if ($scope.data) {
			$scope.availDirectories = $scope.data.result.listDirectories;
		}
	});
	
	setLang = function(langKey) {
        // You can change the language during runtime
        $translate.use(langKey).then(function () {
        	msgTitle = $translate.instant('doc.file.msg.title');
        	//msgContent = $translate.instant('doc.file.msg.content');
        	msgOk = $translate.instant('doc.file.msg.ok');
        	msgCancel = $translate.instant('doc.file.msg.cancel');
        	msgDelSuccess = $translate.instant('doc.file.msg.content.delete.folder.msg.success');
    		msgDelFail = $translate.instant('doc.file.msg.content.delete.folder.msg.fail');
    		msgDelErr = $translate.instant('doc.file.msg.content.delete.folder.msg.err');
    		msgSaveSuccess = $translate.instant('doc.file.msg.content.save.folder.msg.success');
    		msgSaveFail = $translate.instant('doc.file.msg.content.save.folder.msg.fail');
    		msgAlarm = $translate.instant('doc.folder.form.multichoice.alert.len.folder');
    		alertAlarm = $translate.instant('doc.folder.form.multichoice.alert.allow.folder');
        });
    }
	
	$scope.$on('getDirectoryFromTrashAlready', function() {
		if ($scope.data) {
			$scope.selectedAvailItems  = $scope.data.result.listDirectories;
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
		}
	});
	
	$scope.undoDirectory = function (event){
		if($scope.treetrash02.currentNode!=undefined){
			$scope.ids = '';
			var folders = [];
			arrFolders.push($scope.treetrash02.currentNode);
			$scope.ids += arrFolders[arrFolders.length-1].id;
			flag = 1;
			while(flag===1){
				var n = findItem($scope.selectedAvailItems , arrFolders[arrFolders.length-1]);
				if(n!=undefined){
					arrFolders.push(n);
					$scope.ids = $scope.ids + "-" + arrFolders[arrFolders.length-1].id;
				}
			}
			if($scope.ids.search("-")!=-1){
				docManagementService.setDirectoryIds($scope);
				showConfirmUndo(event);
			}
			else
				docManagementService.undoFromTrash($scope, $scope.ids);
			//$scope.treetrash02.currentNode.selected = undefined;
		}
	}
	
	$scope.btnOk = function(){
		directoryIds = docManagementService.getDirectoryIds();
		docManagementService.undoFromTrash($scope, directoryIds);
		closeDialog();
	}
	
	$scope.btnCancel = function(){
		closeDialog();
	}
	
	$scope.btnDelete = function() {
		var node = docManagementService.getNode();
		var location = node.location;
		if(location != null)
			location = location.replace(/\\/g, "-") + node.name;
		deleteDirectory(node.id, location);
	}
	
	deleteDirectory = function(id, location){
		var result = docManagementService.delete(id, location)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				getDirectoryFromTrash();
				closeDialog();
				$window.location.reload();
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
	} 
	
	$scope.$on('undoFromTrashSuccess', function() {
		if ($scope.data) {
			if($scope.data.data.status==1){
				getDirectory();
				getDirectoryFromTrash();
				//$window.location.reload();
			}
		}
	});
	
var appendArray = function(sourceArray, destinationArray, parentNode) {
		var len = sourceArray.length;
		for(i = 0; i < len; i++) {
			destinationArray.push({parent: parentNode, node: sourceArray[i]});
		}
	}
	  
var findItem = function(treeNodes, node) {
	  var treeNodesTemp = [];
	  arrFolders = [];
	  // Copy tree.
	  appendArray(treeNodes, treeNodesTemp, null);
	  // Found and delete node.
	  len = treeNodesTemp.length - 1;
	  while(len > -1) {
	  // Get last node from temp.
		  var currentNode = treeNodesTemp[len];
		  treeNodesTemp.splice(len, 1);
  // Check last node.
		  if(currentNode.node.id === node.parentId) {
			  return currentNode.node;
		  }
		  if(currentNode.node.subFolders.length > 0) {
			  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
		  }
		  len = treeNodesTemp.length - 1;
	  }
	  flag = 0;
	}

	getDirectory();
	getDirectoryFromTrash();
	
	showConfirmUndo = function(ev) {
	    $mdDialog.show({
	      skipHide: true,
	      scope: $scope,
	      preserveScope: true,
	      controller: function DialogController($scope, $mdDialog) {
				
	            closeDialog = function() {
	              $mdDialog.hide();
	            }
	            
			},
			template: 
				  '<div>' +	
		          '<md-dialog>' +
		          '	 <md-toolbar> ' +
		          '	   <div> ' +
		          ' 	  <span flex>{{ ' + '\'' + 'doc.file.dialog.custom.title' + '\'' + '| translate}}</span>' +
		          '    </div>' +
		          '  </md-toolbar>' +
		          '  <md-dialog-content>' +
		          '    <md-input-container> ' + 
		          '       <span>{{ ' + '\'' + 'doc.file.dialog.custom.content' + '\'' + '| translate}}</span> ' +
		          '     </md-input-container>' +
		          '  </md-dialog-content>' +
		          '  <md-dialog-actions layout="row">' +
		          '    <md-button ng-click="btnCancel();" class="md-primary">' +
		          '      {{ ' + '\'' + 'doc.file.msg.cancel' + '\'' + '| translate}}' +
		          '    </md-button>' +
		          '    <md-button ng-click="btnOk();" class="md-primary">' +
		          '      {{ ' + '\'' + 'doc.file.msg.ok' + '\'' + '| translate}}' +
		          '    </md-button>' +
		          '  </md-dialog-actions>' +
		          '</md-dialog>' +
		          '<\div>',
	      targetEvent: ev,
	      clickOutsideToClose:false,
	      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
	    });
	  };
	  
	  $scope.showConfirmDel = function(ev) {
		  if($scope.treetrash02.currentNode!=undefined){
			  	docManagementService.setNode($scope.treetrash02.currentNode);
			  	$mdDialog.show({
			      skipHide: true,
			      scope: $scope,
			      preserveScope: true,
			      controller: function DialogController($scope, $mdDialog) {
						
			            closeDialog = function() {
			              $mdDialog.hide();
			            }
			            
					},
					template: 
						  '<div>' +	
				          '<md-dialog>' +
				          '	 <md-toolbar> ' +
				          '	   <div> ' +
				          ' 	  <span flex>{{ ' + '\'' + 'doc.file.dialog.custom.title' + '\'' + '| translate}}</span>' +
				          '    </div>' +
				          '  </md-toolbar>' +
				          '  <md-dialog-content>' +
				          '    <md-input-container> ' + 
				          '       <span>{{ ' + '\'' + 'doc.file.msg.content.delete.folder' + '\'' + '| translate}}</span> ' +
				          '     </md-input-container>' +
				          '  </md-dialog-content>' +
				          '  <md-dialog-actions layout="row">' +
				          '    <md-button ng-click="btnCancel();" class="md-primary">' +
				          '      {{ ' + '\'' + 'doc.file.msg.cancel' + '\'' + '| translate}}' +
				          '    </md-button>' +
				          '    <md-button ng-click="btnDelete();" class="md-primary">' +
				          '      {{ ' + '\'' + 'doc.file.msg.ok' + '\'' + '| translate}}' +
				          '    </md-button>' +
				          '  </md-dialog-actions>' +
				          '</md-dialog>' +
				          '<\div>',
			      targetEvent: ev,
			      clickOutsideToClose:false,
			      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
			    });
		  	}
		  };
		
	// Show message.
	$scope.showMessage = function(message, cssName, autoHide) {
		$scope.alertMessage = message;
		$('#alertMessage').addClass(cssName);
		$('#alertMessage').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessage').slideUp(500, function() {
						$('#alertMessage').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}
}]);


	

	