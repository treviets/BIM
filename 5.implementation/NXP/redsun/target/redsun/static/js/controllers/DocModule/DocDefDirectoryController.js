app.controller('docDefDirectoryCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'docManagementService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, docManagementService) {

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
	$scope.directory = {};
	$scope.defaultDirectories = [];
	$scope.moduleRoleKey = "document_0";
	$scope.initEditDocDirectory =  function(){
		$scope.module_permission_key = "document_0"; 
		// get role for user login
		moduleService.getModuleRole($scope);
		
	}
	getDefaultDirectory = function(){
		docManagementService.getDefaultDirectory($scope);
	}
	$scope.$on('getDefaultDirectoryAlready', function() {
		if ($scope.data) {
			$scope.defaultDirectories = $scope.data.result.listDefaultDirectories;
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
			if($scope.defaultDirectories.length == 0)
				$scope.treedef.currentNode = null;
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
    		msgSaving = $translate.instant('doc.file.msg.content.save.folder.msg.saving');
    		msgSaveSuccess = $translate.instant('doc.file.msg.content.save.folder.msg.success');
    		msgSaveFail = $translate.instant('doc.file.msg.content.save.folder.msg.fail');
    		msgAlarm = $translate.instant('doc.folder.form.multichoice.alert.len.folder');
        });
    }
	// Init for edit.
	$scope.initEdit = function(id) {
		$scope.createNew();
		
		$scope.directory.id = id;
		if($scope.directory.id > -1) {
			$scope.getDefById($scope.directory.id);
		}
		
		$scope.frmDirty = false;
	}
	
	// Show a add screen.
	$scope.addDef = function() {
		$scope.initEdit(-1);
		$scope.visAdd = 1;
		$scope.visEdit = 0;
	}
	
	// Show a edit screen.
	$scope.editDef = function() {
		if(!$scope.treedef.currentNode) {
			alert(msgAlarm, 'alert-danger', true);
			return;
		}
		$scope.initEdit($scope.treedef.currentNode.id);
		$scope.visEdit = 1;
		$scope.visAdd = 0;
	} 
	// Show a dialog folder default screen.
		$scope.showFolderDafaultDialog = function() {
			// Show a dialog.
			$templateRequest("/redsun/static/partials/directory-structure-default.html").then(function(html){
				
				$compile(html);
				var fromPopup = $mdDialog.confirm({
					template : html,
					clickOutsideToClose:false,
					scope: $scope,
					preserveScope: true,  // do not forget this if use parent scope
					controller: function DialogController($scope, $mdDialog) {
						
			            $scope.closeDialog = function() {
			              $mdDialog.hide();
			            }
			            
					}
				});
				$mdDialog.show(fromPopup).then(function() {
				}, function() {
					//getDirectory();
				});
			});
			
		}
		
	// Create new.
	$scope.createNew = function() {
		$scope.directory = {id: -1, projectId: $scope.projectId}
		$scope.directory.attachments = [];
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.frmDirty = false;
	}
	
	// Save.
	$scope.save = function() {
		
		if($scope.frmDirectory.$invalid) {
			$scope.frmDirectory.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.showMessageForm(msgSaving, 'alert-success', false);
		// Ge parent directory info.
		if(($scope.treedef.currentNode || $scope.treedef.currentNode != null) && $scope.directory.id !== $scope.treedef.currentNode.id) {
			var location = $scope.treedef.currentNode.name;
			if($scope.treedef.currentNode.location || $scope.treedef.currentNode.location != null) {
				location = $scope.treedef.currentNode.location + location;
			}
			location += "\\";
			$scope.directory.location = location;
			$scope.directory.parentId = $scope.treedef.currentNode.id;
			//$scope.directory.projectId = $scope.treedef.currentNode.projectId;
			//$scope.directory.clientId = $scope.treedef.currentNode.clientId;
		}
		// Update.
		if($scope.directory.id > -1) {
			docManagementService.updateDef($scope.directory.id, $scope.directory)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1){
					$scope.directory.id = response.result.id;
					if($scope.treedef.currentNode) {
						$scope.treedef.currentNode.name = $scope.directory.name;
					}
					$scope.showMessageForm(msgSaveSuccess, 'alert-success', true);
					$scope.visEdit = 0;
					$scope.treedef.currentNode.selected = undefined;
				    $scope.treedef.currentNode = undefined;
				} else if(response && response.status === -1) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else if(response && response.status === -3) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else {
					$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.showMessageForm(msgDelErr, 'alert-danger', true);
			});
		} else { // Insert.
			docManagementService.addDef($scope.directory)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$scope.directory.id = response.result.id;
					$scope.$broadcast('insertDefSuccess', {done: true});
				} else if(response && response.status === -2) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else {
					$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.showMessageForm(msgDelErr, 'alert-danger', true);
			});
		}
	}
	
	$scope.$on('insertDefSuccess', function(event, agrs) {
		if (agrs.done) {
			if($scope.treedef.currentNode != undefined)
				$scope.treedef.currentNode.subFolders.push(angular.copy($scope.directory));
			else
				getDefaultDirectory();
			$scope.showMessageForm(msgSaveSuccess, 'alert-success', true);
			$scope.visAdd = 0;
			$scope.treedef.currentNode.selected = undefined;
		    $scope.treedef.currentNode = undefined;
		    $scope.directory = {};
		}
	});
	
	
	deleteDirectoryDef = function(id, location){
		var result = docManagementService.deleteDef(id, location)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				$scope.showMessageForm(msgDelSuccess, 'alert-success', true);
				$scope.$broadcast('deleteDefSuccess', {done: true});
				//$scope.getsForPageAndFilter(1);
				closeDialog();
				
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
	} 
	
	$scope.$on('deleteDefSuccess', function(event, agrs) {
		if (agrs.done) {
			getDefaultDirectory();
		}
	});
	
	// Get by Id.
	$scope.getDefById = function(id) {
		docManagementService.getDefById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.directory !== undefined) {
				if(response.result.directory.length > 0) {
					$scope.directory = response.result.directory[0];
				}
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
	}
	
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

	// Show message.
	$scope.showMessageForm = function(message, cssName, autoHide) {
		$scope.alertMessageForm = message;
		$('#alertDefMessageForm').addClass(cssName);
		$('#alertDefMessageForm').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertDefMessageForm').slideUp(500, function() {
						$('#alertDefMessageForm').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}
	
	$scope.btnCancel = function(){
		closeDialog();
	}
	
	 //Delete folder
	$scope.btnDeleteDef = function() {
		var location = $scope.treedef.currentNode.location;
		if(location == null)
			location = $scope.treedef.currentNode.name;
		else{
			location = location.replace(/\//g, "-") + $scope.treedef.currentNode.name;
			location = location.replace(/\\/g, "-");
		}
		deleteDirectoryDef($scope.treedef.currentNode.id, location);		
	}
	
	$scope.showConfirmDel = function(ev) {
		  if($scope.treedef.currentNode!=undefined){
			  	docManagementService.setNode($scope.treedef.currentNode);
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
				          '    <md-button ng-click="btnDeleteDef();" class="md-primary">' +
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
		  
	$scope.initParam = function(doc){
		$scope.chkDoc = doc;
	}
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
	
	 //show confirm dialog
	$scope.showConfirm = function(event, doc) {
		var confirm = $mdDialog.confirm()
			.title(msgTitle)
			.textContent(msgContent)
			.ariaLabel('Lucky day')
			.targetEvent(event)
			.ok(msgOk)
			.cancel(msgCancel);

		$mdDialog.show(confirm).then(function() {
				$scope.deleteDocument(doc);
			}, function() {
				$scope.status = 'Không thực hiện';
		});
		
	};
	
	$scope.btnOk = function(){
		closeDialog();
	}
	getDefaultDirectory();
}]);


	

	