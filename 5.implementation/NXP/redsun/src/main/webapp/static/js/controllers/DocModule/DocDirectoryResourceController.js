app.controller('docDirectoryResourceCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                                   		'$window', '$element', 'docManagementService', 'resourceService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, $element, docManagementService, resourceService) {

	$scope.SelectedAvailItems = [];
	$scope.availDirectories = [];
	$scope.assignToResources = [];
	var splitTree = [];
	$scope.searchTerm;
	$scope.selectedResource = [];
	$scope.chkType = false;
	$scope.chkAssign = false;
	var alertResource = '';
	var alertFolder = '';
	var alertAlarm = '';
	var msgSaveSuccess = '';
	var msgSaveFail = '';
	$scope.subject = 'V/v phân quyền thư mục';
	$scope.content = 'Bạn đã được phân quyền làm việc trên thư mục';
	
    $scope.clearSearchTerm = function() {
      $scope.searchTerm = '';
    };
    // The md-select directive eats keydown events for some quick select
    // logic. Since we have a search input here, we don't need that logic.
    $element.find('input').on('keydown', function(ev) {
        ev.stopPropagation();
    });
    
    $scope.initTreeRight = function(){
    	$scope.SelectedAvailItems = [];
    	getResourceAllType();
    	//getResources();
    }
	getDirectory = function(){
		docManagementService.getDirectory($scope);
	}
	
	$scope.getDirectoryByUser = function(user){
		$scope.userName = user.code;
		docManagementService.getDirectoryByUser($scope);
	}
	$scope.$on('getDirectoryAlready', function() {
		if ($scope.data) {
			$scope.availDirectories = $scope.data.result.listDirectories;
		}
	});
	
	$scope.$on('getDirectoryByUserAlready', function() {
		if ($scope.data) {
			$scope.SelectedAvailItems = $scope.data.result.listDirectories;
		}
	});
	
	// get resource
	getResources = function() {
		resourceService.getResources($scope);
	};
	getResourceAllType = function() {
		resourceService.getResourceAllType($scope);
	};
	
	getResourceAssigned = function() {
		docManagementService.getResourceAssigned($scope);
	};

	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
			getResourceAssigned();
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
		}
	});
	
	$scope.$on('getResourceAllTypeAlready', function() {
		if ($scope.data) {
			var resourceAllType = $scope.data.result.resources;
			$scope.resources = [];
			for(var i=0; i<resourceAllType.length; i++){
				if($scope.chkType == false){
					if(resourceAllType[i].isContact == 0)
						$scope.resources.push(resourceAllType[i]);
				}
				else
					if(resourceAllType[i].isContact == 1)
						$scope.resources.push(resourceAllType[i]);
			}
			
			getResourceAssigned();
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
		}
	});
	
	$scope.$on('getResourceAssignedAlready', function() {
		if ($scope.data) {
			$scope.resourcesAssigned = $scope.data.result.listResourceAssigned;
			var newResource = [];
			var assginedResource = [];
			for(var i=0; i<$scope.resources.length; i++){
				var flag = 0;
				for(var j=0; j<$scope.resourcesAssigned.length; j++)
					if($scope.resources[i].code == $scope.resourcesAssigned[j].createUserName){
						flag=1;
						break;
					}
				if(flag===0)
					newResource.push($scope.resources[i]);
				else
					assginedResource.push($scope.resources[i]);
			}
			$scope.resources = newResource;
			$scope.resourcesAssigned = assginedResource;
			
		}
	});
	
	$scope.btnRight = function(){
		if($scope.treeas01.currentNode != undefined){
			var node = angular.copy($scope.treeas01.currentNode);
			//node.subFolders = [];
			moveRight(node);
		}
	}
	
	moveRight = function(node){
		if($scope.SelectedAvailItems.length == 0){
			$scope.SelectedAvailItems.push(angular.copy(node));
			$scope.treeas01.currentNode.selected = undefined;
		}
		else{
			if(isExitsNode($scope.SelectedAvailItems, $scope.treeas01.currentNode)==1)
				alert(alertAlarm);
			else{
				if(node.subFolders.length > 0){
					var len = $scope.SelectedAvailItems.length;
					for(var i=0; i<$scope.SelectedAvailItems.length; i++){
						deleteSubNodeRight(node.subFolders, $scope.SelectedAvailItems[i]);
						if($scope.SelectedAvailItems.length<len){
							i=-1;
							len = $scope.SelectedAvailItems.length;
						}
					}
					$scope.SelectedAvailItems.push(angular.copy(node));
					$scope.treeas01.currentNode.selected = undefined;
				}
				else{
					$scope.SelectedAvailItems.push(angular.copy(node));
					$scope.treeas01.currentNode.selected = undefined;
				}
			}
		}
	}
	$scope.btnLeftAll = function (){
		deleteNode($scope.SelectedAvailItems, $scope.treeas02.currentNode);
	}
	$scope.btnLeft = function (){
		deleteParentNode($scope.SelectedAvailItems, $scope.treeas02.currentNode);
	}
	var appendArray = function(sourceArray, destinationArray, parentNode) {
		var len = sourceArray.length;
		for(i = 0; i < len; i++) {
			destinationArray.push({parent: parentNode, node: sourceArray[i]});
		}
	}
	  
	var isExitsNode = function(treeNodes, node) {
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
		  if(currentNode.node.id === node.id) {
			  return 1;
		  }
		  if(currentNode.node.subFolders != null)
			  if(currentNode.node.subFolders.length > 0) {
				  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
			  }
		  len = treeNodesTemp.length - 1;
	  }
	  return 0;
	}

	var deleteSubNodeRight = function(treeNodes, node) {
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
			  if(currentNode.node.id === node.id) {
				  deleteNode($scope.SelectedAvailItems, node);
			  }
			  
			  if(currentNode.node.subFolders != null)
				  if(currentNode.node.subFolders.length > 0) {
					  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
				  }
			  
			  len = treeNodesTemp.length - 1;
		  }
		}
	
	var deleteNode = function(treeNodes, node) {
		  var treeNodesTemp = [];
		  // Copy tree.
		  appendArray(treeNodes, treeNodesTemp, null);
		  // Found and delete node.
		  len = treeNodesTemp.length - 1;
		  while(len > -1) {
			  // Get last node from temp.
			  var currentNode = treeNodesTemp[len];
			  treeNodesTemp.splice(len, 1);
			  // Check last node.
			  if(currentNode.node.id === node.id) {
				  var children = treeNodes;
				  if(currentNode.parent) {
					  children = currentNode.parent.subFolders;
				  }
				  children.remove(node);
				  return;
			  }
			  if(currentNode.node.subFolders != null)
				  if(currentNode.node.subFolders.length > 0) {
					  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
				  }
			  len = treeNodesTemp.length - 1;
		  }
	  }
	
	var deleteParentNode = function(treeNodes, node) {
		  var treeNodesTemp = [];
		  // Copy tree.
		  appendArray(treeNodes, treeNodesTemp, null);
		  // Found and delete node.
		  len = treeNodesTemp.length - 1;
		  while(len > -1) {
			  // Get last node from temp.
			  var currentNode = treeNodesTemp[len];
			  treeNodesTemp.splice(len, 1);
			  // Check last node.
			  if(currentNode.node.id === node.id) {
				  var children = treeNodes;
				  if(currentNode.parent) {
					  children = currentNode.parent.subFolders;
				  }
				  if(node.subFolders.length>0)
					  for(var i=0; i<node.subFolders.length; i++)
						  $scope.SelectedAvailItems.push(node.subFolders[i]);
				  children.remove(node);
				  return;
			  }
			  if(currentNode.node.subFolders != null)
				  if(currentNode.node.subFolders.length > 0) {
					  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
				  }
			  len = treeNodesTemp.length - 1;
		  }
	  }
	
	var deleteEachNode = function(treeNodes, node) {
		  var treeNodesTemp = [];
		  // Copy tree.
		  appendArray(treeNodes, treeNodesTemp, null);
		  // Found and delete node.
		  len = treeNodesTemp.length - 1;
		  while(len > -1) {
			  // Get last node from temp.
			  var currentNode = treeNodesTemp[len];
			  treeNodesTemp.splice(len, 1);
			  // Check last node.
			  if(currentNode.node.id === node.id) {
				  var children = treeNodes;
				  if(currentNode.parent) {
					  children = currentNode.parent.subFolders;
				  }
				  if(node.subFolders.length>0)
					  for(var i=0; i<node.subFolders.length; i++)
						  treeNodes.push(node.subFolders[i]);
				  children.remove(node);
				  return;
			  }
			  if(currentNode.node.subFolders != null)
				  if(currentNode.node.subFolders.length > 0) {
					  appendArray(currentNode.node.subFolders, treeNodesTemp, currentNode.node);
				  }
			  len = treeNodesTemp.length - 1;
		  }
	  }

	
	Array.prototype.remove = function() {
	    var what, a = arguments,
			L = a.length,
			ax;
	    while (L && this.length) {
	    	what = a[--L];
	    	while ((ax = this.indexOf(what)) != -1) {
	    		this.splice(ax, 1);
	    	}
	    }
	    return this;
	}

	convertTreeToArray = function(treeView){
		while(treeView.length>0){
			splitTree.push(treeView[treeView.length-1].id);
			deleteEachNode(treeView, treeView[treeView.length-1]);
		}
	}
	
	$scope.btnSave = function(){
		var selectedAvailItemClone = []; 
		angular.copy($scope.SelectedAvailItems, selectedAvailItemClone);
		convertTreeToArray(selectedAvailItemClone);
		saveFolderToResource(splitTree, $scope.selectedResource);
	}
	
	saveFolderToResource = function(subFolder, resources){
		var lenSelectedItem = subFolder.length;
		var lenResource = resources.length;
		var assignToResource = {"directoryId": 0, "projectId": 0, "resourceId": 0, "clientId": 0, "userName": ''};
		if($scope.chkAssign == false){
			if(lenResource == 0){
				alert(alertResource);
				return;
			}
			else if(lenSelectedItem == 0){
				alert(alertFolder);
				return;
			}
			else{
				for(var i=0; i<lenSelectedItem; i++){
					for(var j=0; j<lenResource; j++){
						assignToResource = {"directoryId": subFolder[i], "projectId": 0, "resourceId": resources[j].id, "clientId": 0, "userName": resources[j].code};
						$scope.assignToResources.push(assignToResource);
					}
					/*
					if(subFolder[i].subFolders.length>0)
						saveFolderToResource(subFolder[i].subFolders, resources);
					*/
						
				}
			}
		}
		else if($scope.chkAssign == true){
			if(lenResource != undefined){
				if(lenResource == 0){
					alert(alertResource);
					return;
				}
			}
			else{
			if(lenSelectedItem == 0){
				assignToResource = {"directoryId": 0, "projectId": 0, "resourceId": resources.id, "clientId": 0, "userName": resources.code};
				$scope.assignToResources.push(assignToResource);
			}
			else{
					for(var i=0; i<lenSelectedItem; i++){
						assignToResource = {"directoryId": subFolder[i], "projectId": 0, "resourceId": resources.id, "clientId": 0, "userName": resources.code};
						$scope.assignToResources.push(assignToResource);
					}
				}
			}
		}
			docManagementService.addFolderToResource($scope)
			.success(function(response) {
				response = angular.fromJson(response);
				if(response[0].status === 1){
					$scope.showMessage(msgSaveSuccess, 'alert-success', true);
					sendEmail();
				} else {
					$scope.showMessage(msgSaveFail, 'alert-danger', true);
				}
			})
			// error.
			.error(function(response) {
				$scope.showMessage('Error!', 'alert-danger', true);
			});
	}

	// Show message.
	$scope.showMessage = function(message, cssName, autoHide) {
		$scope.alertMessage = message;
		$('#alertMessageASR').addClass(cssName);
		$('#alertMessageASR').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessageASR').slideUp(500, function() {
						$('#alertMessageASR').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}
	
	setLang = function(langKey) {
        // You can change the language during runtime
        $translate.use(langKey).then(function () {
        	alertResource = $translate.instant('doc.folder.form.multichoice.alert.len.resource');
        	alertFolder = $translate.instant('doc.folder.form.multichoice.alert.len.folder');
        	msgSaveSuccess = $translate.instant('doc.file.msg.content.save.folder.msg.success');
    		msgSaveFail = $translate.instant('doc.file.msg.content.save.folder.msg.fail');
    		alertAlarm = $translate.instant('doc.folder.form.multichoice.alert.allow.folder');
        });

    }
	
	sendEmail = function(){
		var listEmail = '';
		if($scope.chkFolder){
			var lenSelectedResource = $scope.selectedResource.length;
			if(lenSelectedResource == undefined){
				listEmail = $scope.selectedResource.email;
			}
			else{
				for(var i=0; i<$scope.selectedResource.length; i++){
					listEmail += $scope.selectedResource[i].email;
					if(i<$scope.selectedResource.length-1)
						listEmail += ',';
				}
			}
			if(listEmail != ''){
				$scope.toEmail = listEmail;
				docManagementService.sendEmail($scope);
			}
		}
	}
	
	$scope.btnOk = function(){
		closeDialog();
	}
	
	$scope.showConfigMailDoc = function(ev) {		
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
					 // '<div>' +	
			          '<md-dialog>' +
			          '	 <md-toolbar> ' +
			          '	   <div> ' +
			          ' 	  <span flex>{{ ' + '\'' + 'doc.file.dialog.custom.mail.title' + '\'' + '| translate}}</span>' +
			          '    </div>' +
			          '  </md-toolbar>' +
			          '  <md-dialog-content>' +
			          '    <md-input-container> ' +
			          '       <span>{{ ' + '\'' + 'doc.file.dialog.custom.mail.subject' + '\'' + '| translate}}</span> ' +
			          '       <input type="text" ng-model="subject" placeholder="{{ ' + '\'' + 'doc.file.dialog.custom.mail.title' + '\'' + '| translate}}"><br> ' +
			          '       <span>{{ ' + '\'' + 'doc.file.dialog.custom.mail.content' + '\'' + '| translate}}</span> ' +
			          ' 	  <textarea rows="4" cols="50" ng-model="content"></textarea> ' +
			          '     </md-input-container>' +
			          '  </md-dialog-content>' +
			          '  <md-dialog-actions layout="row">' +
			          '    <md-button ng-click="btnOk();" class="md-primary">' +
			          '      {{ ' + '\'' + 'doc.file.msg.ok' + '\'' + '| translate}}' +
			          '    </md-button>' +
			          '  </md-dialog-actions>' +
			          '</md-dialog>', //+
			          //'<\div>',
		      targetEvent: ev,
		      clickOutsideToClose:false,
		      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		    });
	  };
	  
	getResourceAllType();
	//getResources();
	getDirectory();
}]);


	

	